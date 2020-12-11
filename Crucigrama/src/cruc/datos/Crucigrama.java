package cruc.datos;

import java.awt.*;
import java.io.Serializable;
import java.util.*;

import cruc.interfaces.*;
import cruc.visuales.VentanaGrafica;
/**
 * 
 * @author Eneko, Jon Ander y Danel
 *
 */
public class Crucigrama implements Visualizable,Corregible, Serializable,Comparable<Crucigrama>{
	
	private static final long serialVersionUID = 2L;
	Casilla[][] crucigrama;
	GrupoPreguntas preguntas;
	ArrayList<Cabezera>cabezeras= new ArrayList<Cabezera>();
	/**
	 * Constructor del crucigrama a traves de una plantilla de chars con las letras correctas en MAYUSCULAS y con un objeto Grupo de preguntas
	 * @param plantilla matriz de chars con las letras correctas en MAYUSCULAS
	 * @param preguntas Objeto GrupoPreguntas
	 */
	public Crucigrama (char[][] plantilla, GrupoPreguntas preguntas) { //TODO
		crucigrama = new Casilla[plantilla.length][plantilla[0].length];
		for (int i=0;i<plantilla.length;i++) {
			for (int j=0;j<plantilla[0].length;j++) {
				Casilla c = null;
				if (plantilla[i][j]!='-') c = new Casilla(i, j, plantilla[i][j]);
				this.crucigrama[i][j]= c ;
			}
		}
		setGrupoPreguntas(preguntas);
	}

	@Override
	public String toString() {
		String str="Crucigrama:[\n";
		for (Casilla[] fila: crucigrama) {
			str+="[";
			for (Casilla c: fila) {
				if (c!=null) str+=c.toString();
				else str+="null";
				str+=", ";
			}
			str+="]\n";
		}
		str+="]";
		return str;
	}
	
	public boolean esCorrecto() {
		boolean correcto=true;
		for (Casilla[] fila: crucigrama) {
			for (Casilla c:fila) {
				if (c!=null && !c.esCorrecto()) {
					correcto=false;
					break;
				}
			}
			if (!correcto) break;
		}
		return correcto;
	}
	/**
	 * Establece las posiciones y las fuentes de los objetos segun la altura y anchura maximas para la ocupacion del crucigrama en pantalla
	 * @param alturaMax
	 * @param anchuraMax
	 */
	public void colocarEnPantalla(int alturaMax, int anchuraMax ) {
		double lado = menorNum(anchuraMax/(crucigrama[0].length+1),alturaMax/(crucigrama.length+1));
		Casilla.setLado(lado);
		Casilla.initFuente();
		for (Casilla[] fila: crucigrama) {
			for (Casilla c:fila) {
				if (c!=null) { 
					c.initPosicion();
				}
			}
		}
		Cabezera.initFuente();
		for (Cabezera cab: cabezeras) {
			if (cab!=null) {
				cab.initCoor();
			}
		}
					
	}
	public void dibujar(VentanaGrafica v) {
		for (Casilla[] fila: crucigrama) {
			for (Casilla c:fila) {
				if (c!=null) c.dibujar(v);
			}
		}
		preguntas.dibujar(v);
		for (Cabezera cab: cabezeras) {
			if (cab!=null) cab.dibujar(v);
		}
	}
	/**
	 * Devuelve el objeto casilla en la cual esta el objeto Point
	 * @param pos Objeto Point
	 * @return objeto casilla, null si el Point no esta sobre ninguna casilla
	 */
	public Casilla getCasillaDentro(Point pos) {
		Casilla casillaPulsada = null;
		for (Casilla[] fila: crucigrama) {
			for (Casilla c:fila) {
				if (c!=null) {
					if (c.estaDentro(pos)) {
						casillaPulsada=c;
						break;
					}
				}
			}
			if (casillaPulsada!=null) break;
		}
		return casillaPulsada;
	}
	/**
	 * Metodo que devuelve el menor valor de los parametros introducido
	 * @param i
	 * @param j
	 * @return menorNum
	 */
	private double menorNum(double i, double j) {
		if (i>j) return j;
		else return i;
	}
	/**
	 * Devuelve el objeto GrupoPreguntas del crucigrama
	 * @return objeto GrupoPreguntas
	 */
	public GrupoPreguntas getPreguntas() {
		return preguntas;
	}
	/**
	 * Establece un nuevo grupo de preguntas a traves de una lista de preguntas
	 * @param preguntas
	 */
	public void setNuevasPreguntas(ArrayList<Pregunta>preguntas) {
		this.preguntas = new GrupoPreguntas(preguntas);
	}
	/**
	 * Devuelve la casilla referente a su posicion en el crucigrama
	 * @param numFila
	 * @param numColumna
	 * @return objeto casilla
	 */
	public Casilla getCasilla(int numFila, int numColumna) {
		return crucigrama[numFila][numColumna];
	}
	/**
	 * Establece el grupo de preguntas de crucigrama 
	 * @param preguntas
	 */
	private void setGrupoPreguntas(GrupoPreguntas preguntas) {
		this.preguntas=preguntas;
	}
	/**
	 * Establece la lista de cabezeras del crucigrama
	 * @param cabezeras
	 */
	public void setCabezeras(ArrayList<Cabezera> cabezeras) {
		this.cabezeras = cabezeras;
	}
	/**
	 * Devuelve una matriz de casillas que representa el crucigrama
	 * @return crucigrama
	 */
	public Casilla[][] getCrucigrama() {
		return crucigrama;
	}
	/**
	 * Devuelve una lista con las cabezeras del crucigrama
	 * @return ArrayList de cabezeras
	 */
	public ArrayList<Cabezera> getCabezeras() {
		return cabezeras;
	}

	@Override
	public boolean equals(Object obj) {
		boolean resultado = false;
		if (obj instanceof Crucigrama) {
			Crucigrama c = (Crucigrama) obj;
			resultado = crucigrama.equals(c.getCrucigrama()) && preguntas.equals(c.getPreguntas());
		}
		return resultado;
	}
	@Override
	public int hashCode() {
		return crucigrama.hashCode() + preguntas.hashCode();
	}

	@Override
	public int compareTo(Crucigrama o) {
		return hashCode()-o.hashCode();
	}
}