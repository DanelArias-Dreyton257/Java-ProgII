import java.awt.Color;
import java.util.Random;
/**
 * 
 * @author Danel
 *
 */
public class VentanaGrafica2 extends VentanaGrafica{

	public VentanaGrafica2(int anchura, int altura, String titulo) {
		super(anchura, altura, titulo);
	}
	/**
	 * Dibuja una linea "curva"con lineas rectas//TODO
	 * @param x coordenada x de origen
	 * @param y coordenada y de origen
	 * @param x2 coordenada x de destino
	 * @param y2 coordenada y de destino
	 * @param grosor float que indica el grosor
	 * @param color objeto color
	 */
	public void dibujaLineaCurva(double x, double y, double x2, double y2, float grosor, Color color) {
		Random r= new Random();
		double difX=x2-x;
		double difY=y2-y;
		double incrX=0;
		double incrY=0;
		incrX=r.nextDouble();
		incrY=r.nextDouble();
		dibujaLinea(x,y,x+difX*incrX,y+difY*incrY,grosor,color);
		dibujaLinea(x+difX*incrX,y+difY*incrY,x2,y2,grosor,color);
			
	}
}
