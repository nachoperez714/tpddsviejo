package windows;

import vm.PathViewModel;

import java.io.IOException;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

@SuppressWarnings("serial")
public class PathWindow extends SimpleWindow<PathViewModel> {
	
	
	public PathWindow(WindowOwner parent) {
		super(parent, new PathViewModel());
	}
	
	public void createFormPanel(Panel panelActions) {
	new Panel(panelActions);
	}
	
	@Override
	public void createContents(Panel panelActions) {
		this.setTitle("Cargar cuentas");
		panelActions.setLayout(new VerticalLayout());
		
		new Label(panelActions).setText("			Ingrese la ruta del archivo			");
		new TextBox(panelActions).bindValueToProperty("path");
		
		new Label(panelActions).setText("");
		
		new Button(panelActions)
			.setCaption("Aceptar")
			.onClick(this::aceptar);
		
		new Label(panelActions).setText("");
	}
	
	protected void addActions(Panel actionsPanel) {}
	
	public void aceptar() {
		try {
			this.getModelObject().cargarCuentas();
			this.close();
		}
		catch(IOException e)
		{
			ErrorWindow dialog = new ErrorWindow(this, "Ruta incorrecta");
			dialog.open();
		}
		
		
	}
}
