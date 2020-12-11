package proy.objectMatrix;

import proy.gameObjects.Zombie;
/**
 * 
 * @author Danel
 *
 */
public class ZombieMatrix extends ObjectMatrix {
	private static final long serialVersionUID = 1L;
	/**
	 * Inicializa la matrix segun la template dada, 1 implica que hay zombie, 0 implica que no hay nada
	 * @param template
	 */
	public ZombieMatrix(int[][] template) {
		super(template);
	}
	/**
	 * Inicializa la matriz vacia según el numero de filas y columnas
	 * @param numRows
	 * @param numColumns
	 */
	public ZombieMatrix(int numRows, int numColumns) {
		super(numRows,numColumns);
	}

	@Override
	/**
	 * Metodo que inicializa la matrix llenandola de zombies segun la template
	 * @param template
	 */
	public void initMatrix(int[][] template) {
		for (int i=0;i<template.length;i++) {
			for(int j=0;j<template[0].length;j++) {
				if (template[i][j]==1) {
					Zombie z = new Zombie(i,j);
					insertObject(z, i, j);
				}
			}
		}
	}
	/**
	 * Metodo que revisa si los zombies estan muertos y los elimina de la matrix
	 */
	public void eraseDeads() {
		for (int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[0].length;j++) {
				Zombie z = (Zombie) matrix[i][j];
				if (z!=null) {
					if(z.isDead()) {
						Zombie.getRndDieSound().playSongInThread();
						eraseObject(i, j);
					}
				}
			}
		}
	}
	/**
	 * Metodo que revisa todos los zombies muertos, recolecta los puntos y los devuelve
	 * @return points
	 */
	public int getPointsFromDeads() {
		int pts=0;
		for (int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[0].length;j++) {
				Zombie z = (Zombie) matrix[i][j];
				if (z!=null) {
					if(z.isDead()) {
						pts+=z.getBrains();
					}
				}
			}
		}
		return pts;
	}
	/**
	 * true si un Zombie ha llegado a la salida del laberinto
	 * @return
	 */
	public boolean ZombieArrived() {
		for (int j=0;j<matrix[0].length;j++) {
			Zombie z = (Zombie) matrix[matrix.length-1][j];
			if (z!=null) {
				return true;
			}
		}
		return false;
	}
	
	public void resetMoved() {
		for (int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[0].length;j++) {
				Zombie z = (Zombie) matrix[i][j];
				if (z!=null) {
					z.setMoved(false);
				}
			}
		}
	}
	
}
