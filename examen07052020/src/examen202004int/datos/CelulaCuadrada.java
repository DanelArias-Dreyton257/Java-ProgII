package examen202004int.datos;

public class CelulaCuadrada extends Celula {

	private static final long serialVersionUID = 1L;

	public CelulaCuadrada(Mundo mundo, int fila, int columna) {
		super(mundo, fila, columna);
	}
	
	@Override
	public void dibujar() {
		if (estado.getColor()!=null) {  // Si el color es nulo no se dibuja
			mundo.getVentana().dibujaRect(x, y,mundo.getTamCasilla(), mundo.getTamCasilla(), 0f,estado.getColor(),estado.getColor());
		}
	}
}
