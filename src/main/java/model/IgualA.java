package model;

import java.math.BigDecimal;

import javax.persistence.Entity;

import org.uqbar.commons.utils.Observable;

@Observable
@Entity
public class IgualA extends OperacionIndicador {
	
	//------------------------------------ CONSTRUCTORES --------------------------------
	
	public IgualA(String unNombre) {
		nombre = unNombre;
	}
	
	
	public IgualA() {
		
	}
	
	//------------------------------------ METODOS --------------------------------
	@Override
	public boolean operar(BigDecimal unMonto, BigDecimal otroMonto) {
		return unMonto.compareTo(otroMonto) == 0;
	}
	

}
