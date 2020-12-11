package proy.gameObjects;

import proy.audio.Song;

/**
 * 
 * @author Danel
 *
 */
public class Turret extends Weapon {
	private static final long serialVersionUID = 1L;
	private static final String ATT_SOUND_FILE = "audios/Turret_sound.wav";
	/**
	 * Inicializa el objeto segun su posicion del tablero
	 * @param row
	 * @param column
	 */
	public Turret(int row, int column) {
		super(row, column);
		setLifeBar(100);
		setAttack(10);
		setCost(1000);
		setReach(4);
		setDepreciateValue(4);
		setOneShot(false);
		setImage("images/Turret_pxArt.png");
		setAttackSound(new Song(ATT_SOUND_FILE));
	}

}
