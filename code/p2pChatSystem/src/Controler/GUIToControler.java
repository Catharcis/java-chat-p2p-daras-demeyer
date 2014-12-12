package Controler;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.TreeSet;
import NI.NIControler;

/**
 * @author Val�rie Daras et Alexandre Demeyer
 */

public class GUIToControler {

	/************************************************* 
	 * 				ATTRIBUTS and FIELDS 
	 ************************************************/
	
	// Reference a GUIToControler
	private static GUIToControler guiToContSingleton;
	
	// Reference a NIControler
	private static NIControler niCon;
	
	// Reference a NetworkInformation
	private static NetworkInformation NI;
	
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	/**
	 * Constructeur par defaut
	 */
	private GUIToControler() {
		// On recupere l'instance du NI
		NI = NetworkInformation.getInstance();
	}
	
	
	/**
	 * Methode qui permet de recuperer l'instance de la classe
	 * @return l'objet GUIToControler
	 */
	public static GUIToControler getInstance() {
		if (guiToContSingleton == null) {
			guiToContSingleton = new GUIToControler() ;
		}
		return guiToContSingleton ;
	}
	

	/************************************************* 
	 * 				GETTERS & SETTERS
	 * @throws UnknownHostException 
	 ************************************************/
	
	/**
	 *  Permet d'assigner la valeur du NIControler
	 * @param niCont : le NIControler
	 */
	public void setNiCon (NIControler niCont) {
		GUIToControler.niCon = NIControler.getInstance();
	}
	
	/**
	 * Permet de r�cup�rer l'objet NetworkInformation
	 * @return l'objet NetworkInformation
	 */
	public NetworkInformation getNetInfo () {
		return GUIToControler.NI ;
	}
	
	/************************************************* 
	 * 					METHODS
	 ************************************************/
	
	/** 
	 * Permet de notifier le NIControler d'envoyer un Hello
	 * @param name : nom de l'utilisateur local
	 */
	public void performConnect(String name) {
		
		// On crée le local user grâce à son nom
		NI.setLocalUser(name);
		// On envoie un hello sur le réseau avec : "nom@IP"
		String nameWithPattern = NI.getNicknameWithIP(NI.getLocalUser());
		niCon.sendHello(nameWithPattern);
		
		// Si la socket est dans l'etat "closed", cela indique qu'elle a deja ete ouverte et qu'il faut la remplace
		if (niCon.getUDPReceiver().getSocket().isClosed()){
			niCon.createThreadUDPReceiver();
		}
		
		// On notifie la vue du dernier changement
		NI.notifyLastChange(typeOfChange.CONNECTION);
	}
	
	/**
	 * Permet de notifier le NIControler d'envoyer un HelloAck
	 * @param idDestUser : id de l'utilisateur distant
	 */
	public void performSendHelloAck(int idDestUser){
			// On envoie un HelloAck a celui qui nous a envoye un Hello
			String localNameWithPattern = NI.getNicknameWithIP(NI.getLocalUser());
			String destNameWithPattern = NI.getNicknameWithIP(GUIToControler.NI.getUserWithId(idDestUser));
			niCon.sendHelloAck(localNameWithPattern,destNameWithPattern,NI.getIPAddressOfUser(GUIToControler.NI.getUserWithId(idDestUser)));
	}
	
	/**
	 * Permet de notifier le NIControler d'envoyer un Goodbye
	 */
	public void performDisconnect() {
			// On envoie un Goodbye à l'ensemble du reseau
			String nameWithPattern = NI.getNicknameWithIP(NI.getLocalUser());
			niCon.getUDPReceiver().setStateListen(false);
			niCon.getTCPServer().setStateListen(false) ;
			try {
				niCon.getTCPServer().getServerSocket().close();
			} catch (IOException e) {
				System.out.println("GUI TO CONTROLER - performDisconnect : Error during closing socket from TCPServer") ;
			}
			niCon.sendGoodbye(nameWithPattern);
			// On notifie la vue et on réinitialise des variables
			NI.notifyLastChange(typeOfChange.DISCONNECTION);
			NI.reinitializeVariables();
	}
	
