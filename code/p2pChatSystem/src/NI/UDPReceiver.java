package NI;

import java.io.*;
import java.net.*;

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
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private UDPReceiver(){
		this.setPortEcoute(9876);
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
	 * 					METHODS 
	 ************************************************/
	
	public void listen(){
		/** creation d'un socket UDP**/
		DatagramSocket socket = null;
		try {
		socket = new DatagramSocket (this.getPortEcoute()) ;
		
		//while (this.isListening()) {
			/** creation du paquet DatagramPacket **/
			byte[] buf = new byte[5000] ;
			DatagramPacket packet = new DatagramPacket (buf, buf.length);
			
			System.out.println("UDPReceiver : En attente d'une reception...") ;
			socket.receive(packet) ;
			System.out.println("UDPReceiver : Paquet recu!") ;
			
			AbstractMessage message = this.bufToMessage(buf) ;
			if (message.getTypeContenu() == typeContenu.HELLO) {
				String name = message.getNickname();
				NiCon.receivedHello(name, socket.getInetAddress()) ;
				System.out.println("UDPReceiver : Hello, I am " +name) ;
				
			}
			
			if (message.getTypeContenu() == typeContenu.HELLOACK) {
				String name = message.getNickname();
				NiCon.receivedHelloAck(name, socket.getInetAddress()) ;
				System.out.println("UDPReceiver : Hello (Ack), I am " +name) ;
				
			}
			
			if (message.getTypeContenu() == typeContenu.GOODBYE) {
				String name = message.getNickname();
				NiCon.receivedGoodbye(name, socket.getInetAddress()) ;
				System.out.println("UDPReceiver : Goodbye, my name was " +name) ;
			}
			
			if (message.getTypeContenu() == typeContenu.TEXTMESSAGE) {
				TextMessage textMessage = (TextMessage) message;
				NiCon.receivedTextMessage (textMessage.getMessage(),textMessage.getListNicknamesDest()) ;
			} 
			
		//}
		
		} catch (BindException e1) {
			System.out.println("UDPReceiver : Port for UDP SocketReceiver already used.") ;
		} catch (SocketException e2) {
			System.out.println("UDPReceiver : Creation of UDP SocketReceiver failed.") ;
		} catch (IOException e3) {
			System.out.println("UDPReceiver : IOException during Receive.") ;
			// fermer la socket ?
		} catch (ClassNotFoundException e4) {
			System.out.println("UDPReceiver : Lecture du message en réception impossible.") ;			
		}
		finally {
			if (socket != null)
				socket.close() ;
		}
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
