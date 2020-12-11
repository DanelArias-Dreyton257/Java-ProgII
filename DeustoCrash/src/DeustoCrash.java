import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * @author Danel
 *
 */
public class DeustoCrash {
	private static BufferedReader brTeclado=new BufferedReader(new InputStreamReader(System.in));
	private static final int V_ANCHURA=1000;
	private static final int V_ALTURA=1000;
	private static final int NUM_PA_ELIMINAR=3;
	private static final int NUM_MAX_SIN_QUITAR=5;
	private static int numMovs;
	private static int movsSeguidosSinQuitar;
	
	public static void main(String[] args) {

		TableroCaramelos t = new TableroCaramelos();
		System.out.println(t);
		VentanaGrafica v = new VentanaGrafica(V_ANCHURA, V_ALTURA, "DeustoCrash");
		
		boolean terminar=false;
		numMovs=0;
		movsSeguidosSinQuitar=0;
		dibujar(t, v);
		while(!terminar) {
			v.espera(250);
			v.borra();
			dibujar(t, v);
			boolean quitado=false;
			do {
				quitado = buscaYQuitaLineas( t );
				v.espera(250);
				v.borra();
				dibujar(t, v);
				if (quitado) {
					movsSeguidosSinQuitar = 0;
					caenLasPiezas( t );
					v.espera(250);
					v.borra();
					dibujar(t, v);
				}
			}while (quitado);
			if (movsSeguidosSinQuitar >= NUM_MAX_SIN_QUITAR) {
				terminar = true;
			} 
			else if (!hayMovimientosPosibles( t )) {  // No hay movimientos posibles
				terminar = true;
			} 
			else {
				// Controla movimiento
				interaccionConUsuario(t);
				v.espera(250);
				v.borra();
				dibujar(t, v);
				System.out.println( "Movimientos realizados: " + numMovs );
			}


		}
		
	}
	/**
	 * Metodo temporal para la consecucion del juego
	 * @param t
	 */
	private static void interaccionConUsuario(TableroCaramelos t) {
		System.out.print( "Introduce intercambio. Fila de origen: ");
		int c1f = leerEnteroDeTeclado(); 
		System.out.print( "Introduce intercambio. Columna de origen: ");
		int c1c = leerEnteroDeTeclado(); 
		System.out.print( "Introduce intercambio. Fila de destino: ");
		int c2f = leerEnteroDeTeclado(); 
		System.out.print( "Introduce intercambio. Columna de destino: ");
		int c2c = leerEnteroDeTeclado(); 
		if (c1f >= 0 && c1f < t.getNumFilas() && c2f >= 0 && c2f < t.getNumFilas() &&
			c1c >= 0 && c1c < t.getNumColumnas() && c2c >= 0 && c2c < t.getNumColumnas()) {
			int distancia = Math.abs( c1f-c2f );
			distancia = Math.min( distancia, Math.abs( c1c-c2c ) );
			if (distancia <= 1) {  // Sólo si están contiguas
				CarameloUD cm1 = t.getCaramelo(c1f, c1c);
				CarameloUD cm2 = t.getCaramelo(c2f, c2c);
				if (cm1 != null && cm2 != null) {  
					// Sólo si son caramelos los que se intercambian
					t.intercambiaCaramelo( c1f,c1c, c2f,c2c );
					movsSeguidosSinQuitar++;
					numMovs++;
				}
			}
		}
	}

