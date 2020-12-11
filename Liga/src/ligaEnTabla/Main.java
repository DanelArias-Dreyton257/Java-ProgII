package ligaEnTabla;
import ligaEnTabla.datos.*;
import ligaEnTabla.visual.*;

/**
 * 
 * @author Danel
 *
 */
public class Main {
	private static final int TMP_ESPERA = 250;
	public static void main(String[] args) {
		//Inicializo los objetos
		Clasificacion[] clasifs = {new Clasificacion(Liga.LigaFutbol),new Clasificacion(Liga.LigaBaloncesto),new Clasificacion(Liga.LigaFMS)};
		Tabla[] tablas = {new TablaFutbol(clasifs[0]), new TablaBaloncesto(clasifs[1]), new TablaFMS(clasifs[2])};
		VentanaGrafica[] vents = {new VentanaGrafica( 840, 580, "Clasificación de liga Futbol" ),
								  new VentanaGrafica( 800, 530, "Clasificación de liga Baloncesto" ),
								  new VentanaGrafica( 680, 320, "Clasificación de liga FMS" )};
		//Recorro todas las clasificaciones y voy dibujando las tablas jornada a jornada
		for(int i=0;i<clasifs.length;i++) {
			for(int j=0;j<=clasifs[i].getLiga().getJornadas();j++) {
				vents[i].borra();
				clasifs[i].creaArrayPartidos();
				clasifs[i].creaArrayEquipos();
				clasifs[i].calcPuntos(j);
				System.out.println( "Equipos:" );
				clasifs[i].visualizaEquipos();
				clasifs[i].ordenaEquiposPorPuntos();
				System.out.println( "Clasificación:" );
				clasifs[i].visualizaEquipos();
				tablas[i].dibujaCompleta( vents[i] );
				vents[i].setMensaje("Clasificacion de la jornada " +j);
				vents[i].espera(TMP_ESPERA);
			}
		}
	}
}
