import pr8.datos.User;
import pr8.datos.UserGestor;
import pr8.datos.UserType;
import pr8.ventanas.VentanaUsuario;

public class Main {
	 public static void main(String[] args){
		 
		 //Creo 3 usuarios
		 User u = new User("D-Admin", "123456789", 123456789, "Danel", "Arias Alamo", UserType.ADMIN);
		 u.addEmailsToList("amigo@prueba.es","quehacesleyendoesto@tontoquienlolea.org");
		 u.updateLastLogInToNow();
		 
		 User u2 = new User("Teacher", "123456789", 987654321, "Andoni", "Eguiluz", UserType.MODERATOR);
		 u2.addEmailsToList("amigo@prueba.es","quehacesleyendoesto@tontoquienlolea.org");
		 u2.updateLastLogInToNow();
		 
		 User u3 = new User("Invitado1", "123456789", 666666666, "Solomeo", "Paredes", UserType.GUEST);
		 u3.addEmailsToList("amigo@prueba.es","quehacesleyendoesto@tontoquienlolea.org");
		 u3.updateLastLogInToNow();
		 
		 UserGestor ug = new UserGestor(u,u2,u3);
		 
		 ug.addTestUsers(50);
		 
		 //Creacion de ventana
		 VentanaUsuario v = new VentanaUsuario();
		 //Anyade los usuarios a la JList de la ventana
		 v.addUsersToList(ug);
		 //v.showMessage("The window should be working");
		 v.setVisible(true);
		 
	}
}
