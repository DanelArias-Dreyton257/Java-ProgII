package cruc.datos;

import java.awt.*;
import java.io.Serializable;

import cruc.interfaces.Visualizable;
import cruc.visuales.VentanaGrafica;
/**
 * 
 * @author Eneko, Jon Ander y Danel
 *
 */
public class Cabezera implements Visualizable, Serializable, Comparable<Cabezera> {

	private static final long serialVersionUID = 2L;
	Casilla casilla;
	Pregunta pregunta;
	String cabezera;
	double x;
	double y;
	static Font fuente;
	static final Color COLOR = Color.BLACK;
	/**
	 * Constructor d ela cabezera a traves de la casilla de la primera letra de la erspuesta y la pregunta correspondiente
	 * @param casilla Objeto Casilla de la primera letra de la palabra que es la respuesta de la pregunta pasada como parametro
	 * @param pregunta Objeto Pregunta cuya respuesta su primera letra esta contenida en la casilla pasada como parametro
	 */
	public Cabezera(Casilla casilla,Pregunta pregunta){
		setCasilla(casilla);
		setPregunta(pregunta);
		setCabezera(pregunta.getCabezera());
	}
	/**
	 * Metodo que establece la posicion de la cabezera segun la posicion de la casilla
	 */
	public void initCoor() {
		if (pregunta.isHorizontal()) {
			setX(casilla.getX()-Casilla.getLado());
			setY(casilla.getY()+(2*Casilla.getLado()/3));
		}
		else {
			setX(casilla.getX());
			setY(casilla.getY());
		}
	}
	/**
	 * Metodo que establece la fuente de la clase cabezera segun la fuente de la clase casilla
	 */
	public static void initFuente() {
		Font fuenteC=Casilla.getFuente();
		setFuente(new Font("Cabezera",1,fuenteC.getSize()));
	}
	/**
	 * Establece el texto de la cabezera
	 * @param cabezera String de formato 'H' o 'V' segun si es horizontal o vertical + el numPregunta
	 */
	private void setCabezera(String cabezera) {
		this.cabezera=cabezera;
	}
	/**
	 * Devuelve el texto de la cabezera
	 * @return cabezera
	 */
	public String getCabezera() {
		return cabezera;
	}
	/**
	 * Devuelve la casilla de la primera letra de la palabra, la contigua a la cabezera
	 * @return objeto casilla
	 */
	public Casilla getCasilla() {
		return casilla;
	}
	/**
	 * Establece la casilla de la primera letra de la palabra que a su vez es respuesta a la pregunta
	 * @param casilla
	 */
	public void setCasilla(Casilla casilla) {
		this.casilla = casilla;
	}
	/**
	 * Devuelve el objeto pregunta 
	 * @return pregunta
	 */
	public Pregunta getPregunta() {
		return pregunta;
	}
	/**
	 * Establece la pregunta cuya respuesta su primera letra esta contenida en la casilla
	 * @param pregunta
	 */
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	/**
	 * Devuelve la coordenada x de la cabezera. Coordenada de abajo a la izquierda.
	 * @return x
	 */
	public double getX() {
		return x;
	}
	/**
	 * Establece la coordenada x de la cabezera. Coordenada de abajo a la izquierda.
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * Devuelve la coordenada y de la cabezera. Coordenada de abajo a la izquierda.
	 * @return y
	 */
	public double getY() {
		return y;
	}
	/**
	 * Establece la coordenada y de la cabezera. Coordenada de abajo a la izquierda.
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}
	/**
	 * Devuelve el objeto Font de la clase
	 * @return fuente
	 */
	public static Font getFuente() {
		return fuente;
	}
	/**
	 * Establece el objetoFont de la clase
	 * @param fuente
	 */
	public static void setFuente(Font fuente) {
		Cabezera.fuente = fuente;
	}

	@Override
	public void dibujar(VentanaGrafica v) {
		v.dibujaTexto(x, y, cabezera, fuente, COLOR);
	}
	@Override
	public String toString() {
		return getCabezera()+"("+"coor:"+x+","+y+") casilla: ["+casilla.getFila()+","+casilla.getColumna()+"]";
	}
	@Override
	public boolean equals(Object obj) {
		boolean resultado = false;
		if (obj instanceof Cabezera) {
			Cabezera c = (Cabezera) obj;
			resultado = casilla.equals(c.getCasilla()) && pregunta.equals(c.getPregunta());
		}
		return resultado;
	}
	@Override
	public int hashCode() {
		return casilla.hashCode()+pregunta.hashCode();
	}
	@Override
	public int compareTo(Cabezera o) {
		return hashCode()-o.hashCode();
	}
}

