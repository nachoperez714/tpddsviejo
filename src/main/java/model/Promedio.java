package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


import javax.persistence.Entity;

@Entity
public class Promedio extends Operacion {

	//------------------------------------ CONSTRUCTORES --------------------------------
	
	public Promedio(String unNombre) {
		nombre = unNombre;
	}
	
	
	public Promedio() {
		
	}
	
	//------------------------------------ METODOS --------------------------------
	

	public BigDecimal obtenerMontoTotal(List<BigDecimal> montos, int cantidadAnios) {
		return this.sumatoria(montos, cantidadAnios).divide(new BigDecimal(String.valueOf(cantidadAnios)), 2, RoundingMode.HALF_EVEN);
	}
	
	@Override
	public BigDecimal calcular(Indicador indicador, Empresa empresa) {
		int cantidadAnios = this.obtenerAniosDeCuentas(empresa);
		List<String> anios = this.obtenerUltimosAnios(cantidadAnios); 
		List<BigDecimal> montos = this.obtenerListaDeMontos(anios, indicador, empresa); 
		return this.obtenerMontoTotal(montos, cantidadAnios); 
	}

}
