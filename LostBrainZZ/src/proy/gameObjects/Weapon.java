package proy.gameObjects;

import proy.audio.Song;
import proy.interfaces.Purchasable;

/**
 * 
 * @author Danel
 *
 */
public abstract class Weapon extends HurtableObject implements Purchasable{
	private static final long serialVersionUID = 1L;
	private int attack;
	private int cost;
	private int reach;
	private boolean oneShot;
	private int depreciateValue;
	private Song attackSound;
	/**
	 * Inicializa el arma con segun su posicion en el tablero
	 * @param row
	 * @param column
	 */
	protected Weapon(int row, int column) {
		super(row,column);
	}

	/**
	 * Devuelve el ataque del arma
	 * @return attack
	 */
	public int getAttack() {
		return attack;
	}
	/**
	 * Establece el ataque del arma
	 * @param attack
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}
	/**
	 * Devuelve el coste del arma
	 * @return cost
	 */
	public int getCost() {
		return cost;
	}
	/**
	 * Establece el coste del arma
	 * @param newCost
	 */
	public void setCost(int newCost) {
		this.cost = newCost;
	}

	/**
	 * Devuelve el alcanze del arma
	 * @return reach
	 */
	public int getReach() {
		return reach;
	}
	/**
	 * Establece el alcanze del arma
	 * @param reach
	 */
	public void setReach(int reach) {
		this.reach = reach;
	}
	/**
	 * Devuelve el valor de depreciacion del arma
	 * @return 
	 */
	public int getDepreciateValue() {
		return depreciateValue;
	}
	/**
	 * Establece el valor de depreciacion del arma
	 * @param depreciateValue
	 */
	public void setDepreciateValue(int depreciateValue) {
		this.depreciateValue = depreciateValue;
	}
	/**
	 * Reduce la vida del arma segun el valor de depreciacion
	 */
	public void depreciate() {
		hurt(depreciateValue);
	}
	/**
	 * Establece el sonido que hara cuando se dispare
	 * @param s
	 */
	protected void setAttackSound(Song s) {
		attackSound=s;
	}
	/**
	 * Devuelve el objeto Song con el sonido que hace cuando ataca
	 * @return
	 */
	public Song getAttackSound() {
		return attackSound;
	}
	/**
	 * Establece si el arma solo ataca una vez
	 * @param oneShot
	 */
	public void setOneShot(boolean oneShot) {
		this.oneShot=oneShot;	
	}
	/**
	 * Devuelve si el arma solo ataca na vez, true si solo ataca una vez, false si no
	 * @return
	 */
	public boolean isOneShot() {
		return oneShot;	
	}

	@Override
	public String toString() {
		return "W: ["+this.row+", "+this.column+"]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Weapon)) {return false;}
		Weapon w = (Weapon) obj;
		return this.row==w.row && this.column==w.column;
	}
	@Override
	public boolean isPurchasable(int money) {
		return money>=this.getCost();
	}
}
