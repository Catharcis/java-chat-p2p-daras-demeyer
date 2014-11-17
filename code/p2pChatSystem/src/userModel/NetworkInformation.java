package userModel;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Observable; 

public class NetworkInformation extends Observable {

	private NetworkInformation InfoSingleton ;
	
	private HashMap<User, InetAddress> usersIPAddress ;
	
	private NetworkInformation () { 
		usersIPAddress = new HashMap <User, InetAddress> () ;
	}
	
	public NetworkInformation getInstance () {
		if (InfoSingleton == null)
			InfoSingleton = new NetworkInformation () ;
		return InfoSingleton ;
	}
}
