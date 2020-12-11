package proy.unused.visuals.utilObjects;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

import proy.interfaces.*;
import proy.visuals.VentanaGrafica;
/**
 * 
 * @author Danel
 *
 */
public class SimpleButton implements Showable,Clickable,Serializable{
	private static final long serialVersionUID = 1L;
	protected double x=0;
	protected double y=0;
	protected int height=0;
	protected int width=0;
	protected Color color = Color.GREEN;
	protected Color borderColor = Color.BLACK;
	protected static final float THICKNESS= 1.0f;

	/**
	 * Constructor del objeto boton
	 * @param x coordenada x de arriba a la izquierda
	 * @param y coordenada y de arriba a la izquierda
	 * @param height
	 * @param width
	 * @param color
	 */
	public SimpleButton(double x, double y, int height, int width,Color color) {
		setCoor(x,y);
		setDimensions(height, width);
		setColor(color);
	}
	/**
	 * Constructor del objeto boton
	 * @param x coordenada x de arriba a la izquierda
	 * @param y coordenada y de arriba a la izquierda
	 * @param height
	 * @param width
	 */
	public SimpleButton(double x, double y, int height, int width) {
		setCoor(x,y);
		setDimensions(height, width);
		setColor(color);
	}

	public boolean isIn(Point pos) {
		return pos.getX()>=x && pos.getY()>=y && pos.getX()<=x+width && pos.getY()<=y+height;
	}
	/**
	 * Devuelve la coordenada x de arriba a la izquierda
	 * @return
	 */
	public double getX() {
		return x;
	}
	/**
	 * Devuelve la coordenada y de arriba a la izquierda
	 * @return
	 */
	public double getY() {
		return y;
	}
	/**
	 * Establece la coordenada de arriba a la izquierda del boton
	 * @param x
	 * @param y
	 */
	public void setCoor(double x, double y) {
		this.y = y;
		this.x = x;
	}
	/**
	 * Establece las dimensiones del boton
	 * @param height
	 * @param width
	 */
	public void setDimensions(int height, int width) {
		this.height=height;
		this.width=width;
	}
	/**
	 * Devuelve la altura del boton
	 * @return
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * Establece la altura del boton
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * Devuelve la anchura del boton
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * Establece la anchura del boton
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * Devuelve el color del boton
	 * @return
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * Establece el color del boton
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	@Override
	public void show(VentanaGrafica v) {
		v.dibujaRect(x, y, width, height, THICKNESS, borderColor, color);
	}
}
