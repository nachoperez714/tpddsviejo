package windows;


import vm.AgregarIndicadorViewModel;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

@SuppressWarnings("serial")
public class AgregarIndicadorWindow extends SimpleWindow<AgregarIndicadorViewModel> {
	
	
	public AgregarIndicadorWindow(WindowOwner parent) {
		super(parent, new AgregarIndicadorViewModel());
	}
	
	public void createFormPanel(Panel panelActions) {
	new Panel(panelActions);
	}
	
	@Override
	public void createContents(Panel panelActions) {
		this.setTitle("Nuevo indicador");
		panelActions.setLayout(new VerticalLayout());
		new Label(panelActions).setText("         Ingrese un nombre         ");
		new TextBox(panelActions).bindValueToProperty("nombreIndicador");
		new Label(panelActions).setText("Ingrese una formula");
		new TextBox(panelActions).bindValueToProperty("formulaIndicador");
		
		new Label(panelActions).setText("");
		
		new Button(panelActions)
			.setCaption("Aceptar")
			.onClick(this::crearIndicador);
		
		new Label(panelActions).setText("");
		
	}
	
	protected void addActions(Panel actionsPanel) {}
	
	public void crearIndicador() {
		try {
			this.getModelObject().guardarIndicador();
			this.close();
		}
		catch(ParseCancellationException | NullPointerException e) {
			Dialog <?> dialog = new ErrorWindow(this, "Datos incompletos o incorrectos");
			dialog.open();
		}
	}
	
}