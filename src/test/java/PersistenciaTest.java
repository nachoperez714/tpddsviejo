import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Empresa;
import model.FileHandler;
import repositories.Repositorios;
import vm.PathViewModel;


public class PersistenciaTest{
	
	private List<Empresa> listaEmpresas = new ArrayList<Empresa>();;
	
	@Before
	public void init() {
		
	}
	
	@Test
	public void comprobarPersistenciaDeUnaEmpresa() {
		
		Empresa empresa = new Empresa("Facebook", 2004);
		empresa.guardar();
		Empresa empresaPersistida = Repositorios.repositorioEmpresas.buscarEmpresaPorId(empresa.getIdEmpresa());
		
		Assert.assertEquals(empresa.getNombre(), empresaPersistida.getNombre());
		Assert.assertEquals(empresa.getAnioFundacion(), empresaPersistida.getAnioFundacion());
	}
	
	@Test
	public void tiraExcepcionSiNoEncuentraArchivoCuentas() {
		PathViewModel vm = new PathViewModel();
		vm.setPath("humo");
		try {
			vm.cargarCuentas();
			Assert.fail();
		}
		catch(FileNotFoundException e)
		{}
		catch(IOException e)
		{}
	}
	
	@Test 
	@SuppressWarnings("unchecked")
	public void filtraUnaListaDeEmpresasMedianteUnaQuery() {
		
		EntityManager entity = PerThreadEntityManagers.getEntityManager();
		
		Empresa twitter = new Empresa("Twitter", 2006);
		Empresa manaos = new Empresa("Manaos", 2011);
		Empresa nike = new Empresa("Nike", 1964);
	    
		try {
			
	    entity.getTransaction().begin();
	    entity.persist(nike);
	    entity.persist(manaos);
	    entity.persist(twitter);
	    listaEmpresas = entity.createQuery("FROM Empresa WHERE anioFundacion = 2011").getResultList();
	    entity.getTransaction().commit();

	    } catch(HibernateException e) {
	    	entity.getTransaction().rollback();
	    } finally {
	    	entity.close();
	    }
	    Assert.assertEquals(1, listaEmpresas.size());
	    Assert.assertEquals(manaos.getNombre(), listaEmpresas.get(0).getNombre());
		

	}
	
	
	@Test
	@SuppressWarnings("unchecked")
	public void cargarCuentasDesdeUnArchivo() throws IOException
	{
		FileHandler lector = new FileHandler();
		lector.importarArchivoCuentas(new File("").getAbsolutePath().concat("\\cuentas2.csv"));
		EntityManager entity = PerThreadEntityManagers.getEntityManager();
		
		try {
		    entity.getTransaction().begin();
		 
		    listaEmpresas = entity.createQuery("FROM Empresa").getResultList();
		    entity.getTransaction().commit();

		    } catch(HibernateException e) {
		    	entity.getTransaction().rollback();
		    } finally {
		    	entity.close();
		    }
		
		Assert.assertEquals(4, listaEmpresas.size());
		Assert.assertEquals(6, listaEmpresas.stream().mapToInt(x->x.getCuentas().size()).sum());
	}
	
	@After
	public void end() {
		Repositorios.repositorioEmpresas.limpiar();
	}
	
}