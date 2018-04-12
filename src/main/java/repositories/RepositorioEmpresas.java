package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Cuenta;
import model.Empresa;

public class RepositorioEmpresas {

	//------------------------------------ ATRIBUTOS --------------------------------
	
	private List<Empresa> empresas;
	private EntityManager entity;
	
	//------------------------------------ CONSTRUCTORES --------------------------------
	
	public RepositorioEmpresas(){
		this.empresas = new ArrayList<Empresa>();
		this.entity = PerThreadEntityManagers.getEntityManager();
		
	}
	
	//------------------------------------ GETTERS Y SETTERS --------------------------------
	
	public List<Empresa> getEmpresas(){
		return this.empresas;
	}
	
	//------------------------------------ METODOS--------------------------------
	
	public List<Empresa> buscarEmpresasPorNombre(List<String> nombres) {
		this.obtenerEmpresas();
		return empresas.stream().filter(e-> nombres.contains(e.getNombre())).collect(Collectors.toList());
	}
	
	public boolean existeEmpresa(String empresa){
		return empresas.stream().anyMatch(x->x.getNombre().equals(empresa));
	}
	
	public Empresa find(Predicate<? super Empresa> criterio) {
		return this.empresas.stream().filter(criterio).findFirst().get();
	}
	
	public void agregarEmpresa(Empresa empresa) {
		empresas.add(empresa);
	}
	
	
	public void guardarCuenta(String[] linea) {
		//0 = NombreEmpresa | 1 = AnioFundacion  | 2 = NombreCuenta | 3 = anioCuenta | 4 = MontoCuenta
		String nombreEmpresa = linea[0];
		int anioFundacion = Integer.parseInt(linea[1]);
		String nombreCuenta = linea[2];
		int anioCuenta = Integer.parseInt(linea[3]);
		String montoCuenta = linea[4];
		Empresa empresa = this.obtenerEmpresa(nombreEmpresa, anioFundacion);
		Cuenta cuenta = new Cuenta(nombreCuenta, anioCuenta, montoCuenta, empresa);
		empresa.agregarCuenta(cuenta);
	}
	
	public Empresa generarEmpresa(String nombreEmpresa, int anioFundacion) {
		Empresa empresa = new Empresa(nombreEmpresa, anioFundacion);
		empresas.add(empresa);
		return empresa;
	}
	
	
	public List<Cuenta> filtrarCuentasPorPeriodo(String nombreEmpresa, String anioMin, String anioMax){
	Empresa empresa = buscarEmpresa(nombreEmpresa);
	return empresa.getCuentas().stream().filter(unaCuenta -> this.cuentaEstaDentroDelPeriodo(unaCuenta, anioMin, anioMax))
			.collect(Collectors.toList());
	}
	
	public boolean cuentaPertenceAPeriodo(Cuenta cuenta, int min, int max) {
		return cuenta.getAnio() >= min && cuenta.getAnio() <= max;
	}
	
	public boolean cuentaEstaDentroDelPeriodo(Cuenta cuenta, String anioMin, String anioMax) {
		int anio1 = Integer.parseInt(anioMin);
		int anio2 = Integer.parseInt(anioMax);
		return this.cuentaPertenceAPeriodo(cuenta, anio1, anio2);
	}
	
	public Empresa obtenerEmpresa(String nombreEmpresa, int anioFundacion) {
		if(this.existeEmpresa(nombreEmpresa))
			return this.buscarEmpresa(nombreEmpresa);
		else
			return this.generarEmpresa(nombreEmpresa, anioFundacion);
	}
	
	public Empresa buscarEmpresaPorId(int unId) {
		EntityManager entity = PerThreadEntityManagers.getEntityManager();
		Empresa empresa = entity.find(Empresa.class, unId);
		return empresa;
	}
	
	public Empresa buscarEmpresa(String nombre) {
		return this.find(e -> e.getNombre().equals(nombre));
	}
	
	public void limpiar() {
		empresas.clear();
	}
	
	public void eliminarEmpresa(String empresa) {
		this.empresas.removeIf(x->x.getNombre().equals(empresa));
	}
	
	//MAGIA NEGRA
	
	public void aumentarPuntuacion(Empresa unaEmpresa) {
	    try {
	    entity.getTransaction().begin();
	    entity.merge(unaEmpresa);
	    unaEmpresa.aumentarPuntuacion();
	    entity.getTransaction().commit();
	    } catch(HibernateException e) {
	    	entity.getTransaction().rollback();
	    }
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void obtenerEmpresas() {
		empresas = (List<Empresa>)entity.createQuery("FROM Empresa").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cuenta> obtenerCuentasDeUnPeriodo(int id, String anio1, String anio2) {
		int anioMinimo = Integer.parseInt(anio1);
		int anioMaximo = Integer.parseInt(anio2);
		Query query = entity.createQuery("FROM Cuenta WHERE anio >= :anioMinimo AND anio <= :anioMaximo AND idEmpresa = :id");
		query.setParameter("id", id);
		query.setParameter("anioMinimo", anioMinimo);
		query.setParameter("anioMaximo", anioMaximo);
		List<Cuenta> cuentas = query.getResultList();
		return cuentas; 
	}
	
	public void persistirEmpresas() {
	    try {
	    entity.getTransaction().begin();
	    for(Empresa empresa : empresas)
	    	entity.persist(empresa);
	    entity.getTransaction().commit();
	    } catch(HibernateException e) {
	    	entity.getTransaction().rollback();
	    }
	}
	
	public void persistirEmpresa(Empresa unaEmpresa) {
		EntityManager entity = PerThreadEntityManagers.getEntityManager();
	    try {
	    entity.getTransaction().begin();
	    	entity.persist(unaEmpresa);
	    entity.getTransaction().commit();
	    } catch(HibernateException e) {
	    	entity.getTransaction().rollback();
	    }
	}

}
