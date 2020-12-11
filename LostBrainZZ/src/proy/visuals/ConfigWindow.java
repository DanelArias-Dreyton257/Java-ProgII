package proy.visuals;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import proy.completeGameObj.LostBrainZZGame;

public class ConfigWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 500;
	private static final int HEIGHT= 300;
	private static final String TITLE = "Game Settings";
	//Los valores por defecto para el juego
	private static final int DEFAULT_NUM_ROWS=LostBrainZZGame.DEFAULT_NUM_ROWS;
	private static final int DEFAULT_NUM_COLUMNS=LostBrainZZGame.DEFAULT_NUM_COLUMNS;
	private static final int DEFAULT_NUM_ROUNDS=LostBrainZZGame.DEFAULT_NUM_ROUNDS;
	private static final int DEFAULT_INITIAL_MONEY=LostBrainZZGame.DEFAULT_INITIAL_MONEY;
	private static final int DEFAULT_INITIAL_BRAINS=LostBrainZZGame.DEFAULT_INITIAL_BRAINS;
	private static final int MIN_NUM_ROWS = LostBrainZZGame.MIN_NUM_ROWS;
	private static final int MIN_NUM_COLS = LostBrainZZGame.MIN_NUM_COLUMNS;
	private static final int MAX_NUM_ROWS = LostBrainZZGame.MAX_NUM_ROWS;
	private static final int MAX_NUM_COLS = LostBrainZZGame.MAX_NUM_COLUMNS;
	//Array con los Strings para las Jlabel
	private static String[] labelStr = {"","Number of rows ("+MAX_NUM_ROWS+" > x > "+MIN_NUM_ROWS+"):","Number of columns("+MAX_NUM_COLS+" > x > "+MIN_NUM_COLS+"):", "Number of rounds(x > 0):","","Initial money(x >= 0):", "Initial Brains(x >= 0):"};
	//Variables que guardan lo seleccionado en el menu de configuracion
	private int numRows = DEFAULT_NUM_ROWS;
	private int numColumns = DEFAULT_NUM_COLUMNS;
	private int numRounds = DEFAULT_NUM_ROUNDS;
	private int initialMoney = DEFAULT_INITIAL_MONEY;
	private int initialBrains = DEFAULT_INITIAL_BRAINS;
	//JPanels
	private JPanel pnCentral = new JPanel();
	private JPanel pnUp = new JPanel();
	private JPanel pnDown = new JPanel();
	//JCheckBoxs
	private JCheckBox cbSquareShape = new JCheckBox("Square shape");
	private JCheckBox cbDeveloperMode = new JCheckBox("Developer Mode");
	//JButtons
	private JButton btDone = new JButton("DONE");
	private JButton btDefaultValues = new JButton("Default values");
	//JTextFields
	private JTextField tfNumRows = new JTextField(numRows+"",10);
	private JTextField tfNumColumns = new JTextField(numColumns+"",10);
	private JTextField tfNumRounds= new JTextField(numRounds+"",10);
	private JTextField tfInitialMoney = new JTextField(initialMoney+"",10);
	private JTextField tfInitialBrains = new JTextField(initialBrains+"",10);
	//Array con los componentes que se mostraran en pantalla
	private Component[] components = {cbSquareShape,tfNumRows,tfNumColumns,tfNumRounds,cbDeveloperMode,tfInitialMoney,tfInitialBrains};
	//JLabel que se mostrara en la parte baja de la pantalla con mensajes
	private JLabel message = new JLabel();
	//boolean que indica si el modo dessarrollador fue activado
	private boolean developerMode = false;

	/**
	 * Contructor de la clase, crea una ventana de configuracion para el juego de LostBrainZZ
	 * Por defecto: setVisible(false)
	 */
	public ConfigWindow() {
		//Colocación de paneles y componentes en paneles
		getContentPane().setLayout(new BorderLayout());
		//Panel central
		pnCentral.setLayout(new BoxLayout(pnCentral, BoxLayout.Y_AXIS));
		getContentPane().add(pnCentral, BorderLayout.CENTER);
		//Panel inferior
		pnDown.setLayout(new BoxLayout(pnDown, BoxLayout.Y_AXIS));
		JPanel pn = new JPanel();
		JPanel pn1 = new JPanel();
		pn.add(btDone);
		pn.add(btDefaultValues);
		pn1.add(message);
		pnDown.add(pn);
		pnDown.add(pn1);
		getContentPane().add(pnDown, BorderLayout.SOUTH);
		//Panel superior
		pnUp.add(new JLabel(TITLE));
		getContentPane().add(pnUp, BorderLayout.NORTH);

		//Tamanyo y colocacion de la ventana
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		//Poner la ventana en el centro de la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setResizable(false);
		setTitle(TITLE);

		//Desactivo los JtextFields que solo se permiten en el modo de desarrollador
		tfInitialMoney.setEnabled(false);
		tfInitialBrains.setEnabled(false);

		//pongo activado el modo cuadrado
		cbSquareShape.setSelected(true);
		checkBoxSquareUpdate();

		//Anyade los JLabels y Componentes al panel Central
		pnCentral.setLayout(new GridLayout(labelStr.length, 2));
		for (int i=0;i<labelStr.length;i++) {
			JPanel lb = new JPanel();
			lb.add(new JLabel(labelStr[i]));
			JPanel txF = new JPanel();
			txF.add(components[i]);
			pnCentral.add(lb);
			pnCentral.add(txF);
		}
		//Listeners
		cbSquareShape.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkBoxSquareUpdate();

			}
		});
		cbDeveloperMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkBoxDeveloperModeUpdate();
			}
		});
		tfNumRows.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				rowsCheck();
			}

		});
		tfNumColumns.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				colsCheck();
			}

		});
		tfNumRounds.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				roundsCheck();
			}

		});
		tfInitialMoney.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				moneyCheck();
			}
		});
		tfInitialBrains.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				brainsCheck();
			}
		});
		btDone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(allChecks()) {
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(ConfigWindow.this, "Please enter a valid number in the fields", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btDefaultValues.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetValuesToDefault();
			}
		});
	}
	/**
	 * Actualiza el estado de la ventana segun si cambia el checkBox
	 */
	private void checkBoxSquareUpdate() {
		if (cbSquareShape.isSelected()) {
			tfNumColumns.setEnabled(false);
			rowsCheck();
		}
		else {
			tfNumColumns.setEnabled(true);
		}
	}
	/**
	 * Actualiza el estado de la ventana segun si cambia el checkBox
	 */
	private void checkBoxDeveloperModeUpdate() {
		if(cbDeveloperMode.isSelected()) {
			JOptionPane.showMessageDialog(ConfigWindow.this, "If Developer Mode is activated\nthe records will not be saved", "Warning", JOptionPane.WARNING_MESSAGE);
			tfInitialMoney.setEnabled(true);
			tfInitialBrains.setEnabled(true);
			developerMode=true;
		}
		else {
			tfInitialMoney.setEnabled(false);
			tfInitialBrains.setEnabled(false);
			developerMode=false;
		}
	}
	/**
	 * Reestablece todos los valores a los valores por defecto
	 */
	public void resetValuesToDefault() {
		tfNumRows.setText(DEFAULT_NUM_ROWS+"");
		tfNumColumns.setText(DEFAULT_NUM_COLUMNS+"");
		tfNumRounds.setText(DEFAULT_NUM_ROUNDS+"");
		tfInitialMoney.setText(DEFAULT_INITIAL_MONEY+"");
		tfInitialBrains.setText(DEFAULT_INITIAL_BRAINS+"");
		cbDeveloperMode.setSelected(false);
		checkBoxDeveloperModeUpdate();
		cbSquareShape.setSelected(true);
		checkBoxSquareUpdate();
	}
	/**
	 * Comprueba si el numero introducido en el campo para las filas es correcto y si es así lo establece y devuelve true
	 * En caso de que el numero no sea correcto muestra un mensaje y devuelve false
	 * @return si las filas son correctas
	 */
	private boolean rowsCheck() {
		int rows = isValidNumOfRows(tfNumRows.getText());
		if (rows!=-1) {
			setNumRows(rows);
			return true;
		}
		else {
			tfNumRows.requestFocus();
			tfNumRows.selectAll();
			setMessage("Please enter a valid number of rows");
			return false;
		}
	}
	/**
	 * Comprueba si el numero introducido en el campo para las columnas es correcto y si es así lo establece y devuelve true
	 * En caso de que el numero no sea correcto muestra un mensaje y devuelve false
	 * @return si las columnas son correctas
	 */
	private boolean colsCheck() {
		int cols = isValidNumOfColumns(tfNumColumns.getText());
		if (cols!=-1) {
			setNumColumns(cols);
			return true;
		}
		else {
			tfNumColumns.requestFocus();
			tfNumColumns.selectAll();
			setMessage("Please enter a valid number of columns");
			return false;
		}
	}
	/**
	 * Comprueba si el numero introducido en el campo para las rondas de juego es correcto y si es así lo establece y devuelve true
	 * En caso de que el numero no sea correcto muestra un mensaje y devuelve false
	 * @return si las rondas son correctas
	 */
	private boolean roundsCheck() {
		int rounds = isPositiveNumber(tfNumRounds.getText());
		if (rounds!=-1) {
			setNumRounds(rounds);
			return true;
		}
		else {
			tfNumRounds.requestFocus();
			tfNumRounds.selectAll();
			setMessage("Please enter a valid number of rounds");
			return false;
		}
	}
	/**
	 * Comprueba si el numero introducido en el campo para el dinero inicial es correcto y si es así lo establece y devuelve true
	 * En caso de que el numero no sea correcto muestra un mensaje y devuelve false
	 * @return si el dinero es correcto
	 */
	private boolean moneyCheck() {
		int money = isPositiveOrZeroNumber(tfInitialMoney.getText());
		if (money!=-1) {
			setInitialMoney(money);
			return true;
		}
		else {
			tfInitialMoney.requestFocus();
			tfInitialMoney.selectAll();
			setMessage("Please enter a valid initial money");
			return false;
		}
	}
	/**
	 * Comprueba si el numero introducido en el campo para los cerebros iniciales es correcto y si es así lo establece y devuelve true
	 * En caso de que el numero no sea correcto muestra un mensaje y devuelve false
	 * @return si la cantidad de cerebros es correcta
	 */
	private boolean brainsCheck() {
		int brains = isPositiveOrZeroNumber(tfInitialBrains.getText());
		if (brains!=-1) {
			setInitialBrains(brains);
			return true;
		}
		else {
			tfInitialBrains.requestFocus();
			tfInitialBrains.selectAll();
			setMessage("Please enter a valid number of brains");
			return false;
		}
	}
	/**
	 * Comprueba si todos los campos cumplen las condiciones y si es asi los establece y devuelve true
	 * Si alguno de los campos no es correcto muestra el mensaje correspondiente
	 * ATENCION los campos que esten correctos si seran establecidos aunque alguno de ellos no lo sea
	 * @return true si todos los camppos son correctos
	 */
	public boolean allChecks() {
		return rowsCheck() && colsCheck() && roundsCheck() && moneyCheck() && brainsCheck();
	}
	/**
	 * Comprueba si el string recibido corresponde a un numero entero que cumpl laas condicione para ser cantidad de filas
	 * sino cumple las condiciones o el string no se puede convertir a entero devuelve -1
	 * @param numOfRows String con el numero de filas
	 * @return entero con el numero de filas si cumple las condiciones y -1 si no las cumple
	 */
	private int isValidNumOfRows(String numOfRows) {
		try {
			int num=Integer.parseInt(numOfRows);
			if(num>=MIN_NUM_ROWS && num<=MAX_NUM_ROWS) return num;
			else return -1;
		} catch (Exception e) {
			return -1;
		}
	}
	/**
	 * Comprueba si el string recibido corresponde a un numero entero que cumpl laas condicione para ser cantidad de columnas
	 * sino cumple las condiciones o el string no se puede convertir a entero devuelve -1
	 * @param numOfColumns String con el numero de columnas
	 * @return entero con el numero de columnas si cumple las condiciones y -1 si no las cumple
	 */
	private int isValidNumOfColumns(String numOfColumns) {
		try {
			int num=Integer.parseInt(numOfColumns);
			if(num>=MIN_NUM_COLS && num<=MAX_NUM_COLS) return num;
			else return -1;
		} catch (Exception e) {
			return -1;
		}
	}
	/**
	 * Comprueba si el string recibido corresponde a un numero entero que sea 0 o positivo
	 * sino cumple las condiciones o el string no se puede convertir a entero devuelve -1
	 * @param number String con el numero
	 * @return entero positivo o cero o -1 si no cumple las condiciones
	 */
	private int isPositiveOrZeroNumber(String number) {
		try {
			int num=Integer.parseInt(number);
			if(num>=0) return num;
			else return -1;
		} catch (Exception e) {
			return -1;
		}
	}
	/**
	 * Comprueba si el string recibido corresponde a un numero entero que sea positivo
	 * sino cumple las condiciones o el string no se puede convertir a entero devuelve -1
	 * @param number String con el numero
	 * @return entero positivo o -1 si no cumple las condiciones
	 */
	private int isPositiveNumber(String number) {
		try {
			int num=Integer.parseInt(number);
			if(num>0) return num;
			else return -1;
		} catch (Exception e) {
			return -1;
		}
	}
	/**
	 * Establece el numero de filas
	 * Si que el laberinto sea de forma cuadrada esta seleccionado entonces establece este mismo numero para las columnas
	 * @param numRows
	 */
	public void setNumRows(int numRows) {
		this.numRows = numRows;
		this.tfNumRows.setText(numRows+"");
		if (cbSquareShape.isSelected()) {
			setNumColumns(numRows);
		}
	}
	/**
	 * Establece el numero de columnas
	 * @param numColumns
	 */
	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
		this.tfNumColumns.setText(numColumns+"");
	}
	/**
	 * Establece el numero de rondas a jugar
	 * @param numRounds
	 */
	public void setNumRounds(int numRounds) {
		this.numRounds = numRounds;
		this.tfNumRounds.setText(numRounds+"");
	}
	/**
	 * Establece la cantidad de dinero inicial de la partida
	 * @param initialMoney
	 */
	public void setInitialMoney(int initialMoney) {
		this.initialMoney = initialMoney;
		this.tfInitialMoney.setText(initialMoney+"");
	}
	/**
	 * Establece la cantidad inicial de cerebros de la partida
	 * @param initialBrains
	 */
	public void setInitialBrains(int initialBrains) {
		this.initialBrains = initialBrains;
		this.tfInitialBrains.setText(initialBrains+"");
	}
	/**
	 * Establece el String que se muestra en el mensaje
	 * @param message
	 */
	public void setMessage(String message) {
		this.message.setText(message);
	}
	/**
	 * Borra el mensaje
	 */
	public void clearMessage() {
		this.message.setText("");
	}
	/**
	 * Comprueba si el mensaje esta borrado
	 * @return true si el mensaje esta borrado
	 */
	public boolean isMessageClear() {
		return this.message.getText().equals("");
	}
	/**
	 * Devuelve el numero de filas
	 * @return
	 */
	public int getNumRows() {
		return numRows;
	}
	/**
	 * Devuelve el numero de columnas
	 * @return
	 */
	public int getNumColumns() {
		return numColumns;
	}
	/**
	 * Devuelve el numero de rondas
	 * @return
	 */
	public int getNumRounds() {
		return numRounds;
	}
	/**
	 * Devuelve el numero de dinero inicial
	 * @return
	 */
	public int getInitialMoney() {
		return initialMoney;
	}
	/**
	 * Devuelve el numero de cerebros inicial
	 * @return
	 */
	public int getInitialBrains() {
		return initialBrains;
	}
	/**
	 * Devuelve el JButton que marca que se ha terminado de configurar
	 * @return
	 */
	public JButton getBtDone() {
		return btDone;
	}
	/**
	 * Devuelve si el modo de desarrollador ha sido activado
	 * @return
	 */
	public boolean isDeveloperMode() {
		return developerMode;
	}

	@Override
	public void setVisible(boolean b) {
		if (b) resetValuesToDefault();
		super.setVisible(b);
	}
}
