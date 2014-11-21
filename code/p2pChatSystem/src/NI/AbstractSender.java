package NI;

import java.net.InetAddress;
import java.util.ArrayList;

import Controler.User;
import Signals.*;

public abstract class AbstractSender extends AbstractNetwork {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	/** Port d'envoi **/
	private int portEnvoi ;

	
	
	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/
	
	/** Permet d'obtenir le port d'envoi **/
	public int getPortEnvoi(){
		return this.portEnvoi ;
	}
	
	/** Permet de modifier le port d'envoi **/
	public void setPortEnvoi(int newPort){
	    this.portEnvoi = newPort;
	}
	
	
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/
	
	/** Permet d'envoyer un message en UDP en broadcast sur le réseau **/
	public abstract void sendBroadcast(AbstractMessage message);
	
	/** Permet d'envoyer un TextMessage en UDP à la liste d'utilisateurs en paramètres **/
	public abstract void send(AbstractMessage message, ArrayList<String> listDest, ArrayList<InetAddress> listIPDest);
	

	
}
