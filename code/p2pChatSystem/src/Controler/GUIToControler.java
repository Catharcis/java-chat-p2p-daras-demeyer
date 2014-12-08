package Controler;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import GUI.GUIControler;
import NI.NIControler;


public class GUIToControler {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
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
	
	// Constructeur par defaut
	private GUIToControler(){
		// On recupere l'instance du NI
		NI = NI.getInstance();
	}
	
	
	/** Methode qui permet de recuperer l'instance de la classe **/
	public static GUIToControler getInstance(){
		if (guiToContSingleton == null) {
			guiToContSingleton = new GUIToControler() ;
		}
		return guiToContSingleton ;
	}
	

	/************************************************* 
	 * 				GETTERS & SETTERS
	 * @throws UnknownHostException 
	 ************************************************/
	
	// Permet d'assigner la valeur du NIControler
	public void setNiCon (NIControler niCont) {
		this.niCon = niCont.getInstance();
	}
	
	public NetworkInformation getNetInfo () {
		return this.NI ;
	}
	
	/************************************************* 
	 * 					METHODS
	 ************************************************/
	
	/** Differentes methodes de type perform() permettant d'envoyer un signal au NI**/
	
	public void performConnect(String name){
		
		// On crée le local user grâce à son nom
		NI.setLocalUser(name);
		try {
			// On envoie un hello sur le réseau avec : "nom@IP"
			String nameWithPattern = NI.getNicknameWithIP(NI.getLocalUser());
			niCon.sendHello(nameWithPattern);
		} catch (UnknownHostException e) {
			System.out.println("GUIToControler - PERFORM CONNECT : UnknownHostException") ;
		}
		
		// Si la socket est dans l'etat "closed", cela indique qu'elle a deja ete ouverte et qu'il faut la remplace
		if (niCon.getUDPReceiver().getSocket().isClosed()){
			niCon.createThreadUDPReceiver();
		}
		
		// On notifie la vue du dernier changement
		NI.notifyLastChange(typeOfChange.CONNECTION);
	}
	
	public void performSendHelloAck(int idDestUser){
		try {
			// On envoie un HelloAck a celui qui nous a envoye un Hello
			String localNameWithPattern = NI.getNicknameWithIP(NI.getLocalUser());
			String destNameWithPattern = NI.getNicknameWithIP(this.NI.getUserWithId(idDestUser));
			niCon.sendHelloAck(localNameWithPattern,destNameWithPattern,NI.getIPAddressOfUser(this.NI.getUserWithId(idDestUser)));
		} catch (UnknownHostException e) {
			System.out.println("GUIToControler - PERFORM SEND HELLO ACK : UnknownHostException") ;
		}
	}
	
	public void performDisconnect(){
		try {
			// On envoie un Goodbye à l'ensemble du reseau
			String nameWithPattern = NI.getNicknameWithIP(NI.getLocalUser());
			niCon.getUDPReceiver().setStateListen(false);
			niCon.sendGoodbye(nameWithPattern);
			// On notifie la vue et on réinitialise des variables
			NI.notifyLastChange(typeOfChange.DISCONNECTION);
			NI.reinitializeVariables();
		} catch (UnknownHostException e) {
			System.out.println("GUIToControler - PERFORM DISCONNECT : UnknownHostException") ;
		}		
	}
	
	public void performSendTextMessage(String message, TreeSet <Integer> listOfId) throws UnknownHostException{
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
		Iterator l = listOfId.iterator() ;
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
	
	public void performSendFile(File file, TreeSet<Integer> listOfId) throws UnknownHostException{
		
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
			System.out.println("GUITOCon - PERFORM FILE TEXT MESSAGE - New historic : Me : Send file - "+file.getName()) ;
			NI.getHistoricConversations().put(listOfId, "Me : Sending file - "+file.getName()+"\n") ;
		}
		
		NI.notifyLastChange(typeOfChange.NEWINCOMINGFILEMESSAGE, listOfId) ;
		
		/** Construction d'une List de String sous format NICKNAME@XX.XX.XX.XX**/
		ArrayList<String> nicknameList = new ArrayList <String> () ;
		ArrayList<InetAddress> ipAddressesList = new ArrayList<InetAddress>();
		Iterator l = listOfId.iterator() ;
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
	 * @param id : id du User
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
