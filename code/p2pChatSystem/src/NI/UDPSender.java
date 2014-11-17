package NI;

import java.util.ArrayList;

import Signals.*;
import userModel.*;

public class UDPSender extends AbstractSender {

	// On a comme champ le singleton de la classe
	private static UDPSender singleton = null;
	
	
	/*
	 * On définit les constructeurs
	 */
	private UDPSender(){
		this.setPort(5000);
	}
	
	/*
	 * Méthode pour récupérer l'instance 
	 */
	public static UDPSender getInstanceUDPReceiver(){
		if (singleton == null){
			singleton = new UDPSender();
		}
		return singleton;
	}
	
	/*
	 * Méthode permettant d'envoyer un message en broadcast
	 */
	public void sendBroadcast(AbstractMessage message){
		
	}
	
	/*
	 * Méthode permettant d'envoyer un message à une liste d'utilisateurs
	 */
	public void send(AbstractMessage message, ArrayList<User> listOfUsers){
		
	}
	
}
