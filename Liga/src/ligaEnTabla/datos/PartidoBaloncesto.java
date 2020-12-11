package ligaEnTabla.datos;
/**
 * 
 * @author Danel
 *
 */
public class PartidoBaloncesto extends Partido {
	private int puntosEquipoL;
	private int puntosEquipoV;
	/**
	 * Constructor del partido de baloncesto que recibe los nombres de los equipos y los puntos marcados por cada uno
	 * @param nomEquipoL nombre del equipo local
	 * @param nomEquipoV nombre del equipo visitante
	 * @param puntosEquipoL puntos del equipo local
	 * @param puntosEquipoV puntos equipo visitante
	 */
	public PartidoBaloncesto(String nomEquipoL, String nomEquipoV,int puntosEquipoL, int puntosEquipoV) {
		super(nomEquipoL, nomEquipoV);
		setPuntosEquipoL(puntosEquipoL);
		setPuntosEquipoV(puntosEquipoV);
	}
	/**
	 * Devuelve los puntos que marco el equipo local
	 * @return puntos que marco el equipo local
	 */
	public int getPuntosEquipoL() {
		return puntosEquipoL;
	}
	/**
	 * Establece los puntos marcados por el equipo local
	 * @param puntosEquipoL
	 */
	public void setPuntosEquipoL(int puntosEquipoL) {
		this.puntosEquipoL = puntosEquipoL;
	}
	/**
	 * Devuelve los puntos que marco el equipo visitante
	 * @return puntos que marco el equipo visitante
	 */
	public int getPuntosEquipoV() {
		return puntosEquipoV;
	}
	/**
	 * Establece los puntos marcados por el equipo visitante
	 * @param puntosEquipoL
	 */
	public void setPuntosEquipoV(int puntosEquipoV) {
		this.puntosEquipoV = puntosEquipoV;
	}
	@Override
	public String toString() {
		return nomEquipoL + ": "+puntosEquipoL+" vs "+puntosEquipoV+" :"+nomEquipoV;
	}

}
