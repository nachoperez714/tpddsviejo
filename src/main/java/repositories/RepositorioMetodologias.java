package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.AnalizarHistoriaDelIndicador;
import model.AnalizarIndicadorEnUltimosAnios;
import model.AnalizarIndicadoresEntreEmpresas;
import model.Condicion;
import model.Indicador;
import model.Longevidad;
import model.MayorA;
import model.MenorA;
import model.Metodologia;
import model.Operacion;
import model.OperacionIndicador;
import model.Promedio;
import model.Sumatoria;


@SuppressWarnings("unused")
public class RepositorioMetodologias{
	
	//------------------------------------- ATRIBUTOS ----------------------------------
	
	private List<Metodologia> metodologias;
	private EntityManager entity;
	
	//----------------------------------- CONSTRUCTORES --------------------------------
	
	public RepositorioMetodologias() {
		this.metodologias = new ArrayList<Metodologia>();
		this.entity = PerThreadEntityManagers.getEntityManager();
		
	}

	//------------------------------- GETTERS Y SETTERS --------------------------------
	
	public List<Metodologia> getMetodologias() {
		return metodologias;
	}

	//--------------------------------------- METODOS ----------------------------------

	public void agregarMetodologia(Metodologia metodologia) {
		this.metodologias.add(metodologia);
	}
	
	public void crearMetodologia(String nombre, List<Condicion> condiciones) {
		Metodologia metodologia = new Metodologia(nombre, condiciones);
		metodologia.asignarseEnSusCondiciones();
		this.persistirMetodologia(metodologia);
	}
	
	@SuppressWarnings("unchecked")
	public void obtenerMetodologias() {
		metodologias = (List<Metodologia>)entity.createQuery("FROM Metodologia").getResultList();
	}
	
	public Metodologia find(Predicate<? super Metodologia> criterio)
	{
		return this.metodologias.stream().filter(criterio).findFirst().get();
	}
	
	public void persistirMetodologia(Metodologia metodologia) {
	    try {
	    entity.getTransaction().begin();
	    entity.persist(metodologia);
	    entity.getTransaction().commit();
	    } catch(HibernateException e) {
	    	entity.getTransaction().rollback();
	    }
	}
	
	public boolean hayOperacionesSinCargar() {
		Repositorios.repositorioIndicadores.obtenerOperaciones();
		Repositorios.repositorioIndicadores.obtenerOperacionesIndicador();
		this.obtenerMetodologias();
		return Repositorios.repositorioIndicadores.getOperaciones().isEmpty() || Repositorios.repositorioIndicadores.getOperacionesIndicador().isEmpty()  || this.getMetodologias().isEmpty(); 
	}
	
}
