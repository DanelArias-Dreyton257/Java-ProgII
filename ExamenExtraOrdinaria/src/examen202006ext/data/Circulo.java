package examen202006ext.data;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import examen202006ext.utils.Geometria;
import examen202006ext.gui.PanelGrafico;

/** Tipo de gráfico círculo para el Datausto
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class Circulo extends ElementoDatausto implements Coloreable {

	/** Inicialización estática de la clase Círculo (llamar a este método para inicializar el gráfico como disponible)
	 */
	public static void init() {
		ElementoDatausto.LISTA_ELEMENTOS_VISUALES.add( new Circulo( null ) );
	}
	
	protected double radio;
	protected Color color = Color.black;
	protected Color colorRelleno = null;  // null para relleno transparente 
	
	/** Construye un círculo por defecto con centro (0,0), radio 0, color principal negro y color de relleno transparente
	 * @param panel	Panel de referencia donde pintarlo
	 */
	public Circulo( PanelGrafico panel ) {
		super( panel, 0.0, 0.0 );
	}
	
	/** Construye un círculo
	 * @param panel	Panel de referencia donde pintarlo
	 * @param x	Coordenada x
	 * @param y	Coordenada y
	 * @param radio	Radio (positivo)
	 */
	public Circulo( PanelGrafico panel, double x, double y, double radio ) {
		super( panel, x, y );
		this.radio = radio;
	}

	@Override
	public void dibuja( float transp, boolean invertido ) {
		if (panel==null) return;  // No se pinta si no hay panel
		Graphics2D panelG = panel.getGraphicsPers();  // Objeto gráfico donde pintar
		panelG.setStroke( ElementoDatausto.STROKE_2 );
		panelG.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR); // Configuraciones para mejor calidad del gráfico
		panelG.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		panelG.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
		panelG.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, transp ) ); // Modifica transparencia
	    if (invertido) { int valInv = (int)(transp*255); Color newColor = new Color( valInv, valInv, valInv, 0 ); panelG.setXORMode(newColor); }  // Modo xor cambia los colores de pintado para invertirlos
		if (colorRelleno!=null) { // Se pinta el relleno si no es transparente
			panelG.setColor( colorRelleno );
			panelG.fillOval( (int)Math.round(x-radio), (int)Math.round(y-radio), (int)Math.round(radio*2), (int)Math.round(radio*2) );
		}
		// Se pinta el círculo
		panelG.setColor( color );
		panelG.drawOval( (int)Math.round(x-radio), (int)Math.round(y-radio), (int)Math.round(radio*2), (int)Math.round(radio*2) );
	    if (invertido) panelG.setXORMode(Color.white);  // Restaura modo xor
		panelG.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 1.0f ) ); // Restaura opacidad
	}

	@Override
	public boolean enPunto( Point p ) {
		double dist = Geometria.distancia( x, y, p.getX(), p.getY() );
		return dist <= radio;
	}
	
	@Override
	public void escala( double escala ) {
		radio *= escala;
	}

	@Override
	public ElementoDatausto crearNuevo( PanelGrafico panel, Point p1, Point p2 ) {
		if (p1==null || p2==null) return null;  // Para crear un círculo hacen falta dos puntos de referencia
		Circulo ret = new Circulo( panel, p1.getX(), p1.getY(), Geometria.distancia( p1,  p2 ) );  // p1 es el centro y el radio es la distancia p1 y p2
		return ret;
	}

	@Override
	public ElementoDatausto crearNuevo( PanelGrafico panel, String datos ) {
		String[] trozos = datos.split(";");  // Parte la línea con el carácter separador punto y coma (ver método toLinea())
		if (trozos.length<5 || !trozos[0].equals("CIRCULO")) return null; // Si no es círculo válido se devuelve null
		try {
			Circulo ret = new Circulo( panel, Double.parseDouble(trozos[1]), Double.parseDouble(trozos[2]), Double.parseDouble(trozos[3]) );
			ret.setColor( new Color( Integer.parseInt(trozos[4]) ) );
			if (trozos[5].equals("null")) {
				ret.setColorRelleno( null );
			} else {
				ret.setColorRelleno( new Color( Integer.parseInt(trozos[5]) ) );
			}
			return ret;
		} catch (Exception e) {
			return null;  // Cualquier error en el string se devuelve null
		}
	}
	
	@Override
	public String toLinea() {
		// Línea de objeto con separador punto y coma
		return "CIRCULO;" + x + ";" + y + ";" + radio + ";" + color.getRGB() + ";" + (colorRelleno==null?"null":colorRelleno.getRGB());
	}
	
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void setColorRelleno(Color colorRelleno) {
		this.colorRelleno = colorRelleno;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public Color getColorRelleno() {
		return colorRelleno;
	}
	
	@Override
	public String toString() {
		if (panel==null) {
			return "Círculo";
		} else {
			return "Círculo (" + x + "," + y + ") radio " + radio;
		}
	}

}
