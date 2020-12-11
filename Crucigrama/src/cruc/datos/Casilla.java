package cruc.datos;

import java.awt.*;
import java.io.Serializable;

import cruc.interfaces.*;
import cruc.visuales.VentanaGrafica;
/**
 * 
 * @author Eneko, Jon Ander y Danel
 *
 */
public class Casilla implements Visualizable,Corregible, Serializable,Comparable<Casilla>{

	private static final long serialVersionUID = 2L;
	private static final float GROSOR = 2.0f;
	private static final Color COLOR = Color.BLACK;
	private static Font fuente;
	private int fila;
	private int columna;
	private char letraCorrecta;
	private char letraActual='-';
	private static double lado;
	private Point posicion = new Point(0, 0); //la coordenada de la esquina de arriba a la izquierda
	/**
	 * Constructor de la casilla recibiendo como parametros su posicion en el crucigrama y char con la letra correcta (MAYUSCULA)
	 * @param numFila
	 * @param numColumna
	 * @param letraCorrecta char con la letra correcta de la casilla en MAYUSCULAS
	 */
    Casilla(int numFila,int numColumna, char letraCorrecta){
    	setFila(numFila);
    	setColumna(numColumna);
    	setLetraCorrecta(letraCorrecta);
    }
    /**
     * Devuelve la fila de la casilla referente al crucigrama
     * @return fila
     */
	public int getFila() {
		return fila;
	}
	/**
	 * Establece la fila de la casilla referente al crucigrama
	 * @param fila
	 */
	public void setFila(int fila) {
		this.fila = fila;
	}
	 /**
     * Devuelve la columna de la casilla referente al crucigrama
     * @return columna
     */
	public int getColumna() {
		return columna;
	}
	/**
	 * Establece la columna de la casilla referente al crucigrama
	 * @param columna
	 */
	public void setColumna(int columna) {
		this.columna = columna;
	}
	/**
	 * Devuelve el char con la letra correcta de la casilla en MAYUSCULAS
	 * @return letraCorrecta
	 */
	public char getLetraCorrecta() {
		return letraCorrecta;
	}
	/**
	 * Establece el char con la letra correcta de la casilla en MAYUSCULAS
	 * @param letraCorrecta
	 */
	public void setLetraCorrecta(char letraCorrecta) {
		this.letraCorrecta = letraCorrecta;
	}
	/**
	 * Devuelve el char con la letra actual de la casilla en MAYUSCULAS
	 * @return letraActual
	 */
	public char getLetraActual() {
		return letraActual;
	}
	/**
	 * Establece el char con la letra actual de la casilla en MAYUSCULAS
	 * @param letraActual
	 */
	public void setLetraActual(char letraActual) {
		this.letraActual = letraActual;
	}
	
	public boolean esCorrecto() {
		return letraActual==letraCorrecta;
	}
	/**
	 * Devuelve el objeto Font de la clase
	 * @return fuente
	 */
	public static Font getFuente() {
		return Casilla.fuente;
	}
	/**
	 * Devuelve el lado de las casillas
	 * @return
	 */
	public static double getLado() {
		return Casilla.lado;
	}
	/**
	 * Establece el lado de las casillas
	 * @param lado
	 */
	public static void setLado(double lado) {
		Casilla.lado = lado;
	}
	/**
	 * Devuelve el objeto Point con la posicion de la casilla. Coordenada de arriba a la izquierda
	 * @return objeto Point
	 */
	public Point getPosicion() {
		return posicion;
	}
	/**
	 * Establece la posicion de la casilla a traves de un Point. Coordenada de arriba a la izquierda. 
	 * @param newPosicion
	 */
	public void setPosicion(Point newPosicion) {
		setPosicion(newPosicion.getX(), newPosicion.getY());
	}
	/**
	 * Establece la posicion de la casilla a traves de los parametros. Coordenada de arriba a la izquierda. 
	 * @param x
	 * @param y
	 */
	public void setPosicion(double x, double y) {
		this.posicion.setLocation(x, y);
	}
	/**
	 * Devuelve la coordenada x de la casilla. Coordenada de arriba a la izquierda.
	 * @return x
	 */
	public double getX() {
		return posicion.getX();
	}
	/**
	 * Devuelve la coordenada y de la casilla. Coordenada de arriba a la izquierda.
	 * @return y
	 */
	public double getY() {
		return posicion.getY();
	}
	/**
	 * Devuelve si la posicion de raton se encuentraria dentro de la casilla
	 * @param posRaton objeto Point con la posicion del raton
	 * @return true si el Point estaria dentro de la casilla
	 */
	public boolean estaDentro(Point posRaton) {
		return posRaton.getX()>=getX() && posRaton.getY()>=getY() && posRaton.getX()<=getX()+lado && posRaton.getY()<=getY()+lado;
	}
	/**
	 * Inicializa la fuente de la casilla segun el lado
	 */
	public static void initFuente() {
		fuente = new Font("Casilla", 0, (int) (lado/2));
	}
	@Override
	public String toString() {
		return "["+fila+","+columna+"]:"+letraActual+"("+letraCorrecta+")";
	}
	/**
	 * Inicializa su posicion en la pantalla segun su posicion en el crucigrama y el lado
	 */
	public void initPosicion() {
		
		double margen = lado;
		double x = this.columna*lado +margen;
		double y = this.fila*lado+margen;
		
		setPosicion(x, y);
	}
	
	public void dibujar(VentanaGrafica v) {
		
		v.dibujaRect(getX(), getY(), lado, lado, GROSOR, COLOR);
			
		if (letraActual!='-') {
			
			v.dibujaTexto(getX() + lado/2, getY() + lado/2, String.valueOf(letraActual), fuente, COLOR);
		}
		
	}
	@Override
	public boolean equals(Object obj) {
		boolean resultado = false;
		if (obj instanceof Casilla) {
			Casilla c = (Casilla) obj;
			resultado = fila==c.getFila() && columna==c.getColumna() && letraCorrecta==c.getLetraCorrecta();
		}
		return resultado;
	}
	@Override
	public int hashCode() {
		return fila+columna+letraCorrecta;
	}
	@Override
	public int compareTo(Casilla o) {
		return hashCode()-o.hashCode();
	}
	

    
}