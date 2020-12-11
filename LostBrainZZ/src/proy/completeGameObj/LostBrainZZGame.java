package proy.completeGameObj;

import java.awt.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

import proy.Main;
import proy.audio.EasterEggSong;
import proy.audio.Song;
import proy.gameObjects.*;
import proy.interfaces.Showable;
import proy.moreObjects.ZombieHorde;
import proy.objectMatrix.*;
import proy.visuals.utilObjects.*;
import proy.visuals.*;
/**
 * 
 * @author Danel
 *
 */
public class LostBrainZZGame implements Serializable,Showable {

	private static final long serialVersionUID = 2L;

	private static final String TITLE="LOST BRAINZZ";
	//Constantes para el dibujado en ventana
	private static final int MENU_MARGIN = 80;
	private static final int STATS_MARGIN = 110;
	private static final int HORDE_BAR_MARGIN = 200;
	private static final int HEIGHT=700;
	private static final int WIDTH=HEIGHT;
	private static final int GENERAL_MARGIN = 10;
	private static final int BUTTON_SIZE = 100;
	private static final int INDICATOR_HEIGHT=40;
	private static final int FINAL_IMG_HEIGHT=200;
	private static final int FINAL_IMG_WIDTH=500;
	private static final double FINAL_IMG_ZOOM=1.5;
	private static final float FINAL_IMG_OPACITY=1.0f;
	private static final int Y_ROUND=GENERAL_MARGIN;
	private static final int Y_BRAINS=Y_ROUND+INDICATOR_HEIGHT+GENERAL_MARGIN;
	private static final int Y_MONEY=INDICATOR_HEIGHT+Y_BRAINS+GENERAL_MARGIN;
	private static final int Y_C_BRAIN = Y_MONEY+INDICATOR_HEIGHT+GENERAL_MARGIN;
	private static final int Y_QUIT_WALL = Y_C_BRAIN + BUTTON_SIZE + GENERAL_MARGIN;
	private static final int Y_EX_MENU = HEIGHT-BUTTON_SIZE-GENERAL_MARGIN;
	private static final int Y_BP_EX = Y_EX_MENU-BUTTON_SIZE-GENERAL_MARGIN;
	private static final int Y_HORDE_BAR = (2*MENU_MARGIN)/7;
	private static final int Y_BP_TITLE = MENU_MARGIN-GENERAL_MARGIN;
	private static final int Y_ROUND_TITLE = MENU_MARGIN-GENERAL_MARGIN*2;
	private static final int HORDE_BAR_HEIGHT = (3*MENU_MARGIN)/7;
	private static final int HORDE_BAR_WIDTH = WIDTH-STATS_MARGIN-HORDE_BAR_MARGIN-GENERAL_MARGIN;
	private static final Font FINAL_FONT = new Font("finalFont",1,25);
	//Constantes publicas sobre el juego
	public static final int MIN_NUM_ROWS = 10;
	public static final int MIN_NUM_COLUMNS = 10;
	public static final int MAX_NUM_ROWS = 30;
	public static final int MAX_NUM_COLUMNS = 30;
	public static final int DEFAULT_NUM_ROWS=20;
	public static final int DEFAULT_NUM_COLUMNS=20;
	public static final int DEFAULT_NUM_ROUNDS=100;
	public static final int DEFAULT_INITIAL_MONEY=15000;
	public static final int DEFAULT_INITIAL_BRAINS=0;
	//Imagenes
	private static final String EX_MENU_ICON = "images/X_pxArt.png";
	private static final String BP_EX_ICON = "images/BP_exit_pxArt.png";
	private static final String COIN_ICON = "images/Coin_pxArt.png";
	private static final String BRAIN_ICON = "images/Brain_pxArt.png";
	private static final String WALL_O_ICON = "images/WallO_pxArt.png";
	private static final String WALL_X_ICON = "images/WallX_pxArt.png";
	private static final String ROUND_ICON = "images/Round_pxArt.png";
	private static final String C_BRAIN_ICON = "images/ConvertBrain_pxArt.png";
	private static final String GAME_OVER_IMG = "images/GAME_OVER.png";
	private static final String YOU_WIN_IMG = "images/YOU_WIN.png";
	//Sonidos y canciones
	private static final Song[] BP_SONGS = {new Song("audios/BP_sound_1.wav"),new Song("audios/BP_sound_2.wav"),new Song("audios/BP_sound_3.wav"),new Song("audios/BP_sound_4.wav")};
	private static final Song[] ROUND_SONGS = {new Song("audios/R_sound_1.wav"),new Song("audios/R_sound_2.wav"),new Song("audios/R_sound_3.wav"),new Song("audios/R_sound_4.wav")};
	private static final Song[] BRAINS_SONGS = {new Song("audios/Brains_sound_1.wav"),new Song("audios/Brains_sound_2.wav"),new Song("audios/Brains_sound_3.wav"),new Song("audios/Brains_sound_4.wav"),new Song("audios/Brains_sound_SMASH.wav")};
	private static final Song WIN_SONG = new Song("audios/Period_P4_Shoji-Meguro.wav");
	private static final Song GAME_OV_SONG = new Song("audios/Tasogare no Tansaku_DGR2_Takada-Masafumi.wav");
	private static final Song GAME_OV_SOUND = new Song("audios/GameOver_sound.wav");
	private static final Song BP_BG_SONG = new Song("audios/Playing with a Full Deck_HS_Peter-McConnel.wav");
	private static final Song FIGHT_SONG = new Song("audios/Consternation_VLR_Shinji-Hosoe.wav");
	private static final Song SELL_SOUND = new Song("audios/Money_sound.wav");
	private static final Song PLACE_SOUND = new Song("audios/Place_Obj_sound.wav");
	private static final EasterEggSong EE_PLACE_WALL = new EasterEggSong("audios/Wall_sound.wav", 10);

