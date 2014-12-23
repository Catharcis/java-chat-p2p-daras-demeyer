package NI;
import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;
import Controler.NetworkInformation;
import Controler.NetworkToControler;
import Signals.* ;

/**
 * @author Valérie Daras et Alexandre Demeyer
 */

public class NIControler {

	/************************************************* 
	 * 				ATTRIBUTS and FIELDS 
	 ************************************************/
	
	/** pointeur d'objet UDPSender **/
	private UDPSender udpSender ;
	
	/** pointeur d'objet UDPReceiver **/
	private UDPReceiver udpReceiver ;
	
	/** pointeur d'objet TCPSender **/
	private TCPSender tcpSender ;
	
	/** pointeur d'objet TCPServer **/
	private TCPServer tcpServer ;
	
	/** pointeur d'objet NIControler **/
	private static NIControler NISingleton ;
	
	/** pointeur d'objet NetworkInformation **/
	private NetworkInformation NI ;
	
	/** pointeur d'objet NetworkToControler **/
	protected static NetworkToControler netToCont ;
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	/**
	 * Constructeur par defaut
	 */
	private NIControler ()	{
		udpReceiver = UDPReceiver.getInstanceUDPReceiver() ;
		createThreadUDPReceiver();
		this.udpSender = UDPSender.getInstanceUDPSender() ;
		this.tcpServer = TCPServer.getInstanceTCPServer();
		createThreadTCPServer();
		this.tcpSender = TCPSender.getInstanceTCPSender();
		NI = NetworkInformation.getInstance();
	}
	
	/**
	 * Creer l'instance si elle n'est pas cree ou la recupere
	 * @return l'instance NIControler
	 */
	public static NIControler getInstance () {
		if (NISingleton == null) {
			NISingleton = new NIControler () ;
		}
		return NISingleton ;
	}
	
	/************************************************* 
	 * 				GETTERS and SETTERS
	 ************************************************/
	
	/**
	 * Getter de udpReceiver
	 * @return l'objet UDPReceiver
	 */
	public UDPReceiver getUDPReceiver () {
		return udpReceiver ;
	}
	
	/**
	 * Getter de udpSender
	 * @return l'objet UDPSender
	 */
	public UDPSender getUDPSender () {
		return udpSender ;
	}
	
	/**
	 * Getter de NI
	 * @return l'objet NetworkInformation
	 */
	public NetworkInformation getNetInfo () {
		return NI ;
	}
	
	/**
	 * Setter de netToCont
	 * @param netCon : l'objet NetworkToControler
	 */
	public void setNetToCon (NetworkToControler netCon) {
		netToCont = NetworkToControler.getInstance();	
	}
	
	/**
	 * Getter de tcpServer
	 * @return l'objet TCPServer
	 */
	public TCPServer getTCPServer () {
		return this.tcpServer ;
	}
	
	/**
	 * Getter de tcpSender
	 * @return l'objet TCPSender
	 */
	public TCPSender getTCPSender() {
		return this.tcpSender ;
	}
	
	
	/************************************************* 
	 * 					METHODS 
	 * 
	 * REMARQUE : On doit envoyer le nom au format "nom@adresseIP"
	 * 
	 ************************************************/
	
	/**
	 * Permet de creer le thread pour la socket d'ecoute en UDP
	 */
	public void createThreadUDPReceiver() {
		Thread tUdpReceiver = new Thread (udpReceiver, "ThreadUdpReceiver") ;
		tUdpReceiver.start() ;
	}
	
	/**
	 * Permet de creer le thread pour la socket d'ecoute en TCP
	 */
	public void createThreadTCPServer() {
		Thread tTcpServer = new Thread (tcpServer, "tcpServer") ;
		tTcpServer.start() ;
	}
	
	/**
	 * Permet de notifier UDPSender d'envoyer un Hello
	 * @param name : le nom de l'utilisateur local
	 */
	public void sendHello (String name) {
		AbstractMessage message = new Hello (name) ;
		this.udpSender.sendBroadcast(message); 
	}
	
