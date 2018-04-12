package server;

import java.util.HashSet;
import java.util.Set;

import controllers.EmpresasController;
import controllers.HomeController;
import controllers.IndicadoresController;
import controllers.MetodologiasController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
	
	static Set<String> publicRoutes = new HashSet<String>();

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();

		Spark.staticFiles.location("/public");
		setPublicRoutes(publicRoutes);

		Spark.before(SessionHandler.allowed());
		
 		HomeController homeController = new HomeController();
 		EmpresasController empresasController = new EmpresasController();
  		IndicadoresController indicadoresController = new IndicadoresController();
 		MetodologiasController metodologiasController = new MetodologiasController();

 		Spark.get("/", homeController::home, engine);
 		Spark.get("/login", homeController::login, engine);
  		Spark.post("/login", homeController::newSession);
		Spark.get("/wrong-user-or-pass", homeController::wrongLogin, engine);
		Spark.post("/wrong-user-or-pass", homeController::wrongLogin, engine);
  		
		Spark.get("/cuentas", empresasController::verArchivos,engine);
		Spark.post("/cuentas", empresasController::cargarArchivos,engine);
		Spark.get("/archivo-invalido.hbs", empresasController::archivoInvalido,engine);
 		Spark.get("/empresas", empresasController::home,engine);
 		Spark.post("/empresas", empresasController::aplicar,engine);
  		
 		Spark.get("/metodologias", metodologiasController::home, engine);
 		Spark.post("/metodologias", metodologiasController::aplicar, engine);
 		Spark.get("/metodologias/nueva", metodologiasController::nueva, engine);
 		Spark.post("/metodologias/nueva", metodologiasController::crear, engine);
 		Spark.get("/metodologias/:id", metodologiasController::mostrar, engine);
  		
 		Spark.get("/indicadores", indicadoresController::home, engine);
 		Spark.post("/indicadores", indicadoresController::aplicar, engine);
 		Spark.get("/indicadores/nuevo", indicadoresController::nuevo, engine);
 		Spark.get("/indicadores/error", indicadoresController::error, engine);
 		Spark.post("/indicadores/nuevo", indicadoresController::crear, engine);
 		
 		
 		Spark.get("/condiciones/nueva", metodologiasController::verCondicion, engine);
 		Spark.post("/condiciones/nueva", metodologiasController::crearCondicion, engine);
		Spark.get("/condiciones/condicionTipo1", metodologiasController::verCondicionTipo1, engine);
		Spark.post("/condiciones/condicionTipo1", metodologiasController::crearCondicionTipo1, engine);
		Spark.get("/condiciones/condicionTipo2", metodologiasController::verCondicionTipo2, engine);
		Spark.post("/condiciones/condicionTipo2", metodologiasController::crearCondicionTipo2, engine);
		Spark.get("/condiciones/condicionTipo3", metodologiasController::verCondicionTipo3, engine);
		Spark.post("/condiciones/condicionTipo3", metodologiasController::crearCondicionTipo3, engine);
		Spark.get("/condiciones/condicionTipo4", metodologiasController::verCondicionTipo4, engine);
		Spark.post("/condiciones/condicionTipo4", metodologiasController::crearCondicionTipo4, engine);

	}
	
	public static Boolean isPublic(String route){
		return publicRoutes.contains(route);
	}
	
	private static void setPublicRoutes(Set<String> publicRoutes){
		publicRoutes.add("/login");
		publicRoutes.add("/wrong-user-or-pass");
	}

}