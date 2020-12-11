package proy.visuals.utilObjects;

import java.awt.Color;
import java.io.Serializable;

import proy.interfaces.Showable;
import proy.visuals.VentanaGrafica;
/**
 * 
 * @author Danel
 *
 */
public class LifeBar implements Showable,Serializable {

	private static final long serialVersionUID = 1L;
	private int maxLife;
	private int currentLife;
	private double x=0;
	private double y=0;
	private double height=0;
	private double width=0;
	private static final float GROSOR = 1.0f;
	private static final Color BORDER_COLOR = Color.BLACK;
	private static final Color CU_LIFE_COLOR = Color.GREEN;
	private static final Color MAX_LIFE_COLOR = Color.RED;
	/**
	 * Constructor de la barra de vida solo con la vida máxima, constructor solo de uso especifico si despues se usa el metodo placeOnScreen()
	 * @param maxLife
	 */
	public LifeBar(int maxLife) {
		setMaxLife(maxLife);
		setCurrentLife(maxLife);
	}
	/**
	 * Constructor de la barra de vida
	 * @param maxLife
	 * @param x coordenada x de arriba a la izquierda
	 * @param y coordenada y de arriba a la izquierda
	 * @param height
	 * @param width
	 */
	public LifeBar(int maxLife,double x,double y, int height, int width) {
		setMaxLife(maxLife);
		setCurrentLife(maxLife);
		setCoor(x,y);
		setHeight(height);
		setWidth(width);
	}

	@Override
	public void show(VentanaGrafica v) {
		double widthCurrentLife=(currentLife*width)/maxLife;
		v.dibujaRect(x, y, width, height, GROSOR, BORDER_COLOR,MAX_LIFE_COLOR);
		v.dibujaRect(x, y, widthCurrentLife, height, GROSOR, BORDER_COLOR,CU_LIFE_COLOR);

	}
	/**
	 * Coloca en pantalla la barra de vida recibiendo
	 * @param x coordenada x de arriba a la izquierda
	 * @param y coordenada y de arriba a la izquierda
	 * @param height
	 * @param width
	 */
	public void placeOnScreen(double x, double y, int height, int width) {
		setCoor(x,y);
		setHeight(height);
		setWidth(width);
	}
	/**
	 * Establece la coordenada de arriba a la izquierda
	 * @param x coordenada x de arriba a la izquierda
	 * @param y coordenada y de arriba a la izquierda
	 */
	public void setCoor(double x, double y) {
		setX(x);
		setY(y);
	}
	/**
	 * Devuelve el maximo de vida de la barra
	 * @return
	 */
	public int getMaxLife() {
		return maxLife;
	}
	/**
	 * Establece el maximo de vida de la barra
	 * @param maxLife
	 */
	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}
	/**
	 * Devuelve la cantidad de vida actual
	 * @return
	 */
	public int getCurrentLife() {
		return currentLife;
	}
	/**
	 * Establece la cantidad de vida actual
	 * @param currentLife
	 */
	public void setCurrentLife(int currentLife) {
		this.currentLife = currentLife;
	}
	/**
	 * Devuelve la coordenada x de arriba ala izquierda de la barra
	 * @return
	 */
	public double getX() {
		return x;
	}
	/**
	 * Establece la coordenada x de arriba ala izquierda de la barra
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * Devuelve la coordenada y de arriba ala izquierda de la barra
	 * @return
	 */
	public double getY() {
		return y;
	}
	/**
	 * Establece la coordenada y de arriba ala izquierda de la barra
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}
	/**
	 * Devuelve la altura de la barra
	 * @return height
	 */
	public double getHeight() {
		return height;
	}
	/**
	 * Establece la altura de la barra
	 * @param height
	 */
	public void setHeight(double height) {
		this.height = height;
	}
	/**
	 * Devuelve la anchura de la barra
	 * @return width
	 */
	public double getWidth() {
		return width;
	}
	/**
	 * Establece la anchura de la barra
	 * @param width
	 */
	public void setWidth(double width) {
		this.width = width;
	}
	/**
	 * Devuelve el color de la barra que muestra la vida actual
	 * @return
	 */
	public static Color getCuLifeColor() {
		return CU_LIFE_COLOR;
	}
	/**
	 * Devuelve el color de la barra que muestra la vida máxima
	 * @return
	 */
	public static Color getMaxLifeColor() {
		return MAX_LIFE_COLOR;
	}
	/**
	 * Incrementa la vida actual según el incremento
	 * @param increment
	 */
	public void increaseLife(int increment) {
		currentLife+=increment;
	}
	/**
	 * Incrementa la vida actual en 1
	 */
	public void increaseLife() {
		currentLife++;
	}
	/**
	 * Decrementa la vida actual en 1
	 */
	public void decreaseLife() {
		currentLife--;
	}
	/**
	 * Decrementa la vida actual según el decremento
	 * @param decrement
	 */
	public void decreaseLife(int decrement) {
		currentLife-=decrement;
	}
	/**
	 * Establece la vida actual a 0
	 */
	public void resetLife() {
		currentLife=0;
	}
	/**
	 * Devuelve true si la vida maxima es igual a la vida actual
	 * @return maxLife==currentLife
	 */
	public boolean isFull() {
		return maxLife==currentLife;
	}

}
