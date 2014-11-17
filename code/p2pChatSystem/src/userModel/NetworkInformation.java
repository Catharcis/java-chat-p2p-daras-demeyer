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
	
	/** Liste de l'ensemble des utilisateurs présents dans la HashMap **/
	private ArrayList<User> listOfUsers;
	
	/** Correspondance entre User et adresse IP **/
	private HashMap<User, InetAddress> usersIPAddress ;
	
	/** Constructeur **/
	private NetworkInformation () { 
		listOfUsers = new ArrayList<User>();
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
	
	/** Méthode qui crée un User et l'ajoute à la liste et à la HashMap  **/
	public User addUser (String nickname, InetAddress ip) {
		User user = new User (nickname) ;
		this.usersIPAddress.put(user, ip) ;
		this.listOfUsers.add(user);
		return user; 
	}
	
	/** Methode qui supprime un User grÃ¢ce Ã  son adresse IP **/
	public void removeUser (InetAddress ip) {
		User user = getUserWithInetAddress(ip);
		this.usersIPAddress.remove(ip) ;
		this.listOfUsers.remove(listOfUsers.indexOf(user));
	}
	
	/** Méthode qui permet de récupérer l'adresse IP connaissant le nickname de l'utilisateur **/
	public InetAddress getIPAddressWithNickname(String nickname){
		User user = null;
		int i = 0;
		while (i < listOfUsers.size() && user == null){
			if (listOfUsers.get(i).getNickname() == nickname){
				user = listOfUsers.get(i);
			}
			i++;
		}
		return usersIPAddress.get(user);
	}
	
	/** Méthode qui permet de récupérer l'adresse IP connaissant le nickname de l'utilisateur **/
	public User getUserWithNickname(String nickname){
		User user = null;
		int i = 0;
		while (i < listOfUsers.size() && user == null){
			if (listOfUsers.get(i).getNickname() == nickname){
				user = listOfUsers.get(i);
			}
			i++;
		}
		return user;
	}
	
	/** Méthode qui permet de récupérer le user connaissant l'adresse IP **/
	public User getUserWithInetAddress(InetAddress ip){
		User user = null;
		Iterator<Entry<User, InetAddress>> it = usersIPAddress.entrySet().iterator();
		while (it.hasNext() && user == null){
			Entry<User,InetAddress> entry = it.next();
			if (entry.getValue().equals(ip)){
				user = entry.getKey();
			}
		}
		return user;
	}
	
	/** Methodes en relation avec la classe Observable **/
	
}
