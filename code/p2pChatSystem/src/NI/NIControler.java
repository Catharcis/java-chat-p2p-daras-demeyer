package NI;
import java.io.File;
import java.util.ArrayList;
import userModel.* ;


public class NIControler extends Thread {

	private UDPSender udpSender ;
	
	private UDPReceiver udpReceiver ;
	
	private TCPSender tcpSender ;
	
	private TCPServer tcpServer ;
	
	private static NIControler NISingleton ;
	
	private NIControler () 
	{
		
	}
	
	public static NIControler getInstance () {
		if (NISingleton == null) {
			NISingleton = new NIControler () ;
		}
		return NISingleton ;
	}
	
	public void sendHello (User user) {
		
	}
	
	public void sendHelloAck (User user) {
		
	}
	
	public void sendGoodbye (User user) {
		
	}
	
	public void sendTextMessage (ArrayList<User> userList, String message) {
		
	}
	
	public void sendFileMessage (ArrayList<User> userList, File file) {
		
	}
	
}
