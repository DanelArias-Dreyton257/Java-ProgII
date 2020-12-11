package proy.audio;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
/**
 * 
 * @author Danel
 *	
 */
public class Song implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final long AVERAGE_WAIT_TO_STOP_MILLIS = 500; 

	private static boolean playingEnabled=true; //Controla si la clase permite que se reproduzcan audios

	private URL songFileResource; 
	private boolean audioPlaying =false;
	private boolean inLoop = false;

	/**
	 * Constructor de la clase cancion, el string que recibe es: nombreFichero +".wav"
	 * @param wavFileName
	 */
	public Song(String wavFileName) {
		this(Song.class.getResource(wavFileName));
	}
	/**
	 * Constructor de la clase cancion, recibe una URL con el fichero wav de la cancion
	 * @param wavFileResource
	 */
	public Song(URL wavFileResource){
		setSongFileResource(wavFileResource);
	}
	/**Metodo modificado cogido de Audio.java de la practica 9 
	 * Lanza un audio indicado en el fichero wav indicado en el constructor
	 * 	si el fichero wav no se encuentra no reproduce nada
	 */
	public void playSong() {
		int BUFFER_SIZE = 128000;
		AudioInputStream flujoAudio = null;
		AudioFormat formatoAudio = null;
		SourceDataLine lineaDatosSonido = null;
		try {
			flujoAudio = AudioSystem.getAudioInputStream(songFileResource);
			formatoAudio = flujoAudio.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, formatoAudio);
			lineaDatosSonido = (SourceDataLine) AudioSystem.getLine(info);
			lineaDatosSonido.open(formatoAudio);
			lineaDatosSonido.start();
			int bytesLeidos = 0;
			byte[] bytesAudio = new byte[BUFFER_SIZE];
			audioPlaying=true;
			while (bytesLeidos != -1 & audioPlaying && playingEnabled) {
				try {
					bytesLeidos = flujoAudio.read(bytesAudio, 0, bytesAudio.length);
				} catch (IOException e) { }
				if (bytesLeidos >= 0) {
					lineaDatosSonido.write(bytesAudio, 0, bytesLeidos);
				}
			}
		} catch (Exception e) {
			// Excepci�n si el fichero es nulo, err�neo, o wav incorrecto
		}
		if (lineaDatosSonido != null) {
			lineaDatosSonido.drain();
			try {
				lineaDatosSonido.close();
				flujoAudio.close();
			} catch (Exception e) {}
		}
		audioPlaying=false;
	}
	/**
	 * Reproduce la cancion en un nuevo hilo con el metodo playSong()
	 */
	public void playSongInThread() {
		Thread s = new Thread() {
			@Override
			public void run() {
				playSong();
			}
		};s.start();
	}
	/**
	 * Reproduce la cancion en un nuevo hilo con el metodo playSong()
	 * tras esperar los milisegundos indicados
	 */
	public void playSongInThreadAfterWait(long millis) {
		Thread s = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(millis);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				playSong();
			}
		};s.start();
	}
	/**
	 * Reproduce la cancion en bucle en un nuevo hilo
	 */
	public void playLoopSongInThread() {
		Thread l = new Thread() {
			@Override
			public void run() {
				inLoop=true;
				while (inLoop && playingEnabled) {
					playSong();
				}
			}
		};l.start();
	}
	/**
	 * Reproduce la cancion en numOfTimes veces en un nuevo hilo
	 */
	public void playLoopSongInThread(int numOfTimes) {
		Thread h = new Thread() {
			@Override
			public void run() {
				int num=numOfTimes;
				inLoop=true;
				while(num>0 && inLoop && playingEnabled) {
					playSong();
					num--;
				}
			}
		}; h.start();
	}
	/**
	 * Devuelve la duracion de la cancion en milisegundos
	 * @return song duration in millis
	 */
	public long getDuration(){   
		AudioInputStream stream = null;
		String path = songFileResource.getFile();
		File f = new File(path);

		try {
			stream = AudioSystem.getAudioInputStream(songFileResource);

			AudioFormat format = stream.getFormat();

			return (long) Math.floor(1000 * f.length() / format.getSampleRate() / (format.getSampleSizeInBits() / 8.0) / format.getChannels());
		}
		catch (Exception e) {
			System.err.println("Error in calculating song Duration");
			return -1;
		}
		finally{
			try { stream.close(); } catch (Exception ex) { }
		}
		
	}
	/**
	 * Hace que la cancion pare de sonar, si la cancion esta en bucle solo para la reproduccion
	 * de la que actualmente esta sonando
	 */
	public void stopPlaying() {
		audioPlaying=false;
	}
	/**
	 * Hace que la cancion pare de sonar, y si la cancion esta en bucle para el bucle tambien
	 */
	public void stopLoopPlaying() {
		inLoop=false;
		audioPlaying=false;
	}
	/**
	 * Establece la URL con el fichero wav de la cancion
	 * @param resource
	 */
	private void setSongFileResource(URL resource) {
		this.songFileResource=resource;
	}
	/*
	 * Devuelve si la cancion esta sonando
	 */
	public boolean isPlaying() {
		return audioPlaying;
	}
	/**
	 * Devuelve si la clase esta activada para reproducir canciones
	 * @return
	 */
	public static boolean isPlayigEnabled() {
		return playingEnabled;
	}
	/**
	 * Establece si la clase esta activada para reproducir canciones
	 * @param b
	 */
	public static void setPlayingEnable(boolean b) {
		playingEnabled=b;
	}
	/**
	 * Devuelve si la cancione esta en bucle
	 * @return
	 */
	public boolean isInLoop() {
		return inLoop;
	}
	/**
	 * Establece si la cancion esta en bucle
	 * @param b
	 */
	public void setInLoop(boolean b) {
		this.inLoop = b;
	}
	@Override
	public String toString() {
		return "S:"+songFileResource+"("+getDuration()+" millis)";
	}
}
