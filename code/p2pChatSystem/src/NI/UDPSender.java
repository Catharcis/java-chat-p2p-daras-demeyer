package NI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

import Controler.NetworkInformation;
import Signals.*;

public class UDPSender extends AbstractSender {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private static UDPSender singleton = null;
	
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private UDPSender(){
		this.setPortEnvoi(5000);
		this.setPortEcoute(9876) ;
	}
	
	/*
	 * Méthode pour récupérer l'instance 
	 */
	public static UDPSender getInstanceUDPSender(){
		if (singleton == null){
			singleton = new UDPSender();
		}
		return singleton;
	}
	
	
	/************************************************* 
	 * 					METHODS
	 ************************************************/
	
	/**Methode permettant d'envoyer un message en broadcast **/
	public void sendBroadcast(AbstractMessage message){
		/** creation d'un socket UDP**/
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket (this.getPortEnvoi()) ;
			System.out.println("Creation du socket UDPSender") ;
			

			/** Creation du Datagram Packet **/
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutput out = null;
			try {
				out = new ObjectOutputStream(bos);
			  	out.writeObject(message);
			  	byte[] buf = bos.toByteArray();
			  	DatagramPacket packet = new DatagramPacket (buf, buf.length, InetAddress.getByName("255.255.255.255"), this.getPortEcoute()) ;
			  	System.out.println("Paquet concu ! ") ;

			  	/** Envoi du paquet **/
			  	socket.send(packet);
			  	System.out.println("Paquet envoy�!") ;
			} catch (IOException e) {
				System.out.println("Erreur lors de l'�criture dans le UDP Sender") ;
			}

		} catch (BindException e1) {
			System.out.println("Port for UDP SocketSender already used.") ;
		} catch (SocketException e2) {
			System.out.println("Creation of UDP SocketSender failed.") ;
		} finally {
			if (socket != null)
				socket.close() ;
		}
	}
	
	/** Methode permettant d'envoyer un message à une liste d'utilisateurs **/
	public void send(AbstractMessage message, ArrayList<String> listOfUsers, ArrayList<InetAddress> ipAddressesList){
		/** On r�cup�re les informations r�seaux **/
		NetworkInformation NI = null;
		NI = NI.getInstance();
		
		/** Creation d'un socket UDP**/
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket (this.getPortEnvoi()) ;
			System.out.println("Creation du socket UDPSender") ;
			
			/** Envoi des paquets a chaque User de la liste **/
			for (int i = 0; i < ipAddressesList.size(); i++) {
			
				/** Creation du Datagram Packet **/
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutput out = null;
				try {
					out = new ObjectOutputStream(bos);
				  	out.writeObject(message);
				  	byte[] buf = bos.toByteArray();
				  	DatagramPacket packet = new DatagramPacket (buf, buf.length, ipAddressesList.get(i), this.getPortEcoute()) ;
				  	System.out.println("Paquet concu ! Adresse IP destinataire : " + ipAddressesList.get(i)) ;
	
				  	/** Envoi du paquet **/
				  	socket.send(packet);
				  	System.out.println("Paquet envoy�!") ;
				} catch (IOException e) {
					System.out.println("Erreur lors de l'�criture dans le UDP Sender") ;
				}
			}
		} catch (BindException e1) {
			System.out.println("Port for UDP SocketSender already used.") ;
		} catch (SocketException e2) {
			System.out.println("Creation of UDP SocketSender failed.") ;
		} finally {
			if (socket != null)
				socket.close() ;
		}
	}
}
	
