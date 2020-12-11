package ligaEnTabla.datos;
/**
 * 
 * @author Danel
 *
 */
public class PartidoFutbol extends Partido {
	private int golesEquipoL;
	private int golesEquipoV;
	/**
	 * Constructor del partido de futbol que recibe los nombres de los equipos y los puntos marcados por cada uno
	 * @param nomEquipoL nombre del equipo local
	 * @param nomEquipoV nombre del equipo visitante
	 * @param puntosEquipoL puntos del equipo local
	 * @param puntosEquipoV puntos equipo visitante
	 */
	public PartidoFutbol(String nomEquipoL, String nomEquipoV,int golesEquipoL, int golesEquipoV) {
		super(nomEquipoL, nomEquipoV);
		setGolesEquipoL(golesEquipoL);
		setGolesEquipoV(golesEquipoV);
	}
	/**
	 * Devuelve los puntos que marco el equipo local
	 * @return puntos que marco el equipo local
	 */
	public int getGolesEquipoL() {
		return golesEquipoL;
	}
	/**
	 * Establece los puntos marcados por el equipo local
	 * @param puntosEquipoL
	 */
	public void setGolesEquipoL(int golesEquipoL) {
		this.golesEquipoL = golesEquipoL;
	}
	/**
	 * Devuelve los puntos que marco el equipo visitante
	 * @return puntos que marco el equipo visitante
	 */
	public int getGolesEquipoV() {
		return golesEquipoV;
	}
	/**
	 * Establece los puntos marcados por el equipo visitante
	 * @param puntosEquipoL
	 */
	public void setGolesEquipoV(int golesEquipoV) {
		this.golesEquipoV = golesEquipoV;
	}
	@Override
	public String toString() {
		return nomEquipoL + ": "+golesEquipoL+" vs "+golesEquipoV+" :"+nomEquipoV;
	}

}
