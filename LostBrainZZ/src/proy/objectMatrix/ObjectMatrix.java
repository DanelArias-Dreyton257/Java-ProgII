package proy.objectMatrix;

import java.awt.Point;
import java.io.Serializable;

import proy.gameObjects.*;
import proy.interfaces.Showable;
import proy.visuals.utilObjects.LifeBar;
import proy.visuals.VentanaGrafica;
/**
 * 
 * @author Danel
 *
 */
public abstract class ObjectMatrix implements Showable,Serializable{

	private static final long serialVersionUID = 1L;
	protected GameObject[][] matrix;
	private int upMargin=0;
	private int leftMargin=0;
	private double nodeSide=10;

	/**
	 * Inicializa objeto recibiendo una template
	 * @param template
	 */
	ObjectMatrix(int[][] template){
		matrix= new GameObject[template.length][template[0].length];
		initMatrix(template);
	}
	/**
	 * Inicializa la matriz vacia segun el numero de filas y columnas
	 * @param numRows
	 * @param numColumns
	 */
	ObjectMatrix(int numRows, int numColumns){
		int[][] emptyTemplate=new int[numRows][numColumns];
		matrix = new GameObject[emptyTemplate.length][emptyTemplate[0].length];
		initMatrix(emptyTemplate);
	}
	/**
	 * Inicializa la martiz llenandola de objetos segun la template
	 * @param template
	 */
	public abstract void initMatrix(int[][] template);

	/**
	 * Devuelve el objeto que se encuentra en la posicion pasada como parametro
	 * @param numRow
	 * @param numColumn
	 * @return objeto GameObject
	 */
	public GameObject getObject(int numRow, int numColumn) {
		return matrix[numRow][numColumn];
	}
	/**
	 * Inserta el objeto pasado en las coordenadas pasadas por los parametros
	 * @param obj
	 * @param numRow
	 * @param numColumn
	 */
	public void insertObject(GameObject obj,int numRow, int numColumn) {
		matrix[numRow][numColumn]=obj;
		if (obj!=null) {
			matrix[numRow][numColumn].setColumn(numColumn);
			matrix[numRow][numColumn].setRow(numRow);
		}
		placeOnScreen();
	}
	/**
	 * Elimina el objeto indicado por los parametros
	 * @param numRow
	 * @param numColumn
	 */
	public void eraseObject(int numRow, int numColumn) {
		matrix[numRow][numColumn]=null;
	}
	/**
	 * Intercambia los objetos indicados en los parametros
	 * @param numRow1
	 * @param numColumn1
	 * @param numRow2
	 * @param numColumn2
	 */
	public void swapObjects(int numRow1, int numColumn1,int numRow2, int numColumn2) {
		GameObject temp= matrix[numRow1][numColumn1];

		matrix[numRow1][numColumn1]=matrix[numRow2][numColumn2];
		if (matrix[numRow1][numColumn1]!=null) {
			matrix[numRow1][numColumn1].setRow(numRow1);
			matrix[numRow1][numColumn1].setColumn(numColumn1);
		}
		if (matrix[numRow2][numColumn2]!=null) {
			matrix[numRow2][numColumn2]=temp;
			matrix[numRow2][numColumn2].setRow(numRow2);
			matrix[numRow2][numColumn2].setColumn(numColumn2);
		}
		placeOnScreen();
	}
	/**
	 * Mueve un objeto de una posicion a otra en el tablero segun lo indicado por los parametros
	 * ATENCION! sobreescribe lo que hubiese en la posicion de destino
	 * @param numRowOg
	 * @param numColumnOg
	 * @param numRowDes
	 * @param numColumnDes
	 */
	public void moveObjects(int numRowOg, int numColumnOg,int numRowDes, int numColumnDes) {
		matrix[numRowDes][numColumnDes]=matrix[numRowOg][numColumnOg];
		if (matrix[numRowDes][numColumnDes]!=null) {
			matrix[numRowDes][numColumnDes].setRow(numRowOg);
			matrix[numRowDes][numColumnDes].setColumn(numColumnOg);
		}
		matrix[numRowOg][numColumnOg]=null;
		placeOnScreen();
	}
	/**
	 * Devuelve la matrix de objetos
	 * @return
	 */
	public GameObject[][] getMatrix() {
		return matrix;
	}

