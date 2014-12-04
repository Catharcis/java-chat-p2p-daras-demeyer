package NI;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import Controler.NetworkInformation;
import Controler.NetworkToControler;
import Controler.User;
import Signals.*;

public class UDPReceiver extends AbstractReceiver implements Runnable {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private static UDPReceiver singleton = null;
	
	private static NIControler NiCon ;
	
	private DatagramSocket socket;
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private UDPReceiver(){
		this.setPortEcoute(9876);
		try {
			socket = new DatagramSocket (this.getPortEcoute()) ;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static UDPReceiver getInstanceUDPReceiver(){
		if (singleton == null){
			singleton = new UDPReceiver();
		}
		return singleton;
	}
	
	public void setNiCon (NIControler NiCont){
		this.NiCon = NiCont.getInstance() ;
	}
	
	
	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/
	
	public DatagramSocket getSocket(){
		return socket;
	}
	
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/
	
	public void listen(){
		
		// Cas ou la socket aurait deja ete utilisee et que l'on relance le programme
		if (!socket.equals(null) && socket.isClosed()){
			try {
				socket = new DatagramSocket(this.getPortEcoute());
				this.setStateListen(true);
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			
			String myUsername = NiCon.getNetInfo().getLocalUser().getNickname()+"@"+(InetAddress.getLocalHost()).getHostAddress();
			
			if (!(message.getNickname().equals(myUsername))){
			
				if (message.getTypeContenu() == typeContenu.HELLO) {
					String name = message.getNickname();
					System.out.println("UDPReceiver : Hello, I am " +name) ;
					NiCon.receivedHello(name, packet.getAddress()) ;						
				}
				
				if (message.getTypeContenu() == typeContenu.HELLOACK) {
					String name = message.getNickname();
					NiCon.receivedHelloAck(name, packet.getAddress()) ;
					System.out.println("UDPReceiver : Hello (Ack), I am " +name) ;	
				}
				
				if (message.getTypeContenu() == typeContenu.GOODBYE) {
					String name = message.getNickname();
					NiCon.receivedGoodbye(name, packet.getAddress()) ;
					System.out.println("UDPReceiver : Goodbye, my name was " +name) ;
				}
				
				if (message.getTypeContenu() == typeContenu.TEXTMESSAGE) {
					TextMessage textMessage = (TextMessage) message;
					// On ajoute l'utilisateur distant et on nous retire de la liste
					ArrayList<String> list = textMessage.getListNicknamesDest();
					if (list.remove(myUsername)){
						System.out.println("UDPReceiver - TextMessage - Remove myUsername success");
					}
					else{
						System.out.println("UDPReceiver - TextMessage - [ERROR] This message was not for us");
					}
					
					// On ajoute l'utilisateur qui nous a envoye le message
					list.add(textMessage.getNickname());
					
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
			System.out.println("UDPReceiver : IOException during Receive.") ;
		} catch (ClassNotFoundException e4) {
			System.out.println("UDPReceiver : Lecture du message en réception impossible.") ;			
		}
		finally {
			if (socket != null)
				socket.close() ;
		}
		System.out.println("RESUME DE FERMETURE : \n{\nListen : "+Boolean.toString(this.isListening()).toUpperCase()+"\nSocket connected ? : "+Boolean.toString(socket.isConnected()).toUpperCase()+
																	"\nSocket closed ? : "+Boolean.toString(socket.isClosed()).toUpperCase()+"\n}");
		
	}

	/**
	 * @throws IOException  
	 * @throws ClassNotFoundException **/
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
