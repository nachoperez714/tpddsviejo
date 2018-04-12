package controllers;

import javax.persistence.NoResultException;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import model.Usuario;
import repositories.Repositorios;
import scala.Console;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

public class HomeController implements WithGlobalEntityManager, TransactionalOps{
	
	public ModelAndView home(Request req, Response res){
		
		return new ModelAndView(null, "home/home.hbs");
	}
	
	public ModelAndView login (Request req, Response res){
	
		return new ModelAndView(null, "home/login.hbs");
	}
	
	public ModelAndView wrongLogin (Request req, Response res){
		
		return new ModelAndView(null, "home/wrongLogin.hbs");
	}
	
	public Void newSession(Request req, Response res){
		
		String username = req.queryParams("user");
		String pass = req.queryParams("password");
		Usuario user = new Usuario();
		try
		{
		user = Repositorios.repositorioUsuarios.buscarUsuario(username);
		}
		catch (NoResultException e)
		{
			res.redirect("/archivo-incorrecto");
		}
		if(user.loginCorrecto(pass))
		{
			Session sesion = req.session(true);
			sesion.attribute("user", username);
			res.redirect("/");
		}
		else
			res.redirect("/wrong-user-or-pass");
		return null;
	}
}
