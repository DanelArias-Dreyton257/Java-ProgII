package examen202004int.datos;

import java.awt.Point;
import java.io.Serializable;

/** Clase para crear células del juego de la vida
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class Celula extends ObjetoJuego implements Clickable,Serializable {
	
	private static final long serialVersionUID = 1L;
	protected Mundo mundo;   // Mundo de la célula
	protected Estado estado; // Estado de la célula
	protected int fila;      // Fila de la célula en el mundo
	protected int columna;   // Columna de la célula en el mundo
	
	/** Crea una nueva célula
	 * @param mundo	Mundo en el que se encuentra
	 * @param fila	Fila de su posición en el mundo (0 a numFilas-1)
	 * @param columna	Columna de su posición en el mundo (0 a numCols-1)
	 */
	public Celula( Mundo mundo, int fila, int columna ) {
		super( mundo.getPunto(fila, columna).x, mundo.getPunto(fila, columna).y, mundo.getVentana() );
		this.mundo = mundo;
		this.fila = fila;
		this.columna = columna;
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Mundo getMundo() {
		return mundo;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	@Override
	public void dibujar() {
		if (estado.getColor()!=null) {  // Si el color es nulo no se dibuja
			double mitad = mundo.getTamCasilla()/2.0;
			mundo.getVentana().dibujaCirculo( x + mitad, y + mitad, mitad, 0f, estado.getColor(), estado.getColor() );
		}
	}

	@Override
	public boolean contienePunto(int x, int y) {
		return x>=this.x && x<=(this.x+mundo.getTamCasilla()) && y>=this.y && y<=(this.y+mundo.getTamCasilla());
		// Si se quisiera calcular como un círculo en vez de como un rectángulo:
		// double mitad = mundo.getTamCasilla()/2.0;
		// double dist = Math.sqrt( (x-this.x-mitad)*(x-this.x-mitad) + (y-this.y-mitad)*(y-this.y-mitad) );
		// return dist<=mitad;
	}

	@Override
	public boolean contienePunto(Point punto) {
		return contienePunto(punto.x, punto.y);
	}
	
	@Override
	public String toString() {
		return super.toString() + " [" + fila + "," + columna + "]";
	}

	/** Calcula la evolución de la célula en función de la distribución del mundo actual, según las leyes del juego de la vida de Conway: <br>
	 * - Una célula muerta con 3 células vecinas vivas, volverá a la vida <br>
	 * - Una célula viva con 2 o 3 células vecinas vivas, seguirá viva <br>
	 * - En cualquier otro caso la celula morirá, por soledad o superpoblación <br>
	 * @return nuevo estado para esta célula (no cambia el estado, simplemente se devuelve cuál será su nuevo estado tras la evolución)
	 */
	public Estado getEvolucion() {
		return this.estado.getEvolucion(mundo, fila, columna);
		
	}

}
