package vm;

import java.io.IOException;

import model.FileHandler;

import org.uqbar.commons.utils.Observable;


@Observable
public class PathViewModel {
	
	//------------------------------------- ATRIBUTOS ----------------------------------
	
	private String path = "";
	
	//------------------------------- GETTERS Y SETTERS --------------------------------

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	//--------------------------------------- METODOS ----------------------------------
	
	public void cargarCuentas() throws IOException{
		FileHandler lector= new FileHandler();
		lector.importarArchivoCuentas(path);
	}
	
}
