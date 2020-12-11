package proy.objectMatrix;

import proy.gameObjects.*;
/**
 * 
 * @author Danel
 *
 */
public class LabNodeMatrix extends ObjectMatrix {
	private static final long serialVersionUID = 1L;
	/**
	 * Inicializa la matrix segun la template dada, 1 implica que hay pared, 0 implica que hay camino
	 * @param template
	 */
	public LabNodeMatrix(int[][] template) {
		super(template);
	}

	@Override
	/**
	 * Metodo que inicializa la matrix llenandola de nodos segun la template
	 * @param template
	 */
	public void initMatrix(int[][] template) {
		for (int i=0;i<template.length;i++) {
			for(int j=0;j<template[0].length;j++) {
				if (template[i][j]==1){
					Wall w =new Wall(i,j);
					insertObject(w, i, j);
				}
				else {
					Path p = new Path(i,j);
					insertObject(p, i, j);
				}	
			}
		}
	}
	/**
	 * Devuelve el numero de objetos Path que hay en la matriz
	 * @return
	 */
	public int getNumPathNodes() {
		int cont=0;
		for (GameObject[] arr: matrix) {
			for (GameObject obj : arr) {
				if (obj instanceof Path) {
					cont++;
				}
			}
		}
		return cont;
	}
}
