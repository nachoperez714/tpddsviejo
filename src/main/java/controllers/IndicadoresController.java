package controllers;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import model.Empresa;
import model.Indicador;
import parserIndicador.ParsearIndicador;
import repositories.Repositorios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresController implements WithGlobalEntityManager, TransactionalOps{
	
	public ModelAndView mostrar(Request req, Response res){
		
		return new ModelAndView(null, "indicadores/show.hbs");
	}
	
	public ModelAndView home(Request req, Response res){
		Map<String, Object> model=new HashMap<>();
		Repositorios.repositorioIndicadores.obtenerIndicadores();
		Repositorios.repositorioEmpresas.obtenerEmpresas();
		model.put("empresas", Repositorios.repositorioEmpresas.getEmpresas());
		model.put("indicadores",Repositorios.repositorioIndicadores.getIndicadores());
		model.put("resultado", 0);
		return new ModelAndView(model, "indicadores/home.hbs");
	}
	
	public ModelAndView aplicar(Request req, Response res) {
		Map<String, Object> model=new HashMap<>();
		String nombreIndicador = req.queryParams("nombreIndicador");
		String anio = req.queryParams("anio");
		String nombreEmpresa = req.queryParams("nombreEmpresa");
		Indicador indicador = Repositorios.repositorioIndicadores.find(ind->ind.getNombreIndicador().equals(nombreIndicador));
		Empresa empresa = Repositorios.repositorioEmpresas.find(e-> e.getNombre().equals(nombreEmpresa));
		java.math.BigDecimal bd = indicador.calcularMonto(empresa, anio);
		model.put("empresas", Repositorios.repositorioEmpresas.getEmpresas());
		model.put("indicadores",Repositorios.repositorioIndicadores.getIndicadores());
		model.put("resultado", bd.toString());
		model.put("anio", anio);
		model.put("nombreIndicador", nombreIndicador);
		model.put("nombreEmpresa",nombreEmpresa);
		return new ModelAndView(model, "indicadores/home.hbs");
	}
	
	
	public ModelAndView nuevo(Request req, Response res){
		return new ModelAndView(null, "indicadores/new.hbs");
	}
	
	public ModelAndView crear(Request req, Response res){
		String formula = req.queryParams("formula");
		String nombre = req.queryParams("nombre");
		try
		{
			ParsearIndicador parser = new ParsearIndicador();
			if(formula.contains(nombre))
				throw new ParseCancellationException("Se llama a si mismo");
			parser.generarArbol(formula);
			Indicador indicador = new Indicador(nombre, formula);
			withTransaction(() ->{
				Repositorios.repositorioIndicadores.persistirIndicador(indicador);
			});
			res.redirect("/indicadores");
		}
		catch(ParseCancellationException | NullPointerException e) {
			res.redirect("/indicadores/error");
		}
		return null;
	}
	public ModelAndView error(Request req, Response res){
	
		return new ModelAndView(null, "indicadores/error.hbs");
	}
	
}
