package model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;

import repositories.Repositorios;

@Entity
public class Longevidad extends Condicion {


	//------------------------------------ CONSTRUCTORES --------------------------------
	
	public Longevidad() {
		
	}
	
	//------------------------------------ METODOS --------------------------------
	
	public BigDecimal calcularAntiguedad(Empresa unaEmpresa) {
		return new BigDecimal(Calendar.getInstance().get(Calendar.YEAR) - unaEmpresa.getAnioFundacion());
	}
	
	public int esMasAntigua(Empresa unaEmpresa, Empresa otraEmpresa) {
		BigDecimal unaAntiguedad = this.calcularAntiguedad(unaEmpresa);
		BigDecimal otraAntiguedad = this.calcularAntiguedad(otraEmpresa);
		return unaAntiguedad.compareTo(otraAntiguedad);
	}

	
	@Override
	public void compararEmpresas(Empresa unaEmpresa, Empresa otraEmpresa) {
		if(this.esMasAntigua(unaEmpresa, otraEmpresa) > 0)
			Repositorios.repositorioEmpresas.aumentarPuntuacion(unaEmpresa);
		else
			Repositorios.repositorioEmpresas.aumentarPuntuacion(otraEmpresa);
	}
	
}
