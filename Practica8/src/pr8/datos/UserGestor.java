package pr8.datos;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Danel
 *
 */
public class UserGestor {
	ArrayList<User> users = new ArrayList<>();
	/**
	 * Constructor de gestor de usuarios
	 */
	public UserGestor() {
	}
	/**
	 * Constructor de gestor de usuarios
	 * @param users
	 */
	public UserGestor(User ...users) {
		addUsers(users);
	}
	/**
	 * Devuelve el usuario del indice especificado, si no encuentra devuelve null
	 * @param index
	 * @return user
	 */
	public User getUser(int index) {
		User u = null;
		if (index>=0 && index<users.size()) u=users.get(index);
		return u;
	}
	/**
	 * Busca un usuario con el nick pasado como parametro, si no encuentra devuelve null
	 * @param nick
	 * @return user
	 */
	public User getUserByNick(String nick) {
		User foundUser = null;
		for (User us:users) if (us.getNick().equalsIgnoreCase(nick)) foundUser=us;
		return foundUser;
	}
	/*Busca un usuario con el name pasado como parametro, si no encuentra devuelve null
	 * @param name
	 * @return user
	 */
	public User getUserByName(String name) {
		User foundUser = null;
		for (User us:users) if (us.getName().equalsIgnoreCase(name)) foundUser=us;
		return foundUser;
	}
	/**
	 * Devuelve el numero de usuarios que hay en el gestor
	 * @return
	 */
	public int getNumUsers() {
		return users.size();
	}
	/**
	 * Anyade usuarios al gestor
	 * @param users
	 */
	public void addUsers(User ...users) {
		for (User u: users) this.users.add(u);
	}
	/**
	 * Anyade "numUsers" usuarios de prueba al gestor
	 * @param numUsers
	 */
	public void addTestUsers(int numUsers) {
		Random randomGen = new Random();
		for (int i = 0; i < numUsers; i++) {
			String num = String.format( "%1$04d", i );
			int tu = randomGen.nextInt( 5 );
			int telef = randomGen.nextInt( 999999999 );
			User u = new User( "nick"+num, "pass"+num,  telef, "name"+num, "sur"+num, UserType.values()[tu], num+"@email.com" );
			addUsers(u);
		 } 
	}
	/**
	 * Elimina usuarios del gestor si los encuentra
	 * @param users
	 */
	public void removeUsers(User ...users) {
		for (User u: users) if (this.users.contains(u)) this.users.remove(u);	
	}
	/**
	 * Devuelve el indice en el gestor del usuario si lo encuentra, sino devuelve -1
	 * @param u
	 * @return
	 */
	public int getIndexOfUser(User u) {
		if (!users.contains(u)) return -1;
		return users.indexOf(u);
	}
}
