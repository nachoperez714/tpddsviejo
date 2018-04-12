package windows;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import vm.ErrorViewModel;

public class ErrorWindow extends Dialog<ErrorViewModel>{

	public ErrorWindow(WindowOwner parent, String texto) {
		super(parent, new ErrorViewModel(texto));
	}
	
	public void createFormPanel(Panel panelActions) {
		new Panel(panelActions);
	}
	
	@Override
	public void createContents(Panel panelActions) {
		this.setTitle("Error");
		panelActions.setLayout(new VerticalLayout());
		new Label(panelActions).bindValueToProperty("texto");
		
		new Button(panelActions)
		.setCaption("Aceptar")
		.onClick(this::close);
		
		new Label(panelActions).setText("");
		
	}
	
}
