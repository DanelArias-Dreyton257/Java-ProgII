import java.util.Arrays;

/**
 * 
 * @author Danel
 *
 */
public class Grafo {
	private int[][] matrizAdyacencia;
	private int[][] matrizIncidencia;
	private Vertice[] vertices;
	private Arista[] aristas;
	/**
	 * 
	 * @param vertices Array de objetos Vertice
	 * @param aristas Array de objetos Arista
	 */
	Grafo(Vertice[] vertices,Arista[] aristas){
		setVertices(vertices);
		setAristas(aristas);
		matrizAdyacencia=new int[vertices.length][vertices.length];
		matrizIncidencia= new int[vertices.length][aristas.length];
		createMtzAdy();
		createMtzInc();
	}
	/**
	 * 
	 * @param mtzInc Matriz de Incidencia
	 */
	Grafo(int[][] mtzInc){
		matrizIncidencia= mtzInc;
		vertices = new Vertice[mtzInc.length];
		aristas = new Arista[mtzInc[0].length];
		matrizAdyacencia=new int[vertices.length][vertices.length];
		createVertices();
		createAristas();
		createMtzAdy();
	}
	/**
	 * Crea la matriz de incidencia del grafo a partir de las arrays de vertices y de aristas 
	 */
	private void createMtzInc() {
		for(int a=0;a<aristas.length;a++) {
			Vertice v1=aristas[a].getV1();
			Vertice v2=aristas[a].getV2();
			for(int v=0;v<vertices.length;v++) {
				if(vertices[v].equals(v1)) {
					matrizIncidencia[v][a]++;
				}
				if(vertices[v].equals(v2)) {
					matrizIncidencia[v][a]++;
				}
			}
		}
		
	}
	/**
	 * Crea la matriz de adyacencia del grafo a partir de las arrays de vertices y de aristas 
	 */
	private void createMtzAdy() {
		for (int a=0;a<aristas.length;a++) {
			Vertice v1=aristas[a].getV1();
			Vertice v2=aristas[a].getV2();
			for (int vf=0;vf<vertices.length;vf++) {
				for (int vc = 0;vc<vertices.length;vc++) {
					if (vertices[vf].equals(v1)) {
						if(vertices[vc].equals(v2)) {
							matrizAdyacencia[vf][vc]++;
						}
					}
					else if (vertices[vf].equals(v2)) {
						if(vertices[vc].equals(v1)) {
							matrizAdyacencia[vf][vc]++;
						}
					}
				}
			}
		}
		
		
	}
	/**
	 * Llena la array de vertices
	 */
	private void createVertices() {
		for(int l=0;l<vertices.length;l++) {
			vertices[l]= new Vertice();
		}
	}
	
	/**
	 * crea las arrays de aristas según la matriz de incidencia
	 */
	private void createAristas() {
		for (int j=0;j<matrizIncidencia[0].length;j++) {
			Vertice v1=null;
			Vertice v2=null;
			Arista a=null;
			for(int i=0; i<matrizIncidencia.length;i++) {
				if(matrizIncidencia[i][j]==1) {
					if(v1==null) {
						v1=vertices[i];
					}
					else {
						v2=vertices[i];
					}
				}
				else if(matrizIncidencia[i][j]==2) {
					v1=vertices[i];
					v2=v1;
				}
			}
			a=new Arista(v1,v2);
			aristas[j]=a;
		}
	}
	public Vertice[] getVertices() {
		return vertices;
	}
	public void setVertices(Vertice[] vertices) {
		this.vertices = vertices;
	}
	public Arista[] getAristas() {
		return aristas;
	}
	public void setAristas(Arista[] aristas) {
		this.aristas = aristas;
	}
	public int[][] getMatrizAdyacencia() {
		return matrizAdyacencia;
	}

	public void setMatrizAdyacencia(int[][] matrizAdyacencia) {
		this.matrizAdyacencia = matrizAdyacencia;
	}

	public int[][] getMatrizIncidencia() {
		return matrizIncidencia;
	}

	public void setMatrizIncidencia(int[][] matrizIncidencia) {
		this.matrizIncidencia = matrizIncidencia;
	}
	public int getOrden() {
		return this.vertices.length;
	}
	public int getTamanyo() {
		return this.aristas.length;
	}
	public void show(VentanaGrafica2 ven) {
		for(Vertice v:vertices) {
			v.show(ven);
		}
		for(Arista a:aristas) {
			a.show(ven);
		}
		for(Vertice v:vertices) {
			v.show(ven);
		}
		
	}
	@Override
	public String toString() {
		return "Vertices:"+Arrays.toString(vertices)+"\n Aristas:"+Arrays.toString(aristas);
	}
	@Override
	public boolean equals(Object obj) {
		Grafo g=(Grafo) obj;
		return grafosIsomorfos(g);
	}

	private boolean grafosIsomorfos(Grafo g) {
		if (this.getOrden()!=g.getOrden() || this.getTamanyo()!=g.getTamanyo()) {
			return false;
		}
		else {
			//TODO
		}
		
		return false;
	}
	public static void main(String[] args) {
		VentanaGrafica2 ven=new VentanaGrafica2(700, 700, "Grafos");
		int[][] mInc = {{1,0,0,1},
						{1,2,1,1},
						{0,0,1,0}};
		Grafo g1=new Grafo(mInc);
		g1.show(ven);
		System.out.println(g1);

	}


}
