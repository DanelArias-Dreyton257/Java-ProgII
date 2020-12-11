package proy.moreObjects;

import java.io.Serializable;
import java.util.*;
/**
 * 
 * @author Danel
 *
 */
public class RecordHolder implements Serializable{

	private static final long serialVersionUID = 1L;
	//Diferentes mapas que gestionan los diferentes records
	private HashMap<String,Integer> maxZombiesKilled = new HashMap<>();
	private HashMap<String,Integer> maxRoundsCompleted = new HashMap<>();
	private HashMap<String,ArrayList<Boolean>> gamesPlayed = new HashMap<>();
	/**
	 * Constructor del gestor de records
	 */
	public RecordHolder() {

	}
	/**
	 * Anyade un record, si el usuario no habia sido almacenado antes lo guarda automaticamente, si el usuario
	 * ya tenia unos records guardados se comprueba si se han superado uno por uno y si es asi se almacenan
	 * @param user
	 * @param win
	 * @param roundsCompleted
	 * @param zombiesKilled
	 */
	public void addRecord(String user, boolean win, int roundsCompleted, int zombiesKilled) {
		if (maxZombiesKilled.containsKey(user)){
			int prevZombiesKilled=maxZombiesKilled.get(user);
			int prevRoundsComp=maxRoundsCompleted.get(user);
			gamesPlayed.get(user).add(win);
			if (zombiesKilled>prevZombiesKilled) maxZombiesKilled.replace(user, zombiesKilled);
			if (roundsCompleted>prevRoundsComp) maxRoundsCompleted.replace(user, roundsCompleted);
		}
		else {
			maxZombiesKilled.put(user, zombiesKilled);
			maxRoundsCompleted.put(user, roundsCompleted);
			ArrayList<Boolean> a = new ArrayList<>();
			a.add(win);
			gamesPlayed.put(user, a);
		}
	}
	/**
	 * Devuelve el numero de partidas ganadas por el usuario indicado
	 * @param user
	 * @return
	 */
	public int getNumGamesWon(String user) {
		if (gamesPlayed.containsKey(user)) {
			int cont=0;
			for (boolean b: gamesPlayed.get(user)) {
				if (b) cont++;
			}
			return cont;
		}
		else return -1;
	}
	/**
	 * Devuelve el numero de partidas perdidas por el usuario indicado
	 * @param user
	 * @return
	 */
	public int getNumGamesLost(String user) {
		if (gamesPlayed.containsKey(user)) {
			int cont=0;
			for (boolean b: gamesPlayed.get(user)) {
				if (!b) cont++;
			}
			return cont;
		}
		else return -1;
	}
	/**
	 * Devuelve un String con los records del usuario indicado del usuario
	 * @param user
	 * @return
	 */
	public String getInfoOfUser(String user) {
		String str="";
		if (maxZombiesKilled.containsKey(user)) {
			str=user+":\n";
			str+="\tGames Played: "+gamesPlayed.get(user).size()+"\n";
			str+="\t\tWon: "+getNumGamesWon(user)+"\n";
			str+="\t\tLost: "+getNumGamesLost(user)+"\n";
			str+="\tMax. Number of Rounds Completed: "+maxRoundsCompleted.get(user)+"\n";
			str+="\tMax. Number of Zombies Killed: "+maxZombiesKilled.get(user);
		}
		else str="User Not Found";
		return str;
	}
	/**
	 * Devuelve los records de todos los usuarios almacenados
	 * @return
	 */
	public String getInfoOfAllUsers() {
		String str="";
		if (!gamesPlayed.isEmpty()) {
			for (String s: gamesPlayed.keySet()) {
				str+=getInfoOfUser(s)+"\n";
			}
		}
		else str="No user found";
		return str;
	}
	/**
	 * Devuelve una array de Strings con los usuarios almacenados
	 * @return
	 */
	public String[] getUsers() {
		Set<String> keySet= gamesPlayed.keySet();
		String[] users = new String[keySet.size()];
		int i = 0;
		for (String s: keySet) {
			users[i]=s;
			i++;
		}
		return users;
	}
	@Override
	public String toString() {
		return ""+gamesPlayed+maxRoundsCompleted+maxZombiesKilled;
	}
}
