import java.awt.*;
import javax.swing.*;
/**
 * 
 * @author Danel
 *
 */
public class VentanaCalculosNumericos extends JFrame{

	private static final long serialVersionUID = 1L;
	private static final String TITULO = "Calculador de numeros con combinaciones de operaciones";
	private static final int ALTURA = 500;
	private static final int ANCHURA = 550;
	private JComboBox<String> cbxListaProblemas = new JComboBox<String>( new String[] { "Cuatro cuatros", "Cinco cincos" } );
	private JButton bGuardarProblemaActual = new JButton( "Guardar problema actual");
	private JTextField tfNumeros = new JTextField( 15 );
	private JTextField tfRepeticiones = new JTextField( 2 );
	private JCheckBox cbRepeticionesExactas = new JCheckBox( "exactos" );
	private JCheckBox cbOperadorMas = new JCheckBox( "+" );
	private JCheckBox cbOperadorMenos = new JCheckBox( "-" );
	private JCheckBox cbOperadorPor = new JCheckBox( "*" );
	private JCheckBox cbOperadorDivision = new JCheckBox( "/" );
	private JTextField tfDesde = new JTextField( 4 );
	private JTextField tfHasta = new JTextField( 4 );
	private JCheckBox cbSoloEnteros = new JCheckBox( "Sólo enteros" );
	private JCheckBox cbMostrarSoloUnaOperacion = new JCheckBox("Mostrar sólo una op de cada resultado");
	private JButton bCalcular = new JButton( "Calcular");
	private JTable tCalculos = new JTable();
	private JTextField tfCombinaciones = new JTextField( 5 );
	private JTextField tfValorMinimo = new JTextField( 4 );
	private JTextField tfValorMaximo = new JTextField( 4 );
	private JLabel lMensaje = new JLabel( "" );
	
	private JPanel pSuperior = new JPanel();
	private JPanel pSup1 = new JPanel();
	private JPanel pSup2 = new JPanel();
	private JPanel pBox1 = new JPanel();
	private JPanel pBox2 = new JPanel();
	private JPanel pBox2a = new JPanel();
	private JPanel pBox2aUp = new JPanel();
	private JPanel pBox2aBot = new JPanel();
	private JPanel pBox2b = new JPanel();
	private JPanel pSup3 = new JPanel();
	private JPanel pMedio = new JPanel();
	private JPanel pInferior = new JPanel(new GridLayout(2, 1));
	private JPanel pInf1 = new JPanel();
	private JPanel pInf2 = new JPanel();
	/**
	 * Constructor de la ventana
	 */
	public VentanaCalculosNumericos() {
		setTitle(TITULO);
		setSize(ANCHURA, ALTURA);
		setMinimumSize(new Dimension(ANCHURA, ALTURA));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//Colocacion de paneles
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(pSuperior,BorderLayout.NORTH);
		pSuperior.setLayout(new BoxLayout(pSuperior, BoxLayout.Y_AXIS));
		pSuperior.add(pSup1);
		pSuperior.add(pSup2);
		pSup2.setLayout(new BoxLayout(pSup2, BoxLayout.Y_AXIS));
		pSup2.add(pBox1);
		pSup2.add(pBox2);
		pBox2.setLayout(new BoxLayout(pBox2, BoxLayout.X_AXIS));
		pBox2.add(pBox2a);
		pBox2a.add(pBox2aUp);
		pBox2a.add(pBox2aBot);
		pBox2.add(pBox2b);
		pBox2a.setLayout(new BoxLayout(pBox2a, BoxLayout.Y_AXIS));
		pBox2b.setLayout(new BoxLayout(pBox2b, BoxLayout.Y_AXIS));
		pSuperior.add(pSup3);
		getContentPane().add(pMedio,BorderLayout.CENTER);
		getContentPane().add(pInferior,BorderLayout.SOUTH);
		pInferior.add(pInf1);
		pInferior.add(pInf2);
		
		//Anyadir los componentes a los paneles
		addToContainer(pSup1, new JLabel("Cargar Problemas:"),cbxListaProblemas);
		pSup1.add(bGuardarProblemaActual,BorderLayout.EAST);
		
		addToContainer(pBox1, new JLabel("Numeros:"),tfNumeros,new JLabel("¿Cuantas repeticiones?:"),tfRepeticiones,cbRepeticionesExactas);;
		
		addToContainer(pBox2aUp, new JLabel("Operadores:"), cbOperadorMas,cbOperadorMenos,cbOperadorPor,cbOperadorDivision);
		addToContainer(pBox2aBot,new JLabel("Objetivos:"),new JLabel("Desde"),tfDesde,new JLabel("Hasta"),tfHasta);
		
		addToContainer(pBox2b,cbSoloEnteros,cbMostrarSoloUnaOperacion);
		
		pSup3.add(bCalcular);
		
		pMedio.add(tCalculos);
		
		addToContainer(pInf1, new JLabel("Total Combinaciones:"),tfCombinaciones, new JLabel("Valor minimo"),tfValorMinimo,new JLabel("Valor maximo"),tfValorMaximo);
		pInf2.add(lMensaje);
		
	}
	/**
	 * Anyade los componentes al contenedor
	 * @param c
	 * @param comps
	 */
	private void addToContainer(Container c, Component ...comps) {
		 for (Component cp:comps) c.add(cp);
	}
	/** Metodo principal de prueba
	* @param args No utilizados
	*/
	public static void main(String[] args) {
		VentanaCalculosNumericos v = new VentanaCalculosNumericos();
		v.setVisible(true);
	}
}
