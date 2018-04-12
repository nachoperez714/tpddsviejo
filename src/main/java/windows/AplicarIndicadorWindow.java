package windows;


import java.util.NoSuchElementException;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import model.Empresa;
import model.Indicador;
import vm.VerIndicadoresViewModel;

@SuppressWarnings("serial")
public class AplicarIndicadorWindow extends Dialog<VerIndicadoresViewModel> {
	
	public AplicarIndicadorWindow(WindowOwner parent, Empresa unaEmpresa) throws NullPointerException
	{
		super(parent, new VerIndicadoresViewModel(unaEmpresa));
		if(unaEmpresa == null)
			throw new NullPointerException();	
	}
	
	public void createFormPanel(Panel panelActions) {
		new Panel(panelActions);
	}
	
	@Override
	public void createContents(Panel panelActions) {
		
		 panelActions.setLayout(new ColumnLayout(2));
		this.setTitle("Aplicar indicador");
		new Label(panelActions).setText("Seleccionar indicador");
		Selector<Indicador> selector = new Selector<Indicador>(panelActions);
		selector.allowNull(false);
		selector.bindValueToProperty("indicadorSeleccionado");
		selector.bindItemsToProperty("indicadores");
		new Label(panelActions).setText("Seleccionar anio");		
		new TextBox(panelActions).bindValueToProperty("anio");
		new Label(panelActions).setText("Resultado del indicador: ");
		new Label(panelActions).bindValueToProperty("resultado");
		new Button(panelActions)
		.setCaption("Aplicar Indicador")
		.onClick(this::aplicarIndicador);

	}
	
	public void aplicarIndicador()
	{
		try {
			this.getModelObject().aplicarIndicador();
		}
		catch(NullPointerException | NumberFormatException | NoSuchElementException e) {
			Dialog <?> dialog = new ErrorWindow(this, "Datos incompletos o incorrectos");
			dialog.open();
		}
		
	}
}