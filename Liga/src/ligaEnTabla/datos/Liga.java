package ligaEnTabla.datos;
/**
 * 
 * @author Danel
 *
 */
public class Liga {
	private String equipoL[][];
	private String equipoV[][];
	private int puntosL[][];
	private int puntosV[][];
	
	public static Liga LigaFutbol;
	public static Liga LigaBaloncesto;
	public static Liga LigaFMS;
	
	//incializo los 3 objetos Liga
	static {
		initFutbol();
		initBaloncesto();
		initFMS();
	}
	/**
	 * Constructor del objeto Liga
	 * @param equipoL matriz de Strings con los nombres de los equipos locales
	 * @param equipoV matriz de Strings con los nombres de los equipos visitantes
	 * @param puntosL matriz de ints con los puntos de los equipos locales
	 * @param puntosV matriz de ints con los puntos de los equipos visitantes
	 */
	Liga(String[][] equipoL,String[][] equipoV,int[][] puntosL,int[][] puntosV){
		setEquipoL(equipoL);
		setEquipoV(equipoV);
		setPuntosL(puntosL);
		setPuntosV(puntosV);
	}
	
	/**
	 * Devuelve el nombre del equipo local en la jornada y partido indicados
	 * @param numJornada
	 * @param numPartido
	 * @return nombre del equipo local en esa jornada y ese partido
	 */
	public String getEquipoL(int numJornada,int numPartido) {
		return equipoL[numJornada][numPartido];
	}
	/**
	 * Devuelve el nombre del equipo visitante en la jornada y partido indicados
	 * @param numJornada
	 * @param numPartido
	 * @return nombre del equipo visitante en esa jornada y ese partido
	 */
	public String getEquipoV(int numJornada,int numPartido) {
		return equipoV[numJornada][numPartido];
	}
	/**
	 * Devuelve los puntos del equipo local en la jornada y partido indicados
	 * @param numJornada
	 * @param numPartido
	 * @return puntos del equipo local en esa jornada y ese partido
	 */
	public int getPuntosL(int numJornada,int numPartido) {
		return puntosL[numJornada][numPartido];
	}
	/**
	 * Devuelve los puntos del equipo visitante en la jornada y partido indicados
	 * @param numJornada
	 * @param numPartido
	 * @return puntos del equipo visitante en esa jornada y ese partido
	 */
	public int getPuntosV(int numJornada,int numPartido) {
		return puntosV[numJornada][numPartido];
	}
	/**
	 * Devuelve el número de jornadas jugadas
	 * @return num Jornadas jugadas
	 */
	public int getJornadas() {
		int jornadasJugadas=0;
		for (int i=0;i<equipoL.length;i++) {
			if (equipoL[i][0]==null) {
				break;
			}
			jornadasJugadas++;
		}
		return jornadasJugadas;
	}
	/**
	 * Inicializa el objeto LigaFutbol con los datos
	 */
	private static void initFutbol() {
		String equipoFutL[][] = new String[38][10];
		String equipoFutV[][] = new String[38][10];
		int puntosFutL[][] = new int [38][10];
		int puntosFutV[][] = new int [38][10];
		// Inicialización de resultados
		equipoFutL[0][0] = "Athletic"; equipoFutV[0][0] = "Barcelona"; puntosFutL[0][0] = 1; puntosFutV[0][0] = 0;
		equipoFutL[0][1] = "Celta"; equipoFutV[0][1] = "R.Madrid"; puntosFutL[0][1] = 1; puntosFutV[0][1] = 3;
		equipoFutL[0][2] = "Valencia"; equipoFutV[0][2] = "R.Sociedad"; puntosFutL[0][2] = 1; puntosFutV[0][2] = 1;
		equipoFutL[0][3] = "Mallorca"; equipoFutV[0][3] = "Eibar"; puntosFutL[0][3] = 2; puntosFutV[0][3] = 1;
		equipoFutL[0][4] = "Leganés"; equipoFutV[0][4] = "Osasuna"; puntosFutL[0][4] = 0; puntosFutV[0][4] = 1;
		equipoFutL[0][5] = "Villarreal"; equipoFutV[0][5] = "Granada"; puntosFutL[0][5] = 4; puntosFutV[0][5] = 4;
		equipoFutL[0][6] = "Alavés"; equipoFutV[0][6] = "Levante"; puntosFutL[0][6] = 1; puntosFutV[0][6] = 0;
		equipoFutL[0][7] = "Espanyol"; equipoFutV[0][7] = "Sevilla"; puntosFutL[0][7] = 0; puntosFutV[0][7] = 2;
		equipoFutL[0][8] = "Betis"; equipoFutV[0][8] = "Valladolid"; puntosFutL[0][8] = 1; puntosFutV[0][8] = 2;
		equipoFutL[0][9] = "Atlético"; equipoFutV[0][9] = "Getafe"; puntosFutL[0][9] = 1; puntosFutV[0][9] = 0;

		equipoFutL[1][0] = "Granada"; equipoFutV[1][0] = "Sevilla"; puntosFutL[1][0] = 0; puntosFutV[1][0] = 1;
		equipoFutL[1][1] = "Levante"; equipoFutV[1][1] = "Villarreal"; puntosFutL[1][1] = 2; puntosFutV[1][1] = 1;
		equipoFutL[1][2] = "Osasuna"; equipoFutV[1][2] = "Eibar"; puntosFutL[1][2] = 0; puntosFutV[1][2] = 0;
		equipoFutL[1][3] = "R.Madrid"; equipoFutV[1][3] = "Valladolid"; puntosFutL[1][3] = 1; puntosFutV[1][3] = 1;
		equipoFutL[1][4] = "Celta"; equipoFutV[1][4] = "Valencia"; puntosFutL[1][4] = 1; puntosFutV[1][4] = 0;
		equipoFutL[1][5] = "Getafe"; equipoFutV[1][5] = "Athletic"; puntosFutL[1][5] = 1; puntosFutV[1][5] = 1;
		equipoFutL[1][6] = "Alavés"; equipoFutV[1][6] = "Espanyol"; puntosFutL[1][6] = 0; puntosFutV[1][6] = 0;
		equipoFutL[1][7] = "Mallorca"; equipoFutV[1][7] = "R.Sociedad"; puntosFutL[1][7] = 0; puntosFutV[1][7] = 1;
		equipoFutL[1][8] = "Leganés"; equipoFutV[1][8] = "Atlético"; puntosFutL[1][8] = 0; puntosFutV[1][8] = 1;
		equipoFutL[1][9] = "Barcelona"; equipoFutV[1][9] = "Betis"; puntosFutL[1][9] = 5; puntosFutV[1][9] = 2;

		equipoFutL[2][0] = "Sevilla"; equipoFutV[2][0] = "Celta"; puntosFutL[2][0] = 1; puntosFutV[2][0] = 1;
		equipoFutL[2][1] = "Athletic"; equipoFutV[2][1] = "R.Sociedad"; puntosFutL[2][1] = 2; puntosFutV[2][1] = 0;
		equipoFutL[2][2] = "Osasuna"; equipoFutV[2][2] = "Barcelona"; puntosFutL[2][2] = 2; puntosFutV[2][2] = 2;
		equipoFutL[2][3] = "Getafe"; equipoFutV[2][3] = "Alavés"; puntosFutL[2][3] = 1; puntosFutV[2][3] = 1;
		equipoFutL[2][4] = "Levante"; equipoFutV[2][4] = "Valladolid"; puntosFutL[2][4] = 2; puntosFutV[2][4] = 0;
		equipoFutL[2][5] = "Betis"; equipoFutV[2][5] = "Leganés"; puntosFutL[2][5] = 2; puntosFutV[2][5] = 1;
		equipoFutL[2][6] = "Valencia"; equipoFutV[2][6] = "Mallorca"; puntosFutL[2][6] = 2; puntosFutV[2][6] = 0;
		equipoFutL[2][7] = "Atlético"; equipoFutV[2][7] = "Eibar"; puntosFutL[2][7] = 3; puntosFutV[2][7] = 2;
		equipoFutL[2][8] = "Espanyol"; equipoFutV[2][8] = "Granada"; puntosFutL[2][8] = 0; puntosFutV[2][8] = 3;
		equipoFutL[2][9] = "Villarreal"; equipoFutV[2][9] = "R.Madrid"; puntosFutL[2][9] = 2; puntosFutV[2][9] = 2;

		equipoFutL[3][0] = "Mallorca"; equipoFutV[3][0] = "Athletic"; puntosFutL[3][0] = 0; puntosFutV[3][0] = 0;
		equipoFutL[3][1] = "R.Madrid"; equipoFutV[3][1] = "Levante"; puntosFutL[3][1] = 3; puntosFutV[3][1] = 2;
		equipoFutL[3][2] = "Leganés"; equipoFutV[3][2] = "Villarreal"; puntosFutL[3][2] = 0; puntosFutV[3][2] = 3;
		equipoFutL[3][3] = "R.Sociedad"; equipoFutV[3][3] = "Atlético"; puntosFutL[3][3] = 2; puntosFutV[3][3] = 0;
		equipoFutL[3][4] = "Barcelona"; equipoFutV[3][4] = "Valencia"; puntosFutL[3][4] = 5; puntosFutV[3][4] = 2;
		equipoFutL[3][5] = "Eibar"; equipoFutV[3][5] = "Espanyol"; puntosFutL[3][5] = 1; puntosFutV[3][5] = 2;
		equipoFutL[3][6] = "Alavés"; equipoFutV[3][6] = "Sevilla"; puntosFutL[3][6] = 0; puntosFutV[3][6] = 1;
		equipoFutL[3][7] = "Celta"; equipoFutV[3][7] = "Granada"; puntosFutL[3][7] = 0; puntosFutV[3][7] = 2;
		equipoFutL[3][8] = "Valladolid"; equipoFutV[3][8] = "Osasuna"; puntosFutL[3][8] = 1; puntosFutV[3][8] = 1;
		equipoFutL[3][9] = "Betis"; equipoFutV[3][9] = "Getafe"; puntosFutL[3][9] = 1; puntosFutV[3][9] = 1;

		equipoFutL[4][0] = "Osasuna"; equipoFutV[4][0] = "Betis"; puntosFutL[4][0] = 0; puntosFutV[4][0] = 0;
		equipoFutL[4][1] = "Villarreal"; equipoFutV[4][1] = "Valladolid"; puntosFutL[4][1] = 2; puntosFutV[4][1] = 0;
		equipoFutL[4][2] = "Levante"; equipoFutV[4][2] = "Eibar"; puntosFutL[4][2] = 0; puntosFutV[4][2] = 0;
		equipoFutL[4][3] = "Atlético"; equipoFutV[4][3] = "Celta"; puntosFutL[4][3] = 0; puntosFutV[4][3] = 0;
		equipoFutL[4][4] = "Granada"; equipoFutV[4][4] = "Barcelona"; puntosFutL[4][4] = 2; puntosFutV[4][4] = 0;
		equipoFutL[4][5] = "Getafe"; equipoFutV[4][5] = "Mallorca"; puntosFutL[4][5] = 4; puntosFutV[4][5] = 2;
		equipoFutL[4][6] = "Espanyol"; equipoFutV[4][6] = "R.Sociedad"; puntosFutL[4][6] = 1; puntosFutV[4][6] = 3;
		equipoFutL[4][7] = "Valencia"; equipoFutV[4][7] = "Leganés"; puntosFutL[4][7] = 1; puntosFutV[4][7] = 1;
		equipoFutL[4][8] = "Athletic"; equipoFutV[4][8] = "Alavés"; puntosFutL[4][8] = 2; puntosFutV[4][8] = 0;
		equipoFutL[4][9] = "Sevilla"; equipoFutV[4][9] = "R.Madrid"; puntosFutL[4][9] = 0; puntosFutV[4][9] = 1;

		equipoFutL[5][0] = "Valladolid"; equipoFutV[5][0] = "Granada"; puntosFutL[5][0] = 1; puntosFutV[5][0] = 1;
		equipoFutL[5][1] = "Betis"; equipoFutV[5][1] = "Levante"; puntosFutL[5][1] = 3; puntosFutV[5][1] = 1;
		equipoFutL[5][2] = "Barcelona"; equipoFutV[5][2] = "Villarreal"; puntosFutL[5][2] = 2; puntosFutV[5][2] = 1;
		equipoFutL[5][3] = "Leganés"; equipoFutV[5][3] = "Athletic"; puntosFutL[5][3] = 1; puntosFutV[5][3] = 1;
		equipoFutL[5][4] = "Mallorca"; equipoFutV[5][4] = "Atlético"; puntosFutL[5][4] = 0; puntosFutV[5][4] = 2;
		equipoFutL[5][5] = "Valencia"; equipoFutV[5][5] = "Getafe"; puntosFutL[5][5] = 3; puntosFutV[5][5] = 3;
		equipoFutL[5][6] = "R.Madrid"; equipoFutV[5][6] = "Osasuna"; puntosFutL[5][6] = 2; puntosFutV[5][6] = 0;
		equipoFutL[5][7] = "Eibar"; equipoFutV[5][7] = "Sevilla"; puntosFutL[5][7] = 3; puntosFutV[5][7] = 2;
		equipoFutL[5][8] = "Celta"; equipoFutV[5][8] = "Espanyol"; puntosFutL[5][8] = 1; puntosFutV[5][8] = 1;
		equipoFutL[5][9] = "R.Sociedad"; equipoFutV[5][9] = "Alavés"; puntosFutL[5][9] = 3; puntosFutV[5][9] = 0;

		equipoFutL[6][0] = "Villarreal"; equipoFutV[6][0] = "Betis"; puntosFutL[6][0] = 5; puntosFutV[6][0] = 1;
		equipoFutL[6][1] = "Athletic"; equipoFutV[6][1] = "Valencia"; puntosFutL[6][1] = 0; puntosFutV[6][1] = 1;
		equipoFutL[6][2] = "Getafe"; equipoFutV[6][2] = "Barcelona"; puntosFutL[6][2] = 0; puntosFutV[6][2] = 2;
		equipoFutL[6][3] = "Granada"; equipoFutV[6][3] = "Leganés"; puntosFutL[6][3] = 1; puntosFutV[6][3] = 0;
		equipoFutL[6][4] = "Atlético"; equipoFutV[6][4] = "R.Madrid"; puntosFutL[6][4] = 0; puntosFutV[6][4] = 0;
		equipoFutL[6][5] = "Espanyol"; equipoFutV[6][5] = "Valladolid"; puntosFutL[6][5] = 0; puntosFutV[6][5] = 2;
		equipoFutL[6][6] = "Eibar"; equipoFutV[6][6] = "Celta"; puntosFutL[6][6] = 2; puntosFutV[6][6] = 0;
		equipoFutL[6][7] = "Alavés"; equipoFutV[6][7] = "Mallorca"; puntosFutL[6][7] = 2; puntosFutV[6][7] = 0;
		equipoFutL[6][8] = "Levante"; equipoFutV[6][8] = "Osasuna"; puntosFutL[6][8] = 1; puntosFutV[6][8] = 1;
		equipoFutL[6][9] = "Sevilla"; equipoFutV[6][9] = "R.Sociedad"; puntosFutL[6][9] = 3; puntosFutV[6][9] = 2;

		equipoFutL[7][0] = "Betis"; equipoFutV[7][0] = "Eibar"; puntosFutL[7][0] = 1; puntosFutV[7][0] = 1;
		equipoFutL[7][1] = "Leganés"; equipoFutV[7][1] = "Levante"; puntosFutL[7][1] = 1; puntosFutV[7][1] = 2;
		equipoFutL[7][2] = "R.Madrid"; equipoFutV[7][2] = "Granada"; puntosFutL[7][2] = 4; puntosFutV[7][2] = 2;
		equipoFutL[7][3] = "Valencia"; equipoFutV[7][3] = "Alavés"; puntosFutL[7][3] = 2; puntosFutV[7][3] = 1;
		equipoFutL[7][4] = "Osasuna"; equipoFutV[7][4] = "Villarreal"; puntosFutL[7][4] = 2; puntosFutV[7][4] = 1;
		equipoFutL[7][5] = "Mallorca"; equipoFutV[7][5] = "Espanyol"; puntosFutL[7][5] = 2; puntosFutV[7][5] = 0;
		equipoFutL[7][6] = "Celta"; equipoFutV[7][6] = "Athletic"; puntosFutL[7][6] = 1; puntosFutV[7][6] = 0;
		equipoFutL[7][7] = "Valladolid"; equipoFutV[7][7] = "Atlético"; puntosFutL[7][7] = 0; puntosFutV[7][7] = 0;
		equipoFutL[7][8] = "R.Sociedad"; equipoFutV[7][8] = "Getafe"; puntosFutL[7][8] = 1; puntosFutV[7][8] = 2;
		equipoFutL[7][9] = "Barcelona"; equipoFutV[7][9] = "Sevilla"; puntosFutL[7][9] = 4; puntosFutV[7][9] = 0;

		equipoFutL[8][0] = "Granada"; equipoFutV[8][0] = "Osasuna"; puntosFutL[8][0] = 1; puntosFutV[8][0] = 0;
		equipoFutL[8][1] = "Eibar"; equipoFutV[8][1] = "Barcelona"; puntosFutL[8][1] = 0; puntosFutV[8][1] = 3;
		equipoFutL[8][2] = "Atlético"; equipoFutV[8][2] = "Valencia"; puntosFutL[8][2] = 1; puntosFutV[8][2] = 1;
		equipoFutL[8][3] = "Getafe"; equipoFutV[8][3] = "Leganés"; puntosFutL[8][3] = 2; puntosFutV[8][3] = 0;
		equipoFutL[8][4] = "Mallorca"; equipoFutV[8][4] = "R.Madrid"; puntosFutL[8][4] = 1; puntosFutV[8][4] = 0;
		equipoFutL[8][5] = "Alavés"; equipoFutV[8][5] = "Celta"; puntosFutL[8][5] = 2; puntosFutV[8][5] = 0;
		equipoFutL[8][6] = "R.Sociedad"; equipoFutV[8][6] = "Betis"; puntosFutL[8][6] = 3; puntosFutV[8][6] = 1;
		equipoFutL[8][7] = "Espanyol"; equipoFutV[8][7] = "Villarreal"; puntosFutL[8][7] = 0; puntosFutV[8][7] = 1;
		equipoFutL[8][8] = "Athletic"; equipoFutV[8][8] = "Valladolid"; puntosFutL[8][8] = 1; puntosFutV[8][8] = 1;
		equipoFutL[8][9] = "Sevilla"; equipoFutV[8][9] = "Levante"; puntosFutL[8][9] = 1; puntosFutV[8][9] = 0;

		equipoFutL[9][0] = "Villarreal"; equipoFutV[9][0] = "Alavés"; puntosFutL[9][0] = 4; puntosFutV[9][0] = 1;
		equipoFutL[9][1] = "Leganés"; equipoFutV[9][1] = "Mallorca"; puntosFutL[9][1] = 1; puntosFutV[9][1] = 0;
		equipoFutL[9][2] = "Valladolid"; equipoFutV[9][2] = "Eibar"; puntosFutL[9][2] = 2; puntosFutV[9][2] = 0;
		equipoFutL[9][3] = "Atlético"; equipoFutV[9][3] = "Athletic"; puntosFutL[9][3] = 2; puntosFutV[9][3] = 0;
		equipoFutL[9][4] = "Celta"; equipoFutV[9][4] = "R.Sociedad"; puntosFutL[9][4] = 0; puntosFutV[9][4] = 1;
		equipoFutL[9][5] = "Granada"; equipoFutV[9][5] = "Betis"; puntosFutL[9][5] = 1; puntosFutV[9][5] = 0;
		equipoFutL[9][6] = "Levante"; equipoFutV[9][6] = "Espanyol"; puntosFutL[9][6] = 0; puntosFutV[9][6] = 1;
		equipoFutL[9][7] = "Sevilla"; equipoFutV[9][7] = "Getafe"; puntosFutL[9][7] = 2; puntosFutV[9][7] = 0;
		equipoFutL[9][8] = "Osasuna"; equipoFutV[9][8] = "Valencia"; puntosFutL[9][8] = 3; puntosFutV[9][8] = 1;
		equipoFutL[9][9] = "Barcelona"; equipoFutV[9][9] = "R.Madrid"; puntosFutL[9][9] = 0; puntosFutV[9][9] = 0;

		equipoFutL[10][0] = "Alavés"; equipoFutV[10][0] = "Atlético"; puntosFutL[10][0] = 1; puntosFutV[10][0] = 1;
		equipoFutL[10][1] = "Barcelona"; equipoFutV[10][1] = "Valladolid"; puntosFutL[10][1] = 5; puntosFutV[10][1] = 1;
		equipoFutL[10][2] = "R.Sociedad"; equipoFutV[10][2] = "Levante"; puntosFutL[10][2] = 1; puntosFutV[10][2] = 2;
		equipoFutL[10][3] = "Valencia"; equipoFutV[10][3] = "Sevilla"; puntosFutL[10][3] = 1; puntosFutV[10][3] = 1;
		equipoFutL[10][4] = "Athletic"; equipoFutV[10][4] = "Espanyol"; puntosFutL[10][4] = 3; puntosFutV[10][4] = 0;
		equipoFutL[10][5] = "Betis"; equipoFutV[10][5] = "Celta"; puntosFutL[10][5] = 2; puntosFutV[10][5] = 1;
		equipoFutL[10][6] = "R.Madrid"; equipoFutV[10][6] = "Leganés"; puntosFutL[10][6] = 5; puntosFutV[10][6] = 0;
		equipoFutL[10][7] = "Eibar"; equipoFutV[10][7] = "Villarreal"; puntosFutL[10][7] = 2; puntosFutV[10][7] = 1;
		equipoFutL[10][8] = "Mallorca"; equipoFutV[10][8] = "Osasuna"; puntosFutL[10][8] = 2; puntosFutV[10][8] = 2;
		equipoFutL[10][9] = "Getafe"; equipoFutV[10][9] = "Granada"; puntosFutL[10][9] = 3; puntosFutV[10][9] = 1;

		equipoFutL[11][0] = "Espanyol"; equipoFutV[11][0] = "Valencia"; puntosFutL[11][0] = 1; puntosFutV[11][0] = 2;
		equipoFutL[11][1] = "Levante"; equipoFutV[11][1] = "Barcelona"; puntosFutL[11][1] = 3; puntosFutV[11][1] = 1;
		equipoFutL[11][2] = "Sevilla"; equipoFutV[11][2] = "Atlético"; puntosFutL[11][2] = 1; puntosFutV[11][2] = 1;
		equipoFutL[11][3] = "R.Madrid"; equipoFutV[11][3] = "Betis"; puntosFutL[11][3] = 0; puntosFutV[11][3] = 0;
		equipoFutL[11][4] = "Valladolid"; equipoFutV[11][4] = "Mallorca"; puntosFutL[11][4] = 3; puntosFutV[11][4] = 0;
		equipoFutL[11][5] = "Villarreal"; equipoFutV[11][5] = "Athletic"; puntosFutL[11][5] = 0; puntosFutV[11][5] = 0;
		equipoFutL[11][6] = "Osasuna"; equipoFutV[11][6] = "Alavés"; puntosFutL[11][6] = 4; puntosFutV[11][6] = 2;
		equipoFutL[11][7] = "Celta"; equipoFutV[11][7] = "Getafe"; puntosFutL[11][7] = 0; puntosFutV[11][7] = 1;
		equipoFutL[11][8] = "Leganés"; equipoFutV[11][8] = "Eibar"; puntosFutL[11][8] = 1; puntosFutV[11][8] = 2;
		equipoFutL[11][9] = "Granada"; equipoFutV[11][9] = "R.Sociedad"; puntosFutL[11][9] = 1; puntosFutV[11][9] = 2;

		equipoFutL[12][0] = "R.Sociedad"; equipoFutV[12][0] = "Leganés"; puntosFutL[12][0] = 1; puntosFutV[12][0] = 1;
		equipoFutL[12][1] = "Alavés"; equipoFutV[12][1] = "Valladolid"; puntosFutL[12][1] = 3; puntosFutV[12][1] = 0;
		equipoFutL[12][2] = "Valencia"; equipoFutV[12][2] = "Granada"; puntosFutL[12][2] = 2; puntosFutV[12][2] = 0;
		equipoFutL[12][3] = "Eibar"; equipoFutV[12][3] = "R.Madrid"; puntosFutL[12][3] = 0; puntosFutV[12][3] = 4;
		equipoFutL[12][4] = "Barcelona"; equipoFutV[12][4] = "Celta"; puntosFutL[12][4] = 4; puntosFutV[12][4] = 1;
		equipoFutL[12][5] = "Mallorca"; equipoFutV[12][5] = "Villarreal"; puntosFutL[12][5] = 3; puntosFutV[12][5] = 1;
		equipoFutL[12][6] = "Athletic"; equipoFutV[12][6] = "Levante"; puntosFutL[12][6] = 2; puntosFutV[12][6] = 1;
		equipoFutL[12][7] = "Atlético"; equipoFutV[12][7] = "Espanyol"; puntosFutL[12][7] = 3; puntosFutV[12][7] = 1;
		equipoFutL[12][8] = "Getafe"; equipoFutV[12][8] = "Osasuna"; puntosFutL[12][8] = 0; puntosFutV[12][8] = 0;
		equipoFutL[12][9] = "Betis"; equipoFutV[12][9] = "Sevilla"; puntosFutL[12][9] = 1; puntosFutV[12][9] = 2;

		equipoFutL[13][0] = "Levante"; equipoFutV[13][0] = "Mallorca"; puntosFutL[13][0] = 2; puntosFutV[13][0] = 1;
		equipoFutL[13][1] = "Leganés"; equipoFutV[13][1] = "Barcelona"; puntosFutL[13][1] = 1; puntosFutV[13][1] = 2;
		equipoFutL[13][2] = "Betis"; equipoFutV[13][2] = "Valencia"; puntosFutL[13][2] = 2; puntosFutV[13][2] = 1;
		equipoFutL[13][3] = "Granada"; equipoFutV[13][3] = "Atlético"; puntosFutL[13][3] = 1; puntosFutV[13][3] = 1;
		equipoFutL[13][4] = "R.Madrid"; equipoFutV[13][4] = "R.Sociedad"; puntosFutL[13][4] = 3; puntosFutV[13][4] = 1;
		equipoFutL[13][5] = "Espanyol"; equipoFutV[13][5] = "Getafe"; puntosFutL[13][5] = 1; puntosFutV[13][5] = 1;
		equipoFutL[13][6] = "Osasuna"; equipoFutV[13][6] = "Athletic"; puntosFutL[13][6] = 1; puntosFutV[13][6] = 2;
		equipoFutL[13][7] = "Eibar"; equipoFutV[13][7] = "Alavés"; puntosFutL[13][7] = 0; puntosFutV[13][7] = 2;
		equipoFutL[13][8] = "Villarreal"; equipoFutV[13][8] = "Celta"; puntosFutL[13][8] = 1; puntosFutV[13][8] = 3;
		equipoFutL[13][9] = "Valladolid"; equipoFutV[13][9] = "Sevilla"; puntosFutL[13][9] = 0; puntosFutV[13][9] = 1;

		equipoFutL[14][0] = "Celta"; equipoFutV[14][0] = "Valladolid"; puntosFutL[14][0] = 0; puntosFutV[14][0] = 0;
		equipoFutL[14][1] = "Alavés"; equipoFutV[14][1] = "R.Madrid"; puntosFutL[14][1] = 1; puntosFutV[14][1] = 2;
		equipoFutL[14][2] = "R.Sociedad"; equipoFutV[14][2] = "Eibar"; puntosFutL[14][2] = 4; puntosFutV[14][2] = 1;
		equipoFutL[14][3] = "Mallorca"; equipoFutV[14][3] = "Betis"; puntosFutL[14][3] = 1; puntosFutV[14][3] = 2;
		equipoFutL[14][4] = "Valencia"; equipoFutV[14][4] = "Villarreal"; puntosFutL[14][4] = 2; puntosFutV[14][4] = 1;
		equipoFutL[14][5] = "Sevilla"; equipoFutV[14][5] = "Leganés"; puntosFutL[14][5] = 1; puntosFutV[14][5] = 0;
		equipoFutL[14][6] = "Athletic"; equipoFutV[14][6] = "Granada"; puntosFutL[14][6] = 2; puntosFutV[14][6] = 0;
		equipoFutL[14][7] = "Espanyol"; equipoFutV[14][7] = "Osasuna"; puntosFutL[14][7] = 2; puntosFutV[14][7] = 4;
		equipoFutL[14][8] = "Getafe"; equipoFutV[14][8] = "Levante"; puntosFutL[14][8] = 4; puntosFutV[14][8] = 0;
		equipoFutL[14][9] = "Atlético"; equipoFutV[14][9] = "Barcelona"; puntosFutL[14][9] = 0; puntosFutV[14][9] = 1;

		equipoFutL[15][0] = "Villarreal"; equipoFutV[15][0] = "Atlético"; puntosFutL[15][0] = 0; puntosFutV[15][0] = 0;
		equipoFutL[15][1] = "R.Madrid"; equipoFutV[15][1] = "Espanyol"; puntosFutL[15][1] = 2; puntosFutV[15][1] = 0;
		equipoFutL[15][2] = "Granada"; equipoFutV[15][2] = "Alavés"; puntosFutL[15][2] = 3; puntosFutV[15][2] = 0;
		equipoFutL[15][3] = "Levante"; equipoFutV[15][3] = "Valencia"; puntosFutL[15][3] = 2; puntosFutV[15][3] = 4;
		equipoFutL[15][4] = "Barcelona"; equipoFutV[15][4] = "Mallorca"; puntosFutL[15][4] = 5; puntosFutV[15][4] = 2;
		equipoFutL[15][5] = "Eibar"; equipoFutV[15][5] = "Getafe"; puntosFutL[15][5] = 0; puntosFutV[15][5] = 1;
		equipoFutL[15][6] = "Betis"; equipoFutV[15][6] = "Athletic"; puntosFutL[15][6] = 3; puntosFutV[15][6] = 2;
		equipoFutL[15][7] = "Valladolid"; equipoFutV[15][7] = "R.Sociedad"; puntosFutL[15][7] = 0; puntosFutV[15][7] = 0;
		equipoFutL[15][8] = "Leganés"; equipoFutV[15][8] = "Celta"; puntosFutL[15][8] = 3; puntosFutV[15][8] = 2;
		equipoFutL[15][9] = "Osasuna"; equipoFutV[15][9] = "Sevilla"; puntosFutL[15][9] = 1; puntosFutV[15][9] = 1;

		equipoFutL[16][0] = "Alavés"; equipoFutV[16][0] = "Leganés"; puntosFutL[16][0] = 1; puntosFutV[16][0] = 1;
		equipoFutL[16][1] = "Granada"; equipoFutV[16][1] = "Levante"; puntosFutL[16][1] = 1; puntosFutV[16][1] = 2;
		equipoFutL[16][2] = "R.Sociedad"; equipoFutV[16][2] = "Barcelona"; puntosFutL[16][2] = 2; puntosFutV[16][2] = 2;
		equipoFutL[16][3] = "Athletic"; equipoFutV[16][3] = "Eibar"; puntosFutL[16][3] = 0; puntosFutV[16][3] = 0;
		equipoFutL[16][4] = "Atlético"; equipoFutV[16][4] = "Osasuna"; puntosFutL[16][4] = 2; puntosFutV[16][4] = 0;
		equipoFutL[16][5] = "Getafe"; equipoFutV[16][5] = "Valladolid"; puntosFutL[16][5] = 2; puntosFutV[16][5] = 0;
		equipoFutL[16][6] = "Celta"; equipoFutV[16][6] = "Mallorca"; puntosFutL[16][6] = 2; puntosFutV[16][6] = 2;
		equipoFutL[16][7] = "Espanyol"; equipoFutV[16][7] = "Betis"; puntosFutL[16][7] = 2; puntosFutV[16][7] = 2;
		equipoFutL[16][8] = "Sevilla"; equipoFutV[16][8] = "Villarreal"; puntosFutL[16][8] = 1; puntosFutV[16][8] = 2;
		equipoFutL[16][9] = "Valencia"; equipoFutV[16][9] = "R.Madrid"; puntosFutL[16][9] = 1; puntosFutV[16][9] = 1;

		equipoFutL[17][0] = "Eibar"; equipoFutV[17][0] = "Granada"; puntosFutL[17][0] = 3; puntosFutV[17][0] = 0;
		equipoFutL[17][1] = "Mallorca"; equipoFutV[17][1] = "Sevilla"; puntosFutL[17][1] = 0; puntosFutV[17][1] = 2;
		equipoFutL[17][2] = "Barcelona"; equipoFutV[17][2] = "Alavés"; puntosFutL[17][2] = 4; puntosFutV[17][2] = 1;
		equipoFutL[17][3] = "Villarreal"; equipoFutV[17][3] = "Getafe"; puntosFutL[17][3] = 1; puntosFutV[17][3] = 0;
		equipoFutL[17][4] = "Valladolid"; equipoFutV[17][4] = "Valencia"; puntosFutL[17][4] = 1; puntosFutV[17][4] = 1;
		equipoFutL[17][5] = "Leganés"; equipoFutV[17][5] = "Espanyol"; puntosFutL[17][5] = 2; puntosFutV[17][5] = 0;
		equipoFutL[17][6] = "Osasuna"; equipoFutV[17][6] = "R.Sociedad"; puntosFutL[17][6] = 3; puntosFutV[17][6] = 4;
		equipoFutL[17][7] = "Betis"; equipoFutV[17][7] = "Atlético"; puntosFutL[17][7] = 1; puntosFutV[17][7] = 2;
		equipoFutL[17][8] = "Levante"; equipoFutV[17][8] = "Celta"; puntosFutL[17][8] = 3; puntosFutV[17][8] = 1;
		equipoFutL[17][9] = "R.Madrid"; equipoFutV[17][9] = "Athletic"; puntosFutL[17][9] = 0; puntosFutV[17][9] = 0;

		equipoFutL[18][0] = "Valladolid"; equipoFutV[18][0] = "Leganés"; puntosFutL[18][0] = 2; puntosFutV[18][0] = 2;
		equipoFutL[18][1] = "Sevilla"; equipoFutV[18][1] = "Athletic"; puntosFutL[18][1] = 1; puntosFutV[18][1] = 1;
		equipoFutL[18][2] = "Valencia"; equipoFutV[18][2] = "Eibar"; puntosFutL[18][2] = 1; puntosFutV[18][2] = 0;
		equipoFutL[18][3] = "Getafe"; equipoFutV[18][3] = "R.Madrid"; puntosFutL[18][3] = 0; puntosFutV[18][3] = 3;
		equipoFutL[18][4] = "Atlético"; equipoFutV[18][4] = "Levante"; puntosFutL[18][4] = 2; puntosFutV[18][4] = 1;
		equipoFutL[18][5] = "Espanyol"; equipoFutV[18][5] = "Barcelona"; puntosFutL[18][5] = 2; puntosFutV[18][5] = 2;
		equipoFutL[18][6] = "Granada"; equipoFutV[18][6] = "Mallorca"; puntosFutL[18][6] = 1; puntosFutV[18][6] = 0;
		equipoFutL[18][7] = "R.Sociedad"; equipoFutV[18][7] = "Villarreal"; puntosFutL[18][7] = 1; puntosFutV[18][7] = 2;
		equipoFutL[18][8] = "Alavés"; equipoFutV[18][8] = "Betis"; puntosFutL[18][8] = 1; puntosFutV[18][8] = 1;
		equipoFutL[18][9] = "Celta"; equipoFutV[18][9] = "Osasuna"; puntosFutL[18][9] = 1; puntosFutV[18][9] = 1;

		equipoFutL[19][0] = "Leganés"; equipoFutV[19][0] = "Getafe"; puntosFutL[19][0] = 0; puntosFutV[19][0] = 3;
		equipoFutL[19][1] = "Levante"; equipoFutV[19][1] = "Alavés"; puntosFutL[19][1] = 0; puntosFutV[19][1] = 1;
		equipoFutL[19][2] = "R.Madrid"; equipoFutV[19][2] = "Sevilla"; puntosFutL[19][2] = 2; puntosFutV[19][2] = 1;
		equipoFutL[19][3] = "Osasuna"; equipoFutV[19][3] = "Valladolid"; puntosFutL[19][3] = 0; puntosFutV[19][3] = 0;
		equipoFutL[19][4] = "Eibar"; equipoFutV[19][4] = "Atlético"; puntosFutL[19][4] = 2; puntosFutV[19][4] = 0;
		equipoFutL[19][5] = "Mallorca"; equipoFutV[19][5] = "Valencia"; puntosFutL[19][5] = 4; puntosFutV[19][5] = 1;
		equipoFutL[19][6] = "Betis"; equipoFutV[19][6] = "R.Sociedad"; puntosFutL[19][6] = 3; puntosFutV[19][6] = 0;
		equipoFutL[19][7] = "Villarreal"; equipoFutV[19][7] = "Espanyol"; puntosFutL[19][7] = 1; puntosFutV[19][7] = 2;
		equipoFutL[19][8] = "Athletic"; equipoFutV[19][8] = "Celta"; puntosFutL[19][8] = 1; puntosFutV[19][8] = 1;
		equipoFutL[19][9] = "Barcelona"; equipoFutV[19][9] = "Granada"; puntosFutL[19][9] = 1; puntosFutV[19][9] = 0;

		equipoFutL[20][0] = "Osasuna"; equipoFutV[20][0] = "Levante"; puntosFutL[20][0] = 2; puntosFutV[20][0] = 0;
		equipoFutL[20][1] = "Espanyol"; equipoFutV[20][1] = "Athletic"; puntosFutL[20][1] = 1; puntosFutV[20][1] = 1;
		equipoFutL[20][2] = "Valencia"; equipoFutV[20][2] = "Barcelona"; puntosFutL[20][2] = 2; puntosFutV[20][2] = 0;
		equipoFutL[20][3] = "Alavés"; equipoFutV[20][3] = "Villarreal"; puntosFutL[20][3] = 1; puntosFutV[20][3] = 2;
		equipoFutL[20][4] = "Sevilla"; equipoFutV[20][4] = "Granada"; puntosFutL[20][4] = 2; puntosFutV[20][4] = 0;
		equipoFutL[20][5] = "Atlético"; equipoFutV[20][5] = "Leganés"; puntosFutL[20][5] = 0; puntosFutV[20][5] = 0;
		equipoFutL[20][6] = "Celta"; equipoFutV[20][6] = "Eibar"; puntosFutL[20][6] = 0; puntosFutV[20][6] = 0;
		equipoFutL[20][7] = "Getafe"; equipoFutV[20][7] = "Betis"; puntosFutL[20][7] = 1; puntosFutV[20][7] = 0;
		equipoFutL[20][8] = "R.Sociedad"; equipoFutV[20][8] = "Mallorca"; puntosFutL[20][8] = 3; puntosFutV[20][8] = 0;
		equipoFutL[20][9] = "Valladolid"; equipoFutV[20][9] = "R.Madrid"; puntosFutL[20][9] = 0; puntosFutV[20][9] = 1;

		equipoFutL[21][0] = "Granada"; equipoFutV[21][0] = "Espanyol"; puntosFutL[21][0] = 2; puntosFutV[21][0] = 1;
		equipoFutL[21][1] = "R.Madrid"; equipoFutV[21][1] = "Atlético"; puntosFutL[21][1] = 1; puntosFutV[21][1] = 0;
		equipoFutL[21][2] = "Mallorca"; equipoFutV[21][2] = "Valladolid"; puntosFutL[21][2] = 0; puntosFutV[21][2] = 1;
		equipoFutL[21][3] = "Valencia"; equipoFutV[21][3] = "Celta"; puntosFutL[21][3] = 1; puntosFutV[21][3] = 0;
		equipoFutL[21][4] = "Leganés"; equipoFutV[21][4] = "R.Sociedad"; puntosFutL[21][4] = 2; puntosFutV[21][4] = 1;
		equipoFutL[21][5] = "Eibar"; equipoFutV[21][5] = "Betis"; puntosFutL[21][5] = 1; puntosFutV[21][5] = 1;
		equipoFutL[21][6] = "Athletic"; equipoFutV[21][6] = "Getafe"; puntosFutL[21][6] = 0; puntosFutV[21][6] = 2;
		equipoFutL[21][7] = "Sevilla"; equipoFutV[21][7] = "Alavés"; puntosFutL[21][7] = 1; puntosFutV[21][7] = 1;
		equipoFutL[21][8] = "Villarreal"; equipoFutV[21][8] = "Osasuna"; puntosFutL[21][8] = 3; puntosFutV[21][8] = 1;
		equipoFutL[21][9] = "Barcelona"; equipoFutV[21][9] = "Levante"; puntosFutL[21][9] = 2; puntosFutV[21][9] = 1;

		equipoFutL[22][0] = "Alavés"; equipoFutV[22][0] = "Eibar"; puntosFutL[22][0] = 2; puntosFutV[22][0] = 1;
		equipoFutL[22][1] = "Levante"; equipoFutV[22][1] = "Leganés"; puntosFutL[22][1] = 2; puntosFutV[22][1] = 0;
		equipoFutL[22][2] = "Getafe"; equipoFutV[22][2] = "Valencia"; puntosFutL[22][2] = 3; puntosFutV[22][2] = 0;
		equipoFutL[22][3] = "Valladolid"; equipoFutV[22][3] = "Villarreal"; puntosFutL[22][3] = 1; puntosFutV[22][3] = 1;
		equipoFutL[22][4] = "Atlético"; equipoFutV[22][4] = "Granada"; puntosFutL[22][4] = 1; puntosFutV[22][4] = 0;
		equipoFutL[22][5] = "Espanyol"; equipoFutV[22][5] = "Mallorca"; puntosFutL[22][5] = 1; puntosFutV[22][5] = 0;
		equipoFutL[22][6] = "R.Sociedad"; equipoFutV[22][6] = "Athletic"; puntosFutL[22][6] = 2; puntosFutV[22][6] = 1;
		equipoFutL[22][7] = "Osasuna"; equipoFutV[22][7] = "R.Madrid"; puntosFutL[22][7] = 1; puntosFutV[22][7] = 4;
		equipoFutL[22][8] = "Celta"; equipoFutV[22][8] = "Sevilla"; puntosFutL[22][8] = 2; puntosFutV[22][8] = 1;
		equipoFutL[22][9] = "Betis"; equipoFutV[22][9] = "Barcelona"; puntosFutL[22][9] = 2; puntosFutV[22][9] = 3;

		equipoFutL[23][0] = "Valencia"; equipoFutV[23][0] = "Atlético"; puntosFutL[23][0] = 2; puntosFutV[23][0] = 2;
		equipoFutL[23][1] = "Mallorca"; equipoFutV[23][1] = "Alavés"; puntosFutL[23][1] = 1; puntosFutV[23][1] = 0;
		equipoFutL[23][2] = "Barcelona"; equipoFutV[23][2] = "Getafe"; puntosFutL[23][2] = 2; puntosFutV[23][2] = 1;
		equipoFutL[23][3] = "Villarreal"; equipoFutV[23][3] = "Levante"; puntosFutL[23][3] = 2; puntosFutV[23][3] = 1;
		equipoFutL[23][4] = "Granada"; equipoFutV[23][4] = "Valladolid"; puntosFutL[23][4] = 2; puntosFutV[23][4] = 1;
		equipoFutL[23][5] = "Sevilla"; equipoFutV[23][5] = "Espanyol"; puntosFutL[23][5] = 2; puntosFutV[23][5] = 2;
		equipoFutL[23][6] = "Leganés"; equipoFutV[23][6] = "Betis"; puntosFutL[23][6] = 0; puntosFutV[23][6] = 0;
		equipoFutL[23][7] = "Athletic"; equipoFutV[23][7] = "Osasuna"; puntosFutL[23][7] = 0; puntosFutV[23][7] = 1;
		equipoFutL[23][8] = "R.Madrid"; equipoFutV[23][8] = "Celta"; puntosFutL[23][8] = 2; puntosFutV[23][8] = 2;
		equipoFutL[23][9] = "Eibar"; equipoFutV[23][9] = "R.Sociedad"; puntosFutL[23][9] = 1; puntosFutV[23][9] = 2;

		equipoFutL[24][0] = "Betis"; equipoFutV[24][0] = "Mallorca"; puntosFutL[24][0] = 3; puntosFutV[24][0] = 3;
		equipoFutL[24][1] = "Celta"; equipoFutV[24][1] = "Leganés"; puntosFutL[24][1] = 1; puntosFutV[24][1] = 0;
		equipoFutL[24][2] = "Barcelona"; equipoFutV[24][2] = "Eibar"; puntosFutL[24][2] = 5; puntosFutV[24][2] = 0;
		equipoFutL[24][3] = "R.Sociedad"; equipoFutV[24][3] = "Valencia"; puntosFutL[24][3] = 3; puntosFutV[24][3] = 0;
		equipoFutL[24][4] = "Levante"; equipoFutV[24][4] = "R.Madrid"; puntosFutL[24][4] = 1; puntosFutV[24][4] = 0;
		equipoFutL[24][5] = "Osasuna"; equipoFutV[24][5] = "Granada"; puntosFutL[24][5] = 0; puntosFutV[24][5] = 3;
		equipoFutL[24][6] = "Alavés"; equipoFutV[24][6] = "Athletic"; puntosFutL[24][6] = 2; puntosFutV[24][6] = 1;
		equipoFutL[24][7] = "Valladolid"; equipoFutV[24][7] = "Espanyol"; puntosFutL[24][7] = 2; puntosFutV[24][7] = 1;
		equipoFutL[24][8] = "Getafe"; equipoFutV[24][8] = "Sevilla"; puntosFutL[24][8] = 0; puntosFutV[24][8] = 3;
		equipoFutL[24][9] = "Atlético"; equipoFutV[24][9] = "Villarreal"; puntosFutL[24][9] = 3; puntosFutV[24][9] = 1;
		
		equipoFutL[25][0] = "R.Sociedad"; equipoFutV[25][0] = "Valladolid"; puntosFutL[25][0] = 1; puntosFutV[25][0] = 0;
		equipoFutL[25][1] = "Eibar"; equipoFutV[25][1] = "Levante"; puntosFutL[25][1] = 3; puntosFutV[25][1] = 0;
		equipoFutL[25][2] = "Valencia"; equipoFutV[25][2] = "Betis"; puntosFutL[25][2] = 2; puntosFutV[25][2] = 1;
		equipoFutL[25][3] = "Leganés"; equipoFutV[25][3] = "Alavés"; puntosFutL[25][3] = 1; puntosFutV[25][3] = 1;
		equipoFutL[25][4] = "Granada"; equipoFutV[25][4] = "Celta"; puntosFutL[25][4] = 0; puntosFutV[25][4] = 0;
		equipoFutL[25][5] = "Sevilla"; equipoFutV[25][5] = "Osasuna"; puntosFutL[25][5] = 3; puntosFutV[25][5] = 2;
		equipoFutL[25][6] = "Athletic"; equipoFutV[25][6] = "Villarreal"; puntosFutL[25][6] = 1; puntosFutV[25][6] = 0;
		equipoFutL[25][7] = "Espanyol"; equipoFutV[25][7] = "Atlético"; puntosFutL[25][7] = 1; puntosFutV[25][7] = 1;
		equipoFutL[25][8] = "Mallorca"; equipoFutV[25][8] = "Getafe"; puntosFutL[25][8] = 0; puntosFutV[25][8] = 1;
		equipoFutL[25][9] = "R.Madrid"; equipoFutV[25][9] = "Barcelona"; puntosFutL[25][9] = 2; puntosFutV[25][9] = 0;
	
		equipoFutL[26][0] = "Alavés"; equipoFutV[26][0] = "Valencia"; puntosFutL[26][0] = 1; puntosFutV[26][0] = 1;
		equipoFutL[26][1] = "Eibar"; equipoFutV[26][1] = "Mallorca"; puntosFutL[26][1] = 1; puntosFutV[26][1] = 2;
		equipoFutL[26][2] = "Atlético"; equipoFutV[26][2] = "Sevilla"; puntosFutL[26][2] = 2; puntosFutV[26][2] = 2;
		equipoFutL[26][3] = "Barcelona"; equipoFutV[26][3] = "R.Sociedad"; puntosFutL[26][3] = 1; puntosFutV[26][3] = 0;
		equipoFutL[26][4] = "Getafe"; equipoFutV[26][4] = "Celta"; puntosFutL[26][4] = 0; puntosFutV[26][4] = 0;
		equipoFutL[26][5] = "Osasuna"; equipoFutV[26][5] = "Espanyol"; puntosFutL[26][5] = 1; puntosFutV[26][5] = 0;
		equipoFutL[26][6] = "Valladolid"; equipoFutV[26][6] = "Athletic"; puntosFutL[26][6] = 1; puntosFutV[26][6] = 4;
		equipoFutL[26][7] = "Levante"; equipoFutV[26][7] = "Granada"; puntosFutL[26][7] = 1; puntosFutV[26][7] = 1;
		equipoFutL[26][8] = "Villarreal"; equipoFutV[26][8] = "Leganés"; puntosFutL[26][8] = 1; puntosFutV[26][8] = 2;
		equipoFutL[26][9] = "Betis"; equipoFutV[26][9] = "R.Madrid"; puntosFutL[26][9] = 2; puntosFutV[26][9] = 1;
		
		LigaFutbol = new Liga(equipoFutL,equipoFutV,puntosFutL,puntosFutV);
	}
	
