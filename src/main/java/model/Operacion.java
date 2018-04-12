package model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
public abstract class Operacion {

	//------------------------------------ ATRIBUTOS --------------------------------
	
	@Id @GeneratedValue()
	protected int idOperacion;
	protected String nombre;

	//----------------------------------- CONSTRUCTORES --------------------------------
	
	public Operacion(String unNombre) {
		nombre = unNombre;
	}
	
	public Operacion() {
		
	}
	
	//------------------------------- GETTERS Y SETTERS --------------------------------


	public String getNombre() {
		return nombre;
	}

	public int getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(int idOperacion) {
		this.idOperacion = idOperacion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	//--------------------------------------- METODOS ----------------------------------
	
	public BigDecimal calcular(Indicador indicador, Empresa empresa)
	{
		return new BigDecimal(0);
	}
	

	public List<String> obtenerUltimosAnios(int cantidadAnios) {
		Integer anio = Calendar.getInstance().get(Calendar.YEAR);
		return IntStream.rangeClosed(anio - cantidadAnios, anio).boxed().map(unAnio -> unAnio.toString()).collect(Collectors.toList());
	}
	
	public int obtenerAniosDeCuentas(Empresa empresa) {
		int maximo = Collections.max(empresa.getCuentas().stream().map(e -> e.getAnio()).collect(Collectors.toList()));
		int minimo = Collections.min(empresa.getCuentas().stream().map(e -> e.getAnio()).collect(Collectors.toList()));
		return maximo - minimo;
	}
	
	public List<BigDecimal> obtenerListaDeMontos(List<String> anios, Indicador indicador, Empresa empresa) {
		return anios.stream().map(a -> indicador.calcularMonto(empresa, a)).collect(Collectors.toList());
	}
	
	public BigDecimal sumatoria(List<BigDecimal> montos, int cantidadAnios) {
		return montos.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	
	@Override
	public String toString() {
		return this.nombre;
	}
	
}
