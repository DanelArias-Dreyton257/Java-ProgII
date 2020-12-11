package proy.interfaces;
/**
 * 
 * @author Danel
 *
 */
public interface Purchasable {
	/**
	 * Devuelve el coste del objeto
	 * @return
	 */
	public int getCost();
	/**
	 * Establece el coste del objeto
	 * @param cost
	 */
	public void setCost(int cost);
	/**
	 * Devuelve si con la cantidad de dinero pasada como parametro se podria comprar el objeto
	 * @param money
	 * @return money>=this.cost
	 */
	public boolean isPurchasable(int money);
}
