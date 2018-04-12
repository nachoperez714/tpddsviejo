package vm;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import repositories.Repositorios;
import model.Condicion;
import model.Longevidad;

@Observable
public class ElegirCondicionViewModel {
	
	//------------------------------------- CONSTANTES ----------------------------------
	
	static final String TIPO1 = "Analizar indicador en los ultimos anios";
	static final String TIPO2 = "Analizar indicadores entre empresas";
	static final String TIPO3 = "Analizar historia del indicador";
	static final String TIPO4 = "Analizar crecimiento del indicador";
	static final String TIPO5 = "Analizar antiguedad de la empresa";
	
	
	//------------------------------------- ATRIBUTOS ----------------------------------
	
	private Condicion condicion;
	private String condicionSeleccionada;
	private List<String> condiciones = new ArrayList<String>();

	//----------------------------------- CONSTRUCTORES --------------------------------
	public ElegirCondicionViewModel() {
		condiciones.add(TIPO1);
		condiciones.add(TIPO2);
		condiciones.add(TIPO3);
		condiciones.add(TIPO4);
		condiciones.add(TIPO5);
		Repositorios.repositorioIndicadores.obtenerIndicadores();
		Repositorios.repositorioIndicadores.obtenerOperaciones();
		Repositorios.repositorioIndicadores.obtenerOperacionesIndicador();
	}
	
	//------------------------------- GETTERS Y SETTERS --------------------------------
	
	public void setCondiciones(List<String> condiciones){
		this.condiciones =condiciones;
	}
	
	public List<String> getCondiciones(){
		return condiciones;
	}

	
	public String getcondicionSeleccionada(){
		return condicionSeleccionada;
	}
	

	public Condicion getCondicion() {
		return condicion;
	}

	public void setCondicion(Condicion unaCondicion) {
		condicion = unaCondicion;
	}

	public void setCondicionSeleccionada(String unaCondicionSeleccionada){
		condicionSeleccionada = unaCondicionSeleccionada;
 	}

	//--------------------------------------- METODOS ----------------------------------
	
	public Condicion crearCondicion() {
		return new Longevidad();
	}
	
}