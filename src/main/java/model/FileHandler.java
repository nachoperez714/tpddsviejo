package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.opencsv.CSVReader;

import repositories.Repositorios;

public class FileHandler {
	
	//------------------------------------ CONSTRUCTORES --------------------------------
	
	public FileHandler() {
		
	}
	
	//------------------------------------ METODOS --------------------------------
	
	public CSVReader crearReader(String path) throws IOException {
		return new CSVReader(new FileReader(path), ';');
	}
	
	public void leerArchivoCuentas(String path) throws IOException {
		CSVReader reader= this.crearReader(path);
	    String [] linea;
	    while ((linea = reader.readNext()) != null)
	    	Repositorios.repositorioEmpresas.guardarCuenta(linea);
	}
	
	public void importarArchivoCuentas(String path) throws IOException  {
		Repositorios.repositorioEmpresas.obtenerEmpresas();
		leerArchivoCuentas(path);
	    Repositorios.repositorioEmpresas.persistirEmpresas();
	    Repositorios.repositorioEmpresas.limpiar();
	}
	
	public void leerArchivoIndicadores(String path)
	{
		CSVReader reader;
		try {
			reader = this.crearReader(path);
			String [] linea;
			while ((linea = reader.readNext()) != null) {
	        
	        Indicador unIndicador = new Indicador(linea[0], linea[1]);
	    	Repositorios.repositorioIndicadores.agregarIndicador(unIndicador);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void guardarIndicador(String path, Indicador indicador)
	{
		File archivo = new File(path);
		PrintWriter escritor;
		try {
			escritor = new PrintWriter(new FileOutputStream(archivo, true));
			escritor.append(indicador.getNombreIndicador() + ";" + indicador.getFormula() + "\n");
			escritor.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
}