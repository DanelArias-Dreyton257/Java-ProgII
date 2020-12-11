import pr9.saltarin.JuegoSaltarin;
import pr9.saltarin.VentanaMenu;

public class Main {

	public static void main(String[] args) {
		JuegoSaltarin juego = new JuegoSaltarin();  // Objeto de juego
		VentanaMenu vMenu = new VentanaMenu( juego );
		vMenu.setVisible(true);
	}

}
