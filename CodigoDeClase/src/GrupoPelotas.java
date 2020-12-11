import java.util.Random;
import java.awt.Color;
import java.awt.Font;

public class GrupoPelotas {
	
	private Pelota[] grupo;
	/**
	 * Waits and gets a "got interrupted" message if interrupted 
	 * @param millis Milliseconds
	 */
	public static void wait(int millis) {try {
	    Thread.sleep(millis);
	} catch(InterruptedException e) {
	    System.out.println("got interrupted!");
	}
		
	}
	/**
	 * Creates random Balls and stores them in an array
	 * @param numPelotas Number of Balls to draw
	 * @param color Color of Balls
	 */
	GrupoPelotas(int numPelotas,Color color){
		
		grupo = new Pelota[numPelotas+100];
		
		for (int i=0; i<numPelotas; i++) {
			
			Random randomGenerator = new Random();
			
			int radio = randomGenerator.nextInt(25) + 5;
			
			Pelota nuevaPelota=new Pelota(radio, 0, 0, color);
			
			grupo[i]=nuevaPelota;
		}
	}
	/**
	 * Draws the balls stored in the array in a random location of the window
	 * unless the ball already has a x and y defined
	 * @param v Ventana Grafica
	 */
	public void dibujarPelotas(VentanaGrafica v ) {
		int rangeMaxX=v.getAnchura();
		int rangeMaxY=v.getAnchura();
		int rangeMin=10;
		Random randomGenerator = new Random();

		for (Pelota i:grupo) {
			wait(50);
			if (i.getX()==0 && i.getY()==0){
				i.setX(rangeMin + (rangeMaxX - rangeMin) * randomGenerator.nextDouble());
				i.setY(rangeMin + (rangeMaxY - rangeMin) * randomGenerator.nextDouble());
			}
			if (i!=null) {i.dibuja(v);}
		}
	}
	/**
	 * Erase the balls stored in the array
	 * @param v Ventana Grafica
	 */
	public void borrarPelotas(VentanaGrafica v) {
		for (Pelota i:grupo) {
			wait(50);
			if (i!=null) {i.dibuja(v);}
		}
	}
	//TODO
	/**
	 * Inserts a new ball in a specific index
	 * @param ball new ball
	 * @param index
	 */
	public void insertarPelota(Pelota ball,int index) {
		
	}
	/**
	 * Inserts a new ball at the end
	 * @param ball new ball
	 */
	public void insertarPelota(Pelota ball) {
		grupo[grupo.length]=ball;
	}
	/**
	 * Erase from the array the ball indicated by the index
	 * @param index
	 */
	public void destruyePelota(int index) {
		grupo[index]=null;
		for (int i=index;i<grupo.length-1;i++) {
			Pelota current=grupo[i];
			Pelota next=grupo[i+1];
			grupo[i]=next;
			grupo[i+1]=current;
		}
	}
	/**
	 *gets the array with the balls
	 * @return array of group of balls
	 */
	public Pelota[] getGrupo() {return grupo;}

	public static void main (String[] args) {
		GrupoPelotas g1 = new GrupoPelotas(100, Color.BLUE);
		VentanaGrafica v1 = new VentanaGrafica(1000, 1000, "Grupo Pelotas");
		g1.destruyePelota(3);
		g1.dibujarPelotas(v1);
		wait(200);
		g1.borrarPelotas(v1);
		Font Arial= new Font("Arial", 23, 100);
		v1.dibujaTexto(v1.getAnchura()/4, v1.getAltura()/2, "FINISHED", Arial, Color.BLACK);
	}
	
	
}