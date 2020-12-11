package examen202006;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import examen202006.datos.*;
import examen202006.ventanas.VentanaMenu;

/** Clase principal del ejemplo de ofertas
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class MainTienda {

	private static VentanaMenu ventana;         // Ventana principal
	private static TiendaOfertas sistema;       // Sistema de trabajo
	public static final String NOM_FICHERO = "ofertasExamen202006.dat";  // Ruta relativa - carpeta raíz del proyecto
	private static final boolean REINICIA_DATOS = true;  // Variable para reiniciar los datos (si se pone a true no carga el fichero sino que inicia los datos de prueba)
	
	/** Método principal - crea un sistema nuevo y lanza la interacción 
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		sistema = new TiendaOfertas();
		init();
		menu();
	}
	
	/** Devuelve el sistema de datos de la tienda
	 * @return	tienda de ofertas actual
	 */
	public static TiendaOfertas getSistema() {
		return sistema;
	}
	
	/** Devuelve la ventana de menú principal de la tienda
	 * @return	ventana de menú
	 */
	public static VentanaMenu getVentana() {
		return ventana;
	}

	// Inicializa el sistema cargando el fichero, o con tres productos de ejemplo si el fichero no existe o es erróneo
	private static void init() {
		try {
			if (REINICIA_DATOS) throw new NullPointerException(); 
			sistema.cargarDatos( NOM_FICHERO );
		} catch (Exception e) {
			Oferta[] ofertas = {
				new OfertaUnidades( "Rollo de papel higiénico", 0.75, Proveedor.CHINA ),
				new OfertaPeso( "Paracetamol en polvo", 61.25, Proveedor.EEUU ),
				new OfertaMedida( "Tela de mascarilla", 10.28, Proveedor.NACIONAL ),
				new OfertaUnidades( "Rollo de papel higiénico", 0.82, Proveedor.NACIONAL ),
				new OfertaPeso( "Paracetamol en polvo", 58.75, Proveedor.CHINA ),
				new OfertaMedida( "Tela de mascarilla", 11.15, Proveedor.EEUU ),
				new OfertaTalla("Albornoz Jedi", 7.77, Proveedor.EEUU, Talla.XXL)
			};
			// Añade ofertas
			for (Oferta oferta : ofertas) {
				sistema.getListaOfertas().add( oferta );
			}
			// Añade historial de ventas
			ArrayList<Oferta> ventas1 = new ArrayList<>();
			OfertaUnidades venta1a = (OfertaUnidades) ofertas[0].clone();
			venta1a.setNumUnidades( 2 );
			ventas1.add( venta1a );
			OfertaPeso venta1b = (OfertaPeso) ofertas[1].clone();
			venta1b.setPesoKg( 0.5 );
			ventas1.add( venta1b );
			OfertaMedida venta1c = (OfertaMedida) ofertas[2].clone();
			venta1c.setAnchura( 2.0 );
			venta1c.setAltura( 1.5 );
			ventas1.add( venta1c );
			sistema.addVenta( "andoni", new Venta( new GregorianCalendar( 2020, 5, 1 ).getTimeInMillis(), ventas1 ), true );
			ArrayList<Oferta> ventas2 = new ArrayList<>();
			OfertaUnidades venta2a = (OfertaUnidades) ofertas[3].clone();
			venta2a.setNumUnidades( 5 );
			ventas2.add( venta2a );
			OfertaPeso venta2b = (OfertaPeso) ofertas[4].clone();
			venta2b.setPesoKg( 0.8 );
			ventas2.add( venta2b );
			OfertaPeso venta2c = (OfertaPeso) ofertas[1].clone();
			venta2c.setPesoKg( 0.7 );
			ventas2.add( venta2c );
			OfertaMedida venta2d = (OfertaMedida) ofertas[5].clone();
			venta2d.setAnchura( 10.0 );
			venta2d.setAltura( 5.0 );
			ventas2.add( venta2d );
			sistema.addVenta( "itxaso", new Venta( new GregorianCalendar( 2020, 5, 2 ).getTimeInMillis(), ventas2 ), true );
			ArrayList<Oferta> ventas3 = new ArrayList<>();
			OfertaUnidades venta3a = (OfertaUnidades) ofertas[0].clone();
			venta3a.setNumUnidades( 10 );
			ventas3.add( venta3a );
			OfertaPeso venta3b = (OfertaPeso) ofertas[4].clone();
			venta3b.setPesoKg( 1.0 );
			ventas3.add( venta3b );
			sistema.addVenta( "andoni", new Venta( new GregorianCalendar( 2020, 5, 3 ).getTimeInMillis(), ventas3 ), true );
		}
	}

	// Ventana principal del sistema
	private static void menu() {
		ventana = new VentanaMenu();
		ventana.setVisible( true );
	}

}