	/**
	 * Inicializa el objeto LigaBaloncesto con los datos
	 */
	private static void initBaloncesto() {
		String[][] equipoBasL= new String[34][9];
		String[][] equipoBasV = new String[34][9];
		int[][] puntosBasL = new int[34][9];
		int[][] puntosBasV = new int[34][9];
		
		//J1
		equipoBasL[0][0]="Montakit Fuenlabrada";equipoBasV[0][0]="San Pablo Burgos";puntosBasL[0][0]=76;puntosBasV[0][0]=87;
		equipoBasL[0][1]="Baxi Manresa";equipoBasV[0][1]="Unicaja";puntosBasL[0][1]=79;puntosBasV[0][1]=69;
		equipoBasL[0][2]="Iberostar Tenerife";equipoBasV[0][2]="RETAbet Bilbao Basket";puntosBasL[0][2]=67;puntosBasV[0][2]=81;
		equipoBasL[0][3]="Valencia Basket";equipoBasV[0][3]="MoraBanc Andorra";puntosBasL[0][3]=89;puntosBasV[0][3]=68;
		equipoBasL[0][4]="Herbalife Gran Canaria";equipoBasV[0][4]="Casademont Zaragoza";puntosBasL[0][4]=73;puntosBasV[0][4]=79;
		equipoBasL[0][5]="Monbus Obradoiro";equipoBasV[0][5]="Barça";puntosBasL[0][5]=86;puntosBasV[0][5]=92;
		equipoBasL[0][6]="Kirolbet Baskonia";equipoBasV[0][6]="Movistar Estudiantes";puntosBasL[0][6]=105;puntosBasV[0][6]=82;
		equipoBasL[0][7]="Joventut de Badalona";equipoBasV[0][7]="Real Madrid";puntosBasL[0][7]=69;puntosBasV[0][7]=88;
		equipoBasL[0][8]="UCAM Murcia";equipoBasV[0][8]="Coosur Real Betis";puntosBasL[0][8]=90;puntosBasV[0][8]=78;
		
		//J2
		equipoBasL[1][0]="MoraBanc Andorra";equipoBasV[1][0]="Montakit Fuenlabrada";puntosBasL[1][0]=79;puntosBasV[1][0]=69;
		equipoBasL[1][1]="Unicaja";equipoBasV[1][1]="Iberostar Tenerife";puntosBasL[1][1]=80;puntosBasV[1][1]=88;
		equipoBasL[1][2]="RETAbet Bilbao Basket";equipoBasV[1][2]="Valencia Basket";puntosBasL[1][2]=83;puntosBasV[1][2]=79;
		equipoBasL[1][3]="Movistar Estudiantes";equipoBasV[1][3]="Herbalife Gran Canaria";puntosBasL[1][3]=82;puntosBasV[1][3]=78;
		equipoBasL[1][4]="Casademont Zaragoza";equipoBasV[1][4]="Monbus Obradoiro";puntosBasL[1][4]=96;puntosBasV[1][4]=64;
		equipoBasL[1][5]="Coosur Real Betis";equipoBasV[1][5]="Joventut de Badalona";puntosBasL[1][5]=95;puntosBasV[1][5]=88;
		equipoBasL[1][6]="Barça";equipoBasV[1][6]="Kirolbet Baskonia";puntosBasL[1][6]=95;puntosBasV[1][6]=87;
		equipoBasL[1][7]="Real Madrid";equipoBasV[1][7]="UCAM Murcia";puntosBasL[1][7]=97;puntosBasV[1][7]=69;
		equipoBasL[1][8]="San Pablo Burgos";equipoBasV[1][8]="Baxi Manresa";puntosBasL[1][8]=79;puntosBasV[1][8]=65;
		
		//J3
		equipoBasL[2][0]="UCAM Murcia";equipoBasV[2][0]="Casademont Zaragoza";puntosBasL[2][0]=89;puntosBasV[2][0]=73;
		equipoBasL[2][1]="Monbus Obradoiro";equipoBasV[2][1]="RETAbet Bilbao Basket";puntosBasL[2][1]=98;puntosBasV[2][1]=96;
		equipoBasL[2][2]="Iberostar Tenerife";equipoBasV[2][2]="Real Madrid";puntosBasL[2][2]=71;puntosBasV[2][2]=76;
		equipoBasL[2][3]="Joventut de Badalona";equipoBasV[2][3]="San Pablo Burgos";puntosBasL[2][3]=74;puntosBasV[2][3]=82;
		equipoBasL[2][4]="Montakit Fuenlabrada";equipoBasV[2][4]="Movistar Estudiantes";puntosBasL[2][4]=93;puntosBasV[2][4]=85;
		equipoBasL[2][5]="Baxi Manresa";equipoBasV[2][5]="Herbalife Gran Canaria";puntosBasL[2][5]=74;puntosBasV[2][5]=75;
		equipoBasL[2][6]="Valencia Basket";equipoBasV[2][6]="Coosur Real Betis";puntosBasL[2][6]=95;puntosBasV[2][6]=72;
		equipoBasL[2][7]="Kirolbet Baskonia";equipoBasV[2][7]="Unicaja";puntosBasL[2][7]=77;puntosBasV[2][7]=78;
		equipoBasL[2][8]="MoraBanc Andorra";equipoBasV[2][8]="Barça";puntosBasL[2][8]=86;puntosBasV[2][8]=84;
		
		//J4
		equipoBasL[3][0]="San Pablo Burgos";equipoBasV[3][0]="UCAM Murcia";puntosBasL[3][0]=92;puntosBasV[3][0]=82;
		equipoBasL[3][1]="Coosur Real Betis";equipoBasV[3][1]="Casademont Zaragoza";puntosBasL[3][1]=69;puntosBasV[3][1]=71;
		equipoBasL[3][2]="Unicaja";equipoBasV[3][2]="Joventut de Badalona";puntosBasL[3][2]=77;puntosBasV[3][2]=65;
		equipoBasL[3][3]="Iberostar Tenerife";equipoBasV[3][3]="Monbus Obradoiro";puntosBasL[3][3]=90;puntosBasV[3][3]=78;
		equipoBasL[3][4]="Real Madrid";equipoBasV[3][4]="Montakit Fuenlabrada";puntosBasL[3][4]=89;puntosBasV[3][4]=64;
		equipoBasL[3][5]="Herbalife Gran Canaria";equipoBasV[3][5]="MoraBanc Andorra";puntosBasL[3][5]=80;puntosBasV[3][5]=67;
		equipoBasL[3][6]="RETAbet Bilbao Basket";equipoBasV[3][6]="Kirolbet Baskonia";puntosBasL[3][6]=79;puntosBasV[3][6]=75;
		equipoBasL[3][7]="Barça";equipoBasV[3][7]="Valencia Basket";puntosBasL[3][7]=97;puntosBasV[3][7]=94;
		equipoBasL[3][8]="Movistar Estudiantes";equipoBasV[3][8]="Baxi Manresa";puntosBasL[3][8]=87;puntosBasV[3][8]=79;
		
		//J5
		equipoBasL[4][0]="MoraBanc Andorra";equipoBasV[4][0]="San Pablo Burgos";puntosBasL[4][0]=87;puntosBasV[4][0]=74;
		equipoBasL[4][1]="Casademont Zaragoza";equipoBasV[4][1]="RETAbet Bilbao Basket";puntosBasL[4][1]=84;puntosBasV[4][1]=61;
		equipoBasL[4][2]="Monbus Obradoiro";equipoBasV[4][2]="Real Madrid";puntosBasL[4][2]=76;puntosBasV[4][2]=83;
		equipoBasL[4][3]="Joventut de Badalona";equipoBasV[4][3]="Movistar Estudiantes";puntosBasL[4][3]=78;puntosBasV[4][3]=76;
		equipoBasL[4][4]="Baxi Manresa";equipoBasV[4][4]="Iberostar Tenerife";puntosBasL[4][4]=61;puntosBasV[4][4]=81;
		equipoBasL[4][5]="Kirolbet Baskonia";equipoBasV[4][5]="Coosur Real Betis";puntosBasL[4][5]=84;puntosBasV[4][5]=73;
		equipoBasL[4][6]="Montakit Fuenlabrada";equipoBasV[4][6]="UCAM Murcia";puntosBasL[4][6]=75;puntosBasV[4][6]=74;
		equipoBasL[4][7]="Valencia Basket";equipoBasV[4][7]="Unicaja";puntosBasL[4][7]=63;puntosBasV[4][7]=79;
		equipoBasL[4][8]="Barça";equipoBasV[4][8]="Herbalife Gran Canaria";puntosBasL[4][8]=89;puntosBasV[4][8]=75;
		//J6
		equipoBasL[5][0]="Herbalife Gran Canaria";equipoBasV[5][0]="Joventut de Badalona";puntosBasL[5][0]=68;puntosBasV[5][0]=79;
		equipoBasL[5][1]="UCAM Murcia";equipoBasV[5][1]="Monbus Obradoiro";puntosBasL[5][1]=90;puntosBasV[5][1]=95;
		equipoBasL[5][2]="San Pablo Burgos";equipoBasV[5][2]="Valencia Basket";puntosBasL[5][2]=62;puntosBasV[5][2]=93;
		equipoBasL[5][3]="Unicaja";equipoBasV[5][3]="Movistar Estudiantes";puntosBasL[5][3]=62;puntosBasV[5][3]=67;
		equipoBasL[5][4]="Real Madrid";equipoBasV[5][4]="Baxi Manresa";puntosBasL[5][4]=94;puntosBasV[5][4]=74;
		equipoBasL[5][5]="RETAbet Bilbao Basket";equipoBasV[5][5]="Montakit Fuenlabrada";puntosBasL[5][5]=92;puntosBasV[5][5]=80;
		equipoBasL[5][6]="Coosur Real Betis";equipoBasV[5][6]="MoraBancAndorra";puntosBasL[5][6]=86;puntosBasV[5][6]=81;
		equipoBasL[5][7]="Casademont Zaragoza";equipoBasV[5][7]="Barça";puntosBasL[5][7]=89;puntosBasV[5][7]=83;
		equipoBasL[5][8]="Iberostar Tenerife";equipoBasV[5][8]="Kirolbet Baskonia";puntosBasL[5][8]=78;puntosBasV[5][8]=79;
		
		LigaBaloncesto= new Liga(equipoBasL, equipoBasV, puntosBasL, puntosBasV);
	}
	
