package proy.unused.completeGameObj;

import java.awt.*;
import java.util.*;
import proy.interfaces.Showable;
import proy.unused.visuals.utilObjects.*;
import proy.visuals.utilObjects.*;
import proy.visuals.VentanaGrafica;
/**
 * 
 * @author Danel
 * CLASE OBSOLETA, NO USADA
 *
 */
public class GameMenu implements Showable {
	private static final String TITLE = "LOST BRAINZZ MENU";
	private static final int HEIGHT=700;
	private static final int WIDTH=HEIGHT;
	private static final int GENERAL_MARGIN = 10;
	private static final int MARGIN_BETWEEN_BUTTONS=50;
	private static final int UP_MARGIN=70;
	private static final int BUTTON_WIDTH=200;
	private static final int BUTTON_HEIGHT=50;
	private static final int IZQ_MARGIN=(WIDTH-BUTTON_WIDTH)/2;
	private static final Color BACKGROUND_COLOR=Color.PINK;
	private static final Color R_GAME_BUTTON_COLOR=Color.YELLOW;
	private static final Color N_GAME_BUTTON_COLOR=Color.GREEN;
	private static final Color SAVE_BUTTON_COLOR=Color.MAGENTA;
	private static final Color LOAD_BUTTON_COLOR=Color.CYAN;
	private static final Color DEL_SAVE_BUTTON_COLOR=Color.ORANGE;
	private static final Color EXIT_BUTTON_COLOR=Color.RED;
	private static final int Y_R_GAME_BUTTON=UP_MARGIN;
	private static final int Y_N_GAME_BUTTON=Y_R_GAME_BUTTON+BUTTON_HEIGHT+MARGIN_BETWEEN_BUTTONS;
	private static final int Y_LOAD_BUTTON=Y_N_GAME_BUTTON+BUTTON_HEIGHT+MARGIN_BETWEEN_BUTTONS;
	private static final int Y_SAVE_BUTTON=Y_LOAD_BUTTON+BUTTON_HEIGHT+MARGIN_BETWEEN_BUTTONS;
	private static final int Y_DEL_SAVE_BUTTON=Y_SAVE_BUTTON+BUTTON_HEIGHT+MARGIN_BETWEEN_BUTTONS;
	private static final int Y_EXIT_BUTTON=Y_DEL_SAVE_BUTTON+BUTTON_HEIGHT+MARGIN_BETWEEN_BUTTONS;

	private TemporaryTitle message;

	private boolean titlesInitialized=false;
	private static boolean dibujadoInmediato=false;

	private DeactivatableTextButton resumeButton= new DeactivatableTextButton(IZQ_MARGIN, Y_R_GAME_BUTTON, BUTTON_HEIGHT, BUTTON_WIDTH, "RESUME GAME",R_GAME_BUTTON_COLOR);
	private TextButton newGameButton= new TextButton(IZQ_MARGIN, Y_N_GAME_BUTTON, BUTTON_HEIGHT, BUTTON_WIDTH, "NEW GAME",N_GAME_BUTTON_COLOR);
	private DeactivatableTextButton loadGameButton = new DeactivatableTextButton(IZQ_MARGIN, Y_LOAD_BUTTON, BUTTON_HEIGHT, BUTTON_WIDTH, "LOAD GAME",LOAD_BUTTON_COLOR);
	private DeactivatableTextButton saveGameButton = new DeactivatableTextButton(IZQ_MARGIN, Y_SAVE_BUTTON, BUTTON_HEIGHT, BUTTON_WIDTH, "SAVE GAME",SAVE_BUTTON_COLOR);
	private DeactivatableTextButton deleteSaveButton = new DeactivatableTextButton(IZQ_MARGIN, Y_DEL_SAVE_BUTTON, BUTTON_HEIGHT, BUTTON_WIDTH, "DELETE SAVE",DEL_SAVE_BUTTON_COLOR);
	private TextButton exitButton= new TextButton(IZQ_MARGIN, Y_EXIT_BUTTON, BUTTON_HEIGHT, BUTTON_WIDTH, "EXIT",EXIT_BUTTON_COLOR);

