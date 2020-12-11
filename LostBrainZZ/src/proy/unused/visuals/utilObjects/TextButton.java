package proy.unused.visuals.utilObjects;

import java.awt.Color;
import java.awt.Font;

import proy.visuals.VentanaGrafica;

/**
 * 
 * @author Danel
 *
 */
public class TextButton extends SimpleButton {
	private static final long serialVersionUID = 1L;
	protected String text="";
	protected Font font = new Font("TextButton", 1, 15);
	protected Color fontColor=Color.BLACK;
	/**
	 * Constructor del objeto boton con texto
	 * @param x coordenada x de arriba a la izquierda
	 * @param y coordenada y de arriba a la izquierda
	 * @param height
	 * @param width
	 * @param text texto a mostrar en el boton
	 */
	public TextButton(double x, double y, int height, int width,String text) {
		super(x, y, height, width);
		setText(text);
	}
	/**
	 * Constructor del objeto boton con texto
	 * @param x coordenada x de arriba a la izquierda
	 * @param y coordenada y de arriba a la izquierda
	 * @param height
	 * @param width
	 * @param text texto a mostrar en el boton
	 * @param color Color del boton
	 */
	public TextButton(double x, double y, int height, int width,String text,Color color) {
		super(x, y, height, width,color);
		setText(text);
	}
	/**
	 * Devuelve el texto que se muestra en el boton
	 * @return
	 */
	public String getText() {
		return text;
	}
	/**
	 * Establece el tecto que se muestra en el boton
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

	public void show (VentanaGrafica v) {
		super.show(v);
		v.dibujaTexto(x+(2*width)/5, y+(2*height)/3, text, font, borderColor);
	}

}
