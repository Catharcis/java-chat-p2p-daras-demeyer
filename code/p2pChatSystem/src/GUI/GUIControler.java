package GUI;

import Controler.GUIToControler;

public class GUIControler {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	enum Etats {connected, disconnected } ;
	
	private static GUIControler GUISingleton ;
	
	private GUIToControler guiToCon ;
	
	private Etats etat ;
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private GUIControler () {
		guiToCon = guiToCon.getInstance() ;
		etat = Etats.disconnected ;
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
	
	public GUIToControler getGUIToControler(){
		return guiToCon;
	}

	public Etats getEtat () {
		return etat ;
	}
	
	public void setEtatConnect () {
		etat = Etats.connected ;
	}
	
	public void setEtatDisconnect () {
		etat = Etats.disconnected ;
	}
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/
	
	protected void Connection (String name) {
		guiToCon.performConnect(name) ;
		
		/** Cas de la connexion **/
		this.setEtatConnect();
	}
	
	protected void Disconnection () {
		guiToCon.performDisconnect();
		
		/** Cas de la deconnexion **/
		this.setEtatDisconnect();
	}
}