	//User Easter Egg names
	private static final String[] EE_USERS = {"Andoni","Anonymous","Benat","Danel","Dani","David","DIO","Dreyton","Eneko","Gandalf","Goku","Horus","Jon Ander","Jon","Jtaz","Juan","Mario"};
	private static HashMap<String,Song> userEasterEggMap = new HashMap<>();

	private static final boolean DIBUJADO_INMEDIATO=false;

	//Para el calculo de ganancia tras el trafico de cerebros
	private double BrainsToMoneyCoef=1.0;
	private static final double BRAINS_TO_MONEY_COEF_INCR=0.3;

	//Logica de cambio de fases
	private boolean buildingPhase=true;
	//Logica de construccion y venta de paredes
	private boolean wallButtonPressed=false;

	//Variables que dictaminan los tamanyos de las hordas
	private int maxHordeZ = 0;
	private int minHordeZ = 0;

	//Variables que dictaminan como sera la partida
	private int numRows=DEFAULT_NUM_ROWS;
	private int numColumns=DEFAULT_NUM_COLUMNS;
	private int maxNumRounds=DEFAULT_NUM_ROUNDS;
	private int initialMoney=DEFAULT_INITIAL_MONEY;
	private int initialBrains=DEFAULT_INITIAL_BRAINS;

	//Matrices de objetos de juego
	private LabNodeMatrix mLB=null;;
	private WeaponMatrix mW=null;
	private ZombieMatrix mZ=null;

	//Menu de seleccion de las armas
	private WeaponSelecMenu weaponSelecMenu = new WeaponSelecMenu();

	//Barra de vida que indica lo que queda de horda
	private LifeBar hordeBar=null;

	//Botones
	private ImageButton exitToMenu = new ImageButton(WIDTH-STATS_MARGIN,Y_EX_MENU, BUTTON_SIZE, BUTTON_SIZE, EX_MENU_ICON);
	private ImageButton exitBuildingPhase =  new ImageButton(WIDTH-STATS_MARGIN, Y_BP_EX, BUTTON_SIZE, BUTTON_SIZE, BP_EX_ICON);
	private ImageButton quitWalls = new ImageButton(WIDTH-STATS_MARGIN, Y_QUIT_WALL, BUTTON_SIZE, BUTTON_SIZE, WALL_O_ICON);
	private ImageButton convertBrains = new ImageButton(WIDTH-STATS_MARGIN, Y_C_BRAIN, BUTTON_SIZE, BUTTON_SIZE, C_BRAIN_ICON);

	//Titulos temporales
	private TemporaryTitle bpTitle=null;
	private TemporaryTitle roundTitle=null;

	//Indicadores de la partida
	private StatIndicator brains; 
	private StatIndicator money;
	private StatIndicator roundCounter = new StatIndicator(WIDTH-STATS_MARGIN, Y_ROUND, INDICATOR_HEIGHT, STATS_MARGIN, ROUND_ICON);

	//ArrayLists para el almacenamiento d elos elementos visuales del juego
	private ArrayList<Showable>showables=new ArrayList<>();
	private ArrayList<Showable>showablesBP=new ArrayList<>();
	private ArrayList<Showable>showablesNBP=new ArrayList<>();
	//Guarda las hordas de zombies
	private TreeSet<ZombieHorde>hordes = new TreeSet<>();
	
	private ZombieHorde actualHorde = null;
	//Logica de pulsaciones de raton
	private boolean validClick = false;
	private Point PrevClick=null; 
	private Point CurrClick=null;
	//Guarda la fecha en que se creo el juego
	private Date createdDate;

