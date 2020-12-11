package ligaEnTabla.datos;
/**
 * 
 * @author Danel
 *
 */
public class EquipoFutbol extends Equipo{
	private int golesFavor=0;
	private int golesContra=0;
	private int partidosG=0;
	private int partidosE=0;
	private int partidosP=0;
	public EquipoFutbol(String nombre) {
		super(nombre);
	}
	/**
	 * Devuelve los goles a favor del equipo
	 * @return goles a favor del equipo
	 */
	public int getGolesFavor() {
		return golesFavor;
	}
	/**
	 * Metodo que pone los goles a favor del equipo
	 * @param goles a favor del equipo
	 */
	public void setGolesFavor(int d) {
		this.golesFavor = d;
	}
	/**
	 * Devuelve los goles en contra del equipo
	 * @return goles en contra del equipo
	 */
	public int getGolesContra() {
		return golesContra;
	}
	/**
	 * Metodo que pone los goles en contra del equipo
	 * @param goles en contra del equipo
	 */
	public void setGolesContra(int d) {
		this.golesContra = d;
	}
	/**
	 * Devuelve los partidos ganados por el equipo
	 * @return partidos ganados por el equipo
	 */
	public int getPartidosG() {
		return partidosG;
	}
	/**
	 * Metodo que pone los partidos ganados por el equipo
	 * @param partidos ganados por el equipo
	 */
	public void setPartidosG(int partidosG) {
		this.partidosG = partidosG;
	}
	/**
	 * Devuelve los partidos empatados por el equipo
	 * @return partidos empatados por el equipo
	 */
	public int getPartidosE() {
		return partidosE;
	}
	/**
	 * Metodo que pone los partidos empatados por el equipo
	 * @param partidos empatados por el equipo
	 */
	public void setPartidosE(int partidosE) {
		this.partidosE = partidosE;
	}
	/**
	 * Devuelve los partidos perdidos por el equipo
	 * @return partidos perdidos por el equipo
	 */
	public int getPartidosP() {
		return partidosP;
	}
	/**
	 * Metodo que pone los partidos perdidos por el equipo
	 * @param partidos perdidos por el equipo
	 */
	public void setPartidosP(int partidosP) {
		this.partidosP = partidosP;
	}
	/**
	 * Calcula e implementa todas las estadísticas
	 * @param p Objeto Partido
	 */
	public void calculaPartido(Partido p) {
		PartidoFutbol pt=(PartidoFutbol)p;
		int PTS_GANADOR=3;
		int PTS_EMPATE=1;
		if(pt.nomEquipoL.equals(this.nombre) && pt.getGolesEquipoL()>pt.getGolesEquipoV()) {
			this.incPuntos(PTS_GANADOR);
			setGolesFavor(golesFavor+pt.getGolesEquipoL());
			setGolesContra(golesContra+pt.getGolesEquipoV());
			setPartidosG(partidosG+1);
		}
		else if (pt.nomEquipoV.equals(this.nombre) && pt.getGolesEquipoV()>pt.getGolesEquipoL()) {
			this.incPuntos(PTS_GANADOR);
			setGolesFavor(golesFavor+pt.getGolesEquipoV());
			setGolesContra(golesContra+pt.getGolesEquipoL());
			setPartidosG(partidosG+1);
		}
		else if (pt.nomEquipoL.equals(this.nombre) && pt.getGolesEquipoL()==pt.getGolesEquipoV()) {
			this.incPuntos(PTS_EMPATE);
			setGolesFavor(golesFavor+pt.getGolesEquipoL());
			setGolesContra(golesContra+pt.getGolesEquipoV());
			setPartidosE(partidosE+1);
		}
		else if(pt.nomEquipoV.equals(this.nombre) && pt.getGolesEquipoL()==pt.getGolesEquipoV()) {
			this.incPuntos(PTS_EMPATE);
			setGolesFavor(golesFavor+pt.getGolesEquipoV());
			setGolesContra(golesContra+pt.getGolesEquipoL());
			setPartidosE(partidosE+1);
		}
		else if (pt.nomEquipoL.equals(this.nombre)){
			setGolesFavor(golesFavor+pt.getGolesEquipoL());
			setGolesContra(golesContra+pt.getGolesEquipoV());
			setPartidosP(partidosP+1);
		}
		else if (pt.nomEquipoV.equals(this.nombre)) {
			setGolesFavor(golesFavor+pt.getGolesEquipoV());
			setGolesContra(golesContra+pt.getGolesEquipoL());
			setPartidosP(partidosP+1);
		}
		
	}
	/**
	 * Comprueba si un equipo debería estar por encima en la clasificación
	 */
	public boolean esMejorQue(Equipo e2) {
		EquipoFutbol ef2 =(EquipoFutbol) e2;
		boolean result=false;
		if (!this.equals(ef2)) {
			if (this.puntos>ef2.puntos) {
				result=true;
			}
			else if (this.puntos==ef2.puntos && this.golesFavor-this.golesContra>ef2.golesFavor-ef2.golesContra) {
				result=true;
			}
			else if (this.puntos==ef2.puntos && this.golesFavor-this.golesContra==ef2.golesFavor-ef2.golesContra && this.getGolesFavor() > ef2.getGolesFavor()) {
				result=true;
			}
		}
		return result;
	}
	@Override
	public String toString() {
		return super.toString()+"\n  GF/GC: "+golesFavor+"/"+golesContra+"\n  PG/PE/PP: "+partidosG+"/"+partidosE+"/"+partidosP;
	}

}
