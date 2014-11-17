package userModel;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Observable; 

public class NetworkInformation extends Observable {

	/** Singleton **/
	private static NetworkInformation InfoSingleton ;
	
	/** Correspondance entre User et adresse IP **/
	private HashMap<User, InetAddress> usersIPAddress ;
	
	/** Constructeur **/
	private NetworkInformation () { 
		usersIPAddress = new HashMap <User, InetAddress> () ;
	}
	
	/** Methode creant une instance de classe si necessaire et renvoie l'objet**/
	public static NetworkInformation getInstance () {
		if (InfoSingleton == null)
			InfoSingleton = new NetworkInformation () ;
		return InfoSingleton ;
	}
	
	/** Getter du UsersIPAddress **/
	public HashMap<User,InetAddress> getUserList () {
		return usersIPAddress ;		
	}
	
	/** Methode qui crée un User et l'ajoute à la liste  **/
	public User addUser (String nickname, InetAddress ip) {
		User user = new User (nickname) ;
		this.usersIPAddress.put(user, ip) ;
		return user; 
	}
	
	/** Methode qui supprime un User grâce à son adresse IP **/
	public void removeUser (InetAddress ip) {
		this.usersIPAddress.remove(ip) ;
	}
	
	
	/** Methodes en relation avec la classe Observable **/
	
}