	//Variables que guardan el estado actual de la partida
	private int zombiesKilled=0;
	private String userPlaying="Anonymous";

	//Boolean que informa de si los titulos han sido inicializados
	private boolean titlesInitialized=false;

	/**
	 * Guarda en el mapa los sonidos Easter Eggs de cada usuario
	 */
	static {
		String bef = "audios/User_";
		String aft = "_sound";
		String ext = ".wav";
		for (String user: EE_USERS) {
			userEasterEggMap.put(user, new Song(bef+user+aft+ext));
		}
	}
	/**
	 * Constructor de un juego nuevo segun el numero de filas y columnas de la matriz de la partida y el numero maximo de rondas
	 * @param numRows
	 * @param numColumns
	 * @param numRounds
	 */
	public LostBrainZZGame(String user,int numRows, int numColumns, int numRounds, int initialMoney, int initialBrains) {

		//Actualiza la fecha de creacion al momento actual
		createdDate	= new Date(System.currentTimeMillis());

		//establezco los valores principales del juego
		setUserPlaying(user);
		setNumRows(numRows);
		setNumColumns(numColumns);
		setMaxNumRounds(numRounds);
		setInitialMoney(initialMoney);
		setInitialBrains(initialBrains);

		//Calculo de cantidad de zombies y creacion de las hordas
		int averageNumZombies = (int) Math.round(numRows*2);
		maxHordeZ = (int)(averageNumZombies *2);
		minHordeZ = (int)(averageNumZombies *0.5);
		createHordes();

		//Creacion de las matrices del juego
		mLB = new LabNodeMatrix(createLab(numRows, numColumns));
		mZ = new ZombieMatrix(numRows, numColumns);
		mW = new WeaponMatrix(numRows,numColumns);

		//Establece la barra de vida de la horda segun la primera horda
		hordeBar = new LifeBar(hordes.first().getSize(),HORDE_BAR_MARGIN,Y_HORDE_BAR,HORDE_BAR_HEIGHT,HORDE_BAR_WIDTH);

		//Establece los indicadores de dinero y cerebros segun los valores indicados
		brains = new StatIndicator(WIDTH-STATS_MARGIN, Y_BRAINS, INDICATOR_HEIGHT, STATS_MARGIN,this.initialBrains, BRAIN_ICON,Color.PINK);
		money = new StatIndicator(WIDTH-STATS_MARGIN, Y_MONEY, INDICATOR_HEIGHT, STATS_MARGIN, this.initialMoney, COIN_ICON,Color.ORANGE);

		//Almacena en las ArrayList los diferentes elementos
		showables.addAll(Arrays.asList(mLB,mZ,mW,weaponSelecMenu,brains,money,roundCounter,exitToMenu));
		showablesBP.addAll(Arrays.asList(exitBuildingPhase,quitWalls,convertBrains));
		showablesNBP.add(hordeBar);
		//Establece las posiciones en pantalla de los elementos
		initPlacementsOnScreen();
	}

	/**
	 * Inicializa los titulos temporales
	 * @param secs
	 * @param fps
	 */
	public void initTempTitles(double secs, int fps) {
		bpTitle = new TemporaryTitle(HORDE_BAR_MARGIN, Y_BP_TITLE, "Building Phase", Color.RED, secs,fps);
		roundTitle = new TemporaryTitle(HORDE_BAR_MARGIN, Y_ROUND_TITLE, "Unknown Round",secs,fps);
		showablesBP.add(bpTitle);
		showablesNBP.add(roundTitle);
		titlesInitialized=true;
	}
	/**
	 * Inicializa los titulos temporales
	 * @param durationInCicles
	 */
	public void initTempTitles(int durationInCicles) {
		bpTitle = new TemporaryTitle(HORDE_BAR_MARGIN, Y_BP_TITLE, "Building Phase", Color.RED, durationInCicles);
		roundTitle = new TemporaryTitle(HORDE_BAR_MARGIN, Y_ROUND_TITLE, "Unknown Round",durationInCicles);
		showablesBP.add(bpTitle);
		showablesNBP.add(roundTitle);
		titlesInitialized=true;
	}

