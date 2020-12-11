package examen202006ext.gui;
import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import examen202006ext.data.*;
import examen202006ext.utils.Geometria;
import examen202006ext.Datausto;
import examen202006ext.EstadoEditor;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/** Ventana del editor de niveles de Datausto
*/
public class VentanaEditorNivel {

	public static final String[] NOMS_COLORES = { "Negro", "Amarillo", "Rojo", "Verde", "Cyan", "Azul", "Blanco" };
	public static final Color[] COLORES = { Color.black, Color.yellow, Color.red, Color.green, Color.cyan, Color.blue, Color.white };
	public static final String[] NOMS_COLORES_TRANSP = { "Negro", "Amarillo", "Rojo", "Verde", "Cyan", "Azul", "Blanco", "Transparente" };
	public static final Color[] COLORES_TRANSP = { Color.black, Color.yellow, Color.red, Color.green, Color.cyan, Color.blue, Color.white, null };

	// Atributos de la IU
	private JFrame ventana;             // Ventana que se visualiza
	private boolean cerrada;            // Lógica de cierre (false al inicio)
	private Tablero tablero;            // Tablero con panel principal
	private PanelGrafico panelTablero;  // Panel gráfico de tablero
	private JLabel lMens;               // Etiqueta de texto de mensajes en la parte inferior
	private JComboBox<String> cbColoresBorde;  // Combo de colores
	private JComboBox<String> cbColoresRelleno;  // Combo de colores de fondo
	private JComboBox<ElementoDatausto> cbFormas;   // Combo de formas
	
	// Atributos de la lógica del editor
	private EstadoEditor estado;
	private ArrayList<ElementoDatausto> listaElementosAdicionales;
	private ElementoDatausto grafSel = null;  // Gráfico seleccionado
	private double rotInicial;  // Rotación inicial del gráfico seleccionado
	private double xInicial;  // Coord. x inicial del gráfico movido
	private double yInicial;  // Coord. y inicial del gráfico movido

