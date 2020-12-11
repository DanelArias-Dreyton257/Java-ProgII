package proy.visuals.utilObjects;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;

import proy.interfaces.Showable;
import proy.visuals.VentanaGrafica;
/**
 * 
 * @author Danel
 *
 */
public class StatIndicator implements Showable,Serializable{
	private static final long serialVersionUID = 1L;
	private static final int ZOOM=1;
	private static final int ROTATION=0;
	private static final float OPACITY=1.0f;
	private String iconFileName = "";
	private double x;
	private double y;
	private int value=0;
	private int height;
	private int width;
	private Font font = new Font("StatIndicator",1,25);
	private double imgX;
	private double imgY;
	private double textX;
	private double textY;
	private Color valueTextColor = Color.BLACK;
	private int logAnt=3;
	/**
	 * Constructor del Indicador de estadísticas
	 * @param x coordenada x de arriba a la izquierda
	 * @param y coordenada y de arriba a la izquierda
	 * @param height altura en pixeles
	 * @param width anchura en pixeles
	 * @param iconFileName	nombre del archivo con el icono del indicador
	 */
	public StatIndicator(double x, double y, int height, int width,String iconFileName) {
		setCoor(x,y);
		setIconFileName(iconFileName);
		setHeight(height);
		setWidth(width);
		setImgCoor(x+(height/2), y+(height/2));
		setTextCoor(x+height, y+height);
		updateFont();
	}
	/**
	 * Constructor del Indicador de estadísticas
	 * @param x coordenada x de arriba a la izquierda
	 * @param y coordenada y de arriba a la izquierda
	 * @param height altura en pixeles
	 * @param width anchura en pixeles
	 * @param iconFileName	nombre del archivo con el icono del indicador
	 * @param valueTextColor color del texto que indica el valor de la estadística
	 */
	public StatIndicator(double x, double y, int height, int width, String iconFileName, Color valueTextColor) {
		setCoor(x,y);
		setIconFileName(iconFileName);
		setHeight(height);
		setWidth(width);
		setImgCoor(x+(height/2), y+(height/2));
		setTextCoor(x+height, y+height);
		setValueTextColor(valueTextColor);
		updateFont();
	}
	/**
	 * Constructor del Indicador de estadísticas
	 * @param x coordenada x de arriba a la izquierda
	 * @param y coordenada y de arriba a la izquierda
	 * @param height altura en pixeles
	 * @param width anchura en pixeles
	 * @param value valor inicial de la estadística
	 * @param iconFileName	nombre del archivo con el icono del indicador
	 */
	public StatIndicator(double x, double y, int height, int width, int value,String iconFileName) {
		setCoor(x,y);
		setValue(value);
		setIconFileName(iconFileName);
		setHeight(height);
		setWidth(width);
		setImgCoor(x+(height/2), y+(height/2));
		setTextCoor(x+height, y+height);
		updateFont();
	}
	/**
	 * Constructor del Indicador de estadísticas
	 * @param x coordenada x de arriba a la izquierda
	 * @param y coordenada y de arriba a la izquierda
	 * @param height altura en pixeles
	 * @param width anchura en pixeles
	 * @param value valor inicial de la estadística
	 * @param iconFileName	nombre del archivo con el icono del indicador
	 * @param valueTextColor color del texto que indica el valor de la estadística
	 */
	public StatIndicator(double x, double y,int height, int width,int value,String iconFileName, Color valueTextColor) {
		setCoor(x,y);
		setValue(value);
		setIconFileName(iconFileName);
		setValueTextColor(valueTextColor);
		setHeight(height);
		setWidth(width);
		setImgCoor(x+(height/2), y+(height/2));
		setTextCoor(x+height, y+height);
		updateFont();
	}
	/**
	 * Devuelve el nombre del archivo que contiene la imagen icono
	 * @return String con el nombre del archivo
	 */
	public String getIconFileName() {
		return iconFileName;
	}
	/**
	 * Establece el nombre del archivo que contiene la imagen icono
	 * @param iconFileName
	 */
	public void setIconFileName(String iconFileName) {
		this.iconFileName = iconFileName;
	}
	/**
	 * Devuelve el valor de la estadística del indicador
	 * @return valor
	 */
	public int getValue() {
		return value;
	}
	/**
	 * Establece el valor de la estadística del indicador
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
		updateFont();
	}
	/**
	 * Devuelve la coordenada x de arriba a la izquierda
	 * @return coordenada x de arriba a la izquierda
	 */
	public double getX() {
		return x;
	}
	/**
	 * Devuelve la coordenada y de arriba a la izquierda
	 * @return coordenada y de arriba a la izquierda
	 */
	public double getY() {
		return y;
	}
	/**
	 * Establece la coordenada del indicador de estadísticas, la coordenada es la de arriba a la izquierda
	 * @param x coordenada x de arriba a la izquierda
	 * @param y coordenada y de arriba a la izquierda
	 */
	public void setCoor(double x,double y) {
		this.x=x;
		this.y=y;
	}
	/**
	 * Establece las coordenadas del icono
	 * @param x coordenada x del centro del icono
	 * @param y coordenada y del centro del icono
	 */
	private void setImgCoor(double x, double y) {
		this.imgX=x;
		this.imgY=y;
	}
	/**
	 * Establece las coordenadas del texto que muestra el valor
	 * @param x coordenada x de abajo a la izquierda del texto que muestra el valor
	 * @param y coordenada y de abajo a la izquierda del texto que muestra el valor
	 */
	private void setTextCoor(double x, double y) {
		this.textX=x;
		this.textY=y;
	}
	/**
	 * Devuelve el color del texto que muestra el valor
	 * @return valueTextColor
	 */
	public Color getValueTextColor() {
		return valueTextColor;
	}
	/**
	 * Establece el color del texto que muestra el valor
	 * @param valueTextColor
	 */
	public void setValueTextColor(Color valueTextColor) {
		this.valueTextColor = valueTextColor;
	}
	/**
	 * Devuelve la fuente que se usa para el texto que muestra el valor
	 * @return
	 */
	public Font getFont() {
		return this.font;
	}
	/**
	 * Devuelve la altura del indicador
	 * @return
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * Establece la altura del indicador
	 * @param height
	 */
	public void setHeight(int height) {
		this.height=height;
	}
	/**
	 * Devuelve la anchura del indicador
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * Establece la anchura del indicador
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * Aumenta el valor del indicador según el incremento pasado como parametro. valor+=incremento
	 * @param increment
	 */
	public void increaseValue(int increment) {
		value+=increment;
		updateFont();
	}
	/**
	 * Aumenta el valor del indicador en 1. valor++
	 */
	public void increaseValue() {
		value++;
		updateFont();
	}
	/**
	 * Decrementa el valor del indicador en 1. valor--
	 */
	public void decreaseValue() {
		value--;
		updateFont();
	}
	/**
	 * Decrementa el valor del indicador según el decremento pasado como parametro. valor-=decremento
	 * @param decrement
	 */
	public void decreaseValue(int decrement) {
		value-=decrement;
		updateFont();
	}
	/**
	 * Cambia el valor del indicador segun el factor pasado como parametro. valor= valor*factor
	 * @param changeFactor
	 */
	public void changeValueByFactor(double changeFactor) {
		value=(int)(value*changeFactor);
		updateFont();
	}
	/**
	 * Devuelve el valor del indicador a 0
	 */
	public void resetValue() {
		value=0;
		updateFont();
	}
	/**
	 * Actualiza el tamanyo de la fuente según la el valor del indicador
	 */
	private void updateFont() {
		int logAc = (int)Math.round(Math.log10(value));
		int decrement=2*(logAc-logAnt);
		int newFontSize = font.getSize()-decrement;
		if (newFontSize<=0) newFontSize=1;
		Font newFont = new Font(font.getName(), font.getStyle(), newFontSize);
		this.font = newFont;
		this.logAnt=logAc;

	}
	@Override
	public void show(VentanaGrafica v) {
		v.dibujaImagen(iconFileName, imgX, imgY, height, height, ZOOM, ROTATION, OPACITY);
		v.dibujaTexto(textX, textY,Integer.toString(value), font, valueTextColor);
	}
}
