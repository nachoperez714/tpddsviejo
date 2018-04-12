package parserIndicador;
// Generated from Indicador.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link IndicadorParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface IndicadorVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code NUMERO}
	 * labeled alternative in {@link IndicadorParser#indicador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNUMERO(IndicadorParser.NUMEROContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CUENTA}
	 * labeled alternative in {@link IndicadorParser#indicador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCUENTA(IndicadorParser.CUENTAContext ctx);
	/**
	 * Visit a parse tree produced by the {@code operacion}
	 * labeled alternative in {@link IndicadorParser#indicador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperacion(IndicadorParser.OperacionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IND}
	 * labeled alternative in {@link IndicadorParser#indicador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIND(IndicadorParser.INDContext ctx);
}