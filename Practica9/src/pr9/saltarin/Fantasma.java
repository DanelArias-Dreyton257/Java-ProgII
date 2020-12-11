package pr9.saltarin;

import javax.swing.ImageIcon;

public class Fantasma extends ObjetoJuego {
	public static int ANCHO_FANT = 80;
	public static int ALTO_FANT = 80;
	public static double VEL_ROTACION = Math.PI / 3;  // (rds/sg) Un sexto de vuelta por segundo
	
	private double rot = 0;
	private long tiempoCreacion = 0;
	
	/** Construye un objeto Fantasma
	 * @param tiempoCreacion	Tiempo de creaci�n del fantasma
	 */
	public Fantasma( long tiempoCreacion ) {
		super( new JLabelEscalableRotable( new ImageIcon( Fantasma.class.getResource("ud-ghost.png") ), ANCHO_FANT, ALTO_FANT, 0 )); 
		this.tiempoCreacion = tiempoCreacion;
	}
	
	public long getTiempoCreacion() { return tiempoCreacion; }
	
	// Los fantasmas no se mueven, solo giran
	void actualizaFisica(double tiempo) {
		rot += (VEL_ROTACION * tiempo / 1000);
		grafico.setRotacion( rot );
	}
}
