package model;

import java.util.List;
import java.util.stream.IntStream;

import javax.persistence.*;

import org.uqbar.commons.utils.Observable;

@Entity
@Observable
@DiscriminatorColumn(length = 100)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Condicion {
	
	//------------------------------------- ATRIBUTOS ----------------------------------
	
	@Id @GeneratedValue
	protected int idCondicion;
	
	@ManyToOne
	@JoinColumn(name = "nombreIndicador")
	protected Indicador indicador;

	@ManyToOne
	@JoinColumn(name = "idOperacionIndicador")
	protected OperacionIndicador operacionIndicador;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idMetodologia")
	protected Metodologia metodologia;
	
	//----------------------------------- CONSTRUCTORES --------------------------------
	
	public Condicion() {
		
	}
	
	//------------------------------- GETTERS Y SETTERS --------------------------------
	
	public int getIdCondicion() {
		return idCondicion;
	}


	public void setIdCondicion(int idCondicion) {
		this.idCondicion = idCondicion;
	}


	public OperacionIndicador getOperacionIndicador() {
		return operacionIndicador;
	}


	public void setOperacionIndicador(OperacionIndicador operacionIndicador) {
		this.operacionIndicador = operacionIndicador;
	}


	public Metodologia getMetodologia() {
		return metodologia;
	}


	public void setMetodologia(Metodologia metodologia) {
		this.metodologia = metodologia;
	}


	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	
	//--------------------------------------- METODOS ----------------------------------
	
	public void evaluarEmpresas(List<Empresa> empresas)
	{
		IntStream.range(0, empresas.size() - 1)
		.filter(i -> i % 2 == 0)
		.forEach(i -> this.compararEmpresas(empresas.get(i), empresas.get(i+1)));
	}
		
	public abstract void compararEmpresas(Empresa unaEmpresa, Empresa otraEmpresa);
	
	public Indicador getIndicador()
	{
		return indicador;
	}
	
	@Override 
	public String toString() {
		return indicador.toString() + operacionIndicador.toString();
	}
}
