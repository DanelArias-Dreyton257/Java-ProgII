package examen202006ext.data;

import java.awt.BasicStroke;
import java.awt.Point;
import java.util.ArrayList;

import examen202006ext.gui.PanelGrafico;

/** Clase abstracta padre de los elementos visuales incluibles en el Datausto
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public abstract class ElementoDatausto {
	
	// Atributo general de todos los elementos visuales que se incrementa con cada nueva subclase
	public static final ArrayList<ElementoDatausto> LISTA_ELEMENTOS_VISUALES = new ArrayList<>();
	
	// Atributos estáticos de referencia y uso general
	public static final BasicStroke STROKE_1 = new BasicStroke( 1.0f );  // Pincel de 1 pixel
	public static final BasicStroke STROKE_2 = new BasicStroke( 2.0f );  // Pincel de 2 píxeles
	public static final BasicStroke STROKE_4 = new BasicStroke( 4.0f );  // Pincel de 4 píxeles

	// Atributos de todo elemento visual gráfico
	protected double x = 0.0;      // Coordenada x de referencia del gráfico
	protected double y = 0.0;      // Coordenada y de referencia del gráfico
	protected PanelGrafico panel;  // Panel donde se pinta el gráfico
	
	/** Construye un nuevo elemento visual gráfico
	 * @param panel	donde se debe pintar el gráfico
	 * @param x	Coordenada x de referencia del gráfico
	 * @param y	Coordenada y de referencia del gráfico
	 */
	public ElementoDatausto( PanelGrafico panel, double x, double y ) {
		super();
		setPanel( panel );
		setX( x );
		setY( y );
	}
	
	/** Cambia el panel de referencia de este gráfico
	 * @param graphics
	 */
	public void setPanel( PanelGrafico panel ) {
		this.panel = panel;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	/** Incremente la posición del gráfico
	 * @param incX	Incremento de coordenada x
	 * @param incY	Incremento de coordenada y
	 */
	public void inc( double incX, double incY ) {
		setX( x + incX );
		setY( y + incY );
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	/** Dibuja el elemento visual gráfico (opaco) en su panel de acuerdo a sus datos y tipo
	 */
	public void dibuja() {
		dibuja( 1.0f, false );
	}
	
	/** Dibuja el elemento visual gráfico en su panel de acuerdo a sus datos y tipo,
	 * con información adicional de transparencia e inversión de color
	 * @param transp	valor de transparencia entre 0.0f (completamente transparente) y 1.0f (completamente opaco)
	 * @param invertido	true si se quieren invertir los colores, false para dibujado normal
	 */
	public abstract void dibuja( float transp, boolean invertido );

	/** Determina si una coordenada de ventana está en el elemento visual gráfico
	 */
	public abstract boolean enPunto( Point p );

	/** Escala el gráfico
	 * @param escala	Porcentaje de escala (positivo): 1.0 para mantenerlo igual, 0.0 para reducirlo al máximo, 2.0 para duplicar el tamaño, etc. 
	 */
	public abstract void escala( double escala );

	/** Crea un nuevo elemento visual gráfico partiendo de uno existente, dados dos puntos de referencia para la creación
	 * @param panel	Panel de referencia
	 * @param p1	Punto de origen
	 * @param p2	Punto de desplazamiento
	 * @return	Nuevo elemento visual gráfico creado, null si no se crea ninguno
	 */
	public abstract ElementoDatausto crearNuevo( PanelGrafico panel, Point p1, Point p2 );
	
	/** Crea un nuevo elemento visual gráfico partiendo de la descripción de uno en un string de línea
	 * @param panel	Panel de referencia
	 * @param datos	Línea de datos del elemento visual en un string
	 * @return	Nuevo elemento visual creado, null si el string no es correcto con respecto al elemento esperado
	 */
	public abstract ElementoDatausto crearNuevo( PanelGrafico panel, String datos );
	
	/** Devuelve una línea de texto descriptiva del elemento visual gráfico
	 * @return	Línea de texto formateada de acuerdo a cada elemento con todos sus datos
	 */
	public abstract String toLinea();
	
}
