package Controler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.TreeSet;

import GUI.GUIControler;


public class NetworkToControler {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	// Reference a NetworkToControler
	private static NetworkToControler netToContSingleton ;
	
	// Reference a GUIControler
	private static GUIControler guiCon;
	
	// Reference a NetworkInformation
	private static NetworkInformation NI;
	
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	/**
	 * Constructeur par defaut
	 */
	private NetworkToControler(){
		NI = NI.getInstance();
	}
	
	/** Methode qui permet d'obtenir l'instance de la classe **/
	public static NetworkToControler getInstance(){
			if (netToContSingleton == null) {
				netToContSingleton = new NetworkToControler() ;
			}
			return netToContSingleton ;
	}

	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/
	
	/**
	 * Permet de fixer le GUIControler
	 * @param guiCont : le guiControler
	 */
	public void setGuiCon (GUIControler guiCont) {
		this.guiCon = guiCont.getInstance();
	}
	
	/**
	 * Getter recuperant NetworkInformation
	 * @return NetworkInformation
	 */
	public NetworkInformation getNetInfo () {
		return NI ;
	}
	
	/************************************************* 
	 * 					METHODS
	 ************************************************/
	
	/** 
	 * Methode permettant de traiter la reception d'un Hello sur le reseau
	 * @param name : nom de la personne envoyant le message (au format "nom@IP")
	 * @param ipAddress : l'adresse IP de la personne
	 */
	public void processHello(String name, InetAddress ipAddress){
		// ajout du nouvel user
		String nameWithoutPattern = NI.getNicknameWithoutIP(name);
		NI.addUser(nameWithoutPattern, ipAddress, false) ;
	}
	
	/**
	 * Methode permettant de traiter la reception d'un HelloAck sur le reseau
	 * @param name : nom de la personne envoyant le message (au format "nom@IP")
	 * @param ipAddress : l'adresse IP de la personne
	 */
	public void processHelloAck(String name, InetAddress ipAddress){
		String nameWithoutPattern = NI.getNicknameWithoutIP(name);
		NI.addUser(nameWithoutPattern, ipAddress, true) ;
	}
	
	/**
	 * Methode permettant de traiter la reception d'un Goodbye sur le reseau
	 * @param name : nom de la personne envoyant le message (au format "nom@IP")
	 * @param ipAddress : l'adresse IP de la personne
	 */
	public void processGoodbye(String name, InetAddress ipAddress){
		if (NI.getUserList().containsKey(ipAddress))
			NI.removeUser(ipAddress);
	}
	
	/**
	 * Methode permettant de triater la reception d'un TextMessage sur le reseau
	 * @param message : le message envoye aux utilisateurs
	 * @param listOfUsernames : la liste des noms d'utilisateurs
	 * @throws UnknownHostException
	 */
	public void processTextMessage(String message, ArrayList<String> listOfUsernames) throws UnknownHostException{
		
		// On crée une liste d'ID Utilisateurs
		TreeSet<Integer> listOfIDs = new TreeSet<Integer>();
		
		// On déclare un utilisateur pointant sur null pour une utilisation par la suite
		User user = null;
		String ipString = null;
		InetAddress ip = null;
		
		// On récupère l'ensemble des id de chaque utilisateur concerné par le message
		for (int i = 0; i < listOfUsernames.size(); i++){
			ipString = NI.getIPOfPattern(listOfUsernames.get(i));
			ip = InetAddress.getByName(ipString);
			if ((user = NI.getUserList().get(ip)) != null){
				listOfIDs.add(user.getIdUser());
			}
			else{
				System.out.println("ERREUR - ProcessTextMessage - User who has the ip address "+ip+" doesn't exist");
			}
		}
		
		System.out.println("Liste des utilisateurs concernes : " + listOfIDs.toString());
		System.out.println("Message : " + message);
		
		// On definit le format d'affichage du message
		String finalMessage = user.getNickname()+" : "+message+"\n";
		
		// Ajouter le message a� l'historique et notifier la vue
		if (NI.getHistoricConversations().containsKey(listOfIDs)){
			NI.getHistoricConversations().get(listOfIDs).concat(finalMessage);
		}
		else {
			NI.getHistoricConversations().put(listOfIDs, finalMessage);
		}
		
		// Notification envoyée à la vue
		NI.notifyLastChange(typeOfChange.NEWINCOMINGTEXTMESSAGE, listOfIDs);
	}
	
	public void processFileMessage(){
		
	}
}
