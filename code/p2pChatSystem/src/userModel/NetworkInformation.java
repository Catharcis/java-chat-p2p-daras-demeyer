package userModel;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Observable; 

public class NetworkInformation extends Observable {

	/** Singleton **/
	private NetworkInformation InfoSingleton ;
	
	/** Correspondance entre User et adresse IP **/
	private HashMap<User, InetAddress> usersIPAddress ;
	
	/** Constructeur **/
	private NetworkInformation () { 
		usersIPAddress = new HashMap <User, InetAddress> () ;
	}
	
	/** Methode creant une instance de classe si necessaire et renvoie l'objet**/
	public NetworkInformation getInstance () {
		if (InfoSingleton == null)
			InfoSingleton = new NetworkInformation () ;
		return InfoSingleton ;
	}
	
	/** Getter du UsersIPAddress **/
	public HashMap<User,InetAddress> getUserList () {
		return usersIPAddress ;		
	}
	
	/** Methode pour ajouter un User à la liste 
	 * Elle crée le User **/
	public void addUser (String nickname, InetAddress ip) {
		this.usersIPAddress.put(new User (nickname), ip) ;
	}
	
	/** Methode qui supprime un User grâce à son adresse IP **/
	public void remove (InetAddress ip) {
		this.usersIPAddress.remove(ip) ;
	}
}
