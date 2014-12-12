package NI;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import Signals.*;

/**
 * @author Valérie Daras et Alexandre Demeyer
 */

public class UDPReceiver extends AbstractReceiver implements Runnable {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	/**
	 * Singleton
	 */
	private static UDPReceiver singleton ;
	
	/**
	 * Pointeur d'objet NIControler
	 */
	private static NIControler NiCon ;
	
	/**
	 * Pointeur d'objet DatagramSocket
	 */
	private DatagramSocket socket;
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	/**
	 * Constructeur par defaut qui initialise le port d'ecoute
	 */
	private UDPReceiver() {
		this.setPortEcoute(9876);
		try {
			socket = new DatagramSocket (this.getPortEcoute()) ;
		} catch (SocketException e) {
			System.out.println("UDPRECEIVER - CONSTRUCTOR : SocketEXCEPTION ! ") ;
		}
	}
	
	/**
	 * Creer l'instance si elle n'est pas cree ou la recupere
	 * @return l'instance UDPReceiver
	 */
	public static UDPReceiver getInstanceUDPReceiver() {
		if (singleton == null){
			singleton = new UDPReceiver();
		}
		return singleton;
	}
	
	/************************************************* 
	 * 				GETTERS and SETTERS
	 ************************************************/
	
	/**
	 * Getter de socket (du type DatagramSocket)
	 * @return l'objet server
	 */
	public DatagramSocket getSocket(){
		return socket;
	}
	
	/**
	 * Setter de NiCon
	 * @param NiCont : l'objet NIControler
	 */
	public void setNiCon (NIControler NiCont) {
		UDPReceiver.NiCon = NIControler.getInstance() ;
	}
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/
	
	/**
	 * Redefinition de la methode d'ecoute sur le reseau pour la classe UDPReceiver
	 */
	public void listen(){
		
		// Cas ou la socket aurait deja ete utilisee et que l'on relance le programme
		if (!socket.equals(null) && socket.isClosed()){
			try {
				socket = new DatagramSocket(this.getPortEcoute());
				this.setStateListen(true);
			} catch (SocketException e) {
				System.out.println("UDPRECEIVER - NEW SOCKET ERROR : SocketEXCEPTION ! ") ;
			}	
		}
		
		try {
		while (this.isListening()) {
			/** creation du paquet DatagramPacket **/
			byte[] buf = new byte[5000] ;
			DatagramPacket packet = new DatagramPacket (buf, buf.length);
			
			System.out.println("UDPReceiver : En attente d'une reception...") ;
			socket.receive(packet) ;
			
			AbstractMessage message = this.bufToMessage(buf) ;
			
			String myUsername = NiCon.getNetInfo().getLocalUser().getNickname()+"@"+NiCon.getNetInfo().getLocalIPAddress();
			
			if (!(message.getNickname().equals(myUsername))){
				if (message.getTypeContenu() == typeContenu.HELLO) {
					String name = message.getNickname();
					System.out.println("UDPReceiver : Hello, I am " +name) ;
					NiCon.receivedHello(name, packet.getAddress()) ;
				}
				
				if (message.getTypeContenu() == typeContenu.HELLOACK) {
					String name = message.getNickname();
					System.out.println("UDPReceiver : Hello (Ack), I am " +name) ;	
					NiCon.receivedHelloAck(name, packet.getAddress()) ;
				}
				
				if (message.getTypeContenu() == typeContenu.GOODBYE) {
					String name = message.getNickname();
					System.out.println("UDPReceiver : Goodbye, my name was " +name) ;
					NiCon.receivedGoodbye(name, packet.getAddress()) ;
				}
				
				if (message.getTypeContenu() == typeContenu.TEXTMESSAGE) {
					TextMessage textMessage = (TextMessage) message;
					System.out.println("UDPReceiver : TextMessage Received") ;
					// On ajoute l'utilisateur distant et on nous retire de la liste
					ArrayList<String> list = textMessage.getListNicknamesDest();
					if (list.remove(myUsername)){
						System.out.println("UDPReceiver - TextMessage - Remove myUsername success : "+myUsername);

					}
					else{
						System.out.println("UDPReceiver - TextMessage - [ERROR] This message was not for us");
					}
					
					// On ajoute l'utilisateur qui nous a envoye le message
					list.add(textMessage.getNickname());
					System.out.println("UDPReceiver - TextMessage - Source :"+list.get(list.size()-1));
					
					// On envoie les parametres au NIControler
					NiCon.receivedTextMessage (textMessage.getMessage(),list) ;
				} 
				
			}
		}
		} catch (BindException e1) {
			System.out.println("UDPReceiver : Port for UDP SocketReceiver already used.") ;
		} catch (SocketException e2) {
			System.out.println("UDPReceiver : Creation of UDP SocketReceiver failed.") ;
		} catch (IOException e3) {
			System.out.println("UDPReceiver : IOException during Receive : "+e3.getCause()) ;
			
		} catch (ClassNotFoundException e4) {
			System.out.println("UDPReceiver : Lecture du message en rÃ©ception impossible.") ;			
		}
		finally {
			if (socket != null)
				socket.close() ;
		}
		System.out.println("RESUME DE FERMETURE : \n{\nListen : "+Boolean.toString(this.isListening()).toUpperCase()+"\nSocket connected ? : "+Boolean.toString(socket.isConnected()).toUpperCase()+
																	"\nSocket closed ? : "+Boolean.toString(socket.isClosed()).toUpperCase()+"\n}");
		
	}

	/**
	 * Permet d'obtenir un AbstractMessage a partir d'un tableau d'octects
	 * @param buf : tableau d'octets
	 * @return un AbstractMessage
	 * @throws IOException : "Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations."
	 * @throws ClassNotFoundException : "Thrown when an application tries to load in a class through its string name using some methods"
	 */
	private AbstractMessage bufToMessage (byte[] buf) throws IOException, ClassNotFoundException {
		ByteArrayInputStream byteIn = new ByteArrayInputStream(buf);
		ObjectInput in = new ObjectInputStream(byteIn);
		return (AbstractMessage) in.readObject() ;
	}
	
	
	/** Redefiniton de la methode run du Runnable **/
	public void run() {
		listen() ;
	}
	
}
