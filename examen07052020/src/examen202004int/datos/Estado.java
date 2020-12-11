package examen202004int.datos;

import java.awt.Color;

/** Interfaz de comportamiento de estado de un objeto que admite cambios de estado
 * (Ejemplos: vivo/muerto, parado/andando/corriendo...)
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public interface Estado {
	/** Devuelve la posición (índice) de 0 a n-1 del estado, dentro de su lista de estados posibles
	 * @return	índice de estado
	 */
	public int ordinal();
	/** Devuelve el color que se quiere asociar al estado en la representación visual que se haga por defecto
	 * @return	Color del estado, null si lo que corresponde es no visualizarlo
	 */
	public Color getColor();
	/** Devuelve el estado siguiente
	 * @return	Estado siguiente (si es el último, se devuelve el inicial)
	 */
	public Estado siguienteEstado();
	
	public Estado getEvolucion( Mundo mundo, int fila, int columna );
}