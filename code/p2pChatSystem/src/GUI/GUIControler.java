package GUI;

import NI.NIControler;
import NI.TCPSender;
import NI.TCPServer;
import NI.UDPReceiver;
import NI.UDPSender;

public class GUIControler {
	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private static GUIControler GUISingleton ;
	
	
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private GUIControler () {
		
	}
	
	public static GUIControler getInstance () {
		if (GUISingleton == null) {
			GUISingleton = new GUIControler () ;
		}
		return GUISingleton ;
	}
	
	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/
	

	
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/
	
	/*
	 * Permet d'ajouter un utilisateur à une liste visuelle
	 */
	public void addUserToVisualList(){
		
	}
}
