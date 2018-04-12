package model;

import java.math.BigDecimal;

public interface OperandoDeIndicador {

	public BigDecimal calcularMonto(Empresa empresa, String anio);
}
