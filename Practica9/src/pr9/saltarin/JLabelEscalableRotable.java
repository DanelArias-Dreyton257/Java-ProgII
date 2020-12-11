package pr9.saltarin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/** JLabel con escala y rotable para uso en juegos
 * @author AE
 */
public class JLabelEscalableRotable extends JLabel {
	private static final long serialVersionUID = 1L;
	private double rotacion;
	private int ancho, alto;
	private Image imagenObjeto;
	
	/** Crea un JLabel rotable y escalable
	 * @param objetoGrafico	Imagen del jlabel
	 * @param ancho	P�xels de ancho
	 * @param alto	P�xels de alto
	 * @param rotacionInicial	Rotaci�n inicial (en radianes)
	 */
	public JLabelEscalableRotable( ImageIcon objetoGrafico, int ancho, int alto, double rotacionInicial ) {
		super( objetoGrafico );
		this.ancho = ancho;
		this.alto = alto;
		rotacion = rotacionInicial;
		imagenObjeto = objetoGrafico.getImage();
		setBounds( 0, 0, ancho, alto );
	}
	
	/** Cambia la rotaci�n del label
	 * @param rot	Nueva rotaci�n (en radianes)
	 */
	public void setRotacion( double rot ) {
		rotacion = rot;
		repaint();  // Si no se repinta swing no se entera que se ha cambiado la rotaci�n
	}

		private int radio = 0;
	/** Dibuja el radio de colisi�n del JLabel
	 * @param radio	Radio de colisi�n (no se dibuja si <= 0)
	 */
	public void dibujaRadioColision( int radio ) {
		this.radio = radio;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;  // El Graphics realmente es Graphics2D
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
        g2.rotate( rotacion, ancho/2, alto/2 );
        g2.drawImage( imagenObjeto, 0, 0, ancho, alto, null);
        if (radio>0) {
        	g2.setColor( Color.red );
        	g2.setStroke( new BasicStroke(1) );
        	g2.drawOval(ancho/2-radio, alto/2-radio, radio*2, radio*2 );
        }
	}
}