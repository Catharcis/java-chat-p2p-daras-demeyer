package NI;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import Controler.NetworkInformation;
import Controler.NetworkToControler;
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
	 * @throws UnknownHostException 
	 ************************************************/
	
	private NIControler ()	{
		udpReceiver = UDPReceiver.getInstanceUDPReceiver() ;
		createThreadUDPReceiver();
		this.udpSender = UDPSender.getInstanceUDPSender() ;
		this.tcpServer = TCPServer.getInstanceTCPServer();
		createThreadTCPServer();
		this.tcpSender = TCPSender.getInstanceTCPSender();
		NI = NetworkInformation.getInstance();
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
		netToCont = NetworkToControler.getInstance();	
	}
	
	
	/************************************************* 
	 * 					METHODS 
	 * 
	 * @throws UnknownHostException 
	 * 
	 * REMARQUE : On doit envoyer le nom au format "nom@adresseIP"
	 * 
	 ************************************************/
	
	public void createThreadUDPReceiver(){
		Thread tUdpReceiver = new Thread (udpReceiver, "ThreadUdpReceiver") ;
		tUdpReceiver.start() ;
	}
	
	public void createThreadTCPServer(){
		Thread tTcpServer = new Thread (tcpServer, "tcpServer") ;
		tTcpServer.start() ;
	}
	
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
		AbstractMessage message = new TextMessage (NI.getNicknameWithIP(NI.getLocalUser()), data, usernameList) ;
		this.udpSender.send(message, usernameList, ipAddressList); 
		
	}
	
	public void sendFileMessage (ArrayList<String> usernameList, File file, ArrayList<InetAddress> ipAddressList) throws UnknownHostException {
		
		/** Construction de l'Abstract message a envoye **/
		AbstractMessage message = new FileMessage (file.getName(), usernameList, file.length()) ;
		message.setNickname(NI.getNicknameWithIP(NI.getLocalUser()));
		this.tcpSender.sendFile(message, usernameList, ipAddressList, file); 
		
	}
	
	protected void receivedHello (String name, InetAddress address) {
		netToCont.processHello(name, address);
	}
	
	protected void receivedHelloAck (String name, InetAddress address) {
		netToCont.processHelloAck(name, address) ;
	}
	
	protected void receivedGoodbye (String name, InetAddress address) {
		netToCont.processGoodbye(name, address) ;
	}
	
	protected void receivedTextMessage (String text, ArrayList <String> ListNicknames) throws UnknownHostException {
		netToCont.processTextMessage(text, ListNicknames) ;
	}
	
	
}
