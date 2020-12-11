package examen202004int.datos;

import java.awt.Point;

/** Comportamiento de cualquier objeto en el que se pueda hacer click con el ratón
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public interface Clickable {
	/** Comprueba si el objeto contiene un punto de la ventana
	 * @param punto	Punto x,y a comprobar
	 * @return	true si esa posición está dentro del objeto, false en caso contrario
	 */
	public boolean contienePunto( Point punto );
	
	/** Comprueba si el objeto contiene un punto de la ventana
	 * @param x	Posición x del punto
	 * @param y	Posición y del punto
	 * @return	true si esa posición está dentro del objeto, false en caso contrario
	 */
	abstract public boolean contienePunto( int x, int y );

}
