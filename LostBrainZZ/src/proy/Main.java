package proy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

import javax.swing.JOptionPane;

import proy.audio.Song;

//import javax.swing.JFrame;

import proy.completeGameObj.LostBrainZZGame;
import proy.moreObjects.RecordHolder;
import proy.visuals.*;

/**
 * 
 * @author Danel
 *
 */
public class Main {
	//variables relacionadas con el visionado del juego
	private static final int FPS=40;
	private static final long REFRESH_TIME=1000/FPS;
	private static final double SECS_INGAME_TMP_TITLES=1;
	private static final int TIME_BETWEEN_SCREENS=250;
	private static final int ZOMBIE_FPS_DIV_FACTOR = 6;
	private static final int WEAPON_ATK_FPS_DIV_FACTOR = ZOMBIE_FPS_DIV_FACTOR*2;
	private static final int ZOMBIE_MOVE_PACE = Math.round(FPS/ZOMBIE_FPS_DIV_FACTOR);
	private static final int WEAPON_ATTACK_PACE = Math.round(FPS/WEAPON_ATK_FPS_DIV_FACTOR);
	//RecordHolder
	private static final File R_H_FILE = new File("Record_Holder.dat");
	private static RecordHolder recordHolder=null;
	private static String currentUser = "Anonymous";
	//lógica para la gestión de cuando esta activo el juego
	private static boolean inGame = false;
	private static boolean isGameInitialized=false;
	private static boolean resuming=false;
	//Objetos para el desarrollo del juego
	private static MenuWindow menu=null;
	private static LostBrainZZGame game=null;
	private static VentanaGrafica v=null;
	//Para guardar la partida
	public static final String SAVE_FILE_NAME="Saved_Game.dat";


	public static void main(String[] args) {

		//Carga de record Holder
		loadRecordHolder();

		menu = new MenuWindow();

		//"Actualizacion" de los listeners del menu
		menu.getBtSave().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveGame();
			}
		});
		menu.getBtLoad().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadGame();
				inGame=true;
				isGameInitialized=true;
				resuming=true;
				JOptionPane.showMessageDialog(menu, "This game was created by "+game.getUserPlaying()+" in "+game.getCreatedDateToString(), "User", JOptionPane.WARNING_MESSAGE);
				menu.setVisible(false);
				runGame();
			}
		});
		menu.getBtResume().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inGame=true;
				menu.setVisible(false);
				resuming=true;
				runGame();
			}
		});
		menu.getUserWindow().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				currentUser=menu.getUserWindow().getUserSelected();
				inGame=true;
				isGameInitialized=false;
				runGame();
			}
		});
	}

	/**
	 * Carga la partida guardada en el archivo de guardado y lo establece como la partida actual
	 */
	private static void loadGame() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE_NAME));
			LostBrainZZGame loadedGame = (LostBrainZZGame) ois.readObject();
			ois.close();
			game=loadedGame;
			menu.setMessage("Game loaded successfully");
		}catch (IOException|ClassNotFoundException e) {
			menu.setMessage("Error in loading saved game");
			e.printStackTrace();
		}	

	}

	/**
	 * Guarda la partida actual en un archivo de guardado
	 */
	private static void saveGame() {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE_NAME));
					oos.writeObject(game);
					oos.close();
					menu.setMessage("Game saved successfully");
				} catch(IOException e) {
					menu.setMessage("Error in saving game");
					e.printStackTrace();
				}
			}
		}; t.start();
	}

	/**
	 * Carga el gestor de records del fichero correspondiente
	 */
	public static void loadRecordHolder() {
		if (R_H_FILE.exists()) {
			Thread t = new Thread() {
				@Override
				public void run() {
					try {
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(R_H_FILE.getAbsolutePath()));
						RecordHolder rh = (RecordHolder) ois.readObject();
						ois.close();
						recordHolder=rh;
					}catch (IOException|ClassNotFoundException e) {
						e.printStackTrace();
					}	
				}
			}; t.start();
		}
		else {
			recordHolder=new RecordHolder();
		}

	}

	/**
	 * Guarda en el fichero correspondiente el getsor de records
	 */
	public static void saveRecordHolder() {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(R_H_FILE));
					oos.writeObject(recordHolder);
					oos.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}; t.start();
	}

	/**
	 * Elimina el fichero de records 
	 */
	public static void deleteRecords() {
		if (R_H_FILE.exists()) R_H_FILE.delete();
		loadRecordHolder();
	}

	/**
	 * Ejecuta el juego
	 */
	private static void runGame() {
		Thread gameThread = new Thread() {
			@Override
			public void run() {
				try {
					v=LostBrainZZGame.getPreferredWindow();
					Song.setPlayingEnable(true);
					boolean finished=false;
					int cont =0;
					/**
					 * Bucle principal del juego
					 */
					while(inGame) {
						if (!v.estaCerrada()) {
							//Si no se ha inicializado el juego lo inicializa
							if (!isGameInitialized) {
								//Cojo los datos introducidos en la ventana de configuracion para que inicialize el juego
								ConfigWindow cg = menu.getConfigWindow();
								game = new LostBrainZZGame(currentUser,cg.getNumRows(), cg.getNumColumns(),cg.getNumRounds(),cg.getInitialMoney(),cg.getInitialBrains());
								cg.resetValuesToDefault();
								game.initTempTitles(SECS_INGAME_TMP_TITLES, FPS);
								isGameInitialized=true;

							}

							game.show(v);
							game.checkClicks(v);
							boolean exit = game.isExitToMenuButtonPressed();
							if (!exit && !finished) {
								//Hace las acciones principales del juego
								game.resumeSongs(resuming);//Si se vuelve al juego, reanuda las canciones correspondientes
								game.doBuyingSellingAndGameStateChanges();
								
								if (cont % WEAPON_ATTACK_PACE ==0) {
									game.fight();
								}
								
								if (cont % ZOMBIE_MOVE_PACE == 0) {
									
									game.zombieEntersLab();
									game.moveZombies();
								}
								
								cont++;
								if (cont==Integer.MAX_VALUE) cont=0;
								resuming=false;
								v.repaint();
								v.borra();
								v.espera(REFRESH_TIME);
								
								
								//Si se ha perdido la partida
								if (game.isOver()) {
									game.gameOverScreen(v);
									isGameInitialized=false;
									finished=true;
								}
								//Si se ha ganado la partida
								if (game.isWon()) {
									game.winScreen(v);
									isGameInitialized=false;
									finished=true;
								}	
								
							}
							//Sale de la pantalla de juego
							else if (exit) {
								v.espera(TIME_BETWEEN_SCREENS);
								v.borra();
								v.acaba();
								menu.setVisible(true);
								Song.setPlayingEnable(false);
								inGame=false;
							}
						}
						else {
							v.espera(TIME_BETWEEN_SCREENS);
							if (finished) {
								menu.getBtResume().setEnabled(false);
								menu.getBtSave().setEnabled(false);
							}
							menu.setVisible(true);
							Song.setPlayingEnable(false);
							inGame=false;
						}

					}
				}catch(Exception e) {
					v.acaba();
					menu.setVisible(true);
					menu.setMessage("Error in running the game, try again");
					e.printStackTrace();
				}
			}
		};
		gameThread.start();
	}

	/**
	 * Devuelve el gestor de records
	 * @return
	 */
	public static RecordHolder getRecordHolder() {
		return recordHolder;
	}

	/**
	 * Devuelve el menu utilizado en el Main
	 * @return
	 */
	public static MenuWindow getMenu() {
		return menu;
	}

}