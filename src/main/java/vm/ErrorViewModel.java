package vm;

import org.uqbar.commons.utils.Observable;

@Observable
public class ErrorViewModel {
	
	//------------------------------------- ATRIBUTOS ------------------------------------

	private String texto;

	//----------------------------------- CONSTRUCTORES --------------------------------
	
	public ErrorViewModel(String texto)
	{
		this.texto= texto;
	}
	
	//------------------------------- GETTERS Y SETTERS ---------------------------------
	
	public String getTexto() {
		return texto;
	}
}
