package examen202004int.datos;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import examen202004int.iu.VentanaGrafica;

/** Clase de tablero (mundo) donde se sitúan las células en el juego de la vida
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class Mundo extends ObjetoJuego implements Serializable {

	private static final long serialVersionUID = 1L;
	// Constantes
	private static final Point INICIO_TABLERO = new Point( 10, 10 );  // Punto de de la ventana donde empieza el tablero (esquina superior izquierda)
	private static final Color COLOR_MALLA = Color.LIGHT_GRAY;        // Color de dibujado de la malla del tablero
	private static final float GROSOR_MALLA = 1f;                   // Grosor de dibujado de la malla
	
	private CelulaCuadrada[][] celulas;      // Tablero de células (cada una con su estado)
	private ArrayList<Celula> lista; // Las mismas células, pero en una lista lineal
	private int numFilas;            // Número de filas del juego
	private int numCols;             // Número de columnas del juego
	private int tamCasilla;          // Pixels de tamaño de la casilla (alto y ancho) 

	/** Crea un nuevo mundo para juego, inicialmente lleno de células muertas
	 * @param vent	Ventana gráfica en el que dibujarlo
	 * @param numFilas	Número de filas
	 * @param numCols	Número de columnas
	 * @param tamCasilla	Tamaño de la casilla (en píxels)
	 */
	public Mundo( VentanaGrafica vent, int numFilas, int numCols, int tamCasilla ) {
		this( vent, numFilas, numCols, tamCasilla, EstadoDeVida.MUERTA );
	}
	
	/** Crea un nuevo mundo para juego, lleno de células
	 * @param vent	Ventana gráfica en el que dibujarlo
	 * @param numFilas	Número de filas
	 * @param numCols	Número de columnas
	 * @param tamCasilla	Tamaño de la casilla (en píxels)
	 * @param Estado	estado inicial de todas las células
	 */
	public Mundo( VentanaGrafica vent, int numFilas, int numCols, int tamCasilla, Estado estado ) {
		super( INICIO_TABLERO.x, INICIO_TABLERO.y, vent );
		this.numFilas = numFilas;
		this.numCols = numCols;
		this.tamCasilla = tamCasilla;
		celulas = new CelulaCuadrada[numFilas][numCols];
		lista = new ArrayList<>();
		for (int fila=0; fila<numFilas; fila++) {
			for (int col=0; col<numCols; col++) {
				celulas[fila][col] = new CelulaCuadrada( this, fila, col ); // Célula al array
				lista.add( celulas[fila][col] );  // Se añade también a la lista
				celulas[fila][col].setEstado( estado );  // Se pone el estado indicado por defecto
			}
		}
	}
	
	/** Devuelve una célula del mundo
	 * @param fila	Fila de la célula (0 a numFilas - 1)
	 * @param col	Columna de la célula (0 a numCols - 1)
	 */
	public Celula getCelula( int fila, int col ) {
		return celulas[fila][col];
	}
	
	/** Devuelve todas las células del mundo
	 * @return	Lista de células
	 */
	public ArrayList<Celula> getCelulas() {
		return lista;
	}
	
	/** Devuelve el tamaño de cada casilla (tamaño = ancho = alto)
	 * @return	Tamaño en píxels
	 */
	public int getTamCasilla() {
		return tamCasilla;
	}

	/** Devuelve el número de filas del mundo
	 * @return	número de filas
	 */
	public int getNumFilas() {
		return numFilas;
	}

	/** Devuelve el número de columnas del mundo
	 * @return	número de columnas
	 */
	public int getNumCols() {
		return numCols;
	}

	@Override
	public void dibujar() {
		// 1.- Dibujar malla
		for (int fila=0; fila<=numFilas; fila++) {  // Líneas horizontales
			ventana.dibujaLinea( INICIO_TABLERO.x, INICIO_TABLERO.y + fila*tamCasilla, INICIO_TABLERO.x+(numCols*tamCasilla), INICIO_TABLERO.y + fila*tamCasilla, GROSOR_MALLA, COLOR_MALLA );
		}
		for (int col=0; col<=numCols; col++) {  // Líneas verticales
			ventana.dibujaLinea( INICIO_TABLERO.x + col*tamCasilla, INICIO_TABLERO.y, INICIO_TABLERO.x + col*tamCasilla, INICIO_TABLERO.y + (numCols*tamCasilla), GROSOR_MALLA, COLOR_MALLA );
		}
		// 2.- Dibujar células
		for (int fila=0; fila<numFilas; fila++) {
			for (int col=0; col<numCols; col++) {
				celulas[fila][col].dibujar();
			}
		}
	}	
	
	/** Devuelve la coordenada de esquina de una posición del mundo
	 * @param fila	Fila de la posición deseada
	 * @param columna	Columna de la posición deseada
	 * @return	Punto de esquina superior izquierda de esa casilla en la ventana
	 */
	public Point getPunto( int fila, int columna ) {
		int x = INICIO_TABLERO.x + columna*tamCasilla;
		int y = INICIO_TABLERO.y + fila*tamCasilla;
		return new Point( x, y );
	}

	@Override
	public void setVentana( VentanaGrafica ventana ) {
		super.setVentana( ventana );  // Actualiza la ventana del mundo
		for (Celula celula: lista) {  // Actualiza la ventana de las células
			celula.setVentana( ventana );
		}
	}

}
