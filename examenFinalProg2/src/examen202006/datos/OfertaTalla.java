package examen202006.datos;

import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;
/**
 * 
 * @author Danel
 *
 */
public class OfertaTalla extends OfertaUnidades {
	
	private static final long serialVersionUID = 1L;
	
	private Talla talla;

	/** Crea una nueva oferta (medida en unidades y talla)
	 * @param nombre	Nombre de producto ofertado
	 * @param precio	Precio de cada unidad (en euros)
	 * @param proveedor	Proveedor de la oferta
	 * @param talla elemento de enum Talla
	 * @param numUnidades	Número de unidades que se compran
	 */
	public OfertaTalla(String nombre, double precio, Proveedor proveedor, Talla talla, int numUnidades) {
		super(nombre, precio, proveedor, numUnidades);
		this.talla = talla;
	}
	/** Crea una nueva oferta (medida en unidades y talla), con 0 unidades de compra
	 * @param nombre	Nombre de producto ofertado
	 * @param precio	Precio de cada unidad (en euros)
	 * @param proveedor	Proveedor de la oferta
	 * @param talla elemento de enum Talla
	 */
	public OfertaTalla(String nombre, double precio, Proveedor proveedor, Talla talla) {
		this(nombre, precio, proveedor,talla,0);
	}
	/**
	 * Devuelve la talla de la oferta
	 * @return
	 */
	public Talla getTalla() {
		return talla;
	}
	/**
	 * Establece la talla de la oferta
	 * @param talla
	 */
	public void setTalla(Talla talla) {
		this.talla = talla;
	}
	
	// Método de copia (nos interesa poder duplicar ofertas de este tipo) - por eso implementa Cloneable
		@Override
		public Object clone() {
			return new OfertaTalla(nombre, precio, proveedor,talla, numUnidades);
		}
	
	@Override
	public double getPrecioTotal() {
		double sinTalla = precio * numUnidades;
		return sinTalla + sinTalla*talla.getPorcentaje();
	}
	@Override
	public JPanel getPanelDeDatos() {
		if (panel==null) {
			panel = super.getPanelDeDatos();
			JComboBox<Talla> cbtallas = new JComboBox<>(Talla.values());
			JPanel pLinea = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
			pLinea.add(new JLabel("Talla:"));
			pLinea.add(cbtallas);
			panel.add(pLinea);
			for (int i=0; i<Talla.values().length;i++) {
				if (Talla.values()[i] == talla) {
					cbtallas.setSelectedIndex(i);
				}
			}
			cbtallas.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					setTalla((Talla)cbtallas.getSelectedItem());
				}
			});
		}
		return panel;
	}
	 @Override
	public String toString() {
		return super.toString() +" "+talla;
	}

}
