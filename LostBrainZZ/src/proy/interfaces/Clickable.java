package proy.interfaces;

import java.awt.Point;
/**
 * 
 * @author Danel
 *
 */
public interface Clickable {
	/**
	 * Devuelve si el point se encuentra dentro del objeto
	 * @param pos
	 * @return true si esta dentro, sino false
	 */
	public boolean isIn(Point pos);
}
