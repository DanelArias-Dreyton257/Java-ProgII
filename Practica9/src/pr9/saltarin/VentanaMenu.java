package pr9.saltarin;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Ventana de men� de juego saltar�n
 * @author AE
 */
public class VentanaMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	JButton bJugar;
	JButton bAcabar;
	JuegoSaltarin jSaltarin;
	
	/** Construye la ventana de men�
	 * @param jSaltarin	Objeto de juego asociado con la ventana
	 */
	public VentanaMenu( JuegoSaltarin jSaltarin ) {
		this.jSaltarin = jSaltarin;
		// Configuraci�n ventana
		setSize( 320, 200 );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		// Creaci�n componentes y contenedores
		bJugar = new JButton( "Jugar" );
		bAcabar = new JButton( "Acabar" );
		JPanel pPrincipal = new JPanel();
		JLabel lMensaje = new JLabel( "<html><br/><br/>Juego Saltarin<br/><br/></html>" );
		// Asignaci�n de componentes a contenedores
		pPrincipal.add( bJugar );
		pPrincipal.add( bAcabar );
		getContentPane().add( pPrincipal, BorderLayout.CENTER );
		getContentPane().add( lMensaje, BorderLayout.NORTH );
		// Formato componentes
		lMensaje.setHorizontalAlignment( JLabel.CENTER );
		// Escuchadores
		bJugar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread nuevoJuego = new Thread() {
					@Override
					public void run() {
						VentanaSaltarin vSaltarin = new VentanaSaltarin( jSaltarin, JuegoSaltarin.VENT_ANCHO, JuegoSaltarin.VENT_ALTO );
						jSaltarin.setVentana( vSaltarin );
						vSaltarin.setVisible( true );
						jSaltarin.bucleDejuego();
					}
				};
				nuevoJuego.start();
			}
		});
		bAcabar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
