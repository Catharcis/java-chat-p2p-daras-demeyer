package NI;
import java.io.File;
import java.util.ArrayList;
import userModel.* ;
import Signals.* ;

public class NIControler extends Thread {

	private UDPSender udpSender ;
	
	private UDPReceiver udpReceiver ;
	
	private TCPSender tcpSender ;
	
	private TCPServer tcpServer ;
	
	private static NIControler NISingleton ;
	
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
	
	public UDPReceiver getUDPReceiver () {
		return udpReceiver ;
	}
	
	public UDPSender getUDPSender () {
		return udpSender ;
	}
	
	public void sendHello (User user) {
		AbstractMessage message = new Hello (user.getNickname()) ;
		this.udpSender.sendBroadcast(message); 
	}
	
	public void sendHelloAck (User user) {
		AbstractMessage message = new HelloAck (user.getNickname()) ;
		this.udpSender.sendBroadcast(message); 
	}
	
	public void sendGoodbye (User user) {
		AbstractMessage message = new Goodbye (user.getNickname()) ;
		this.udpSender.sendBroadcast(message); 
	}
	
	public void sendTextMessage (ArrayList<User> userList, String data) {
		/** Construction d'une List de String sous format NICKNAME@XX.XX.XX.XX**/
		ArrayList<String> nicknameList = new ArrayList <String> () ;
		for (int i = 0 ; i< userList.size() ; i++) {
			
		}
		
		/** Construction de l'Abstract message � envoyer **/
		AbstractMessage message = new TextMessage (data, nicknameList) ;
		this.udpSender.sendBroadcast(message); 
	}
	
	public void sendFileMessage (ArrayList<User> userList, File file) {
		
	}
	
}
