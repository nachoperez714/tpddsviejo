package vm;

import java.math.BigDecimal;
import java.util.List;

import org.uqbar.commons.utils.Observable;



import model.Empresa;
import model.Indicador;
import repositories.Repositorios;

@Observable
public class VerIndicadoresViewModel {
	
	//------------------------------------- ATRIBUTOS ----------------------------------
	
	private List<Indicador> indicadores;
	private BigDecimal resultado;
	private Empresa empresa;
	private String anio;
	private Indicador indicadorSeleccionado;

	//----------------------------------- CONSTRUCTORES --------------------------------
	
	
	public VerIndicadoresViewModel(Empresa unaEmpresa)
	{
		Repositorios.repositorioIndicadores.obtenerIndicadores();
		this.indicadores = Repositorios.repositorioIndicadores.getIndicadores();
		this.empresa = unaEmpresa;
	}
	

	//------------------------------- GETTERS Y SETTERS --------------------------------
	
	public String getAnio() {
		return anio;
	}
	
	public void setAnio(String anio) {
		this.anio = anio;
	}

	public void setIndicadorSeleccionado(Indicador indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}

	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public Indicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}

	public BigDecimal getResultado() {
		return resultado;
	}		

	//--------------------------------------- METODOS ----------------------------------
	
	public void aplicarIndicador() throws NullPointerException
	{
		this.resultado = this.indicadorSeleccionado.calcularMonto(empresa, anio);
	}

}
