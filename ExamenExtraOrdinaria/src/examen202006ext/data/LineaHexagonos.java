package examen202006ext.data;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Arrays;

import examen202006ext.utils.Geometria;
import examen202006ext.utils.Geometria.Polar;
import examen202006ext.Datausto;
import examen202006ext.gui.PanelGrafico;

/** Tipo de gráfico línea de hexágonos para el Datausto
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class LineaHexagonos extends ElementoDatausto implements Rotable, Coloreable, EnTablero {

	/** Inicialización estática de la clase LineaHexágono (llamar a este método para inicializar el gráfico como disponible)
	 */
	public static void init() {
		ElementoDatausto.LISTA_ELEMENTOS_VISUALES.add( new LineaHexagonos( null ) );
	}

	protected double lado = 0.0;  // Medida de lado de hexágono (= distancia de vértice a radio = mitad de distancia de vértice a vértice opuesto (altura))
	protected Color colorBorde = Color.black;  // negro por defecto
	protected Color colorRelleno = null;  // null para relleno transparente (por defecto)
	protected ArrayList<Casilla> listaCasillas = null;  // Lista de casillas que ocupa la línea de hexágonos
	protected Tablero tablero;  // tablero del elemento
	protected int numHexagonos; // Número de hexágonos de la línea
	protected double rotacion;  // rotación
	
	/** Construye una línea de hexágonos por defecto de un hexágono, con centro (0,0), lado 0, color principal negro y color de relleno transparente
	 * @param panel	Panel de referencia donde pintarlo
	 */
	public LineaHexagonos( PanelGrafico panel ) {
		this( panel, 0.0, 0.0, 0.0, 1 );
	}
	
	/** Construye una línea de hexágonos de borde negro y relleno transparente
	 * @param panel	Panel de referencia donde pintar el elemento
	 * @param x	Coordenada x de centro de primer hexágono
	 * @param y	Coordenada y de centro de primer hexágono
	 * @param lado	Tamaño del lado
	 * @param numHexagonos	Número de hexágonos de la línea
	 */
	public LineaHexagonos( PanelGrafico panel, double x, double y, double lado, int numHexagonos ) {
		super( panel, x, y );
		this.lado = lado;
		this.numHexagonos = numHexagonos;
	}

	@Override
	public void dibuja( float transp, boolean invertido ) {
		if (panel==null) return;  // No se pinta si no hay panel
		boolean anguloExacto = Datausto.anguloGiroPosible( rotacion );  // Diferencia si está en ángulo perfecto de juego (6 posibilidades) o no
		Graphics2D panelG = panel.getGraphicsPers();  // Objeto gráfico donde pintar
		panelG.translate( x, y );   // Incorpora traslación y rotación del hexágono
		panelG.rotate( anguloExacto ? 0 : rotacion );
		panelG.setStroke( ElementoDatausto.STROKE_2 );
		panelG.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR); // Configuraciones para mejor calidad del gráfico
		panelG.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		panelG.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
		panelG.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, transp ) ); // Modifica transparencia
	    if (invertido) { int valInv = (int)(transp*255); Color newColor = new Color( valInv, valInv, valInv, 0 ); panelG.setXORMode(newColor); }  // Modo xor cambia los colores de pintado para invertirlos
	    ArrayList<Casilla> lCasillas = getCasillas();
    	ArrayList<Point> lCentros = getListaCentros(lCasillas,0.0);  // Lista de centros de casillas a pintar
    	if (anguloExacto) {
    		// Pintar hexágonos
		    for (int i=0; i<lCentros.size(); i++) {
		    	Point centro = lCentros.get(i);
			    Point[] vert = Hexagono.calculaVerticesHexagono( centro.x-x, centro.y-y, lado, 0 );  // Calcula los vértices centrados en 0,0 para dibujar
				if (colorRelleno!=null) { // Se pinta el relleno si no es transparente
					panelG.setColor( colorRelleno );
					panelG.fillPolygon( new int[] { vert[0].x, vert[1].x, vert[2].x, vert[3].x, vert[4].x, vert[5].x }, 
							new int[] { vert[0].y, vert[1].y, vert[2].y, vert[3].y, vert[4].y, vert[5].y }, 6 );
				}
				// Se pinta el borde del hexágono
				panelG.setColor( colorBorde );
				panelG.drawPolygon( new int[] { vert[0].x, vert[1].x, vert[2].x, vert[3].x, vert[4].x, vert[5].x }, 
						new int[] { vert[0].y, vert[1].y, vert[2].y, vert[3].y, vert[4].y, vert[5].y }, 6 );
		    }
		    // Pintar líneas/flechas interiores
    		Point ultimoCentro = null;
    		Casilla ultimaCasilla = null;
		    for (int i=0; i<lCentros.size(); i++) {
		    	Point centro = lCentros.get(i);
		    	Casilla casilla = lCasillas==null ? null : lCasillas.get(i);
				if (ultimoCentro!=null) {
					panelG.setColor( colorBorde );
					if (centro==lCentros.get(lCentros.size()-1)) {  // Final de flecha
						if (casilla!=null && ultimaCasilla!=null && ultimaCasilla.getFila()==casilla.getFila()-1 && ultimaCasilla.getColumna()==tablero.getNumColumnas()-1 && casilla.getColumna()==0) {  // Hay salto de línea en medio de la línea
							panelG.drawLine( (int)(ultimoCentro.x-x), (int)(ultimoCentro.y-y), (int)(ultimoCentro.x-x+lado), (int)(ultimoCentro.y-y) );
							PanelGrafico.dibujaFlecha( panelG, colorBorde, ElementoDatausto.STROKE_2, 
									(int)(centro.x-x-lado), (int)(centro.y-y), (int)(centro.x-x), (int)(centro.y-y) ); 
						} else {
							PanelGrafico.dibujaFlecha( panelG, colorBorde, ElementoDatausto.STROKE_2, 
								(int)(ultimoCentro.x-x), (int)(ultimoCentro.y-y), (int)(centro.x-x), (int)(centro.y-y) ); 
						}
					} else {
						if (casilla!=null && ultimaCasilla!=null && ultimaCasilla.getFila()==casilla.getFila()-1 && ultimaCasilla.getColumna()==tablero.getNumColumnas()-1 && casilla.getColumna()==0) {  // Hay salto de línea en medio de la línea
							panelG.drawLine( (int)(ultimoCentro.x-x), (int)(ultimoCentro.y-y), (int)(ultimoCentro.x-x+lado), (int)(ultimoCentro.y-y) );
							panelG.drawLine( (int)(centro.x-x-lado), (int)(centro.y-y), (int)(centro.x-x), (int)(centro.y-y) );
						} else {
							panelG.drawLine( (int)(ultimoCentro.x-x), (int)(ultimoCentro.y-y), (int)(centro.x-x), (int)(centro.y-y) );
						}
					}
				}
				ultimoCentro = centro;
				ultimaCasilla = casilla;
		    }
    	} else {
    		int anchoHex = (int) Math.ceil( Math.sqrt(3.0) * lado / 2.0 ) * 2;
		    for (int i=0; i<numHexagonos; i++) {
			    Point[] vert = Hexagono.calculaVerticesHexagono( anchoHex*i, 0, lado, 0 );  // Calcula los vértices centrados en 0,0 para dibujar
				if (colorRelleno!=null) { // Se pinta el relleno si no es transparente
					panelG.setColor( colorRelleno );
					panelG.fillPolygon( new int[] { vert[0].x, vert[1].x, vert[2].x, vert[3].x, vert[4].x, vert[5].x }, 
							new int[] { vert[0].y, vert[1].y, vert[2].y, vert[3].y, vert[4].y, vert[5].y }, 6 );
				}
				// Se pinta el borde del hexágono
				panelG.setColor( colorBorde );
				panelG.drawPolygon( new int[] { vert[0].x, vert[1].x, vert[2].x, vert[3].x, vert[4].x, vert[5].x }, 
						new int[] { vert[0].y, vert[1].y, vert[2].y, vert[3].y, vert[4].y, vert[5].y }, 6 );
				// Pintar la línea/flecha entre hexágonos
				if (i<numHexagonos-1) {
					panelG.setColor( colorBorde );
					if (i==numHexagonos-1) {  // Final de flecha
						PanelGrafico.dibujaFlecha( panelG, colorBorde, ElementoDatausto.STROKE_2, anchoHex*i, 0, anchoHex*(i+1), 0 );
					} else {
						panelG.drawLine( anchoHex*i, 0, anchoHex*(i+1), 0 ); 
					}
				}
		    }
    	}
		panelG.setTransform( new AffineTransform() );  // Restaurar graphics  (sin rotación ni traslación)
	    if (invertido) panelG.setXORMode(Color.white);  // Restaura modo xor
		panelG.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 1.0f ) ); // Restaura opacidad
	}
		private ArrayList<Point> getListaCentros( ArrayList<Casilla> lCasillas, double rotacionCalculo ) {
	    	ArrayList<Point> lCentros = new ArrayList<>();  // Lista de centros a calcular
		    if (lCasillas==null || lCasillas.size()==0) {  // Calcular centros sin casilla
		    	double xCentro = x;
		    	double yCentro = y;
	    		int anchoHex = (int) Math.ceil( Math.sqrt(3.0) * lado / 2.0 ) * 2;
		    	for (int i=0; i<numHexagonos; i++) {
		    		Point p = new Point();
		    		p.setLocation( xCentro, yCentro );
		    		lCentros.add( p );
		    		xCentro += anchoHex * Math.cos(rotacionCalculo);  // TODO Comprobar y quitar log
		    		yCentro += anchoHex * Math.sin(rotacionCalculo);  // 
		    	}
		    } else {  // Calcular centros de casillas
		    	for (Casilla casilla : lCasillas) {
		    		lCentros.add( tablero.getCentroDeCasilla( casilla ) );
		    	}
		    }
		    return lCentros;
		}

	@Override
	public boolean enPunto( Point p ) {
    	ArrayList<Point> lCentros = getListaCentros(getCasillas(),rotacion);  // Lista de centros de casillas a comprobar
	    for (Point centro : lCentros) {
		    Point[] vert = Hexagono.calculaVerticesHexagono( centro.x, centro.y, lado, (tablero==null ? rotacion : 0.0) );  // Calcula los vértices centrados en 0,0 para dibujar
		    boolean puedeEstarDentro = true;
		    for (int i=0; i<6; i++) {
				int val = Geometria.orientacion( vert[i], vert[(i+1)%6], p );
		    	if (val!=1) { puedeEstarDentro = false; break; }  // Si el punto está a la derecha de todos los lados está dentro; si no, está fuera
		    }
		    if (puedeEstarDentro) return true;
	    }
	    return false;
	}
	
	@Override
	public void escala( double escala ) {
		lado *= lado;
	}

	@Override
	public ElementoDatausto crearNuevo( PanelGrafico panel, Point p1, Point p2 ) {
		if (p1==null || p2==null) return null;  // Para crear hacen falta dos puntos de referencia (centro inicial y punto de progreso de línea de hexágonos)
		LineaHexagonos ret = null;
		if (tablero!=null) {  // Si se crea en tablero se fuerza a las casillas
			Casilla cIni = tablero.getCasillaDe( p1 );
			Casilla cFin = tablero.getCasillaVirtualDe( p2 );
			if (cIni==null || cFin==null) return null;  // No se puede crear nada si las casillas son incorrectas
			if (cFin.getFila()<cIni.getFila() || (cIni.getFila()==cFin.getFila() && cIni.getColumna()>=cFin.getColumna())) return null;  // No se puede crear si la casilla fin no es posterior a la ini
			int distancia = tablero.getDistanciaLineal( cIni, cFin );
			if (distancia<0) return null;  // No se puede crear nada si la casilla 2 está antes de la 1
			p1 = tablero.getCentroDeCasilla( cIni );  // Recoloca el punto en el centro exacto de la casilla
			ret = new LineaHexagonos( panel, p1.getX(), p1.getY(), Datausto.RADIO_HEXAGONO, distancia+1 );
			ret.setTablero( tablero );
		} else {  // Si se crea sin tablero se crea libremente
			double distancia = Geometria.distancia( p1, p2 );
    		int anchoHex = (int) Math.ceil( Math.sqrt(3.0) * lado / 2.0 ) * 2;
    		int numHex = (int) Math.ceil( distancia / anchoHex );
			ret = new LineaHexagonos( panel, p1.getX(), p1.getY(), lado, numHex );
			ret.setRotacion( Geometria.getAngulo( p1, p2 ) );
		}
		return ret;
	}
	
	@Override
	public ElementoDatausto crearNuevo( PanelGrafico panel, String datos ) {
		String[] trozos = datos.split(";");  // Parte la línea con el carácter separador punto y coma (ver método toLinea())
		if (trozos.length<8 || !trozos[0].equals("LISHEX")) return null; // Si no es gráfico válido se devuelve null
		try {
			double x = Double.parseDouble(trozos[1]);
			double y = Double.parseDouble(trozos[2]);
			double lado = Double.parseDouble(trozos[3]);
			int num = Integer.parseInt(trozos[4]);
			LineaHexagonos ret = new LineaHexagonos( panel, x, y, lado, num );
			double rot = Double.parseDouble(trozos[5]);
			ret.setRotacion( rot );
			ret.setColor( new Color( Integer.parseInt(trozos[6]) ) );
			if (trozos[7].equals("null")) {
				ret.setColorRelleno( null );
			} else {
				ret.setColorRelleno( new Color( Integer.parseInt(trozos[7]) ) );
			}
			return ret;
		} catch (Exception e) {
			return null;  // Cualquier error en el string se devuelve null
		}
	}
	
	@Override
	public String toLinea() {
		// Línea de objeto con separador punto y coma
		return "LISHEX;" + x + ";" + y + ";" + lado + ";" + numHexagonos + ";" + rotacion + ";" + colorBorde.getRGB() + ";" + (colorRelleno==null?"null":colorRelleno.getRGB());
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
			return "Línea Hexágonos";
		} else {
			return "Línea Hexágonos " + numHexagonos + "*(" + x + "," + y + ") lado (" + lado + ") ";
		}
	}

	// Interfaz EnTablero

	@Override
	public ArrayList<Casilla> getCasillas() {
		if (listaCasillas==null) {
			if (tablero==null) return null;  // Las que no tienen tablero
			Casilla casilla = tablero.getCasillaDe( new Point( (int)x, (int)y ) );
			if (casilla==null) return null;  // Las que no están en el tablero
			listaCasillas = new ArrayList<>();
			listaCasillas.add( casilla );
	    	double xCentro = x;
	    	double yCentro = y;
    		int anchoHex = (int) Math.ceil( Math.sqrt(3.0) * lado / 2.0 ) * 2;
	    	for (int i=1; i<numHexagonos; i++) {
	    		Point p = new Point();
	    		xCentro += anchoHex * Math.cos(rotacion);
	    		yCentro += anchoHex * Math.sin(rotacion); 
	    		p.setLocation( xCentro, yCentro );
				casilla = tablero.getCasillaVirtualDe( new Point( (int)xCentro, (int)yCentro ) );
				while (casilla.getColumna() >= tablero.getNumColumnas()) {
					if (rotacion>0.01 || rotacion<-0.01) return null;  // Las inclinadas no pueden salirse del tablero
					casilla.setColumna( casilla.getColumna() - tablero.getNumColumnas() );
					casilla.setFila( casilla.getFila() + 1 );
				}
				if (casilla==null || casilla.getFila()>=tablero.getNumFilas()) return null;  // Las que están fuera de tablero
				listaCasillas.add( casilla );
	    	}
		} else if (tablero==null) {
			listaCasillas=null;
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
	
	// Interfaz Rotable

	@Override
	public void setRotacion(double angulo) {
		rotacion = angulo;
		while (rotacion>=Math.PI*2) rotacion -= Math.PI*2;  // Poner el ángulo en el intervalo [0, 2pi)
		while (rotacion<0) rotacion += Math.PI*2;
		listaCasillas = null; // Cancela las casillas porque al rotar pueden cambiar (se recalcularán cuando se necesiten)
	}

	@Override
	public void addRotacion(double angulo) {
		rotacion += angulo;
		while (rotacion>=Math.PI*2) rotacion -= Math.PI*2;  // Poner el ángulo en el intervalo [0, 2pi)
		while (rotacion<0) rotacion += Math.PI*2;
		listaCasillas = null; // Cancela las casillas porque al rotar pueden cambiar (se recalcularán cuando se necesiten)
	}

	@Override
	public double getRotacion() {
		return rotacion;
	}

}
