package model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
public abstract class OperacionIndicador {
	
	//------------------------------------ ATRIBUTOS --------------------------------
	
	@Id @GeneratedValue
	protected int idOperacionIndicador;

	protected String nombre;
	
	//----------------------------------- CONSTRUCTORES --------------------------------
	
	public OperacionIndicador() {
		
	}

	//------------------------------- GETTERS Y SETTERS --------------------------------
	
	public int getIdOperacionIndicador() {
		return idOperacionIndicador;
	}

	public void setIdOperacionIndicador(int idOperacionIndicador) {
		this.idOperacionIndicador = idOperacionIndicador;
	}
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombreOperacionIndicador) {
		this.nombre = nombreOperacionIndicador;
	}
	
	//--------------------------------------- METODOS ----------------------------------
	
	public boolean operar(BigDecimal unMonto, BigDecimal otroMonto) {
		return true;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
}
