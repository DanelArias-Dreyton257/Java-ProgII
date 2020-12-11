package tema4.ejercicios.acertijo;

import tema3.VentanaGrafica;

public class Pastor extends Circulo {
	
	//================= Parte static
	
	/** Color del círculo */
	/** Tamaño visual del pastor (radio del círculo) */
	public static final int RADIO = 75;
	/** Ruta de la imagen del pastor */
	public static final String IMAGEN = "/tema4/ejercicios/acertijo/img/pastor.png";
	
	/** Crea un nuevo pastor
	 * @param x	Coordenada x
	 * @param y	Coordenada y
	 * @param ventana	Ventana del pastor
	 */
	public Pastor(int x, int y, VentanaGrafica ventana ) {
		super(x, y, RADIO,IMAGEN,ventana);
		this.radio = RADIO;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Pastor)) return false;
		Pastor o2 = (Pastor) obj;
		return super.equals(obj) && radio==o2.radio;
	}
	
	@Override
	public String toString() {
		return "Pastor " + super.toString();
	}


}
