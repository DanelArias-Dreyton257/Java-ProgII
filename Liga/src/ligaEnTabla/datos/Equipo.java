package ligaEnTabla.datos;
/**
 * 
 * @author Danel
 *
 */
public abstract class Equipo {
	
	protected String nombre;
	protected int puntos=0;
	
	/**
	 * Constructor de equipo
	 * @param nombre
	 */
	public Equipo(String nombre){
		this.setNombre(nombre);
	}
	/** Añade al equipo el cálculo de puntos y demás estadísticos que corresponda a su
	 * comportamiento en un partido, dependiendo del deporte y de ser local o visitante
	 * @param p Partido en el que participa el equipo
	 */
	public abstract void calculaPartido( Partido p ); 
	
	/** Compara el equipo con otro equipo de acuerdo al criterio de clasificación en liga
	 * que corresponda a su deporte
	 * @param e2 Equipo con el que comparar
	 * @return true si el equipo es mejor que e2, false en caso contrario
	 */
	public abstract boolean esMejorQue( Equipo e2 ); 
	
	/**
	 * Metodo que pone el nombre al equipo
	 * @param nombre
	 */
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	/**
	 * Metodo que pone los puntos al equipo
	 * @param puntos
	 */
	public void setPuntos(int puntos){
		this.puntos=puntos;
	}
	/**
	 * Devuelve el nombre del equipo
	 * @return nombre del equipo
	 */
	public String getNombre() {
		return this.nombre;
	}
	/**
	 * Devuelve los puntos del equipo
	 * @return puntos del equipo
	 */
	public int getPuntos() {
		return this.puntos;
	}
	/**
	 * Aumenta los puntos del equipo segun el incremento, que seran los puntos que habra hecho en cada partido.
	 * @param incremento
	 */
	public void incPuntos(int incremento) {
		this.puntos+=incremento;
	}
	
	public String toString() {
		return "Equipo: " + this.nombre + " Puntos: "+ this.puntos;
	}
	
	@Override
	public boolean equals(Object obj) {
		Equipo q = (Equipo)obj;
		boolean r = this.nombre == q.nombre;
		return r;
	}

}
