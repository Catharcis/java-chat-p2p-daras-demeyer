package NI;
import java.io.File;
import java.util.ArrayList;

import userModel.* ;
import Signals.* ;

public class NIControler extends Thread {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private UDPSender udpSender ;
	
	private UDPReceiver udpReceiver ;
	
	private TCPSender tcpSender ;
	
	private TCPServer tcpServer ;
	
	private static NIControler NISingleton ;
	
	
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private NIControler () 
	{
		this.udpReceiver = udpReceiver.getInstanceUDPReceiver() ;
		this.udpSender = udpSender.getInstanceUDPSender() ;
	}
	
	public static NIControler getInstance () {
		if (NISingleton == null) {
			NISingleton = new NIControler () ;
		}
		return NISingleton ;
	}
	
	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/
	
	public UDPReceiver getUDPReceiver () {
		return udpReceiver ;
	}
	
	public UDPSender getUDPSender () {
		return udpSender ;
	}
	
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/
	
	public void sendHello (User user) {
		AbstractMessage message = new Hello (user.getNickname()) ;
		this.udpSender.sendBroadcast(message); 
	}
	
	public void sendHelloAck (User user) {
		ArrayList <User> userList = new ArrayList<User> () ;
		userList.add(user) ;
		AbstractMessage message = new HelloAck (user.getNickname()) ;
		this.udpSender.send(message, userList); 
	}
	
	public void sendGoodbye (User user) {
		AbstractMessage message = new Goodbye (user.getNickname()) ;
		this.udpSender.sendBroadcast(message); 
	}
	
	
	public void sendTextMessage (ArrayList<User> userList, String data) {
		/** Construction d'une List de String sous format NICKNAME@XX.XX.XX.XX**/
		ArrayList<String> nicknameList = new ArrayList <String> () ;
		for (int i = 0 ; i< userList.size() ; i++) {
			/** On récupère les informations réseaux **/
			NetworkInformation NI = null;
			NI = NI.getInstance();
			nicknameList.add(userList.get(i).getNickname()+"@"+(NI.getIPAddressOfUser(userList.get(i))).toString()) ;
		}
		
		/** Construction de l'Abstract message à envoyer **/
		AbstractMessage message = new TextMessage (data, nicknameList) ;
		this.udpSender.send(message, userList); 
	}
	
	public void sendFileMessage (ArrayList<User> userList, File file) {
		
	}
	
}
