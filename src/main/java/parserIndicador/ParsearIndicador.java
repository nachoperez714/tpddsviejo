package parserIndicador;
import java.math.BigDecimal;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import model.Empresa;

public class ParsearIndicador {

	public BigDecimal reducirIndicador(String formula, Empresa unaEmpresa, String anio) {
		ParseTree tree = generarArbol(formula);
		Visitador visitador = new Visitador(unaEmpresa, anio);
		BigDecimal calculo = visitador.visit(tree);
		return calculo;
		
	}
	
	
	public ParseTree generarArbol(String formula)
	{
		CharStream input = CharStreams.fromString(formula);
		IndicadorLexer lexer = new IndicadorLexer(input);
		lexer.removeErrorListeners();
		lexer.addErrorListener(new ParserErrorListener());
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		IndicadorParser parser = new IndicadorParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(new ParserErrorListener());
		ParseTree tree = parser.indicador();
		return tree;
	}
}
