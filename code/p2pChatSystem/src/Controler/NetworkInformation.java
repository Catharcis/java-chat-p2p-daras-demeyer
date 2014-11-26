package Controler;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Observable; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import GUI.ConnectDisconnectFenetre;
import Signals.AbstractMessage;
import Signals.Hello;

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
	
	/** Singleton **/
	private static ConnectDisconnectFenetre conDisFen;
	
	
	/************************************************* 
	 * 					CONSTRUCTOR
	 ************************************************/
	/** Constructeur **/
	private NetworkInformation () { 
		conDisFen = conDisFen.getInstance();
		addObserver(conDisFen);
		usersIPAddress = new HashMap <InetAddress, User> () ;
		// A CORRIGER ! Enlever l'argument 
		try {
			this.localUser = this.addUser("BestBinomeEver", InetAddress.getByName(InetAddress.getLocalHost().getHostAddress()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	/** Getter du localUser **/
	public User getLocalUser () {
		return localUser ;
	}
	
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
		setChanged();
		notifyObservers();
		return user; 
	}
	
	/** Methode qui supprime un User grace a� son adresse IP **/
	public void removeUser (InetAddress ip) {
		this.usersIPAddress.remove(ip) ;
		//setChanged();
		//notifyObservers();
	}
	
	/** Methode qui recupere l'adresse IP d'un utilisateur **/
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
	
	/** Methode qui ajoute le pattern @IP au nickname 
	 * @throws UnknownHostException **/
	public String getNicknameWithIP (User user) throws UnknownHostException {
		NetworkInformation NI = null;
		NI = NI.getInstance();
		if (user.getIdUser() == NI.getLocalUser().getIdUser()) {
			try {
				return (user.getNickname()+"@"+(InetAddress.getLocalHost()).getHostAddress()) ;
			} catch (UnknownHostException e) {
				System.out.println("Erreur lors de l'acquisition de l'@IP locale") ;
				return "0" ;
			}
			
		}
		else 
			return (user.getNickname()+"@"+(NI.getIPAddressOfUser(user)).toString()) ;
	}
	
	/** Methode qui enleve le pattern @IP au nickname **/
	public String getIPOfPattern (String name){
		 Pattern pattern = Pattern.compile("^(.*)@(([0-9]{1,3}[.]){3}[0-9]{1,3})");
	     Matcher matcher = pattern.matcher(name);
	     if (matcher.find()){
	    	 return matcher.group(2);
	     }
	     else
	     {
	    	return name; 
	     }
	}
	
	public String getNicknameWithoutIP(String name){
		Pattern pattern = Pattern.compile("^(.*)@(([0-9]{1,3}[.]){3}[0-9]{1,3})");
	     Matcher matcher = pattern.matcher(name);
	     if (matcher.find()){
	    	 return matcher.group(1);
	     }
	     else
	     {
	    	return name; 
	     }
	}
	
	/** Methodes en relation avec la classe Observable **/
	
}
