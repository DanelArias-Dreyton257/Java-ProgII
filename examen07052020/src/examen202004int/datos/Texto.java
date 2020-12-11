package examen202004int.datos;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;

import examen202004int.iu.VentanaGrafica;

/** Clase que permite crear textos para el juego, rectángulos fijos que contienen un texto dibujado
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class Texto extends ObjetoJuego implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//================= Parte static
	
	/** Tipo de letra del texto */
	public static final Font FONT_TEXTO = new Font( "Arial", Font.PLAIN, 18 );
	/** Color por defecto del texto */
	public static final Color COLOR_TEXTO_DEFECTO = Color.BLACK;
	
	//================= Parte no static
	
	private String texto;
	private int anchura;
	private int altura;
	private Color color;
	
	/** Crea un nuevo texto
	 * @param x	Coordenada x
	 * @param y	Coordenada y
	 * @param ventana	Ventana del texto
	 * @param anchura	Anchura del rectángulo de texto en píxels
	 * @param altura	Altura del rectángulo de texto en píxels
	 * @param texto	Texto a visualizar en ese espacio de rectángulo
	 */
	public Texto(int x, int y, VentanaGrafica ventana, int anchura, int altura, String texto) {
		super(x, y, ventana);
		this.texto = texto;
		this.anchura = anchura;
		this.altura = altura;
		this.color = COLOR_TEXTO_DEFECTO;
	}
	
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getAnchura() {
		return anchura;
	}

	public void setAnchura(int anchura) {
		this.anchura = anchura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void dibujar() {
		ventana.dibujaTexto( x+10, y+16, texto, FONT_TEXTO, color );
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Texto)) return false;
		Texto o2 = (Texto) obj;
		return super.equals(obj) && texto.equals(o2.texto);
	}
	
	@Override
	public String toString() {
		return "Texto " + super.toString() + " " + texto;
	}
	
}