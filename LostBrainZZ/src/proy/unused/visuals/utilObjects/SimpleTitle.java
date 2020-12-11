package proy.unused.visuals.utilObjects;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;

import proy.interfaces.Showable;
import proy.visuals.VentanaGrafica;

/**
 * 
 * @author Danel
 *
 */
public class SimpleTitle implements Showable,Serializable{

	private static final long serialVersionUID = 1L;
	private double x=0;
	private double y=0;
	private Font font = new Font("Title", 1, 12);
	private Color color = Color.BLACK;
	private String text = "";
	private int lenAnt=30;
	/**
	 * Constructor de objeto titulo, recibe la coordenada de abajo a la izquierda y un texto
	 * @param x coordenada x de abajo a la izquierda
	 * @param y coordenada y de abajo a la izquierda
	 * @param text texto del  titulo
	 */
	public SimpleTitle(double x,double y, String text) {
		setCoor(x, y);
		setText(text);
	}
	/**
	 * Constructor de objeto titulo, recibe la coordenada de abajo a la izquierda, un texto y un color
	 * @param x coordenada x de abajo a la izquierda
	 * @param y coordenada y de abajo a la izquierda
	 * @param text texto del  titulo
	 * @param color
	 */
	public SimpleTitle(double x,double y, String text,Color color) {
		setCoor(x, y);
		setText(text);
		setColor(color);
	}
	/**
	 * Establece la posicion del texto, coordenada de abajo a la izquierda
	 * @param x coordenada x de abajo a la izquierda
	 * @param y coordenada y de abajo a la izquierda
	 */
	public void setCoor(double x, double y) {
		this.x=x;
		this.y=y;
	}
	/**
	 * Devuelve el color del texto
	 * @return color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * Establece el color del texto
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * Devuelve el String con el texto
	 * @return texto
	 */
	public String getText() {
		return text;
	}
	/**
	 * Establece el contenido del titulo, el texto
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * Devuelve la coordenada x de abajo a la izquierda del titulo
	 * @return x
	 */
	public double getX() {
		return x;
	}
	/**
	 * Devuelve la coordenada y de abajo a la izquierda del titulo
	 * @return y
	 */
	public double getY() {
		return y;
	}

	@Override
	public void show(VentanaGrafica v) {
		updateFont();
		v.dibujaTexto(x, y, text, font, color);
	}
	/**
	 * Actualiza el tamanyo del titulo segun la cantidad de caracteres
	 */
	private void updateFont() {
		int lenAc = text.length();
		int decrement=2*(lenAc-lenAnt);
		int newFontSize = font.getSize()-decrement;
		if (newFontSize<=0) newFontSize=1;
		Font newFont = new Font(font.getName(), font.getStyle(), newFontSize);
		this.font = newFont;
		this.lenAnt=lenAc;
	}
}
