package proy.visuals;

import java.awt.*;

import javax.swing.*;
/**
 * 
 * @author Danel
 *
 */
public class CreditsWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private static final int WIDTH=500;
	private static final int HEIGHT=750;
	private static final String TITLE= "Credits";
	private static final int BORDER_THICKNESS = 1;
	private static Font fTitle = new Font("Title",1,30);
	private static Font fStrong = new Font("Title",1,20);
	private static Font fNormal = new Font("Title",0,20);
	//JPanel
	private JPanel pnTop = new JPanel();
	private JPanel pnCentral = new JPanel();
	private JPanel pnBot= new JPanel();
	//JLabel
	private JLabel message = new JLabel("");

	public CreditsWindow() {
		//Colocación de paneles y componentes en paneles
		getContentPane().setLayout(new BorderLayout());
		//Paneles en su sitio
		getContentPane().add(pnCentral, BorderLayout.CENTER);
		getContentPane().add(pnBot, BorderLayout.SOUTH);
		getContentPane().add(pnTop,BorderLayout.NORTH);
		//Tamanyo y colocacion de la ventana
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		//Poner la ventana en el centro de la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setTitle(TITLE);
		setResizable(false);
		//Titulo
		JLabel title = new JLabel(TITLE,SwingConstants.CENTER);
		title.setFont(fTitle);
		pnTop.add(new JPanel().add(title));
		//Panel inferior
		pnBot.add(message);	
		//Panel Central
		pnCentral.setLayout(new BoxLayout(pnCentral, BoxLayout.Y_AXIS));

		pnCentral.add(getCreditsPart("Programador:", "Danel Arias"));
		pnCentral.add(getCreditsPart("Disenyador:", "Danel Arias", "con soporte de:","Unai Rodrigo"));
		pnCentral.add(getCreditsPart("Musica:", "Shinji Hosoe","Peter McConnel","Takada Masafumi","Shoji Meguro"));
		pnCentral.add(getCreditsPart("Voces y sonidos:", "Danel Arias","con ayuda de:","David Crespo"));
		pnCentral.add(getCreditsPart("Agradecimientos:", "David Crespo", "Unai Rodrigo", "Oier Mentxaka", "Andoni Eguiluz", "Jose Luis Arias", "Naia Arias", "Mario Lopez", "P*TOS NAZIS ARMY", "ALGEBRA B*TCH", "mas en concreto a:", "Jon Ander de la Puebla y", "Jon Gardeazabal"));

	}
	/**
	 * Establece el mensaje
	 * @param msg
	 */
	public void setMessage(String msg) {
		message.setText(msg);
	}
	/**
	 * Devuelve un panel con las JLabel para los creditos
	 * @param category
	 * @param names
	 * @return
	 */
	private JPanel getCreditsPart(String category,String...names) {
		JPanel p = new JPanel(new GridLayout(names.length, 2));
		JLabel c = new JLabel(category);
		c.setFont(fStrong);
		p.add(c);
		for (String s:names) {
			JLabel l = new JLabel(s);
			l.setFont(fNormal);
			if (s!=names[0]) {
				p.add(new JLabel(""));
			}
			p.add(l);
		}
		p.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, BORDER_THICKNESS,false));
		return p;
	}

}
