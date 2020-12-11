package proy.unused.visuals.utilObjects;

import java.awt.Color;
import java.awt.Point;

import proy.visuals.VentanaGrafica;
/**
 * 
 * @author Danel
 *
 */
public class DeactivatableTextButton extends TextButton {

	private static final long serialVersionUID = 1L;
	private boolean activated=true;
	private Color deactivatedColor=Color.LIGHT_GRAY;
	private Color activatedColor;
	/**
	 * Constructor del objeto boton desactivable con texto
	 * @param x coordenada x de arriba a la izquierda
	 * @param y coordenada y de arriba a la izquierda
	 * @param height
	 * @param width
	 * @param text texto a mostrar en el boton
	 */
	public DeactivatableTextButton(double x, double y, int height, int width, String text) {
		super(x, y, height, width, text);
		activatedColor=color;
	}
	/**
	 * Constructor del objeto boton desactivable con texto
	 * @param x coordenada x de arriba a la izquierda
	 * @param y coordenada y de arriba a la izquierda
	 * @param height
	 * @param width
	 * @param text texto a mostrar en el boton
	 * @param color Color del boton
	 */
	public DeactivatableTextButton(double x, double y, int height, int width, String text, Color color) {
		super(x, y, height, width, text, color);
		activatedColor=color;
	}
	/**
	 * Devuelve true si el boton esta activado
	 * @return
	 */
	public boolean isActivated() {
		return activated;
	}
	/**
	 * Vuelve false la activacion del boton
	 */
	public void deactivate() {
		activated=false;
	}
	/**
	 * Vuelve true la activacion del boton
	 */
	public void activate() {
		activated=true;
	}
	@Override
	public void show(VentanaGrafica v) {
		if (activated) color=activatedColor;
		else color=deactivatedColor;
		super.show(v);
	}
	@Override
	public boolean isIn(Point pos) {
		boolean result=false;
		if (activated) result = super.isIn(pos);
		return result;
	}
}
