package ligaEnTabla.datos;
/**
 * 
 * @author Danel
 *
 */
public class Clasificacion {
	protected Liga liga;
	protected Equipo equipos[];
	protected Partido partidos[][];
	/**
	 * Constructor que recibe un Objeto Liga
	 * @param liga
	 */
	public Clasificacion(Liga liga){
		setLiga(liga);
		equipos=new Equipo[2*liga.getEquipoL()[0].length];
		partidos=new Partido[liga.getJornadas()][liga.getEquipoL()[0].length];
	}
	/**
	 * Hace los calculos e implementa los puntos de la jornada 1 hasta la jornada indicada
	 * @param numJornada
	 */
	public void calcPuntos(int numJornada) {
		for (int i=0;i<numJornada;i++) {
			for (Partido p:partidos[i]) {
				for(Equipo e:equipos) {
					e.calculaPartido(p);
				}
			}
		}
			
	}
	/**
	 * Ordena los equipos en  la clasificacion segun el criterio de la liga
	 */
	public void ordenaEquiposPorPuntos() {
		int n= equipos.length;
		for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (equipos[j+1].esMejorQue(equipos[j])) { 
                    // Intercambiar arr[j+1] and arr[i] 
                    Equipo temp = equipos[j]; 
                    equipos[j] = equipos[j+1]; 
                    equipos[j+1] = temp; 
                }
            }
		}
	}
	/**
	 * Método que llena el array de partidos
	 */
	public void creaArrayPartidos() {
		for (int i=0;i<liga.getJornadas();i++) {
			for(int j=0;j<liga.getEquipoL()[0].length;j++) {
				if (this.liga.equals(Liga.LigaBaloncesto)) {
					partidos[i][j]= new PartidoBaloncesto(liga.getEquipoL(i, j),liga.getEquipoV(i, j),liga.getPuntosL(i, j),liga.getPuntosV(i, j));
				}
				else if (this.liga.equals(Liga.LigaFutbol)) {
					partidos[i][j]= new PartidoFutbol(liga.getEquipoL(i, j),liga.getEquipoV(i, j),liga.getPuntosL(i, j),liga.getPuntosV(i, j));
				}
				else if (this.liga.equals(Liga.LigaFMS)) {
					partidos[i][j]= new PartidoFMS(liga.getEquipoL(i, j),liga.getEquipoV(i, j),liga.getPuntosL(i, j),liga.getPuntosV(i, j));
				}
			}
		}
	}
	
	/**
	 * Crea el array de equipos siempre que haya alguna jornada disputada tomando los equipos de la primera jornada
	 */
	public void creaArrayEquipos() {
		if (liga.getJornadas()>0) {
			for (int i=0;i<partidos[0].length;i++) {
				if (this.liga.equals(Liga.LigaBaloncesto)) {
					Equipo eq1 = new EquipoBaloncesto(partidos[0][i].getNomEquipoL());
					Equipo eq2 = new EquipoBaloncesto(partidos[0][i].getNomEquipoV());
					//guarda en las posiciones pares
					equipos[2*i]=eq1;
					//guarda en las posiciones impares
					equipos[2*i+1]=eq2;
				}
				else if  (this.liga.equals(Liga.LigaFutbol)) {
					Equipo eq1 = new EquipoFutbol(partidos[0][i].getNomEquipoL());
					Equipo eq2 = new EquipoFutbol(partidos[0][i].getNomEquipoV());
					//guarda en las posiciones pares
					equipos[2*i]=eq1;
					//guarda en las posiciones impares
					equipos[2*i+1]=eq2;
				}
				else if  (this.liga.equals(Liga.LigaFMS)) {
					Equipo eq1 = new EquipoFMS(partidos[0][i].getNomEquipoL());
					Equipo eq2 = new EquipoFMS(partidos[0][i].getNomEquipoV());
					//guarda en las posiciones pares
					equipos[2*i]=eq1;
					//guarda en las posiciones impares
					equipos[2*i+1]=eq2;
				}
			}
		}
		else {
			System.out.println("No se ha jugado ninguna jornada");
			}
	}
	
	/**
	 * Visualiza los equipos del array de equipos con su puntuación actual
	 */
	public void visualizaEquipos() {
		int contador=1;
		for (Equipo i:equipos) {
			System.out.println(contador+"."+i);
			contador++;
		}
	}

	/**
	 * Busca un equipo por su nombre en el array de equipos. Devuelve null si no lo encuentra. 
	 * @param nombre del equipo
	 * @return Equipo con el nombre introducido
	 */
	protected Equipo buscaEnArray(String nomEquipo) {
		Equipo result=null;
		for (Equipo i:equipos) {
			if (nomEquipo==i.getNombre()) {
				result=i;
				break;
			}
		}
		return result;
	}
	/**
	 * Devuelve la array de equipos
	 * @return array de objetos equipos
	 */
	public Equipo[] getEquipos() {
		return equipos;
	}
	/**
	 * Devuelve el objeto liga al cual esta asociada la clasificacion
	 * @return liga
	 */
	public Liga getLiga() {
		return liga;
	}
	/**
	 * Devuelve la array de partidos jugados en la clasificacion
	 * @return
	 */
	public Partido[][] getPartidos() {
		return partidos;
	}
	/**
	 * Establece la liga de loa clasificacion
	 * @param liga
	 */
	public void setLiga(Liga liga) {
		this.liga = liga;
	}
	/**
	 * Establece la array de equipos
	 * @param equipos
	 */
	public void setEquipos(Equipo[] equipos) {
		this.equipos = equipos;
	}
	/**
	 * Establece la matriz de partidos
	 * @param partidos
	 */
	public void setPartidos(Partido[][] partidos) {
		this.partidos = partidos;
	}
	
	
	
}
