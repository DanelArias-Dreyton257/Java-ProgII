package ligaEnTabla.datos;
/**
 * 
 * @author Danel
 *
 */
public class PartidoFMS extends Partido {
	private int PBsEquipoL;
	private int PBsEquipoV;
	/**
	 * Constructor de la batalla que recibe los nombres de los equipos y los puntos marcados por cada uno
	 * @param nomEquipoL nombre del MC local
	 * @param nomEquipoV nombre del MC visitante
	 * @param PBsEquipoL puntos del MC local
	 * @param PBsEquipoV puntos MC visitante
	 */
	public PartidoFMS(String nomEquipoL, String nomEquipoV,int PBsEquipoL, int PBsEquipoV) {
		super(nomEquipoL, nomEquipoV);
		setPBsEquipoL(PBsEquipoL);
		setPBsEquipoV(PBsEquipoV);
	}
	/**
	 * Devuelve los puntos que marco el MC local
	 * @return puntos que marco el MC local
	 */
	public int getPBsEquipoL() {
		return PBsEquipoL;
	}
	/**
	 * Establece los puntos marcados por el MC local
	 * @param puntosEquipoL
	 */
	public void setPBsEquipoL(int PBsEquipoL) {
		this.PBsEquipoL = PBsEquipoL;
	}
	/**
	 * Devuelve los puntos que marco el MC visitante
	 * @return puntos que marco el MC visitante
	 */
	public int getPBsEquipoV() {
		return PBsEquipoV;
	}
	/**
	 * Establece los puntos marcados por el MC visitante
	 * @param puntosEquipoL
	 */
	public void setPBsEquipoV(int PBsEquipoV) {
		this.PBsEquipoV = PBsEquipoV;
	}
	@Override
	public String toString() {
		return nomEquipoL + ": "+PBsEquipoL+" vs "+PBsEquipoV+" :"+nomEquipoV;
	}

}