	/**
	 * Permet de notifier le NIControler d'envoyer un TextMessage
	 * @param message : le message a envoye
	 * @param listOfId : la liste des id des utilisateurs concernes
	 */
	public void performSendTextMessage(String message, TreeSet <Integer> listOfId) {
		/** Gestion des conversations **/
		/** Conversation existante **/
		if (NI.getHistoricConversations().containsKey(listOfId)) {
			/** ajout du message a envoyer **/
			String historic = NI.getHistoricConversations().get(listOfId) + "Me : "+message+"\n";
			System.out.println("GUITOCon - PERFORM SEND TEXT MESSAGE - historic : "+historic) ;
			NI.getHistoricConversations().put(listOfId, historic);
		}
		/** Conversation non existante et donc a creer **/
		else {
			System.out.println("GUITOCon - PERFORM SEND TEXT MESSAGE - New historic : Me : "+message) ;
			NI.getHistoricConversations().put(listOfId, "Me : "+message+"\n") ;
		}
		NI.notifyLastChange(typeOfChange.NEWINCOMINGTEXTMESSAGE, listOfId) ;
		
		/** Construction d'une List de String sous format NICKNAME@XX.XX.XX.XX**/
		ArrayList<String> nicknameList = new ArrayList <String> () ;
		ArrayList<InetAddress> ipAddressesList = new ArrayList<InetAddress>();
		for (Integer i : listOfId) {
			int id = (int) i ;
			User user = NI.getUserWithId(id) ;
			
			/** On recupere les informations reseaux **/
			nicknameList.add(NI.getNicknameWithIP(user)) ;
			ipAddressesList.add(NI.getIPAddressOfUser(user));
		}
		
		// On envoie le message sur le reseau
		niCon.sendTextMessage(nicknameList, message, ipAddressesList);
		
	}
	
	/**
	 * Permet de notifier le NIControler d'envoyer un FileMessage
	 * @param file : le fichier a envoye
	 * @param listOfId : la liste des id des utilisateurs concernes
	 */
	public void performSendFile(File file, TreeSet<Integer> listOfId) {
		
		/** Gestion des conversations **/
		/** Conversation existante **/
		if (NI.getHistoricConversations().containsKey(listOfId)) {
			/** ajout du file a envoyer **/
			String historic = NI.getHistoricConversations().get(listOfId) + "Me : Sending file - "+file.getName()+"\n";
			System.out.println("GUITOCon - PERFORM SEND FILE MESSAGE - historic : "+historic) ;
			NI.getHistoricConversations().put(listOfId, historic);
		}
		/** Conversation non existante et donc a creer **/
		else {
			System.out.println("GUITOCon - PERFORM FILE TEXT MESSAGE - New historic : Me : Sending file - "+file.getName()) ;
			NI.getHistoricConversations().put(listOfId, "Me : Sending file - "+file.getName()+"\n") ;
		}
		
		NI.notifyLastChange(typeOfChange.NEWINCOMINGFILEMESSAGE, listOfId) ;
		
		/** Construction d'une List de String sous format NICKNAME@XX.XX.XX.XX**/
		ArrayList<String> nicknameList = new ArrayList <String> () ;
		ArrayList<InetAddress> ipAddressesList = new ArrayList<InetAddress>();
		for (Integer i : listOfId) {
			int id = (int) i ;
			User user = NI.getUserWithId(id) ;
			
			/** On recupere les informations reseaux **/
			nicknameList.add(NI.getNicknameWithIP(user)) ;
			ipAddressesList.add(NI.getIPAddressOfUser(user));
		}
		
		// On envoie le message sur le reseau
		niCon.sendFileMessage(nicknameList, file, ipAddressesList);
		
	}
	
	
	/**
	 * Permet d'ajouter l'id user a la liste des positions du composant graphique JList
	 * @param id : id de l'utilisateur
	 */
	public void addIDListModel(int id){
		NI.getArrayPositionsListModel().add(id);	
	}
	
	/**
	 * Permet de retirer l'id user en parametre a la liste des positions du composant graphique JList
	 * @param id : id de l'utilisateur
	 */
	public void removeIDListModel(int id){
		boolean find = false;
		int compteur = 0 ;
		while (!find && compteur<NI.getArrayPositionsListModel().size()) {
			if (NI.getArrayPositionsListModel().get(compteur) == id) {
				find = true ;
			}
			compteur ++ ;
		}
		if (find == true) {
			NI.getArrayPositionsListModel().remove(compteur-1);
		}
	}
}
