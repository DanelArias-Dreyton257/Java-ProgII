package examen202004int;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import examen202004int.datos.Boton;
import examen202004int.datos.Celula;
import examen202004int.datos.Estado;
import examen202004int.datos.Mundo;
import examen202004int.datos.ObjetoJuego;
import examen202004int.datos.Texto;
import examen202004int.iu.VentanaGrafica;

public class JuegoDeLaVida implements Comparable<JuegoDeLaVida>,Serializable{
	private static final long serialVersionUID = 1L;
	// Constantes
	private static final Rectangle TAMANYO_VENTANA = new Rectangle( 1000, 810 );
	private static final int ANCHO_BOTONES = 100;  // Píxels de columna izquierda donde va la botonera
	private static final int MARGEN_BOTONES = 8;   // Margen en píxels de los botones a la derecha del tablero
	private static final int MARGEN_INFERIOR = 40; // Píxels de margen inferior de tablero en la ventana gráfica (donde va el texto)
	private static final long TIEMPO_CICLO = 20;   // Milisegundos por ciclo de dibujado
	
	private static int CICLOS_ANIMACION = 50; // Número de ciclos que tarda en evolucionar el sistema (multiplicado por el tiempo de ciclo, es el tiempo de evolución)
	
	private Mundo mundo;                    // Tablero de células (cada una con su estado)
	private ArrayList<ObjetoJuego> objetos; // Objetos adicionales del juego (botones, texto)
	private Texto textoMensaje;             // Texto de mensaje de juego
	private transient VentanaGrafica vent;  // Ventana donde se muestra el juego  (transient para que si se quiere serializar, no se guarde la ventana)
	private Date fecha;                     // Fecha en la que se ha editado el mundo (tablero) del juego
	
	/** Crea un nuevo juego de la vida
	 * @param numFilas	Número de filas
	 * @param numCols	Número de columnas
	 */
	public JuegoDeLaVida( int numFilas, int numCols, Estado estadoInicial ) {
		reiniciaVentana();
		int anchoMax = (vent.getAnchura() - ANCHO_BOTONES) / numCols;  // Tamaño máximo en píxels de cada casilla con el tamaño disponible
		int altoMax = (vent.getAltura() - MARGEN_INFERIOR) / numFilas;  // Tamaño máximo en píxels de cada casilla con el tamaño disponible (quitamos un pixel para que quepa la última línea)
		mundo = new Mundo( vent, numFilas, numCols, Math.min(anchoMax, altoMax), estadoInicial );  // Las casillas son cuadradas - se usa el mínimo de anchura y altura posible
		textoMensaje = new Texto( 0, vent.getAltura() - MARGEN_INFERIOR + 10, vent, vent.getAnchura(), MARGEN_INFERIOR, "" );
		objetos = new ArrayList<>();
		objetos.add( textoMensaje );
		objetos.add( new Boton( vent.getAnchura()-ANCHO_BOTONES + MARGEN_BOTONES, 10, vent, "Play", false ) );   // Desactivado por defecto
		objetos.add( new Boton( vent.getAnchura()-ANCHO_BOTONES + MARGEN_BOTONES, 60, vent, "Pausa", false ) );  // Desacivado por defecto
		objetos.add( new Boton( vent.getAnchura()-ANCHO_BOTONES + MARGEN_BOTONES, 110, vent, "Acabar" ) );
	}

	/** Devuelve el mundo del juego
	 * @return	mundo del juego
	 */
	public Mundo getMundo() {
		return mundo;
	}
	
	/** Reinicia la ventana gráfica de este juego
	 */
	public void reiniciaVentana() {
		vent = new VentanaGrafica( TAMANYO_VENTANA.width, TAMANYO_VENTANA.height, "Juego de la vida" );
		vent.setDibujadoInmediato( false );
		if (mundo!=null) mundo.setVentana( vent );  // Actualiza la ventana del mundo
		if (objetos!=null) for (ObjetoJuego objeto : objetos) objeto.setVentana( vent );  // Actualiza la ventana de los objetos
	}
	
	/** Devuelve la fecha/hora en la que el mundo del tablero se ha editado por última vez
	 * @return	Fecha de edición
	 */
	public Date getFecha() {
		return fecha;
	}

