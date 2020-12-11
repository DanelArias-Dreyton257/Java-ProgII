import java.awt.Color;
import java.util.Random;
/**
 * 
 * @author Danel
 *
 */
public class Vertice extends GraphObject{
	private double x;
	private double y;
	private static final double RADIO=5;
	private static final int MARGEN=20;
	private boolean fixedCoor=false;
	/**
	 * Inicializa vertice con sus coordenadas
	 * @param x
	 * @param y
	 */
	Vertice(double x, double y){
		setCoor(x, y);
		fixedCoor=true;
	}
	/**
	 * Inicializa vertice con coordenadas aleatorias entre 0 y 500
	 */
	Vertice(){
		Random r = new Random();
		setCoor(r.nextInt(),r.nextInt());
		fixedCoor=false;
	}
	/**
	 * Dibuja el vertice como un punto negro en pantalla
	 * @param ven Objeto VentanaGrafica
	 */
	public void show(VentanaGrafica2 ven) {
		if (!fixedCoor) {
			Random r = new Random();
			setCoor(r.nextDouble()*(ven.getAnchura()-MARGEN)+MARGEN,r.nextDouble()*(ven.getAltura()-MARGEN)+MARGEN);
			fixedCoor=true;
		}
		ven.dibujaCirculo(x,y,RADIO,GROSOR,Color.BLACK,color);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	public void setCoor(double x, double y) {
		this.x=x;
		this.y=y;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Vertice)) return false;
		Vertice v=(Vertice)obj;
		double margen=0.001;
		return Math.abs(this.x-v.getX())<=margen && Math.abs(this.y-v.getY())<=margen;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "V: ["+this.x+", "+this.y+"]";
	}

}
