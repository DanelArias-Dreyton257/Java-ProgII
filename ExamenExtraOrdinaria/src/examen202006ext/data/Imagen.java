package examen202006ext.data;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import examen202006ext.utils.Geometria;
import examen202006ext.Datausto;
import examen202006ext.gui.PanelGrafico;

/** Tipo de gráfico imagen para el Datausto
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class Imagen extends ElementoDatausto implements Rotable, EnTablero {

	/** Inicialización estática de la clase Imagen (llamar a este método para inicializar el gráfico como disponible)
	 */
	public static void init() {
		ElementoDatausto.LISTA_ELEMENTOS_VISUALES.add( new Imagen( null ) );
	}

	protected double anchura = 0.0;
	protected double altura = 0.0;
	protected double rotacion = 0.0;
	protected String nombreImg = null;
	protected ArrayList<Casilla> listaCasillas = null;  // Lista de casillas que ocupa el elemento
	protected Tablero tablero;  // tablero del elemento
	
	/** Construye una imagen por defecto con centro (0,0), anchura 0 y altura 0, y nombre de imagen nulo
	 * @param panel	Panel de referencia donde pintarlo
	 */
	public Imagen( PanelGrafico panel ) {
		super( panel, 0.0, 0.0 );
	}
	
	/** Construye una imagen
	 * @param panel	Panel de referencia donde pintarla
	 * @param x	Coordenada x de centro
	 * @param y	Coordenada y de centro
	 * @param anchura	Anchura en píxels
	 * @param altura	Altura en píxels
	 */
	public Imagen( PanelGrafico panel, double x, double y, double anchura, double altura, String nombreImg ) {
		super( panel, x, y );  // Centro en el punto medio
		this.anchura = anchura;
		this.altura = altura;
		this.nombreImg = nombreImg;
	}
	
		// Variable local para guardar las imágenes y no recargarlas cada vez
		private static volatile HashMap<String,ImageIcon> recursosGraficos = new HashMap<>();
	
	@Override
	public void dibuja( float transp, boolean invertido ) {
		if (panel==null || nombreImg==null) return;  // No se pinta si no hay panel o no hay nombre de fichero
		ImageIcon ii = recursosGraficos.get( nombreImg );
		if (ii==null) {
			try {
				ii = new ImageIcon( Datausto.CARPETA_IMAGENES + nombreImg );
			} catch (NullPointerException e) {
				return;
			}
			recursosGraficos.put( nombreImg, ii );
		}
		Graphics2D panelG = panel.getGraphicsPers();  // Objeto gráfico donde pintar
		panelG.translate( x, y );   // Incorpora traslación y rotación del rectángulo
		panelG.rotate( rotacion );
		panelG.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR); // Configuraciones para mejor calidad del gráfico
		panelG.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		panelG.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
		panelG.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, transp ) ); // Modifica transparencia
	    if (invertido) { int valInv = (int)(transp*255); Color newColor = new Color( valInv, valInv, valInv, 0 ); panelG.setXORMode(newColor); }  // Modo xor cambia los colores de pintado para invertirlos
		// Se pinta la imagen
        panelG.drawImage( ii.getImage(), (int)Math.round(-anchura/2.0), (int)Math.round(-altura/2.0), (int)Math.round(anchura), (int)Math.round(altura), null);  // Dibujar la imagen con el tamaño indicado
	    if (invertido) panelG.setXORMode(Color.white);  // Restaura modo xor
		panelG.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 1.0f ) ); // Restaura opacidad
		panelG.setTransform( new AffineTransform() );  // Restaurar graphics  (sin rotación ni traslación)
	}

	@Override
	public boolean enPunto( Point p ) {
		// Calcula el click en el hexágono equivalente al lado del menor de los datos anchura/altura
	    Point[] vert = Hexagono.calculaVerticesHexagono( x, y, Math.min(anchura, altura), 0.0 );
	    for (int i=0; i<6; i++) {
			int val = Geometria.orientacion( vert[i], vert[(i+1)%6], p );
	    	if (val!=1) return false;  // Si el punto está a la derecha de todos los lados está dentro; si no, está fuera
	    }
	    return true;
	}
	
	@Override
	public void escala( double escala ) {
		anchura *= escala;
		altura *= escala;
	}

	@Override
	public ElementoDatausto crearNuevo( PanelGrafico panel, Point p1, Point p2 ) {
		if (p1==null || p2==null) return null;  // Para crear una imagen hacen falta dos puntos de referencia: el centro y el que marca distancias
		File carpetaGrafs = new File( Datausto.CARPETA_IMAGENES );
		File[] ficheros = carpetaGrafs.listFiles();
		String[] nomsFichs = new String[ ficheros.length ];
		for (int i=0; i<ficheros.length; i++) nomsFichs[i] = ficheros[i].getName();
		String nomFic = (String) JOptionPane.showInputDialog( null, "Introduce fichero de imagen a crear:", "Selección de imagen", JOptionPane.QUESTION_MESSAGE, null, nomsFichs, null );
		if (nomFic==null) return null;
		Imagen ret = new Imagen( panel, p1.getX(), p1.getY(), p2.getX()-p1.getX(),  p2.getY()-p1.getY(), nomFic );  // p1 centro, p2 marca distancias con p1
		return ret;
	}
	
	@Override
	public ElementoDatausto crearNuevo( PanelGrafico panel, String datos ) {
		String[] trozos = datos.split(";");  // Parte la línea con el carácter separador punto y coma (ver método toLinea())
		if (trozos.length<6 || !trozos[0].equals("IMAGEN")) return null; // Si no es gráfico válido se devuelve null
		try {
			double x = Double.parseDouble(trozos[1]);
			double y = Double.parseDouble(trozos[2]);
			double anc = Double.parseDouble(trozos[3]);
			double alt = Double.parseDouble(trozos[4]);
			Imagen ret = new Imagen( panel, x, y, anc, alt, trozos[5] );
			ret.setRotacion( Double.parseDouble(trozos[6]) );
			return ret;
		} catch (Exception e) {
			return null;  // Cualquier error en el string se devuelve null
		}
	}
	
	@Override
	public String toLinea() {
		// Línea de objeto con separador punto y coma
		return "IMAGEN;" + x + ";" + y + ";" + anchura + ";" + altura + ";" + nombreImg + ";" + rotacion;
	}
	
	@Override
	public void setRotacion(double angulo) {
		this.rotacion = angulo;
	}

	@Override
	public void addRotacion(double angulo) {
		this.rotacion += angulo;
	}

	@Override
	public double getRotacion() {
		return rotacion;
	}

	@Override
	public String toString() {
		if (panel==null) {
			return "Imagen";
		} else {
			return "Imagen " + nombreImg + " (" + x + "," + y + ") tamaño (" + anchura + "," + altura + ") rotación " + rotacion;
		}
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

	// Interfaz EnTablero

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
	public int getNumCasillas() {
		if (listaCasillas==null) getCasillas();
		if (listaCasillas==null) return 0;
		return listaCasillas.size();
	}

}
