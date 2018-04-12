package windows;

import vm.AnalisisEmpresaViewModel;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import model.Cuenta;
import model.Empresa;

public class AnalisisEmpresaWindow extends SimpleWindow<AnalisisEmpresaViewModel> {
	
	
	public AnalisisEmpresaWindow(WindowOwner parent) {
		super(parent, new AnalisisEmpresaViewModel());
	}
	
	public void createFormPanel(Panel panelActions) {
	new Panel(panelActions);
	}
	
	@Override
	public void createContents(Panel panelActions) {
		this.setTitle("Analizar empresas");
		panelActions.setLayout(new VerticalLayout());

		new Label(panelActions).setText("		Seleccionar Empresa		");
		Selector<Empresa> selector = new Selector<Empresa>(panelActions);
		selector.allowNull(false);
		selector.bindItemsToProperty("empresas");
		selector.bindValueToProperty("empresaSeleccionada");
				
		new Label(panelActions).setText("		Año inicial		 ");
		new TextBox(panelActions).bindValueToProperty("anioMin");
		new Label(panelActions).setText("		Año final		 ");
		new TextBox(panelActions).bindValueToProperty("anioMax");
		
		new Label(panelActions).setText("");
		
		new Button(panelActions)
		.setCaption("Buscar")
		.onClick(this::mostrarResultados);
		
		new Label(panelActions).setText("");
		
		Table<Cuenta> tableCuentas = new Table<Cuenta>(panelActions, Cuenta.class);
		tableCuentas.setNumberVisibleRows(5).bindItemsToProperty("cuentas");
				
		Column<Cuenta> columnaNombres = new Column<Cuenta>(tableCuentas);
		columnaNombres.setTitle("Nombre").setFixedSize(100).bindContentsToProperty("nombre");
		
		Column<Cuenta> columnaAprobados = new Column<Cuenta>(tableCuentas);
		columnaAprobados.setTitle("Año").setFixedSize(75).bindContentsToProperty("anio");
		
		Column<Cuenta> columnaMonto = new Column<Cuenta>(tableCuentas);
		columnaMonto.setTitle("Monto").setFixedSize(150).bindContentsToProperty("monto");
		
		new Label(panelActions).setText("");
		
		new Button(panelActions)
		.setCaption("Aplicar indicador")
		.onClick(this::verIndicadores);

		new Button(panelActions)
		.setCaption("Aplicar metodologia")
		.onClick(this::verMetodologias);
		
		new Button(panelActions)
		.setCaption("Ver grafico");
		
		new Label(panelActions).setText("");
	}
	
	protected void addActions(Panel actionsPanel) {}
	
	public void verIndicadores() {
		if(getModelObject().getEmpresaSeleccionada() != null) {
			Dialog <?> dialog = new AplicarIndicadorWindow(this, this.getModelObject().getEmpresaSeleccionada());
			dialog.open();			
		} else
			this.mostrarMensajeDerror("Seleccione una Empresa");	
	}
	
	public void verMetodologias()
	{
		AplicarMetodologiaWindow dialog = new AplicarMetodologiaWindow(this);
		dialog.open();
	}
	
	public void mostrarResultados() 
	{
		try 
		{
			this.getModelObject().mostrarCuentas();
		}
		catch(NullPointerException | NumberFormatException e)
		{
			this.mostrarMensajeDerror("Hay campos incompletos");
		}

	}
	
	private void mostrarMensajeDerror(String texto)
	{
		Dialog <?> dialog = new ErrorWindow(this, texto);
		dialog.open();
	}

}
