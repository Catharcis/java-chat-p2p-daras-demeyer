package Controler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import GUI.GUIControler;


public class NetworkToControler {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private static NetworkToControler netToContSingleton;
	
	// il n'a connaissance que du GUIControler (pas du NI car il ne communique pas avec lui, c'est lui qui communique avec cette classe)
	private static GUIControler guiCont;
	
	private static NetworkInformation NI;
	
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private NetworkToControler(){
		guiCont = guiCont.getInstance();
		NI = NI.getInstance();
	}
	
	/** M�thode qui permet d'obtenir l'instance de la classe **/
	public static NetworkToControler getInstance(){
			if (netToContSingleton == null) {
				netToContSingleton = new NetworkToControler() ;
			}
			return netToContSingleton ;
	}
	
	
	/************************************************* 
	 * 					METHODS
	 ************************************************/
	
	/** M�thodes de type process() indiquant la r�ception d'un message sur le r�seau au GUI **/
	
	public void processHello(String name, InetAddress ipAddress){
		
		String nameWithoutPattern = NI.getNicknameWithoutIP(name);
		System.out.println("Name = " +nameWithoutPattern);
		User user = NI.addUser(nameWithoutPattern, ipAddress) ;
		System.out.println("IP = " +NI.getIPOfPattern(name));
		
	}
	
	public void processHelloAck(String name, InetAddress ipAddress){
		
		String nameWithoutPattern = NI.getNicknameWithoutIP(name);
		System.out.println("Name = " +nameWithoutPattern);
		User user = NI.addUser(nameWithoutPattern, ipAddress) ;
		System.out.println("IP = " +NI.getIPOfPattern(name));
		
	}
	
	public void processGoodbye(String name, InetAddress ipAddress){
		
		String nameWithoutPattern = NI.getNicknameWithoutIP(name);
		NI.removeUser(ipAddress);
		System.out.println("Name = " +nameWithoutPattern);
		System.out.println("IP = " +NI.getIPOfPattern(name));
	}
	
	public void processTextMessage(String message, ArrayList<String> listOfUsernames) throws UnknownHostException{
		
		ArrayList<User> listOfUsers = new ArrayList<User>();
		for (int i = 0; i < listOfUsernames.size(); i++){
			String ipString = NI.getIPOfPattern(listOfUsernames.get(i));
			InetAddress ip = InetAddress.getByName(ipString);
			listOfUsers.add(NI.getUserList().get(ip));
		}
		System.out.println("Liste des utilisateurs concern�s : " + listOfUsers.toString());
		System.out.println("Message : " + message);
		
	}
	
	public void processFileMessage(){
		
	}
}
