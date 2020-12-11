import java.awt.Color;
/**
 * 
 * @author Danel
 *
 */
public class CarameloUD {
	private int posFila=0;
	private int posColumna=0;
	private Color color = Color.BLUE;
	private Pelota pel;
	private static final int RADIO_PEL= 50;
	/**
	 * Constructor de la clase caramelo
	 * @param posFila posicion referente a la fila
	 * @param posColumna posicion referente a la columna
	 * @param color
	 */
	CarameloUD(int posFila,int posColumna,Color color){
		this.setPosicionFila(posFila);
		this.setPosicionColumna(posColumna);
		this.setColor(color);
		pel = new Pelota(RADIO_PEL,0 ,0 , this.getColor());
	}
	/**
	 * Setter de la posicion refernte a la fila
	 * @param fila posicion referente a la fila
	 */
	public void setPosicionFila(int fila) {
		this.posFila=fila;
	}
	/**
	 * Setter de la posicion refernte a la columna
	 * @param columna posicion referente a la columna
	 */
	public void setPosicionColumna(int columna) {
		this.posColumna=columna;
	}
	/**
	 * Setter de la posicion en cuanto al tablero
	 * @param fila posicion referente a la fila
	 * @param columna posicion referente a la columna
	 */
	public void setPosicion(int fila,int columna) {
		this.setPosicionFila(fila);
		this.setPosicionColumna(columna);
	}
	/**
	 * Setter del color
	 * @param color POSIBLES COLORES: "azul","verde","rojo","magenta" sino esta en posibles no se cambia.
	 */
	public void setColor(Color color) {
		Color[] posibles = {Color.BLUE,Color.GREEN,Color.RED,Color.MAGENTA};
		for(Color i:posibles) {
			if (i==color) {
				this.color=color;
			}
		}
	}
	
	/**
	 * Getter de la posicion referente a la fila
	 * @return posicion Fila
	 */
	public int getPosicionFila() {return this.posFila;}
	
	/**
	 * Getter de la posicion referente a la columna
	 * @return posicion Columna
	 */
	public int getPosicionColumna() {return this.posColumna;}
	
	/**
	 * Getter del color
	 * @return color
	 */
	public Color getColor() {return this.color;}
	
	/**
	 * Método toString, devuelve las coordenadas [fila,columna] y su color
	 */
	public String toString() {
		String colorStr="";
		if (this.color.equals(Color.BLUE)) {
			colorStr="Azul";
		}
		else if (this.color.equals(Color.GREEN)) {
			colorStr="Verde";
		}
		else if (this.color.equals(Color.RED)) {
			colorStr="Rojo";
		}
		else {
			colorStr="Magenta";
		}
		return "["+ posFila+ "," +posColumna+"] color: " +colorStr;
	}
	
	@Override
	public boolean equals(Object obj) {
		CarameloUD c=(CarameloUD) obj;
		boolean r = this.posFila==c.posFila && this.posColumna==c.posColumna && this.color==c.color && this.pel.equals(c.pel);
		return r;
	}
	/**
	 * Dibuja las pelotas que representan los caramelos, estableciendo sus coordenadas segun su posicion en el tablero
	 * @param numFilTab numero de filas del tablero
	 * @param numColTab numero de columnas del tablero
	 * @param v Ventana Grafica en la que se dibuja
	 */
	public void dibujar(int numFilTab,int numColTab,VentanaGrafica v) {
		int anch = v.getAnchura();
		int alt = v.getAltura();
		this.pel.setX(pel.getRadio()+ this.posColumna *(anch/numColTab));
		this.pel.setY(pel.getRadio()+ this.posFila * (alt/numFilTab));
		this.pel.dibuja(v);
	}
	
}
