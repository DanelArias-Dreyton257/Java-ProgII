package examen202006ext;

import examen202006ext.data.Circulo;
import examen202006ext.data.Hexagono;
import examen202006ext.data.Imagen;
import examen202006ext.data.LineaHexagonos;
import examen202006ext.gui.VentanaEditorNivel;

public class Datausto {
	
	// Constantes generales
	
	/** Carpeta de ficheros de datos */
	public static final String CARPETA_FICHEROS = "data/examen202006ext/";
	/** Carpeta de imágenes (relativa a la carpeta de proyecto) */
	public static String CARPETA_IMAGENES = "data/examen202006ext/img/";
	/** Extensión de ficheros de datos */
	public static final String EXTENSION_FICHERO = ".datausto";
	/** Tamaño de las fichas de juego en píxels */
	public static final int RADIO_HEXAGONO = 20;
	/** Tamaño de las fichas de imagen en píxels */
	public static final int DIAM_IMAGEN = 38;
	/** Anchura de la zona auxiliar */
	public static final int ANCHO_AUXILIAR = 100;

	// Métodos de utilidad generales
	
	/** Informa si el ángulo de giro es viable en un tablero hexagonal (solo múltiplos de PI/3 y valores muy aproximados)
	 * @param angulo	Ángulo a chequear
	 * @return	true si el ángulo es viable (0º, 60º, etc.), false en caso contrario
	 */
	public static boolean anguloGiroPosible( double angulo ) {
		double viable = angulo;
		while (viable>Math.PI/3.0) viable -= Math.PI/3.0;
		return Math.abs(viable) < 0.1 || Math.abs(Math.PI/3.0-viable) <  0.1; // Ángulo con precisión cercana al viable
	}
	
	/** Devuelve el ángulo de giro exacto correspondiente a un ángulo de giro dado
	 * @param angulo	Ángulo a convertir
	 * @return	Ángulo exacto más cercano al dado
	 */
	public static double anguloGiroExacto( double angulo ) {
		double viable = angulo / (Math.PI/3.0);
		return Math.round(viable) * (Math.PI/3.0);  // Ángulo exacto redondeado al más cercano múltiplo de PI/3
	}
	
		private static VentanaEditorNivel v;
	/** Método main de ejecución del editor de Datausto
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		// Inicialización de gráficos disponibles
		Hexagono.init();
		LineaHexagonos.init();
		Circulo.init();
		Imagen.init();
		// Inicializar y lanzar ventana
		v = new VentanaEditorNivel( 1000, 800 );
		v.activa();
	}
	
}