	public void show(VentanaGrafica v) {

		for (Showable s:showables) s.show(v);

		if (buildingPhase) { 
			for (Showable s:showablesBP) if(s!=null) s.show(v);
		}
		//Dibuja los elementos de forma inversa pues la barra de la horda de permaneceer por detras de el titulo temporal
		else for (int i = showablesNBP.size()-1; i>=0; i--) if(showablesNBP.get(i)!=null) showablesNBP.get(i).show(v);
	}
	/**
	 * Hace los calculos de daños y los aplica
	 */
	public void fight() {
		if (!buildingPhase) {
			int prevZombiesAlive=mZ.getNumObjects();
			//CALCULATE AND DO DAMAGE
			mW.hurtZombies(mZ);
			//TAKE OVER OF DEATHS
			brains.increaseValue(mZ.getPointsFromDeads());
			mW.eraseDeads();
			mZ.eraseDeads();
			int afterZombiesAlive=mZ.getNumObjects();
			//CALCULTE KILLED ZOMBIES
			zombiesKilled+=(prevZombiesAlive-afterZombiesAlive);
			//UPDATE HORDE BAR
			hordeBar.setCurrentLife(actualHorde.getSize()+mZ.getNumObjects());
			checkFightEnd();
		}
	}
	/**
	 * Metodo que calcula las posibles siguientes posiciones de cada zombie en el tablero y elige una aleatoriamente para que este se mueva ahi
	 * Si el zombie no tiene casilla contiguas a las que moverse se queda quieto.
	 */
	public void moveZombies() {
		for (int i=0;i<numRows;i++) {
			for(int j=0;j<numColumns;j++) {
				Zombie z = (Zombie) mZ.getObject(i, j);
				if (z!=null && !z.isMoved()) {
					ArrayList<Path> posibles = new ArrayList<>();
					//ARRIBA
					if (i-1>0) {
						Zombie zUp = (Zombie) mZ.getObject(i-1, j);
						Weapon wUp = (Weapon) mW.getObject(i-1, j);
						LabNode lbUp = (LabNode) mLB.getObject(i-1, j);
						if (zUp==null && wUp==null && lbUp.isWalkable()) {
							posibles.add((Path)lbUp);
						}
					}
					//ABAJO
					if (i+1<=numRows-1) {
						Zombie zUp = (Zombie) mZ.getObject(i+1, j);
						Weapon wUp = (Weapon) mW.getObject(i+1, j);
						LabNode lbUp = (LabNode) mLB.getObject(i+1, j);
						if (zUp==null && wUp==null && lbUp.isWalkable()) {
							posibles.add((Path)lbUp);
							posibles.add((Path)lbUp); //Lo anyado dos veces para que haya mas posibilidades de
														//que se elija
						}
					}
					//IZQ
					if (j-1>0) {
						Zombie zUp = (Zombie) mZ.getObject(i, j-1);
						Weapon wUp = (Weapon) mW.getObject(i, j-1);
						LabNode lbUp = (LabNode) mLB.getObject(i, j-1);
						if (zUp==null && wUp==null && lbUp.isWalkable()) {
							posibles.add((Path)lbUp);
						}
					}
					//DER
					if (j+1<numColumns-1) {
						Zombie zUp = (Zombie) mZ.getObject(i, j+1);
						Weapon wUp = (Weapon) mW.getObject(i, j+1);
						LabNode lbUp = (LabNode) mLB.getObject(i, j+1);
						if (zUp==null && wUp==null && lbUp.isWalkable()) {
							posibles.add((Path)lbUp);
						}
					}
					
					if (!posibles.isEmpty()) {
						java.util.Random r = new java.util.Random();
						int id = r.nextInt(posibles.size());
						Path p = posibles.get(id);
						mZ.moveObjects(i, j, p.getRow(), p.getColumn());
					}
					z.setMoved(true);
					
				}
			}
		}
		mZ.resetMoved();
	}
	
	
	
	/**
	 * Devuelve true si ha derrotado a todas las hordas de zombies
	 * @return
	 */
	public boolean isWon() {
		return hordes.isEmpty();
	}
	/**
	 * Muestra en pantalla la pantalla de la partida ganada
	 * @param v
	 */
	public void winScreen(VentanaGrafica v) {
		addRecordAndSave();
		BP_BG_SONG.stopLoopPlaying();
		FIGHT_SONG.stopLoopPlaying();
		v.borra();
		v.dibujaImagen(YOU_WIN_IMG, (WIDTH/2)-(GENERAL_MARGIN*2), (HEIGHT/2)-(GENERAL_MARGIN*5), FINAL_IMG_WIDTH, FINAL_IMG_HEIGHT, FINAL_IMG_ZOOM, 0, FINAL_IMG_OPACITY);
		v.dibujaTexto(GENERAL_MARGIN, HEIGHT-(GENERAL_MARGIN*5), getUserAchievements(), FINAL_FONT, Color.BLACK);
		v.repaint();
		//Cancion de ganar, SI SE CUMPLE EL REQUISITO PARA EL EASTER EGG entonces sonara antes el sonido correspondiente
		Song s = checkEasterEggSong();
		long wait = 0;
		long sWait=Song.AVERAGE_WAIT_TO_STOP_MILLIS;
		if (s!=null) {
			wait=s.getDuration();
			s.playSongInThreadAfterWait(sWait);
		}
		WIN_SONG.playSongInThreadAfterWait(wait+sWait);
	}

