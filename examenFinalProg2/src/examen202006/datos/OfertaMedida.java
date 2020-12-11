package examen202006.datos;

import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** Oferta medida en tamaño (en dos dimensiones)
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class OfertaMedida extends Oferta implements Cloneable, NecesitaOperacion, Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final double COSTE_ESTANDAR_CORTADO_POR_METRO = 0.50;  // Por metro de corte cuesta 0,50 euros por defecto
	
	private double anchura;  // Ancho que se compra (en m.)
	private double altura;  // Ancho que se compra (en m.)
	private double costeCortado; // Coste de corte por m.
	
	/** Crea una nueva oferta (medida en corte de anchura y altura), con coste estándar {@link #COSTE_ESTANDAR_CORTADO_POR_METRO}
	 * @param nombre	Nombre de producto ofertado
	 * @param precio	Precio de cada kilogramo
	 * @param proveedor	Proveedor de la oferta
	 * @param anchura	Anchura que se compra (en m.)
	 * @param altura	Altura que se compra (en m.)
	 */
	public OfertaMedida(String nombre, double precio, Proveedor proveedor, double anchura, double altura) {
		super(nombre, precio, proveedor);
		this.anchura = anchura;
		this.altura = anchura;
		costeCortado = COSTE_ESTANDAR_CORTADO_POR_METRO;
	}
	
	/** Crea una nueva oferta (medida en corte de anchura y altura), con altura y anchura 0 de compra, con coste estándar {@link #COSTE_ESTANDAR_CORTADO_POR_METRO}
	 * @param nombre	Nombre de producto ofertado
	 * @param precio	Precio de cada kilogramo
	 * @param proveedor	Proveedor de la oferta
	 */
	public OfertaMedida(String nombre, double precio, Proveedor proveedor) {
		this(nombre, precio, proveedor, 0, 0);  // Reutilizamos un constructor desde el otro
	}

	// Método de copia (nos interesa poder duplicar ofertas de este tipo) - por eso implementa Cloneable
	@Override
	public Object clone() {
		return new OfertaMedida(nombre, precio, proveedor, anchura, altura);
	}

	public double getAnchura() {
		return anchura;
	}

	public void setAnchura(double anchura) {
		this.anchura = anchura;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	/** Devuelve el coste de cortado
	 * @return	Coste de cortado de cada metro (en euros)
	 */
	public double getCosteCortado() {
		return costeCortado;
	}

	/** Modifica el coste de cortado
	 * @param costeCortado	Nuevo coste de cortado de cada metro (en euros)
	 */
	public void setCosteCortado(double costeCortado) {
		this.costeCortado = costeCortado;
	}

	@Override
	public double getPrecioTotal() {
		return precio * anchura * altura + getCosteOperacion();
	}

	@Override
	public double getCosteOperacion() {
		return anchura * altura * costeCortado;
	}

	@Override
	public boolean datosCorrectosParaVenta() {
		return (anchura>0 && altura>0);
	}
	
	@Override
	public JPanel getPanelDeDatos() {
		if (panel==null) {  // Si es la primera vez se crea el panel, si no simplemente se devuelve
			panel = super.getPanelDeDatos();
			JPanel pLinea = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
			pLinea.add( new JLabel( "Anchura y altura (metros): ") );
			final JTextField tfAnchura = new JTextField( String.format( "%1.3f", anchura ), 5 );
			final JTextField tfAltura = new JTextField( String.format( "%1.3f", anchura ), 5 );
			pLinea.add( tfAnchura );
			pLinea.add( new JLabel( "x" ) );
			pLinea.add( tfAltura );
			panel.add( pLinea );
			pLinea = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
			pLinea.add( new JLabel( "Coste de cortado (€ / metro):") );
			final JTextField tfCoste = new JTextField( String.format( "%1.2f", costeCortado ), 5 );
			pLinea.add( tfCoste );
			panel.add( pLinea );
			// Eventos de actualización
			tfAnchura.addFocusListener( new FocusAdapter() {  // Al perder el foco, actualiza el atributo
				@Override
				public void focusLost(FocusEvent e) {
					try {
						setAnchura( Double.parseDouble( tfAnchura.getText() ) );
					} catch (NumberFormatException ex) {  // Hay error - se vuelve a dejar el atributo tal y como estaba
						tfAnchura.setText( String.format( "%1.2f", anchura ) );
					}
				}
			});
			tfAltura.addFocusListener( new FocusAdapter() {  // Al perder el foco, actualiza el atributo
				@Override
				public void focusLost(FocusEvent e) {
					try {
						setAltura( Double.parseDouble( tfAltura.getText() ) );
					} catch (NumberFormatException ex) {  // Hay error - se vuelve a dejar el atributo tal y como estaba
						tfAltura.setText( String.format( "%1.2f", altura ) );
					}
				}
			});
			tfCoste.addFocusListener( new FocusAdapter() {  // Al perder el foco, actualiza el atributo
				@Override
				public void focusLost(FocusEvent e) {
					try {
						setCosteCortado( Double.parseDouble( tfCoste.getText() ) );
					} catch (NumberFormatException ex) {  // Hay error - se vuelve a dejar el atributo tal y como estaba
						tfCoste.setText( String.format( "%1.3f", costeCortado ) );
					}
				}
			});
		}
		return panel;
	}
	
	@Override
	public String toString() {
		String ret = super.toString() + String.format( "(%4.2f€ por m2)", precio );
		if (anchura == 0.0 || altura == 0.0) {
			return ret;
		} else {
			return ret + String.format( " - compra de %4.2f x %4.2f m.", anchura, altura );
		}
	}
	
}
