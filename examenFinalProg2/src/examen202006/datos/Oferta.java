package examen202006.datos;

import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.Serializable;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** Clase padre de la jerarquía de ofertas
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public abstract class Oferta implements Cloneable, Serializable, Comparable<Oferta> 
{  // Queremos que todas sus hijas se puedan duplicar
	private static final long serialVersionUID = 1L;
	protected String nombre;  // Nombre de la oferta
	protected double precio;  // Precio unitario
	protected Proveedor proveedor;  // Nacionalidad del proveedor de la oferta
	
	/** Crea una nueva oferta
	 * @param nombre	Nombre de producto ofertado
	 * @param precio	Precio de cada unidad (en euros)
	 * @param proveedor	Proveedor de la oferta
	 */
	public Oferta(String nombre, double precio, Proveedor proveedor) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.proveedor = proveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

		transient protected JPanel panel; // Panel interno de datos de la oferta  (transient porque no se serializa)
	/** Devuelve un panel asociado a los datos de la oferta, con un formulario que permite editar los datos del panel
	 * y actualiza los atributos cuando el usuario modifica esos datos
	 * @return	true si se han introducido los datos correctamente (y se dejan en los atributos correspondientes), false si no se han introducido correctamente
	 */
	public JPanel getPanelDeDatos() {
		if (panel==null) {  // Si es la primera vez se crea el panel, si no simplemente se devuelve
			panel = new JPanel();
			panel.setLayout( new BoxLayout(panel, BoxLayout.Y_AXIS) );
			JPanel pLinea = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
			pLinea.add( new JLabel( "Nombre: ") );
			final JTextField tfNombre = new JTextField( nombre, 15 );
			pLinea.add( tfNombre );
			panel.add( pLinea );
			pLinea = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
			pLinea.add( new JLabel( "Precio (€): " ) );
			final JTextField tfPrecio = new JTextField( String.format( "%1.2f", precio ), 5 );
			pLinea.add( tfPrecio );
			panel.add( pLinea );
			pLinea = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
			pLinea.add( new JLabel( "Proveedor: " ) );
			final JTextField tfProveedor = new JTextField( proveedor.toString(), 8 );
			pLinea.add( tfProveedor );
			panel.add( pLinea );
			// Eventos de actualización
			tfNombre.addFocusListener( new FocusAdapter() {  // Al perder el foco, actualiza el nombre
				@Override
				public void focusLost(FocusEvent e) {
					setNombre( tfNombre.getText() );
				}
			});
			tfPrecio.addFocusListener( new FocusAdapter() {  // Al perder el foco, actualiza el precio
				@Override
				public void focusLost(FocusEvent e) {
					try {
						setPrecio( Double.parseDouble( tfPrecio.getText() ) );
					} catch (NumberFormatException ex) {
						// Hay error - se vuelve a dejar el precio tal y como estaba
						tfPrecio.setText( String.format( "%1.2f", precio ) );
					}
				}
			});
			tfProveedor.addFocusListener( new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					validarProveedor( tfProveedor );
				}
			});
		}
		return panel;
	}

		// Método de validación de proveedor desde el cuadro de texto (si el string es válido se actualiza, si no se mantiene el valor anterior
		private void validarProveedor( JTextField tfProveedor ) {
			try {
				setProveedor( Proveedor.valueOf( tfProveedor.getText().toUpperCase() ) );
			} catch (IllegalArgumentException e) {
				tfProveedor.setText( proveedor.toString() );
			}
		}

	/** Devuelve el precio total (en euros) de la oferta que se desea comprar (dependiendo de la cantidad), incluyendo posibles costes de operación)
	 * @return	Precio total en euros
	 */
	public abstract double getPrecioTotal();
	
	/** Informa si la oferta tiene los datos definidos correctos para realizar una venta con esa oferta
	 * @return	true si la oferta tiene los datos correctos para una venta, false en caso contrario
	 */
	public abstract boolean datosCorrectosParaVenta();
	
	@Override
	public abstract Object clone();
	
	@Override
	public String toString() {
		return nombre + " (" + proveedor + ") ";
	}
	@Override
	public int compareTo(Oferta o) {
		return nombre.compareTo(o.nombre);
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return (int) (nombre.hashCode() - getPrecioTotal());
	}

}
