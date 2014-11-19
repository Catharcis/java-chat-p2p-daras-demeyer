package NI;
import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;

import userModel.* ;
import Signals.* ;

public class NIControler {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private UDPSender udpSender ;
	
	private UDPReceiver udpReceiver ;
	
	private TCPSender tcpSender ;
	
	private TCPServer tcpServer ;
	
	private static NIControler NISingleton ;
	
	private NetworkInformation NI ;
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private NIControler () 
	{
		udpReceiver = udpReceiver.getInstanceUDPReceiver() ;
		Thread tUdpReceiver = new Thread (udpReceiver, "ThreadUdpReceiver") ;
		tUdpReceiver.start() ;
		this.udpSender = udpSender.getInstanceUDPSender() ;
		NI = NI.getInstance();
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
	
	public NetworkInformation getNetInfo () {
		return NI ;
	}
	
	/************************************************* 
	 * 					METHODS 
	 * @throws UnknownHostException 
	 ************************************************/
	
	public void sendHello (User user) throws UnknownHostException {
		AbstractMessage message = new Hello (NI.getNicknameWithIP(user)) ;
		this.udpSender.sendBroadcast(message); 
	}
	
	public void sendHelloAck (User user) throws UnknownHostException {
		ArrayList <User> userList = new ArrayList<User> () ;
		userList.add(user) ;
		AbstractMessage message = new HelloAck (NI.getNicknameWithIP(user)) ;
		this.udpSender.send(message, userList); 
	}
	
	public void sendGoodbye (User user) throws UnknownHostException {
		AbstractMessage message = new Goodbye (NI.getNicknameWithIP(user)) ;
		this.udpSender.sendBroadcast(message); 
	}
	
	
	public void sendTextMessage (ArrayList<User> userList, String data) throws UnknownHostException {
		/** Construction d'une List de String sous format NICKNAME@XX.XX.XX.XX**/
		ArrayList<String> nicknameList = new ArrayList <String> () ;
		for (int i = 0 ; i< userList.size() ; i++) {
			/** On recupere les informations reseaux **/
			nicknameList.add(NI.getNicknameWithIP(userList.get(i))) ;
		}
		
		/** Construction de l'Abstract message � envoyer **/
		AbstractMessage message = new TextMessage (data, nicknameList) ;
		this.udpSender.send(message, userList); 
	}
	
	public void sendFileMessage (ArrayList<User> userList, File file) {
		
	}
	
}
