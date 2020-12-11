package examen202006.datos;

import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** Oferta medida en unidades
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class OfertaUnidades extends Oferta implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	protected int numUnidades;  // Num. de unidades que se compran
	
	/** Crea una nueva oferta (medida en unidades)
	 * @param nombre	Nombre de producto ofertado
	 * @param precio	Precio de cada unidad (en euros)
	 * @param proveedor	Proveedor de la oferta
	 * @param numUnidades	Número de unidades que se compran
	 */
	public OfertaUnidades(String nombre, double precio, Proveedor proveedor, int numUnidades) {
		super(nombre, precio, proveedor);
		this.numUnidades = numUnidades;
	}
	
	/** Crea una nueva oferta (medida en unidades), con 0 unidades de compra
	 * @param nombre	Nombre de producto ofertado
	 * @param precio	Precio de cada unidad (en euros)
	 * @param proveedor	Proveedor de la oferta
	 */
	public OfertaUnidades(String nombre, double precio, Proveedor proveedor) {
		this(nombre, precio, proveedor, 0);  // Reutilizamos un constructor desde el otro
	}

	// Método de copia (nos interesa poder duplicar ofertas de este tipo) - por eso implementa Cloneable
	@Override
	public Object clone() {
		return new OfertaUnidades(nombre, precio, proveedor, numUnidades);
	}

	public int getNumUnidades() {
		return numUnidades;
	}

	public void setNumUnidades(int numUnidades) {
		this.numUnidades = numUnidades;
	}

	@Override
	public double getPrecioTotal() {
		return precio * numUnidades;
	}
	
	@Override
	public boolean datosCorrectosParaVenta() {
		return (numUnidades>0);
	}
	
	@Override
	public JPanel getPanelDeDatos() {
		if (panel==null) {  // Si es la primera vez se crea el panel, si no simplemente se devuelve
			panel = super.getPanelDeDatos();
			JPanel pLinea = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
			pLinea.add( new JLabel( "Unidades:") );
			final JTextField tfUds = new JTextField( String.format( "%1d", numUnidades ), 3 );
			pLinea.add( tfUds );
			panel.add( pLinea );
			// Eventos de actualización
			tfUds.addFocusListener( new FocusAdapter() {  // Al perder el foco, actualiza el atributo
				@Override
				public void focusLost(FocusEvent e) {
					try {
						setNumUnidades( Integer.parseInt( tfUds.getText() ) );
					} catch (NumberFormatException ex) {  // Hay error - se vuelve a dejar el atributo tal y como estaba
						tfUds.setText( String.format( "%1d", numUnidades ) );
					}
				}
			});
		}
		return panel;
	}
	
	@Override
	public String toString() {
		String ret = super.toString() + String.format( "(%4.2f€)", precio );
		if (numUnidades == 0) {
			return ret;
		} else {
			return ret + " - compra de " + numUnidades + " unidades.";
		}
	}
	
}
