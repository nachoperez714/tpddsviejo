package model;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class Mediana extends Operacion {
	
	//------------------------------------ CONSTRUCTORES --------------------------------
	
	public Mediana(String unNombre) {
		nombre = unNombre;
	}
	
	
	public Mediana() {
		
	}
	
	//------------------------------------ METODOS --------------------------------
	
	@Override
	public BigDecimal calcular(Indicador indicador, Empresa empresa) {
		return null;
	}

}
