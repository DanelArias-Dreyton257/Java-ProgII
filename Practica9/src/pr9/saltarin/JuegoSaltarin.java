package pr9.saltarin;

import java.util.ArrayList;

public class JuegoSaltarin {

	private boolean sigueJuego = true;
	private boolean pulsadoSalto = false;
	private boolean pausa = false;
	private VentanaSaltarin vSaltarin;
	
	public static int VEL_MAXIMA_SONIC = 300;
	public static int INICIO_SONIC_X = 0;
	public static int INICIO_SONIC_Y = 500;
	public static int LIM_JUEGO_X = 750;
	public static int LIM_JUEGO_Y = 560;
	public static int VENT_ANCHO = 1000;
	public static int VENT_ALTO = 800;
	public static int TIEMPO_ENTRE_FANTASMAS = 1000; // msgs
	private static final String AUDIO_CHOQUE = "smash.wav";
	
	/** Crea una instancia de juego
	 */
	public JuegoSaltarin() {
	}
	
	/** Asocia al juego la ventana indicada
	 * @param vSaltarin	Ventana en la que se desarrolla el juego
	 */
	public void setVentana( VentanaSaltarin vSaltarin ) {
		this.vSaltarin = vSaltarin;
	}
	
	/** Activa/desactiva la pausa del juego
	 * @param pausa	true para pausar el juego, false para reactivarlo
	 */
	public void setPausa( boolean pausa ) { this.pausa = pausa; }
	public boolean getPausa() { return pausa; }
	
	/** Activa el control de salto del personaje
	 */
	public void activaSalto() { this.pulsadoSalto = true; }

		private int xParadaPersonaje = -1;
	/** Activa el movimiento del rat�n en el juego
	 * @param x	Coordenada x a la que se mueve el rat�n
	 * @param y	Coordenada y a la que se mueve el rat�n
	 */
	public void mueveRaton( int x, int y ) {
		if (sonic==null) return;
		xParadaPersonaje = x;
		sonic.velX = (x-100) - sonic.getX();
		if (sonic.velX > VEL_MAXIMA_SONIC) sonic.velX = VEL_MAXIMA_SONIC;
		else if (sonic.velX < -VEL_MAXIMA_SONIC) sonic.velX = -VEL_MAXIMA_SONIC;   // Limitar la velocidad horizontal
	}
		
	/** Provoca que el juego acabe
	 */
	public void acabaJuego() { this.sigueJuego = false; }

		Sonic sonic;
		ArrayList<Fantasma> fantasmas;
		private void initJuego() {
			sigueJuego = true;
			sonic = new Sonic(200, 200);
			sonic.setPos( INICIO_SONIC_X, INICIO_SONIC_Y );
			sonic.setVel( 0, 0 );
			sonic.setRadioColision( 70 );
			vSaltarin.getEscenario().removeAll();  // Vac�a el escenario
			vSaltarin.getEscenario().add( sonic.getLabel() );  // A�ade a sonic
			fantasmas = new ArrayList<Fantasma>();  // Lista de fantasmas
		}
		
	/** Bucle principal de juego
	 * Inicializa los objetos de juego y empieza un ciclo de partida
	 */
	public void bucleDejuego() {
		initJuego();
		// Bucle de juego:
		int msgEspera = 20;  // Tiempo de espera entre iteraciones de visualizaci�n del juego
		long milis = System.currentTimeMillis();
		long tiempoDeJuego = 0;
		long tiempoDeFantasmas = 0;
		while (sigueJuego) {  // Va rebotando con vel y y gravedad
			try { Thread.sleep(msgEspera); } catch (InterruptedException e) { }
			if (pausa) {
				milis = System.currentTimeMillis();
			} else {
				long difTiempo = (System.currentTimeMillis()-milis);
				tiempoDeJuego += difTiempo;
				tiempoDeFantasmas += difTiempo;
				milis = System.currentTimeMillis();
				// Actualiza sonic
				sonic.actualizaFisica(difTiempo / 1000.0);
				sonic.limitesMovimiento( xParadaPersonaje );
				if (pulsadoSalto) { sonic.intentaSalto(); pulsadoSalto = false; }
				sonic.actualizaGrafico();
				// Actualiza fantasmas
 				if (tiempoDeFantasmas > TIEMPO_ENTRE_FANTASMAS ) {  // Crear nuevo fantasma
 					tiempoDeFantasmas -= TIEMPO_ENTRE_FANTASMAS;
 					Fantasma f = new Fantasma( tiempoDeJuego );
 					f.setPos( Math.random()*LIM_JUEGO_X, 20 + Math.random()*50 );
 					f.setVel( 0, 0 );
 					f.setRadioColision( 35 );
 					fantasmas.add( f );
 					vSaltarin.getEscenario().add( f.getLabel() );
 				}
 				for (Fantasma f : fantasmas) {
 					f.actualizaFisica(difTiempo);
 					f.actualizaGrafico();
 				}
 				// Comprueba choques
 				for (int i=0; i<fantasmas.size(); i++) {
 					Fantasma f = fantasmas.get(i);
 					if (sonic.colisionaCon(f)) {
 						Audio.lanzaAudioEnHilo(AUDIO_CHOQUE);
 						fantasmas.remove( i );
 						i--;  // Disminuye la lista, mirar un elemento antes
 						vSaltarin.getEscenario().remove( f.getLabel() );
 					}
 				}
 				// Comprueba fin de juego (alg�n fantasma lleva m�s de 10 segundos)
 				for (Fantasma f : fantasmas) {
 					if (tiempoDeJuego - f.getTiempoCreacion() > 10000) {
 						// FINAL DE JUEGO!!!
 						sigueJuego = false;
 						break;
 					}
 				}
			}
		}
		vSaltarin.dispose();
	}
}
