package proy.gameObjects;

import java.awt.Point;
import java.io.Serializable;

import proy.interfaces.Clickable;
import proy.interfaces.Showable;
import proy.visuals.VentanaGrafica;
/**
 * 
 * @author Danel
 *
 */
public abstract class GameObject implements Clickable, Showable,Serializable{

	private static final long serialVersionUID = 1L;
	private static final int IMG_SIZE=100;
	protected static final float OPACITY=1.0f;
	protected static final int ROTATION=0;
	protected int row;
	protected int column;
	protected double x;
	protected double y;
	protected double side;
	protected String image;

	/**
	 * Inicializa el objeto segun su posicion en el tablero
	 * @param row
	 * @param column
	 */
	GameObject(int row, int column){
		setRow(row);
		setColumn(column);
	}
	/**
	 * Dibuja en pantalla el objeto
	 * @param v Objeto VentanaGrafica
	 */
	public void show(VentanaGrafica v) {
		double zoom=side/IMG_SIZE;
		double margin=side/2;
		v.dibujaImagen(image, x+margin, y+margin, zoom, ROTATION, OPACITY);
	}
	/**
	 * Devuelve la fila en la que se encuentra el objeto
	 * @return row
	 */
	public int getRow() {
		return row;
	}
	/**
	 * Establece la fila en la que se encuentra el objeto
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * Devuelve la columna en la que se encuentra el objeto
	 * @return column
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * Establece la columna en la que se encuentra el objeto
	 * @param column
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	/**
	 * Devuelve el nombre del archivo de imagen del objeto
	 * @return String con el nombre del archivo
	 */
	public String getImage() {
		return image;
	}
	/**
	 * Establece el nombre del archivo de imagen
	 * @param image nombre del archivo de imagen
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * Devuelve la coordenada x de arriba a la izquierda
	 * @return
	 */
	public double getX() {
		return x;
	}
	/**
	 * Establece la coordenada x de arriba a la izquierda
	 * @return
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * Devuelve la coordenada y de arriba a la izquierda
	 * @return
	 */
	public double getY() {
		return y;
	}
	/**
	 * Establece la coordenada y de arriba a la izquierda
	 * @return
	 */
	public void setY(double y) {
		this.y = y;
	}
	/**
	 * Devuelve el tamanyo del lado del objeto
	 * @return
	 */
	public double getSide() {
		return side;
	}
	/**
	 * Establece el tamanyo del lado del objeto
	 * @return
	 */
	public void setSide(double side) {
		this.side = side;
	}
	/**
	 * Establece la coordenada de arriba a la izquierda del objeto
	 * @param x
	 * @param y
	 */
	public void setCoor(double x, double y) {
		this.x=x;
		this.y=y;
	}
	public boolean isIn(Point pos) {
		return pos.getX()>=x && pos.getY()>=y && pos.getX()<=x+side && pos.getY()<=y+side;
	}

}