	public void colocar() {
		// Desactivar todos los botones excepto el de "Acabar"
		for (ObjetoJuego obj : objetos) {
			if (obj instanceof Boton) {
				if (!((Boton)obj).getTexto().equals("Acabar")) {
					((Boton)obj).setActivado( false );
				}
			}
		}
		// Colocar interactivamente
		textoMensaje.setTexto( "Fase de creación. Click/drag en casillas para cambiar el estado viva/muerta de sus células. Botón para acabar." );
		dibujarTodo();
		boolean colocando = true;
		Celula ultimaCelula = null;
		while (colocando && !vent.estaCerrada()) {  // Bucle de interacción y dibujado
			Point puls = vent.getRatonPulsado();
			if (puls!=null) {
				// Ver si es botón
				for (ObjetoJuego obj : objetos) {
					if (obj instanceof Boton) {  // Si es interactivo (botón)
						Boton boton = (Boton) obj;
						if (boton.contienePunto(puls) && boton.getTexto().equals("Acabar")) {  // Pulsado el botón de acabar
							colocando = false; // Sale del bucle de edición
							while (vent.getRatonPulsado()!=null) { vent.espera( TIEMPO_CICLO ); }  // Espera a que se suelte el botón
							break;  
						}
					}
				}
				// Ver si es célula
				for (Celula celula : mundo.getCelulas()) {
					if (celula.contienePunto( puls )) {  // Esta es la célula pulsada
						if (celula!=ultimaCelula) {  // Para no pulsar dos veces la misma célula con el mismo evento de ratón
							celula.setEstado( celula.getEstado().siguienteEstado() );  // Cambia el estado de la célula
							ultimaCelula = celula;
						}
						break; // Solo se puede pulsar una, así que no seguimos repitiendo
					}
				}
				dibujarTodo();
			} else {
				ultimaCelula = null;  // Se pierde la memorización de la célula si se suelta el ratón (para poder hacer cambios de la misma célula con clicks sucesivos)
			}
			vent.espera( TIEMPO_CICLO );  // Ciclo de espera entre iteraciones de dibujado / gestión interacción
		}
		fecha = new Date(); // Guarda la fecha de edición
	}
	
	public void jugar() {
		if (vent.estaCerrada()) return;  // Si la ventana ya se ha cerrado en la colocación, no se puede jugar
		textoMensaje.setTexto( "Fase de juego. Usa los botones para activar el juego de la vida." );
		// Activar todos los botones
		for (ObjetoJuego obj : objetos) {
			if (obj instanceof Boton) {
				((Boton)obj).setActivado( true );
			}
		}
		// Jugar
		int numGen = 0;
		boolean finJuego = false;
		boolean juegoActivo = false;  // Empieza en pausa
		int numCiclos = 0;  // Contador de ciclos para evolucionar el juego
		Point antPuls = null;  // Pulsación de ratón
		while (!finJuego && !vent.estaCerrada()) {
			// Comprueba interacción (busca click)
			Point nuevaPuls = vent.getRatonPulsado();
			if (antPuls==null && nuevaPuls!=null) {  // Inicia un posible click
				antPuls = nuevaPuls;
			} else if (antPuls!=null && nuevaPuls==null) {  // Acaba un click (no comprobamos que la coordenada sea lo mismo, podría hacerse pero solo es para los botones)
				for (ObjetoJuego obj : objetos) {
					if (obj instanceof Boton) {  // Si es interactivo (botón)
						Boton boton = (Boton) obj;
						if (boton.contienePunto(antPuls) && boton.getTexto().equals("Acabar")) {  // Pulsado el botón de acabar
							finJuego = true; // Sale del juego
							break;  
						} else if (boton.contienePunto(antPuls) && boton.getTexto().equals("Play")) {  // Pulsado el botón de play
							juegoActivo = true;
							textoMensaje.setTexto( "Vida fluyendo a 1 evolución cada " + TIEMPO_CICLO*CICLOS_ANIMACION + " milisegundos..." );
						} else if (boton.contienePunto(antPuls) && boton.getTexto().equals("Pausa")) {  // Pulsado el botón de pausa
							juegoActivo = false;
							textoMensaje.setTexto( "Vida en pausa." );
						}
					}
				}
				antPuls = null;
			}
			// Lógica de juego (animación de evolución)
			if (juegoActivo) {
				numCiclos--;
				if (numCiclos<0) {
					numCiclos = CICLOS_ANIMACION;  // Prepara la siguiente animación
					numGen++;
					evolucionar();  // Evoluciona el mundo
					textoMensaje.setTexto( "Vida fluyendo a 1 evolución cada " + TIEMPO_CICLO*CICLOS_ANIMACION + " milisegundos... Generación " + numGen );
				}
			}
			dibujarTodo();
			vent.espera( TIEMPO_CICLO );  // Ciclo de espera entre iteraciones de dibujado / gestión interacción
		}
	}

	// Calcula una evolución del mundo
	private void evolucionar() {
		// 1.- Calcula los estados de evolución de todas las células
		Estado[][] nuevoEstado = new Estado[mundo.getNumFilas()][mundo.getNumCols()];
		for (Celula celula : mundo.getCelulas()) {
			Estado evolucion = celula.getEvolucion();
			nuevoEstado[celula.getFila()][celula.getColumna()] = evolucion;
		}
		// 2.- Pone a cada célula su nuevo estado
		for (int fila=0; fila<mundo.getNumFilas(); fila++) {
			for (int col=0; col<mundo.getNumCols(); col++) {
				mundo.getCelula(fila, col).setEstado( nuevoEstado[fila][col] );
			}
		}
	}
	
	private void dibujarTodo() {
		vent.borra();
		mundo.dibujar();
		for (ObjetoJuego obj : objetos) {
			obj.dibujar();
		}
		vent.repaint();
	}
	
	private SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
	@Override
	public String toString() {
		return "Juego " + sdf.format( fecha );
	}
	
	@Override
	public int compareTo(JuegoDeLaVida o) {
		return this.fecha.hashCode()-o.fecha.hashCode();
	}
	
}
