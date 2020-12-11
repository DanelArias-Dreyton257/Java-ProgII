package ligaEnTabla.visual;

import java.awt.Color;
import java.awt.Font;

import ligaEnTabla.datos.Clasificacion;
/**
 * 
 * @author Danel
 *
 */
public abstract class Tabla {
	protected int[] anchuras; 
	protected static final int ALTURA_CASILLA = 25;
	protected static final int X_ORIGEN = 20;
	protected static final int Y_ORIGEN = 45;
	protected static final Font FUENTE = new Font("Arial", Font.PLAIN, 20);
	protected static final int MARGEN=3;
	protected Clasificacion clasif;
	/**
	 * inicializa la tabla dada la clasificacion
	 * @param clasif objeto ClasificacionFutbol
	 */
	public Tabla (Clasificacion clasif) {
		this.setClasif(clasif);
	}
	/**
	 * Devuelve clasificacion
	 * @return Clasificacion
	 */
	public Clasificacion getClasif() {
		return clasif;
	}
	/**
	 * Cambia clasificacion por la dada
	 * @param clasif Clasificacion
	 */
	public void setClasif(Clasificacion clasif) {
		this.clasif = clasif;
	}
	/**
	 * Establece el array con las anchuras de las casillas
	 * @param Anchuras
	 */
	public void setAnchuras(int[] Anchuras) {
		this.anchuras = Anchuras;
	}
	/**
	 * Dibuja en Ventana Grafica la tabla con todos los datos de la clasificacion
	 * @param v VentanaGrafica
	 */
	public void dibujaCompleta(VentanaGrafica v) {
		dibujarCabezera(v);
		dibujarGraficos(v);
		dibujarTexto(v);
		dibujarMalla(v);
		
	}
	/**
	 * Dibuja la cabezera de la tabla
	 * @param v Objeto VentanaGrafica
	 */
	protected abstract void dibujarCabezera(VentanaGrafica v);
	/**
	 * Dibuja el apartado de graficos en la tabla
	 * @param v Objeto VentanGrafica
	 */
	protected abstract void dibujarGraficos(VentanaGrafica v);
	/**
	 * dibuja todo el apartado de texto en la tabla
	 * @param v Objeto VentanaGrafica
	 */
	protected abstract void dibujarTexto(VentanaGrafica v);

	/**
	 * Dibuja la malla de lineas negras que marcan las casillas de la tabla 
	 * @param v VentanaGrafica
	 */
	protected void dibujarMalla(VentanaGrafica v) {
		//lineas verticales
		int xValor=X_ORIGEN;
		for(int j=0;j<anchuras.length+1;j++) {
			v.dibujaLinea(xValor, Y_ORIGEN, xValor, Y_ORIGEN+(clasif.getEquipos().length*ALTURA_CASILLA), 2.0f, Color.BLACK);
			if (j<anchuras.length) {xValor+=anchuras[j];}
		}
		//lineas horizontales
		int yValor=Y_ORIGEN;
		int sumAnch=0;
		for(int anch:anchuras) {
			sumAnch+=anch;
		}
		for(int i=0;i<clasif.getEquipos().length+1;i++) {
			v.dibujaLinea(X_ORIGEN, yValor, X_ORIGEN+sumAnch, yValor, 2.0f, Color.BLACK);
			yValor+=ALTURA_CASILLA;
		}
	}
}
