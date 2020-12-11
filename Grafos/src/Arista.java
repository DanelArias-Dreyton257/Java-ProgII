
public class Arista extends GraphObject{
	private Vertice v1;
	private Vertice v2;
	private boolean bucle=false;
	private static final double RADIO_BUCLE=15;
	
	Arista(Vertice v1,Vertice v2){
		setV1(v1);
		setV2(v2);
		if (v1.equals(v2)) {
			bucle=true;
		}
	}

	public Vertice getV1() {
		return v1;
	}

	public void setV1(Vertice v1) {
		this.v1 = v1;
	}

	public Vertice getV2() {
		return v2;
	}

	public void setV2(Vertice v2) {
		this.v2 = v2;
	}
	public boolean isBucle() {
		return bucle;
	}

	public void setBucle(boolean bucle) {
		this.bucle = bucle;
	}

	/**
	 * Dibuja la arista, conectando los vertices, en caso de bucle lo dibuja en forma de circulo
	 * @param ven Objeto VentanaGrafica2
	 */
	public void show(VentanaGrafica2 ven) {
		if (this.bucle) {
			ven.dibujaCirculo(v1.getX(), v1.getY()-RADIO_BUCLE, RADIO_BUCLE, GROSOR, color);
		}
		else{
			ven.dibujaLineaCurva(v1.getX(), v1.getY(), v2.getX(), v2.getY(), GROSOR, color);
		}
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Arista)) return false;
		Arista a = (Arista)obj;
		return this.v1.equals(a.getV1()) && this.v2.equals(a.getV2());
	}
	@Override
	public String toString() {
		return "A: "+this.v1.toString()+" to "+this.v2.toString();
	}

}
