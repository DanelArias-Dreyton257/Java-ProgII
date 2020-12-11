package proy.visuals.utilObjects;

import java.awt.Color;

import proy.unused.visuals.utilObjects.SimpleTitle;
import proy.visuals.VentanaGrafica;
/**
 * 
 * @author Danel
 *
 */
public class TemporaryTitle extends SimpleTitle {
	private static final long serialVersionUID = 1L;
	private int numCiclesDone=0;
	private double numMaxCicles;
	/**
	 * Constructor de titulo temporal
	 * @param x
	 * @param y
	 * @param text
	 * @param durationInCicles
	 */
	public TemporaryTitle(double x, double y, String text, int durationInCicles) {
		super(x, y, text);
		setDurationInCicles(durationInCicles);
	}
	/**
	 * Constructor de titulo temporal
	 * @param x
	 * @param y
	 * @param text
	 * @param color
	 * @param durationInCicles
	 */
	public TemporaryTitle(double x, double y, String text, Color color, int durationInCicles) {
		super(x, y, text, color);
		setDurationInCicles(durationInCicles);
	}
	/**
	 * Contructor de titulo temporal
	 * @param x
	 * @param y
	 * @param text
	 * @param seconds
	 * @param fps
	 */
	public TemporaryTitle(double x, double y, String text, double seconds, int fps) {
		super(x, y, text);
		setDurationInCicles(Math.round(seconds*fps));
	}
	/**
	 * Contructor de titulo temporal
	 * @param x
	 * @param y
	 * @param text
	 * @param seconds
	 * @param fps
	 */
	public TemporaryTitle(double x, double y, String text, Color color, double seconds, int fps) {
		super(x, y, text, color);
		setDurationInCicles(Math.round(seconds*fps));
	}
	/**
	 * Devuelve el numero de ciclos hechos por el titulo temporal
	 * @return
	 */
	public int getNumCiclesDone() {
		return numCiclesDone;
	}
	/**
	 * Devuelve la duracion del titulo temporal en ciclos
	 * @return
	 */
	public double getDurationInCicles() {
		return numMaxCicles;
	}
	/**
	 * Establece la duracion del titulo temporal en ciclos
	 * @param durationInCicles
	 */
	public void setDurationInCicles(double durationInCicles) {
		this.numMaxCicles = durationInCicles;
	}
	/**
	 * Reinicia el titulo temporal
	 */
	public void resetCicles() {
		numCiclesDone=0;
	}

	@Override
	public void show(VentanaGrafica v) {
		if (numCiclesDone<=numMaxCicles) {
			super.show(v);
			numCiclesDone++;
		}
	}

}
