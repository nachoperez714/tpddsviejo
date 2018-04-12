import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import repositories.Repositorios;
import model.Condicion;
import model.AnalizarIndicadorEnUltimosAnios;
import model.AnalizarIndicadoresEntreEmpresas;
import model.AnalizarCrecimientoEnPeriodo;
import model.Cuenta;
import model.Empresa;
import model.Indicador;
import model.Longevidad;
import model.MayorA;
import model.MenorA;
import model.Metodologia;


public class MetodologiasTest {
	
		private AnalizarIndicadoresEntreEmpresas unaCondicion;
		private Empresa unaEmpresa;
		private Empresa otraEmpresa;
		private Indicador indicador;
		
		@Before
		public void init() {
			
			Empresa facebook = new Empresa("Facebook", 2004);
			Empresa twitter = new Empresa("Twitter", 2006);
			
			//Para Facebook daria ROE = 0.5 | ProporcionDeuda = 1 | Margen = 2  
			
			Cuenta fEbitda = new Cuenta("EBITDA",2015,"75", facebook);
			Cuenta fFds = new Cuenta("FDS",2015,"150", facebook);
			Cuenta fDividendos = new Cuenta("dividendos",2015,"50", facebook);
			Cuenta fCapitalTotal = new Cuenta("capitalTotal",2015,"200", facebook);
			Cuenta fIngresoNeto = new Cuenta("ingresoNeto",2015,"150", facebook);
			
			//Para Twitter daria ROE = 0.1 | ProporcionDeuda = 2 | Margen = 1
			
			Cuenta tEbitda = new Cuenta("EBITDA",2015,"50", twitter);
			Cuenta tFds = new Cuenta("FDS",2015,"100", twitter);
			Cuenta tDividendos = new Cuenta("dividendos",2015,"40", twitter);
			Cuenta tCapitalTotal= new Cuenta("capitalTotal",2015,"100", twitter);
			Cuenta tIngresoNeto = new Cuenta("ingresoNeto",2015,"50", twitter);
			
			Repositorios.repositorioEmpresas.agregarEmpresa(facebook);
			Repositorios.repositorioEmpresas.agregarEmpresa(twitter);

			facebook.agregarCuenta(fEbitda);
			facebook.agregarCuenta(fCapitalTotal);
			facebook.agregarCuenta(fFds);
			facebook.agregarCuenta(fIngresoNeto);
			facebook.agregarCuenta(fDividendos);

			twitter.agregarCuenta(tEbitda);
			twitter.agregarCuenta(tCapitalTotal);
			twitter.agregarCuenta(tFds);
			twitter.agregarCuenta(tIngresoNeto);
			twitter.agregarCuenta(tDividendos);

			indicador = new Indicador("humo", "2*(EBITDA+2000)");
			unaCondicion = new AnalizarIndicadoresEntreEmpresas("2015", new MayorA(), indicador);
			unaEmpresa = Repositorios.repositorioEmpresas.find(empresa -> empresa.getNombre().equals("Facebook"));
			otraEmpresa = Repositorios.repositorioEmpresas.find(empresa -> empresa.getNombre().equals("Twitter"));			
		}
		
		@Test
		public void condictionTipo3Compara() {
			unaCondicion.compararEmpresas(unaEmpresa, otraEmpresa);
			Assert.assertEquals(1, unaEmpresa.getPuntacion());
		}
		
		@Test
		public void metodologiaWarrenBuffet()
		{
			Indicador roe = new Indicador("ROE", "(ingresoNeto-dividendos)/capitalTotal");
			Indicador proporcionDeDeuda = new Indicador("ProporcionDeuda", "FDS/ingresoNeto");
			Indicador margen = new Indicador("Margen", "ingresoNeto/EBITDA");
			
			//Para Facebook daria ROE = 0.5 | ProporcionDeuda = 1 | Margen = 2  
			//Para Twitter daria ROE = 0.1 | ProporcionDeuda = 2 | Margen = 1
			//Los años que no tienen cuentas no se tiene en cuenta (suma 0)
			
			//Deberia sumarle a facebook 0.5 > 0.1
			Condicion condicionROE = new AnalizarIndicadorEnUltimosAnios(roe,  new MayorA(), 10);
			//Deberia sumarle a facebook 1 < 2 
			Condicion condicionDeuda = new AnalizarIndicadoresEntreEmpresas("2015", new MenorA(),proporcionDeDeuda);
			//Deberia sumarle a facebook 2 > 1
			Condicion condicionMargen = new AnalizarCrecimientoEnPeriodo(margen, new MayorA(),10);
			//Deberia sumarle a facebook es mas antiguo 2004 < 2006
			Condicion condicionLongevidad = new Longevidad();
			
			List<Condicion> condiciones = new ArrayList<Condicion>();
			condiciones.add(condicionROE);
			condiciones.add(condicionDeuda);
			condiciones.add(condicionMargen);
			condiciones.add(condicionLongevidad);
			
			Metodologia warrenBuffet = new Metodologia("Warren Buffet", condiciones);
			List<Empresa> empresas = new ArrayList<Empresa>();
			empresas.add(unaEmpresa);
			empresas.add(otraEmpresa);
			List<Empresa> empresasOrdenadas = warrenBuffet.aplicarMetodologia(empresas);
			Empresa primera = empresasOrdenadas.get(0);
			Empresa segunda = empresasOrdenadas.get(1);
			Assert.assertEquals(4, primera.getPuntacion());
			Assert.assertEquals(0, segunda.getPuntacion());
			Assert.assertEquals(primera.getNombre(), "Facebook");
		}
		
		@After
		public void end()
		{
			Repositorios.repositorioEmpresas.limpiar();
			
		}

}

