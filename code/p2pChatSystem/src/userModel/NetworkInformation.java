package userModel;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Observable; 

public class NetworkInformation extends Observable {

	/** Singleton **/
	private static NetworkInformation InfoSingleton ;
	
	/** Correspondance entre User et adresse IP **/
	private HashMap<InetAddress, User> usersIPAddress ;
	
	/** Constructeur **/
	private NetworkInformation () { 
		usersIPAddress = new HashMap <InetAddress, User> () ;
	}
	
	/** Methode creant une instance de classe si necessaire et renvoie l'objet**/
	public static NetworkInformation getInstance () {
		if (InfoSingleton == null)
			InfoSingleton = new NetworkInformation () ;
		return InfoSingleton ;
	}
	
	/** Getter du UsersIPAddress **/
	public HashMap<InetAddress,User> getUserList () {
		return usersIPAddress ;		
	}
	
	/** M�thode qui cr�e un User et l'ajoute � la liste et � la HashMap  **/
	public User addUser (String nickname, InetAddress ip) {
		User user = new User (nickname) ;
		this.usersIPAddress.put(ip, user) ;
		return user; 
	}
	
	/** Methode qui supprime un User grâce à son adresse IP **/
	public void removeUser (InetAddress ip) {
		this.usersIPAddress.remove(ip) ;
	}
	
	/** Methodes en relation avec la classe Observable **/
	
}
