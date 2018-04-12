package windows;

import java.util.NoSuchElementException;

import vm.AgregarMetodologiaViewModel;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

public class AgregarMetodologiaWindow extends SimpleWindow<AgregarMetodologiaViewModel> {
	
	public AgregarMetodologiaWindow(WindowOwner parent) {
		super(parent, new AgregarMetodologiaViewModel());
	}
	
	public void createFormPanel(Panel panelActions) {
	new Panel(panelActions);
	}
	
	@Override
	public void createContents(Panel panelActions) {
		this.setTitle("Nueva metodologia");
		panelActions.setLayout(new VerticalLayout());
		
		new Label(panelActions).setText("          Ingrese un nombre          ");
		new TextBox(panelActions).bindValueToProperty("nombreNuevaMetodologia");
		
		new Label(panelActions).setText("");
		
		new Button(panelActions)
		.setCaption("Agregar condicion")
		.onClick(this:: agregarCondicion);
	
		new Button(panelActions)
			.setCaption("Aceptar")
			.onClick(this::crearNuevaMetodologia);
		
		new Label(panelActions).setText("");
		
	}
	
	protected void addActions(Panel actionsPanel) {}
		
	public void crearNuevaMetodologia() {
		try {
			if(this.usuarioSePasaDeListo()) {
				ErrorWindow dialog = new ErrorWindow(this, "Datos incompletos o incorrectos");
				dialog.open();
			} else {
				this.getModelObject().crearMetodologia();
				this.close();
			}
		}
		catch(NullPointerException | NumberFormatException | NoSuchElementException e) {
			Dialog <?> dialog = new ErrorWindow(this, "Datos incompletos o incorrectos");
			dialog.open();
		}
	}
	
	public boolean usuarioSePasaDeListo() {
		return getModelObject().getCondicionesDeLaMetodologia().isEmpty() || getModelObject().getNombreNuevaMetodologia().isEmpty();
	}
	
	public void agregarCondicion() {
		ElegirCondicionWindow dialog = new ElegirCondicionWindow(this);
		dialog.open();
	}
	
}