	private Point click=null;

	private ArrayList<TextButton> buttons = new ArrayList<>();

	private HashMap<Integer,String> actionMessages= new HashMap<>();
	/**
	 * Consructor del menu de juego
	 */
	public GameMenu() {
		resumeButton.deactivate();
		loadGameButton.deactivate();
		saveGameButton.deactivate();
		deleteSaveButton.deactivate();
		buttons.addAll(Arrays.asList(resumeButton,newGameButton,loadGameButton,saveGameButton,deleteSaveButton,exitButton));

		actionMessages.put(0, "Game Resumed"); actionMessages.put(1, "New Game Started"); actionMessages.put(2, "Loaded Saved Game");
		actionMessages.put(3, "Game Saved"); actionMessages.put(4, "Save deleted"); actionMessages.put(5, "Bye!");
	}
	/**
	 * Inicializa los titulos temporales
	 * @param secs
	 * @param fps
	 */
	public void initTempTitles(double secs, int fps) {
		message = new TemporaryTitle(0, HEIGHT, "", Color.BLACK, secs,fps);
		titlesInitialized=true;
	}
	/**
	 * Inicializa los titulos temporales
	 * @param durationInCicles
	 */
	public void initTempTitles(int durationInCicles) {
		message = new TemporaryTitle(0, HEIGHT, "", Color.BLACK, durationInCicles);
		titlesInitialized=true;
	}
	public void show(VentanaGrafica v) {
		v.dibujaRect(-GENERAL_MARGIN, -GENERAL_MARGIN, WIDTH+GENERAL_MARGIN*2, HEIGHT+GENERAL_MARGIN*2, 0, BACKGROUND_COLOR, BACKGROUND_COLOR);
		for (Showable s:buttons) s.show(v);
		try {
			message.show(v);
		} catch (NullPointerException e) {
			System.err.println("Error al acceder al titulo temporal seguramente no se ha inicializado a traves de initTempTitles()");
			e.printStackTrace();
		}
	}
	/**
	 * Devuelve el ID del boton pulsado del menu
	 * @param v
	 * @return
	 */
	public int getClickedButtonID(VentanaGrafica v) {
		int result=-1;
		click=v.getRatonPulsado();
		if (click!=null) {
			for (int i=0;i<buttons.size();i++) if (buttons.get(i).isIn(click)) result=i;
		}
		if (actionMessages.containsKey(result)) {
			message.setText(actionMessages.get(result));
			message.resetCicles();
		}
		return result;
	}
	/**
	 * Devuelve un String con informacion del boton segun la ID
	 * @param index
	 * @return
	 */
	public String getButtonInfoByID(int index) {
		String str = "Button NOT FOUND";
		if (index>=0 && index<=buttons.size()) {
			str = buttons.get(index).toString();
		}
		return str;
	}

	/**
	 * Devuelve el ObjetoVentanaGrafica idoneo para el menu
	 * @return
	 */
	public static VentanaGrafica getScreen() {
		VentanaGrafica v = new VentanaGrafica(WIDTH, HEIGHT, TITLE);
		v.setDibujadoInmediato(dibujadoInmediato);
		return v;
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
	 * Devuelve el boton desactivable de continuar el juego
	 * @return
	 */
	public DeactivatableTextButton getResumeButton() {
		return resumeButton;
	}
	/**
	 * Devuelve el boton desactivable de cargar el juego
	 * @return
	 */
	public DeactivatableTextButton getLoadGameButton() {
		return loadGameButton;
	}
	/**
	 * Devuelve el boton desactivable de guardar el juego
	 * @return
	 */
	public DeactivatableTextButton getSaveGameButton() {
		return saveGameButton;
	}
	/**
	 * Devuelve el boton desactivable de borrar el juego guardado
	 * @return
	 */
	public DeactivatableTextButton getDeleteSaveButton() {
		return deleteSaveButton;
	}
	/**
	 * Devuelve true si los titulos temporales se han inicializado
	 * @return
	 */
	public boolean areTitlesInitialized() {
		return titlesInitialized;
	}
}
