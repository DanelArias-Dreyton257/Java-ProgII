package examen202006.ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import examen202006.MainTienda;

/** Ventana de menú de ejercicio de ofertas y ventas
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
@SuppressWarnings("serial")
public class VentanaMenu extends JFrame {
	private JLabel lMensaje;
	private JTextArea taDatos;
	
	boolean sigue = true;
	
	public VentanaMenu() {
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setTitle( "Tienda Prog2 a su servicio" );
		setSize( 800, 600 );
		setLocationRelativeTo( null );  // Centra en la pantalla
		// Componentes
		lMensaje = new JLabel( " " );
		taDatos = new JTextArea();
		JScrollPane sp = new JScrollPane( taDatos );
		getContentPane().add( sp, BorderLayout.CENTER );
		getContentPane().add( lMensaje, BorderLayout.NORTH );
		JPanel pBotones = new JPanel();
		JButton bInfo = new JButton( "Información" );
		JButton bTarea2 = new JButton( "Tarea 2" );
		JButton bVenta = new JButton( "Venta" );
		JButton bSalir = new JButton( "Salir" );
		bInfo.setToolTipText( "Obtener información de las ventas realizadas" );
		bInfo.setToolTipText( "Realizar nueva venta" );
		pBotones.add( bInfo );
		pBotones.add( bTarea2 );
		pBotones.add( bVenta );
		pBotones.add( bSalir );
		getContentPane().add( pBotones, BorderLayout.SOUTH );
		setMensaje( "Bienvenido/a a nuestra tienda virtual" );
		// Eventos
		bInfo.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMensaje( "Resumen actual de ventas" );
				taDatos.setText( MainTienda.getSistema().getInfoVentas() );
			}
		});
		bTarea2.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMensaje( "Resultado de la tarea 2" );
				taDatos.setText( MainTienda.getSistema().getInfoT2() );
			}
		});
		bVenta.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible( false );
				VentanaVenta ventVenta = new VentanaVenta();
				ventVenta.setVisible( true );
			}
		});
		bSalir.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				MainTienda.getSistema().guardarDatos( MainTienda.NOM_FICHERO );
			}
		});
	}
	
	/** Cambia el mensaje de la ventana
	 * @param mensaje	Nuevo mensaje
	 */
	public void setMensaje( String mensaje ) {
		lMensaje.setText( mensaje );
	}

}
