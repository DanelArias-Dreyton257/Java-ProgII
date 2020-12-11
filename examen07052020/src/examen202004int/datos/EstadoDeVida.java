package examen202004int.datos;

import java.awt.Color;

/** Estado de vida (viva o muerta) para células del juego de la vida
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public enum EstadoDeVida implements Estado {
	VIVA, MUERTA;
	
	// Devuelve negro para estado viva, null para muerta
	@Override
	public Color getColor() {
		return new Color[] { Color.BLACK, null }[ordinal()]; // Devuelve el valor usando una tabla de valores y el índice del valor enum (0 = VIVA o 1 = MUERTA)
		// if (ordinal()==0) return Color.BLACK; else return null;  // Otra forma de hacerlo
		// if (this==VIVA) return Color.BLACK; else return null;  // Otra forma de hacerlo
	}
	
	@Override
	public Estado siguienteEstado() {
		if (this==MUERTA) return VIVA; else return MUERTA;
	}
	
	@Override
	public Estado getEvolucion(Mundo mundo, int fila, int columna) {
		// Recorremos las 9 células del entorno de la actual para ver cuántas están vivas  (matriz de 3x3)
		int numVivas = 0;
		int contFilas=fila-1;
		for (int f = fila-1; contFilas<=fila+1; f++) {
			int contCols=columna-1;
			for (int c = columna-1; contCols<=columna+1; c++) {
				if (f==-1) f=mundo.getNumFilas()-1;
				if (c==-1) c=mundo.getNumCols()-1;
				if (f==mundo.getNumFilas()) f=0;
				if (c==mundo.getNumCols()) c=0;
				if (f>=0 && f<mundo.getNumFilas() && c>=0 && c<mundo.getNumCols() && (f!=fila || c!=columna)) {  // Si no está fuera de la matriz del mundo y no es la misma célula
					if (mundo.getCelula(f, c).getEstado()==EstadoDeVida3.VIVA) numVivas++;
				}
				contCols++;
			}
			contFilas++;
		}
		// Aplicamos las reglas del juego
		if (mundo.getCelula(fila, columna).getEstado() == EstadoDeVida.MUERTA && numVivas==3) {  // Una célula muerta con 3 células vecinas vivas, volverá a la vida
			return EstadoDeVida.VIVA;
		} else if (mundo.getCelula(fila, columna).getEstado() == EstadoDeVida.VIVA && (numVivas==2 || numVivas==3)) {  // Una célula viva con 2 o 3 células vecinas vivas, seguirá viva
			return EstadoDeVida.VIVA;
		} else { // En cualquier otro caso la celula morirá, por soledad o superpoblación
			return EstadoDeVida.MUERTA;
		}
	}
}
