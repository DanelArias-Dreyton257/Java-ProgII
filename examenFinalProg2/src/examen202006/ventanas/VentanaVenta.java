package examen202006.ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import examen202006.MainTienda;
import examen202006.datos.Oferta;
import examen202006.datos.Venta;

/** Ejemplo de uso de JList con modelo de datos por defecto
 * (ejemplo también de etiqueta en glasspane - panel cristal)
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
@SuppressWarnings("serial")
public class VentanaVenta extends JFrame {
	private JList<Oferta> lOfertasDisponibles;
	private DefaultListModel<Oferta> mOfertasDisponibles;  // Modelo de datos de la lista de ofertas
	private JList<Oferta> lOfertasVendidas;
	private DefaultListModel<Oferta> mOfertasVendidas;  // Modelo de datos de la lista de ventas
	private JComboBox<String> cbUsuarios;
	private DefaultComboBoxModel<String> mUsuarios;  // Modelo de datos de la lista de usuarios
	private JLabel lMensajeOfertas;
	private JLabel lMensajeVentas;
	private JButton bAnyadir;  // Botón de añadir
	private JButton bBorrar;   // Botón de borrar

	private Color colorLista = Color.BLACK;
	
	private boolean closed = false; //Logica de cierre
	
	private JPanel pOferta;  // Panel para la oferta en curso
	private Oferta ofertaEnCurso;  // Oferta en curso en el panel izquierdo
	
	public VentanaVenta() {
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setTitle( "Venta" );
		setSize( 800, 600 );
		setLocationRelativeTo( null );  // Centrar en pantalla
		// Componentes - paneles principales
		JPanel pPrincipal = new JPanel( new GridLayout( 1, 2 ) );
		JPanel pIzquierdo = new JPanel( new BorderLayout() );
		JPanel pDerecho = new JPanel( new BorderLayout() );
		pPrincipal.add( pIzquierdo );
		pPrincipal.add( pDerecho );
		getContentPane().add( pPrincipal, BorderLayout.CENTER );
		// Panel con combo superior
		JPanel pSuperior = new JPanel();
		pSuperior.add( new JLabel( "Selecciona usuario:" ) );
		mUsuarios = new DefaultComboBoxModel<>();
		cbUsuarios = new JComboBox<>( mUsuarios );
		initUsuarios();
		cbUsuarios.setEditable( true );  // Combo editable (se pueden meter usuarios adicionales)
		pSuperior.add( cbUsuarios );
		getContentPane().add( pSuperior, BorderLayout.NORTH );
		// Panel izquierdo
		lMensajeOfertas = new JLabel( "Selecciona oferta a vender:" );
		lOfertasDisponibles = new JList<>();
		mOfertasDisponibles = new DefaultListModel<Oferta>();
		lOfertasDisponibles.setModel( mOfertasDisponibles );
		
		initOfertas(); // Carga las ofertas en la lista
		pIzquierdo.add( lMensajeOfertas, BorderLayout.NORTH );
		pIzquierdo.add( new JScrollPane( lOfertasDisponibles ), BorderLayout.CENTER );
		pOferta = new JPanel( new BorderLayout() );
		pIzquierdo.add( pOferta, BorderLayout.SOUTH );
		// Panel derecho
		lMensajeVentas = new JLabel( "Carrito de la compra" );
		lOfertasVendidas = new JList<>();
		mOfertasVendidas = new DefaultListModel<Oferta>();
		lOfertasVendidas.setModel( mOfertasVendidas );
		lOfertasDisponibles.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		estableceColorLista();
		pDerecho.add( lMensajeVentas, BorderLayout.NORTH );
		pDerecho.add( new JScrollPane( lOfertasVendidas ), BorderLayout.CENTER );
		// Botonera
		JPanel pBotonera = new JPanel();
		bAnyadir = new JButton( "Venta de oferta actual" );
		pBotonera.add( bAnyadir );
		bBorrar = new JButton( "Quitar línea de venta" );
		pBotonera.add( bBorrar );
		JButton bAcabar = new JButton( "Confirmar venta" );
		pBotonera.add( bAcabar );
		getContentPane().add( pBotonera, BorderLayout.SOUTH );
		
		// Eventos
		bAnyadir.addActionListener( new ActionListener() {  // Botón de añadir - pasar oferta a venta
			@Override
			public void actionPerformed(ActionEvent e) {
				anyadirOferta();
			}
		});
		bBorrar.addActionListener( new ActionListener() {  // Botón de borrado de línea de venta seleccionada
			@Override
			public void actionPerformed(ActionEvent e) {
				if (lOfertasVendidas.getSelectedIndex()!=-1) {
					mOfertasVendidas.remove( lOfertasVendidas.getSelectedIndex() );
					lOfertasVendidas.setSelectedIndices( new int[0] );
				}
			}
		});
		bAcabar.addActionListener( new ActionListener() {  // Botón de procesar carrito
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cbUsuarios.getSelectedItem()==null) {
					JOptionPane.showMessageDialog( VentanaVenta.this, "Para realizar la venta, debes indicar el usuario", "Error en venta", JOptionPane.ERROR_MESSAGE );
				} else {
					String usuario = (String) cbUsuarios.getSelectedItem();
					if (mOfertasVendidas.size() > 0) {  // Si hay alguna venta... generar el objeto de venta y añadir al mapa
						Venta venta = new Venta( System.currentTimeMillis(), new ArrayList<Oferta>() );
						for (int i=0; i<mOfertasVendidas.size(); i++) {
							Oferta oferta = mOfertasVendidas.get( i );
							venta.getlVenta().add( oferta );
						}
						MainTienda.getSistema().addVenta( usuario, venta, true );
					}
					dispose();
					MainTienda.getVentana().setVisible( true );
				}
			}
		});
		lOfertasDisponibles.addListSelectionListener( new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && lOfertasDisponibles.getSelectedValue()!=null) {
					Oferta ofertaActual = lOfertasDisponibles.getSelectedValue();
					ofertaEnCurso = (Oferta) ofertaActual.clone();  // Crea un nuevo objeto duplicado de esa oferta para poderlo editar
					actualizarPanelIzquierdo();
				}
			}
		});
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				MainTienda.getVentana().setVisible( true );  // Reactiva la ventana de menú al cerrar esta
				closed=true;
			}
		});

		//4-2 ctrl+supr borra la seleccion
		lOfertasVendidas.addKeyListener(new KeyAdapter() {
			boolean ctrlPressed= false;
			boolean suprPressed=false;
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_CONTROL) ctrlPressed = true;
				else if (e.getKeyCode()==KeyEvent.VK_DELETE) suprPressed = true;
				else { ctrlPressed = false; suprPressed = false;}
				if (ctrlPressed && suprPressed) {
					int id = lOfertasVendidas.getSelectedIndex();
					if (id!=-1) {
						mOfertasVendidas.remove(id);
					}
					ctrlPressed = false; suprPressed = false;
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				//"deselecciona" las teclas correspondientes
				if (e.getKeyCode()==KeyEvent.VK_CONTROL) ctrlPressed = false;
				else if (e.getKeyCode()==KeyEvent.VK_DELETE) suprPressed = false;
			}
		});
		
		//4-3 al usuario indicado al seleccionarlo le aparece una ventana informandole de las ventas relaizadas anteriores
		cbUsuarios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String u = (String) cbUsuarios.getSelectedItem();
				ArrayList<Venta> a = MainTienda.getSistema().getListaVentas(u);
				JOptionPane.showMessageDialog(VentanaVenta.this, ventasStr(a),"Ventas realizadas",JOptionPane.INFORMATION_MESSAGE);
			}
			private String ventasStr(ArrayList<Venta> a) {
				String ret = "";
				for (Venta v: a) {
					for (Oferta o:v.getlVenta()) {
						ret+=o.toString()+"\n";
					}
				}
				return ret;
			}
		});
		
		//4-1 Permite arrastrar ofertas a la lista de vendidas
		addMouseListener(new MouseAdapter() {
			boolean dragged = true;
			@Override
			public void mousePressed(MouseEvent e) {
				int mouseX = e.getX();
				int mouseY = e.getY();
				int pX = pOferta.getX();
				int pY = pOferta.getY();
				int pX2= pX+pOferta.getWidth();
				int pY2= pY+pOferta.getHeight();
				boolean validX = mouseX>=pX && mouseX<=pX2;
				boolean validY = mouseY>=pY && mouseY<=pY2;
				/*
				System.out.println("["+pX+","+pY+"] to ["+pX2+","+pY2+"]");
				System.out.println("MOUSE: ["+mouseX+","+mouseY+"]");
				System.out.println("["+validX+","+validY+"]");
				*/
				if(validX &&validY) dragged=true;
				else dragged=false;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				int mouseX = e.getX();
				int mouseY = e.getY();
				int pX = pDerecho.getX();
				int pY = pDerecho.getY(); 
				int pX2= pX+pDerecho.getWidth();
				int pY2= pY+pDerecho.getHeight();
				boolean validX = mouseX>=pX && mouseX<=pX2;
				boolean validY = mouseY>=pY && mouseY<=pY2;
				/*
				System.out.println("["+pX+","+pY+"] to ["+pX2+","+pY2+"]");
				System.out.println("MOUSE: ["+mouseX+","+mouseY+"]");
				System.out.println("["+validX+","+validY+"]");
				*/
				if (validX && validY &&dragged) {
					anyadirOferta();
				}
				dragged=false;
			}
			
		});
		
		//Hilo colorintxis, hilo que va cambiando el color del texto de la lista de ofertas disponibles
		Thread negroYazul = new Thread() {
				@Override
				public void run() {
					final long sleepTimemillis = 15;
					while(!closed) {
					int cont = colorLista.getBlue();
						//vuelve el color azul gradualmente
						while(colorLista.getBlue()<255 && !closed && lOfertasDisponibles.getSelectedIndex()==-1) {
							colorLista= new Color(0, 0, cont);
							estableceColorLista();
							try {
								Thread.sleep(sleepTimemillis);
							} catch (Exception e) {}
							revalidate();
							cont++;
						}
						cont--;
						//vuelve el color negro gradualmente
						while(colorLista.getBlue()>0 && !closed && lOfertasDisponibles.getSelectedIndex()==-1) {
							colorLista= new Color(0, 0, cont);
							estableceColorLista();
							try {
								Thread.sleep(sleepTimemillis);
							} catch (Exception e) {}
							revalidate();
							cont--;
						}
						colorLista = Color.BLACK;
					}
					colorLista = Color.BLACK;
					estableceColorLista();
					revalidate();
					
				}	
		}; 
		negroYazul.setDaemon(true);
		negroYazul.start();
		
	}
	/**
	 * Permite anyadir la oferta en curso a la lista de ofertas vendidas
	 */
	private void anyadirOferta() {
		if (ofertaEnCurso != null) {  // Si hay oferta en curso, ver si pasar a venta
			if (ofertaEnCurso.datosCorrectosParaVenta()) {
				mOfertasVendidas.addElement( ofertaEnCurso );
				ofertaEnCurso = null;  // Se queda sin oferta en curso la izquierda
				actualizarPanelIzquierdo();
				lOfertasDisponibles.setSelectedIndices( new int[0] );
			} else {
				JOptionPane.showMessageDialog( VentanaVenta.this, "Introduce datos para la venta (la medición tiene que ser positiva)", "Error en venta", JOptionPane.ERROR_MESSAGE );
			}
		}
		
	}

	/**
	 * Establece el color de la lista segun la variable "colorLista"
	 */
	private void estableceColorLista() {
		lOfertasDisponibles.setCellRenderer( new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                      boolean isSelected, boolean cellHasFocus) {
                 Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                 setForeground(colorLista);
                 return c;
            }
		});
	}
	
	// Inicializa los usuarios existentes
	private void initUsuarios() {
		for (String usuario : MainTienda.getSistema().getUsuarios()) {
			mUsuarios.addElement( usuario );
		}
	}
	
	// Inicializa las ofertas disponibles
	private void initOfertas() {
		for (Oferta oferta : MainTienda.getSistema().getListaOfertas()) {
			mOfertasDisponibles.addElement( oferta );
		}
	}
	
	// Actualiza el panel izquierdo con la oferta en curso
	private void actualizarPanelIzquierdo() {
		if (ofertaEnCurso==null) {
			pOferta.removeAll();
		} else {
			pOferta.removeAll();
			pOferta.add( ofertaEnCurso.getPanelDeDatos(), BorderLayout.CENTER );
		}
		pOferta.revalidate();  // Se rehace el panel de datos de la oferta (imprescindible cuando se cambia en caliente el panel en Swing)
	}

}
