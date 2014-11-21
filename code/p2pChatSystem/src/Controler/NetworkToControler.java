package Controler;

import java.net.InetAddress;
import java.util.ArrayList;

import GUI.GUIControler;


public class NetworkToControler {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private static NetworkToControler netToContSingleton;
	
	// il n'a connaissance que du GUIControler (pas du NI car il ne communique pas avec lui, c'est lui qui communique avec cette classe)
	private static GUIControler guiCont;
	
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private NetworkToControler(){
		guiCont = guiCont.getInstance();
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
		
		NetworkInformation NI = null;
		NI = NI.getInstance();
		String nameWithoutPattern = NI.getNicknameWithoutIP(name);
		User user = NI.addUser(nameWithoutPattern, ipAddress) ;
		
	}
	
	public void processHelloAck(String name, InetAddress ipAddress){
		
		NetworkInformation NI = null;
		NI = NI.getInstance();
		String nameWithoutPattern = NI.getNicknameWithoutIP(name);
		User user = NI.addUser(nameWithoutPattern, ipAddress) ;
		
	}
	
	public void processGoodbye(String name, InetAddress ipAddress){
		
		NetworkInformation NI = null;
		NI = NI.getInstance();
		String nameWithoutPattern = NI.getNicknameWithoutIP(name);
		NI.removeUser(ipAddress);
		
	}
	
	public void processTextMessage(String message, ArrayList<String> listOfUsernames){
		
	}
	
	public void processFileMessage(){
		
	}
}
