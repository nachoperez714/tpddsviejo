package model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class IndicadorPrecargado {
	@Id @GeneratedValue
	private long id;
	@ManyToOne
	@JoinColumn(name="nombreIndicador")
	private Indicador indicador;
	private BigDecimal valor;
	@ManyToOne
	@JoinColumn(name="idEmpresa")
	private Empresa empresa;
	private String anio;
	
	public IndicadorPrecargado(Indicador indicador , Empresa empresa, String anio, BigDecimal valor)
	{
		this.indicador = indicador;
		this.empresa = empresa;
		this.anio = anio;
		this.valor = valor;
	}

	public BigDecimal getValor() {
		return valor;
	}
	

	
}
