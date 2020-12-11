package examen202006.datos;

import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** Oferta medida en peso
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class OfertaPeso extends Oferta implements Cloneable, NecesitaOperacion, Serializable {
	private static final long serialVersionUID = 1L;
	private static final double COSTE_PESADO = 0.25;  // 0.25 euros cuesta pesar	
	
	private double pesoKg;  // Peso que se compra (en kg.)
	
	/** Crea una nueva oferta (medida en peso)
	 * @param nombre	Nombre de producto ofertado
	 * @param precio	Precio de cada kilogramo
	 * @param proveedor	Proveedor de la oferta
	 * @param pesoKg	Número de kg. que se compran
	 */
	public OfertaPeso(String nombre, double precio, Proveedor proveedor, double pesoKg) {
		super(nombre, precio, proveedor);
		this.pesoKg = pesoKg;
	}
	
	/** Crea una nueva oferta (medida en peso), con 0 kg de compra
	 * @param nombre	Nombre de producto ofertado
	 * @param precio	Precio de cada kilogramo
	 * @param proveedor	Proveedor de la oferta
	 */
	public OfertaPeso(String nombre, double precio, Proveedor proveedor) {
		this(nombre, precio, proveedor, 0);  // Reutilizamos un constructor desde el otro
	}

	// Método de copia (nos interesa poder duplicar ofertas de este tipo) - por eso implementa Cloneable
	@Override
	public Object clone() {
		return new OfertaPeso(nombre, precio, proveedor, pesoKg );
	}

	public double getPesoKg() {
		return pesoKg;
	}

	public void setPesoKg(double pesoKg) {
		this.pesoKg = pesoKg;
	}

	@Override
	public double getPrecioTotal() {
		return precio * pesoKg + COSTE_PESADO;
	}
	
	@Override
	public double getCosteOperacion() {
		return COSTE_PESADO;
	}

	@Override
	public boolean datosCorrectosParaVenta() {
		return (pesoKg>0);
	}
	
	@Override
	public JPanel getPanelDeDatos() {
		if (panel==null) {  // Si es la primera vez se crea el panel, si no simplemente se devuelve
			panel = super.getPanelDeDatos();
			JPanel pLinea = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
			pLinea.add( new JLabel( "Peso (kgs.): ") );
			final JTextField tfPeso = new JTextField( String.format( "%1.3f", pesoKg ), 6 );
			pLinea.add( tfPeso );
			panel.add( pLinea );
			// Eventos de actualización
			tfPeso.addFocusListener( new FocusAdapter() {  // Al perder el foco, actualiza el atributo
				@Override
				public void focusLost(FocusEvent e) {
					try {
						setPesoKg( Double.parseDouble( tfPeso.getText() ) );
					} catch (NumberFormatException ex) {  // Hay error - se vuelve a dejar el atributo tal y como estaba
						tfPeso.setText( String.format( "%1.3f", pesoKg ) );
					}
				}
			});
		}
		return panel;
	}
	
	@Override
	public String toString() {
		String ret = super.toString() + String.format( "(%4.2f€ por kg.)", precio );
		if (pesoKg == 0.0) {
			return ret;
		} else {
			return ret + String.format( " - compra de %4.2f kg.", pesoKg );
		}
	}
	
}
