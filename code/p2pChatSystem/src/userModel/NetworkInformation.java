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
	
	/** Mï¿½thode qui crï¿½e un User et l'ajoute ï¿½ la liste et ï¿½ la HashMap  **/
	public User addUser (String nickname, InetAddress ip) {
		User user = new User (nickname) ;
		this.usersIPAddress.put(ip, user) ;
		return user; 
	}
	
	/** Methode qui supprime un User grÃ¢ce Ã  son adresse IP **/
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
