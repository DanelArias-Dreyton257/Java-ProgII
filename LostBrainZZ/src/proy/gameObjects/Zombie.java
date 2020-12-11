package proy.gameObjects;

import proy.audio.Song;

/**
 * 
 * @author Danel
 *
 */
public class Zombie extends HurtableObject implements Comparable<Zombie>{
	private static final long serialVersionUID = 1L;
	private boolean moved=false;
	private double speed;
	private int attack;
	private int brains;
	private static final Song[] DIE_SOUNDS= {new Song("audios/Zombie_sound_1.wav"),new Song("audios/Brains_sound_SMASH.wav"),new Song("audios/Zombie_sound_2.wav"),new Song("audios/Zombie_sound_3.wav"),new Song("audios/Zombie_sound_4.wav"),new Song("audios/Brains_sound_SMASH.wav")};
	private static final int MIN_LIFE = 25;
	private static final int MAX_LIFE = 90;
	private static final int MIN_ATK = 7;
	private static final int MAX_ATK = 13;
	private static final double MIN_SPEED = 0.5;
	private static final double MAX_SPEED = 1.5;

	/**
	 * Inicializa el zombie según su posición en el tablero
	 * @param row
	 * @param column
	 */
	public Zombie(int row, int column){
		super(row,column);
		
		setImage("images/Zombie_pxArt.png");

		//Establece los atributos de los zombies de forma aleatoria respetando los margenes impuestos
		java.util.Random r = new java.util.Random();
		setLifeBar(r.nextInt(MAX_LIFE-MIN_LIFE+1)+MIN_LIFE);
		setSpeed(MIN_SPEED + (MAX_SPEED-MIN_SPEED)*r.nextFloat());
		setAttack(r.nextInt(MAX_ATK-MIN_ATK+1)+MIN_ATK);
		setBrains((int)(getDifficulty()/5));
	}


	/**
	 * Devuelve la velocidad del zombie
	 * @return speed
	 */
	public double getSpeed() {
		return speed;
	}
	/**
	 * Establece la velocidad del zombie
	 * @param speed
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	/**
	 * Devuelve el ataque del zombie
	 * @return attack
	 */
	public int getAttack() {
		return attack;
	}
	/**
	 * Establece el ataque del zombie
	 * @param attack
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}
	/**
	 * Devuelve el numero de puntos que vale el zombie
	 * @return brains
	 */
	public int getBrains() {
		return brains;
	}
	/**
	 * Establece los puntos que vale el zombie
	 * @param brains
	 */
	public void setBrains(int brains) {
		this.brains = brains;
	}
	/**
	 * Devuelve la dificultad del zombie
	 * @return
	 */
	public int getDifficulty() {
		return (int) (attack*getMaxLife()*speed);
	}
	/**
	 * Devuelve si el zombie ha sido movido
	 * @return
	 */
	public boolean isMoved() {
		return moved;
	}
	/**
	 * Establece si el zombie se ha movido
	 * @param moved
	 */
	public void setMoved(boolean moved) {
		this.moved = moved;
	}


	/**
	 * Devuelve el sonido que hace el zombie al morir
	 * @return
	 */
	public static Song getRndDieSound() {
		java.util.Random r = new java.util.Random();
		int id=r.nextInt(DIE_SOUNDS.length);
		return DIE_SOUNDS[id];
	}

	@Override
	public String toString() {
		return "Z: Diff: "+getDifficulty()+",["+this.row+", "+this.column+"]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Zombie)) {return false;}
		Zombie z = (Zombie) obj;
		return this.row==z.row && this.column==z.column;
	}

	@Override
	public int compareTo(Zombie z) {
		return this.getDifficulty()-z.getDifficulty();
	}

}
