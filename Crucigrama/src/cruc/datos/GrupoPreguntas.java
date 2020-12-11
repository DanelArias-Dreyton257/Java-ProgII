package cruc.datos;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.util.*;

import cruc.interfaces.Visualizable;
import cruc.visuales.VentanaGrafica;
/**
 * 
 * @author Eneko, Jon Ander y Danel
 *
 */
public class GrupoPreguntas implements Visualizable,Serializable,Comparable<GrupoPreguntas>{

	private static final long serialVersionUID = 2L;
	private ArrayList<Pregunta>verticales = new ArrayList<Pregunta>();
	private ArrayList<Pregunta>horizontales = new ArrayList<Pregunta>();
	static final Font FUENTE_TITULO = new Font("H-V", 1, 12);
	static final Font FUENTE_PREGUNTAS = new Font("Preguntas", 0, 12);
	static final Color COLOR = Color.BLACK;
	/**
	 * Constructor que crea un Grupo de Preguntas vacio
	 */
	public GrupoPreguntas(){
		
	}
	/**
	 * Constructor que crea un Grupo de Preguntas a partir de una lista de preguntas
	 * @param preguntas
	 */
	public GrupoPreguntas(ArrayList<Pregunta>preguntas){
		for (Pregunta preg: preguntas) {
			anyadePregunta(preg);
		}
	}
	/**
	 * Constructor que crea un Grupo de Preguntas a partir de una array de preguntas
	 * @param preguntas
	 */
	public GrupoPreguntas(Pregunta[] preguntas) {
		for (Pregunta preg: preguntas) {
			anyadePregunta(preg);
		}
	}
	/**
	 * Anyade un objeto pregunta al grupo de preguntas
	 * @param preg Objeto GrupoPreguntas
	 */
	public void anyadePregunta(Pregunta preg){
		if (preg.isHorizontal()) horizontales.add(preg);
		else verticales.add(preg);
	}
	/**
	 * Devuelve el objeto pregunta que se indica a traves de los parametros
	 * @param horizontal tue=> horizontal, false=> vertical
	 * @param numPreg
	 * @return objeto Pregunta
	 */
	public Pregunta getPregunta (boolean horizontal, int numPreg) {
		String str = "";
		if(horizontal) str="H";
		else str="V";
		return getPregunta(str+numPreg);
	}
	/**
	 * Devuelve el objeto pregunta que se indica a traves de los parametros
	 * @param cabezera String de formato 'H' o 'V' segun si es horizontal o vertical + el numPregunta
	 * @return objeto Pregunta
	 */
	public Pregunta getPregunta (String cabezera) {
		Pregunta preg=null;
		if (cabezera.charAt(0)=='V') {
			for (Pregunta pregV: verticales) {
				if (pregV.getCabezera().equals(cabezera)) preg=pregV;
			}
		}
		else {
			for (Pregunta pregH: horizontales) {
				if (pregH.getCabezera().equals(cabezera)) preg=pregH;
			}
		}
		return preg;
	}

	@Override
	public void dibujar(VentanaGrafica v) {
		int margenX = 10;
		int margenXSubLinea=35;
		int margenY = 25;
		int lengthMaxStr = 100;
		double x = v.getAnchura()/2 + margenX;
		double y = margenY;
		//titulo apartado verticales
		v.dibujaTexto(x, y, "VERTICALES", FUENTE_TITULO, COLOR);
		y+=margenY;
		//preguntas verticales
		for (Pregunta pregV: verticales) {
			
			String texto = pregV.getPreguntaCompleta();
			//Si el texto es muy largo lo separa y lo imprime en la siguiente linea
			if (texto.length()> lengthMaxStr) {
				v.dibujaTexto(x, y, texto.substring(0, lengthMaxStr)+"-", FUENTE_PREGUNTAS, COLOR);
				y+=margenY;
				v.dibujaTexto(x+margenXSubLinea, y, texto.substring(lengthMaxStr), FUENTE_PREGUNTAS, COLOR);
			}
			else {
				v.dibujaTexto(x, y, texto, FUENTE_PREGUNTAS, COLOR);
			}
			y+=margenY;
		}
		y+=margenY;
		//titulo apartado horizontales
		v.dibujaTexto(x, y, "HORIZONTALES", FUENTE_TITULO, COLOR);
		y+=margenY;
		//preguntas horizontales
		for (Pregunta pregH: horizontales) {
			
			String texto = pregH.getPreguntaCompleta();
			//Si el texto es muy largo lo separa y lo imprime en la siguiente linea
			if (texto.length()> lengthMaxStr) {
				v.dibujaTexto(x, y, texto.substring(0, lengthMaxStr)+"-", FUENTE_PREGUNTAS, COLOR);
				y+=margenY;
				v.dibujaTexto(x+margenXSubLinea, y, texto.substring(lengthMaxStr), FUENTE_PREGUNTAS, COLOR);
			}
			else {
				v.dibujaTexto(x, y, texto, FUENTE_PREGUNTAS, COLOR);
			}
			y+=margenY;
		}
	}
	/**
	 * Devuelve la lista con las preguntas verticales
	 * @return ArrayList de preguntas
	 */
	public ArrayList<Pregunta> getVerticales() {
		return verticales;
	}
	/**
	 * Devuelve la lista con las preguntas horizontales
	 * @return ArrayList de preguntas
	 */
	public ArrayList<Pregunta> getHorizontales() {
		return horizontales;
	}
	@Override
	public String toString() {
		return "Horizontales("+horizontales.size()+"):\n"+horizontales.toString()+"\nVerticales("+verticales.size()+"):\n"+verticales.toString();
	}
	@Override
	public boolean equals(Object obj) {
		boolean resultado = false;
		if (obj instanceof GrupoPreguntas) {
			GrupoPreguntas gp = (GrupoPreguntas) obj;
			resultado = horizontales.equals(gp.getHorizontales()) && verticales.equals(gp.getVerticales());
		}
		return resultado;
	}
	@Override
	public int hashCode() {
		return horizontales.hashCode() + verticales.hashCode();
	}
	@Override
	public int compareTo(GrupoPreguntas o) {
		return hashCode()-o.hashCode();
	}
}
