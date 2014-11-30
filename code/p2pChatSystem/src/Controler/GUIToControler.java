package Controler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import NI.NIControler;


public class GUIToControler {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private static GUIToControler guiToContSingleton;
	
	private static NIControler niCont;
	
	private static NetworkInformation NI;
	
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private GUIToControler(){
		niCont = niCont.getInstance();
		NI = NI.getInstance();
	}
	
	
	/** Méthode qui permet de récupérer l'instance de la classe **/
	public static GUIToControler getInstance(){
		if (guiToContSingleton == null) {
			guiToContSingleton = new GUIToControler() ;
		}
		return guiToContSingleton ;
	}
	
	
	/************************************************* 
	 * 					METHODS
	 ************************************************/
	
	/** Différentes méthodes de type perform() permettant d'envoyer un signal au NI**/
	
	public void performConnect(String name){
		NI.setLocalUser(name);
		try {
			String nameWithPattern = NI.getNicknameWithIP(NI.getLocalUser());
			niCont.sendHello(nameWithPattern);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void performSendHelloAck(User destUser){
		try {
			String localNameWithPattern = NI.getNicknameWithIP(NI.getLocalUser());
			String destNameWithPattern = NI.getNicknameWithIP(destUser);
			niCont.sendHelloAck(localNameWithPattern,destNameWithPattern,NI.getIPAddressOfUser(destUser));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void performDisconnect(){
		try {
			String nameWithPattern = NI.getNicknameWithIP(NI.getLocalUser());
			niCont.sendGoodbye(nameWithPattern);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void performSendTextMessage(String message, ArrayList<User> userList) throws UnknownHostException{
		
		/** Construction d'une List de String sous format NICKNAME@XX.XX.XX.XX**/
		ArrayList<String> nicknameList = new ArrayList <String> () ;
		ArrayList<InetAddress> ipAddressesList = new ArrayList<InetAddress>();
		for (int i = 0 ; i< userList.size() ; i++) {
			/** On recupere les informations reseaux **/
			nicknameList.add(NI.getNicknameWithIP(userList.get(i))) ;
			ipAddressesList.add(NI.getIPAddressOfUser(userList.get(i)));
		}
		
		niCont.sendTextMessage(nicknameList, message, ipAddressesList);
		
	}
	
	public void performSendFile(){
		
	}
}