	/**
	 * Devuelve true si se ha perdido la partida
	 * @return
	 */
	public boolean isOver() {
		return mZ.ZombieArrived();
	}
	/**
	 * Muestra en pantalla la pantalla de fin de la partida
	 * @param v
	 */
	public void gameOverScreen(VentanaGrafica v) {
		addRecordAndSave();
		BP_BG_SONG.stopLoopPlaying();
		FIGHT_SONG.stopLoopPlaying();
		v.dibujaRect(-GENERAL_MARGIN, -GENERAL_MARGIN, WIDTH+GENERAL_MARGIN*2, HEIGHT +GENERAL_MARGIN*2, 0, Color.BLACK, Color.BLACK);
		v.dibujaImagen(GAME_OVER_IMG, (WIDTH/2)-(GENERAL_MARGIN*2), (HEIGHT/2)-(GENERAL_MARGIN*5), FINAL_IMG_WIDTH, FINAL_IMG_HEIGHT, FINAL_IMG_ZOOM, 0, FINAL_IMG_OPACITY);
		v.dibujaTexto(GENERAL_MARGIN,  HEIGHT-(GENERAL_MARGIN*5), getUserAchievements(), FINAL_FONT, Color.WHITE);
		v.repaint();
		GAME_OV_SONG.playSongInThread();
		GAME_OV_SOUND.playSongInThread();
	}

	/**
	 * Inicializa las posiciones de los objetos del juego en pantalla
	 */
	private void initPlacementsOnScreen() {
		mLB.placeOnScreen(HEIGHT,WIDTH,MENU_MARGIN,0,0,STATS_MARGIN);
		mZ.placeOnScreen(HEIGHT,WIDTH,MENU_MARGIN,0,0,STATS_MARGIN);
		mW.placeOnScreen(HEIGHT,WIDTH,MENU_MARGIN,0,0,STATS_MARGIN);
		weaponSelecMenu.placeOnScreen(HEIGHT,WIDTH, 0, HEIGHT-MENU_MARGIN, 0, WIDTH-HORDE_BAR_MARGIN);
	}
	/**
	 * Ejecuta las mecanicas principales de juego que implican comprar vender y pulsaciones de la mayoria de botones
	 */
	public void doBuyingSellingAndGameStateChanges() {

		if (validClick) {

			LabNode node = (LabNode) mLB.getObjectIsIn(CurrClick);
			Weapon w = (Weapon) mW.getObjectIsIn(CurrClick);
			Zombie z = (Zombie) mZ.getObjectIsIn(CurrClick);
			Weapon selec = weaponSelecMenu.getSelectedWeapon();

			boolean nodeIsBorder = true;
			if (node!=null) {
				nodeIsBorder = node.getRow()==0 || node.getRow()==numRows-1 || node.getColumn()==0 || node.getColumn()==numColumns-1;
			}

			if (buildingPhase) {
				//WALL BUTTON PRESSED
				if (quitWalls.isIn(CurrClick)) {
					selecWallButtonState();
				}
				//CONVERT BRAINS BUTTON PRESSED
				else if (convertBrains.isIn(CurrClick)) {
					convertBrainsToMoney();
				}
				else if (node!=null && !nodeIsBorder) {
					if (w==null) {
						//BUY WALL
						if (!wallButtonPressed && node.isWalkable() && Wall.isPurchasable(money.getValue())) {
							buyWall(node);
						}
						//SELL WALL
						else if (wallButtonPressed && !node.isWalkable()) {
							sellWall(node);
						}
					}
					//SELL WEAPON
					else if(w!=null && wallButtonPressed) {
						sellWeapon(w);
					}
				}
				//EXIT BUILDING PHASE BUTTON PRESSED
				else if (exitBuildingPhase.isIn(CurrClick)) {
					endBuildingPhase();
				}

			}
			//BUY A NEW WEAPON
			if (node!=null && !nodeIsBorder && w==null && z==null && selec!=null && node.isWalkable() && ((buildingPhase && wallButtonPressed) || !buildingPhase)) {
				if (selec.isPurchasable(money.getValue())) {
					buyAndPutNewWeapon(selec, node);
				}

			}
		}
	}
	/**
	 * Devuelve el objeto VentanaGrafica ideal para el juego
	 * @return
	 */
	public static VentanaGrafica getPreferredWindow() {
		VentanaGrafica v = new VentanaGrafica(WIDTH, HEIGHT, TITLE);
		v.setDibujadoInmediato(DIBUJADO_INMEDIATO);
		v.setResizable(false);
		return v;
	}
	/**
	 * Devuelve true si el boton para salir al menu es pulsado
	 * @return
	 */
	public boolean isExitToMenuButtonPressed() {
		boolean result=false;
		if (validClick) {
			result=exitToMenu.isIn(CurrClick);
		}
		return result;
	}
	/**
	 * Crea una arma nueva segun la seleccionada y la coloca en el lugar indicado
	 * @param selec
	 * @param node
	 */
	private void buyAndPutNewWeapon(Weapon selec, LabNode node) {

		Weapon newW=null;
		int row = node.getRow();
		int col = node.getColumn();

		if (selec instanceof ElectricWeapon) {
			newW = new ElectricWeapon(row,col);
		}
		else if (selec instanceof Turret) {
			newW = new Turret(row,col);
		}
		else if (selec instanceof Bomb) {
			newW = new Bomb(row,col);
		}							
		mW.insertObject(newW, row, col);
		money.decreaseValue(newW.getCost());
		PLACE_SOUND.playSongInThread();
	}



