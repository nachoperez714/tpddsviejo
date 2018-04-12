package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.*;

import org.uqbar.commons.utils.Observable;

import repositories.Repositorios;

@Observable
@Entity
@Table(name="Empresa")
public class Empresa {
	
	//------------------------------------ ATRIBUTOS --------------------------------
	
	@Id @GeneratedValue
	private int idEmpresa;
	
	private String nombre;
	
	@OneToMany(mappedBy = "empresa", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Cuenta> cuentas;
	
	private int puntuacion;
	
	private int anioFundacion;
	
	//------------------------------------ CONSTRUCTORES --------------------------------
	
	public Empresa(String empresa, int anio){
		this.nombre= empresa;
		this.anioFundacion = anio;
		this.cuentas= new ArrayList<Cuenta>();
	}

	public Empresa() {
		
	}
	
	//------------------------------------ GETTERS Y SETTERS --------------------------------
	
	public String getNombre(){
		return this.nombre;
	}
	
	public List<Cuenta> getCuentas(){
		return this.cuentas;
	}
	
	public void setCuentas(List<Cuenta> unasCuentas) {
		this.cuentas = unasCuentas;
	}
	
	public int getAnioFundacion() {
		return anioFundacion;
	}
	
	public void setAnioFundacion(int anioFundacion) {
		this.anioFundacion = anioFundacion;
	}

	public int getPuntacion() {
		return this.puntuacion;
	}
	
	public void setPuntacion(int  puntuacion) {
		this. puntuacion =  puntuacion;
	}
	
	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	//------------------------------------ METODOS --------------------------------
	
	public void agregarCuenta(Cuenta cuenta){
		cuentas.add(cuenta);
	}
		
	private Cuenta find(Predicate<? super Cuenta> criterio) {
		return this.cuentas.stream().filter(criterio).findFirst().get();
	}
	
	public Cuenta encontrarCuenta(String nombre, String anio) {
		int anioNumerico = Integer.parseInt(anio);
		return this.find(unaCuenta -> unaCuenta.getNombre().equals(nombre) && unaCuenta.getAnio() == anioNumerico);
	}
	
	public void guardar() {
		Repositorios.repositorioEmpresas.persistirEmpresa(this);
	}
	
	public void aumentarPuntuacion() {
		puntuacion++;
	}
	
	public boolean equals(String compare){
		return this.getNombre().toLowerCase().equals(compare.toLowerCase());
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
	

}

