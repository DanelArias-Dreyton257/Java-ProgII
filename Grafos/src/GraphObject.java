import java.awt.Color;
import java.util.Random;
/**
 * 
 * @author Danel
 *
 */
public abstract class GraphObject {
	
	protected static final float GROSOR=2.0f;
	protected static final Color[] ColoresPosibles = {Color.BLACK,Color.BLUE,Color.GREEN,Color.MAGENTA,Color.RED};
	/**
	 * metodo que devuelve uncolor aletaorio de la lista ColoresPosibles
	 * @return objeto color
	 */
	protected Color randomColor() {
		Random r = new Random();
		return ColoresPosibles[r.nextInt(ColoresPosibles.length)];
	}
	
	protected Color color=randomColor();
	
	public abstract void show(VentanaGrafica2 ven);
}
