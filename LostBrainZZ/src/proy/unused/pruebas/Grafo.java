package proy.unused.pruebas;

import java.util.*;

public class Grafo {
	ArrayList<Nodo> vertices = new ArrayList<>();
	ArrayList<Arista>aristas = new ArrayList<>();
	public Grafo() {
		// TODO Auto-generated constructor stub
	}
	public int getOrden() {
		return vertices.size();//Numero de Vertices
	}
	public int getTamanyo() {
		return aristas.size();//Numero de Vertices
	}
	public boolean tieneAristaDe(Nodo n1, Nodo n2) {
		Arista aN = new Arista(n1, n2);
		boolean result = false;
		for (Arista aT: aristas) {
			if (aT.equals(aN)) result= true;
		}
		return result;
	}
	@Override
	public String toString() {
		return "Vertices:"+vertices+"\nAristas:"+aristas;
	}
	public Nodo getNodo(int fila, int columa) {
		return null;
	}

	public void generarGrid(int numFilas, int numColumnas) {
		//FALTAN ARISTAS
		Nodo[][] grid = new Nodo[numFilas][numColumnas];
		for (int f = 0; f<numFilas;f++) {
			for (int c = 0; c<numColumnas;c++) {
				Nodo nuevoN = new Nodo(f,c);
				vertices.add(nuevoN);
				grid[f][c] = nuevoN;
			}
		}
		for (int f = 0; f<numFilas;f++) {
			for (int c = 0; c<numColumnas-1;c++) {
				aristas.add( new Arista(grid[f][c], grid[f][c+1]));
			}
		}
		for (int c = 0; c<numColumnas;c++) {
			for (int f = 0; f<numFilas-1;f++) {
				aristas.add( new Arista(grid[f][c], grid[f+1][c]));
			}
		}
	}


	public static void Dijkstra(Grafo g, Nodo salida){
		//Distancia de la salida al resto de nodos
		int[] distancias = new int[g.getOrden()];
		for (int i=0; i<g.getOrden();i++) distancias[i]=Integer.MAX_VALUE;
		//Si los nodos han sido vistos
		boolean[] vistos = new boolean[g.getOrden()];
		for (int i=0; i<g.getOrden();i++) vistos[i]=false;

		for (int wInd=0; wInd<g.getOrden();wInd++) {
			//No existe arista entre w y salida
			if (!g.tieneAristaDe(g.vertices.get(wInd), salida)) {
				distancias[wInd]= Integer.MAX_VALUE;
			}
			else {
				distancias[wInd] = g.getArista(g.vertices.get(wInd), salida).coste;
			}
			if (g.vertices.get(wInd).equals(salida)) {
				distancias[wInd]=0;
				vistos[wInd]=true;
			}
		}
		while (!g.sonTodosTrue(vistos)) {
			int indNodo = g.indMinDistNoVisto(distancias,vistos);
			//if (indNodo==-1) break;
			Nodo v = g.vertices.get(indNodo);
			vistos[indNodo]= true;
			int[] indsucesores = g.sucesores(v);
			for (int wInd: indsucesores) {
				if (distancias[wInd]>distancias[indNodo]+g.getArista(g.vertices.get(wInd), v).coste) {
					distancias[wInd]=distancias[indNodo]+g.getArista(g.vertices.get(wInd), v).coste;
				}
			}

		}
		for (int i=0;i<distancias.length;i++) {
			System.out.println("Ruta:" + salida +"->" + g.vertices.get(i)+": "+distancias[i]);
		}

	}
	private Arista getArista(Nodo n1, Nodo n2) {
		Arista aN = new Arista(n1, n2);
		Arista result = null;
		for (Arista aT: aristas) {
			if (aT.equals(aN)) result= aT;
		}
		return result;
	}
	private ArrayList<Arista> getAristas(Nodo n){
		ArrayList<Arista> aristas = new ArrayList<>();
		for (Arista a: this.aristas) {
			if (a.n1.equals(n) || a.n2.equals(n)) {
				aristas.add(a);
			}
		}
		return aristas;
	}
	private int[] sucesores(Nodo v) {
		ArrayList<Arista> arConV = getAristas(v);
		ArrayList<Nodo> adyacentesV = new ArrayList<>();
		for (Arista a: arConV) {
			if (a.n1.equals(v)) {
				adyacentesV.add(a.n2);
			}
			else if (a.n2.equals(v)) {
				adyacentesV.add(a.n1);
			}
		}
		int[] r = new int[adyacentesV.size()];
		int cont = 0;
		for (int i = 0;i<getOrden();i++) {
			for (Nodo n: adyacentesV) {
				if (vertices.get(i).equals(n)) {
					r[cont] = i;
					cont++;
				}
			}

		}
		return r;
	}
	private int indMinDistNoVisto(int[] dists, boolean[] vistos) {
		ArrayList<Integer> indNoVistas = new ArrayList<>();
		ArrayList<Integer> dsNoVistas = new ArrayList<>();
		for (int b=0;b<vistos.length;b++) {
			if (!vistos[b]) {
				dsNoVistas.add(dists[b]);
				indNoVistas.add(b);
			}
		}
		int min = Collections.min(dsNoVistas);
		for (int i =0; i<dists.length;i++) {
			if (indNoVistas.contains(i)) {
				if (dists[i]==min) {
					return i;
				}
			}
		}
		return -1;
	}
	private boolean sonTodosTrue(boolean[] bs) {
		for (boolean b:bs) if (!b) return false;
		return true;
	}

	public static void main(String[] args) {
		Grafo g = new Grafo();
		g.generarGrid(10, 10);
		System.out.println(g);
		Grafo.Dijkstra(g, new Nodo(5,5));

	}

}
