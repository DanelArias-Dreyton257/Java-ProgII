package examen202004int.datos;

import java.awt.Point;

import examen202004int.iu.VentanaGrafica;

/** Objeto gráfico general de juego, clase padre de la jerarquía de objetos con posición que se dibujan en una ventana
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public abstract class ObjetoJuego extends Object {
	
	protected int x;  // Coordenada x en ventana
	protected int y;  // Coordenada y en ventana
	protected transient VentanaGrafica ventana;  // Ventana donde se encuentra el objeto (transient para que si se quiere serializar, no se guarde la ventana)
	
	/** Crea un nuevo objeto
	 * @param x	Posición x (horizontal)
	 * @param y	Posición y (vertical)
	 * @param ventana	Ventana gráfica en la que está ese objeto
	 */
	public ObjetoJuego(int x, int y, VentanaGrafica ventana) {
		// super();  // Como si estuviera (Java lo añade si no llamamos a otro super)
		this.x = x;
		this.y = y;
		this.ventana = ventana;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	/** Devuelve el punto de referencia en pantalla del objeto
	 * @return	Punto de referencia (x,y) en pixels
	 */
	public Point getPunto() {
		return new Point( x, y );
	}

	public VentanaGrafica getVentana() {
		return ventana;
	}

	public void setVentana( VentanaGrafica ventana ) {
		this.ventana = ventana;
	}

	/** Dibuja el objeto en su ventana gráfica
	 */
	abstract public void dibujar();
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ObjetoJuego)) return false;
		ObjetoJuego o2 = (ObjetoJuego) obj;  // Miro este obj como lo que realmente es: un ObjetoJuego
		return x==o2.x && y==o2.y && ventana==o2.ventana;  // Dos objetos juego son iguales si tienen la misma x, y y ventana gráfica
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
		
}
