package pr9.saltarin;

public class ObjetoJuego {
	protected double x,y,velX,velY;
	protected int ancho, alto;
	protected JLabelEscalableRotable grafico;
	protected static final double G = 980.0;  // px/sg2
	protected int radioColision = 0;  // Tama�o radio de colisi�n (si es 0 es que no colisiona)
	
	/** Constructor de objeto de juego
	 * @param grafico	Label gr�fico asociado al objeto
	 */
	public ObjetoJuego(JLabelEscalableRotable grafico) { 
		this.grafico = grafico; 
		this.ancho = grafico.getWidth();
		this.alto = grafico.getHeight();
	}

	/** Pone el radio de colisi�n del objeto de juego
	 * @param radio	Pixels de radio de colisi�n del objeto (si <= 0 no colisiona)
	 */
	public void setRadioColision( int radio ) {
		radioColision = radio;
		// Descomentar si se quieren ver los radios de colisi�n
		// grafico.dibujaRadioColision( radio );
	}
	
	/** Chequea la colisi�n entre dos objetos de juego
	 * @param o2	Segundo objeto de juego 
	 * @return	true si this y o2 colisionan (seg�n sus radios de colisi�n, suponiendo esferas virtuales)
	 */
	public boolean colisionaCon( ObjetoJuego o2 ) {
		if (radioColision<=0 || o2.radioColision<0) return false;
		double centro1x = x + ancho/2.0;
		double centro1y = y + alto/2.0;
		double centro2x = o2.x + o2.ancho/2.0;
		double centro2y = o2.y + o2.alto/2.0;
		double distRadios = Math.sqrt( (centro2x-centro1x)*(centro2x-centro1x) + 
				(centro2y-centro1y)*(centro2y-centro1y) );
		return distRadios < radioColision + o2.radioColision;
	}
	
	/** Modifica la posici�n del objeto en el espacio l�gico de juego
	 * (no mueve en la ventana - ver m�todo {@link #actualizaGrafico()})
	 * @param x	Nueva posici�n x
	 * @param y	Nueva posici�n y
	 */
	void setPos( double x, double y ) { 
		this.x = x; this.y = y; 
	}
	
	/** Modifica la velocidad del objeto
	 * @param velX	Nueva velocidad x (p�xels/sg)
	 * @param velY	Nueva velocidad y (p�xels/sg)
	 */
	void setVel( double velX, double velY ) { 
		this.velX = velX; this.velY = velY; 
	}
	
	/** Actualiza las f�sicas mec�nicas del objeto de juego (velocidad en funci�n de la gravedad,
	 * posici�n en funci�n de la velocidad), de acuerdo al tiempo transcurrido en segundos
	 * @param tiempo	Tiempo transcurrido (segundos)
	 */
	void actualizaFisica(double tiempo) {
		velY += (G * tiempo);
		setPos(x + velX * tiempo, y + velY * tiempo);
	}
	
	/** Actualiza la posici�n del gr�fico en la ventana de acuerdo a la posici�n actual
	 */
	void actualizaGrafico() { 
		grafico.setLocation( (int)Math.round(this.x), (int)Math.round(this.y) );
	}
	
	public double getX() { return x; }
	public double getY() { return y; }
	public double getVelX() { return velX; }
	public double getVelY() { return velY; }
	public int getAncho() { return ancho; }
	public int getAlto() { return alto; }
	public JLabelEscalableRotable getLabel() { return grafico; }
	
}
