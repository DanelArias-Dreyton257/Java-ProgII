package examen202006ext;

/** Estados del editor de niveles
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public enum EstadoEditor {
	
	CREACION, MOVIMIENTO, SELECCION, ROTACION;
	
	private static String[] textos = { "Creación", "Movimiento", "Selección", "Rotación"};
	private static String[] hints = { "Usa el ratón para crear figura nueva", "Usa el ratón para mover cualquier gráfico", "Usa el ratón para seleccionar", "Usa el ratón para seleccionar y luego rotar" };
	
	/** Devuelve el estado maquetado para poder utilizarlo como texto de botón
	 * @return	Estado en minúsculas capitalizadas y en castellano
	 */
	public String getTextoBoton() {
		return textos[ ordinal() ];
	}
	/** Devuelve el hint correspondiente al estado
	 * @return	Devuelve la pista de uso de ratón en cada estado
	 */
	public String getHint() {
		return hints[ ordinal() ];
	}
}
