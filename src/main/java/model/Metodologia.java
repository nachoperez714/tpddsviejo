package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.*;

import repositories.Repositorios;

@Entity
@Table(name = "Metodologia")
public class Metodologia {

	//------------------------------------- ATRIBUTOS ----------------------------------
	@Id @GeneratedValue
	private int idMetodologia;
	
	private String nombre;
	
	@OneToMany(mappedBy = "metodologia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Condicion> condiciones;

	//----------------------------------- CONSTRUCTORES --------------------------------
	
	public Metodologia(String nombre, List<Condicion> condiciones) {
		this.nombre = nombre;
		this.condiciones = condiciones;
	}
	
	public Metodologia() {
		
	}

	//------------------------------- GETTERS Y SETTERS --------------------------------
	
	public String getNombre() {
		return nombre;
	}

	public List<Condicion> getCondicion(){
		return this.condiciones;
	}
	
	public int getIdMetodologia() {
		return idMetodologia;
	}

	public void setIdMetodologia(int idMetodologia) {
		this.idMetodologia = idMetodologia;
	}
	
	//--------------------------------------- METODOS ----------------------------------
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
	public void guardar() {
		Repositorios.repositorioMetodologias.persistirMetodologia(this);
	}
	
	public void agregarCondicion(Condicion condicion){
		this.condiciones.add(condicion);
	}
	
	public void asignarseEnSusCondiciones() {
		this.condiciones.forEach(condicion -> condicion.setMetodologia(this));
	}

	public List<Empresa> aplicarMetodologia( List<Empresa> empresas){;
		this.condiciones.forEach(unaCondicion -> unaCondicion.evaluarEmpresas(empresas));
		empresas.sort(Comparator.comparingInt(Empresa::getPuntacion));
		Collections.reverse(empresas);
		return empresas;
	}
	
}
