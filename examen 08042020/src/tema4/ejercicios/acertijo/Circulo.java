package tema4.ejercicios.acertijo;

import java.awt.Color;

import tema3.VentanaGrafica;
/**
 * 
 * @author Danel
 *
 */
public class Circulo extends ObjetoAcertijo implements Draggable {
	
	public int radio;
	public String imagen;
	
	public static final Color COLOR_CIRCULO = Color.MAGENTA;
	public static final float GROSOR_CIRCULO = 2.0f;
	
	/**
	 * Circulo se inicializa con su posicion en la ventana: x, y, el radio, el nombre del archivo imagen y el objeto VentanaGrafica donde se va a visulaizar
	 * @param x
	 * @param y
	 * @param radio
	 * @param imagen
	 * @param ventana
	 */
	public Circulo(int x, int y, int radio, String imagen, VentanaGrafica ventana) {
		super(x, y, ventana);
		setRadio(radio);
		setImagen(imagen);
	}
	
	@Override
	/**
	 * Dibuja el objeto en la ventana
	 */
	public void dibujar() {
		ventana.dibujaCirculo( x, y, radio, GROSOR_CIRCULO, COLOR_CIRCULO );
		ventana.dibujaImagen( imagen, x, y, radio*2, radio*2, 1.0, 0.0, 1.0f );

	}
	
	@Override
	/**
	 * true si la posicion referente a los paramentros se encontraría dentro del circulo
	 * @param x
	 * @param y 
	 */
	public boolean contienePunto(int x, int y) {
		double dist = Math.sqrt( (x-this.x)*(x-this.x) + (y-this.y)*(y-this.y) );
		return dist<=radio;
	}
	/**
	 * Devuelve el radio del circulo
	 * @return radio
	 */
	public int getRadio() {
		return radio;
	}
	/**
	 * Establece el radio del circulo
	 * @param radio
	 */
	public void setRadio(int radio) {
		this.radio = radio;
	}
	/**
	 * Devuelve el nombre del archivo imagen
	 * @return String imagen
	 */
	public String getImagen() {
		return imagen;
	}
	/**
	 * Establece el nombre del archivo imagen
	 * @param String imagen
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	/**
	 * Cambia la posicion actual por la pasada a traves de los parametros
	 * @param x
	 * @param y
	 */
	public void mover( int x, int y ) {
		setX( x );
		setY( y );
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
