
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
/**
 * 
 * @author Danel
 *
 */
public class VentanaUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int HEIGHT = 500;
	private static final int WIDTH = 800;
	private JPanel topPane = new JPanel();
	private JPanel centerPane = new JPanel();
	private JPanel leftPane = new JPanel();
	private JPanel bottomPane = new JPanel();
	private JTextField nick= new JTextField("random",12);
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
	
	private Font buttonFont = new Font("Arial", 1, 16);

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
		
		// Desabilitacion
		nick.setEnabled(false);
		lastLogIn.setEnabled(false);

		//Fuente de los botones
		prev.setFont(buttonFont);
		next.setFont(buttonFont);
		search.setFont(buttonFont);
		create.setFont(buttonFont);
		mod.setFont(buttonFont);
		del.setFont(buttonFont);
		//Borde de la lista de usuarios
		userList.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		
		addToContainer(topPane, prev,next,search,create,mod,del); //Anyade los botones al panel superior
		leftPane.add(userList);
		
		// BoxLayout del panel central
		centerPane.setLayout(new BoxLayout(centerPane,BoxLayout.Y_AXIS) );
		
		Component[] comps = {nick,pass,name,surnames,phone,lastLogIn,selUserType,emailList};
		String[] labelMsg = {"Nick:","Password:","Name:","Surnames:","Phone:","Last Log In:","Select User Type:","Email List:"};
		
		for (int i=0;i<comps.length;i++) {
			JPanel panel = rowOfBoxLayout(labelMsg[i], comps[i]);
			centerPane.add(panel);
		}
		
	}
	/** 
	 * Carga los datos del usuario en la ventana
	 * @param u
	 */
	public void loadUser(User u) {
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
	private void addUsersToList(User ...users) {
		DefaultListModel<String> model = new DefaultListModel<>();
		for (int i=0; i<users.length;i++) model.add(i, users[i].getNick());
		userList.setModel(model);
	}
	/**
	 * Establece el mensaje de abajo de la ventana
	 * @param msg
	 */
	public void showMessage(String msg) {
		getContentPane().remove(bottomPane);
		bottomPane = new JPanel();
		getContentPane().add(bottomPane,BorderLayout.SOUTH);
		JLabel message = new JLabel(msg);
		bottomPane.add(message,SwingConstants.CENTER);
	}
	 /** Metodo principal de prueba
	 * @param args No utilizados
	 */
	 public static void main(String[] args){
		 
		 //Creo 3 usuarios
		 User u = new User("D-Admin", "123456789", 123456789, "Danel", "Arias Alamo", UserType.ADMIN);
		 u.addEmailsToList("amigo@prueba.es","quehacesleyendoesto@tontoquienlolea.org");
		 u.updateLastLogInToNow();
		 
		 User u2 = new User("Teacher", "123456789", 987654321, "Andoni", "Eguiluz", UserType.MODERATOR);
		 u2.addEmailsToList("amigo@prueba.es","quehacesleyendoesto@tontoquienlolea.org");
		 u2.updateLastLogInToNow();
		 
		 User u3 = new User("Invitado1", "123456789", 666666666, "Solomeo", "Paredes", UserType.GUEST);
		 u3.addEmailsToList("amigo@prueba.es","quehacesleyendoesto@tontoquienlolea.org");
		 u3.updateLastLogInToNow();
		 
		 //Creacion de ventana
		 VentanaUsuario v = new VentanaUsuario();
		 //Anyade los usuarios a la JList de la ventana
		 v.addUsersToList(u,u2,u3);
		 v.showMessage("User window exercise finished");
		 v.loadUser(u2);//Carga usuario 2 en la ventana
		 v.setVisible(true);
		 
	 }

	
} 