	/**
	 * Crea una plantilla para el laberinto completando con caminos todas las posicones
	 *  y todos los bordes con paredes excepto una entrada y una salida elegidas aleatoriamente
	 * @param numRows
	 * @param numColumns
	 * @return
	 */
	private int[][] createLab(int numRows, int numColumns){

		int[][] template = new int[numRows][numColumns];
		Random r = new Random();
		int entry = r.nextInt(template[0].length-2)+1;
		int exit = r.nextInt(template[0].length-2)+1;

		for (int i=0;i<template.length;i++) {
			for(int j=0;j<template[0].length;j++) {
				template[i][j]++;
				boolean isInside = i!=0 && i!=template.length-1 &&  j!=0 && j!=template[0].length-1 ;
				boolean isEntry=(i==0 && j==entry);
				boolean isExit=(i==template.length-1 && j==exit);
				if ( isInside || isEntry || isExit ) {
					template[i][j]--;
				}
			}
		}

		return template;
	}
	/**
	 * Comprueba si quedan zombies en la horda y si es asi entonces intentara introducirlo en el laberinto por la entrada del mismo
	 */
	public void zombieEntersLab() {
		//Encontrar la entrada
		int entranceCol = 0;
		for (int j=0;j<numColumns;j++) {
			LabNode n = (LabNode) mLB.getObject(0, j);
			if (n.isWalkable()) entranceCol=j;
		}
		if (actualHorde!=null && !actualHorde.isEmpty() && mZ.getObject(0, entranceCol)==null) {
			Zombie newZ = actualHorde.pollFirstZombie();
			newZ.setRow(0);
			newZ.setColumn(entranceCol);
			mZ.insertObject(newZ, 0, entranceCol);
		}
		
	}
	
	
	
	
	/**
	 * NO USADO genera aleatoriamente zombies repartidos por el tablero segun las hordas
	 */
	/*
	private void createZombies() {
		Random r = new Random();
		int numZombies=0;
		if (actualHorde.getSize()<=mLB.getNumPathNodes()-mW.getNumObjects()-1) {
			numZombies=actualHorde.getSize();
		}
		else {
			System.err.println("No se han podido introducir tantos Zombies\nNumero de Zombies a introducir:"+actualHorde.getSize()+" Capacidad:"+(mLB.getNumPathNodes()-mW.getNumObjects()));
			numZombies=mLB.getNumPathNodes()-mW.getNumObjects()-1;
		}
		for (int n=numZombies;n>0;n--) {
			int row=r.nextInt(mZ.getNumRows());
			int column = r.nextInt(mZ.getNumColumns());
			if (mLB.getObject(row,column) instanceof Path && mZ.getObject(row, column)==null && mW.getObject(row, column)==null && row<numRows) {
				Zombie newZ = actualHorde.pollFirstZombie();
				newZ.setRow(row);
				newZ.setColumn(column);
				mZ.insertObject(newZ, row, column);
			}
			else n++;
		}
	}*/

