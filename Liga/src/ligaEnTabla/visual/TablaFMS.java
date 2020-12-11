package ligaEnTabla.visual;
import java.awt.Color;
import ligaEnTabla.datos.*;
/**
 * 
 * @author Danel
 *
 */
public class TablaFMS extends Tabla{
	private int[] anch = { 80, 180, 80, 80, 200};
	
	public TablaFMS(Clasificacion clasifs) {
		super(clasifs);
		setAnchuras(anch);
	}
	

	/**
	 * Dibuja la cabezera de la tabla
	 * @param v VentanaGrafica
	 */
	protected void dibujarCabezera(VentanaGrafica v) {
		String[] pal= {"Pos.","MC","Ptos.","PB","Bat.G/G.Rép/P.Rép/P"};
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
			int PBs=((EquipoFMS) eqs[i]).getPuntosBatalla();
			v.dibujaTexto(X_ORIGEN+MARGEN, valorY,Integer.toString(i+1), FUENTE, Color.BLACK);
			v.dibujaTexto(X_ORIGEN+anchuras[0]+MARGEN, valorY, nombre, FUENTE, Color.BLACK);
			v.dibujaTexto(X_ORIGEN+anchuras[0]+anchuras[1]+MARGEN, valorY,Integer.toString(pts), FUENTE, Color.BLACK);
			v.dibujaTexto(X_ORIGEN+anchuras[0]+anchuras[1]+anchuras[2]+MARGEN, valorY,Integer.toString(PBs), FUENTE, Color.GREEN);
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
		int xOgCasillaGEP=X_ORIGEN+anchuras[0]+anchuras[1]+anchuras[2]+anchuras[3];		
		for(int i=0;i<eqs.length;i++) {
			//Partidos G/GRep/PRep/P
			int partG=((EquipoFMS) eqs[i]).getPartidosG();
			int partGR=((EquipoFMS) eqs[i]).getPartidosGRep();
			int partPR=((EquipoFMS) eqs[i]).getPartidosPRep();
			int partP=((EquipoFMS) eqs[i]).getPartidosP();
			double anchMax=anchuras[4];
			double partMax=partG+partP+partGR+partPR;
			double anchG=(partG/partMax)*anchMax;
			double anchGR=(partGR/partMax)*anchMax;
			double anchPR=(partPR/partMax)*anchMax;
			double anchP=(partP/partMax)*anchMax;
			v.dibujaRect(xOgCasillaGEP, valorY, anchG, ALTURA_CASILLA, 1.0f, Color.BLUE, Color.BLUE);
			v.dibujaRect(xOgCasillaGEP+anchG, valorY, anchGR, ALTURA_CASILLA, 1.0f, Color.GREEN, Color.GREEN);
			v.dibujaRect(xOgCasillaGEP+anchG+anchGR, valorY, anchPR, ALTURA_CASILLA, 1.0f, Color.YELLOW, Color.YELLOW);
			v.dibujaRect(xOgCasillaGEP+anchG+anchGR+anchPR, valorY, anchP, ALTURA_CASILLA, 1.0f, Color.RED, Color.RED);
			
			valorY+=ALTURA_CASILLA;
		}
		
	}


}
