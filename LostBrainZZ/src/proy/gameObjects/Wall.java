package proy.gameObjects;

/**
 * 
 * @author Danel
 *
 */
public class Wall extends LabNode{
	private static final long serialVersionUID = 1L;
	private static int cost = 100; 

	/**
	 * Inicializa el objeto segun su posicion en el tablero
	 * @param row
	 * @param column
	 */
	public Wall(int row, int column) {
		super(row, column);
		setWalkable(false);
		setImage("images/Wall_pxArt.png");
	}
	/**
	 * Devuelve el coste de las paredes
	 * @return
	 */
	public static int getCost() {
		return cost;
	}
	/**
	 * Establece el coste de las paredes
	 * @param cost
	 */
	public static void setCost(int cost) {
		Wall.cost=cost;
	}
	/**
	 * true si la cantidad de dinero es suficiente para comprar el objeto
	 * @param money
	 * @return
	 */
	public static boolean isPurchasable(int money) {
		return money>=getCost();
	}

}
