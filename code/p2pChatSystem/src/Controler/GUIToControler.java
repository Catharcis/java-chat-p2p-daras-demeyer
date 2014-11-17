package Controler;

public class GUIToControler {

	private String myNickname;
	
	private static GUIToControler guiToContSingleton;
	
	
	private GUIToControler(){
		
	}
	
	
	/** M�thode qui permet de r�cup�rer l'instance de la classe **/
	public static GUIToControler getInstance(){
		if (guiToContSingleton == null) {
			guiToContSingleton = new GUIToControler() ;
		}
		return guiToContSingleton ;
	}
	
	
	/** Diff�rentes m�thodes de type perform() permettant d'envoyer un signal au NI**/
	
	public void performConnect(){
		
	}
	
	public void performDisconnect(){
		
	}
	
	public void performSendTextMessage(){
		
	}
	
	public void performSendFile(){
		
	}
}
