package ligaEnTabla.visual;
import java.awt.Color;
import ligaEnTabla.datos.*;
/**
 * 
 * @author Danel
 *
 */
public class TablaFutbol extends Tabla{
	private int[] anch = { 80, 180, 80, 80, 80, 100, 200};
	
	public TablaFutbol(Clasificacion clasifs) {
		super(clasifs);
		setAnchuras(anch);
	}
	

	/**
	 * Dibuja la cabezera de la tabla
	 * @param v VentanaGrafica
	 */
	protected void dibujarCabezera(VentanaGrafica v) {
		String[] pal= {"Pos.","Equipo","Ptos.","GF","GC","Dif.Goles","Part.G/E/P"};
		int valorX=X_ORIGEN;
		for (int i=0;i<anchuras.length;i++) {
			v.dibujaTexto(valorX, Y_ORIGEN-MARGEN, pal[i], FUENTE, Color.BLACK);
			valorX+=anchuras[i];
		}
		
	}
	/**
	 * Dibuja los contenidos de casillas que tienen texto
	 * @param v VentanaGrafica
	 */
	protected void dibujarTexto(VentanaGrafica v) {
		Equipo[] eqs=clasif.getEquipos();
		int valorY=Y_ORIGEN+ALTURA_CASILLA-MARGEN;
		for(int i=0;i<eqs.length;i++) {
			String nombre=eqs[i].getNombre();
			int pts=eqs[i].getPuntos();
			int golF=((EquipoFutbol) eqs[i]).getGolesFavor();
			int golC=((EquipoFutbol) eqs[i]).getGolesContra();
			v.dibujaTexto(X_ORIGEN+MARGEN, valorY,Integer.toString(i+1), FUENTE, Color.BLACK);
			v.dibujaTexto(X_ORIGEN+anchuras[0]+MARGEN, valorY, nombre, FUENTE, Color.BLACK);
			v.dibujaTexto(X_ORIGEN+anchuras[0]+anchuras[1]+MARGEN, valorY,Integer.toString(pts), FUENTE, Color.BLACK);
			v.dibujaTexto(X_ORIGEN+anchuras[0]+anchuras[1]+anchuras[2]+MARGEN, valorY,Integer.toString(golF), FUENTE, Color.GREEN);
			v.dibujaTexto(X_ORIGEN+anchuras[0]+anchuras[1]+anchuras[2]+anchuras[3]+MARGEN, valorY,Integer.toString(golC), FUENTE, Color.RED);
			valorY+=ALTURA_CASILLA;
		}
		
	}
	/**
	 * Dibuja los contenidos de casillas que tienen graficos
	 * @param v VentanaGrafica
	 */
	protected void dibujarGraficos(VentanaGrafica v) {
		Equipo[] eqs=clasif.getEquipos();
		int valorY=Y_ORIGEN;
		int xOgCasillaDif=X_ORIGEN+anchuras[0]+anchuras[1]+anchuras[2]+anchuras[3]+anchuras[4];
		int xMedCasillaDif=xOgCasillaDif+(anchuras[5]/2);
		int xOgCasillaGEP=xOgCasillaDif+anchuras[5];
		
		double maxDifPuntoses=buscarMaxDifPuntos();
		
		for(int i=0;i<eqs.length;i++) {
			
			//Diferencia de goles
			double dif=((EquipoFutbol) eqs[i]).getGolesFavor()-((EquipoFutbol) eqs[i]).getGolesContra();
			double anchMaxDif=xMedCasillaDif-xOgCasillaDif;
			if (dif>=0) {
				double anch=(dif/maxDifPuntoses)*anchMaxDif;
				v.dibujaRect(xMedCasillaDif, valorY, anch, ALTURA_CASILLA, 1.0f, Color.GREEN,Color.GREEN);
			}
			else {
				dif=dif*-1;
				double anch=(dif/maxDifPuntoses)*anchMaxDif;
				v.dibujaRect(xMedCasillaDif-anch, valorY, anch, ALTURA_CASILLA, 1.0f, Color.RED, Color.RED);
			}
			
			//Partidos G/E/P
			int partG=((EquipoFutbol) eqs[i]).getPartidosG();
			int partE=((EquipoFutbol) eqs[i]).getPartidosE();
			int partP=((EquipoFutbol) eqs[i]).getPartidosP();
			double anchMax=anchuras[6];
			double partMax=partG+partE+partP;
			double anchG=(partG/partMax)*anchMax;
			double anchE=(partE/partMax)*anchMax;
			double anchP=(partP/partMax)*anchMax;
			v.dibujaRect(xOgCasillaGEP, valorY, anchG, ALTURA_CASILLA, 1.0f, Color.GREEN, Color.GREEN);
			v.dibujaRect(xOgCasillaGEP+anchG, valorY, anchE, ALTURA_CASILLA, 1.0f, Color.YELLOW, Color.YELLOW);
			v.dibujaRect(xOgCasillaGEP+anchG+anchE, valorY, anchP, ALTURA_CASILLA, 1.0f, Color.RED, Color.RED);
			
			valorY+=ALTURA_CASILLA;
		}
		
	}

	/**
	 * Busca y devuelve la maxima diferencia de goles
	 * @return Maxima diferencia de goles
	 */
	protected double buscarMaxDifPuntos() {
		Equipo[] eqs=clasif.getEquipos();
		double maxDifPuntos=Math.abs(((EquipoFutbol) eqs[0]).getGolesFavor()-((EquipoFutbol) eqs[0]).getGolesContra());
		for (Equipo e:eqs) {
			double difPuntos=Math.abs(((EquipoFutbol) e).getGolesFavor()-((EquipoFutbol) e).getGolesContra());
			if (difPuntos>maxDifPuntos) {
				maxDifPuntos=difPuntos;
			}
		}
		return maxDifPuntos;
	}


}
