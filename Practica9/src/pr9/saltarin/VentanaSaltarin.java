package pr9.saltarin;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class VentanaSaltarin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel pEscenario;
	private JuegoSaltarin miJuego;
	
	/** Constructor de la ventana de juego
	 * @param juego	Juego asociado a la ventana
	 * @param anchoVent	P�xels ancho ventana
	 * @param altoVent	P�xels alto ventana
	 */
	public VentanaSaltarin( JuegoSaltarin juego, int anchoVent, int altoVent ) {
		this.miJuego = juego;
		// Configuraci�n general de la ventana
		setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
		setSize( anchoVent, altoVent );
		// Creaci�n de panel principal
		pEscenario = new JPanel();
		pEscenario.setLayout(null);
		// Asociaci�n de componentes
		getContentPane().add( pEscenario, BorderLayout.CENTER );
		// Eventos
		// 1. Escuchador de bot�n
		JButton bPausa = new JButton("Pausa");
		getContentPane().add( bPausa, BorderLayout.NORTH );
		bPausa.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					miJuego.setPausa( !miJuego.getPausa() );
				}
			} );
		// 2. Escuchador de teclado
		KeyListener kl = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)  // Escape -> acaba el juego
					miJuego.acabaJuego();
			}
		};
		pEscenario.addKeyListener( kl );
		bPausa.addKeyListener( kl );  // El bot�n de pausa tambi�n escucha escape
		pEscenario.requestFocus();  // Para que el foco de teclado est� al inicio en el panel
		// 3. Escuchador de rat�n
		pEscenario.addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				miJuego.activaSalto();
			}
		} );
		// 4. Escuchador de ventana
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				miJuego.acabaJuego();
			}
		} );
		// 5. Escuchador de movimiento de rat�n
		MouseMotionListener mml = new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				miJuego.mueveRaton( e.getX(), e.getY() );
			}
		};
		pEscenario.addMouseMotionListener( mml );
	}
	
	/** Devuelve el escenario de juego en la ventana (panel principal)
	 * @return	Panel de juego
	 */
	public JPanel getEscenario() {
		return pEscenario;
	}
	
}
