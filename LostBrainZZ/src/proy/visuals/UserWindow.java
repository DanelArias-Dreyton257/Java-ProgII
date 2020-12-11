package proy.visuals;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * 
 * @author Danel
 *
 */
public class UserWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	//Atributos ventana
	private static final int WIDTH=350;
	private static final int HEIGHT=100;
	private static final String TITLE= "Select User";
	//JPanel
	private JPanel pnCentral = new JPanel();
	private JPanel pnDown = new JPanel();
	//JtextField
	private JTextField txfUser = new JTextField("Anonymous",15);
	//JButton
	private JButton btDone = new JButton("DONE");
	//User selected
	private String userSelected = "Anonymous";

	public UserWindow() {
		//Colocación de paneles y componentes en paneles
		getContentPane().setLayout(new BorderLayout());
		//Panel central
		getContentPane().add(pnCentral, BorderLayout.CENTER);
		getContentPane().add(pnDown, BorderLayout.SOUTH);
		//Tamanyo y colocacion de la ventana
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		//Poner la ventana en el centro de la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setResizable(false);
		setTitle(TITLE);
		//Colocar componentes en paneles
		pnCentral.add(new JLabel("Enter UserName:"));
		pnCentral.add(txfUser);
		pnDown.add(btDone);
		//Listeners
		btDone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userSelected=txfUser.getText();
				txfUser.setText("Anonymous");
				dispose();
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				userSelected=txfUser.getText();
				txfUser.setText("Anonymous");
			}
		});
	}
	/**
	 * Devuelve el usuario seleccionado en la ventana
	 * @return
	 */
	public String getUserSelected() {
		return userSelected;
	}

}
