package ligaEnTabla.datos;
/**
 * 
 * @author Danel
 *
 */
public class EquipoBaloncesto extends Equipo{
	private int puntosFavor=0;
	private int puntosContra=0;
	private int partidosG=0;
	private int partidosP=0;
	/**
	 * Constructor que recibe el nombre del equipo
	 * @param nombre
	 */
	public EquipoBaloncesto(String nombre) {
		super(nombre);
	}
	/**
	 * Devuelve los puntos a favor del equipo
	 * @return puntos a favor del equipo
	 */
	public int getPuntosFavor() {
		return puntosFavor;
	}
	/**
	 * Metodo que pone los puntos a favor del equipo
	 * @param puntos a favor del equipo
	 */
	public void setPuntosFavor(int d) {
		this.puntosFavor = d;
	}
	/**
	 * Devuelve los puntos en contra del equipo
	 * @return puntos en contra del equipo
	 */
	public int getPuntosContra() {
		return puntosContra;
	}
	/**
	 * Metodo que pone los puntos en contra del equipo
	 * @param puntos en contra del equipo
	 */
	public void setPuntosContra(int d) {
		this.puntosContra = d;
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
		PartidoBaloncesto pt=(PartidoBaloncesto)p;

		if(pt.nomEquipoL.equals(this.nombre) && pt.getPuntosEquipoL()>pt.getPuntosEquipoV()) {
			setPuntosFavor(puntosFavor+pt.getPuntosEquipoL());
			setPuntosContra(puntosContra+pt.getPuntosEquipoV());
			setPartidosG(partidosG+1);
		}
		else if (pt.nomEquipoV.equals(this.nombre) && pt.getPuntosEquipoV()>pt.getPuntosEquipoL()) {
			setPuntosFavor(puntosFavor+pt.getPuntosEquipoV());
			setPuntosContra(puntosContra+pt.getPuntosEquipoL());
			setPartidosG(partidosG+1);
		}
		else if (pt.nomEquipoL.equals(this.nombre)){
			setPuntosFavor(puntosFavor+pt.getPuntosEquipoL());
			setPuntosContra(puntosContra+pt.getPuntosEquipoV());
			setPartidosP(partidosP+1);
		}
		else if (pt.nomEquipoV.equals(this.nombre)) {
			setPuntosFavor(puntosFavor+pt.getPuntosEquipoV());
			setPuntosContra(puntosContra+pt.getPuntosEquipoL());
			setPartidosP(partidosP+1);
		}
		
	}
	/**
	 * Comprueba si un equipo debería estar por encima en la clasificación
	 */
	public boolean esMejorQue(Equipo e2) {
		boolean result=false;
		EquipoBaloncesto eb2 =(EquipoBaloncesto) e2;
		if (!this.equals(eb2)) {
			if (this.partidosG>eb2.partidosG) {
				result=true;
			}
			else if (this.partidosG==eb2.partidosG && this.puntosFavor-this.puntosContra>eb2.puntosFavor-eb2.puntosContra) {
				result=true;
			}
			else if (this.puntos==eb2.puntos && this.puntosFavor-this.puntosContra==eb2.puntosFavor-eb2.puntosContra && this.getPuntosFavor() > eb2.getPuntosFavor()) {
				result=true;
			}
		}
		return result;
	}
	@Override
	public String toString() {
		return "Equipo: "+this.nombre+"\n  PG/PP: "+this.partidosG+"/"+this.partidosP+"\n  GF/GC: "+puntosFavor+"/"+puntosContra;
	}
}

