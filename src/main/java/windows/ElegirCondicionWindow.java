package windows;

import vm.ElegirCondicionViewModel;
import model.Metodologia;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

public class ElegirCondicionWindow extends SimpleWindow<ElegirCondicionViewModel> {
	
	static final String TIPO1 = "Analizar indicador en los ultimos anios";
	static final String TIPO2 = "Analizar indicadores entre empresas";
	static final String TIPO3 = "Analizar historia del indicador";
	static final String TIPO5 = "Analizar antiguedad de la empresa";
	
	private AgregarMetodologiaWindow ventanaMetodologia;
	
	public AgregarMetodologiaWindow getVentanaMetodologia() {
		return ventanaMetodologia;
	}

	public ElegirCondicionWindow(WindowOwner parent) {
		super(parent, new ElegirCondicionViewModel());
		ventanaMetodologia = (AgregarMetodologiaWindow)parent;
	}

	@Override
	public void createContents(Panel panelActions)  {
		this.setTitle("Nueva condicion");
		panelActions.setLayout(new VerticalLayout());
		
		new Label(panelActions).setText("            Seleccione una condicion            ");
		Selector<Metodologia> selector1 = new Selector<Metodologia>(panelActions);
		selector1.allowNull(false);
		selector1.bindValueToProperty("condicionSeleccionada");
		selector1.bindItemsToProperty("condiciones");
		
		new Label(panelActions).setText("");
		
		new Button(panelActions)
		.setCaption("Configurar condicion")
		.onClick(this:: configurar);
		
		new Label(panelActions).setText("");
	}

	public void configurar() {
		try {
			configurarCondicion();
		}
		catch(NullPointerException e) {
			Dialog <?> dialog = new ErrorWindow(this, "Debe seleccionar una condicion");
			dialog.open();
		}
	}
	
	public void configurarCondicion() {
		
		switch(this.getModelObject().getcondicionSeleccionada()){
		
		case TIPO2:
			AgregarCondicionTipo2Window dialog = new AgregarCondicionTipo2Window(this, TIPO2);
			dialog.open();
			break;
		
		case TIPO3:
			AgregarCondicionTipo3Window dialog1 = new AgregarCondicionTipo3Window(this, TIPO3);
			dialog1.open();
			break;
			
		case TIPO5:
			this.getModelObject().setCondicion(this.getModelObject().crearCondicion());
			ventanaMetodologia.getModelObject().agregarCondicion(this.getModelObject().getCondicion());
			this.close(); break;
			
		default: 
			AgregarCondicionTipo1o4Window dialog2 = new AgregarCondicionTipo1o4Window(this, TIPO1);
			dialog2.open();
			break;
			
			}
		}
	
	public void cerrar(){
		this.close();
	}
	
	@Override
	protected void createFormPanel(Panel arg0) {
		
	}

	@Override
	protected void addActions(Panel arg0) {
		
	}
}