	/**
	 * Inicializa el objeto LigaFMS con los datos
	 */
	private static void initFMS() {
		String[][] galloL= new String[9][5];
		String[][] galloV = new String[9][5];
		int[][] puntosFMSL = new int[9][5];
		int[][] puntosFMSV = new int[9][5];
		
		//J1
		galloL[0][0]="Chuty";galloV[0][0]="BTA";puntosFMSL[0][0]=400;puntosFMSV[0][0]=343;
		galloL[0][1]="Skone";galloV[0][1]="Mister Ego";puntosFMSL[0][1]=362;puntosFMSV[0][1]=355;
		galloL[0][2]="Blon";galloV[0][2]="Errece";puntosFMSL[0][2]=323;puntosFMSV[0][2]=331;
		galloL[0][3]="Bnet";galloV[0][3]="Force";puntosFMSL[0][3]=398;puntosFMSV[0][3]=311;
		galloL[0][4]="Walls";galloV[0][4]="Zasko";puntosFMSL[0][4]=339;puntosFMSV[0][4]=355;
		//J2
		galloL[1][0]="Chuty";galloV[1][0]="Walls";puntosFMSL[1][0]=419;puntosFMSV[1][0]=367;
		galloL[1][1]="Force";galloV[1][1]="Blon";puntosFMSL[1][1]=321;puntosFMSV[1][1]=371;
		galloL[1][2]="BTA";galloV[1][2]="Skone";puntosFMSL[1][2]=330;puntosFMSV[1][2]=381;
		galloL[1][3]="Mister Ego";galloV[1][3]="Bnet";puntosFMSL[1][3]=318;puntosFMSV[1][3]=374;
		galloL[1][4]="Zasko";galloV[1][4]="Errece";puntosFMSL[1][4]=321;puntosFMSV[1][4]=340;
		//J3
		galloL[2][0]="Skone";galloV[2][0]="Bnet";puntosFMSL[2][0]=313;puntosFMSV[2][0]=359;
		galloL[2][1]="Blon";galloV[2][1]="Chuty";puntosFMSL[2][1]=325;puntosFMSV[2][1]=380;
		galloL[2][2]="Walls";galloV[2][2]="Force";puntosFMSL[2][2]=328;puntosFMSV[2][2]=298;
		galloL[2][3]="Errece";galloV[2][3]="Mister Ego";puntosFMSL[2][3]=312;puntosFMSV[2][3]=327;
		galloL[2][4]="Zasko";galloV[2][4]="BTA";puntosFMSL[2][4]=418;puntosFMSV[2][4]=420;
		//J4
		galloL[3][0]="Chuty";galloV[3][0]="Skone";puntosFMSL[3][0]=398;puntosFMSV[3][0]=411;
		galloL[3][1]="Blon";galloV[3][1]="Mister Ego";puntosFMSL[3][1]=365;puntosFMSV[3][1]=371;
		galloL[3][2]="Force";galloV[3][2]="Zasko";puntosFMSL[3][2]=323;puntosFMSV[3][2]=312;
		galloL[3][3]="Bnet";galloV[3][3]="BTA";puntosFMSL[3][3]=371;puntosFMSV[3][3]=301;
		galloL[3][4]="Errece";galloV[3][4]="Walls";puntosFMSL[3][4]=360;puntosFMSV[3][4]=344;
		//J5
		galloL[4][0]="Blon";galloV[4][0]="Bnet";puntosFMSL[4][0]=320;puntosFMSV[4][0]=335;
		galloL[4][1]="Chuty";galloV[4][1]="Errece";puntosFMSL[4][1]=349;puntosFMSV[4][1]=404;
		galloL[4][2]="BTA";galloV[4][2]="Force";puntosFMSL[4][2]=280;puntosFMSV[4][2]=279;
		galloL[4][3]="Skone";galloV[4][3]="Zasko";puntosFMSL[4][3]=257;puntosFMSV[4][3]=325;
		galloL[4][4]="Mister Ego";galloV[4][4]="Walls";puntosFMSL[4][4]=320;puntosFMSV[4][4]=323;
		
		LigaFMS = new Liga(galloL, galloV, puntosFMSL, puntosFMSV);
	}
	/**
	 * Devuelve el array con los nombres de los equipos locales
	 * @return equipoL
	 */
	public String[][] getEquipoL() {
		return equipoL;
	}
	/**
	 * Establece el array de equipos locales
	 * @param equipoL
	 */
	public void setEquipoL(String[][] equipoL) {
		this.equipoL = equipoL;
	}
	/**
	 * Devuelve el array con los nombres de los equipos visitantes
	 * @return equipoV
	 */
	public String[][] getEquipoV() {
		return equipoV;
	}
	/**
	 * Establece el array de equipos visitantes
	 * @param equipoV
	 */
	public void setEquipoV(String[][] equipoV) {
		this.equipoV = equipoV;
	}
	/**
	 * Devuelve el array con los puntos marcados por el equipo local
	 * @return puntosL
	 */
	public int[][] getPuntosL() {
		return puntosL;
	}
	/**
	 * Establece el array con los puntos marcados por el equipo local
	 * @param puntosL
	 */
	public void setPuntosL(int[][] puntosL) {
		this.puntosL = puntosL;
	}
	/**
	 * Devuelve el array con los puntos marcados por el equipo visitante
	 * @return puntosV
	 */
	public int[][] getPuntosV() {
		return puntosV;
	}
	/**
	 * Establece el array con los puntos marcados por el equipo visitante
	 * @param puntosL
	 */
	public void setPuntosV(int[][] puntosV) {
		this.puntosV = puntosV;
	}
	@Override
	public boolean equals(Object obj) {
		boolean result=false;
		if (obj instanceof Liga) {
			Liga l=(Liga) obj;
			result=this.equipoL.equals(l.equipoL) && this.equipoV.equals(l.equipoV) && this.puntosL.equals(l.puntosL) && this.puntosV.equals(l.puntosV);
		}
		return result;
	}
}
