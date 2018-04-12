package model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import repositories.Repositorios;

@Entity
public class AnalizarHistoriaDelIndicador extends Condicion {

	//------------------------------------- ATRIBUTOS ----------------------------------

	private BigDecimal valor;

	@ManyToOne
	@JoinColumn(name = "idOperacion")
	private Operacion operacion;
	
	//----------------------------------- CONSTRUCTORES --------------------------------
	
	public AnalizarHistoriaDelIndicador(Indicador unIndicador, Operacion unaOperacion, OperacionIndicador unaOperacionIndicador, String unValor) {
		operacion = unaOperacion;
		indicador = unIndicador;
		operacionIndicador = unaOperacionIndicador;
		valor = new BigDecimal(unValor) ;
	}
	
	public AnalizarHistoriaDelIndicador() {
		
	}
	
	//------------------------------- GETTERS Y SETTERS --------------------------------
	
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public Operacion getOperacion() {
		return operacion;
	}

	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}
	//--------------------------------------- METODOS ----------------------------------
	
	@Override
	public void compararEmpresas(Empresa unaEmpresa, Empresa otraEmpresa) {
		BigDecimal total = operacion.calcular(indicador, unaEmpresa);
		if(operacionIndicador.operar(total, valor))
			Repositorios.repositorioEmpresas.aumentarPuntuacion(unaEmpresa);
		else
			Repositorios.repositorioEmpresas.aumentarPuntuacion(otraEmpresa);
	}
	
}
