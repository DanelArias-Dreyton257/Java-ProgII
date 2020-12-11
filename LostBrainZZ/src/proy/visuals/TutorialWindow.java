package proy.visuals;

import java.awt.*;
import javax.swing.*;
/**
 * 
 * @author Danel
 *
 */
public class TutorialWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	//Atributos ventana
	private static final int WIDTH=700;
	private static final int HEIGHT=700;
	private static final String TITLE= "Tutorial and Info.";
	private static final int TXA_HEIGHT = 100;
	private static final int TXA_WIDTH_MARGIN = 100;
	private static final Color BG_COLOR = new Color(238, 238, 238);
	private static final int IMG_SIZE = 100;
	//JPanel
	private JPanel pnTop = new JPanel();
	private JPanel pnCentral = new JPanel();

	private JPanel pnCenP1 = new JPanel();
	private JPanel pnCenP2 = new JPanel();
	private JPanel pnCenP3 = new JPanel();
	private JPanel pnCenP4 = new JPanel();
	private JPanel pnCenP5 = new JPanel();
	private JPanel pnCenP6 = new JPanel();
	private JPanel pnCenP7 = new JPanel();
	private JPanel pnCenP8 = new JPanel();
	private JPanel pnCenP9 = new JPanel();
	private JPanel pnCenP10 = new JPanel();
	private JPanel pnCenP11 = new JPanel();
	private JPanel pnCenP12 = new JPanel();

	private JPanel pnBot= new JPanel();
	//JScrollPane
	private JScrollPane scrollPnCentral = new JScrollPane(pnCentral);
	//Fonts
	private Font fTitle = new Font("Title",1,30);
	private Font fSubTitle = new Font("Title",1,20);
	//JLabel
	private JLabel message = new JLabel("");

	//Strings
	private String t1 = "¿De que trata el juego?";
	private String p1 = "The LostBrainZZ es un juego Arcade en el que tendras que sobrevivir a "
			+ "hordas de Zombies que te atacaran en oleadas. Para ello, podras matarlos con "
			+ "diferentes armas y conseguir despues dinero traficando con los cerebros que hayas "
			+ "conseguido para asi, poder mejorar tu laberinto e impedir a las nuevas oleadas de "
			+ "Zombies llegar a la salida del mismo. ";

	private String t2 = "¿Como se gana, y como se pierde?";
	private String p2 = "Simplificando, se gana si se derrotan todos los zombies, y se pierde si alguno de ellos "
			+ "lega al final del laberinto. Especificando un poco mas, se gana si superas todas las hordas de "
			+ "zombies, o lo que es lo mismo, si superas todas las rondas aniquilando a todos los infectados. "
			+ "Perder en cambio, al estar incompleto el juego es un poco mas \"fortuito\". La idea es que si un "
			+ "zombie llega a la casilla de salida del laberinto has perdido pues el zombie ha llegado a su destino "
			+ "sin que pudieras pararlo. Lo de que sea \"fortuito\" se explica en el siguiente apartado.\n"
			+ "A continuacion aparecen las dos imagenes que te puedes encontrar cuando se finaliza el juego.";

	private String t3 ="Peculiaridades de que este incompleto";
	private String p3 = "Este proyecto ha sido muy ambicioso, y por lo tanto muchos apartados estan "
			+ "incompletos en comparacion con lo que se tenia previsto. El mejor ejemplo es que desde "
			+ "el principio se penso en que los zombies tuvieran capacidad de movimiento en el laberinto "
			+ "y hasta esta ultima version, aparecian fortuitamente en una casilla cualquiera al inicio de "
			+ "la ronda, ahora ya pueden moverse y van entrando en el laberinto pero no se mueven de forma "
			+ "logica sino justo lo contrario, se mueven aleatoriamente a cualquiera de las casillas adyacentes "
			+ "que esten libres. Inicialmente se tenia previsto implementar un algoritmo de \"PathFinding\" como "
			+ "Dijkstra para que siguieran una ruta con sentido. A consecuencia de esto, no hay forma de limitar "
			+ "la construccion del usuario para que no cree un callejon sin salida el cual impediria totalmente a "
			+ "los zombies avanzar por el laberinto. Con Dijkstra implementado solo dejaria avanzar a la fase de "
			+ "pelea si el laberinto permitia una ruta desde la entrada hasta la salida ya que se comprobaria ejecutando"
			+ "Dijkstra.\nOtra caracteristica es que la intencion no era crear un juego arcade en el que se consigue "
			+ "una puntuacion y el unico objetivo es superarla sin ofrecer ninguna experiencia nueva, solo el hecho "
			+ "de superarse, sino que la intencion era crear una moneda fuera de las partidas que se consiguiese "
			+ "segun las puntuacion y permitiera comprar mejoras para favorecer la progresion del usuario y por "
			+ "lo tanto no hacer tediosas las primeras rondas, que al avanzar las horas jugadas acabarian siendo "
			+ "las mas aburridas por ya conocer las mecanicas del juego. Asi se podria llegar mas facilmente a las "
			+ "rondas finales y disfrutar de la complejidad de las mismas y el reto que supone superarlas."
			+ "Realmente, existen muchas otras curiosidades, como que inicialmente se pensaron mas tipos de armas "
			+ "o que se tenia pensado implementar diferentes tipo de zombies con diferentes atributos y caracteristicas "
			+ "en vez de solo un tipo con atributos aleatorios dentro de un margen.";

	private String t4 = "Las Armas";
	private String p41 = "Electric Weapon o Arma Electrica:\nEsta arma hace daño gracias a la potente descarga electrica "
			+ "que genera. Es un arma de muy corto alcance, y solo llega  alas casillas continuas, no en diagonal. "
			+ "Tiene un poder de ataque normal y se estropea no muy rapido. Ademas solo cuesta 500 monedas por lo que "
			+ "es el arma ideal para matar zombies a poca distancia.";
	private String p42 = "Turret o Torreta:\nEsta arma se caracteriza por tener un largo alcance. Sus ataques son los menos "
			+ "poderosos pero al ser un arma rapida y con su alcance lo compensa. No se estropea muy rapido, pero como dispara "
			+ "muchas veces da la impresion de que si lo hace. Es un arma bastante util pero de razonable precio. 1000 monedas";
	private String p43 = "Bomb o Bomba:\nEsta arma es la mas poderosa del juego ya que es capaz de matar a cualquier Zombie de "
			+ "un solo golpe. Claramente esto se ve reflejado en su precio pues es la mas cara ya que llega a costar 2000 monedas. "
			+ "Como bomba que es, solo explota una vez y por ello hay que tener cuidado de donde se colocan para intentar alcanzar a "
			+ "la mayor cantidad de infectados.";

	private String p44 = "¿COMO COLOCAR LAS ARMAS?\nRealmente es muy sencillo, en cualquiera de las fases, tanto de batalla como "
			+ "de construccion el jugador puede clickar con el raton en el arma que prefiera del menu superior y si tiene las monedas "
			+ "necesarias para la compra de la misma, cuando seleccione un camino en el laberinto esa arma sera comprada y colocada en "
			+ "ese punto del laberinto.";

	private String t5 = "Indicadores";
	private String p51 = "Contador de rondas:\nLLeva el conteo de la ronda en la se encuentra el jugador o "
			+ "lo que es lo mismo el numero de hordas que se ha encontrado durante la partida.";
	private String p52 = "Contador de cerebros:\nLleva el conteo de los cerebros recogidos en la batalla contra los zombies.";
	private String p53 = "Contador de monedas:\nLleva el conteo del dinero conseguido en el juego, tanto traficando con cerebros "
			+ "como vendiendo cualquier tipo de arma o pared.";

	private String t6 ="Boton de salir";
	private String p61 = "No hay mucho que explicar. Pulsar este boton te permite cerrar la ventana de juego y volver al menu. "
			+ "Hace la misma funcion que la \"X\" de la esquina superior de la ventana.";

	private String t7="Barra de vida de la horda";
	private String p71="Esta barra es un indicador de el numero de zombies que quedan vivos de la horda. Cuanta mas cantidad del "
			+ "rectangulo este de color verde es que quedan mas zombies por derrotar, en cambio, cuanto mas cantidad sea de color rojo "
			+ "mas zombies se habran derrotado. Esta barra solo aparecera durante las fases de lucha.";

	private String t8 ="Botones: Fase de construccion";
	private String p81 = "Convertidor de cerebros a dinero o de trafico de cerebros:\nEste boton permite traficar con los cerebros conseguidos "
			+ "derrotando infectados y asi conseguir dinero para la construccion del laberinto. ¡ATENCION! Segun pasan las rondas, el mercado negro "
			+ "se va quedando sin provisiones por lo que cuanto mas alta tardia sea la ronda en la que traficas con ellos mas cantidad de dinero "
			+ "podras conseguir, aunque sean cerebros de rondas anteriores que no hayas vendido. Recuerda, es importante gestionar cuando venderlos "
			+ "par no quedarte sin dinero.";
	private String p82 = "Colocacion de paredes:\nEste boton y el de abajo son esencialmente el mismo pero mientras estan activados permiten  hacer "
			+ "cosas diferentes. Si el boton se encuentra en el modo de la imagen significa que en cada trozo de camino del laberinto que pulses estaras "
			+ "intentando colocar una pared, por lo tanto, si tienes el dinero suficiente, se comprara y se colocara en el lugar deseado. Si se pulsa el "
			+ "boton en este estado pasara al de abajo.";
	private String p83 = "Venta:\nEste boton y el de arriba son esencialmente el mismo pero mientras estan activados permiten  hacer cosas diferentes. "
			+ " Si el boton se encuentra en el modo de la imagen significa que esta en modo de venta. Por lo tanto si se pulsa cualquier arma o pared ("
			+ "queno sea del borde) se vendera por su precio correspondiente, siempre menor al precio de compra.  Si se pulsa el boton en este estado "
			+ "pasara al de arriba." ;
	private String p84 = "Salir de la fase de construccion: Este boton permite salir de la fase de construccion y dirijirsse a la siguiente ronda de "
			+ "pelea en la cual la proxima oleada de Zombies atacaran. Es recomendable solo pulsar este boton solo si se esta preparado para la horda "
			+ "ya que durante la fase de pelea no se podran construir paredes de laberinto ni vender ningun tipo de arma o pared.";

	private String t9 = "Zombies";
	private String p91 = "Estas criaturas seran las que atacaran nuestro laberinto con objetivo de llegar a la salida e infectar a los supervivientes. "
			+ "Lo curioso es que son humanos deformados por un parasito que los humanos supervivientes llaman \"Zombieitor\" y habita en cerebro "
			+ "humano. Este parasito se alimenta del cerebro y poco a poco lo va devorando. Durante el proceso el parasito va estimulando el cuerpo que "
			+ "habita para que se vaya moviendo pues pretende que tras incubar sus crias y antes de que muera el huesped pueda infectar a otro humano para "
			+ "asi seguir reproduciendose. A los humanos infectados se les considera muertos vivientes pues el parasito los mata dolorosamente mientras "
			+ "devoran las partes principales del cerebro, que seran las que le permitan comenzar a gestar a sus crias, y el cuerpo se empieza a pudrirse pues "
			+ "no recibe ningun tipo de sustento. Lo unico que diferencia a un muerto normal de uno infectado es el hecho de que se pueden mover. "
			+ "Tras la infeccion estos parasitos desarrollan una forma de orientacion basada en ondas sonoras, lo que les permite no chocar con paredes y muros."
			+ " Tambien desarrollan un gran capacidad olfativa que les permite oler humanos sanos desde kilometros de distancia. Se puede observar que hay "
			+ "diferentes \"variedades\" de estas criaturas. Al igual que los humanos, hay zombies mas atleticos, mas resistentes o mas fuertes, al igual que mas "
			+ "debiles en cualquier aspecto. Se observa que se organizan en grupos para atacar.";

	private String t10 = "El laberinto";
	private String p101 = "Path o camino:\nComo su nombre indica es donde se camina, y por lo tanto los zombies podran andar sobre este suelo. Tambien, sera donde "
			+ "se coloquen las armas y donde se pueda construir una pared.";
	private String p102 = "Wall o pared:\nComo su nombre indica es una pared. Esta pared se usa para la creacion del laberinto. Por lo que conocemos es imposible de "
			+ "romper por parte de los zombies. Eso si, es posible tirarla abajo gracias a las herramientas de las que disponen los humanos. Si se tira abajo, dejara "
			+ "camino en donde estaba la pared.";

	private String t11 ="Easter Eggs o Huevos de Pascua";
	private String p11 = "Este juego tiene unos cuantos secretos escondidos. La mayoria de ellos son audios especiales que sonaran antes de la musica de victoria en la "
			+ "pantalla de victoria. Otros 2 en cambio son dos audios que suenan aleatoriamente con ciertas acciones en el juego. ¡¡Buena suerte encontrandolos!!";

	private String t12 = "Historia bajo el juego (Version superficial)";
	private String p12 = "ESTE DOCUMENTO ESTA CON LA IDEA PRINCIPAL DE LA HISTORIA TRAS EL JUEGO. NO ES DEFINITIVO.\n\n" + 
			"Lost BrainZZ ocurre en un mundo post-apocaliptico tras una pandemia brutal que resulta en la infeccion "
			+ "de la mayoria de la poblacion del planeta. Esta enfermedad realmente resulta ser un parasito alienigena "
			+ "venido en un meteorito, a este parasito lo llamarian mas tarde \"Zombieitor\" y habita en el cerebro "
			+ "humano, este parasito se alimenta del cerebro y poco a poco lo va devorando. Durante el proceso el parasito "
			+ "va estimulando el cuerpo que habita para que se vaya moviendo pues pretende que tras incubar sus crias y "
			+ "antes de que muera el huesped pueda infectar a otro humano para asi seguir reproduciendose.A los humanos "
			+ "infectados se les considera muertos vivientes pues el parasito los mata dolorosamente mientras devoran las "
			+ "partes principales del cerebro, que seran las que le permitan comenzar a gestar a sus crias, y el cuerpo se "
			+ "empieza a pudrirse pues no recibe ningun tipo de sustento. Lo unico que diferencia a un muerto normal de uno "
			+ "infectado es el hecho de que se pueden mover. Recientes estudios de este mundo desvelan que los parasitos "
			+ "no sobreviven en cuerpos en los que no quede cerebro o que lleven fallecidos mas de 3 anyos, por lo que "
			+ "la unica forma de que no se reproduzcan mas es o que no puedan acceder a mas alimentacion, o sea cerebros "
			+ "humanos, por 3 anyos y asi, los parasitos no podran sobrevivir y se extinguiran. Por ello grupos de "
			+ "humanos por todo el planeta se refugian en diferentes lugares cerrados y bunqueres. En este caso seguimos "
			+ "a un grupo de personas, que han conseguido sobrevivir durante unos 6 anyos y han contruido una ciudad "
			+ "protegida por muros enormes en la que el mercado negro esta a la orden del dia, sobre todo la trata de "
			+ "cerebros que han sido infectados pues se venden a un alto precio ya que los investigadores precisan de "
			+ "ellos, aunque esta no es la verdadera razon de su valor monetario. Su verdadero valor viene de que en esta "
			+ "situacion la mayoria de personas no tienen acceso a alimentacion y por lo tanto se alimentan de estos cerebros."
			+ " Aunque estos tengan probabilidad de infectarte al ingerirlos, es minima y los excrementosdel parasito "
			+ "que deposita en el cerebro durante su estancia resultan tener un valor energetico y nutritivo muy alto. "
			+ "Eso si, estos cerebros no se consiguen solos y por lo tanto se precisa de alguna forma de acumular "
			+ "personas infectadas para matarlas y conseguir asi sus cerebros para su futura venta. Por "
			+ "esto el grupo decide desarrollar un complejo laberinto con trampas y armas. Esto puede llevar a "
			+ "peligro pues implica dejar abierta una entrada para los infectados. Puede llamar la atencion "
			+ "el porque se elige un laberinto si conlleva tantos problemas. Resulta que los parasitos son capaces de "
			+ "localizar nuevos huespedes gracias a su olfato, y de moverse gracias a ciertas ondas sonoras. Por esto "
			+ "hay que dejar que huelan una carnada para que decida adentrarse. Ademas por el olfato, cuantos mas humanos "
			+ "sanos haya en como carnada mas parasitos de mas lejos se atraeran. Por esto se prepara el lugar para un "
			+ "laberinto con llegada la entrada de la ciudad para que asi los infectados sean traidos por el olor generado "
			+ "por toda esa multitud sana.\n\nEl jugador tendra el papel de proteger la ciudad gracias a la construccion del "
			+ "laberinto y la colocacion de las armas para la destruccion de los infectados. Es importante destruirlos tambien "
			+ "pues lo cerebros conseguidos podran ser vendidos para conseguir el dinero que permitira la construccion "
			+ "de nuevas armas y nuevas paredes del laberinto.";

	public TutorialWindow() {
		//Colocación de paneles y componentes en paneles
		getContentPane().setLayout(new BorderLayout());
		//Paneles en su sitio
		getContentPane().add(scrollPnCentral, BorderLayout.CENTER);
		getContentPane().add(pnBot, BorderLayout.SOUTH);
		getContentPane().add(pnTop,BorderLayout.NORTH);
		//Tamanyo y colocacion de la ventana
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		//Poner la ventana en el centro de la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setResizable(false);
		setTitle(TITLE);
		//Titulo
		JLabel title = new JLabel(TITLE,SwingConstants.CENTER);
		title.setFont(fTitle);
		title.setForeground(Color.BLUE);
		pnTop.add(new JPanel().add(title));
		//Panel inferior
		pnBot.add(message);

		//Panel Central
		pnCentral.setLayout(new BoxLayout(pnCentral, BoxLayout.Y_AXIS));

		//primer parrafo
		pnCentral.add(getSubTitle(t1));
		pnCenP1.add(getParagraph(p1,true));
		pnCentral.add(pnCenP1);

		//SEGUNDO PARRAFO
		pnCentral.add(getSubTitle(t2));
		pnCenP2.setLayout(new BoxLayout(pnCenP2, BoxLayout.Y_AXIS));
		pnCenP2.add(getParagraph(p2,true));
		JPanel p = new JPanel(new GridLayout(1, 2));
		p.add(new JLabelGrafico("images/YOU_WIN.png", (int)(IMG_SIZE*2.5), IMG_SIZE));
		p.add(new JLabelGrafico("images/GAME_OVER.png", IMG_SIZE*3, IMG_SIZE));
		pnCenP2.add(p);
		pnCentral.add(pnCenP2);

		//3er
		pnCentral.add(getSubTitle(t3));
		pnCenP3.add(getParagraph(p3, true));
		pnCentral.add(pnCenP3);

		//4to
		pnCentral.add(getSubTitle(t4));
		pnCenP4.setLayout(new GridLayout(3, 2));
		pnCenP4.add(getImage("images/ElectricWeapon_pxArt.png"));
		pnCenP4.add(getParagraph(p41, false));
		pnCenP4.add(getImage("images/Turret_pxArt.png"));
		pnCenP4.add(getParagraph(p42, false));
		pnCenP4.add(getImage("images/Bomb_pxArt.png"));
		pnCenP4.add(getParagraph(p43, false));
		pnCentral.add(pnCenP4);
		pnCentral.add(getParagraph(p44, true));

		//5to parrafo
		pnCentral.add(getSubTitle(t5));
		pnCenP5.setLayout(new GridLayout(3, 2));
		pnCenP5.add(getImage("images/Round_pxArt.png"));
		pnCenP5.add(getParagraph(p51, false));
		pnCenP5.add(getImage("images/Brain_pxArt.png"));
		pnCenP5.add(getParagraph(p52, false));
		pnCenP5.add(getImage("images/Coin_pxArt.png"));
		pnCenP5.add(getParagraph(p53, false));
		pnCentral.add(pnCenP5);

		//6to parrafo
		pnCentral.add(getSubTitle(t6));
		pnCenP6.setLayout(new GridLayout(1, 2));
		pnCenP6.add(getImage("images/X_pxArt.png"));
		pnCenP6.add(getParagraph(p61, false));
		pnCentral.add(pnCenP6);

		//7mo parrafo
		pnCentral.add(getSubTitle(t7));
		pnCenP7.setLayout(new GridLayout(1, 2));
		JPanel p1 = new JPanel();
		p1.add(new JLabelGrafico("images/LifeBar.png", IMG_SIZE*3, IMG_SIZE/3));
		pnCenP7.add(p1);
		pnCenP7.add(getParagraph(p71, false));
		pnCentral.add(pnCenP7);


		//8vo parrafo
		pnCentral.add(getSubTitle(t8));
		pnCenP8.setLayout(new GridLayout(4, 2));
		pnCenP8.add(getImage("images/ConvertBrain_pxArt.png"));
		pnCenP8.add(getParagraph(p81, false));
		pnCenP8.add(getImage("images/WallO_pxArt.png"));
		pnCenP8.add(getParagraph(p82, false));
		pnCenP8.add(getImage("images/WallX_pxArt.png"));
		pnCenP8.add(getParagraph(p83, false));
		pnCenP8.add(getImage("images/BP_exit_pxArt.png"));
		pnCenP8.add(getParagraph(p84, false));
		pnCentral.add(pnCenP8);

		//9no parrafo
		pnCentral.add(getSubTitle(t9));
		pnCenP9.setLayout(new GridLayout(1, 2));
		pnCenP9.add(getImage("images/Zombie_pxArt.png"));
		pnCenP9.add(getParagraph(p91, false));
		pnCentral.add(pnCenP9);

		//10mo parrafo
		pnCentral.add(getSubTitle(t10));
		pnCenP10.setLayout(new GridLayout(2, 2));
		pnCenP10.add(getImage("images/Path4_pxArt.png"));
		pnCenP10.add(getParagraph(p101, false));
		pnCenP10.add(getImage("images/Wall_pxArt.png"));
		pnCenP10.add(getParagraph(p102, false));
		pnCentral.add(pnCenP10);

		//11mo
		pnCentral.add(getSubTitle(t11));
		pnCenP11.add(getParagraph(p11, true));
		pnCentral.add(pnCenP11);

		//12vo
		pnCentral.add(getSubTitle(t12));
		pnCenP12.add(getParagraph(p12, true));
		pnCentral.add(pnCenP12);

	}
	/**
	 * Establece el mensaje
	 * @param msg
	 */
	public void setMessage(String msg) {
		message.setText(msg);
	}
	/**
	 * Devuelve un panel con una JLabel que cumple las caracteristicas para ser un titulo secundario
	 * @param msg
	 * @return
	 */
	private JPanel getSubTitle(String msg) {
		JPanel p = new JPanel();
		JLabel l = new JLabel(msg);
		l.setFont(fSubTitle);
		p.add(l);
		return p;
	}
	/**
	 * Devuelve un panel con un JTextArea que cumple las caracteristicas para ser un parrafo
	 * @param msg
	 * @param bgDiv true si es parrafo grande, false si es pequeño
	 * @return
	 */
	private JPanel getParagraph(String s, boolean bigDiv) {
		JPanel pn = new JPanel();
		JTextArea t = new JTextArea(s);
		t.setWrapStyleWord(true);
		t.setLineWrap(true);
		t.setEditable(false);
		t.setBackground(BG_COLOR);

		if (bigDiv) {
			t.setSize(WIDTH-TXA_WIDTH_MARGIN, TXA_HEIGHT);
		}
		else t.setSize((WIDTH/2)-TXA_WIDTH_MARGIN,TXA_HEIGHT);
		pn.add(t);
		return pn;
	}
	/**
	 * Devuelve un panel con una JLabel con la imagen indicada en el path enviado como parametro
	 * @param source
	 * @return
	 */
	private JPanel getImage(String source) {
		JPanel pn = new JPanel();

		JLabelGrafico l = new JLabelGrafico(source, IMG_SIZE,IMG_SIZE);

		pn.add(l);
		return pn;
	}

}
