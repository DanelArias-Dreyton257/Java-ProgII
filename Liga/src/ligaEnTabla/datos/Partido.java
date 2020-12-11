package ligaEnTabla.datos;
/**
 * 
 * @author Danel
 *
 */
public abstract class Partido {
	protected String nomEquipoL;
	protected String nomEquipoV;
	/**
	 * Constructor del partido solo con los nombres de los equipos
	 * @param nomEquipoL nombre del equipo local
	 * @param nomEquipoV nombre del equipo visitante
	 */
	public Partido(String nomEquipoL, String nomEquipoV ) {
		 setNomEquipoL(nomEquipoL);
		 setNomEquipoV(nomEquipoV);
	}
	/**
	 * Devuelve el nombre dle equipo local
	 * @return nombre del equipo local
	 */
	public String getNomEquipoL() {
		return nomEquipoL;
	}
	/**
	 * Establece el nombre del equipo local
	 * @param nomEquipoL
	 */
	public void setNomEquipoL(String nomEquipoL) {
		this.nomEquipoL = nomEquipoL;
	}
	/**
	 * Devuelve el nombre del equipo visitante
	 * @return nombre del equipo visitante
	 */
	public String getNomEquipoV() {
		return nomEquipoV;
	}
	/**
	 * Establece el nombre del equipo vistante
	 * @param nomEquipoV
	 */
	public void setNomEquipoV(String nomEquipoV) {
		this.nomEquipoV = nomEquipoV;
	}
	
}
