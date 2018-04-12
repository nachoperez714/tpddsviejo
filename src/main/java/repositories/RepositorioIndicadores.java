package repositories;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.HibernateException;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Empresa;
import model.Indicador;
import model.IndicadorPrecargado;
import model.MayorA;
import model.MenorA;
import model.Operacion;
import model.OperacionIndicador;
import model.Promedio;
import model.Sumatoria;
import javax.persistence.Query;

public class RepositorioIndicadores {

	//------------------------------------ ATRIBUTOS --------------------------------
	
	private List<Indicador> indicadores;
	private List<Operacion> operaciones;
	private List<OperacionIndicador> operacionesIndicador;
	private List<IndicadorPrecargado> indicadoresPrecargados;
	private EntityManager entity;

	//------------------------------------ CONSTRUCTORES --------------------------------
	
	public RepositorioIndicadores() {
		this.indicadores = new ArrayList<Indicador>();
		this.operaciones = new ArrayList<Operacion>();
		this.operacionesIndicador = new ArrayList<OperacionIndicador>();
		this.indicadoresPrecargados = new ArrayList<IndicadorPrecargado>();
		this.entity = PerThreadEntityManagers.getEntityManager();
	}

	//------------------------------------ GETTERS Y SETTERS --------------------------------
	
	public List<Indicador> getIndicadores() {
		return indicadores;
	}
	
	
	public List<Operacion> getOperaciones() {
		return operaciones;
	}

	public List<OperacionIndicador> getOperacionesIndicador() {
		return operacionesIndicador;
	}

	
	//------------------------------------ METODOS --------------------------------

	public BigDecimal buscarPrecargado(Indicador indicador, Empresa empresa, String anio)
	{
		//return indicadoresPrecargados.stream().filter(indicadorPrecargado -> indicadorPrecargado.equals(indicadorBuscado)).findFirst();
		@SuppressWarnings("unchecked")
		Query query = entity.createQuery("FROM IndicadorPrecargado WHERE anio = :anio and empresa = :empresa and indicador = :indicador");
		query.setParameter("indicador", indicador);
		query.setParameter("empresa", empresa);
		query.setParameter("anio", anio);
		@SuppressWarnings("unchecked")
		IndicadorPrecargado indicadorPrecargado = (IndicadorPrecargado) query.getSingleResult();
		return indicadorPrecargado.getValor();
		
		
	}
	
	public void guardarPrecargado(Indicador indicador, Empresa empresa, String anio, BigDecimal valor)
	{
		IndicadorPrecargado indicadorPrecargado = new IndicadorPrecargado(indicador, empresa, anio, valor);
	    try {
	    entity.getTransaction().begin();
	    entity.persist(indicadorPrecargado);
	    entity.getTransaction().commit();
	    } catch(HibernateException e) {
	    	entity.getTransaction().rollback();
	    }
	}
	
	public void agregarIndicador(Indicador indicador)
	{
		this.indicadores.add(indicador);
	}
	
	public Indicador find(Predicate<? super Indicador> criterio)
	{
		return this.indicadores.stream().filter(criterio).findFirst().get();
	}
	
	//NO MIRAR
	
	@SuppressWarnings("unchecked")
	public void obtenerIndicadores() {
		indicadores = (List<Indicador>)entity.createQuery("FROM Indicador").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public void obtenerOperaciones() {
		operaciones = (List<Operacion>)entity.createQuery("FROM Operacion").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public void obtenerOperacionesIndicador() {
		operacionesIndicador = (List<OperacionIndicador>)entity.createQuery("FROM OperacionIndicador").getResultList();
	}
	

	
	public void persistirIndicador(Indicador indicador) {
	    try {
	    entity.getTransaction().begin();
	    entity.persist(indicador);
	    entity.getTransaction().commit();
	    } catch(HibernateException e) {
	    	entity.getTransaction().rollback();
	    }
	}
	
	
	public boolean hayOperacionesSinCargar() {
		this.obtenerOperaciones();
		this.obtenerOperacionesIndicador();
		return operaciones.isEmpty() || operacionesIndicador.isEmpty();
	}

	
	public void cargarOperaciones() {
		if(hayOperacionesSinCargar())
			this.persistirOperaciones();
	}
	
	public void persistirOperaciones() {
		EntityTransaction transaccion = entity.getTransaction();
		transaccion.begin();
		entity.persist(new MayorA("MayorA"));
		entity.persist(new MenorA("MenorA"));
		transaccion.commit();
		// Para que el contador se reinicie
		transaccion.begin();
		entity.persist(new Sumatoria("Sumatoria"));
		entity.persist(new Promedio("Promedio"));
		transaccion.commit();
		//
		transaccion.begin();
		Indicador roe = new Indicador("ROE", "(IngresoNeto-dividendos)/capitalTotal");
		Indicador proporcionDeuda = new Indicador("ProporcionDeuda", "FDS/IngresoNeto");
		Indicador margen = new Indicador("Margen", "IngresoNeto/EBITDA");
		Indicador ingresoNeto = new Indicador("IngresoNeto", "ingresoNetoOperacionesContinuas+ingresoNetoOperacionesDiscontinuas");
		entity.persist(roe);
		entity.persist(proporcionDeuda);
		entity.persist(margen);
		entity.persist(ingresoNeto);
		entity.getTransaction().commit();
	}
}
