package tema4.ejercicios.acertijo;

import tema3.VentanaGrafica;

public class Col extends Circulo implements Comible{
	
	//================= Parte static
	
	/** Color del círculo */
	public static final int RADIO = 60;
	/** Ruta de la imagen de la col */
	public static final String IMAGEN = "/tema4/ejercicios/acertijo/img/col.png";
	
	//================= Parte no static

	int radio;  // Radio en píxels
	
	/** Crea una nueva col
	 * @param x	Coordenada x
	 * @param y	Coordenada y
	 * @param ventana	Ventana de la col
	 */
	public Col(int x, int y, VentanaGrafica ventana) {
		super(x, y,RADIO, IMAGEN, ventana);
		this.radio = RADIO;
	}
	
	
	/** Comprueba si el objeto puede ser comido por otro
	 * @param o	objeto a comprobar
	 * @return	true si ese objeto o puede comer al objeto en curso, false en caso contrario
	 */
	public boolean puedeSerComidoPor(ObjetoAcertijo o) {
		return o instanceof Oveja;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Col)) return false;
		Col o2 = (Col) obj;
		return super.equals(obj) && radio==o2.radio;
	}
	
	@Override
	public String toString() {
		return "Col " + super.toString();
	}

}
