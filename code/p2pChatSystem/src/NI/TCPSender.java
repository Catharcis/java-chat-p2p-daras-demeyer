package NI;

import java.net.InetAddress;
import java.util.ArrayList;

import Controler.User;
import Signals.AbstractMessage;

public class TCPSender extends AbstractSender {

	/*
	 * Méthode permettant d'envoyer un message en broadcast
	 */
	public void sendBroadcast(AbstractMessage message){
		
	}
	
	/*
	 * Méthode permettant d'envoyer un message à une liste d'utilisateurs
	 */
	public void send(AbstractMessage message, ArrayList<String> listOfUsers, ArrayList<InetAddress> ipAddressesList){
		
	}
	
}
