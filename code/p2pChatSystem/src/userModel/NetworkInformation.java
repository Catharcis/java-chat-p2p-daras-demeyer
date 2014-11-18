package userModel;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Observable; 

public class NetworkInformation extends Observable {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	/** Singleton **/
	private static NetworkInformation InfoSingleton ;
	
	/** Local User **/
	private User localUser ;
	
	/** Correspondance entre User et adresse IP **/
	private HashMap<InetAddress, User> usersIPAddress ;
	
	
	/************************************************* 
	 * 					CONSTRUCTOR
	 ************************************************/
	/** Constructeur **/
	private NetworkInformation () { 
		usersIPAddress = new HashMap <InetAddress, User> () ;
		this.localUser = null ;
	}
	
	/** Methode creant une instance de classe si necessaire et renvoie l'objet**/
	public static NetworkInformation getInstance () {
		if (InfoSingleton == null)
			InfoSingleton = new NetworkInformation () ;
		return InfoSingleton ;
	}
	
	
	
	/************************************************* 
	 * 				GETTERS & SETTERS 
	 ************************************************/
	
	/** Getter du UsersIPAddress **/
	public HashMap<InetAddress,User> getUserList () {
		return usersIPAddress ;		
	}

	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/
	
	/** Methode qui cree un User et l'ajoute a la HashMap  **/
	public User addUser (String nickname, InetAddress ip) {
		User user = new User (nickname) ;
		this.usersIPAddress.put(ip, user) ;
		return user; 
	}
	
	/** Methode qui supprime un User grace a  son adresse IP **/
	public void removeUser (InetAddress ip) {
		this.usersIPAddress.remove(ip) ;
	}
	
	/** Méthode qui récupère l'adresse IP d'un utilisateur **/
	public InetAddress getIPAddressOfUser(User user){
		InetAddress ip = null;
		Iterator<Entry<InetAddress, User>> it = usersIPAddress.entrySet().iterator();
		while (it.hasNext() && ip == null){
			Entry<InetAddress,User> entry = it.next();
			if (entry.getValue().getIdUser() == user.getIdUser()){
				ip = entry.getKey();
			}
		}
		return ip;
	}
	
	/** Methodes en relation avec la classe Observable **/
	
}
