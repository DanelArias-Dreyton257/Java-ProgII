package examen202006.datos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/** Clase para crear objetos de venta
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	private long fecha;                // Fecha de venta
	private ArrayList<Oferta> lVenta;  // Lista de ofertas vendidas
	
	/** Crea una nueva venta
	 * @param fecha	Fecha de la venta (milisegundos desde el 1/1/1970)
	 * @param lVenta	Lista de ofertas vendidas
	 */
	public Venta(long fecha, ArrayList<Oferta> lVenta) {
		super();
		this.fecha = fecha;
		this.lVenta = lVenta;
	}

	public long getFecha() {
		return fecha;
	}

	public void setFecha(long fecha) {
		this.fecha = fecha;
	}

	public ArrayList<Oferta> getlVenta() {
		return lVenta;
	}

	public void setlVenta(ArrayList<Oferta> lVenta) {
		this.lVenta = lVenta;
	}
	
		private transient static SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy HH:mm" ); // formato de fecha de venta

	@Override
	public String toString() {
		return sdf.format( new Date(fecha) ) + ": " + lVenta.toString();
	}
	
}
