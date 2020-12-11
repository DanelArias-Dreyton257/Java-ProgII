package pr8.datos;
import java.util.ArrayList;
import java.util.Date;
/**
 * 
 * @author Danel
 *
 */
public class User {
	private String nick="";
	private String password = "";
	private long phoneNum = 0;
	private String name = "";
	private String surnames="";
	private Date lastLogIn =null;
	private UserType type = UserType.GUEST;
	private ArrayList<String> emailList = new ArrayList<>();
	/**
	 * Constructor de objeto user
	 * @param nick
	 * @param password
	 * @param phone
	 * @param name
	 * @param surnames
	 */
	public User(String nick,String password, long phone, String name, String surnames){
		setNick(nick);
		setPassword(password);
		setPhoneNum(phone);
		setName(name);
		setSurnames(surnames);
	}
	/**
	 * Constructor de objeto user
	 * @param nick
	 * @param password
	 * @param phone
	 * @param name
	 * @param surnames
	 * @param type
	 */
	public User(String nick,String password, long phone, String name, String surnames, UserType type){
		setNick(nick);
		setPassword(password);
		setPhoneNum(phone);
		setName(name);
		setSurnames(surnames);
		setType(type);
	}
	/**
	 * Constructor de objeto user
	 * @param nick
	 * @param password
	 * @param phone
	 * @param name
	 * @param surnames
	 * @param type
	 * @param emails
	 */
	public User(String nick,String password, long phone, String name, String surnames, UserType type, String ...emails){
		setNick(nick);
		setPassword(password);
		setPhoneNum(phone);
		setName(name);
		setSurnames(surnames);
		setType(type);
		addEmailsToList(emails);
	}
	
	/**
	 * Devuelve el nick del usuario
	 * @return nick
	 */
	public String getNick() {
		return nick;
	}
	/**
	 * Establece el nick del usuario
	 * @param nick
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}
	/**
	 * Devuelve le numero de telefono
	 * @return numTelefono
	 */
	public long getPhoneNum() {
		return phoneNum;
	}
	/**
	 * Establece el numero de telefono
	 * @param phoneNum
	 */
	public void setPhoneNum(long phoneNum) {
		this.phoneNum = phoneNum;
	}
	/**
	 * Devuelve el nombre del usuario
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Establece el nombre del usuario
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Devuelve ls apellidos del usuario
	 * @return surnames
	 */
	public String getSurnames() {
		return surnames;
	}
	/**
	 * Establece los apellidos del usuario
	 * @param surnames
	 */
	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}
	/**
	 * Devuelve la fecha del ultimo log in
	 * @return fecha de ultimo log in
	 */
	public Date getLastLogIn() {
		return lastLogIn;
	}
	/**
	 * Devuelve el tipo de usuario
	 * @return tipo
	 */
	public UserType getType() {
		return type;
	}
	/**
	 * Establece el tipo de usuario
	 * @param type
	 */
	public void setType(UserType type) {
		this.type = type;
	}
	/**
	 * Deuelve una arrayList con los emails del usuario
	 * @return lista de emails
	 */
	public ArrayList<String> getEmailList() {
		return emailList;
	}
	/**
	 * Anyade un email a la lista de emails
	 * @param email
	 */
	public void addEmailToList(String email) {
		if (email.contains("@") && email.contains(".")) emailList.add(email);
	}
	/**
	 * Anyade emails a la lista de emails
	 * @param emails
	 */
	public void addEmailsToList(String ...emails) {
		for (String s:emails) addEmailToList(s);
	}
	/**
	 * Devuelve la contrasenya del usuario
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Establece la contrasenya del usuario
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Cambia la ultima fecha de log in y la establece al momento actual
	 */
	public void updateLastLogInToNow() {
		this.lastLogIn= new Date(System.currentTimeMillis());
	}
	/**
	 * Devuelve un string con todos los emails del usuario
	 * @return
	 */
	public String getEmailListToString() {
		String finalStr="";
		for (int i=0;i<emailList.size();i++) {
			finalStr+=emailList.get(i);
			if (i!=emailList.size()-1) finalStr+=",";
		}
		return finalStr;
	}
	/**
	 * Establece la lista de emails
	 * @param emailList
	 */
	public void setEmailList(ArrayList<String> emailList) {
		this.emailList = emailList;
	}
	@Override
	public String toString() {
		return "User:"+nick+".Real Name:"+name+" "+surnames;
	}
	@Override
	public boolean equals(Object obj) {
		boolean result= false;
		if (obj instanceof User) {
			User u = (User) obj;
			result = this.nick==u.getNick();
		}
		return result;
	}
	@Override
	public int hashCode() {
		return this.nick.hashCode();
	}
	
}
