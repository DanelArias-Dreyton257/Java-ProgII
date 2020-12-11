package proy.visuals.utilObjects;

import proy.unused.visuals.utilObjects.SimpleButton;
import proy.visuals.VentanaGrafica;
/**
 * 
 * @author Danel
 *
 */
public class ImageButton extends SimpleButton {
	private static final long serialVersionUID = 1L;
	private String imgFileName="";
	private static final float OPACITY= 1.0f;
	private static final int ROTATION= 0;
	private static final int ZOOM=1;
	/**
	 * Constructor del objeto boton con una imagen
	 * @param x coordenada x de arriba a la izquierda
	 * @param y coordenada y de arriba a la izquierda
	 * @param height
	 * @param width
	 * @param imgFileName
	 */
	public ImageButton(double x, double y, int height, int width, String imgFileName) {
		super(x, y, height, width);
		setImgFileName(imgFileName);
	}
	/**
	 * Devuelve el nombre del archivo con la imagen del boton
	 * @return
	 */
	public String getImgFileName() {
		return imgFileName;
	}
	/**
	 * Establece el nombre del archivo con la imagen del boton
	 * @param imgFileName
	 */
	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public void show (VentanaGrafica v) {
		v.dibujaImagen(imgFileName, x+(width/2), y+(height/2), width, height, ZOOM, ROTATION, OPACITY);
	}
}
