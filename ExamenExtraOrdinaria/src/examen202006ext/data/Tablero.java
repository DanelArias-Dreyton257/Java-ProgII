package examen202006ext.data;

import java.awt.*;
import java.util.ArrayList;
import examen202006ext.gui.PanelGrafico;

/** Componente de tablero del juego Datausto, con un panel gráfico asociado
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class Tablero {
	private static int ESPACIO_HORIZONTAL = 10;       // Píxels de espacio por izquierda y derecha
	private static int ESPACIO_VERTICAL = 10;         // Píxels de espacio por arriba y abajo
	private static Color COLOR_FONDO = Color.GRAY;   // Color de fondo de los hexágonos del tablero
	private static Color COLOR_BORDE = Color.LIGHT_GRAY; // Color de borde de los hexágonos del tablero
	private static Color COLOR_TRANSP = new Color( 0, 0, 0, 0 );  // Color transparente

	private int altoCasilla;     // Píxels enteros de alto de cada casilla (redondeado superior)
	private int altoFila;        // Píxels enteros de distancia entre cada fila y la siguiente (redondeado superior)
	private int anchoCasilla;    // Píxels enteros de ancho de cada casilla (redondeado superior)
	private int numFilas;        // Número de filas del tablero
	private int numColumnas;     // Número de columnas del tablero
	private PanelGrafico panel;  // Panel gráfico asociado al tablero
	private ArrayList<Hexagono> listaHexsFondo;  // Lista de hexágonos de fondo tablero
	private ArrayList<EnTablero> listaElementos; // Lista de elementos colocados en el tablero
	private EnTablero[][] elementosTablero;      // Elementos del tablero organizados en filas-columnas
	
	/** Construye un nuevo tablero de juego
	 * @param radioCasilla	Píxels de radio de cada casilla hexagonal
	 * @param filas	Número de filas del tablero
	 * @param columnas	Número de columnas del tablero
	 */
	public Tablero( int radioCasilla, int filas, int columnas ) {
		panel = new PanelGrafico();
		altoCasilla = radioCasilla * 2;
		altoFila = altoCasilla - radioCasilla/2;
		anchoCasilla = (int) Math.ceil( Math.sqrt(3.0) * radioCasilla / 2.0 ) * 2;
		reiniciaTablero( filas, columnas );
	}
	
	/** Construye un nuevo tablero de juego, llenando al máximo posible el panel disponible
	 * @param radioCasilla	Píxels de radio de cada casilla hexagonal
	 * @param tamanyoPanel	Tamaño disponible gráfico en pixels
	 */
	public Tablero( int radioCasilla, Dimension tamanyoPanel ) {
		panel = new PanelGrafico();
		altoCasilla = radioCasilla * 2;
		altoFila = altoCasilla - radioCasilla/2;
		anchoCasilla = (int) Math.ceil( Math.sqrt(3.0) * radioCasilla / 2.0 ) * 2;
		reiniciaTablero( tamanyoPanel );
	}
	
	/** Reinicia el tablero con un nuevo tamaño, borrando todas las piezas
	 * @param filas	Número de filas
	 * @param columnas	Número de columnas
	 */
	public void reiniciaTablero( int filas, int columnas ) {
		numFilas = filas;
		numColumnas = columnas;
		init();
	}
	
	/** Reinicia el tablero con un nuevo tamaño, borrando todas las piezas
	 * @param tamanyoPanel	Tamaño al que adaptar el tablero (cogerá el máximo de filas y columnas posible)
	 */
	public void reiniciaTablero( Dimension tamanyoPanel ) {
		int tamAnchoDisp = tamanyoPanel.width - 2 * ESPACIO_HORIZONTAL;
		int tamAltoDisp = tamanyoPanel.height - 2 * ESPACIO_VERTICAL;
		numFilas = tamAltoDisp / altoFila;
		numColumnas = (tamAnchoDisp - ((anchoCasilla+1)/2)) / anchoCasilla;
		init();
	}
	
		// Inicializador común a todos los constructores
		@SuppressWarnings("serial")
		private void init() {
			altoFila = altoCasilla - altoCasilla/4;
			listaHexsFondo = new ArrayList<>();
			listaElementos = new ArrayList<>();
			calculaTablero();
			elementosTablero = new EnTablero[numFilas][numColumnas];
		}

		// Calcula los hexágonos de base del tablero
		private void calculaTablero() {
			for (int f=0; f<numFilas; f++) {
				for (int c=0; c<numColumnas; c++) {
					Point centroCasilla = getCentroDeCasilla( f, c );
					Hexagono hex = new Hexagono( panel, centroCasilla.x, centroCasilla.y, altoCasilla/2 );
					hex.setColor( COLOR_BORDE );
					hex.setColorRelleno( COLOR_FONDO );
					listaHexsFondo.add( hex );
				}
			}
		}

	/** Devuelve la lista de hexágonos de fondo del tablero
	 * @return	Lista de hexágonos de fondo de tablero
	 */
	public ArrayList<Hexagono> getListaHexagonos() {
		return listaHexsFondo;
	}

	/** Devuelve la lista de elementos del tablero. No modificar esta lista directamente 
	 * @return	Lista de elementos en el tablero
	 */
	public ArrayList<EnTablero> getListaElementos() {
		return listaElementos;
	}

	/** Devuelve los elementos del tablero organizados por posición. No modificar este array directamente 
	 * @return	Array de elementos en el tablero indexado por fila,columna
	 */
	public EnTablero[][] getElementos() {
		return elementosTablero;
	}
	
	/** Intenta añadir un elemento al tablero
	 * @param elemento	Elemento a añadir al tablero
	 * @return	true si se ha podido añadir (las casillas estaban libres), false en caso contrario
	 */
	public boolean addElemento( EnTablero elemento ) {
		elemento.setTablero( this );  // Modifica el tablero del elemento añadido
		if (elemento.getCasillas()==null) return false;
		for (Casilla casilla : elemento.getCasillas()) {
			if (!enTablero(casilla)) return false;  // Si está fuera del tablero, no se puede añadir
			if (elementosTablero[casilla.getFila()][casilla.getColumna()]!=null) return false; // Si está ocupada, no se puede añadir
		}
		listaElementos.add( elemento );
		for (Casilla casilla : elemento.getCasillas()) {
			elementosTablero[casilla.getFila()][casilla.getColumna()] = elemento;
		}
		return true;
	}
	
	/** Quita un elemento del tablero, liberando todas las casillas que ocupara
	 * @param elemento	Elemento a eliminar (si no existe no ocurre nada)
	 * @return	true si el elemento se ha eliminado, false si no existía
	 */
	public boolean removeElemento( EnTablero elemento ) {
		boolean ret = listaElementos.remove( elemento );
		for (int f=0; f<numFilas; f++) {
			for (int c=0; c<numColumnas; c++) {
				if (elementosTablero[f][c]!=null && elementosTablero[f][c].equals(elemento)) 
					elementosTablero[f][c] = null;
			}
		}
		elemento.setTablero( null );  // Anula el tablero del elemento quitado
		return ret;
	}
	
	/** Quita todos los elementos del tablero, liberando todas las casillas
	 */
	public void clearAll() {
		for (EnTablero et : listaElementos) et.setTablero( null );  // Anula tablero de todos los elementos previos
		listaElementos.clear();
		for (int f=0; f<numFilas; f++) {
			for (int c=0; c<numColumnas; c++) {
				elementosTablero[f][c] = null;
			}
		}
	}
	
	/** Intenta modificar un elemento del tablero
	 * @param elemento	Elemento a modificar en el tablero
	 * @return	true si se ha podido añadir (las casillas estaban libres), false en caso contrario (y en ese caso queda fuera del tablero)
	 */
	public boolean modificaElemento( EnTablero elemento ) {
		removeElemento( elemento );
		return addElemento( elemento );
	}
	
	/** Informa de la corrección de una casilla
	 * @param casilla	Casilla a comprobar
	 * @return	true si la casilla es válida para este tablero, false en caso contrario
	 */
	public boolean enTablero( Casilla casilla ) {
		return casilla.getFila()>=0 && casilla.getFila()<numFilas && casilla.getColumna()>=0 && casilla.getColumna()<numColumnas;
	}
		
	/** Devuelve el alto de cada casilla
	 * @return	Alto en píxels
	 */
	public int getAltoCasilla() {
		return altoCasilla;
	}

	/** Devuelve el ancho de cada casilla
	 * @return	Ancho en píxels
	 */
	public int getAnchoCasilla() {
		return anchoCasilla;
	}

	/** Devuelve el número de filas del tablero
	 * @return	Número de filas
	 */
	public int getNumFilas() {
		return numFilas;
	}

	/** Devuelve el número de columnas del tablero
	 * @return	Número de columnas
	 */
	public int getNumColumnas() {
		return numColumnas;
	}

	/** Devuelve el panel gráfico asociado al tablero
	 * @return	Panel gráfico
	 */
	public PanelGrafico getPanel() {
		return panel;
	}
	
	/** Indica si un punto concreto está o no dentro del tablero
	 * @param punto	Punto en coordenadas de píxel del panel de tablero
	 * @return	true si el punto está dentro del tablero, false si está fuera
	 */
	public boolean estaEnTablero( Point punto ) {
		int fila = getFilaDe( punto );
		int col = getColumnaDe( punto );
		return fila>=0 && fila<numFilas && col>=0 && col<numColumnas;
	}
	
	/** Devuelve la fila correspondiente a un punto concreto del tablero
	 * @param punto	Punto en coordenadas de píxel del panel de tablero
	 * @return	Número de fila a la que corresponde (-1 si está por encima de la primera fila, {@link #getNumFilas()} si está por debajo de la última fila)
	 */
	public int getFilaDe( Point punto ) {
		int fila = (punto.y - ESPACIO_VERTICAL) / altoFila;
		if (fila<0) fila = -1; else if (fila>numFilas) fila = numFilas;
		return fila;
	}
	
	/** Devuelve la columna correspondiente a un punto concreto del tablero
	 * @param punto	Punto en coordenadas de píxel del panel de tablero
	 * @return	Número de columna a la que corresponde (-1 si está a la izquierda de la primera columna, {@link #getNumColumnas()} si está a la derecha de la última columna)
	 */
	public int getColumnaDe( Point punto ) {
		int x = punto.x - ESPACIO_HORIZONTAL;
		int fila = getFilaDe( punto );
		if (fila%2==1) x -= anchoCasilla / 2;  // Si la fila es impar empieza medio hexágono más a la derecha
		int col = x / anchoCasilla;
		if (col<0) col = -1; else if (col>numColumnas) fila = numColumnas;
		return col;
	}
	
	/** Devuelve la casilla correspondiente a un punto concreto del tablero
	 * @param punto	Punto en coordenadas de píxel del panel de tablero
	 * @return	Casilla correcta a la que corresponde, null si está fuera del tablero
	 */
	public Casilla getCasillaDe( Point punto ) {
		int fila = getFilaDe( punto );
		int col = getColumnaDe( punto );
		if (fila<0 || col<0 || fila>=numFilas || col>=numColumnas) return null;
		return new Casilla( fila, col );
	}
	
	/** Devuelve la casilla correspondiente a un punto concreto del tablero (aunque el punto no esté en el tablero)
	 * @param punto	Punto en coordenadas de píxel del panel de tablero
	 * @return	Casilla correcta a la que corresponde, aunque esté fuera del tablero
	 */
	public Casilla getCasillaVirtualDe( Point punto ) {
		int fila = getFilaDe( punto );
		int col = getColumnaDe( punto );
		return new Casilla( fila, col );
	}
	
	/** Informa si una lista de casillas están disponibles en el tablero
	 * @param lCasillas	Lista de casillas a comprobar
	 * @return	true si todas están libres, false en caso contrario
	 */
	public boolean estanCasillasLibres( ArrayList<Casilla> lCasillas ) {
		if (lCasillas==null) return false;
		for (Casilla c : lCasillas) {
			if (elementosTablero[c.getFila()][c.getColumna()]!=null) return false;
		}
		return true;
	}
	
	/** Devuelve la coordenada central de panel para una casilla concreta
	 * @param fila	Número de fila de la casilla
	 * @param columna	Número de columna de la casilla
	 * @return	Coordenada central de esa casilla en píxels
	 */
	public Point getCentroDeCasilla( int fila, int columna ) {
		int x = ESPACIO_HORIZONTAL + anchoCasilla/2 + (anchoCasilla * columna);
		if (fila%2==1) x += anchoCasilla/2;  // Si la fila es impar empieza medio hexágono más a la derecha
		int y = ESPACIO_VERTICAL + altoCasilla/2 + (altoFila * fila);
		return new Point(x, y);
	}

	/** Devuelve la coordenada central de panel para una casilla concreta
	 * @param casilla	Casilla a comprobar
	 * @return	Coordenada central de esa casilla en píxels, null si la casilla es incorrecta
	 */
	public Point getCentroDeCasilla( Casilla casilla ) {
		if (casilla==null) return null;
		else return getCentroDeCasilla( casilla.getFila(), casilla.getColumna() );
	}

	/** Devuelve la distancia lineal entre las casillas indicadas en progresión creciente
	 * @param desde	Casilla inicial
	 * @param hasta	Casilla final
	 * @return	Devuelve la distancia entre las dos casillas. Por ejemplo (2,3) y (2,4) tienen distancia 1 y (2,3) y (2,5) distancia 2.  
	 * Si hay salto de línea se consideran todas las casillas de izquierda a derecha y de arriba abajo.
	 * Si la casilla final es anterior a la inicial, se devuelve -1.
	 */
	public int getDistanciaLineal( Casilla desde, Casilla hasta ) {
		if (hasta.getFila()<desde.getFila()) return -1;
		if (hasta.getFila()==desde.getFila() && hasta.getColumna()<hasta.getColumna()) return -1;
		int fila = desde.getFila();
		int col = desde.getColumna();
		int distancia = 0;
		while (fila<hasta.getFila()) {
			distancia += (numColumnas - col);
			fila++;
			col = 0;
		}
		distancia += (hasta.getColumna() - col);
		return distancia;
	}
	
	/** Dibuja el tablero completo en su panel, tanto fondo de hexágonos como elementos contenidos
	 */
	public void dibuja() {
		panel.borra(); 
		// Pinta el tablero
		for (Hexagono h : getListaHexagonos()) {
			h.dibuja();
		}
		for (EnTablero e : getListaElementos()) {
			e.dibuja();
		}
	}

	@Override
	public String toString() {
		String ret = "Tablero " + numFilas + "x" + numColumnas + " -> " + listaElementos + "\n";
			for (int f=0; f<numFilas; f++) {
				for (int c=0; c<numColumnas; c++) {
					ret += elementosTablero[f][c]==null ? "-------- " : (elementosTablero[f][c].toString()+"            ").substring(0, 8) + " ";
				}
				ret += "\n";
			}
		return ret;
	}
	
}
