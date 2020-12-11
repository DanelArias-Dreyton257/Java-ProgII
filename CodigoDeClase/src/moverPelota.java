import java.awt.Color;

public class moverPelota {
	public static void main(String[] args) {
		VentanaGrafica v = new VentanaGrafica(1000, 1000, "Pelotas");
		Pelota pel = new Pelota(50, 70, 150, Color.BLUE);
		Pelota pel2 = new Pelota(50, 100, 300, Color.GREEN);
		Pelota pel3 = new Pelota(50, 170, 200, Color.RED);
		int ciclos=2300000;
		pel.dibuja(v);
		pel2.dibuja(v);
		pel3.dibuja(v);
		v.espera(200);
		double incX1=0.15;
		double incY1=0.05;
		
		double incX2=0.10;
		double incY2=0.10;
		
		double incX3=0.05;
		double incY3=0.15;
		
		for(int i=0;i<ciclos;i++) {
			if (pel.isColisionVentanaX(v)) {
				incX1=-incX1;
			}
			if (pel.isColisionVentanaY(v)) {
				incY1=-incY1;
			}
			if (pel2.isColisionVentanaX(v)) {
				incX2=-incX2;
			}
			if (pel2.isColisionVentanaY(v)) {
				incY2=-incY2;
			}
			if (pel3.isColisionVentanaX(v)) {
				incX3=-incX3;
			}
			if (pel3.isColisionVentanaY(v)) {
				incY3=-incY3;
			}
			if (chocanPelotas(pel,pel2)) {
				double temp1=incX1;
				double temp2=incY1;
				
				incX1=incX2;
				incY1=incY2;
				
				incX2=temp1;
				incY2=temp2;
						
			}
			if (chocanPelotas(pel2,pel3)) {
				double temp1=incX2;
				double temp2=incY2;
				
				incX2=incX3;
				incY2=incY3;
				
				incX3=temp1;
				incY3=temp2;
						
			}
			if (chocanPelotas(pel,pel3)) {
				double temp1=incX1;
				double temp2=incY1;
				
				incX1=incX3;
				incY1=incY3;
				
				incX3=temp1;
				incY3=temp2;
						
			}
			pel.moverPorCiclo(v, incX1, incY1);
			pel2.moverPorCiclo(v, incX2, incY2);
			pel3.moverPorCiclo(v, incX3, incY3);
			v.espera(2000/ciclos);
		}
	}
	public static boolean chocanPelotas(Pelota p1, Pelota p2) {
		boolean result=false;
		double X = Math.abs(p1.getX()-p2.getX());
		double Y = Math.abs(p1.getY()-p2.getY());
		double dist =Math.sqrt( (X*X)+(Y*Y));
		if (dist<=p1.getRadio()+p2.getRadio()) {
			result = true;
		}
		return result;
	}
}