	/**
	 * Permet de notifier UDPSender d'envoyer un HelloAck
	 * @param localUsername : nom de l'utilisateur local
	 * @param destUsername : nom de l'utilisateur distant
	 * @param ipAddress : l'adresse ip de l'utilisateur distant
	 */
	public void sendHelloAck (String localUsername, String destUsername, InetAddress ipAddress) {
		ArrayList <String> userList = new ArrayList<String> () ;
		ArrayList <InetAddress> ipAddressList = new ArrayList<InetAddress> () ;
		userList.add(destUsername) ;
		ipAddressList.add(ipAddress);
		AbstractMessage message = new HelloAck (localUsername) ;
		this.udpSender.send(message, userList, ipAddressList); 
	}
	
	/**
	 * Permet de notifier UDPSender d'envoyer un Goodbye
	 * @param name : nom de l'utilisateur local
	 */
	public void sendGoodbye (String name) {
		AbstractMessage message = new Goodbye (name) ;
		this.udpSender.sendBroadcast(message); 
	}
	
	/**
	 * Permet de notifier UDPSender d'envoyer un TextMessage
	 * @param usernameList : liste des noms d'utilisateurs concernes
	 * @param data : le message a transmettre
	 * @param ipAddressList : liste des adresses ip de chaque utilisateur
	 */
	public void sendTextMessage (ArrayList<String> usernameList, String data, ArrayList<InetAddress> ipAddressList) {
		/** Construction de l'Abstract message a envoye **/
		AbstractMessage message = new TextMessage (NI.getNicknameWithIP(NI.getLocalUser()), data, usernameList) ;
		this.udpSender.send(message, usernameList, ipAddressList); 
		
	}
	
	/**
	 * Permet de notifier UDPSender d'envoyer un FileMessage
	 * @param usernameList : liste des noms d'utilisateurs concernes
	 * @param file : le fichier a transmettre
	 * @param ipAddressList : liste des adresses ip de chaque utilisateur
	 */
	public void sendFileMessage (ArrayList<String> usernameList, File file, ArrayList<InetAddress> ipAddressList) {
		/** Construction de l'Abstract message a envoye **/
		AbstractMessage message = new FileMessage (file.getName(), usernameList, file.length()) ;
		message.setNickname(NI.getNicknameWithIP(NI.getLocalUser()));
		this.tcpSender.sendFile(message, usernameList, ipAddressList, file);
		
	}
	
	/**
	 * Permet de notifier NetworkToControler qu'un Hello a ete recu
	 * @param name : nom de l'utilisateur distant
	 * @param address : l'adresse ip de l'utilisateur distant
	 */
	protected void receivedHello (String name, InetAddress address) {
		netToCont.processHello(name, address);
	}
	
	/**
	 * Permet de notifier NetworkToControler qu'un HelloAck a ete recu
	 * @param name : nom de l'utilisateur distant
	 * @param address : l'adresse ip de l'utilisateur distant
	 */
	protected void receivedHelloAck (String name, InetAddress address) {
		netToCont.processHelloAck(name, address) ;
	}
	
	/**
	 * Permet de notifier NetworkToControler qu'un Goodbye a ete recu
	 * @param name : nom de l'utilisateur distant
	 * @param address : l'adresse ip de l'utilisateur distant
	 */
	protected void receivedGoodbye (String name, InetAddress address) {
		netToCont.processGoodbye(name, address) ;
	}
	
	/**
	 * Permet de notifier NetworkToControler qu'un TextMessage a ete recu
	 * @param text : message recu
	 * @param ListNicknames : liste des noms d'utilisateurs concernes
	 */
	protected void receivedTextMessage (String text, ArrayList <String> ListNicknames) {
		netToCont.processTextMessage(text, ListNicknames) ;
	}
	
	/**
	 * Permet de notifier NetworkToControler qu'un FileMessage a ete recu
	 * @param nameFile : nom du fichier recu
	 * @param ListNicknames : liste des noms d'utilisateurs concernes
	 */
	protected void receivedFileMessage (String nameFile, ArrayList <String> ListNicknames) {
		netToCont.processFileMessage(nameFile, ListNicknames) ;
	}
	
}
