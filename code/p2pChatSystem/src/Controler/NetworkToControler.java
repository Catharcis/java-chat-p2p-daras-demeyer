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
		User user = NI.addUser(nameWithoutPattern, ipAddress) ;
		
	}
	
	public void processHelloAck(String name, InetAddress ipAddress){
		
		String nameWithoutPattern = NI.getNicknameWithoutIP(name);
		User user = NI.addUser(nameWithoutPattern, ipAddress) ;
		
	}
	
	public void processGoodbye(String name, InetAddress ipAddress){
		
		String nameWithoutPattern = NI.getNicknameWithoutIP(name);
		NI.removeUser(ipAddress);
	}
	
	public void processTextMessage(String message, ArrayList<String> listOfUsernames) throws UnknownHostException{
		
		ArrayList<Integer> listOfIDs = new ArrayList<Integer>();
		User user = null;
		for (int i = 0; i < listOfUsernames.size(); i++){
			String ipString = NI.getIPOfPattern(listOfUsernames.get(i));
			InetAddress ip = InetAddress.getByName(ipString);
			if ((user = NI.getUserList().get(ip)) != null){
				listOfIDs.add(user.getIdUser());
			}
			else{
				System.out.println("ERREUR - ProcessTextMessage - User who has the ip address "+ip+" doesn't exist");
			}
		}
		System.out.println("Liste des utilisateurs concernés : " + listOfIDs.toString());
		System.out.println("Message : " + message);
		
		NI.notifyLastChange(typeOfChange.NEWINCOMINGTEXTMESSAGE, listOfIDs);
	}
	
	public void processFileMessage(){
		
	}
}
