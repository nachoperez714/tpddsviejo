package vm;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import repositories.Repositorios;
import model.Condicion;

@Observable
public class AgregarMetodologiaViewModel {
	
	//------------------------------------- ATRIBUTOS ----------------------------------
	
	private String nombreNuevaMetodologia;
	private List<Condicion> condicionesDeLaMetodologia = new ArrayList<Condicion>();

	//----------------------------------- CONSTRUCTORES --------------------------------
	
	public AgregarMetodologiaViewModel() {
	
	}

	//------------------------------- GETTERS Y SETTERS --------------------------------
	
	public String getNombreNuevaMetodologia() {
		return nombreNuevaMetodologia;
	}

	public void setNombreNuevaMetodologia(String nombreNuevaMetodologia) {
		this.nombreNuevaMetodologia = nombreNuevaMetodologia;
	}

	public List<Condicion> getCondicionesDeLaMetodologia() {
		return condicionesDeLaMetodologia;
	}

	public void setCondicionesDeLaMetodologia(
			List<Condicion> condicionesDeLaMetodologia) {
		this.condicionesDeLaMetodologia = condicionesDeLaMetodologia;
	}

	//--------------------------------------- METODOS ----------------------------------
	
	public void crearMetodologia() {
		Repositorios.repositorioMetodologias.crearMetodologia(nombreNuevaMetodologia,condicionesDeLaMetodologia);
	} 
	
	public void agregarCondicion(Condicion condicion){
		condicionesDeLaMetodologia.add(condicion);
	}
	
}

