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

	/**
	 * Enumeration des etats de l'utilisateur : connected ou disconnected
	 */
	enum Etats {connected, disconnected } ;
	
	/**
	 * Singleton GUIControler
	 */
	private static GUIControler GUISingleton ;
	
	/**
	 * Field vers la classe GUIToControler
	 */
	private GUIToControler guiToCon ;
	
	/**
	 * Attribut correspondant a l'etat actuel de l'utilisateur
	 */
	private Etats etat ;
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	/**
	 * Constructeur prive par defaut
	 */
	private GUIControler () {
		guiToCon = GUIToControler.getInstance() ;
		etat = Etats.disconnected ;
	}
	
	/**
	 * Methode permettant de recuperer l'instance singleton
	 * @return GUIControler
	 */
	public static GUIControler getInstance () {
		if (GUISingleton == null) {
			GUISingleton = new GUIControler () ;
		}
		return GUISingleton ;
	}
	
	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/
	
	/**
	 * Getter du GUIToControler
	 * @return lien vers le guiToCon
	 */
	public GUIToControler getGUIToControler() {
		return guiToCon;
	}

	/**
	 * Getter de l'etat actuel de l'utilisateur
	 * @return etat
	 */
	public Etats getEtat () {
		return etat ;
	}
	
	/**
	 * Methode permettant de passer a l'etat "connected"
	 */
	public void setEtatConnect () {
		etat = Etats.connected ;
	}
	
	/**
	 * Methode permettant de passer a l'etat "disconnected"
	 */
	public void setEtatDisconnect () {
		etat = Etats.disconnected ;
	}
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/
	
	/**
	 * Methode procedant a la connexion d'un user local
	 * @param nickname du user local
	 */
	protected void Connection (String name) {
		guiToCon.performConnect(name) ;
		
		/** Cas de la connexion **/
		this.setEtatConnect();
	}
	
	/**
	 * Methode procedant a la deconnexion d'un user local
	 */
	protected void Disconnection () {
		guiToCon.performDisconnect();
		
		/** Cas de la deconnexion **/
		this.setEtatDisconnect();
	}
	
	/**
	 * Methode procedant a l'envoi d'un textMessage
	 * @param message : le message a envoyer
	 * @param listOfId : la liste des ID auxquels envoyer le message
	 */
	protected void TextMessage (String message, TreeSet <Integer> listOfId) {
		guiToCon.performSendTextMessage(message, listOfId);
	}
	
	/**
	 * Methode procedant a l'envoi d'un fichier
	 * @param file : le fichier a envoyer
	 * @param listOfId : la liste des ID auxquels envoyer le message
	 */
	protected void FileMessage (File file, TreeSet <Integer> listOfId) {
		guiToCon.performSendFile(file, listOfId);
	}
	
}
