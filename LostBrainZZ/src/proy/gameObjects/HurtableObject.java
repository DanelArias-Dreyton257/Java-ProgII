package proy.gameObjects;

import proy.interfaces.Showable;
import proy.visuals.utilObjects.LifeBar;
import proy.visuals.VentanaGrafica;
/**
 * 
 * @author Danel
 *
 */
public abstract class HurtableObject extends GameObject implements Showable{
	private static final long serialVersionUID = 1L;
	private LifeBar lifeBar;

	/**
	 * Inicializa el objeto segun su posicion en el tablero
	 * @param row
	 * @param column
	 */
	public HurtableObject(int row, int column) {
		super(row, column);
	}

	public void show(VentanaGrafica v) {
		super.show(v);
		if (!isDead() && !lifeBar.isFull()) lifeBar.show(v);
	}

	/**
	 * Devuelve la vida del objeto
	 * @return life
	 */
	public int getCurrentLife() {
		return lifeBar.getCurrentLife();
	}
	/**
	 * Devuelve la vida maxima del objeto
	 * @return
	 */
	public int getMaxLife() {
		return lifeBar.getMaxLife();
	}
	/**
	 * Devuelve el objeto barra de vida del objeto
	 * @return
	 */
	public LifeBar getLifeBar() {
		return lifeBar;
	}
	/**
	 * Crea un nuevo objeto barra de vida para el objeto
	 * @param maxLife
	 */
	public void setLifeBar(int maxLife) {
		this.lifeBar = new LifeBar(maxLife);
	}
	/**
	 * Reduce la vida en la cantidad pasada como parametro
	 * @param damage
	 */
	public void hurt(int damage) {
		lifeBar.decreaseLife(damage);
	}
	/**
	 * Comprueba si el zombie esta muerto
	 * @return true si esta muerto, false si esta vivo
	 */
	public boolean isDead() {
		return lifeBar.getCurrentLife()<=0;
	}

}
