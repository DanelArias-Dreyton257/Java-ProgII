package proy.audio;

import java.net.URL;
/**
 * 
 * @author Danel
 * Clase hija de Song que permite que la cancion solo se escuche si cumple ciertos porcentajes
 *
 */
public class EasterEggSong extends Song {

	private static final long serialVersionUID = 1L;

	private static final double MAX_PERCENTAJE = 100;
	private static final double MIN_PERCENTAJE= 0;

	private double percentaje=MAX_PERCENTAJE;

	/**
	 * Constructor de la clase cancion, el string que recibe es: nombreFichero +".wav"
	 * @param wavFileName
	 * @param percentaje Double del 0 al 100
	 */
	public EasterEggSong(String wavFileName, double percentaje) {
		super(wavFileName);
		setPercentaje(percentaje);
	}
	/**
	 * Constructor de la clase cancion, recibe una URL con el fichero wav de la cancion
	 * @param wavFileResource
	 * @param percentaje Double del 0 al 100
	 */
	public EasterEggSong(URL wavFileResource, double percentaje) {
		super(wavFileResource);
		setPercentaje(percentaje);
	}
	/**
	 * Devuelve true si la cancion fuera a sonar, cada vez que se llama genera un numero aleatorio
	 * entre 0 y 100, si ese numero se encuentra por debajo del porcentaje de aparicion entonces
	 * devolvera true, sino false
	 * @return
	 */
	private boolean isGoingToBePlayed() {
		java.util.Random r = new java.util.Random();
		double rNum=r.nextDouble()*MAX_PERCENTAJE;
		return rNum<=percentaje;
	}
	/**
	 * Devuelve el double que presenta el porcentaje de veces que suena la cancion
	 * @return
	 */
	public double getPercentaje() {
		return percentaje;
	}
	/**
	 * Establece el porcentaje de veces que suena la cancion
	 * Si el valor introducido es negativo o mayor que 100 no cambia el porcentaje anterior
	 * @param percentaje
	 */
	public void setPercentaje(double percentaje) {
		if (percentaje<=MAX_PERCENTAJE && percentaje>=MIN_PERCENTAJE) {
			this.percentaje = percentaje;
		}
	}

	@Override
	public void playSong() {
		if (isGoingToBePlayed()) super.playSong();
	}
	@Override
	public void playSongInThread() {
		if (isGoingToBePlayed()) super.playSongInThread();
	}
	@Override
	public void playLoopSongInThread() {
		if (isGoingToBePlayed()) super.playLoopSongInThread();
	}
	@Override
	public void playLoopSongInThread(int numOfTimes) {
		if (isGoingToBePlayed()) super.playLoopSongInThread(numOfTimes);
	}


}
