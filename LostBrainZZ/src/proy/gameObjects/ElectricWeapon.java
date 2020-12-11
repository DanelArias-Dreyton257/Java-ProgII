package proy.gameObjects;

import proy.audio.Song;

/**
 * 
 * @author Danel
 *
 */
public class ElectricWeapon extends Weapon {

	private static final long serialVersionUID = 1L;
	private static final String ATT_SOUND_FILE = "audios/Electric_W_sound.wav";

	/**
	 * Inicializa el objeto segun su posicion en el  tablero
	 * @param row
	 * @param column
	 */
	public ElectricWeapon(int row, int column) {
		super(row, column);
		setLifeBar(80);
		setAttack(40);
		setCost(500);
		setReach(1);
		setOneShot(false);
		setDepreciateValue(25);
		setImage("images/ElectricWeapon_pxArt.png");
		setAttackSound(new Song(ATT_SOUND_FILE));
	}
}
