package examen202006ext.data;

/** Interfaz para objetos gráficos rotables
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public interface Rotable {
	/** Cambia la rotación del objeto
	 * @param angulo	Nuevo ángulo (en radianes)
	 */
	public void setRotacion( double angulo );
	/** Añade una rotación sobre la ya existente del objeto
	 * @param angulo	Nuevo ángulo a añadir al existente (en radianes)
	 */
	public void addRotacion( double angulo );
	/** Devuelve la rotación del objeto
	 * @return	Ángulo del objeto (en radianes)
	 */
	public double getRotacion();
}
