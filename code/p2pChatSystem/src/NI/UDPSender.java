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

import Signals.*;
import userModel.*;

public class UDPSender extends AbstractSender {

	// On a comme champ le singleton de la classe
	private static UDPSender singleton = null;
	
	
	/*
	 * On dÃ©finit les constructeurs
	 */
	private UDPSender(){
		this.setPortEnvoi(5000);
	}
	
	/*
	 * MÃ©thode pour rÃ©cupÃ©rer l'instance 
	 */
	public static UDPSender getInstanceUDPSender(){
		if (singleton == null){
			singleton = new UDPSender();
		}
		return singleton;
	}
	
	/*
	 * Methode permettant d'envoyer un message en broadcast
	 */
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
			  	System.out.println("Paquet envoyé!") ;
			} catch (IOException e) {
				System.out.println("Erreur lors de l'écriture dans le UDP Sender") ;
			}

		} catch (BindException e1) {
			System.out.println("Port for UDP SocketSender already used.") ;
		} catch (SocketException e2) {
			System.out.println("Creation of UDP SocketSender failed.") ;
		} catch (IOException e3) {
			System.out.println("IOException during Sending.") ;
		} finally {
			if (socket != null)
				socket.close() ;
		}
	}
	
	/*
	 * Methode permettant d'envoyer un message Ã  une liste d'utilisateurs
	 */
	public void send(AbstractMessage message, ArrayList<User> listOfUsers){
		/** On récupère les informations réseaux **/
		NetworkInformation NI = null;
		NI = NI.getInstance();
		
		/** Récupération des utilisateurs concernés **/
		ArrayList<InetAddress> listOfIPs = new ArrayList<InetAddress>();
		
		for (int i = 0; i < listOfUsers.size(); i++){
			listOfIPs.add(NI.getIPAddressOfUser(listOfUsers.get(i)));
		}
		
		for (int i = 0; i < listOfIPs.size(); i++){
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
				  	DatagramPacket packet = new DatagramPacket (buf, buf.length, listOfIPs.get(i), this.getPortEcoute()) ;
				  	System.out.println("Paquet concu ! Adresse IP destinataire : " + listOfIPs.get(i)) ;
	
				  	/** Envoi du paquet **/
				  	socket.send(packet);
				  	System.out.println("Paquet envoyé!") ;
				} catch (IOException e) {
					System.out.println("Erreur lors de l'écriture dans le UDP Sender") ;
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
	
}
