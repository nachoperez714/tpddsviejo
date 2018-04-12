package model;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.persistence.Entity;

import repositories.Repositorios;

@Entity
public class AnalizarCrecimientoEnPeriodo extends Condicion {

	//------------------------------------- ATRIBUTOS ----------------------------------
	
	private Integer cantidadAnios;
	
	//------------------------------- GETTERS Y SETTERS --------------------------------

	public Integer getCantidadAnios() {
		return cantidadAnios;
	}

	public void setCantidadAnios(Integer cantidadAnios) {
		this.cantidadAnios = cantidadAnios;
	}
	
	//----------------------------------- CONSTRUCTORES --------------------------------
	
	public AnalizarCrecimientoEnPeriodo(Indicador indicador, OperacionIndicador operacion, Integer anios) {
		this.indicador = indicador;
		this.operacionIndicador = operacion;
		this.cantidadAnios = anios;	
	}
	
	public AnalizarCrecimientoEnPeriodo() {
		
	}
	
	//--------------------------------------- METODOS ----------------------------------
	
	public List<String> obtenerUltimosAnios() {
		Integer anio = Calendar.getInstance().get(Calendar.YEAR);
		return IntStream.rangeClosed(anio - cantidadAnios, anio).boxed().map(unAnio -> unAnio.toString()).collect(Collectors.toList());
	}
	
	@Override
	public void compararEmpresas(Empresa unaEmpresa, Empresa otraEmpresa) {
		List<String> anios = obtenerUltimosAnios();
		if(indicador.sigueTendencia(operacionIndicador, unaEmpresa, anios))
			Repositorios.repositorioEmpresas.aumentarPuntuacion(unaEmpresa);
		else
			Repositorios.repositorioEmpresas.aumentarPuntuacion(otraEmpresa);
	}
}
