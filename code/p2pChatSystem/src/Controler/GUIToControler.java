package Controler;

import userModel.User;

public class GUIToControler {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private static GUIToControler guiToContSingleton;
	
	// attribut pour la relation avec le NI !
	
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private GUIToControler(){
		// getInstance du NI
	}
	
	
	/** Méthode qui permet de récupérer l'instance de la classe **/
	public static GUIToControler getInstance(){
		if (guiToContSingleton == null) {
			guiToContSingleton = new GUIToControler() ;
		}
		return guiToContSingleton ;
	}
	
	
	/************************************************* 
	 * 					METHODS
	 ************************************************/
	
	/** Différentes méthodes de type perform() permettant d'envoyer un signal au NI**/
	
	public void performConnect(){
		
	}
	
	public void performDisconnect(){
		
	}
	
	public void performSendTextMessage(){
		
	}
	
	public void performSendFile(){
		
	}
}
