
import java.awt.Color;

/** Clase para creación de pelotas visibles en pantalla, con radio y color configurables
 * @author andoni.eguiluz at ingenieria.deusto.es with collaboration of danel.arias
 */
public class Pelota {
	private int radio;
	private double x;
	private double y;
		// private java.awt.Point centro;  // Se podría hacer un punto en lugar de x,y
	private Color color;
	/** Devuelve radio de la pelota
	 * @return	Radio en píxels
	 */
	Pelota(int radio,double x,double y,Color color){
		setRadio(radio);
		setX(x);
		setY(y);
		setColor(color);
	}
	
	public int getRadio() {
		return radio;
	}
	/** Modifica el radio de la pelota
	 * @param radio	Nuevo radio en píxels
	 */
	public void setRadio(int radio) {
		this.radio = radio;
	}
	/** Devuelve la coordenada x del centro de la pelota, con respecto a la pantalla
	 * @return	Coordenada x en píxels
	 */
	public double getX() {
		return x;
	}
	/** Modifica la coordenada x del centro de la pelota, con respecto a la pantalla
	 * @param x	Nueva coordenada x
	 */
	public void setX(double x) {
		this.x = x;
	}
	/** Devuelve la coordenada y del centro de la pelota, con respecto a la pantalla
	 * @return	Coordenada y en píxels
	 */
	public double getY() {
		return y;
	}
	/** Modifica la coordenada y del centro de la pelota, con respecto a la pantalla
	 * @param y	Nueva coordenada y
	 */
	public void setY(double y) {
		this.y = y;
	}
	/** Devuelve el color de línea de la pelota
	 * @return	Color de la línea de la pelota
	 */
	public Color getColor() {
		return color;
	}
	/** Modifica el color de línea de la pelota
	 * @param color	Color nuevo
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * Dibuja la pelota con grosor 3.0
	 * @param VentanaGrafica
	 */
	public void dibuja( VentanaGrafica v ) {
		// Log de dibujo de pelota en consola
		//System.out.println( "Dibuja en " + x + "," + y + " - radio " + radio + " - color " + color );
		// Dibujo en ventana
		v.dibujaCirculo( x, y, radio, 3.0f, color);
	}
	/**
	 * Borra la pelota
	 * @param VentanaGrafica
	 */
	public void borra(VentanaGrafica v) {
		// Log de dibujo de pelota en consola
		//System.out.println( "Borra en " + x + "," + y + " - radio " + radio + " - color " + color );
		// Dibujo en ventana
		v.borraCirculo( x, y, radio, 4.0f);
	}
	/**
	 * Método que sirve para mover la pelota un cierto numero de ciclos, si choca con la parred rebota
	 * @param v Ventana Grafica para mostrar la pelota
	 * @param incX Desplazamiento por ciclo del eje X
	 * @param incY Desplazamiento por ciclo del eje Y
	 * @param ciclos Número de ciclos que debe ejecutar el movivmiento
	 */
	public void mover (VentanaGrafica v, double incX,double incY,int ciclos) {
		for(int i=0;i<ciclos;i++) {
			if (this.isColisionVentanaX(v)) {
				incX=-incX;
			}
			if (this.isColisionVentanaY(v)) {
				incY=-incY;
			}
			this.moverPorCiclo(v, incX, incY);
			v.espera(2000/ciclos);
		}
	}
	/**
 * Método que sirve para mover la pelota por 1 ciclo
	 * @param v Ventana Grafica para mostrar la pelota
	 * @param incX Desplazamiento por ciclo del eje X
	 * @param incY Desplazamiento por ciclo del eje Y
	 */
	public void moverPorCiclo (VentanaGrafica v, double incX,double incY) {
		this.borra(v);
		this.setX(this.getX()+incX);
		this.setY(this.getY()+incY);
		this.dibuja(v);
	}
	/**
	 * Método que devuelve si la pelota choca con la ventana en el eje X
	 * @param v Ventana Grafica para mostrar la pelota
	 * @return true si choca, false si no.
	 */
	public boolean isColisionVentanaX(VentanaGrafica v) {
		boolean result=false;
		if (this.getX()+this.getRadio()>=v.getAnchura() || this.getX()-this.getRadio()<=0) {
			result=true;
		}
		return result;
	}
	/**
	 * Método que devuelve si la pelota choca con la ventana en el eje Y
	 * @param v Ventana Grafica para mostrar la pelota
	 * @return true si choca, false si no.
	 */
	public boolean isColisionVentanaY(VentanaGrafica v) {
		boolean result=false;
		if (this.getY()+this.getRadio()>=v.getAltura() || this.getY()-this.getRadio()<=0) {
			result=true;
		}
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		Pelota p=(Pelota) obj;
		// TODO Auto-generated method stub
		return this.x==p.x && this.y==p.y;
	}
}
