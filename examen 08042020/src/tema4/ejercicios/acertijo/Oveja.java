package tema4.ejercicios.acertijo;

import tema3.VentanaGrafica;

public class Oveja extends Circulo implements Comible{
	
	/** Tamaño visual de la oveja (radio del círculo) */
	public static final int RADIO = 60;
	/** Ruta de la imagen de la oveja */
	public static final String IMAGEN = "/tema4/ejercicios/acertijo/img/oveja.png";

	
	/** Crea una nueva oveja
	 * @param x	Coordenada x
	 * @param y	Coordenada y
	 * @param ventana	Ventana de la oveja
	 */
	public Oveja(int x, int y, VentanaGrafica ventana) {
		super(x, y,RADIO,IMAGEN, ventana);
	}
	
	
	/** Comprueba si el objeto puede ser comido por otro
	 * @param o	objeto a comprobar
	 * @return	true si ese objeto o puede comer al objeto en curso, false en caso contrario
	 */
	public boolean puedeSerComidoPor(ObjetoAcertijo o) {
		return o instanceof Lobo;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Oveja)) return false;
		Oveja o2 = (Oveja) obj;
		return super.equals(obj) && radio==o2.radio;
	}
	
	@Override
	public String toString() {
		return "Oveja " + super.toString();
	}

}
