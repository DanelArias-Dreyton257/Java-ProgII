package examen202004int.datos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

import examen202004int.iu.VentanaGrafica;

/** Clase para botones de interacción en el juego
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class Boton extends ObjetoJuego implements Clickable,Serializable {
	
	//================= Parte static
	
	private static final long serialVersionUID = 1L;
	/** Tamaño visual del botón (diámetro del círculo) */
	public static final Rectangle TAMANYO_BOTON = new Rectangle( 90, 40 );
	/** Tipo de letra visual del botón */
	public static final Font FONT = new Font( "Arial", Font.BOLD, 20 );
	/** Color de letra visual del botón */
	private static final Color COLOR_TEXTO = Color.BLACK;
	/** Color de fondo del botón */
	private static final Color COLOR_FONDO = Color.CYAN;
	/** Color de fondo del botón cuando está desactivado */
	private static final Color COLOR_FONDO_DESACTIVADO = Color.LIGHT_GRAY;
	/** Color de borde del botón */
	private static final Color COLOR_BORDE = Color.BLACK;
	/** Grosor de borde del botón */
	private static final float GROSOR_BORDE  = 2f;
	
	//================= Parte no static

	String texto;      // Texto visible del botón
	boolean activado;  // Activación del botón

	/** Crea un nuevo botón, activado por defecto
	 * @param x	Coordenada x de la esquina superior izquierda
	 * @param y	Coordenada y de la esquina superior izquierda
	 * @param ventana	Ventana del botón
	 * @param texto	texto del botón
	 */
	public Boton(int x, int y, VentanaGrafica ventana, String texto) {
		this( x, y, ventana, texto, true );
	}
	
	/** Crea un nuevo botón
	 * @param x	Coordenada x de la esquina superior izquierda
	 * @param y	Coordenada y de la esquina superior izquierda
	 * @param ventana	Ventana del botón
	 * @param texto	texto del botón
	 * @param activado	true si el botón se quiere activado, false si se quiere desactivado
	 */
	public Boton(int x, int y, VentanaGrafica ventana, String texto, boolean activado) {
		super(x, y, ventana);
		this.texto = texto;
		this.activado = activado;
	}
	
	public boolean isActivado() {
		return activado;
	}

	public void setActivado(boolean activado) {
		this.activado = activado;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public void dibujar() {
		ventana.dibujaRect(x, y, TAMANYO_BOTON.width, TAMANYO_BOTON.height, GROSOR_BORDE, COLOR_BORDE, activado ? COLOR_FONDO : COLOR_FONDO_DESACTIVADO );
		ventana.dibujaTextoCentrado( x, y, TAMANYO_BOTON.width, TAMANYO_BOTON.height, texto, FONT, activado ? COLOR_TEXTO : COLOR_FONDO );
	}

	// Si el botón está desactivado, no responde al ratón (devuelve false en este método)
	@Override
	public boolean contienePunto(int x, int y) {
		if (activado)
			return x>=this.x && x<=(this.x+TAMANYO_BOTON.width) && y>=this.y && y<=(this.y+TAMANYO_BOTON.height);
		else 
			return false;
	}

	@Override
	public boolean contienePunto(Point punto) {
		return contienePunto(punto.x,punto.y);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Boton)) return false;
		Boton o2 = (Boton) obj;
		return super.equals(obj) && texto.equals(o2.texto);
	}
	
	@Override
	public String toString() {
		return "Botón " + texto + " " + super.toString();
	}
	
}