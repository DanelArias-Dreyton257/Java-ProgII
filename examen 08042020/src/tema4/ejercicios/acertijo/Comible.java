package tema4.ejercicios.acertijo;
/**
 * 
 * @author Danel
 *
 */
public interface Comible {
	/**
	 * Devuelve si el propio objeto puede ser comido por el objeto pasado por el parametro
	 * @param obj Objeto ObjetoAcertijo
	 * @return true si el propio objeto podria ser comido por el objeto pasado por el parametro
	 */
	public boolean puedeSerComidoPor(ObjetoAcertijo o);

}
