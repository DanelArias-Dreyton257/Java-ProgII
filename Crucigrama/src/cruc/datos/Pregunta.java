package cruc.datos;

import java.io.Serializable;

/**
 * 
 * @author Eneko, Jon Ander y Danel
 *
 */
public class Pregunta implements Serializable,Comparable<Pregunta>{

	private static final long serialVersionUID = 2L;
	boolean horizontal;//true=>horizontal, false=>vertical
	int numPreg;
	String pregunta;
	/**
	 * Constructor de la pregunta 
	 * @param horizontal true si la pregunta es horizontal y false si vertical
	 * @param numPreg
	 * @param pregunta
	 */
   public Pregunta (boolean horizontal, int numPreg, String pregunta) {
		setHorizontal(horizontal);
		setNumPreg(numPreg);
		setPregunta(pregunta);
	}
	/**
	 * Establece el valor del boolean horizontal
	 * @param horizontal true=>horizontal, false=>vertical
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	/**
	 * Establece el valor del numero de la pregunta
	 * @param numPreg
	 */
	public void setNumPreg(int numPreg) {
		this.numPreg = numPreg;
	}
	/**
	 * Establece el valor de la propia pregunta
	 * @param pregunta
	 */
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	/**
	 * Establece horizontal y el numero de la pregunta segun la cabezera
	 * @param cabezera String de formato 'H' o 'V' segun si es horizontal o vertical + el numPregunta
	 */
	public void setCabezera(String cabezera) {
		if (cabezera.charAt(0)=='H') setHorizontal(true);
		else setHorizontal(false);
		setNumPreg(Integer.decode(cabezera.substring(1)));
	}
	/**
	 * Devuelve el numero de la pegunta
	 * @return numPregunta
	 */
	public int getNumPreg() {
		return numPreg;
	}
	/**
	 * Devuelve el texto de la propia pregunta
	 * @return pregunta
	 */
	public String getPregunta() {
		return pregunta;
	}
	/**
	 * Devuelve si es horizontal o no
	 * @return true si es horizontal, false si es vertical
	 */
	public boolean isHorizontal() {
		return horizontal;

	}
	/**
	 * Devuelve un String cabezera que indica si es horizontal o vertical y el numero de la pregunta
	 * @return cabezera String de formato 'H' o 'V' segun si es horizontal o vertical + el numPregunta
	 */
	public String getCabezera() {
		String str = "";
		if(horizontal) str="H";
		else str="V";
		return str+numPreg;
	}
	/**
	 * Devuelve un String de formato "-" + cabezera + "-" + pregunta 
	 * @return pregunta completa
	 */
	public String getPreguntaCompleta(){
		return "-"+getCabezera()+"- "+pregunta;
	}
	@Override
	public String toString() {
		return getPreguntaCompleta();
	}
	@Override
	public boolean equals(Object obj) {
		boolean resultado = false;
		if (obj instanceof Pregunta) {
			Pregunta preg = (Pregunta) obj;
			resultado = preg.getPreguntaCompleta()==getPreguntaCompleta();
		}
		return resultado;
	}
	@Override
	public int hashCode() {
		return getPreguntaCompleta().hashCode();
	}
	@Override
	public int compareTo(Pregunta o) {
		return getPreguntaCompleta().compareToIgnoreCase(o.getPreguntaCompleta());
	}

}

