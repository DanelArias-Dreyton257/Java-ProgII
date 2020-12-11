package examen202006ext.data;

import java.awt.Color;

/** Interfaz para objetos gráficos coloreables
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public interface Coloreable {
	/** Modifica el color del objeto
	 * @param color	Color a modificar
	 */
	public void setColor( Color color );
	/** Modifica el color de relleno del objeto
	 * @param colorRelleno	Color a modificar (null para transparente)
	 */
	public void setColorRelleno( Color colorRelleno );
	/** Devuelve el color del objeto
	 * @return	Color del objeto
	 */
	public Color getColor();
	/** Devuelve el color de relleno del objeto
	 * @return	Color de relleno del objeto (null para transparente)
	 */
	public Color getColorRelleno();
}
