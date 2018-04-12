package vm;

import repositories.Repositorios;

public class CargaViewModel {

	//--------------------------------------- METODOS -------------------------------------
	
	public void cargarOperaciones() {
		Repositorios.repositorioIndicadores.cargarOperaciones();
	}
	
}
