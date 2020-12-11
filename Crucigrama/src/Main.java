import java.awt.*;
import java.io.*;
import java.util.*;
import cruc.datos.*;
import cruc.visuales.VentanaGrafica;
/**
 * 
 * @author Eneko, Jon Ander y Danel
 *
 */
public class Main {
	private static boolean funcionando = true;
	private static final int FPS = 50;
	private static final int TMP_ESPERA = 1000/FPS;
	private static final int NUM_CRUCIGRAMAS = 5;
	private static final VentanaGrafica VENTANA = new VentanaGrafica(1200, 700, "CRUCIGRAMAS G7");
	private static final int ANCHURA_CRUC_MAX=VENTANA.getAnchura()/2;
	private static final int ALTURA_CRUC_MAX=VENTANA.getAltura()-50;
	//private static ArrayList<Crucigrama> listaCrucigramas = new ArrayList<Crucigrama>();//SOLO USADO EN EL DESARROLLO
	private static HashMap<Integer, Character>codTeclas = new HashMap<Integer,Character>();
	private static final String FICHERO_COD_TECLAS = "codTeclasToChar.dat";
	private static final double X_GANAR=50;
	private static final double Y_GANAR=VENTANA.getAltura()/1.75;
	private static final String MENS_GANAR="HAS GANADO";
	private static final int TMP_GANAR = 5000;
	private static final Font FUENTE_GANAR =  new Font("Arial", 1, 160);
	private static final Color COLOR_GANAR = Color.GREEN;
	private static final double X_COPY= 30;
	private static final double Y_COPY = VENTANA.getAltura() - 30;
	private static final String MENS_COPY= "HECHO POR ENEKO EGUIGUREN, JON ANDER DE LA PUEBLA Y DANEL ARIAS"; 
	private static final Font FUENTE_COPY =  new Font("Arial", 1, 13);
	private static final Color COLOR_COPY = Color.BLUE;
	
	
	public static void main(String[] args) {
		/*
		//USADO EN EL DESARROLLO
		initCodTeclas();
		guardaCodTeclas(FICHERO_COD_TECLAS);
		initCrucigramas();
		guardarCrucigramasEnFicheros();
		*/
		
		//Elegimos un crucigrama aleatorio
		Random r = new Random();
		int numCrucElegido=r.nextInt(NUM_CRUCIGRAMAS)+1;
		
		//Crucigrama elegido = listaCrucigramas.get(numCrucElegido-1); //SOLO USO EN EL DESARROLLO
		
		//Carga el objeto crucigrama cargando el fichero corespondiente
		Crucigrama elegido = cargaCrucigrama(numCrucElegido); 
		
		//carga desde el fichero correspondiente el hashMap que relaciona los codigos de las teclas con los chars
		cargarCodTeclas();
		
		//Coloca en pantalla el crucigrama
		elegido.colocarEnPantalla(ALTURA_CRUC_MAX,ANCHURA_CRUC_MAX);
		
		//Mensaje que da informcion al usuario del crucigrama al que esta jugando
		VENTANA.setMensaje("Estas jugando al crucigrama: "+numCrucElegido+"/"+NUM_CRUCIGRAMAS+" de física");
		
		VENTANA.setDibujadoInmediato(false);
		
		Casilla pulsadaAnt=null; //Incializa el valor de casilla pulsada a null
		
		//Bucle que se ejecuta tantas veces como indique FPS
		while (funcionando) {
			VENTANA.borra();//borra lo dibuja anteriormente en la pantalla
			
			elegido.dibujar(VENTANA);//Dibuja el crucigrama
			
			//Comprobar pulsado
			Point puls = VENTANA.getRatonPulsado();
			if (puls!=null) {
				Casilla pulsada = elegido.getCasillaDentro(puls);
				if (pulsada!=null) {
					//Si la casilla no es nula la guarda en pulsadaAnt
					pulsadaAnt = elegido.getCasillaDentro(puls);
				}
			}
			//comprobar la tecla pulsada
			int codigoTecla = VENTANA.getCodUltimaTeclaTecleada();
			if (codigoTecla!=0) {
				//Si el codigo de la tecla esta referenciado en el hashmap busca su char correspondiente y se lo establece cmo letra actual a la casilla pulsada
				if (codTeclas.containsKey(codigoTecla)) {
					if (pulsadaAnt!=null) {
						pulsadaAnt.setLetraActual(codTeclas.get(codigoTecla));
						pulsadaAnt=null;//Despues de haber establecido la letra se reestablece el valor de la casilla pulsada
				
					}
				}
			}
			
			//Dibuja mensajito sobre los creadores del crucigrama
			VENTANA.dibujaTexto(X_COPY, Y_COPY, MENS_COPY, FUENTE_COPY, COLOR_COPY);
			
			VENTANA.repaint();
			
			VENTANA.espera(TMP_ESPERA);
			//Si la entana esta cerrada sale del bucle
			if (VENTANA.estaCerrada()) {
				break;
			}
			//Comprobar si se ha ganado y poner pantalla HAS GANADO
			else if (elegido.esCorrecto()) {
				VENTANA.borra();
				VENTANA.dibujaTexto(X_GANAR, Y_GANAR, MENS_GANAR ,FUENTE_GANAR, COLOR_GANAR);
				VENTANA.repaint();
				VENTANA.espera(TMP_GANAR);
				break;
			}
		}
		
	}
	/**
	 * Carga desde su correspondiente fichero el crucigrama indicado por el numero
	 * @param numCrucigrama
	 * @return objeto crucigrama
	 */
	private static Crucigrama cargaCrucigrama(int numCrucigrama) {
		Crucigrama cruc = null;
		String nombre="crucigrama";
		String extension=".dat";
		String nombreFichero=nombre+numCrucigrama+extension;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreFichero));
			cruc = (Crucigrama) ois.readObject();
			ois.close();
		} catch(IOException | ClassNotFoundException e) {
			System.err.println("Error al cargar el fichero" + nombreFichero);
			e.printStackTrace();
		}
		return cruc;
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Carga desde su fichero el hashmap que relaciona los codigos de las teclas pulsadas con el char correspondiente 
	 */
	private static void cargarCodTeclas() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHERO_COD_TECLAS));
			codTeclas = (HashMap<Integer,Character>) ois.readObject();
			ois.close();
		} catch(IOException | ClassNotFoundException e) {
			System.err.println("Error al cargar el fichero" + FICHERO_COD_TECLAS);
			e.printStackTrace();
		}
	}
	/*
	//AQUI ABAJO SE ENCUENTRAN METODOS QUE SE HAN USADO EN ALGUN MOMENTO DURANTE EL DESARROLLO
	
	static void initCodTeclas() {
		codTeclas.put(81, 'Q'); codTeclas.put(87, 'W'); codTeclas.put(69, 'E'); codTeclas.put(82, 'R');
		codTeclas.put(84, 'T'); codTeclas.put(89, 'Y'); codTeclas.put(85, 'U'); codTeclas.put(73, 'I');
		codTeclas.put(79, 'O'); codTeclas.put(80, 'P'); codTeclas.put(65, 'A'); codTeclas.put(83, 'S');
		codTeclas.put(68, 'D'); codTeclas.put(70, 'F'); codTeclas.put(71, 'G'); codTeclas.put(72, 'H');
		codTeclas.put(74, 'J'); codTeclas.put(75, 'K'); codTeclas.put(76, 'L'); codTeclas.put(90, 'Z');
		codTeclas.put(88, 'X'); codTeclas.put(67, 'C'); codTeclas.put(86, 'V'); codTeclas.put(66, 'B');
		codTeclas.put(78, 'N'); codTeclas.put(77, 'M'); codTeclas.put(8, '-'); codTeclas.put(127, '-');
	}
	
	private static void guardaCodTeclas(String nombreFichero) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreFichero));
			oos.writeObject(codTeclas);
			oos.close();
		} catch(IOException e) {
			System.err.println("Error al guardar en el fichero" + nombreFichero);
			e.printStackTrace();
		}
	}

	static void initCrucigramas() {
	
		char [][] cru1 = {{'-','-','N','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
						  {'-','-','U','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
			              {'M','U','C','H','O','S','-','-','-','-','-','-','-','-','-','-','-','S','-'},
			              {'-','-','L','-','-','-','-','-','-','-','-','-','-','-','-','-','-','A','-'},
			              {'-','-','E','-','-','-','-','-','-','-','-','-','-','-','-','-','-','L','-'},
			              {'-','-','O','R','B','I','T','A','S','-','-','-','-','-','-','-','-','I','-'},
			              {'-','-','-','-','-','-','E','-','-','-','-','-','-','C','U','A','T','R','O'},
			              {'-','-','-','-','-','-','M','-','-','-','-','-','-','-','L','-','-','-','C'},
			              {'-','-','-','-','-','-','P','-','-','-','N','-','-','-','T','-','-','-','H'},
			              {'P','R','O','T','O','N','E','S','-','S','I','L','I','C','I','O','-','-','O'},
			              {'-','-','-','-','-','-','R','-','-','-','V','-','-','-','M','-','-','-','-'},
			              {'-','-','-','-','-','-','A','-','-','-','E','L','E','V','A','D','A','-','-'},
			              {'C','O','N','D','U','C','T','O','R','-','L','-','-','-','-','-','-','-','-'},
			              {'-','-','-','-','-','-','U','-','-','-','E','S','T','A','B','L','E','-','-'},
			              {'-','E','L','E','C','T','R','O','N','E','S','-','-','-','-','U','-','-','-'},
			              {'-','-','I','-','-','-','A','-','-','-','-','-','-','-','-','Z','-','-','-'},
			              {'-','-','B','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
			              {'-','-','R','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
			              {'-','-','E','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'}};
	
	    char[][] cru2= {{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','C','-','-','-','-','-','-','-','-','-','-'},
		 		        {'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','M','A','C','R','O','S','C','O','P','I','C','A'},
		 		        {'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','V','-','M','-','-','-','-','-','-','-','-','-','-'},
		  		        {'-','-','-','-','-','-','-','-','-','-','-','-','N','-','-','E','-','P','-','-','-','-','-','-','-','-','-','-'},
	                    {'-','-','-','-','-','-','-','-','H','-','P','L','A','S','T','I','C','O','-','-','-','-','-','-','-','-','-','-'},
	                    {'-','-','-','-','-','-','-','-','U','-','-','-','D','-','-','N','-','-','-','-','-','-','-','-','-','-','-','-'},
	                    {'D','O','S','-','-','-','E','N','E','R','G','I','A','-','-','T','-','-','-','-','-','-','-','-','-','-','-','-'},
	                    {'-','R','-','-','M','-','-','-','C','-','-','-','-','-','-','I','-','-','-','-','-','-','-','-','-','-','-','-'},
	                    {'C','O','N','D','U','C','C','I','O','N','-','D','I','R','E','C','C','I','O','N','-','-','-','-','-','-','-','-'},
	                    {'-','-','-','-','C','-','-','-','-','-','C','-','-','-','-','U','-','-','-','-','-','-','-','-','-','-','-','-'},
	                    {'-','-','-','-','H','-','-','-','S','-','U','-','-','-','-','A','-','-','-','-','-','-','-','-','-','-','-','-'},
	                    {'-','-','P','R','O','H','I','B','I','D','A','-','-','-','-','T','-','-','-','-','-','-','-','-','-','-','-','-'},
	                    {'-','-','-','-','-','-','-','-','-','-','R','-','M','A','C','R','O','S','C','O','P','I','C','A','-','-','-','-'},
	                    {'-','-','-','-','-','-','-','-','-','-','Z','-','-','-','-','O','-','-','-','-','-','-','-','-','-','-','-','-'},
	                    {'-','-','-','-','-','-','-','-','-','C','O','B','R','E','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},};
	 
		char[][] cru3= {{'-','L','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
				 		{'-','I','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
				 		{'-','B','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
				  		{'T','E','M','P','E','R','A','T','U','R','A','-','-','-','-','-'},
				  		{'-','R','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
				  		{'-','T','-','-','-','R','-','-','-','S','-','-','E','-','-','-'},
				  		{'G','A','R','A','J','E','-','-','-','U','-','-','N','-','-','-'},
				  		{'-','D','-','-','-','C','-','-','-','M','-','-','L','-','-','-'},
				  		{'-','-','-','-','-','O','-','-','-','A','-','-','A','-','-','-'},
				  		{'-','-','-','S','E','M','I','C','O','N','D','U','C','T','O','R'},
				  		{'-','-','-','-','-','B','-','-','-','-','-','-','E','-','-','-'},
				  		{'-','-','-','-','S','I','-','-','-','-','-','-','S','-','-','-'},
				  		{'-','-','-','-','-','N','-','P','-','-','-','-','-','A','-','-'},
				  		{'-','-','-','-','V','A','R','I','A','-','H','-','-','M','A','S'},
				 		{'-','-','-','-','-','-','-','L','-','-','U','-','-','B','-','-'},
				 		{'-','-','-','-','-','-','-','A','M','P','E','R','I','O','S','-'},
				 		{'-','-','-','-','-','-','-','-','-','-','C','-','-','S','-','-'},
				 		{'-','-','-','-','-','-','-','-','-','N','O','-','-','-','-','-'}};
		 
	    char[][] cru4 = {{'D','O','P','A','M','O','S','-','P','-','-','-','-','-','-'},
						 {'-','-','-','-','-','-','E','N','E','-','-','-','-','-','-'},
						 {'-','-','-','-','-','-','M','-','-','H','U','E','C','O','S'},
						 {'-','-','-','-','-','-','I','-','-','-','-','L','-','-','-'},
						 {'-','-','-','-','-','-','C','-','-','-','-','E','-','-','-'},
						 {'-','-','-','B','O','R','O','-','H','U','E','C','O','S','-'},
						 {'-','-','-','-','-','-','N','-','-','-','-','T','-','-','-'},
						 {'-','-','-','-','-','-','D','-','-','-','-','R','-','-','-'},
						 {'-','-','-','-','-','-','U','-','-','-','-','O','-','-','-'},
						 {'-','-','-','-','-','-','C','-','S','-','-','N','-','-','-'},
						 {'-','-','-','-','E','X','T','R','I','N','S','E','C','O','S'},
						 {'-','-','-','-','-','-','O','-','-','-','-','S','-','C','-'},
						 {'-','-','-','-','-','-','R','-','-','-','-','-','-','H','-'},
						 {'-','-','-','-','-','-','-','-','D','O','P','A','D','O','-'}};
	
		char[][] cru5 = {{'-','-','-','-','-','-','-','-','-','-','-','-','M','E','N','O','S','-','-','-','-'},
						 {'-','-','-','-','-','-','-','-','-','-','-','-','-','N','-','-','-','-','-','-','-'},
						 {'-','-','-','-','-','-','-','-','-','-','-','-','E','L','E','C','T','R','I','C','O'},
						 {'-','-','-','-','-','-','-','-','-','-','-','-','-','A','-','O','-','-','-','-','-'},
						 {'-','-','-','-','-','-','-','-','-','-','-','-','-','C','-','N','-','-','-','-','-'},
						 {'-','-','-','-','-','-','-','-','-','-','-','-','-','E','-','T','-','-','-','-','-'},
						 {'-','-','-','-','-','-','-','-','-','-','-','P','-','-','-','R','-','-','-','-','-'},
						 {'-','-','-','-','-','-','-','H','U','E','C','O','S','-','-','A','-','-','-','-','-'},
						 {'-','-','-','-','-','-','-','-','-','-','-','S','-','P','O','R','T','A','D','O','R'},
						 {'-','-','-','-','-','-','-','-','-','-','-','I','-','-','-','I','-','-','-','-','-'},
						 {'F','-','-','-','-','-','G','E','O','M','E','T','R','I','C','O','S','-','-','-','-'},
						 {'A','-','-','-','-','S','-','L','-','-','-','I','-','-','O','-','-','-','-','-','-'},
						 {'R','-','-','-','-','E','-','E','-','-','-','V','-','-','V','-','-','-','-','-','-'},
						 {'A','-','-','-','-','N','-','C','-','-','-','A','-','-','A','-','-','-','-','-','-'},
						 {'D','-','-','M','-','T','-','T','-','-','C','-','S','-','L','-','-','-','-','-','-'},
						 {'I','N','T','E','R','I','O','R','-','-','U','-','U','-','E','-','-','-','-','-','-'},
						 {'O','-','-','N','-','D','-','O','-','-','A','-','M','-','N','-','-','-','-','-','-'},
						 {'-','-','-','O','-','O','-','N','-','-','T','-','A','-','T','-','-','-','-','-','-'},
						 {'-','-','-','S','-','-','-','E','X','T','R','I','N','S','E','C','A','-','-','-','-'},
						 {'-','-','-','-','-','-','-','S','-','-','O','-','-','-','-','-','-','-','-','-','-'}};
	
		ArrayList<Pregunta>pregs1= new ArrayList<Pregunta>();
		
		pregs1.add(new Pregunta (true, 2, "¿Qué tres partículas básicas tiene un átomo?  Electrones,neutrones y..."));
		pregs1.add(new Pregunta (false, 3, "¿Qué dos partes tiene un átomo? La orbita y el..."));
		pregs1.add(new Pregunta (true, 4, "¿Qué partícula está en cada parte? Los neutrones y protones en el núcleo y los electrones en las..."));
		pregs1.add(new Pregunta (false, 5,"¿Cómo se ordenan los electrones? " ));
		pregs1.add(new Pregunta (false, 6, "La capa de valencia es la ............ capa donde se ubican los electrones."));
		pregs1.add(new Pregunta (true, 7,  "¿Qué es una partícula cargada? Es una particula que tiene exceso de electones o un defecto de ...." ));
		pregs1.add(new Pregunta (false, 8,  "¿Qué les pueden pasar a los electrones de valencia de un conducto en la órbita?" ));
		pregs1.add(new Pregunta (false, 9, "¿Que tipo de electron es aquel que sale de su órbita de valencia?" ));
		pregs1.add(new Pregunta (false, 10, "¿Qué energía puede liberar a un electrón de valencia? por ejemplo, la temperatura o la..." ));
		pregs1.add(new Pregunta (false, 11, "¿Qué energía puede hacer que los átomos queden libres? por ejemplo, la luz o la..." ));
		pregs1.add(new Pregunta (true, 12, "¿Cuántos electrones de valencia debe tener un aislante?" ));
		pregs1.add(new Pregunta (true, 13,  "Al liberar un electrón de valencia de un aislante, pasa a ser libre per tiene una cantidad de energía muy ..."));
		pregs1.add(new Pregunta (true, 14, "¿El aire es conductor o aislante?"));
		pregs1.add(new Pregunta (false, 15, "¿Cuántos electrones suele tener un aislante en la órbita de valencia?"));
		pregs1.add(new Pregunta (true, 16, "¿Cuántos electrones de valencia tienen el silicio o el germanio?"));
		pregs1.add(new Pregunta (true, 17, "¿Que cristal es estable gracias al equilibrio de fuerzas creado con la unión de los átomos?"));
		pregs1.add(new Pregunta (true, 18, "¿Por qué “busca” el silicio tener 8 electrones de valencia? Para ser más... "));
	    
		ArrayList<Pregunta>pregs2= new ArrayList<Pregunta>();
	
		pregs2.add(new Pregunta (true, 19, "Para que para que un semiconductor se haga más conductor hay que aplicarle algún tipo de ..."));
		pregs2.add(new Pregunta (false, 20, "Un par electrón-hueco es el formado por un electrón que sale de su órbita de valencia y el ... que deja al salir"));
		pregs2.add(new Pregunta (true, 21, "La banda prohibida de un conductor/semiconductor/aislante es energía mínima necesaria para excitar un electrón desde su estado ligado a un estado libre que le permita participar en la..." ));
		pregs2.add(new Pregunta (false, 22, "¿Cuánto ocupa la banda prohibida en un conductor?"));
		pregs2.add(new Pregunta (false, 23, "¿Y en un aislante?"));
		pregs2.add(new Pregunta (true, 24, "No hay electrones en la banda..."));
		pregs2.add(new Pregunta (false, 25, "¿Cual es la resistividad que tiene 2,2x10-8?"));
		pregs2.add(new Pregunta (true , 26, "¿Cual es la resistividad que tiene 10^13?"));
		pregs2.add(new Pregunta (false , 27, "¿Cual es la resistividad mas alta, la del cuarzo o la del vidrio?"));
		pregs2.add(new Pregunta (true , 28, "¿Cual es la resistividad mas baja, la del wolframio o la del cobre?"));
		pregs2.add(new Pregunta(false,29,"¿Cuál es el rango o fondo de escala de la resistividad? 10 elevado a ..." ));
		pregs2.add(new Pregunta(true,30,"La corriente eléctrica es el movimiento ordenado de electrones con una ... y un sentido" ));
		pregs2.add(new Pregunta(false,31,"Hay corriente eléctrica en un conductor/semiconductor/aislante?"));
		pregs2.add(new Pregunta(true,32,"La diferencia entre la corriente eléctrica microscópica y macroscópica es que para que se dé la ... debe existir un campo eléctrico."));
		pregs2.add(new Pregunta(true,33,"¿Qué corriente se busca?"));
		pregs2.add(new Pregunta(false,34,"¿Cómo se consigue corriente eléctrica en un conductor? Sometiendolo a un ........ eléctrico." ));
		pregs2.add(new Pregunta(true,35,"EJERCICIO: calcular la Resistencia de un cable de cobre de 1 m de largo y 1mm2 de sección. RESPUESTA: 0.0... ohmnios (rellena con el nombre de una cifra)" ));
		
		ArrayList<Pregunta>pregs3= new ArrayList<Pregunta>();
		
		pregs3.add(new Pregunta(true,36,"Utilizando la ley de Ohm, si le conecto una pila de 1 V, ¿qué corriente hay? Decenas de .........., demasiado." ));
		pregs3.add(new Pregunta(false,37,"Qué hace falta para que haya corriente en un semiconductor?La aplicación de energía que genere más electrones libres y la acción de un campo eléctrico, por ejemplo, conectándolo a una..."));
		pregs3.add(new Pregunta(false,38,"¿Se mueven solo los electrones o también los huecos? "));
		pregs3.add(new Pregunta(false,39,"¿Cual se mueve por saltos, el electro o el hueco?" ));
		pregs3.add(new Pregunta(false,40,"¿Qué pasa si un electrón pasa cerca de un hueco? Se... " ));
		pregs3.add(new Pregunta(true,41,"¿Puede haber mas electrones que huecos?" ));
		pregs3.add(new Pregunta(true,42,"¿Se mueve igual un electrón que un hueco, el electron mas o menos?"));
		pregs3.add(new Pregunta(true,43,"Se puede aumentar la corriente que circula en un ... aplicando un campo eléctrico," ));
		pregs3.add(new Pregunta(false,44,"Se suman o se restan los movimientos de los electrones y los huecos"));
		pregs3.add(new Pregunta(false,45,"Segun el modelo de Shockley, cuando un electrón se libera (creando un par electrón-hueco) sube a una planta superior sin “tráfico” y por tanto se mueve con" ));
		pregs3.add(new Pregunta(true,46,"El simil utiliza Shockley para explicar el movimiento de electrones y huecos es un"));
		pregs3.add(new Pregunta(false,47,"¿Cuales son los portadores intrinsecos? Los electrones libres o huecos que se crean al romper los ..."));
		pregs3.add(new Pregunta(true,48,"¿Qué es la conductividad propia o intrínseca? La “corriente” debida a los electrones libres o huecos que se crean, por ejemplo, al aumentar la" ));
		pregs3.add(new Pregunta(true,49,"¿Por qué se dice que a cada temperatura el silicio tiene una conductividad propia? Porque el número de electrones libres (y huecos)" ));
		pregs3.add(new Pregunta(true,50,"Al calentar un material, si la temperatura se mantiene ¿acaban rompiéndose todos los enlaces?" ));
		
		ArrayList<Pregunta>pregs4= new ArrayList<Pregunta>();
		
		pregs4.add(new Pregunta(true,53,"¿Qué ocurre si cambiamos un átomo de silicio por uno de antimonio? Lo..." ));
		pregs4.add(new Pregunta(true,54,"Se puede dopar un atomo,si cambiamos el atomo de silicio por uno de..." ));
		pregs4.add(new Pregunta(false,55,"El dopaje no puede ser muy alto porque dejaria de ser un material..." ));
		pregs4.add(new Pregunta(false,56,"El porcentaje de dopaje que se usa es: 1 atomo por cada 10 elevado a..." ));
		pregs4.add(new Pregunta(true, 57, "¿Qué semiconductor es uno dopado con impurezas negativas (electrones), es decir, con portadores mayoritarios negativos (electrones)? Es de tipo..."));
		pregs4.add(new Pregunta(false, 58, "¿Qué semiconductor es uno dopado con impurezas positivas(huecos), es decir, con portadores mayoritarios positivos(huecos)? Es de tipo..."));
		pregs4.add(new Pregunta(false, 59, "¿Hay más portadores después de dopar un material?"));
		pregs4.add(new Pregunta(true, 60, "¿De qué depende ahora el número de portadores? Del..."));
		pregs4.add(new Pregunta(true, 61, "¿Hay más portadores intrínsecos o extrínsecos cuando dopamos un material?"));
		pregs4.add(new Pregunta(false, 62, "En un semiconductor tipo N ¿hay más electrones o huecos?"));
		pregs4.add(new Pregunta(true, 63, "En un semiconductor tipo P ¿hay más electrones o huecos?"));
		pregs4.add(new Pregunta(true, 69, "En un material tipo P ¿qué portadores son extrínsecos? Los..."));
		
		ArrayList<Pregunta>pregs5= new ArrayList<Pregunta>();
		
		pregs5.add(new Pregunta(false, 70, "En un material tipo N ¿qué portadores son extrínsecos? Los..."));
		pregs5.add(new Pregunta(true, 71, "La conductividad debida a los portadores aportados por el dopado (portadores extrínsecos). Es la conductividad..."));
		pregs5.add(new Pregunta(false, 72, "En un semiconductor N ¿hay más, menos o empate de huecos?"));
		pregs5.add(new Pregunta(true, 73, "En un semiconductor P ¿hay más, menos o empate de huecos?"));
		pregs5.add(new Pregunta(false, 74, "¿Los portadores intrínsecos se suman o se restan de los extrínsecos?"));
		pregs5.add(new Pregunta(true, 75, "No todo electrón es un ... extrínseco en un material N"));
		pregs5.add(new Pregunta(false, 76, "¿Cuantos electrones de valencia suelen tener los semiconductores?"));
		pregs5.add(new Pregunta(false, 77, "El intervalo de banda es la cantidad mínima de energía necesaria para un electrón de liberarse de su estado de..."));
		pregs5.add(new Pregunta(true, 78, "El dopaje es una tecnica que se utiliza para variar el numero de electrones y ..... en los semiconductores."));
		pregs5.add(new Pregunta(false, 79, "¿Que unidad es la describe al condensador?"));
		pregs5.add(new Pregunta(true, 80, "La capacidad de faradios depende de varios factores, pero todos ellos son valores..."));
		pregs5.add(new Pregunta(true, 81, "¿En un condensador de laminas paralelas donde hay campo electrico, en el interior o en el exterior?"));
		pregs5.add(new Pregunta(false, 82, "Si se toman dos placas conductoras, se separan entre sí una distancia y se conectan a  los terminales de una batería, ambas cargas seran del mismo valor y de signo..."));
		pregs5.add(new Pregunta(false, 83, "¿Con que tipo de enlace se unen los atomos de silicio para crear un cristal?"));
		pregs5.add(new Pregunta(true, 84, "Para aumentar la corriente que circula por un semiconductor se le aplica un campo ..."));
		pregs5.add(new Pregunta(false, 85, "Los huecos se \"mueven\" en ... contrario a los electrones"));
		pregs5.add(new Pregunta(false, 86, "Un hueco tiene carga..."));
		
		GrupoPreguntas gp1 = new GrupoPreguntas(pregs1);
		GrupoPreguntas gp2 = new GrupoPreguntas(pregs2);
		GrupoPreguntas gp3 = new GrupoPreguntas(pregs3);
		GrupoPreguntas gp4 = new GrupoPreguntas(pregs4);
		GrupoPreguntas gp5 = new GrupoPreguntas(pregs5);
		
		Crucigrama cruc1 = new Crucigrama(cru1,gp1);
		Crucigrama cruc2 = new Crucigrama(cru2,gp2);
		Crucigrama cruc3 = new Crucigrama(cru3,gp3);
		Crucigrama cruc4 = new Crucigrama(cru4,gp4);
		Crucigrama cruc5 = new Crucigrama(cru5,gp5);
	
		ArrayList<Cabezera>cabs1 = new ArrayList<Cabezera>();
		ArrayList<Cabezera>cabs2 = new ArrayList<Cabezera>();
		ArrayList<Cabezera>cabs3 = new ArrayList<Cabezera>();
		ArrayList<Cabezera>cabs4 = new ArrayList<Cabezera>();
		ArrayList<Cabezera>cabs5 = new ArrayList<Cabezera>();
		
		cabs1.add(new Cabezera(cruc1.getCasilla(9,0),gp1.getPregunta("H2")));
		cabs1.add(new Cabezera(cruc1.getCasilla(0,2),gp1.getPregunta("V3")));
		cabs1.add(new Cabezera(cruc1.getCasilla(5,2),gp1.getPregunta("H4")));
		cabs1.add(new Cabezera(cruc1.getCasilla(8,10),gp1.getPregunta("V5")));
		cabs1.add(new Cabezera(cruc1.getCasilla(6,15),gp1.getPregunta("V6")));
		cabs1.add(new Cabezera(cruc1.getCasilla(14,1),gp1.getPregunta("H7")));
		cabs1.add(new Cabezera(cruc1.getCasilla(2,17),gp1.getPregunta("V8")));
		cabs1.add(new Cabezera(cruc1.getCasilla(14,2),gp1.getPregunta("V9")));
		cabs1.add(new Cabezera(cruc1.getCasilla(13,15),gp1.getPregunta("V10")));
		cabs1.add(new Cabezera(cruc1.getCasilla(5,6),gp1.getPregunta("V11")));
		cabs1.add(new Cabezera(cruc1.getCasilla(2,0),gp1.getPregunta("H12")));
		cabs1.add(new Cabezera(cruc1.getCasilla(11,10),gp1.getPregunta("H13")));
		cabs1.add(new Cabezera(cruc1.getCasilla(12,0),gp1.getPregunta("H14")));
		cabs1.add(new Cabezera(cruc1.getCasilla(6,18),gp1.getPregunta("V15")));
		cabs1.add(new Cabezera(cruc1.getCasilla(6,13),gp1.getPregunta("H16")));
		cabs1.add(new Cabezera(cruc1.getCasilla(9,9),gp1.getPregunta("H17")));
		cabs1.add(new Cabezera(cruc1.getCasilla(13,10),gp1.getPregunta("H18")));
		
		cruc1.setCabezeras(cabs1);
		
		cabs2.add(new Cabezera(cruc2.getCasilla(6, 6),gp2.getPregunta("H19")));
		cabs2.add(new Cabezera(cruc2.getCasilla(4, 8),gp2.getPregunta("V20")));
		cabs2.add(new Cabezera(cruc2.getCasilla(8,0),gp2.getPregunta("H21")));//CONDUCCION
	    cabs2.add(new Cabezera(cruc2.getCasilla(3,12),gp2.getPregunta("V22")));//NADA
	    cabs2.add(new Cabezera(cruc2.getCasilla(7,4),gp2.getPregunta("V23")));	//	MUCHO
	    cabs2.add(new Cabezera(cruc2.getCasilla(11,2),gp2.getPregunta("H24")));	//	PROHIBIDA
	   	cabs2.add(new Cabezera(cruc2.getCasilla(6,1),gp2.getPregunta("V25")));		// ORO
	   	cabs2.add(new Cabezera(cruc2.getCasilla(4,10),gp2.getPregunta("H26"))); // PLASTICO
	   	cabs2.add(new Cabezera(cruc2.getCasilla(9,10),gp2.getPregunta("V27")));// CUARZO
	   	cabs2.add(new Cabezera(cruc2.getCasilla(14,9),gp2.getPregunta("H28")));// COBRE
	   	cabs2.add(new Cabezera(cruc2.getCasilla(2,15),gp2.getPregunta("V29")));// VEINTICUATRO
	   	cabs2.add(new Cabezera(cruc2.getCasilla(8,11),gp2.getPregunta("H30")));// DIRECCION
	   	cabs2.add(new Cabezera(cruc2.getCasilla(10,8),gp2.getPregunta("V31")));// SI
	   	cabs2.add(new Cabezera(cruc2.getCasilla(1,16),gp2.getPregunta("H32")));// MACROSCOPICA ARRIBA
	   	cabs2.add(new Cabezera(cruc2.getCasilla(12,12),gp2.getPregunta("H33")));// MACROSCOPICA ARRIBA
	   	cabs2.add(new Cabezera(cruc2.getCasilla(0,17),gp2.getPregunta("V34")));// CAMPO
	   	cabs2.add(new Cabezera(cruc2.getCasilla(6,0),gp2.getPregunta("H35")));// DOS
		
	   	cruc2.setCabezeras(cabs2);
	
		cabs3.add(new Cabezera(cruc3.getCasilla(15, 7 ),gp3.getPregunta("H36"))); //AMPERIO
		cabs3.add(new Cabezera(cruc3.getCasilla(12, 7),gp3.getPregunta("V37")));//PILA
		cabs3.add(new Cabezera(cruc3.getCasilla(12, 13),gp3.getPregunta("V38")));//AMBOS
		cabs3.add(new Cabezera(cruc3.getCasilla(13, 10),gp3.getPregunta("V39")));//HUECO
		cabs3.add(new Cabezera(cruc3.getCasilla(5,5),gp3.getPregunta("V40")));	//	RECOMBINA
		cabs3.add(new Cabezera(cruc3.getCasilla(17,9),gp3.getPregunta("H41")));	//	NO
		cabs3.add(new Cabezera(cruc3.getCasilla(13,13),gp3.getPregunta("H42")));		// MAS
		cabs3.add(new Cabezera(cruc3.getCasilla(9,3),gp3.getPregunta("H43"))); // SEMICONDUCTOR
		cabs3.add(new Cabezera(cruc3.getCasilla(5,9),gp3.getPregunta("V44")));// SUMAN
		cabs3.add(new Cabezera(cruc3.getCasilla(0,1),gp3.getPregunta("V45")));// LIBERTAD
		cabs3.add(new Cabezera(cruc3.getCasilla(6,0),gp3.getPregunta("H46")));// GARAJE
		cabs3.add(new Cabezera(cruc3.getCasilla(5,12),gp3.getPregunta("V47")));// ENLACES
		cabs3.add(new Cabezera(cruc3.getCasilla(3,0),gp3.getPregunta("H48")));// TEMPERATURA
		cabs3.add(new Cabezera(cruc3.getCasilla(13,4),gp3.getPregunta("H49")));// VARIA
		cabs3.add(new Cabezera(cruc3.getCasilla(11,4),gp3.getPregunta("H50")));// SI
	
		
		cruc3.setCabezeras(cabs3);
	
	
		cabs4.add(new Cabezera(cruc4.getCasilla(0, 0),gp4.getPregunta("H53")));
		cabs4.add(new Cabezera(cruc4.getCasilla(5, 3),gp4.getPregunta("H54")));
		cabs4.add(new Cabezera(cruc4.getCasilla(0, 6),gp4.getPregunta("V55")));
		cabs4.add(new Cabezera(cruc4.getCasilla(10, 13),gp4.getPregunta("V56")));
		cabs4.add(new Cabezera(cruc4.getCasilla(1, 6),gp4.getPregunta("H57")));
		cabs4.add(new Cabezera(cruc4.getCasilla(0, 8),gp4.getPregunta("V58")));
		cabs4.add(new Cabezera(cruc4.getCasilla(9, 8),gp4.getPregunta("V59")));
		cabs4.add(new Cabezera(cruc4.getCasilla(13, 8),gp4.getPregunta("H60")));
		cabs4.add(new Cabezera(cruc4.getCasilla(10, 4),gp4.getPregunta("H61")));
		cabs4.add(new Cabezera(cruc4.getCasilla(2, 11),gp4.getPregunta("V62")));
		cabs4.add(new Cabezera(cruc4.getCasilla(2, 9),gp4.getPregunta("H63")));
		cabs4.add(new Cabezera(cruc4.getCasilla(5, 8),gp4.getPregunta("H69")));
		
		cruc4.setCabezeras(cabs4);
		
		cabs5.add(new Cabezera(cruc5.getCasilla(10, 7),gp5.getPregunta("V70")));
		cabs5.add(new Cabezera(cruc5.getCasilla(18, 7),gp5.getPregunta("H71")));
		cabs5.add(new Cabezera(cruc5.getCasilla(14, 3),gp5.getPregunta("V72")));
		cabs5.add(new Cabezera(cruc5.getCasilla(0, 12),gp5.getPregunta("H73")));
		cabs5.add(new Cabezera(cruc5.getCasilla(14, 12),gp5.getPregunta("V74")));
		cabs5.add(new Cabezera(cruc5.getCasilla(8, 13),gp5.getPregunta("H75")));
		cabs5.add(new Cabezera(cruc5.getCasilla(14, 10),gp5.getPregunta("V76")));
		cabs5.add(new Cabezera(cruc5.getCasilla(0, 13),gp5.getPregunta("V77")));
		cabs5.add(new Cabezera(cruc5.getCasilla(7, 7),gp5.getPregunta("H78")));
		cabs5.add(new Cabezera(cruc5.getCasilla(10, 0),gp5.getPregunta("V79")));
		cabs5.add(new Cabezera(cruc5.getCasilla(10, 6),gp5.getPregunta("H80")));
		cabs5.add(new Cabezera(cruc5.getCasilla(15, 0),gp5.getPregunta("H81")));
		cabs5.add(new Cabezera(cruc5.getCasilla(2, 15),gp5.getPregunta("V82")));
		cabs5.add(new Cabezera(cruc5.getCasilla(10, 14),gp5.getPregunta("V83")));
		cabs5.add(new Cabezera(cruc5.getCasilla(2, 12),gp5.getPregunta("H84")));
		cabs5.add(new Cabezera(cruc5.getCasilla(11, 5),gp5.getPregunta("V85")));
		cabs5.add(new Cabezera(cruc5.getCasilla(6, 11),gp5.getPregunta("V86")));
		
		cruc5.setCabezeras(cabs5);
		
		listaCrucigramas.add(cruc1);
		listaCrucigramas.add(cruc2);
		listaCrucigramas.add(cruc3);
		listaCrucigramas.add(cruc4);
		listaCrucigramas.add(cruc5);
	
	}

	static void guardarCrucigramasEnFicheros() {
		int cont=1;
		String nombre="crucigrama";
		String extension=".dat";
		for (Crucigrama c:listaCrucigramas) {
			String nombreFichero=nombre+cont+extension;
			guardaEnFichero(c,nombreFichero);
			cont++;
		}
	}

	private static void guardaEnFichero(Crucigrama c, String nombreFichero) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreFichero));
			oos.writeObject(c);
			oos.close();
		} catch (IOException e) {
			System.err.println("Error al guardar crucigrama en" + nombreFichero);
			e.printStackTrace();
		}
	}
	*/
}
