package proy.unused.pruebas;

public class Nodo {
	int fila;
	int columna;
	public Nodo(int fila, int columna) {
		this.fila=fila;
		this.columna=columna;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Nodo) {
			Nodo n = (Nodo) obj;
			return this.fila == n.fila && this.columna==n.columna;
		}
		else return false;
	}
	@Override
	public String toString() {
		return "N:("+fila+";"+columna+")";
	}

}