	/**
	 * Dibuja cada elemento de la matriz
	 * @param v Objeto VentanaGrafica
	 */
	public void show(VentanaGrafica v) {
		for (int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[0].length;j++) {
				GameObject obj = matrix[i][j];
				if(obj!=null) {
					obj.show(v);
				}
			}
		}
	}
	/**
	 * Coloca en pantalla la matriz de objetos segun los margenes guardados
	 */
	private void placeOnScreen() {
		for (int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[0].length;j++) {
				GameObject obj = matrix[i][j];
				if(obj!=null) {
					obj.setSide(nodeSide);
					double newX = obj.getColumn()*obj.getSide()+ leftMargin;
					double newY =  obj.getRow()*obj.getSide()+upMargin;
					obj.setCoor(newX, newY);
					if (obj instanceof HurtableObject) {
						HurtableObject hurtObj = (HurtableObject) obj;
						LifeBar lb = hurtObj.getLifeBar();
						lb.setHeight(nodeSide/10);
						lb.setWidth(nodeSide);
						lb.setCoor(newX, newY-lb.getHeight());
					}
				}
			}
		}
	}
	/**
	 * Establece las posiciones en pantalla la matriz de objetos segun los margenes dados
	 * @param v
	 * @param yUpMargin
	 * @param yDownMargin
	 * @param xLeftMargin
	 * @param xRightMargin
	 */
	public void placeOnScreen(VentanaGrafica v, int yUpMargin, int yDownMargin, int xLeftMargin, int xRightMargin) {
		placeOnScreen(v.getAltura(), v.getAnchura(), yUpMargin, yDownMargin, xLeftMargin, xRightMargin);
	}
	/**
	 * Establece las posiciones en pantalla la matriz de objetos segun los margenes dados
	 * @param screenHeight
	 * @param screenWidth
	 * @param yUpMargin
	 * @param yDownMargin
	 * @param xLeftMargin
	 * @param xRightMargin
	 */
	public void placeOnScreen(int screenHeight, int screenWidth, int yUpMargin, int yDownMargin, int xLeftMargin, int xRightMargin) {
		upMargin=yUpMargin;
		//downMargin=yDownMargin;
		leftMargin=xLeftMargin;
		//rightMargin=xRightMargin;
		double sideH = (screenHeight-yUpMargin-yDownMargin)/matrix.length;
		double sideW = (screenWidth-xLeftMargin-xRightMargin)/matrix[0].length;
		nodeSide = Math.min(sideH, sideW);
		placeOnScreen();
	}
	/**
	 * Devuelve el objeto en el cual se encuentre el Point pasado como parametro
	 * @param pos
	 * @return
	 */
	public GameObject getObjectIsIn(Point pos) {
		for (int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[0].length;j++) {
				GameObject obj = matrix[i][j];
				if (obj!=null) {
					if (obj.isIn(pos)) {
						return obj;
					}
				}
			}
		}
		return null;
	}
	/**
	 * Devuelve true si la matriz esta llena de null, false si hay algun objeto no nulo
	 * @return
	 */
	public boolean isEmpty() {
		for (GameObject[] arr: matrix) {
			for (GameObject obj : arr) {
				if (obj!=null) return false;
			}
		}
		return true;
	}
	/**
	 * Devuelve el numero de filas de la matriz
	 * @return
	 */
	public int getNumRows() {
		return matrix.length;
	}
	/**
	 * Devuelve el numero de columnas de la matriz
	 * @return
	 */
	public int getNumColumns() {
		return matrix[0].length;
	}
	/**
	 * Devuelve el numero de posiciones no nulas de la matriz
	 * @return
	 */
	public int getNumObjects() {
		int cont = 0;
		for (GameObject[] arr: matrix) {
			for (GameObject obj : arr) {
				if (obj!=null) cont++;
			}
		}
		return cont;
	}
}
