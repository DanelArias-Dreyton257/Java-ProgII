package ligaEnTabla.datos;
/**
 * 
 * @author Danel
 *
 */
public class EquipoFMS extends Equipo {
	private int puntosBatalla=0;
	private int partidosG=0;
	private int partidosP=0;
	private int partidosGRep=0;
	private int partidosPRep=0;
	/**
	 * Constructor que recibe el nombre del equipo
	 * @param nombre
	 */
	public EquipoFMS(String nombre) {
		super(nombre);
	}
	/**
	 * Devuelve los puntos de batalla conseguidos por el MC
	 * @return int puntos de batalla
	 */
	public int getPuntosBatalla() {
		return puntosBatalla;
	}
	/**
	 * Establece los puntos de batalla
	 * @param puntosBatalla
	 */
	public void setPuntosBatalla(int puntosBatalla) {
		this.puntosBatalla = puntosBatalla;
	}
	/**
	 * Devuelve el numero de batallas ganadas por el MC
	 * @return numero batallas ganados
	 */
	public int getPartidosG() {
		return partidosG;
	}
	/**
	 * Establece el numero de partidos ganados
	 * @param partidosG
	 */
	public void setPartidosG(int partidosG) {
		this.partidosG = partidosG;
	}
	/**
	 * Devuelve el numero de batallas perdidas por el MC
	 * @return numero de batallas perdidas
	 */
	public int getPartidosP() {
		return partidosP;
	}
	/**
	 * Establece el numero de partidos perdidos
	 * @param partidosP
	 */
	public void setPartidosP(int partidosP) {
		this.partidosP = partidosP;
	}
	/**
	 * Devuelve el numero de batallas ganadas con replica por el MC
	 * @return numero de batallas ganadas con replica
	 */
	public int getPartidosGRep() {
		return partidosGRep;
	}
	/**
	 * Establece el numero de partidos ganados con replica
	 * @param partidosGRep
	 */
	public void setPartidosGRep(int partidosGRep) {
		this.partidosGRep = partidosGRep;
	}
	/**
	 * Devuelve el numero de batallas perdidas con replica por el MC
	 * @return numero de batallas perdidas con replica
	 */
	public int getPartidosPRep() {
		return partidosPRep;
	}
	/**
	 * Establece el numero de partidos perdidos con replica
	 * @param partidosPRep
	 */
	public void setPartidosPRep(int partidosPRep) {
		this.partidosPRep = partidosPRep;
	}
	/**
	 * Calcula e implementa todas las estadísticas
	 * @param p Objeto Partido
	 */
	public void calculaPartido(Partido p) {
		PartidoFMS pt=(PartidoFMS)p;
		int PTS_GANADOR=3;
		int PTS_GAN_EMPATE=2;
		int PTS_PER_EMPATE=1;
		int DIF_REPLICA=10;
		
		if(pt.nomEquipoL.equals(this.nombre)){
			this.setPuntosBatalla(puntosBatalla+pt.getPBsEquipoL());
			//gana directo
			if(pt.getPBsEquipoL()>pt.getPBsEquipoV()+DIF_REPLICA) {
				this.incPuntos(PTS_GANADOR);
				this.setPartidosG(partidosG+1);
			}
			//pierde directo
			else if(pt.getPBsEquipoV()>pt.getPBsEquipoL()+DIF_REPLICA) {
				this.setPartidosP(partidosP+1);
			}
			//gana con replica
			else if(pt.getPBsEquipoL()<=pt.getPBsEquipoV()+DIF_REPLICA && pt.getPBsEquipoL()>pt.getPBsEquipoV()) {
				this.incPuntos(PTS_GAN_EMPATE);
				this.setPartidosGRep(partidosGRep+1);
			}
			//pierde con replica
			else if(pt.getPBsEquipoL()<=pt.getPBsEquipoV()+DIF_REPLICA && pt.getPBsEquipoL()<pt.getPBsEquipoV()) {
				this.incPuntos(PTS_PER_EMPATE);
				this.setPartidosPRep(partidosPRep+1);
			}
			//empate total
			else if(pt.getPBsEquipoL()==pt.getPBsEquipoV()) {
				this.incPuntos(PTS_PER_EMPATE);
				this.setPartidosPRep(partidosPRep+1);
			}
		}
		else if (pt.nomEquipoV.equals(this.nombre)) {
			this.setPuntosBatalla(puntosBatalla+pt.getPBsEquipoV());
			//gana directo
			if(pt.getPBsEquipoV()>pt.getPBsEquipoL()+DIF_REPLICA) {
				this.incPuntos(PTS_GANADOR);
				this.setPartidosG(partidosG+1);
			}
			//pierde directo
			else if(pt.getPBsEquipoL()>pt.getPBsEquipoV()+DIF_REPLICA) {
				this.setPartidosP(partidosP+1);
			}
			//gana con replica
			else if(pt.getPBsEquipoV()<=pt.getPBsEquipoL()+DIF_REPLICA && pt.getPBsEquipoV()>pt.getPBsEquipoL()) {
				this.incPuntos(PTS_GAN_EMPATE);
				this.setPartidosGRep(partidosGRep+1);
			}
			//pierde con replica
			else if(pt.getPBsEquipoV()<=pt.getPBsEquipoL()+DIF_REPLICA && pt.getPBsEquipoV()<pt.getPBsEquipoL()) {
				this.incPuntos(PTS_PER_EMPATE);
				this.setPartidosPRep(partidosPRep+1);
			}
			//empate total
			else if(pt.getPBsEquipoV()==pt.getPBsEquipoL()) {
				this.incPuntos(PTS_PER_EMPATE);
				this.setPartidosPRep(partidosPRep+1);
			}
		}
		
		
	}
	/**
	 * Comprueba si un MC debería estar por encima en la clasificación
	 */
	public boolean esMejorQue(Equipo e2) {
		EquipoFMS ef2 =(EquipoFMS) e2;
		boolean result=false;
		if (!this.equals(ef2)) {
			if (this.puntos>ef2.puntos) {
				result=true;
			}
			else if (this.puntos==ef2.puntos && this.puntosBatalla>ef2.puntosBatalla) {
				result=true;
			}
		}
		return result;
	}
	@Override
	public String toString() {
		return super.toString()+" PBs: "+puntosBatalla+"\n  BG/BGRep/BPRep/BP: "+partidosG+"/"+partidosGRep+"/"+partidosPRep+"/"+partidosP;
	}
}

