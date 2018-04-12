package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.AnalizarCrecimientoEnPeriodo;
import model.Condicion;
import model.Indicador;
import model.OperacionIndicador;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import repositories.Repositorios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CondicionesController implements WithGlobalEntityManager, TransactionalOps{
	
	private List<Condicion> condiciones = new ArrayList<Condicion>();
	
	public ModelAndView verCrecimientoEnPeriodo(Request req, Response res) {
		Map<String, Object> model=new HashMap<>();
		Repositorios.repositorioIndicadores.obtenerOperacionesIndicador();
		Repositorios.repositorioIndicadores.obtenerIndicadores();
		model.put("indicadores", Repositorios.repositorioIndicadores.getIndicadores());
		model.put("operacionesIndicador", Repositorios.repositorioIndicadores.getOperacionesIndicador());
		return new ModelAndView(model, "condiciones/condicion1.hbs");
	}
	
	public ModelAndView crearCrecimientoEnPeriodo(Request req, Response res) {
		String operacionIndicador = req.queryParams("operacion");
		String cantidadAnios =req.queryParams("anios");
		String nombreIndicador = req.queryParams("indicador");
		Indicador indicador = Repositorios.repositorioIndicadores.find(ind->ind.getNombreIndicador().equals(nombreIndicador));
		OperacionIndicador operacion = Repositorios.repositorioIndicadores.getOperacionesIndicador().stream().filter(o-> o.getNombre().equals(operacionIndicador)).findFirst().get();
		AnalizarCrecimientoEnPeriodo condicion = new AnalizarCrecimientoEnPeriodo(indicador, operacion, Integer.parseInt(cantidadAnios)); 
		condiciones.add(condicion);
		res.redirect("condicion");
		return null;
	}
	
	public ModelAndView nueva(Request req, Response res){
		return new ModelAndView(null, "condiciones/home.hbs");
	}
	
	public ModelAndView crear(Request req, Response res){
		return new ModelAndView(null, "home/home.hbs");
	}
	
}
