import java.util.ArrayList;
import java.util.Arrays;

public class Gif {
	private static final double ZOOM = 1;
	private static final double ROTATION = 0;
	private static final float OPACITY = 1.0f;
	private ArrayList<String> images = new ArrayList<>();
	private int currentIndex = 0;
	boolean lastFrame=false;
	boolean stopped=true;
	double x;
	double y;
	
	public Gif(double centroX, double centroY) {
		setCoor(centroX, centroY);
	}
	public void addImages(String...imagePaths) {
		images.addAll(Arrays.asList(imagePaths));
	}
	public String getNextImage() {
		String s = images.get(currentIndex);
		currentIndex++;
		if (currentIndex==(images.size())) {
			currentIndex=0;
			stopped=true;
		} 
		return s;
	}
	public String getStopImage() {
		return images.get(0);
	}
	public void setCoor(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	public void show(VentanaGrafica v) {
		String image;
		if (!stopped) image = getNextImage();
		else image = getStopImage(); 
		v.dibujaImagen(image, x, y, ZOOM, ROTATION, OPACITY);
		System.out.println(image);
	}
	
	public void startGif() {
		stopped=false;
	}
	
	public static void main(String[] args) {
		VentanaGrafica v = new VentanaGrafica(500, 500, "");
		Gif g = new Gif(250,250);
		g.addImages("Tanque.png");
		g.addImages(cogerImagenes("/IMAGENESDELGIF/Attack-",".png",0,15));
		v.setDibujadoInmediato(false);
		int cont=0;
		while(!v.estaCerrada()) {
			if (cont==50) {cont=0;g.startGif();}
			v.dibujaImagen("FFBE_Farplane_BG_1.png", 250, 250, ZOOM, ROTATION, OPACITY);
			g.show(v);
			v.repaint();
			v.espera(100);
			v.borra();
			cont++;
		}
	}
	private static String[] cogerImagenes(String pathSinNumSinExt, String ext, int minNum, int maxNum) {
		String[] imagenes = new String[maxNum-minNum+1];
		for (int i = minNum; i<=maxNum;i++) {
			imagenes[i-minNum]= pathSinNumSinExt+i+ext;
		}
		return imagenes;
	}
	
	
}
