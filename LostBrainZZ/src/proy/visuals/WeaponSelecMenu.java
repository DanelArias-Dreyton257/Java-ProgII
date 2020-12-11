package proy.visuals;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import proy.gameObjects.*;
import proy.interfaces.Showable;
import proy.objectMatrix.WeaponMatrix;
/**
 * 
 * @author Danel
 *
 */
public class WeaponSelecMenu implements Showable,Serializable{

	private static final long serialVersionUID = 1L;
	private static Font font = new Font("SelecMenu", 1, 20);
	private static final Color COLOR = Color.BLACK;
	private static int[][] template = {{1,2,3}};
	private WeaponMatrix weapons = new WeaponMatrix(template);
	private Weapon selectedWeapon=null;
	private static final int LITTLE_MARGIN=5; 

	/**
	 * Constructor del menu de armas
	 */
	public WeaponSelecMenu() {
		ArrayList<Weapon>tmp = new ArrayList<>();
		tmp.add(new Bomb(0,0));
		tmp.add(new Turret(0,1));
		tmp.add(new ElectricWeapon(0, 2));
		for (Weapon w:tmp) {
			weapons.insertObject(w, w.getRow(), w.getColumn());
		}
		shortWeapons();
	}
	/**
	 * Metodo que dibuja el menu
	 */
	@Override
	public void show(VentanaGrafica v) {
		weapons.show(v);
		for (int i=0;i<weapons.getNumColumns();i++) {
			Weapon w = (Weapon) weapons.getObject(0, i);
			v.dibujaTexto(w.getX(), w.getY()+w.getSide()+font.getSize(), Integer.toString(w.getCost()), font, COLOR);
		}
	}
	/**
	 * Método que coloca el menu en pantalla
	 * @param v Objeto VentanaGrafica
	 * @param yUpMargin
	 * @param yDownMargin
	 * @param xLeftMargin
	 * @param xRightMargin
	 */
	public void placeOnScreen(VentanaGrafica v, int yUpMargin, int yDownMargin, int xLeftMargin, int xRightMargin) {
		placeOnScreen(v.getAltura(),v.getAnchura(), yUpMargin, yDownMargin+font.getSize(), xLeftMargin, xRightMargin);

	}
	/**
	 * Método que coloca el menu en pantalla
	 * @param screenHeight
	 * @param screenWidth
	 * @param yUpMargin
	 * @param yDownMargin
	 * @param xLeftMargin
	 * @param xRightMargin
	 */
	public void placeOnScreen(int screenHeight,int screenWidth, int yUpMargin, int yDownMargin, int xLeftMargin, int xRightMargin) {
		weapons.placeOnScreen(screenHeight, screenWidth, yUpMargin, yDownMargin+font.getSize()+LITTLE_MARGIN, xLeftMargin, xRightMargin);
	}
	/**
	 * Metodo que comprueba y "almacena" la arma seleccionada
	 */
	public void checkSelectedWeapon(VentanaGrafica v) {
		Point puls = v.getRatonPulsado();
		if (puls!=null) {
			Weapon w = (Weapon) weapons.getObjectIsIn(puls);
			if (w!=null) {
				selectedWeapon = w;
			}
		}
	}
	/**
	 * Devuelve objeto Weapon seleccionado en el menu
	 * @return selectedWeapon
	 */
	public Weapon getSelectedWeapon() {
		return selectedWeapon;
	}
	/**
	 * Organiza las armas según su coste, de menor a mayor
	 */
	private void shortWeapons() {
		int n=weapons.getNumColumns();
		for (int i=0;i<n-1;i++) {
			for (int j=0; j<n-i-1;j++) {
				Weapon w1 = (Weapon) weapons.getObject(0, j+1);
				Weapon w2 = (Weapon) weapons.getObject(0, j);
				if (w1!=null && w2!=null && w1.getCost()<w2.getCost()) {
					weapons.swapObjects(w1.getRow(), w1.getColumn(), w2.getRow(), w2.getColumn());
				}
			}
		}
	}
}