	/**
	 * Segun el si el boton ha sido pulsado cambia de estado y por lo tanto de imagen
	 */
	private void selecWallButtonState() {
		if (wallButtonPressed) {
			quitWalls.setImgFileName(WALL_O_ICON);
			wallButtonPressed=false;
		}
		else {
			quitWalls.setImgFileName(WALL_X_ICON);
			wallButtonPressed=true;
		}
	}
	/**
	 * "Vende" los cerebros y los convierte en dinero segun el avance de juego
	 */
	private void convertBrainsToMoney() {
		getRndBrainsSong().playSongInThread();
		brains.changeValueByFactor(BrainsToMoneyCoef+(roundCounter.getValue()*BRAINS_TO_MONEY_COEF_INCR));
		money.increaseValue(brains.getValue());
		brains.resetValue();
	}
	/**
	 * Ejecuta los cambios pertinentes para el cambio de fase de construccion a fase de "juego"
	 */
	private void endBuildingPhase() {
		buildingPhase=false;
		BP_BG_SONG.stopLoopPlaying();
		try {
			bpTitle.resetCicles();
		} catch (NullPointerException e) {
			System.err.println("Error al acceder titulo temporal seguramente no se ha inicializado a traves de initTempTitles()");
			e.printStackTrace();
		}
		actualHorde=hordes.first();
		showablesNBP.remove(hordeBar);
		hordeBar = new LifeBar(actualHorde.getSize(),HORDE_BAR_MARGIN,Y_HORDE_BAR,HORDE_BAR_HEIGHT,HORDE_BAR_WIDTH);
		showablesNBP.add(hordeBar);
		roundCounter.increaseValue();
		try {
			roundTitle.setText("Round "+roundCounter.getValue());
		} catch (NullPointerException e) {
			System.err.println("Error al acceder titulo temporal seguramente no se ha inicializado a traves de initTempTitles()");
			e.printStackTrace();
		}

		if (!isOver() && !isWon()) {
			FIGHT_SONG.playLoopSongInThread();
			getRndRoundSong().playSongInThread();
		}
	}
	/**
	 * Vende un arma indicada
	 * @param w
	 */
	private void sellWeapon(Weapon w) {
		int row = w.getRow();
		int col = w.getColumn();						
		mW.eraseObject(row, col);
		money.increaseValue((int)Math.round((w.getCurrentLife()*w.getCost())/w.getMaxLife()));
		SELL_SOUND.playSongInThread();
	}
	/**
	 * Compra y coloca una pared en el camino indicado
	 * @param node
	 */
	private void buyWall(LabNode node) {
		if (node instanceof Path) {
			Wall newWall = new Wall(node.getRow(),node.getColumn());
			mLB.insertObject(newWall, node.getRow(), node.getColumn());
			money.decreaseValue(Wall.getCost());
			PLACE_SOUND.playSongInThread();
			EE_PLACE_WALL.playSongInThread();
		}
	}
	/**
	 * Vende una pared y la sustituye por un camino en esa posicion
	 * @param node
	 */
	private void sellWall(LabNode node) {
		if (node instanceof Wall) {
			Path newPath = new Path(node.getRow(),node.getColumn());
			mLB.insertObject(newPath, node.getRow(), node.getColumn());
			money.increaseValue((int)Math.round(Wall.getCost()/2));
			SELL_SOUND.playSongInThread();
		}
	}
	/**
	 * Comprueba si la fase lucha ha terminado y empieza una fase de construccion si es asi
	 */
	private void checkFightEnd() {//TODO cambiar cuando los zombies sepan moverse
		if (actualHorde.isEmpty() && mZ.isEmpty()) {
			startBuildingPhase();
		}
	}
	/**
	 * Hace los cambios pertinentes para que empiece la fase de construccion
	 */
	private void startBuildingPhase() {
		hordes.pollFirst();
		buildingPhase = true;
		FIGHT_SONG.stopLoopPlaying();
		try {
			roundTitle.resetCicles();
		} catch (NullPointerException e) {
			System.err.println("Error al acceder al titulo temporal seguramente no se ha inicializado a traves de initTempTitles()");
			e.printStackTrace();
		}
		if (!isOver() && !isWon()) {
			BP_BG_SONG.playLoopSongInThread();
			getRndBPSong().playSongInThread();
		}

	}
	/**
	 * Crea las hordas para la partida
	 */
	private void createHordes() {
		java.util.Random r = new java.util.Random();
		for(int i = 0; i<maxNumRounds;i++) {
			ZombieHorde zh = new ZombieHorde();
			zh.fillHorde(r.nextInt(maxHordeZ-minHordeZ+1)+minHordeZ);
			hordes.add(zh);
		}
	}

	/**
	 * Comprueba el click recibido, y mira a ver si es valido
	 * @param v
	 */
	public void checkClicks(VentanaGrafica v) {
		weaponSelecMenu.checkSelectedWeapon(v);

		CurrClick = v.getRatonPulsado();

		validClick = CurrClick!=null && CurrClick!=PrevClick;

		PrevClick=CurrClick;

	}

