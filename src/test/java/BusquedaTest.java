import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import repositories.Repositorios;
import model.Cuenta;
import model.Empresa;

public class BusquedaTest{
	
	private Cuenta unaCuenta;
	private Cuenta otraCuenta;
	
	@Before
	public void init() {
		Empresa facebook = new Empresa("Facebook", 2004);
		unaCuenta = new Cuenta("PDS",2015,"200000", facebook);
		otraCuenta = new Cuenta("PDS",2012,"200000", facebook);
		facebook.agregarCuenta(unaCuenta);
		facebook.agregarCuenta(otraCuenta);
		Repositorios.repositorioEmpresas.agregarEmpresa(facebook);
	}
	
	@Test
	public void repositorioEmpresasFiltraLasCuentas() {
		Assert.assertEquals(unaCuenta,Repositorios.repositorioEmpresas.filtrarCuentasPorPeriodo("Facebook","2013","2016").get(0));
	}
	
	@After
	public void end() {
		Repositorios.repositorioEmpresas.limpiar();
	}	
}