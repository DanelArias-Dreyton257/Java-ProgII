package examen202006;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import examen202006.datos.Oferta;
import examen202006.datos.Proveedor;
import examen202006.datos.Venta;

/** Clase contenedora del ejercicio de Ofertas
 * Contiene la lista de todas las ofertas en curso
 * y almacena también la lista de todas las ofertas vendidas
 * Permite E/S básica y operatividad esencial sobre las ofertas
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class TiendaOfertas {
	// Ofertas en curso
	ArrayList<Oferta> listaOfertas;
	// Mapa de ventas realizadas a cada usuario (clave usuario, valor lista de ventas a ese usuario)
	HashMap<String,ArrayList<Venta>> mapaVentas;
	
	HashMap<Proveedor,TreeSet<Oferta>> mapaProveedores;

	/** Crea un objeto de ofertas con los datos de ofertas y ventas vacíos
	 */
	public TiendaOfertas() {
		listaOfertas = new ArrayList<>();
		mapaVentas = new HashMap<>();
		mapaProveedores = new HashMap<>();
		initMapaProveedores();
	}
	/**
	 * Inicializa el mapa de proveedores con cada proveedor y sus respectivos treesets
	 */
	private void initMapaProveedores() {
		for (Proveedor p: Proveedor.values()) {
			mapaProveedores.put(p, new TreeSet<>());
		}
		
	}

	/** Devuelve la lista de ofertas disponibles
	 * @return	Ofertas en curso
	 */
	public ArrayList<Oferta> getListaOfertas() {
		return listaOfertas;
	}

	/** Añade una venta al sistema
	 * @param usuario	Nombre de usuario al que se vende
	 * @param venta	Venta realizada
	 * @param anyadeAlMapaDeUsuarios	true si se quiere actualizar el mapa interno de ventas por usuario
	 */
	public void addVenta( String usuario, Venta venta, boolean anyadeAlMapaDeUsuarios ) {
		if (anyadeAlMapaDeUsuarios) {
			if (!mapaVentas.containsKey( usuario )) {
				mapaVentas.put( usuario, new ArrayList<Venta>() );
			}
			mapaVentas.get( usuario ).add( venta );  // Añade la venta a la lista de ventas del usuario
		}
		for (Oferta o: venta.getlVenta()) {
			TreeSet<Oferta> ofertas = mapaProveedores.get(o.getProveedor());
			ofertas.add(o);
		}
		//System.out.println(mapaProveedores);
	}
	
	/** Devuelve información de ventas por país proveedor
	 * @return	Texto correspondiente a la información de ventas por proveedor
	 */
	public String getInfoT2() {
		String ret = "";
		for (Proveedor p: mapaProveedores.keySet()) {
			ret+=p.toString()+": \n";
			TreeSet<Oferta> t = mapaProveedores.get(p);
			if (t.isEmpty()) {
				ret+="No se han vendido ofertas de este proveedor \n";
			}
			else {
				for (Oferta o : t) {
					ret+=o.toString()+"\n";
				}
			}
		}
		return ret;
	}

	/** Devuelve el conjunto de usuarios de la tienda
	 * @return	Conjunto actual de usuarios
	 */
	public Set<String> getUsuarios() {
		return mapaVentas.keySet();
	}
	
	/** Devuelve la lista de ventas de un usuario
	 * @param usuario	Usuario a buscar
	 * @return	Lista de ventas a ese usuario
	 */
	public ArrayList<Venta> getListaVentas( String usuario ) {
		return mapaVentas.get( usuario );
	}

	/** Devuelve la lista completa de ventas de la tienda
	 * @return	Lista de ventas, ordenadas por usuario
	 */
	public ArrayList<Venta> getListaVentas() {
		ArrayList<Venta> ret = new ArrayList<>();
		for (ArrayList<Venta> l : mapaVentas.values()) {
			for (Venta venta : l) {
				ret.add( venta );
			}
		}
		return ret;
	}

	/** Devuelve un string con la información histórica de ventas
	 * @param ventana	Ventana en la que mostrar la información
	 */
	public String getInfoVentas() {
		String ret = "Resumen de ventas realizadas:\n";
		for (String usu : mapaVentas.keySet()) {
			ret += "  Usuario " + usu + ":\n";
			for (Venta venta : mapaVentas.get( usu )) {
				ret += "    " + venta.toString() + "\n";
			}
		}
		return ret;
	}
	
	/** Guarda los datos para poder restaurar posteriormente el sistema
	 * @param nomFichero	Nombre de fichero de guardado
	 */
	public void guardarDatos( String nomFichero ) {
		// Guardar datos a un fichero : lista y mapa
		try {
			ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( nomFichero ) );
			oos.writeObject( listaOfertas );
			oos.writeObject( mapaVentas );
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Restaura el sistema con los datos existentes en un fichero
	 * @param nomFichero	Nombre de fichero de carga
	 * @throws IOException	Error si hay cualquier problema de lectura en el fichero
	 * @throws ClassNotFoundException	Error si hay error de conversión de clases al leer del fichero
	 */
	@SuppressWarnings("unchecked")  // Esto suprime los warnings (lógicos si no comprobamos el tipo al hacer los casts - forzamos que el fichero tenga lo que esperamos que tenga)
	public void cargarDatos( String nomFichero ) throws IOException, ClassNotFoundException {
		// Cargar datos del fichero a las estructuras de memoria
		ObjectInputStream ois = new ObjectInputStream( new FileInputStream( nomFichero ) );
		listaOfertas = (ArrayList<Oferta>) ois.readObject();
		mapaVentas = (HashMap<String,ArrayList<Venta>>) ois.readObject();
		ois.close();
		// Añade otro posible comportamiento de las ventas, según las cargadas en el mapa de ventas
		for (String usuario : mapaVentas.keySet()) {
			ArrayList<Venta> l = mapaVentas.get(usuario);
			for (Venta venta : l) {
				addVenta( usuario, venta, false );
			}
		}
	}
	
}
