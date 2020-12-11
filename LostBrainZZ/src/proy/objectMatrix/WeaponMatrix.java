package proy.objectMatrix;

import proy.gameObjects.*;
/**
 * 
 * @author Danel
 *
 */
public class WeaponMatrix extends ObjectMatrix {
	private static final long serialVersionUID = 1L;
	/**
	 * Inicializa la matrix segun la template dada, segun el numero implica un tipo de arma, 0 implica que no hay nada
	 * @param template
	 */
	public WeaponMatrix(int[][] template) {
		super(template);
	}
	/**
	 * Inicializa la matriz vacia segun el numero de filas y columnas
	 * @param numRows
	 * @param numColumns
	 */
	public WeaponMatrix(int numRows, int numColumns) {
		super(numRows,numColumns);
	}

	@Override
	/**
	 * Metodo que inicializa la matrix llenandola de armas segun la template
	 * @param template
	 */
	public void initMatrix(int[][] template) {
		for (int i=0;i<template.length;i++) {
			for(int j=0;j<template[0].length;j++) {
				Weapon w = null;
				switch(template[i][j]) {
				case 0:
					break;
				case 1:
					w = new Turret(i,j);
				case 2:
					w = new Bomb(i,j);
				case 3:
					w = new ElectricWeapon(i, j);
				}
				insertObject(w, i, j);
			}
		}

	}
	/**
	 * Metodo que revisa si las armas estan "muertas" y las elimina de la matrix
	 */
	public void eraseDeads() {
		for (int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[0].length;j++) {
				Weapon w = (Weapon) matrix[i][j];
				if (w!=null) {
					if(w.isDead()) {
						eraseObject(i, j);
					}
				}
			}
		}
	}
	/**
	 * Metodo que revisa si hay zombies que estan al alcance de las armas y si es asi, les hace danyo y estropea las armas
	 */
	public void hurtZombies(ZombieMatrix mZ) {
		for (int i=0;i<matrix.length;i++) {
			for (int j=0;j<matrix[0].length;j++) {
				Weapon w = (Weapon) matrix[i][j];
				if (w!=null) {
					boolean done = false;
					//Calculo el area rectangular a la que podria llegar
					int minCol=w.getColumn()-w.getReach();
					int minRow=w.getRow()-w.getReach();
					int maxCol=w.getColumn()+w.getReach();
					int maxRow=w.getRow()+w.getReach();
					//Si alguno los limites del area ectangular se salen del tablero se corrigen
					if (minRow<0) minRow=0;
					if (minCol<0) minCol=0;
					if (maxRow>=matrix.length) maxRow=matrix.length-1;
					if (maxCol>=matrix[0].length) maxCol=matrix[0].length-1;

					int minColEx=minCol-w.getColumn();
					int maxColEx=maxCol-w.getColumn();
					int minRowEx=minRow-w.getRow();
					int maxRowEx=maxRow-w.getRow();

					for (int colEx=minColEx;colEx<=maxColEx;colEx++) {
						for (int rowEx=minRowEx;rowEx<=maxRowEx;rowEx++) {
							//Si la suma de los desplazamientos es mayor que el alcance, el alcance no es suficiente
							if (Math.abs(rowEx)+Math.abs(colEx)<=w.getReach()) {
								Zombie z =(Zombie) mZ.getObject(w.getRow()+rowEx, w.getColumn()+colEx);
								if (z!=null) {

									z.hurt(w.getAttack());//Hace el danyo pertinente al zombie

									//Comprueba si el arma a sido dispara ya si el arma es de unsolo disparo para no sonar otra vez
									if (!w.isOneShot() || (w.isOneShot() && !done)) {
										w.getAttackSound().playSongInThread();
										done = true;
									}

									w.depreciate(); //El arma se estropea
								}
							}
						}
					}
				}
			}
		}
	}
}
