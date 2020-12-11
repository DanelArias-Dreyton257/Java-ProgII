package tema4.ejercicios.acertijo;

import tema3.VentanaGrafica;

public class Lobo extends Circulo {
	
	/** Tamaño visual del lobo (radio del círculo) */
	public static final int RADIO = 60;
	/** Ruta de la imagen del lobo */
	public static final String IMAGEN = "/tema4/ejercicios/acertijo/img/lobo.png";
	
	
	/** Crea un nuevo lobo
	 * @param x	Coordenada x
	 * @param y	Coordenada y
	 * @param ventana	Ventana del lobo
	 */
	public Lobo(int x, int y, VentanaGrafica ventana) {
		super(x, y,RADIO,IMAGEN, ventana);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Lobo)) return false;
		Lobo o2 = (Lobo) obj;
		return super.equals(obj) && radio==o2.radio;
	}
	
	@Override
	public String toString() {
		return "Lobo " + super.toString();
	}

}
