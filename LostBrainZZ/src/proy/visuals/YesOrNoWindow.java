package proy.visuals;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * 
 * @author Danel
 *
 */
public class YesOrNoWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 270;
	private static final int HEIGHT= 120;
	private JPanel pnTop = new JPanel();
	private JPanel pnButtons = new JPanel();
	private JButton btYes = new JButton("Yes");
	private JButton btNo = new JButton("No");
	private JLabel lbQuestion;
	private Font fQuestion = new Font("Question",1,15);
	/**
	 * Constructor que recibe la pregunta a hacer en la ventana
	 * @param question
	 */
	public YesOrNoWindow(String question) {

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		//Poner la ventana en el centro de la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		setResizable(false);
		setTitle(question);

		//Pregunta
		lbQuestion= new JLabel(question);
		lbQuestion.setFont(fQuestion);

		//Colocacion de paneles
		getContentPane().setLayout(new BorderLayout());

		getContentPane().add(pnTop, BorderLayout.NORTH);
		getContentPane().add(pnButtons, BorderLayout.CENTER);

		//Colocar pregunta
		pnTop.add(lbQuestion);
		lbQuestion.setAlignmentX(CENTER_ALIGNMENT);

		//Botones
		pnButtons.add(new JPanel().add(btYes));
		pnButtons.add(new JPanel().add(btNo));

		//Listeners
		btYes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btNo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				dispose();
			}
		});
	}
	/**
	 * Devuelve el JButton referente a la opcion "YES"
	 * @return
	 */
	public JButton getBtYes() {
		return btYes;
	}
	/**
	 * Devuelve el JButton referente a la opcion "NO"
	 * @return
	 */
	public JButton getBtNo() {
		return btNo;
	}

}
