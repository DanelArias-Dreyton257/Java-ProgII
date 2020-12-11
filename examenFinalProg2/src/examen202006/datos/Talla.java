package examen202006.datos;
/**
 * 
 * @author Danel
 *
 */
public enum Talla {
	 XS, S, M, X, XL, XXL;
	 /**
	  * Devuelve el porcentaje del precio de cada una
	  * @return double que represente el porcentaje
	  */
	 public double getPorcentaje() {
		 if (this == XS) return 0;
		 else if (this == S) return 1;
		 else if (this == M) return 2;
		 else if (this == X) return 3;
		 else if (this == X) return 4;
		 else return 5;
	}
	
}
	

