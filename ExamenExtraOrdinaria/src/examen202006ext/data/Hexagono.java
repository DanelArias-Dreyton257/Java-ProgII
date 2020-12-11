package examen202006ext.data;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import examen202006ext.utils.Geometria;
import examen202006ext.utils.Geometria.Polar;
import examen202006ext.gui.PanelGrafico;

/** Tipo de gráfico hexágono para el Datausto
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class Hexagono extends ElementoDatausto implements Coloreable, EnTablero {

	/** Inicialización estática de la clase Hexágono (llamar a este método para inicializar el gráfico como disponible)
	 */
	public static void init() {
		ElementoDatausto.LISTA_ELEMENTOS_VISUALES.add( new Hexagono( null ) );
	}

	protected double lado = 0.0;  // Medida de lado de hexágono (= distancia de vértice a radio = mitad de distancia de vértice a vértice opuesto (altura))
	protected Color colorBorde = Color.black;  // negro por defecto
	protected Color colorRelleno = null;  // null para relleno transparente (por defecto)
	protected ArrayList<Casilla> listaCasillas = null;  // Lista de casillas que ocupa el hexágono
	protected Tablero tablero;  // tablero del elemento
	
	/** Construye un hexágono por defecto con centro (0,0), lado 0, color principal negro y color de relleno transparente
	 * @param panel	Panel de referencia donde pintarlo
	 */
	public Hexagono( PanelGrafico panel ) {
		this( panel, 0.0, 0.0, 0.0 );
	}
	
	/** Construye un hexágono de borde negro y relleno transparente
	 * @param panel	Panel de referencia donde pintar el elemento
	 * @param x	Coordenada x de centro
	 * @param y	Coordenada y de centro
	 * @param lado	Tamaño del lado
	 */
	public Hexagono( PanelGrafico panel, double x, double y, double lado ) {
		super( panel, x, y );
		this.lado = lado;
	}

		/** Calcula los vértices de un hexágono partiendo de su centro
		 * @param xCentro	Coordenada x del centro del hexágono
		 * @param yCentro	Coordenada y del centro del hexágono
		 * @param lado	Tamaño del lado del hexágono (distancia del centro a cualquier vértice)
		 * @param rotacion	Rotación del hexágono (en radianes)
		 * @return	Array de 6 puntos de los vértices del hexágono
		 */
		protected static Point[] calculaVerticesHexagono( double xCentro, double yCentro, double lado, double rotacion ) {
			rotacion -= Math.PI/2;  // El primer punto empieza en -90º (arriba en pantalla)
			Point[] ret = new Point[6];
			for (int i=0; i<6; i++) {
				Geometria.Polar punto = new Polar( lado, rotacion + i * Math.PI / 3.0 );  // Se gira 1/3PI en cada punto
				ret[i] = new Point(); 
				ret[i].setLocation( punto.toPoint().getX() + xCentro, punto.toPoint().getY() + yCentro );
			}
			return ret;
		}
	
	
	@Override
	public void dibuja( float transp, boolean invertido ) {
		if (panel==null) return;  // No se pinta si no hay panel
		Graphics2D panelG = panel.getGraphicsPers();  // Objeto gráfico donde pintar
		panelG.translate( x, y );   // Incorpora traslación y rotación del hexágono
		// graphics.rotate( rotacion );
		panelG.setStroke( ElementoDatausto.STROKE_2 );
		panelG.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR); // Configuraciones para mejor calidad del gráfico
		panelG.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		panelG.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
		panelG.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, transp ) ); // Modifica transparencia
	    if (invertido) { int valInv = (int)(transp*255); Color newColor = new Color( valInv, valInv, valInv, 0 ); panelG.setXORMode(newColor); }  // Modo xor cambia los colores de pintado para invertirlos
	    Point[] vert = calculaVerticesHexagono( 0, 0, lado, 0 );  // Calcula los vértices centrados en 0,0 para dibujar
		if (colorRelleno!=null) { // Se pinta el relleno si no es transparente
			panelG.setColor( colorRelleno );
			panelG.fillPolygon( new int[] { vert[0].x, vert[1].x, vert[2].x, vert[3].x, vert[4].x, vert[5].x }, 
					new int[] { vert[0].y, vert[1].y, vert[2].y, vert[3].y, vert[4].y, vert[5].y }, 6 );
		}
		// Se pinta el borde del hexágono
		panelG.setColor( colorBorde );
		panelG.drawPolygon( new int[] { vert[0].x, vert[1].x, vert[2].x, vert[3].x, vert[4].x, vert[5].x }, 
				new int[] { vert[0].y, vert[1].y, vert[2].y, vert[3].y, vert[4].y, vert[5].y }, 6 );
		panelG.setTransform( new AffineTransform() );  // Restaurar graphics  (sin rotación ni traslación)
	    if (invertido) panelG.setXORMode(Color.white);  // Restaura modo xor
		panelG.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 1.0f ) ); // Restaura opacidad
	}

	@Override
	public boolean enPunto( Point p ) {
	    Point[] vert = calculaVerticesHexagono( x, y, lado, 0.0 );
	    for (int i=0; i<6; i++) {
			int val = Geometria.orientacion( vert[i], vert[(i+1)%6], p );
	    	if (val!=1) return false;  // Si el punto está a la derecha de todos los lados está dentro; si no, está fuera
	    }
	    return true;
	}
	
	@Override
	public void escala( double escala ) {
		lado *= lado;
	}

	@Override
	public ElementoDatausto crearNuevo( PanelGrafico panel, Point p1, Point p2 ) {
		if (p1==null || p2==null) return null;  // Para crear un hexágono hacen falta dos puntos de referencia (centro y punto que marca la distancia de centro a vértice)
		double distancia = Geometria.distancia( p1, p2 );
		Hexagono ret = new Hexagono( panel, p1.getX(), p1.getY(), distancia );
		return ret;
	}
	
	@Override
	public ElementoDatausto crearNuevo( PanelGrafico panel, String datos ) {
		String[] trozos = datos.split(";");  // Parte la línea con el carácter separador punto y coma (ver método toLinea())
		if (trozos.length<6 || !trozos[0].equals("HEX")) return null; // Si no es gráfico válido se devuelve null
		try {
			double x = Double.parseDouble(trozos[1]);
			double y = Double.parseDouble(trozos[2]);
			double lado = Double.parseDouble(trozos[3]);
			Hexagono ret = new Hexagono( panel, x, y, lado );
			ret.setColor( new Color( Integer.parseInt(trozos[4]) ) );
			if (trozos[5].equals("null")) {
				ret.setColorRelleno( null );
			} else {
				ret.setColorRelleno( new Color( Integer.parseInt(trozos[5]) ) );
			}
			if (tablero!=null) ret.setTablero( tablero );  // Asignar el tablero de partida (si existe) 
			return ret;
		} catch (Exception e) {
			return null;  // Cualquier error en el string se devuelve null
		}
	}
	
	@Override
	public String toLinea() {
		// Línea de objeto con separador punto y coma
		return "HEX;" + x + ";" + y + ";" + lado + ";" + colorBorde.getRGB() + ";" + (colorRelleno==null?"null":colorRelleno.getRGB());
	}
	
	@Override
	public void setColor(Color color) {
		this.colorBorde = color;
	}

	@Override
	public void setColorRelleno(Color colorRelleno) {
		this.colorRelleno = colorRelleno;
	}

	@Override
	public Color getColor() {
		return colorBorde;
	}

	@Override
	public Color getColorRelleno() {
		return colorRelleno;
	}
	
	@Override
	public String toString() {
		if (panel==null) {
			return "Hexágono";
		} else {
			return "Hexágono (" + x + "," + y + ") lado (" + lado + ")";
		}
	}

	// Interfaz EnTablero

	@Override
	public ArrayList<Casilla> getCasillas() {
		if (listaCasillas==null) {
			if (tablero==null) return null;  // Las que no tienen tablero
			Casilla casilla = tablero.getCasillaDe( new Point( (int)x, (int)y ) );
			listaCasillas = new ArrayList<>();
			if (casilla==null) {
				listaCasillas = null;
			} else {
				listaCasillas.add( casilla );
			}
		}
		return listaCasillas;
	}
	
	@Override
	public Tablero getTablero() {
		return tablero;
	}
	
	@Override
	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
		listaCasillas = null;  // Reinicia las casillas al cambiar el tablero
	}
	
	@Override
	public void setX(double x) {
		super.setX(x);
		listaCasillas = null;  // Desactiva la lista porque al cambiar de posición las casillas pueden ser distintas
	}

	@Override
	public void setY(double y) {
		super.setY(y);
		listaCasillas = null;  // Desactiva la lista porque al cambiar de posición las casillas pueden ser distintas
	}

	@Override
	public int getNumCasillas() {
		if (listaCasillas==null) getCasillas();
		if (listaCasillas==null) return 0;
		return listaCasillas.size();
	}

}
