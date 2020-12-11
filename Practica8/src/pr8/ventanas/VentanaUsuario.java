package pr8.ventanas;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import pr8.datos.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * 
 * @author Danel
 *
 */
public class VentanaUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int HEIGHT = 500;
	private static final int WIDTH = 800;
	private boolean userCreationMode = false;
	//password checkbox
	private JCheckBox showPass = new JCheckBox("Show password");
	//message
	private JLabel message = new JLabel("");
	//components
	private JPanel topPane = new JPanel();
	private JPanel centerPane = new JPanel();
	private JPanel leftPane = new JPanel();
	private JPanel bottomPane = new JPanel();
	private JTextField nick= new JTextField(12);
	private JPasswordField pass= new JPasswordField(12);
	private JTextField name= new JTextField(12);
	private JTextField surnames= new JTextField(20);
	private JTextField phone = new JTextField(9);
	private JTextField lastLogIn= new JTextField("Never", 10);
	private JComboBox<UserType> selUserType = new JComboBox<>(UserType.values());
	private JTextField emailList= new JTextField(50);
	//buttons
	private JButton prev = new JButton("Previous");
	private JButton next = new JButton("Next");
	private JButton search = new JButton("Search");
	private JButton create = new JButton("Create");
	private JButton mod = new JButton("Modify");
	private JButton del = new JButton("Delete");
	//Jlist
	private JList<String> userList = new JList<>();
	private JScrollPane listscroll = new JScrollPane(userList);
	
	private static final Font BUTTON_FONT = new Font("Arial", 1, 16);
	
	private UserGestor users;

	/**
	 * Constructor principal. Crea una nueva ventana con datos vacíos
	*/
	public VentanaUsuario(){
		setTitle("User editor");
		setMinimumSize( new Dimension(620, 340));
		setSize(WIDTH,HEIGHT);
		setAlwaysOnTop(true);
		setLocation(200, 100);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);//SI SE CIERRA LA VENTANA TERMINA
		topPane.setBackground(Color.GRAY);
		
		//Colocacion de paneles en la ventana
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(topPane,BorderLayout.NORTH);
		getContentPane().add(centerPane,BorderLayout.CENTER);
		getContentPane().add(leftPane,BorderLayout.WEST);
		getContentPane().add(bottomPane,BorderLayout.SOUTH);
		leftPane.setLayout(new BorderLayout());
		// Desabilitacion
		nick.setEnabled(false);
		pass.setEchoChar('*');
		lastLogIn.setEnabled(false);

		//Fuente de los botones
		prev.setFont(BUTTON_FONT);
		next.setFont(BUTTON_FONT);
		search.setFont(BUTTON_FONT);
		create.setFont(BUTTON_FONT);
		mod.setFont(BUTTON_FONT);
		del.setFont(BUTTON_FONT);
		//Borde de la lista de usuarios
		userList.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		
		bottomPane.add(message,SwingConstants.CENTER);
		addToContainer(topPane, prev,next,search,create,mod,del); //Anyade los botones al panel superior
		leftPane.add(listscroll);
		
		// BoxLayout del panel central
		centerPane.setLayout(new BoxLayout(centerPane,BoxLayout.Y_AXIS) );
		
		Component[] comps = {nick,pass,name,surnames,phone,lastLogIn,selUserType,emailList};
		String[] labelMsg = {"Nick:","Password:","Name:","Surnames:","Phone:","Last Log In:","Select User Type:","Email List:"};
		
		for (int i=0;i<comps.length;i++) {
			JPanel panel = rowOfBoxLayout(labelMsg[i], comps[i]);
			if (i==1) panel.add(showPass);
			centerPane.add(panel);
		}
		//listeners
		userList.addListSelectionListener(
				new ListSelectionListener(){
					@Override
					public void valueChanged(ListSelectionEvent e) {
						loadUserByNick();
						clearMessage();
					}
				});
		prev.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						int index=userList.getSelectedIndex();
						if (index>=0) {
							userList.setSelectedIndex(index-1);
							loadUserByNick();
						}
					}
				});
		next.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						int index=userList.getSelectedIndex();
						if (index>=0) {
							userList.setSelectedIndex(index+1);
							loadUserByNick();
						}
					}
				});
		search.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						User selectedUser = users.getUserByName(name.getText());
						nick.setText("");
						pass.setText("");
						surnames.setText("");
						phone.setText("");
						lastLogIn.setText("Never");
						selUserType.setSelectedIndex(0);
						emailList.setText("");
						loadUser(selectedUser);
						if (selectedUser==null) userList.clearSelection();
						else userList.setSelectedIndex(users.getIndexOfUser(selectedUser));
					}
				});
		create.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						if (userCreationMode){
							long tel = phoneNumberValidation();
							ArrayList<String> emails = emailListValidation();
							if (tel>=0 && emails!=null) {
								userCreationMode=false;
								nick.setEnabled(false);
								prev.setEnabled(true);
								next.setEnabled(true);
								search.setEnabled(true);
								del.setEnabled(true);
								userList.setEnabled(true);
								mod.setText("Modify");
								User us = new User(nick.getText(), String.copyValueOf(pass.getPassword()), tel, name.getText(), surnames.getText(), (UserType)selUserType.getSelectedItem());
								us.setEmailList(emails);
								us.updateLastLogInToNow();
								users.addUsers(us);
								addUsersToList(users);
							}
						}
						else{
							userCreationMode=true;
							nick.setEnabled(true);
							prev.setEnabled(false);
							next.setEnabled(false);
							search.setEnabled(false);
							del.setEnabled(false);
							userList.setEnabled(false);
							mod.setText("Cancel");
							//clear components
							nick.setText("");
							name.setText("");
							pass.setText("");
							surnames.setText("");
							phone.setText("");
							lastLogIn.setText("Never");
							selUserType.setSelectedIndex(0);
							emailList.setText("");
							nick.requestFocus();
						}
					}
				});
		mod.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						if (userCreationMode) {
							userCreationMode=false;
							nick.setEnabled(false);
							prev.setEnabled(true);
							next.setEnabled(true);
							search.setEnabled(true);
							del.setEnabled(true);
							userList.setEnabled(true);
							mod.setText("Modify");
						}
						else {
							String selectedNick=userList.getSelectedValue();
							User selectedUser = users.getUserByNick(selectedNick);
							clearMessage();
							try {
								selectedUser.setName(name.getText());
								selectedUser.setPassword(String.copyValueOf(pass.getPassword()));
								selectedUser.setSurnames(surnames.getText());
								selectedUser.setType((UserType)selUserType.getSelectedItem());
								long telVal = phoneNumberValidation();
								if (telVal>=0) selectedUser.setPhoneNum(telVal);
								ArrayList<String> emails = emailListValidation();
								if (emails!=null) selectedUser.setEmailList(emails); 
							} catch (NullPointerException ex) {
								showMessage("No user selected in order to modify");
							}
						}
					}
				});
		del.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						String selectedNick=userList.getSelectedValue();
						User selectedUser = users.getUserByNick(selectedNick);
						users.removeUsers(selectedUser);
						addUsersToList(users);
						userList.clearSelection();
						nick.setText("");
						pass.setText("");
						name.setText("");
						surnames.setText("");
						phone.setText("");
						lastLogIn.setText("Never");
						selUserType.setSelectedIndex(0);
						emailList.setText("");
					}
				});
		phone.addFocusListener(
				new FocusListener(){
					@Override
					public void focusGained(FocusEvent e) {
					}
					@Override
					public void focusLost(FocusEvent e) {
						long t = phoneNumberValidation();
						if (t<0) {
							phone.selectAll();
							phone.requestFocus();
						}
					}
				});
		emailList.addFocusListener(
				new FocusListener(){
					@Override
					public void focusGained(FocusEvent e) {
					}
					@Override
					public void focusLost(FocusEvent e) {
						ArrayList<String> em = emailListValidation();
						if (em==null) {
							emailList.selectAll();
							emailList.requestFocus();
						}
					}
				});
		showPass.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (showPass.isSelected()) pass.setEchoChar((char)0);
						else pass.setEchoChar('*');
					}
				});
	}
	/** 
	 * Carga los datos del usuario en la ventana
	 * @param u
	 */
	public void loadUser(User u) {
		if (u!=null) {
			nick.setText(u.getNick());
			pass.setText(u.getPassword());
			name.setText(u.getName());
			surnames.setText(u.getSurnames());
			phone.setText(String.valueOf(u.getPhoneNum()));
			Date lastLI = u.getLastLogIn();
			if (lastLI!=null) {
				SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
				lastLogIn.setText(dt.format(lastLI));
			}
			else lastLogIn.setText("Never");
			selUserType.setSelectedItem(u.getType());
			emailList.setText(u.getEmailListToString());
			int index = users.getIndexOfUser(u);
			if (index>=0)userList.setSelectedIndex(index);
		}
	}
	/**
	 * Crea y devuelve un panel con una etiqueta y un componente
	 * @param msg
	 * @param c
	 * @return panel con JLabel y el componente
	 */
	private JPanel rowOfBoxLayout(String msg, Component c) {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout( new FlowLayout(FlowLayout.LEFT) ); // flow ajustado a la izquierda
		addToContainer(tempPanel, new JLabel(msg), c);
		return tempPanel;	
	}
	/**
	 * Anyade los componentes al contenedor
	 * @param c
	 * @param comps
	 */
	private void addToContainer(Container c, Component ...comps) {
		 for (Component cp:comps) c.add(cp);
	}
	/**
	 * Anyade los usuarios al JList 
	 * @param users
	 */
	public void addUsersToList(UserGestor ug) {
		DefaultListModel<String> model = new DefaultListModel<>();
		for (int i=0; i<ug.getNumUsers();i++) model.add(i, ug.getUser(i).getNick());
		userList.removeAll();
		userList.setModel(model);
		users=ug;
	}
	/**
	 * Establece el texto del mensaje
	 * @param msg
	 */
	private void showMessage(String msg) {
		message.setText(msg);
	}
	/**
	 * Establece el texto del mensaje a un string vacio
	 */
	private void clearMessage() {
		message.setText("");
	}
	// Valida el teléfono actualmente en pantalla (vacío o numérico).
	// Si es correcto devuelve su valor en formato numérico.
	// Si es incorrecto devuelve -1, y saca el mensaje de "Teléfono incorrecto" en la ventana
	private long phoneNumberValidation() {
		if (phone.getText().equals("")) return 0; // String vacío = teléfono 0
		long telef = -1;
		try {
			telef = Long.parseLong( phone.getText() );
		} catch (NumberFormatException e) { // Si salta la excepción, telef se queda con -1
			showMessage("Incorrect Phone number: " + phone.getText() );
		}
		if (telef>-1) clearMessage();
		return telef;
	}

	// Valida la lista de emails actualmente en pantalla (vacía o strings separados por comas).
	// Si es correcto devuelve su valor en formato de ArrayList<String>.
	// Si es incorrecto (algún email no tiene la arroba) devuelve null, y saca el mensaje de
	// "Lista de emails incorrecta" en la ventana
	private ArrayList<String> emailListValidation() {
		ArrayList<String> lE = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer( emailList.getText(), "," );
		while (st.hasMoreTokens()) {
			String s = st.nextToken().trim();
			if (!s.contains("@") || !s.contains(".")) { // Si no contiene @ es un email incorrecto
				showMessage("Incorrect email: " + s);
				return null;
			}
			lE.add( s );
		}
		clearMessage();
		return lE;
	}
	/**
	 * Coge el nick seleccionado de la JList, lo busca en el gestor y lo carga en pantalla
	 */
	private void loadUserByNick() {
		String selectedNick=userList.getSelectedValue();
		User selectedUser = users.getUserByNick(selectedNick);
		loadUser(selectedUser);
	}

} 
