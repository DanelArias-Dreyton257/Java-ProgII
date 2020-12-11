package examen202006ext.data;

import java.util.ArrayList;

/** Interfaz para elementos visuales colocables en tablero
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public interface EnTablero {
	/** Devuelve el tablero en el que está el elemento
	 * @return	Tablero contenedor
	 */
	public Tablero getTablero();
	/** Modifica el tablero en el que está el elemento
	 * @param tablero	Tablero a asignar
	 */
	public void setTablero( Tablero tablero );
	/** Devuelve la lista de casillas que ocupa el elemento
	 * @param tablero	Tablero en el que se encuentra el elemento
	 * @return	Lista de casillas del elemento, null si no está en ningún tablero o no ocupa ninguna casilla válida
	 */
	public ArrayList<Casilla> getCasillas();
	/** Devuelve el número de casillas que ocupa el elemento
	 * @return	Número de casillas que ocupa
	 */
	public int getNumCasillas();
	/** Dibuja el elemento visual gráfico (opaco) en su panel de acuerdo a sus datos y tipo
	 */
	public void dibuja();

}
