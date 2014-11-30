package NI;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import Controler.NetworkInformation;
import Controler.NetworkToControler;
import Controler.User;
import Signals.* ;

public class NIControler {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private UDPSender udpSender ;
	
	private UDPReceiver udpReceiver ;
	
	private TCPSender tcpSender ;
	
	private TCPServer tcpServer ;
	
	private List<TCPServer> tcpReceiverList ; 
	
	private static NIControler NISingleton ;
	
	private NetworkInformation NI ;
	
	protected static NetworkToControler netToCont ;
	
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
	public void setNetToCon (NetworkToControler netCon) {
		netToCont = netCon.getInstance();	
	}
	
	
	/************************************************* 
	 * 					METHODS 
	 * 
	 * @throws UnknownHostException 
	 * 
	 * REMARQUE : On doit envoyer le nom au format "nom@adresseIP"
	 * 
	 ************************************************/
	
	public void sendHello (String name) throws UnknownHostException {
		AbstractMessage message = new Hello (name) ;
		this.udpSender.sendBroadcast(message); 
	}
	
	public void sendHelloAck (String localUsername, String destUsername, InetAddress ipAddress) throws UnknownHostException {
		ArrayList <String> userList = new ArrayList<String> () ;
		ArrayList <InetAddress> ipAddressList = new ArrayList<InetAddress> () ;
		userList.add(destUsername) ;
		ipAddressList.add(ipAddress);
		AbstractMessage message = new HelloAck (localUsername) ;
		this.udpSender.send(message, userList, ipAddressList); 
	}
	
	public void sendGoodbye (String name) throws UnknownHostException {
		AbstractMessage message = new Goodbye (name) ;
		this.udpSender.sendBroadcast(message); 
	}
	
	
	public void sendTextMessage (ArrayList<String> usernameList, String data, ArrayList<InetAddress> ipAddressList) throws UnknownHostException {
		
		/** Construction de l'Abstract message a envoye **/
		AbstractMessage message = new TextMessage (data, usernameList) ;
		this.udpSender.send(message, usernameList, ipAddressList); 
		
	}
	
	public void sendFileMessage (ArrayList<User> userList, File file) {
		
	}
	
	public void receivedHello (String name, InetAddress address) {
		netToCont.processHello(name, address);
	}
	
	public void receivedHelloAck (String name, InetAddress address) {
		netToCont.processHelloAck(name, address) ;
	}
	
	public void receivedGoodbye (String name, InetAddress address) {
		netToCont.processGoodbye(name, address) ;
	}
	
	public void receivedTextMessage (String text, ArrayList <String> ListNicknames) throws UnknownHostException {
		netToCont.processTextMessage(text, ListNicknames) ;
	}
	
	
}
