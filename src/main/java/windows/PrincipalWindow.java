package windows;

import vm.CargaViewModel;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

@SuppressWarnings("serial")
public class PrincipalWindow extends SimpleWindow<CargaViewModel> {
	
	
	public PrincipalWindow(WindowOwner parent) {
		super(parent, new CargaViewModel());
		this.getModelObject().cargarOperaciones();
	}
	
	public void createFormPanel(Panel panelActions) {
	new Panel(panelActions);
	}
	
	@Override
	public void createContents(Panel panelActions) {
		this.setTitle("Analizador de Inversiones");
		panelActions.setLayout(new VerticalLayout());
		new Label(panelActions).setText("                         Menu Principal                         ");
		new Button(panelActions)
		.setCaption("Cargar Cuentas")
		.onClick(this::cargarCuentas); 
		
		new Button(panelActions)
			.setCaption("Cargar Indicador")
			.onClick(this::cargarIndicadores);
		
		new Button(panelActions)
			.setCaption("Cargar Metodologia")
			.onClick(this::ventanaCrear);
		
		new Button(panelActions)
		.setCaption("Analizar Empresas")
		.onClick(this::ventanaBusqueda);
		
		new Label(panelActions).setText("");
	}
	
	protected void addActions(Panel actionsPanel) {}
	
	public void cargarIndicadores() {
		AgregarIndicadorWindow dialog = new AgregarIndicadorWindow(this);
		dialog.open();
	}
	
	public void cargarMetodologias() {
		  
	}
	
	public void cargarCuentas() {
		PathWindow dialog = new PathWindow(this);
		dialog.open();
	}
	
	public void ventanaBusqueda() {
		AnalisisEmpresaWindow dialog = new AnalisisEmpresaWindow(this);
		dialog.open();

	}
	
	public void ventanaCrear() {
		AgregarMetodologiaWindow dialog = new AgregarMetodologiaWindow(this);
		dialog.open();
	}
	
}