	/** Construye una nueva ventana de editor a la espera de ser activada
	 * @param anchura	Anchura en píxels (valor positivo) de la zona de pintado
	 * @param altura	Altura en píxels (valor positivo) de la zona de pintado
	 */
	public VentanaEditorNivel( int anchura, int altura ) {
		setLookAndFeel();
		cerrada = false;
		ventana = new JFrame( "Editor de niveles Datausto" );
		ventana.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		// Resto de inicialización (lógica del editor)
		init();
		// Componentes y contenedores
		tablero = new Tablero( Datausto.RADIO_HEXAGONO, 10, 10 );
		panelTablero = tablero.getPanel();
		panelTablero.setPreferredSize( new Dimension( anchura, altura ) );
		panelTablero.setBorder( BorderFactory.createLineBorder( Color.BLACK, 2 ) );
		lMens = new JLabel( " " );
		// Radio buttons
		ButtonGroup bg = new ButtonGroup();
		ArrayList<JRadioButton> radioButtons = new ArrayList<>(); 
		boolean seleccionado = true;
		for (EstadoEditor est : EstadoEditor.values()) {
			JRadioButton rb = new JRadioButton( est.getTextoBoton(), seleccionado );
			rb.setName( est.toString() );  // Guardamos el estado de cada radio button en el nombre
			seleccionado = false;
			bg.add( rb );
			radioButtons.add( rb );
		}
		JPanel pIzq = new JPanel();
		pIzq.setLayout( new BoxLayout( pIzq, BoxLayout.Y_AXIS ) );
		cbColoresBorde = new JComboBox<>( NOMS_COLORES );
		cbColoresRelleno = new JComboBox<>( NOMS_COLORES_TRANSP );
		cbColoresRelleno.setSelectedItem( "Transparente" );
		cbFormas = new JComboBox<>( ElementoDatausto.LISTA_ELEMENTOS_VISUALES.toArray( new ElementoDatausto[0] ) );
		cbFormas.setMaximumSize( new Dimension( 120, 30 ) );  // Para que swing no le dé más de 30 píxels de altura
		cbColoresBorde.setMaximumSize( new Dimension( 120, 30 ) ); // Idem
		cbColoresRelleno.setMaximumSize( new Dimension( 120, 30 ) ); // Idem
		cbColoresBorde.setAlignmentX(0);  // Para que la alineación izquierda sea la misma que la de los label y radios
		cbColoresRelleno.setAlignmentX(0);   // Idem
		cbFormas.setAlignmentX(0);   // Idem
		JButton bCargar = new JButton( "Cargar" );
		JButton bSalvar = new JButton( "Salvar" );
		JButton bNuevo = new JButton( "Nuevo" );
		// Asociación de componentes y contenedores
		pIzq.add( new JLabel( "Color:" ) );
		pIzq.add( cbColoresBorde );
		pIzq.add( new JLabel( "Color relleno:" ) );
		pIzq.add( cbColoresRelleno );
		pIzq.add( new JLabel( "Acción:" ) );
		for (JRadioButton rb : radioButtons) { 
			pIzq.add( rb );
		}
		pIzq.add( new JLabel( "Forma a crear:" ) );
		pIzq.add( cbFormas );
		pIzq.add( new JPanel() ); // Espacio intermedio
		pIzq.add( bCargar );
		pIzq.add( bSalvar );
		pIzq.add( bNuevo );
		
		ventana.getContentPane().add( panelTablero, BorderLayout.CENTER );
		ventana.getContentPane().add( lMens, BorderLayout.SOUTH );
		ventana.getContentPane().add( pIzq, BorderLayout.WEST );
		ventana.pack();
		ventana.setLocationRelativeTo( null );
		// Botones 
		// Gestión de eventos
		ventana.addWindowListener( new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				recalculaGrafico();  // Primer dibujado
			}
			@Override
			public void windowClosing(WindowEvent e) {
				cerrada = true;
				guardaConfig();
			}
		});
		for (JRadioButton rb : radioButtons) { 
			rb.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (rb.isSelected()) { 
						EstadoEditor est = EstadoEditor.valueOf( rb.getName() );
						lMens.setText( est.getHint() ); 
						estado = est;  // Cambia el estado del editor
						grafSel = null; 
						recalculaGrafico(); 
					}
				}
			} );
		}
		MouseAdapter ml = new MouseAdapter() {
			private Point pointPressed = null;
			private Point pointInit = null;
			@Override
			public void mouseReleased(MouseEvent e) {
				if (pointInit.equals(e.getPoint())) {
					mouseClick( e );
				} else {
					mouseDragEnd( (PanelGrafico) e.getComponent(), pointInit, pointPressed, e.getPoint() );
				}
				pointPressed = null;
				pointInit = null;
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pointPressed = e.getPoint();
				pointInit = e.getPoint();
			}
			public void mouseDragged(MouseEvent e) {
				mouseDrag( pointInit, pointPressed, e.getPoint() );
				pointPressed = e.getPoint();
			}
		};
		panelTablero.addMouseListener( ml );
		panelTablero.addMouseMotionListener( ml );
		bCargar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarFichero();
			}
		});
		bSalvar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvarFichero();
			}
		});
		bNuevo.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nuevoGrafico();
			}
		});
	}

	// Inicializa la lógica del editor gráfico
	private void init() {
		estado = EstadoEditor.CREACION;
		listaElementosAdicionales = new ArrayList<>();
	}
	
	/** Activa la ventana del editor gráfico
	 */
	public void activa() {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				ventana.setVisible( true );
				cargaConfig();
			}
		};
		lanza( r );
	}
	
	/** Cierra la ventana del editor gráfico (también ocurre cuando se pulsa el icono de cierre)
	 */
	public void acaba() {
		if (!cerrada) ventana.dispose();
		cerrada = true;
		guardaConfig();
	}
	
	/** Consultor de estado de visibilidad de la ventana
	 * @return	false si sigue activa, true si ya se ha cerrado
	 */
	public boolean estaCerrada() {
		return cerrada;
	}
	
	/** Cambia el mensaje de la ventana (línea inferior de mensajes)
	 * @param mensaje	Texto de mensaje
	 */
	public void setMensaje( String mensaje ) {
		if (mensaje==null || mensaje.isEmpty())
			lMens.setText( " " );
		else
			lMens.setText( mensaje );
	}
	
	
	// Lanza el proceso r desde Swing (directamente si el hilo actual es el de Swing, a través de invokeAndWait si no)
	private void lanza( Runnable r ) {
		if (SwingUtilities.isEventDispatchThread()) { // Si es el hilo de swing se visualiza
			r.run();
		} else { // Si es otro hilo se deja para swing la visualización
			try {
				SwingUtilities.invokeAndWait( r );
			} catch (InvocationTargetException | InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	// Intenta poner el look & feel nimbus (solo la primera vez que se crea una ventana)
		private static boolean lookAndFeelIntentado = false;
	private void setLookAndFeel() {
		if (lookAndFeelIntentado) return;
		lookAndFeelIntentado = true;
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            return;
		        }
		    }
		} catch (Exception e) {} // Si no está disponible nimbus, no se hace nada
	}

	/** Devuelve el objeto ventana (JFrame) correspondiente a la ventana gráfica
	 * @return	Objeto JFrame de la ventana
	 */
	public JFrame getJFrame() {
		return ventana;
	}
	
	/** Devuelve el mensaje actual de la ventana (línea inferior de mensajes)
	 * @return	Mensaje actual
	 */
	public String getMensaje() {
		return lMens.getText();
	}
	
	/** Cambia el tipo de letra de la línea inferior de mensajes
	 * @param font	Tipo de letra a utilizar
	 */
	public void setMensajeFont( Font font ) {
		lMens.setFont( font );
	}
	
	/** Devuelve la altura del panel de dibujo de la ventana
	 * @return	Altura del panel principal (última coordenada y) en píxels
	 */
	public int getAltura() {
		return panelTablero.getHeight()-1;
	}
	
	/** Devuelve la anchura del panel de dibujo de la ventana
	 * @return	Anchura del panel principal (última coordenada x) en píxels
	 */
	public int getAnchura() {
		return panelTablero.getWidth()-1;
	}
	
	/** Borra toda la ventana (elimina todos los gráficos existentes) y la pinta de color blanco
	 */
	public void borra() {
		listaElementosAdicionales.clear();
		grafSel = null;  // Quitar gráfico seleccionado si lo hubiera
		recalculaGrafico();
	}
	
	/** Devuelve el objeto de gráfico sobre el que pintar, correspondiente al 
	 * panel principal de la ventana. Después de actualizar graphics hay que llamar a {@link #repaint()}
	 * si se quiere que se visualice en pantalla
	 * @return	Objeto gráfico principal de la ventana
	 */
	public Graphics2D getGraphicsPers() {
		return panelTablero.getGraphicsPers();
	}
	
	/** Recalcula el gráfico de la ventana partiendo de los objetos gráficos incluidos en la edición
	 * (no redibuja la ventana)
	 */
	public void recalculaGrafico() {
		recalculaGrafico(0.6f);
	}
	// transp = transparencia del gráfico seleccionado (si lo hay)
	private void recalculaGrafico( float transp ) {
		// Pinta el tablero
		tablero.dibuja();
		// Pinta todos los elementos de los dos paneles (excepto el seleccionado)
		for (ElementoDatausto g : listaElementosAdicionales) {
			if (g!=grafSel) {
				g.dibuja();
			}
		}
		// Dibuja el seleccionado
		if (grafSel!=null) {  // Dibuja el seleccionado en color invertido y semitransparente
			grafSel.dibuja( transp, true );
		}
		panelTablero.repaint();
	}
	
	//
	// Métodos de utilidad
	//
	
	// Dibuja una flecha en el panel indicado (x,y) a (x2,y2)
	public void dibujaFlecha( PanelGrafico panel, double x, double y, double x2, double y2, boolean persistente ) {
		panel.dibujaFlecha( Color.magenta, ElementoDatausto.STROKE_2, x, y, x2, y2, persistente );
	}
	
	
	//
	// EVENTOS - FUNCIONAMIENTO DEL EDITOR
	//

	// Lanzado cuando se hace un click en el editor
	private void mouseClick( MouseEvent e ) {
		Point p = e.getPoint();
		ElementoDatausto graf = (ElementoDatausto) cbFormas.getSelectedItem();  // Gráfico seleccionado para crear (si procede)
		if (estado==EstadoEditor.CREACION && graf!=null) {  // Estado de creación
			if (graf instanceof EnTablero) {  // Si es en el tablero se tiene que adaptar al tablero
				((EnTablero)graf).setTablero( tablero );  // Definir que qué tablero se parte
				boolean enTablero = tablero.estaEnTablero( p );
				if (enTablero) {
					int fila = tablero.getFilaDe( p );
					int col = tablero.getColumnaDe( p );
					p = tablero.getCentroDeCasilla( fila, col );
					EnTablero g = null;
					if (graf instanceof Hexagono) {
						g = (EnTablero) graf.crearNuevo( panelTablero, p, new Point( p.x, p.y + Datausto.RADIO_HEXAGONO ) );
					} else if (graf instanceof Imagen) {
						g = (EnTablero) graf.crearNuevo( panelTablero, p, new Point( p.x + Datausto.DIAM_IMAGEN, p.y + Datausto.DIAM_IMAGEN ) );
					}
					if (g==null) return;
					g.setTablero( tablero );
					if (!tablero.estanCasillasLibres( g.getCasillas() )) {
						JOptionPane.showMessageDialog( null, "Solo puede crearse un elemento en casillas libres", "Error en creación en casillas ocupadas", JOptionPane.ERROR_MESSAGE );
					} else {
						if (g instanceof Coloreable) {
							int colSel = cbColoresBorde.getSelectedIndex();
							int colRellSel = cbColoresRelleno.getSelectedIndex();
							if (colSel!=-1 && colRellSel!=-1) {
								((Coloreable)g).setColor( COLORES[colSel] );
								((Coloreable)g).setColorRelleno( COLORES_TRANSP[colRellSel] );
							}
						}
						tablero.addElemento( g );
						recalculaGrafico();
					}
				} else {
					JOptionPane.showMessageDialog( null, "No puede crearse este elemento fuera del tablero", "Error en creación", JOptionPane.ERROR_MESSAGE );
				}
			} else {
				ElementoDatausto g = graf.crearNuevo( panelTablero, p, null );  // Intentar crear con un solo punto
				if (g==null) {
					JOptionPane.showMessageDialog( null, "Hace falta click y arrastre para crear un gráfico de tipo " + graf, "Error en creación", JOptionPane.ERROR_MESSAGE );
				} else {
					listaElementosAdicionales.add( g );
					recalculaGrafico();
				}
			}
		} else if (estado==EstadoEditor.MOVIMIENTO) {  // Estado de movimiento
			
			
			// Nada
			
			
			
		} else {  // En cualquiera de los otros dos estados se intenta seleccionar un elemento
			seleccionaElemento( p );
			if (e.isAltDown()) {
				if (grafSel!=null) {  // Si se selecciona elemento con Alt pulsado, se borra
					boolean estaba = false;
					if (listaElementosAdicionales.contains(grafSel)) {
						listaElementosAdicionales.remove( grafSel );
						estaba = true;
					} else if (grafSel instanceof EnTablero) {
						estaba = tablero.removeElemento( (EnTablero) grafSel );
					}
					if (estaba) {
						grafSel = null;
						recalculaGrafico();
					}
				}
			}
		}
	}
	
		private void seleccionaElemento( Point p ) {
			grafSel = null;
			lMens.setText( "No hay objetos seleccionados." );
			for (int i=tablero.getListaElementos().size()-1; i>=0; i--) {
				ElementoDatausto ed = (ElementoDatausto) tablero.getListaElementos().get(i);
				if (ed.enPunto( p )) {
					grafSel = ed;
					lMens.setText( "Seleccionado " + grafSel );
					if (grafSel instanceof Rotable) rotInicial = ((Rotable)grafSel).getRotacion();
					break;
				}
			}
			if (grafSel==null)
				for (int i=listaElementosAdicionales.size()-1; i>=0; i--) {
					if (listaElementosAdicionales.get(i).enPunto( p )) {
						grafSel = listaElementosAdicionales.get(i);
						lMens.setText( "Seleccionado " + grafSel );
						if (grafSel instanceof Rotable) rotInicial = ((Rotable)grafSel).getRotacion();
						break;
					}
				}
			recalculaGrafico(); // Recalcula el pintado para considerar el objeto seleccionado/no seleccionado
		}
	
	// Lanzado cuando se está haciendo un drag en el editor (punto anterior y siguiente)
	private void mouseDrag( Point pIni, Point pAnt, Point pSig ) {
		if (estado==EstadoEditor.CREACION) {
			// Pinta la línea de referencia
			panelTablero.repintaAhora();
			dibujaFlecha( panelTablero, pIni.getX(), pIni.getY(), pSig.getX(), pSig.getY(), false );
		} else if (estado==EstadoEditor.MOVIMIENTO) {  // Estado de movimiento
			if (grafSel==null) {  // El primer drag selecciona el elemento
				seleccionaElemento( pIni );
				if (grafSel!=null) {
					xInicial = grafSel.getX();
					yInicial = grafSel.getY();
				}
			}
			if (grafSel!=null) {
				double difX = pSig.getX()-pAnt.getX();
				double difY = pSig.getY()-pAnt.getY();
				grafSel.inc( difX, difY );
				recalculaGrafico();
			}
		} else if (estado==EstadoEditor.ROTACION) {
			if (grafSel!=null && grafSel instanceof Rotable) {
				Rotable r = (Rotable) grafSel;
				double nuevaRot = rotInicial + Geometria.getAngulo( pIni, pSig );
				r.setRotacion( nuevaRot );
				recalculaGrafico();
				dibujaFlecha( panelTablero, pIni.getX(), pIni.getY(), pSig.getX(), pSig.getY(), true );
			}
		}
	}

	// Lanzado cuando se acaba el drag en el editor (punto inicial, anterior y último)
	private void mouseDragEnd( PanelGrafico panelEvento, Point pIni, Point pAnt, Point pUlt ) {
		ElementoDatausto graf = (ElementoDatausto) cbFormas.getSelectedItem();  // Gráfico seleccionado para crear (si procede)
		if (estado==EstadoEditor.CREACION && graf!=null) {
			ElementoDatausto g = graf.crearNuevo( panelEvento, pIni, pUlt );  // Intentar crear con dos puntos
			if (g==null) {
				JOptionPane.showMessageDialog( null, "Error en creación de gráfico de tipo " + graf, "Error en creación", JOptionPane.ERROR_MESSAGE );
			} else {
				if (panelEvento==panelTablero && graf instanceof EnTablero) {  // Si es en el tablero se tiene que adaptar al tablero
					((EnTablero)graf).setTablero( tablero );  // Definir que qué tablero se parte
					Casilla c1 = tablero.getCasillaDe( pIni );
					Casilla c2 = tablero.getCasillaDe( pUlt );
					if (c1!=null && c2!=null) {
						Point p1 = tablero.getCentroDeCasilla( c1 );
						Point p2 = tablero.getCentroDeCasilla( c2 );
						g = null;
						EnTablero et = (EnTablero) graf.crearNuevo( panelTablero, p1, p2 );
						if (!tablero.estanCasillasLibres( et.getCasillas() )) {
							JOptionPane.showMessageDialog( null, "Solo puede crearse un elemento en casillas libres", "Error en creación en casillas ocupadas", JOptionPane.ERROR_MESSAGE );
							recalculaGrafico();
						} else {
							g = (ElementoDatausto) et;
							tablero.addElemento( et );
							// System.out.println( et + " - " + et.getCasillas() );
							// System.out.println( tablero );
						}
					} else {
						JOptionPane.showMessageDialog( null, "No puede crearse este elemento fuera del tablero", "Error en creación", JOptionPane.ERROR_MESSAGE );
					}
				} else {
					listaElementosAdicionales.add( g );
				}
				if (g!=null) {
					if (g instanceof Coloreable) {
						int colSel = cbColoresBorde.getSelectedIndex();
						int colRellSel = cbColoresRelleno.getSelectedIndex();
						if (colSel!=-1 && colRellSel!=-1) {
							((Coloreable)g).setColor( COLORES[colSel] );
							((Coloreable)g).setColorRelleno( COLORES_TRANSP[colRellSel] );
						}
					}
					recalculaGrafico();
				}
			}
		} else if (estado==EstadoEditor.MOVIMIENTO) {  // Estado de movimiento
			if (grafSel instanceof EnTablero) {  // Si es de tablero, comprobar que el lugar de movimiento es viable
				// Ver si movida cabe en el tablero
				ArrayList<Casilla> lCasillas = ((EnTablero)grafSel).getCasillas();
				tablero.removeElemento( (EnTablero)grafSel );  // Quitamos el elemento en la posición antigua
				if (tablero.estanCasillasLibres(lCasillas)) {  // Cabe al hacer el movimiento! Completarlo en centro de casilla
					Point pExacta = tablero.getCentroDeCasilla( lCasillas.get(0) );
					grafSel.setX( pExacta.getX() );
					grafSel.setY( pExacta.getY() );
					boolean ok = tablero.addElemento( (EnTablero)grafSel );
					if (!ok) {  // Por alguna razón no puede moverse: restaurar
						grafSel.setX( xInicial );
						grafSel.setY( yInicial );
						tablero.addElemento( (EnTablero)grafSel );
					}
				} else {  // No cabe: restaurar
					grafSel.setX( xInicial );
					grafSel.setY( yInicial );
					tablero.addElemento( (EnTablero)grafSel );
				}
				
			}
			grafSel = null;
			recalculaGrafico(); 
		} else if (estado==EstadoEditor.ROTACION) {  // Estado de rotación
			if (grafSel!=null && grafSel instanceof Rotable) {
				if (grafSel instanceof EnTablero) {
					double rot = ((Rotable)grafSel).getRotacion();
					if (Datausto.anguloGiroPosible( rot )) {  // Rotación válida
						rot = Datausto.anguloGiroExacto( rot );
						((Rotable)grafSel).setRotacion( rot );  // Fija la rotación exacta
						// Ver si rotada cabe en el tablero:
						ArrayList<Casilla> lCasillas = ((EnTablero)grafSel).getCasillas();
						((Rotable)grafSel).setRotacion(rotInicial);  // Ponerlo como estaba y quitar del tablero
						tablero.removeElemento( (EnTablero)grafSel );
						if (tablero.estanCasillasLibres(lCasillas)) {  // Cabe al hacer la rotación! Completarla
							((Rotable)grafSel).setRotacion(rot);
							tablero.addElemento( (EnTablero)grafSel );
						} else {  // No cabe: restaurar
							((Rotable)grafSel).setRotacion(rotInicial);
							tablero.addElemento( (EnTablero)grafSel );
						}
						recalculaGrafico();
					} else {  // Rotación no válida
						((Rotable)grafSel).setRotacion( rotInicial );  // Devuelve la rotación original
						recalculaGrafico();
					}
				} else {
					Rotable r = (Rotable) grafSel;
					rotInicial = r.getRotacion();
					recalculaGrafico();
				}
			}
		}
	}
	
	private void cargarFichero() {
		File carpetaFics = new File( Datausto.CARPETA_FICHEROS );
		File[] ficheros = carpetaFics.listFiles();
		ArrayList<String> nomsFichs = new ArrayList<>();
		for (int i=0; i<ficheros.length; i++) if (ficheros[i].getName().endsWith(Datausto.EXTENSION_FICHERO)) nomsFichs.add( ficheros[i].getName() );
		String nomFic = (String) JOptionPane.showInputDialog( null, "Introduce fichero a cargar:", "Selección de fichero", JOptionPane.QUESTION_MESSAGE, null, nomsFichs.toArray( new String[nomsFichs.size()] ), null );
		if (nomFic==null) return;
		cargarFichero( carpetaFics, nomFic );
	}
	private void cargarFichero( File carpetaFics, String nomFic ) {
		try {
			listaElementosAdicionales.clear();
			tablero.clearAll();
			grafSel = null;  // Quitar gráfico seleccionado si lo hubiera
			Scanner scanner = new Scanner( new File( carpetaFics, nomFic ) );
			String lineaFilas = scanner.nextLine();
			int filas = Integer.parseInt( lineaFilas );
			String lineaCols = scanner.nextLine();
			int cols = Integer.parseInt( lineaCols );
			tablero.reiniciaTablero( filas, cols );
			while (scanner.hasNextLine()) {
				String linea = scanner.nextLine();
				ElementoDatausto nuevoElemento = null;
				for (ElementoDatausto elemento : ElementoDatausto.LISTA_ELEMENTOS_VISUALES) {
					nuevoElemento = elemento.crearNuevo( panelTablero, linea );
					if (nuevoElemento!=null) break;
				}
				if (nuevoElemento!=null && nuevoElemento instanceof EnTablero) {
					tablero.addElemento( (EnTablero) nuevoElemento );
				} else {
					JOptionPane.showMessageDialog( null, "Línea incorrecta: " + linea, "Error de lectura", JOptionPane.ERROR_MESSAGE );
				}
			}
			scanner.close();
			recalculaGrafico();
			ficheroCargado = nomFic;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void salvarFichero() {
		String nomFic = JOptionPane.showInputDialog( null, "Introduce nombre de fichero a guardar:", "Selección de fichero", JOptionPane.QUESTION_MESSAGE );
		if (nomFic==null) return;
		try {
			PrintStream ps = new PrintStream( Datausto.CARPETA_FICHEROS + nomFic + Datausto.EXTENSION_FICHERO );
			ps.println( tablero.getNumFilas() );
			ps.println( tablero.getNumColumnas() );
			// Líneas por cada gráfico
			for (EnTablero elemento : tablero.getListaElementos()) {
				ps.println( ((ElementoDatausto) elemento).toLinea() );
			}
			ps.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void nuevoGrafico() {
		tablero.reiniciaTablero( panelTablero.getSize() );
		listaElementosAdicionales.clear();
		grafSel = null;  // Quitar gráfico seleccionado si lo hubiera
		recalculaGrafico();
	}
	
	private String ficheroCargado = null;
	
	private void cargaConfig() {
		try {
			Scanner scanner = new Scanner( new File( Datausto.CARPETA_FICHEROS + "config.txt") );
			String linea = scanner.nextLine(); // anchura y altura
			String linea2 = scanner.nextLine();
			ventana.setSize( Integer.parseInt(linea), Integer.parseInt(linea2) );
			linea = scanner.nextLine(); // x e y
			linea2 = scanner.nextLine();
			ventana.setLocation( Integer.parseInt(linea), Integer.parseInt(linea2) );
			linea = scanner.nextLine(); // color sel
			cbColoresBorde.setSelectedItem( linea );
			linea = scanner.nextLine(); // color relleno sel
			cbColoresRelleno.setSelectedItem( linea );
			linea = scanner.nextLine(); // forma sel
			cbFormas.setSelectedIndex( Integer.parseInt(linea) );
			linea = scanner.nextLine(); // fichero cargado
			if (!linea.equals("null")) {
				cargarFichero( new File( Datausto.CARPETA_FICHEROS ), linea );
			}
			scanner.close();
		} catch (Exception e) {
			// Nada que hacer (no hay fichero de configuración o hay algún error en él)
		}
	}
	
	private void guardaConfig() {
		// guardar en un fichero de configuración la información del tamaño de la ventana, la posición de la ventana en pantalla, el color actual, el color de relleno actual, la forma a crear seleccionada, y la el último fichero que se ha cargado (si ha habido alguno), Dale al fichero el nombre que desees y utiliza ficheros de texto o binarios, como prefieras.	
		try {
			PrintStream ps = new PrintStream( Datausto.CARPETA_FICHEROS + "config.txt" );
			ps.println( ventana.getWidth() );
			ps.println( ventana.getHeight() );
			ps.println( ventana.getX() );
			ps.println( ventana.getY() );
			ps.println( cbColoresBorde.getSelectedItem() );
			ps.println( cbColoresRelleno.getSelectedItem() );
			ps.println( cbFormas.getSelectedIndex() );
			ps.println( ficheroCargado );
			ps.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en creación de fichero config.txt", "Error de configuración", JOptionPane.ERROR_MESSAGE );
		}
	}
	
}



