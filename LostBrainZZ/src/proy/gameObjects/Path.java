package proy.gameObjects;
/**
 * 
 * @author Danel
 *
 */
public class Path extends LabNode {
	private static final long serialVersionUID = 1L;
	/**
	 * Inicializa el objeto segun su posicion en el tablero
	 * @param row
	 * @param column
	 */
	public Path(int row, int column) {
		super(row, column);
		setWalkable(true);
		setImage("images/Path4_pxArt.png");
	}

}
