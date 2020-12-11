package examen202006ext.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.swing.JPanel;

import examen202006ext.data.ElementoDatausto;

/** Panel gráfico de Swing con memoria de pintado
 * (lo que se pinta se mantiene en los refrescos)
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class PanelGrafico extends JPanel {
	private static final long serialVersionUID = 1L;

	private BufferedImage buffer;      // Buffer gráfico del panel (el que se dibuja)
	private Graphics2D graphics;       // Objeto gráfico sobre el que dibujar (del buffer)

	/** Crea un nuevo panel gráfico (inicialmente con fondo blanco)
	 */
	public PanelGrafico() {
		this( Color.WHITE );
	}
	
	/** Crea un nuevo panel gráfico
	 * @param fondo
	 */
	public PanelGrafico( Color fondo ) {
		// Buffer gráfico
		buffer = new BufferedImage( 3000, 2000, BufferedImage.TYPE_INT_ARGB );
		graphics = buffer.createGraphics();
		graphics.setColor( fondo );
		graphics.fillRect( 0, 0, 3000, 2000 );
	}
	
	/** Devuelve el buffer gráfico del panel
	 * @return	Buffer gráfico que se dibuja en el panel
	 */
	public BufferedImage getBuffer() {
		return buffer;
	}
	
	/** Devuelve el objeto gráfico en el que dibujar con persistencia
	 * @return	Objeto gráfico de dibujado
	 */
	public Graphics2D getGraphicsPers() {
		return graphics;
	}
	
	/** Devuelve el objeto gráfico en el que dibujar sin persistencia (no se mantiene al refrescar)
	 * @return	Objeto gráfico de dibujado
	 */
	public Graphics2D getGraphicsSinPers() {
		return (Graphics2D) super.getGraphics();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage( buffer, 0, 0, null );
	}
	
	/** Repinta el panel de forma inmediata (a diferencia de {@link #repaint()} que pide a Swing que repinte en cuanto sea posible)
	 */
	public void repintaAhora() {
		paintImmediately( 0, 0, getWidth(), getHeight() );
	}
	
	// Métodos de dibujado especializados
	
	/** Borra el panel pintándolo de color blanco 
	 */
	public void borra() {
		borra( Color.WHITE );
	}
	
	/** Borra el panel
	 * @param colorFondo	Color a dejar en el fondo
	 */
	public void borra( Color colorFondo ) {
		getGraphicsPers().setColor( colorFondo );
		getGraphicsPers().fillRect( 0, 0, getWidth()+2, getHeight()+2 );
	}
	
	/** Dibuja una flecha en el panel
	 * @param color	Color de flecha
	 * @param stroke	Grueso de línea
	 * @param x	Coordenada inicial (x)
	 * @param y	Coordenada inicial (y)
	 * @param x2	Coordenada final (x)
	 * @param y2	Coordenada final (y)
	 * @param persistente	true si se quiere dibujar y que permanezca, false para que se borre en cuanto haya un repintado
	 */
	public void dibujaFlecha( Color color, Stroke stroke, double x, double y, double x2, double y2, boolean persistente ) {
		Graphics2D g = persistente ? getGraphicsPers() : getGraphicsSinPers();
		dibujaFlecha( g, color, stroke, x, y, x2, y2 );
	}
	
	/** Dibuja una flecha
	 * @param graphics	Objeto gráfico en el que dibujar
	 * @param color	Color de flecha
	 * @param stroke	Grueso de línea
	 * @param x	Coordenada inicial (x)
	 * @param y	Coordenada inicial (y)
	 * @param x2	Coordenada final (x)
	 * @param y2	Coordenada final (y)
	 */
	public static void dibujaFlecha( Graphics2D graphics, Color color, Stroke stroke, double x, double y, double x2, double y2 ) {
		graphics.setColor( color );
		graphics.setStroke( stroke );
		graphics.drawLine( (int)Math.round(x), (int)Math.round(y), (int)Math.round(x2), (int)Math.round(y2) );
		double angulo = Math.atan2( y2-y, x2-x ) + Math.PI;
		double angulo1 = angulo - Math.PI / 10;  // La flecha se forma rotando 1/10 de Pi hacia los dos lados
		double angulo2 = angulo + Math.PI / 10;
		graphics.drawLine( (int)Math.round(x2), (int)Math.round(y2), 
				(int)Math.round(x2+15.0f*Math.cos(angulo1)), (int)Math.round(y2+15.0f*Math.sin(angulo1)) );
		graphics.drawLine( (int)Math.round(x2), (int)Math.round(y2), 
				(int)Math.round(x2+15.0f*Math.cos(angulo2)), (int)Math.round(y2+15.0f*Math.sin(angulo2)) );
	}
	
}