	/**Metodo que busca si NUM_PA_QUITAR<= numero de CarameloUD del mismo color juntos
	 * y los elimina
	 * 
	 * @param t Tablero de caramelos
	 * @return boolean, true si ha desaparecido alguna linea
	 */
	public static boolean buscaYQuitaLineas( TableroCaramelos t ) {
		boolean lineasDesaparecen=false;
		
		//Busco en vertical
		for(int j=0;j<t.getNumColumnas();j++) {
			Color colorAnterior=null;
			int numIguales=0;
			for (int i=0; i<t.getNumFilas();i++) {
				Color nColor= null;
				if (t.getCaramelo(i, j)!=null) {
					nColor=t.getCaramelo(i, j).getColor();
				}
				if(nColor!=null && nColor.equals(colorAnterior)) {
					numIguales++;
				}
				else {
					if (numIguales>=NUM_PA_ELIMINAR) {
						for (int eliminar=i-numIguales;eliminar<i;eliminar++) {
							t.quitaCaramelo(eliminar, j);
						}
						lineasDesaparecen=true;
					}
					colorAnterior=nColor;
					numIguales=1;
				}
			}
			if (numIguales>=NUM_PA_ELIMINAR) {
				for (int eliminar = t.getNumFilas()-numIguales; eliminar<t.getNumFilas(); eliminar++) {
					t.quitaCaramelo(eliminar, j);
				}
				lineasDesaparecen = true;
			}
	
		}
		//Busco en horizontal
		for (int i=0; i<t.getNumFilas();i++) {
			Color colorAnterior=null;
			int numIguales=0;
			for (int j=0; j<t.getNumColumnas();j++) {
				Color nColor= null;
				if (t.getCaramelo(i, j)!=null) {
					nColor=t.getCaramelo(i, j).getColor();
				}
				if(nColor!=null && nColor.equals(colorAnterior)) {
					numIguales++;
				}
				else {
					if (numIguales>=NUM_PA_ELIMINAR) {
						for (int eliminar=j-numIguales;eliminar<j;eliminar++) {
							t.quitaCaramelo(i, eliminar);
						}
						lineasDesaparecen=true;
					}
					colorAnterior=nColor;
					numIguales=1;
				}
			}
			if (numIguales>=NUM_PA_ELIMINAR) {
				for (int eliminar = t.getNumColumnas()-numIguales; eliminar<t.getNumColumnas(); eliminar++) {
					t.quitaCaramelo(i, eliminar);
				}
				lineasDesaparecen = true;
			}
		}
		if (lineasDesaparecen) {
			System.out.println("Se han eliminado");
		}
		return lineasDesaparecen;
	}
	
	public static boolean hayMovimientosPosibles( TableroCaramelos t) {
		boolean result=true;
		for(int i=1;i<t.getNumFilas();i++) {
			for(int j=1;j<t.getNumColumnas();j++) {
				CarameloUD c = t.getCaramelo(i, j);
				if(c!=null) {
					CarameloUD cmArriba = t.getCaramelo(i-1,j);
					if (cmArriba!=null){ return true;}
					CarameloUD cmIzqda = t.getCaramelo(i,j-1);
					if (cmIzqda!=null) { return true;}
				}
			}
		}
		return result;
	}
	/**
	 * Metodo que mira si hay huecos vacios y hace que los Caramelo UD se cambien de posicion con el hueco vacio
	 * @param t Tablero de caramelos
	 */
	public static void caenLasPiezas(TableroCaramelos t) {
		boolean cayoAlguna= true;
		while(cayoAlguna) {
			cayoAlguna=false;
			for (int j=0;j<t.getNumColumnas();j++) {
				for (int i=t.getNumFilas()-2;i>=0;i--) {
					CarameloUD cCurrent= t.getCaramelo(i, j);
					CarameloUD cAbajo= t.getCaramelo(i+1, j);
					if (cAbajo==null && cCurrent!=null) {
						t.mueveCaramelo(i, j, i+1, j);
						cayoAlguna=true;
					}
				}
			}
			if (cayoAlguna) {
				System.out.println("Caen pelotas");
			}
		}
	}
	
	/**
	 * 
	 * @param t objeto TableroCaramelos
	 * @param v objeto VentanaGrafica donde dibujar el  tablero
	 */
	public static void dibujar(TableroCaramelos t,VentanaGrafica v) {
		for (int i=0; i<t.getNumFilas();i++) {
			for(int j=0;j<t.getNumColumnas();j++) {
				CarameloUD c = t.getCaramelo(i, j);
				if (c!=null) {
					c.dibujar(t.getNumFilas(),t.getNumColumnas(),v);
				}
			}
		}
	}
	
	 /** Lee un entero positivo de teclado
	 * @return Entero leída, o -1 si error en la entrada
	 */
	 public static int leerEnteroDeTeclado() {
		 try {
			 String lin = brTeclado.readLine();
			 if (lin==null) return -1;
			 return Integer.parseInt( lin );
		 } catch (Exception e) {
			 return -1;
		 }
	 } 
}
