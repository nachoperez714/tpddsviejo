package vm;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import repositories.Repositorios;
import model.AnalizarCrecimientoEnPeriodo;
import model.AnalizarHistoriaDelIndicador;
import model.AnalizarIndicadorEnUltimosAnios;
import model.Condicion;
import model.AnalizarIndicadoresEntreEmpresas;
import model.Indicador;
import model.Longevidad;
import model.Operacion;
import model.OperacionIndicador;

@Observable
public class ConfigurarCondicionViewModel {
	
	//------------------------------------- CONSTANTES ----------------------------------
	
	static final String TIPO1 = "Analizar indicador en los ultimos anios";
	static final String TIPO2 = "Analizar indicadores entre empresas";
	static final String TIPO3 = "Analizar historia del indicador";
	static final String TIPO4 = "Analizar crecimiento del indicador";
	static final String TIPO5 = "Analizar por antiguedad";
	
	
	//------------------------------------- ATRIBUTOS ----------------------------------
	
	private Indicador indicadorSeleccionado;
	private List<Indicador> indicadores;
	private List<OperacionIndicador> operacionesIndicador = new ArrayList<OperacionIndicador>();
	private List <Operacion> operaciones = new ArrayList<Operacion>();
	private Operacion operacionSeleccionada;
	private OperacionIndicador operacionIndicadorSeleccionada;
	private String anio;
	private Integer anios;
	private String condicionSeleccionada;
	private String valor;

	//----------------------------------- CONSTRUCTORES --------------------------------
	
	public ConfigurarCondicionViewModel(String condicion) {
		indicadores = Repositorios.repositorioIndicadores.getIndicadores();
		operaciones = Repositorios.repositorioIndicadores.getOperaciones();
		operacionesIndicador = Repositorios.repositorioIndicadores.getOperacionesIndicador();
		condicionSeleccionada = condicion;
	}

	//------------------------------- GETTERS Y SETTERS --------------------------------
	
	public void setIndicadores(List<Indicador> indicadores){
		this.indicadores = indicadores;
	}
	
	public List<Indicador> getIndicadores(){
		return this.indicadores;
	} 
	
	public Integer getAnios() {
		return anios;
	}

	public void setAnios(Integer anios) {
		this.anios = anios;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public Operacion getOperacionSeleccionada() {
		return operacionSeleccionada;
	}

	public void setOperacionSeleccionada(Operacion operacionSeleccionada) {
		this.operacionSeleccionada = operacionSeleccionada;
	}

	public OperacionIndicador getOperacionIndicadorSeleccionada() {
		return operacionIndicadorSeleccionada;
	}

	public void setOperacionIndicadorSeleccionada(
			OperacionIndicador operacionIndicadorSeleccionada) {
		this.operacionIndicadorSeleccionada = operacionIndicadorSeleccionada;
	}

	public List<OperacionIndicador> getOperacionesIndicador() {
		return operacionesIndicador;
	}

	public void setOperacionesIndicador(
			List<OperacionIndicador> operacionesIndicador) {
		this.operacionesIndicador = operacionesIndicador;
	}

	public List<Operacion> getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(List<Operacion> operaciones) {
		this.operaciones = operaciones;
	}

	public Indicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}

	public void setIndicadorSeleccionado(Indicador indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}
	

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	//--------------------------------------- METODOS ----------------------------------

	public Condicion crearCondicion(){
		switch(condicionSeleccionada){
		case TIPO1: return new AnalizarIndicadorEnUltimosAnios(indicadorSeleccionado,operacionIndicadorSeleccionada,anios);
		case TIPO2: return new AnalizarIndicadoresEntreEmpresas(anio,operacionIndicadorSeleccionada,indicadorSeleccionado);
		case TIPO3: return new AnalizarHistoriaDelIndicador(indicadorSeleccionado, operacionSeleccionada, operacionIndicadorSeleccionada, valor);
		case TIPO4:return new AnalizarCrecimientoEnPeriodo(indicadorSeleccionado,operacionIndicadorSeleccionada,anios);
		default: return new Longevidad();
		}
	}

	
}