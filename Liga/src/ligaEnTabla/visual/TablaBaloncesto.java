package ligaEnTabla.visual;
import java.awt.Color;
import ligaEnTabla.datos.*;
/**
 * 
 * @author Danel
 *
 */
public class TablaBaloncesto extends Tabla{
	private int[] anch = { 80, 220, 80, 80, 100, 200};
	
	public TablaBaloncesto(Clasificacion clasifs) {
		super(clasifs);
		setAnchuras(anch);
	}
	

	/**
	 * Dibuja la cabezera de la tabla
	 * @param v VentanaGrafica
	 */
	protected void dibujarCabezera(VentanaGrafica v) {
		String[] pal= {"Pos.","Equipo","PF","PC","Dif.Puntos","Part.G/P"};
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
			int ptsF=((EquipoBaloncesto) eqs[i]).getPuntosFavor();
			int ptsC=((EquipoBaloncesto) eqs[i]).getPuntosContra();
			v.dibujaTexto(X_ORIGEN+MARGEN, valorY,Integer.toString(i+1), FUENTE, Color.BLACK);
			v.dibujaTexto(X_ORIGEN+anchuras[0]+MARGEN, valorY, nombre, FUENTE, Color.BLACK);
			v.dibujaTexto(X_ORIGEN+anchuras[0]+anchuras[1]+MARGEN, valorY,Integer.toString(ptsF), FUENTE, Color.GREEN);
			v.dibujaTexto(X_ORIGEN+anchuras[0]+anchuras[1]+anchuras[2]+MARGEN, valorY,Integer.toString(ptsC), FUENTE, Color.RED);
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
		int xOgCasillaDif=X_ORIGEN+anchuras[0]+anchuras[1]+anchuras[2]+anchuras[3];
		int xMedCasillaDif=xOgCasillaDif+(anchuras[4]/2);
		int xOgCasillaGEP=xOgCasillaDif+anchuras[4];
		
		double maxDifPuntoses=buscarMaxDifPuntos();
		
		for(int i=0;i<eqs.length;i++) {
			
			//Diferencia de goles
			double dif=((EquipoBaloncesto) eqs[i]).getPuntosFavor()-((EquipoBaloncesto) eqs[i]).getPuntosContra();
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
			
			//Partidos G/P
			int partG=((EquipoBaloncesto) eqs[i]).getPartidosG();
			int partP=((EquipoBaloncesto) eqs[i]).getPartidosP();
			double anchMax=anchuras[5];
			double partMax=partG+partP;
			double anchG=(partG/partMax)*anchMax;
			double anchP=(partP/partMax)*anchMax;
			v.dibujaRect(xOgCasillaGEP, valorY, anchG, ALTURA_CASILLA, 1.0f, Color.GREEN, Color.GREEN);
			v.dibujaRect(xOgCasillaGEP+anchG, valorY, anchP, ALTURA_CASILLA, 1.0f, Color.RED, Color.RED);
			
			valorY+=ALTURA_CASILLA;
		}
		
	}
	/**
	 * Busca y devuelve la maxima diferencia de goles
	 * @return Maxima diferencia de goles
	 */
	protected double buscarMaxDifPuntos() {
		Equipo[] eqs=clasif.getEquipos();
		double maxDifPuntos=Math.abs(((EquipoBaloncesto) eqs[0]).getPuntosFavor()-((EquipoBaloncesto) eqs[0]).getPuntosContra());
		for (Equipo e:eqs) {
			double difPuntos=Math.abs(((EquipoBaloncesto) e).getPuntosFavor()-((EquipoBaloncesto) e).getPuntosContra());
			if (difPuntos>maxDifPuntos) {
				maxDifPuntos=difPuntos;
			}
		}
		return maxDifPuntos;
	}


}
