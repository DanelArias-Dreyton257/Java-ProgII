package proy.unused.pruebas;

public class Arista{
	Nodo n1;
	Nodo n2;
	int coste = 1;
	public Arista(Nodo n1, Nodo n2) {
		this.n1=n1;
		this.n2=n2;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Arista) {
			Arista a = (Arista) obj;
			return ((this.n1.equals(a.n1) && this.n2.equals(a.n2)) || (this.n1.equals(a.n2) && this.n2.equals(a.n1))) && this.coste==a.coste;
		}
		else return false;

	}
	@Override
	public String toString() {
		return "A:["+n1+";"+n2+"]";
	}
}
