import java.awt.Color;
import java.util.Random;
/**
 * 
 * @author Danel
 *
 */
public class TableroCaramelos {
	private final int N_FILAS=6;
	private final int N_COLUMNAS=6;
	private static Color[] coloresPosibles= {Color.BLUE,Color.RED,Color.GREEN,Color.MAGENTA};
	
	private CarameloUD[][] tablero= new CarameloUD[N_FILAS][N_COLUMNAS];
	
	/**
	 * Contructor, rellena la matriz de caramelos segun la plantilla
	 * @param plantilla Matriz bidimensional de Strings con los colores de los caramelos
	 */
	TableroCaramelos() {
		
		Random r = new Random();
		
		for (int i=0;i<tablero.length;i++) {
			for (int j=0;j<tablero[0].length;j++) {
				Color colorRandom = coloresPosibles[r.nextInt(coloresPosibles.length)];
				CarameloUD c = new CarameloUD(i, j, colorRandom);
				tablero[i][j]=c;
			}
		}
	}
	/**
	 * Coloca un caramelo en las coordenadas indicada
	 * @param c Caramelo que se quiere introducir
	 * @param posFila posicion referente a la fila
	 * @param posCol posicion referente a la columna
	 */
	public void setCaramelo(CarameloUD c, int posFila, int posCol) {
		tablero[posFila][posCol]=c;
	}
	/**
	 * Quita un caramelo de las coordenadas indicadas
	 * @param posFila posicion referente a la fila
	 * @param posCol posicion referente a la columna
	 */
	public void quitaCaramelo(int posFila,int posCol) {
		tablero[posFila][posCol]=null;
	}
	/**
	 * Mueve un caramelo de unas coordenadas a otras indicadas
	 * @param pFilaOg posicion referente a la fila origen
	 * @param pColOg posicion referente a la columna origen
	 * @param pFilaDes posicion referente a la fila destino
	 * @param pColDes posicion referente a la columna destino
	 */
	public void mueveCaramelo(int pFilaOg,int pColOg,int pFilaDes,int pColDes) {
		tablero[pFilaDes][pColDes]=tablero[pFilaOg][pColOg];
		tablero[pFilaDes][pColDes].setPosicion(pFilaDes, pColDes);
		
		tablero[pFilaOg][pColOg]=null;
	}
	/** 
	 * Intercambia 2 caramelos situados en las coordenadas indicadas
	 * @param pFilaOg posicion referente a la fila origen
	 * @param pColOg posicion referente a la columna origen
	 * @param pFilaDes posicion referente a la fila destino
	 * @param pColDes posicion referente a la columna destino
	 */
	public void intercambiaCaramelo(int pFilaOg,int pColOg,int pFilaDes,int pColDes) {
		CarameloUD temp=tablero[pFilaOg][pColOg];
		temp.setPosicion(pFilaDes, pColDes);
		
		tablero[pFilaOg][pColOg]=tablero[pFilaDes][pColDes];
		tablero[pFilaOg][pColOg].setPosicion(pFilaOg, pColOg);
		
		tablero[pFilaDes][pColDes]=temp;
	}
	/**
	 * Método toString
	 * Devuelve los Caramelos que componen el tablero
	 */
	public String toString() {
		String finalStr="";
		for (CarameloUD[] i:this.tablero) {
			for (CarameloUD j:i) {
				String str ="";
				if (j==null){
					str="NULL";
				}
				else {
					str=j.toString();
				}
				finalStr=finalStr+str+ "\n";
			}
		}
		return finalStr;
	}
	/** 
	 * Getter, devuelve el numero de columnas del tablero
	 * @return numero de columnas
	 */
	public int getNumColumnas(){
		return tablero[0].length;
	}
	/** 
	 * Getter, devuelve el numero de filas del tablero
	 * @return numero de filas
	 */
	public int getNumFilas(){
		return tablero.length;
	}
	/**
	 * Devuelve el caramelo indicado por las coordenadas
	 * @param posFila posicion referente a la fila
	 * @param posColumna posicion referente a la columna
	 * @return Caramelo de las coordenadas inidicadas
	 */
	public CarameloUD getCaramelo(int posFila,int posColumna) {
		return tablero[posFila][posColumna];
	}
	
	@Override
	public boolean equals(Object obj) {
		TableroCaramelos tc = (TableroCaramelos) obj;
		boolean r = this.tablero==tc.tablero;
		return r;
	}
}
