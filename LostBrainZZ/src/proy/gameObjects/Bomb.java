package proy.gameObjects;

import proy.audio.Song;

/**
 * 
 * @author Danel
 *
 */
public class Bomb extends Weapon {

	private static final long serialVersionUID = 1L;
	private static final String ATT_SOUND_FILE = "audios/Explosion_sound.wav";

	/**
	 * Inicializa el objeto segun su posicion en el  tablero
	 * @param row
	 * @param column
	 */
	public Bomb(int row, int column) {
		super(row, column);
		setLifeBar(1);
		setAttack(1000);
		setCost(2000);
		setReach(3);
		setOneShot(true);
		setDepreciateValue(100);
		setImage("images/Bomb_pxArt.png");
		setAttackSound(new Song(ATT_SOUND_FILE));
	}

}
