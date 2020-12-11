package examen202004int;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import examen202004int.datos.EstadoDeVida3;

/** Clase principal de lanzamiento del juego de la vida
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class MainJuego2 {

	public static HashMap<String,TreeSet<JuegoDeLaVida>>juegosJugados= new HashMap<>();
	public static final String FICHERO_JUEGO_GUARDADO = "juego2.dat";
	public static final String FICHERO_USUARIO_ANTERIOR = "usuarioAnterior2.dat";
	public static final File ficheroJuegoGuardado = new File(FICHERO_JUEGO_GUARDADO);
	public static final File ficheroUsuarioAnterior = new File(FICHERO_USUARIO_ANTERIOR);
	
	public static void main(String[] args) {
		int resp = 0;
		do {
			String usuario = JOptionPane.showInputDialog( null, "Nombre de usuario:", "default" );
			System.out.println( "Usuario identificado: " + usuario );
			
			JuegoDeLaVida juego = null;
			JuegoDeLaVida juegoAnterior = cargarJuego(usuario);
			
			if (juegoAnterior!=null) {
				juego=juegoAnterior;
				juego.reiniciaVentana();
			}
			else if (juegosJugados.containsKey(usuario)) {
					TreeSet<JuegoDeLaVida> lt = juegosJugados.get(usuario);
					juego = eligeJuego(lt);
			}
			else {
				TreeSet<JuegoDeLaVida> ar = new TreeSet<JuegoDeLaVida>();
				juegosJugados.put(usuario, ar);
				System.out.println(juegosJugados);
			}
			if (juego==null) {
				int numFilas = leeYValidaEntero( "Bienvenido al juego de la vida. Introduce número de filas (entre 10 y 100)", 10, 100, 10 );
				int numCols = leeYValidaEntero( "Introduce número de columnas (entre 10 y 100)", 10, 100, 10 );
				juego = new JuegoDeLaVida( numFilas, numCols, EstadoDeVida3.MUERTA );
			}
			
			juego.colocar();
			juego.jugar();
			if (juego.getMundo().getVentana().estaCerrada()) {
				guardarJuego(usuario,juego);
				break;
			}
			juego.getMundo().getVentana().acaba(); // Cierra la ventana
			juegosJugados.get(usuario).add(juego);
			System.out.println(juegosJugados);
			resp = JOptionPane.showConfirmDialog( null, "¿Quieres volver a jugar?", "Seguir jugando", JOptionPane.YES_NO_OPTION );
		} while (resp==JOptionPane.YES_OPTION);
	}
	
	/** Lee un entero por teclado (usando una ventana de popup) validando que sea correcto, pidiéndolo de nuevo si no lo es cuantas veces sea necesario
	 * @param mensaje	Mensaje a mostrar
	 * @param valMinimo	Valor mínimo válido (inclusive)
	 * @param valMaximo	Valor máximo válido (inclusive)
	 * @return	Valor leído del usuario (correcto)
	 */
	private static int leeYValidaEntero( String mensaje, int valMinimo, int valMaximo, int valPorDefecto ) {
		int num = Integer.MAX_VALUE;  // Valor de marca
		do {
			String resp = JOptionPane.showInputDialog( null, mensaje, ""+valPorDefecto );
			try {
				num = Integer.parseInt( resp );  // Si no es correcta la conversión, salta la excepción y num no cambia
			} catch (NumberFormatException e) { }
			if (num<valMinimo || num>valMaximo) num = Integer.MAX_VALUE;
		} while (num==Integer.MAX_VALUE);
		return num;
	}

	// Preparada para la tarea T1
	// Permite al usuario elegir interactivamente un juego de un conjunto de juegos ya existentes
	// Si el usuario no elige ninguno (o el conjunto esté vacío) devuelve null, si no devuelve el juego elegido
	private static JuegoDeLaVida eligeJuego( TreeSet<JuegoDeLaVida> setJuegos ) {
		if (setJuegos.isEmpty()) return null;
		JuegoDeLaVida[] vectorJuegos = new JuegoDeLaVida[ setJuegos.size() ];
		int posi = 0;
		for (JuegoDeLaVida juego : setJuegos) {
			vectorJuegos[posi] = juego;
			posi++;
		}
		JuegoDeLaVida resp = (JuegoDeLaVida) JOptionPane.showInputDialog( null, "Elige un juego de los ya existentes (<Esc> para juego nuevo):", "Selección de juego", JOptionPane.QUESTION_MESSAGE, null, vectorJuegos, null );
		return resp;
	}
	private static void guardarJuego(String usuario,JuegoDeLaVida juego) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_JUEGO_GUARDADO));
			oos.writeObject(juego);
			oos.close();
			System.out.println("Game saved successfully");
		} catch(IOException e) {
			System.err.println("Error in saving game");
			e.printStackTrace();
		}
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_USUARIO_ANTERIOR));
			oos.writeObject(usuario);
			oos.close();
			System.out.println("user name saved successfully");
		} catch(IOException e) {
			System.err.println("Error in saving user name");
			e.printStackTrace();
		}
	}
	private static JuegoDeLaVida cargarJuego(String usuario) {
		boolean usuarioEsElMismo=false;
		JuegoDeLaVida juegoreturn=null;
		if (ficheroUsuarioAnterior.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHERO_USUARIO_ANTERIOR));
				String usuarioF = (String) ois.readObject();
				ois.close();
				System.out.println("User Loaded Successfully");
				if (usuarioF.equals(usuario)) {
					usuarioEsElMismo=true;
					System.out.println("El usuario es el mismo que cerro antes");
				}
			}catch (IOException|ClassNotFoundException e) {
				System.err.println("Error in loading user name");
				e.printStackTrace();
			}
		}
		if (ficheroJuegoGuardado.exists() && usuarioEsElMismo) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHERO_JUEGO_GUARDADO));
				JuegoDeLaVida juego = (JuegoDeLaVida) ois.readObject();
				ois.close();
				juegoreturn=juego;
				System.out.println("Game Loaded Successfully");
			}catch (IOException|ClassNotFoundException e) {
				System.err.println("Error in loading saved game");
				e.printStackTrace();
			}
		}
		return juegoreturn;	
	}
}

