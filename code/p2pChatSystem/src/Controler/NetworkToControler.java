package Controler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import GUI.GUIControler;


public class NetworkToControler {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private static NetworkToControler netToContSingleton ;
	
	private static GUIControler guiCon;
	
	private static NetworkInformation NI;
	
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private NetworkToControler(){
		NI = NI.getInstance();
	}
	
	/** Méthode qui permet d'obtenir l'instance de la classe **/
	public static NetworkToControler getInstance(){
			if (netToContSingleton == null) {
				netToContSingleton = new NetworkToControler() ;
			}
			return netToContSingleton ;
	}

	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/
	
	public void setGuiCon (GUIControler guiCont) {
		this.guiCon = guiCont.getInstance();
	}
	
	public NetworkInformation getNetInfo () {
		return NI ;
	}
	
	/************************************************* 
	 * 					METHODS
	 ************************************************/
	
	/** Méthodes de type process() indiquant la réception d'un message sur le réseau au GUI **/
	
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
		System.out.println("Liste des utilisateurs concernés : " + listOfUsers.toString());
		System.out.println("Message : " + message);
		
	}
	
	public void processFileMessage(){
		
	}
}
