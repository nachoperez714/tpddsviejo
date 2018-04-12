package model;

import java.math.BigDecimal;

import javax.persistence.Entity;

import org.uqbar.commons.utils.Observable;

@Observable
@Entity
public class MenorA extends OperacionIndicador {

	//------------------------------------ CONSTRUCTORES --------------------------------
	
	public MenorA(String unNombre) {
		nombre = unNombre;
	}
	
	
	public MenorA() {
		
	}
	
	//------------------------------------ METODOS --------------------------------
	@Override
	public boolean operar(BigDecimal unMonto, BigDecimal otroMonto) {
		return unMonto.compareTo(otroMonto) <= 0;
	}
	

}
