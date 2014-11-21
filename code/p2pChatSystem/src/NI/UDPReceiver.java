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
			
			System.out.println("En attente d'une réception...") ;
			socket.receive(packet) ;
			System.out.println("Paquet recu!") ;
			
			ByteArrayInputStream byteIn = new ByteArrayInputStream(buf);
			ObjectInput in = new ObjectInputStream(byteIn);
			AbstractMessage message = (AbstractMessage) in.readObject() ;
			if (message.getTypeContenu() == typeContenu.HELLO) {
				NetworkToControler netToCont = null;
				netToCont = netToCont.getInstance();
				String name = message.getNickname();
				netToCont.processHello(name, socket.getInetAddress());
				System.out.println("Hello, I am " +name) ;
				
			}
			
			if (message.getTypeContenu() == typeContenu.HELLOACK) {
				NetworkToControler netToCont = null;
				netToCont = netToCont.getInstance();
				String name = message.getNickname();
				netToCont.processHelloAck(name, socket.getInetAddress()) ;
				System.out.println("Hello, I am " +name) ;
				
			}
			
			if (message.getTypeContenu() == typeContenu.GOODBYE) {
				NetworkToControler netToCont = null;
				netToCont = netToCont.getInstance();
				String name = message.getNickname();
				netToCont.processGoodbye(name, socket.getInetAddress());
				System.out.println("Goodbye, my name was " +name) ;
			}
		/* A FAIRE 	
			if (message.getTypeContenu() == typeContenu.TEXTMESSAGE) {
				// Ajout des nouveaux User dans la HashMap 
				NetworkInformation NI = null ;
				NI = NI.getInstance() ;
				User user = NI.getUserList().get(socket.getInetAddress());
				// processGoodbye(user) ;
				System.out.println("Goodbye, my name was " +user.getNickname()) ;
				NI.removeUser(socket.getInetAddress()) ;
			} 
		*/
		//}
		
		} catch (BindException e1) {
			System.out.println("Port for UDP SocketReceiver already used.") ;
		} catch (SocketException e2) {
			System.out.println("Creation of UDP SocketReceiver failed.") ;
		} catch (IOException e3) {
			System.out.println("IOException during Receive.") ;
			// fermer la socket ?
		} catch (ClassNotFoundException e4) {
			System.out.println("Lecture du message en réception impossible.") ;			
		}
		finally {
			if (socket != null)
				socket.close() ;
		}
		

	}

	/** Redefiniton de la methode run du Runnable **/
	public void run() {
		listen() ;
	}
	
}
