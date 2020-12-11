package pr9.saltarin;


import javax.swing.ImageIcon;

public class Sonic extends ObjetoJuego {
	private static double COEF_REBOTE = 0.8;  // Coeficiente de restituci�n de sonic
	private static int ANCHO_SONIC = 200;
	private static int ALTO_SONIC = 200;
	private static int SALTO_SONIC = 1000; // px/seg
	 	// Rebota con 80% de restituci�n
	private static final String AUDIO_SALTO = "boing.wav";
	
	/** Construye un objeto Sonic
	 * @param ancho	p�xels de ancho
	 * @param alto p�xels de alto
	 */
	public Sonic( int ancho, int alto ) {
		super( new JLabelEscalableRotable( new ImageIcon( Sonic.class.getResource("sonic.png") ), ANCHO_SONIC, ALTO_SONIC, 0 )); 
	}
	
	/** L�mites de movimiento del personaje (en bordes y en movimiento hacia el rat�n)
	 */
	public void limitesMovimiento( int xParadaSonic ) {
		if (y > JuegoSaltarin.LIM_JUEGO_Y) {  // No pasar del suelo y rebotar
			y = JuegoSaltarin.LIM_JUEGO_Y;
			velY = -velY * COEF_REBOTE; 
		}
		if (x<0) { x = 0; velX = +velX; }  // Rebota en la izquierda
		if (x>JuegoSaltarin.LIM_JUEGO_X) {  // Rebota en la derecha
			x = JuegoSaltarin.LIM_JUEGO_X; velX = -velX; 
		}
		if (Math.abs(x+ANCHO_SONIC/2-xParadaSonic)<2) velX = 0;  // Si est� en el sitio de parada, velocidad x = 0
	}
	
	/** Intenta un salto de sonic
	 */
	public void intentaSalto() {
		if (y >= JuegoSaltarin.LIM_JUEGO_Y-25) {  // Si est� en el suelo o casi y se salta
			velY = -SALTO_SONIC;
			Audio.lanzaAudioEnHilo(AUDIO_SALTO);
		}
	}
	
}
