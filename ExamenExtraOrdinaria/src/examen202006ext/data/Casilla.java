package examen202006ext.data;

/** Clase para posición de casilla dentro del tablero
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class Casilla {
	private int fila;
	private int columna;
	
	/** Crea una nueva casilla
	 * @param fila	Valor de fila (de 0 a n-1)
	 * @param columna	Valor de columna (de 0 a n-1)
	 */
	public Casilla(int fila, int columna) {
		super();
		this.fila = fila;
		this.columna = columna;
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
	public String toString() {
		return "{" + fila + "," + columna + "}";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Casilla) {
			Casilla c2 = (Casilla) obj;
			return fila==c2.fila && columna==c2.columna;
		}
		return false;
	}
}
