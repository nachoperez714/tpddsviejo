package model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.uqbar.commons.utils.Observable;

import repositories.Repositorios;

@Entity
@Observable
public class AnalizarIndicadoresEntreEmpresas extends Condicion {
	
	//------------------------------------- ATRIBUTOS ----------------------------------
	@Column
	private String anio;
	
	//------------------------------- GETTERS Y SETTERS --------------------------------
	
	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}
	
	
	//----------------------------------- CONSTRUCTORES --------------------------------

	public AnalizarIndicadoresEntreEmpresas(String anio, OperacionIndicador operacion, Indicador indicador)
	{
		this.anio = anio;
		this.operacionIndicador = operacion;
		this.indicador = indicador;
	}
	
	public AnalizarIndicadoresEntreEmpresas() {
		
	}
	
	//--------------------------------------- METODOS ----------------------------------
	
	@Override
	public void compararEmpresas(Empresa unaEmpresa, Empresa otraEmpresa) {
		BigDecimal unMonto = indicador.calcularMonto(unaEmpresa, anio);
		BigDecimal otroMonto = indicador.calcularMonto(otraEmpresa, anio);
		if(operacionIndicador.operar(unMonto, otroMonto))
			Repositorios.repositorioEmpresas.aumentarPuntuacion(unaEmpresa);
		else
			Repositorios.repositorioEmpresas.aumentarPuntuacion(otraEmpresa);
	}
	
}
