package model;

import java.math.BigDecimal;

import javax.persistence.Entity;

import java.util.List;

@Entity
public class Sumatoria extends Operacion {
	
	//------------------------------------ CONSTRUCTORES --------------------------------
	
	public Sumatoria(String unNombre) {
		nombre = unNombre;
	}
	
	
	public Sumatoria() {
		
	}
	
	//------------------------------------ METODOS --------------------------------

	public BigDecimal obtenerMontoTotal(List<BigDecimal> montos, int cantidadAnios) {
		return this.sumatoria(montos, cantidadAnios);
	}
	
	@Override
	public BigDecimal calcular(Indicador indicador, Empresa empresa) {
		int cantidadAnios = this.obtenerAniosDeCuentas(empresa);
		List<String> anios = this.obtenerUltimosAnios(cantidadAnios); 
		List<BigDecimal> montos = this.obtenerListaDeMontos(anios, indicador, empresa); 
		return this.obtenerMontoTotal(montos, cantidadAnios); 
	}

}