	/**
	 * Anyade lo conseguido en la partida al record holder de la clase main y lo guarda en el fichero
	 */
	public void addRecordAndSave() {
		if (!Main.getMenu().getConfigWindow().isDeveloperMode()) {
			Main.getRecordHolder().addRecord(userPlaying, isWon(), roundCounter.getValue(), zombiesKilled);
			Main.saveRecordHolder();
		}

	}
	/**
	 * Comprueba si al usuario le corresponde un easter egg y si es asi la devuelve
	 * sino devuelve null
	 */
	private Song checkEasterEggSong() {
		Song s = null;
		if (userEasterEggMap.containsKey(userPlaying)) {
			s = userEasterEggMap.get(userPlaying);
		}
		return s;
	}
	/**
	 * Devuelve un string con los logros conseguidos por el usuario durante la partida 
	 * @return
	 */
	private String getUserAchievements() {
		return userPlaying+":( Rounds completed: "+roundCounter.getValue()+", Killed Zombies: "+zombiesKilled+")";
	}

	/**
	 * Devuelve una cancion aleatoria del grupo de canciones para el inicio de fase de construccion
	 * @return
	 */
	public static Song getRndBPSong() {
		java.util.Random r = new java.util.Random();
		int id=r.nextInt(BP_SONGS.length);
		return BP_SONGS[id];
	}
	/**
	 * Devuelve una cancion aleatoria del grupo de canciones para el inicio de la ronda
	 * @return
	 */
	public static Song getRndRoundSong() {
		java.util.Random r = new java.util.Random();
		int id=r.nextInt(ROUND_SONGS.length);
		return ROUND_SONGS[id];
	}
	/**
	 * Devuelve una cancion aleatoria del grupo de canciones para ela conversion de cerebros
	 * @return
	 */
	public static Song getRndBrainsSong() {
		java.util.Random r = new java.util.Random();
		int id=r.nextInt(BRAINS_SONGS.length);
		return BRAINS_SONGS[id];
	}

	@Override
	public String toString() {
		return "Game("+numRows+"x"+numColumns+"): [Round:"+roundCounter.getValue()+" of "+maxNumRounds+", Created:"+createdDate+"]";
	}
	/**
	 * Reanuda las canciones de fondo
	 * @param resuming
	 */
	public void resumeSongs(boolean resuming) {
		if (buildingPhase && ((!BP_BG_SONG.isPlaying() && !BP_BG_SONG.isInLoop())||resuming)) {
			BP_BG_SONG.playLoopSongInThread();
		}
		else if (!buildingPhase && ((!FIGHT_SONG.isPlaying() && !FIGHT_SONG.isInLoop())||resuming)) {
			FIGHT_SONG.playLoopSongInThread();
		}

	}

	/**
	 * Establece el numero de filas del tablero del juego
	 * @param numRows
	 */
	private void setNumRows(int numRows) {
		this.numRows = numRows;
	}
	/**
	 * Establece el numero de columnas del tablero del juego
	 * @param numColumns
	 */
	private void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
	}
	/**
	 * Establece el dinero con el que se inicia el juego
	 * @param initialMoney
	 */
	private void setInitialMoney(int initialMoney) {
		this.initialMoney = initialMoney;
	}
	/**
	 * Establece el numero de cerebros con el que se inicia el juego
	 * @param initialBrains
	 */
	public void setInitialBrains(int initialBrains) {
		this.initialBrains = initialBrains;
	}
	/**
	 * Establece el usuario que esta jugando
	 * @param user
	 */
	private void setUserPlaying(String user) {
		this.userPlaying=user;
	}
	/**
	 * Devuelve true si los titulos temporales se han inicializado
	 * @return
	 */
	public boolean areTitlesInitialized() {
		return titlesInitialized;
	}
	/**
	 * Devuelve la altura para la que esta preparado el juego
	 * @return
	 */
	public static int getHeight() {
		return HEIGHT;
	}
	/**
	 * Devuelve la anchira para la que esta preparado el juego
	 * @return
	 */
	public static int getWidth() {
		return WIDTH;
	}
	/**
	 * Devuelve el titulo del juego
	 * @return
	 */
	public static String getTitle() {
		return TITLE;
	}
	/**
	 * Devuelve la fecha en que fue creado el juego
	 * @return
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * Devuelve un String que representa la fecha en que fue creado el juego
	 * @return
	 */
	public String getCreatedDateToString() {
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/YYYY");
		return dt.format(createdDate);
	}
	/**
	 * Devuelve el numero de rondas que tendra la partida 
	 * @return
	 */
	public int getMaxNumRounds() {
		return maxNumRounds;
	}
	/**
	 * Establece el numero de rondas que tendra la partida 
	 * @param maxNumRounds
	 */
	public void setMaxNumRounds(int maxNumRounds) {
		this.maxNumRounds = maxNumRounds;
	}
	/**
	 * Devuelve el jugador asignado al juego
	 * @return
	 */
	public String getUserPlaying() {
		return userPlaying;
	}


}
