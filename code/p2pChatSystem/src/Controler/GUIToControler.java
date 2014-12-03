package Controler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
	
	// Constructeur par défaut
	private GUIToControler(){
		// On récupère l'instance du NI
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
	 ************************************************/
	
	// Permet d'assigner la valeur du NIControler
	public void setNiCon (NIControler niCont) {
		this.niCon = niCont.getInstance();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Si la socket est dans l'etat "closed", cela indique qu'elle a deja ete ouverte et qu'il faut la remplace
		if (niCon.getUDPReceiver().getSocket().isClosed()){
			niCon.createThreadUDPReceiver();
		}
		
		// On notifie la vue du dernier changement
		NI.notifyLastChange(typeOfChange.CONNECTION);
	}
	
	public void performSendHelloAck(User destUser){
		try {
			// On envoie un HelloAck à celui qui nous a envoyé un Hello
			String localNameWithPattern = NI.getNicknameWithIP(NI.getLocalUser());
			String destNameWithPattern = NI.getNicknameWithIP(destUser);
			niCon.sendHelloAck(localNameWithPattern,destNameWithPattern,NI.getIPAddressOfUser(destUser));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void performSendTextMessage(String message, ArrayList<User> userList) throws UnknownHostException{
		
		/** Construction d'une List de String sous format NICKNAME@XX.XX.XX.XX**/
		ArrayList<String> nicknameList = new ArrayList <String> () ;
		ArrayList<InetAddress> ipAddressesList = new ArrayList<InetAddress>();
		for (int i = 0 ; i< userList.size() ; i++) {
			/** On recupere les informations reseaux **/
			nicknameList.add(NI.getNicknameWithIP(userList.get(i))) ;
			ipAddressesList.add(NI.getIPAddressOfUser(userList.get(i)));
		}
		
		// On envoie le message sur le reseau
		niCon.sendTextMessage(nicknameList, message, ipAddressesList);
		
	}
	
	public void performSendFile(){
		
	}
	
	/**
	 * Permet d'obtenir le nom d'un User en connaissant son id
	 * @param id : id du User
	 * @return le nom du User
	 */
	public String getNicknameOfId(int id){
		
		String nickname = null;
		User user = null;
		// On parcourt l'ensemble de la hashmap pour retrouver l'utilisateur
		Collection<User> listOfUsers = NI.getUserList().values();
		Iterator<User> it = listOfUsers.iterator();
		while (it.hasNext() && nickname == null){
			user = it.next();
			if (user.getIdUser() == id){
				nickname = user.getNickname();
			}
		}
	
		return nickname;
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
		int compteur = -1;
		ArrayList<Integer> array = NI.getArrayPositionsListModel();
		Iterator<Integer> it = array.iterator();
		while (it.hasNext() && !find){
			compteur++;
			if (it.next() == id){
				find = true;
			}
		}
		NI.getArrayPositionsListModel().remove(compteur);
		
	}
}
