package model;

import java.math.BigDecimal;

import javax.persistence.Entity;

import org.uqbar.commons.utils.Observable;

@Observable
@Entity
public class MayorA extends OperacionIndicador {

	//------------------------------------ CONSTRUCTORES --------------------------------
	
	public MayorA(String unNombre) {
		nombre = unNombre;
	}
	
	public MayorA() {
		
	}
	
	//------------------------------------ METODOS --------------------------------
	
	@Override
	public boolean operar(BigDecimal unMonto, BigDecimal otroMonto) {
		return unMonto.compareTo(otroMonto) >= 0;
	}
}
