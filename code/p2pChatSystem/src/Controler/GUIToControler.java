package Controler;

public class GUIToControler {

	private String myNickname;
	
	private static GUIToControler guiToContSingleton;
	
	
	private GUIToControler(){
		
	}
	
	
	/** Méthode qui permet de récupérer l'instance de la classe **/
	public static GUIToControler getInstance(){
		if (guiToContSingleton == null) {
			guiToContSingleton = new GUIToControler() ;
		}
		return guiToContSingleton ;
	}
	
	
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
