package proy.moreObjects;

import java.io.Serializable;
import java.util.TreeSet;

import proy.gameObjects.Zombie;
/**
 * 
 * @author Danel
 *
 */
public class ZombieHorde implements Comparable<ZombieHorde>,Serializable{

	private static final long serialVersionUID = 1L;
	TreeSet<Zombie>horde=new TreeSet<>();

	/**
	 * Devuelve el primer zombie de la horda
	 * @return
	 */
	public Zombie getFirstZombie() {
		return horde.first();
	}
	/**
	 * Devuelve el primer zombie de la horda y LO ELIMINA de la horda
	 * @return
	 */
	public Zombie pollFirstZombie() {
		return horde.pollFirst();
	}
	/**
	 * Devuelve la dificultad de la horda
	 * @return
	 */
	public int getDifficulty() {
		int cont = 0;
		for (Zombie z: horde) cont+=z.getDifficulty();
		return cont;
	}
	/**
	 * Anyade un zombie a la horda
	 * @param z
	 */
	public void addZombie(Zombie z) {
		horde.add(z);
	}
	/**
	 * Elimina un zombie de la horda
	 * @param z
	 */
	public void deleteZombie(Zombie z) {
		horde.remove(z);
	}
	/**
	 * Devuelve la cantidad de zombies de la horda
	 * @return
	 */
	public int getSize() {
		return horde.size();
	}
	/**
	 * Añade numZombies Zombies a la horda
	 * @param numZombies
	 */
	public void fillHorde(int numZombies) {
		while(horde.size()<numZombies) addZombie(new Zombie(0, 0));
	}
	/**
	 * Devuelve true si la horda no contiene ningun zombie
	 * @return
	 */
	public boolean isEmpty() {
		return horde.isEmpty();
	}

	@Override
	public String toString() {
		return "Horde[Diff: "+getDifficulty()+", "+horde.toString()+"]";
	}
	@Override
	public int compareTo(ZombieHorde o) {
		return this.getDifficulty()-o.getDifficulty();
	}
}
