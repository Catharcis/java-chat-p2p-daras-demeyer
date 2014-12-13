package GUI;

import java.io.File;
import java.util.TreeSet;
import Controler.GUIToControler;

/**
 * @author Valérie Daras et Alexandre Demeyer
 */

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
		guiToCon = GUIToControler.getInstance() ;
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
	
	public GUIToControler getGUIToControler() {
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
	
	protected void TextMessage (String message, TreeSet <Integer> listOfId) {
		guiToCon.performSendTextMessage(message, listOfId);
	}
	
	protected void FileMessage (File file, TreeSet <Integer> listOfId) {
		guiToCon.performSendFile(file, listOfId);
	}
	
}
