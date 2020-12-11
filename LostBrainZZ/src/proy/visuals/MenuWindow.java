package proy.visuals;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

import proy.Main;
/**
 * 
 * @author Danel
 *
 */
public class MenuWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 500;
	private static final int HEIGHT = 700;
	private static final int MIN_WIDTH=400;
	private static final int MIN_HEIGHT=600;
	private static final String TITLE = "Lost BrainZZ...";
	private static final int BT_BORDER_THICKNESS = 3;
	private static final String SAVE_FILE_NAME=Main.SAVE_FILE_NAME;
	private static File saveFile = new File(SAVE_FILE_NAME);
	//Paneles
	private JPanel pnTop = new JPanel();
	private JPanel pnRight = new JPanel();
	private JPanel pnLeft = new JPanel();
	private JPanel pnBot = new JPanel();
	private JPanel pnMid = new JPanel();
	//Botones
	private JButton btResume = new JButton("Resume");
	private JButton btNewGame = new JButton("New Game");
	private JButton btSettings = new JButton("Game Settings");
	private JButton btRecords = new JButton("Records");
	private JButton btLoad = new JButton("Load Saved Game");
	private JButton btSave = new JButton("Save Game");
	private JButton btDelSave = new JButton("Delete Saved Game");
	private JButton btExit = new JButton("Exit");

	private JButton btTutorial = new JButton("Tutorial and Info.");
	private JButton btCredits = new JButton("Credits");

	private JLabel lbBotMsg = new JLabel("");

	private Font fButton = new Font("Button", 1, 20);
	private Font fTitle = new Font("Title",1,25);
	private Font fMsg = new Font("Msg",0,15);
	//Ventanas "secundarias" usadas
	private YesOrNoWindow yOrNWin = new YesOrNoWindow("Are you sure you want to leave?");
	private ConfigWindow configWin = new ConfigWindow();
	private RecordWindow recordWin = new RecordWindow();
	private UserWindow userWin = new UserWindow();
	private TutorialWindow tutoWin = new TutorialWindow();
	private CreditsWindow creditsWin = new CreditsWindow();

	/**
	 * Constructor
	 */
	public MenuWindow() {

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		setTitle(TITLE);

		//General Panels
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(pnTop, BorderLayout.NORTH);
		getContentPane().add(pnRight, BorderLayout.EAST);
		getContentPane().add(pnLeft, BorderLayout.WEST);
		getContentPane().add(pnBot, BorderLayout.SOUTH);
		getContentPane().add(pnMid, BorderLayout.CENTER);

		//Top Panel

		JLabel title = new JLabel(TITLE,SwingConstants.CENTER);
		title.setFont(fTitle);
		title.setForeground(Color.RED);
		pnTop.add(new JPanel().add(title));

		//Mid Panel	
		pnMid.setLayout(new BoxLayout(pnMid, BoxLayout.Y_AXIS));

		JButton[] buttons = {btResume,btNewGame,btTutorial,btSettings,btRecords,btLoad,btSave,btDelSave,btCredits,btExit};
		for (JButton b:buttons) {
			addRowOfButton(pnMid,b);
			b.setBorder(BorderFactory.createLineBorder(Color.BLACK, BT_BORDER_THICKNESS, true));
		}
		setButtonSize();

		//Bottom Panel
		pnBot.add(lbBotMsg);
		lbBotMsg.setFont(fMsg);

		//Deactivate resume and save
		btResume.setEnabled(false);
		btSave.setEnabled(false);

		//Listeners
		btResume.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMessage("Resuming Game");
				setVisible(false);
			}
		});
		btNewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMessage("Starting New Game");
				setVisible(false);
				userWin.setVisible(true);

			}
		});
		btSettings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMessage("Configuring Game");
				setVisible(false);
				configWin.setVisible(true);
			}
		});
		btRecords.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.loadRecordHolder();
				setMessage("Checking Records");
				setVisible(false);
				recordWin.updateComboBox();
				recordWin.setVisible(true);
			}
		});
		btLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMessage("Loading Game");
				btResume.setEnabled(true);
				btSave.setEnabled(true);
			}
		});

		btSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMessage("Saving Game");
			}
		});

		btDelSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile.delete();
				setMessage("Saved File Deleted");
			}
		});
		btExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMessage("Exiting Game");
				setVisible(false);
				yOrNWin.setVisible(true);
			}
		});
		btTutorial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMessage("Viewing the tutorial");
				setVisible(false);
				tutoWin.setVisible(true);
			}
		});
		btCredits.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMessage("Viewing the credits");
				setVisible(false);
				creditsWin.setVisible(true);
			}
		});

		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				setButtonSize();
			}
		});
		this.addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent e) {
				boolean isMaximized = isMaximized(e.getNewState());
				boolean wasMaximized = isMaximized(e.getOldState());

				if (isMaximized && !wasMaximized) setButtonSize();
				else if (wasMaximized && !isMaximized) setButtonSize();
			}
			private boolean isMaximized(int state) {
				return (state & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH;
			}
		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				yOrNWin.setVisible(true);
			}

		});
		//Implementar ActionListener de la venta de si seguro de cerrar para que cierre el menu tambien
		yOrNWin.getBtYes().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		yOrNWin.getBtNo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(true);
			}
		});
		//Listeners "extra" para la ventana de ajustes
		configWin.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(true);	
			}
		});
		configWin.getBtDone().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (configWin.allChecks()) {
					setVisible(true);
				}
			}
		});
		//Listeners "extra" para la ventana de records
		recordWin.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(true);
			}
		});
		//Listeners "extra" para la ventana de seleccion de usuario
		userWin.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				btResume.setEnabled(true);
				btSave.setEnabled(true);
			}
		});
		//Listeners "extra" para la ventana de tutorial
		tutoWin.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(true);
			}
		});
		//Listeners "extra" para la ventana de creditos
		creditsWin.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(true);
			}
		});
		/**
		 * Hilo que comprueba si los botones deberían estar activos
		 */
		Thread activationChecker = new Thread() {
			@Override
			public void run() {
				while(/*!closed*/true) {
					btLoad.setEnabled(saveFile.exists());
					btDelSave.setEnabled(saveFile.exists());

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		activationChecker.setDaemon(true);
		activationChecker.start();

		setVisible(true);

	}
	/**
	 * Crea y anyade una fila con su respextivo boton
	 * @param pn
	 * @param bt
	 */
	private void addRowOfButton(JPanel pn, JButton bt) {
		bt.setAlignmentX(JButton.CENTER_ALIGNMENT);
		bt.setFont(fButton);
		JPanel row = new JPanel();
		row.add(bt);
		pn.add(row);
	}
	/**
	 * Establece los tamanyo de los botones en relación a la ventana
	 */
	private void setButtonSize() {
		JButton[] buttons = {btResume,btNewGame,btSettings,btRecords,btLoad,btSave,btDelSave,btExit,btTutorial,btCredits};
		for (JButton b:buttons) {
			b.setPreferredSize(new Dimension(getWidth()/2,getHeight()/17));
		}
	}
	/**
	 * Establece el mensaje
	 * @param msg
	 */
	public void setMessage(String msg) {
		lbBotMsg.setText(msg);
	}
	/**
	 * Devuelve el JButton de reanudar partida
	 * @return
	 */
	public JButton getBtResume() {
		return btResume;
	}
	/**
	 * Devuelve el JButton de nueva partida
	 * @return
	 */
	public JButton getBtNewGame() {
		return btNewGame;
	}
	/**
	 * Devuelve el JButton de cargar la partida
	 * @return
	 */
	public JButton getBtLoad() {
		return btLoad;
	}
	/**
	 * Devuelve el JButton de guardar la partida
	 * @return
	 */
	public JButton getBtSave() {
		return btSave;
	}
	/**
	 * Devuelve el JButton de borrar la partida guardada
	 * @return
	 */
	public JButton getBtDelSave() {
		return btDelSave;
	}
	/**
	 * Devuelve el JButton de salir de la ventana
	 * @return
	 */
	public JButton getBtExit() {
		return btExit;
	}
	/**
	 * Devuelve la ventana de ajustes
	 * @return configWin
	 */
	public ConfigWindow getConfigWindow() {
		return configWin;
	}
	/**
	 * Devuelve la ventana de seleccion de usuario
	 * @return userWin
	 */
	public UserWindow getUserWindow() {
		return userWin;
	}
}
