package proy.visuals;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import proy.Main;
import proy.audio.EasterEggSong;

public class RecordWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	//Atributos ventana
	private static final int WIDTH=500;
	private static final int HEIGHT=400;
	private static final String TITLE= "Records";
	private static final Color BG_COLOR = new Color(238, 238, 238);
	//JPanels
	private JPanel pnCentral = new JPanel(new BorderLayout());
	private JPanel pnUp = new JPanel();
	private JPanel pnDown = new JPanel();
	//JTextArea
	private JTextArea txAInfo = new JTextArea("");
	private static final Font fInfo = new Font("RecordFont", 1, 17);
	//JButton
	private JButton btAllUsers = new JButton("All Users Info");
	private JButton btResetRecords = new JButton("Reset Records");
	//JComboBox
	private JComboBox<String> cbSelUser;
	private DefaultComboBoxModel<String> mdSelUser;
	//Easter Egg Sound
	private EasterEggSong EE_SOUND = new EasterEggSong("audios/Records_sound.wav", 40);

	public RecordWindow() {

		//Layout de la ventana
		getContentPane().setLayout(new BorderLayout());
		//Anyado los paneles correspondientes a la ventana
		getContentPane().add(pnCentral, BorderLayout.CENTER);
		getContentPane().add(pnUp, BorderLayout.NORTH);
		getContentPane().add(pnDown, BorderLayout.SOUTH);
		//Asociar model a JComboBox 
		mdSelUser = new DefaultComboBoxModel<>(Main.getRecordHolder().getUsers());
		cbSelUser = new JComboBox<>(mdSelUser);
		//Tamanyo y colocacion de la ventana
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		//Poner la ventana en el centro de la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setResizable(false);
		setTitle(TITLE);
		//Asignacion de diferentes componentes a sus paneles
		txAInfo.setEditable(false);
		txAInfo.setFont(fInfo);
		txAInfo.setBackground(BG_COLOR);
		pnCentral.add(new JScrollPane(txAInfo));
		pnUp.add(btAllUsers);
		pnUp.add(new JLabel("Select User Info:"));
		pnUp.add(cbSelUser);
		pnDown.add(btResetRecords);
		//Listeners
		btAllUsers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txAInfo.setText(Main.getRecordHolder().getInfoOfAllUsers());
			}
		});
		cbSelUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String user = (String) cbSelUser.getSelectedItem();
				txAInfo.setText(Main.getRecordHolder().getInfoOfUser(user));
				EE_SOUND.playSongInThread();
			}
		});
		btResetRecords.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.deleteRecords();
				txAInfo.setText("");
				updateComboBox();
				revalidate();
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				txAInfo.setText("");
			}
		});
	}
	/**
	 * Actualiza el modelo del ComboBox
	 */
	public void updateComboBox() {
		mdSelUser = new DefaultComboBoxModel<>(Main.getRecordHolder().getUsers());
		cbSelUser.setModel(mdSelUser);
	}

}
