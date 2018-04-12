package repositories;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Usuario;
public class RepositorioUsuarios {

	//------------------------------------ ATRIBUTOS --------------------------------
	
	private EntityManager entity;
	
	//------------------------------------ CONSTRUCTORES --------------------------------
	
	public RepositorioUsuarios(){
		this.entity = PerThreadEntityManagers.getEntityManager();
		
	}
	
	
	public Usuario buscarUsuario(String user) {
		Query query = entity.createQuery("FROM Usuario WHERE username = :user");
		query.setParameter("user", user);
		Usuario result = (Usuario)query.getSingleResult();
		return result;
	}
	

	
}
