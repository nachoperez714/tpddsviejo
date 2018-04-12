package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import scala.Console;

@Entity
public class Usuario {
	@Id
	private String username;
	
	public String getUsername() {
		return username;
	}

	private String password;
	@OneToMany(mappedBy="usuario",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Indicador> indicadores;
	
	public Usuario()
	{

	}
	
	public Usuario(String usr, String pass)
	{
		this.username = usr;
		this.password = pass;
	}
	
	public boolean loginCorrecto(String pass)
	{
		if (password.equals(pass))
		{
			Console.println("OK");
			return true;
		}
		else {
			Console.println(password);
			Console.println(pass);
			return false;
		}
	}

}
