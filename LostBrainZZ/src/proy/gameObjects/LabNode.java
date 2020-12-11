package proy.gameObjects;


/**
 * 
 * @author Danel
 *
 */
public abstract class LabNode extends GameObject {

	private static final long serialVersionUID = 1L;
	protected boolean walkable=false;
	/**
	 * Inicializa el nodo segun su posicion en el tablero
	 * @param row
	 * @param column
	 */
	protected LabNode(int row, int column) {
		super(row, column);
	}

	/**
	 * Devuelve si se puede "andar" por el nodo
	 * @return true si se puede andar sobre el, false si no
	 */
	public boolean isWalkable() {
		return walkable;
	}
	/**
	 * Establece si se pude andar por el nodo
	 * @param walkable
	 */
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
	@Override
	public String toString() {
		return "N: ["+this.row+", "+this.column+"]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LabNode)) {return false;}
		LabNode n = (LabNode) obj;
		return this.row==n.row && this.column==n.column;
	}
}
