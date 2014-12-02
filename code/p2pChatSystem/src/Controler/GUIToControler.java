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
	
	private static GUIToControler guiToContSingleton;
	
	private static NIControler niCon;
	
	private static NetworkInformation NI;
	
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private GUIToControler(){
		NI = NI.getInstance();
	}
	
	
	/** Méthode qui permet de récupérer l'instance de la classe **/
	public static GUIToControler getInstance(){
		if (guiToContSingleton == null) {
			guiToContSingleton = new GUIToControler() ;
		}
		return guiToContSingleton ;
	}
	

	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/
	
	public void setGuiCon (NIControler niCont) {
		this.niCon = niCont.getInstance();
	}
	
	
	/************************************************* 
	 * 					METHODS
	 ************************************************/
	
	/** Différentes méthodes de type perform() permettant d'envoyer un signal au NI**/
	
	public void performConnect(String name){
		NI.setLocalUser(name);
		try {
			String nameWithPattern = NI.getNicknameWithIP(NI.getLocalUser());
			niCon.sendHello(nameWithPattern);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (niCon.getUDPReceiver().getSocket().isClosed()){
			niCon.createThreadUDPReceiver();
		}
		
		NI.notifyLastChange(typeOfChange.CONNECTION);
	}
	
	public void performSendHelloAck(User destUser){
		try {
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
			String nameWithPattern = NI.getNicknameWithIP(NI.getLocalUser());
			niCon.getUDPReceiver().setStateListen(false);
			niCon.sendGoodbye(nameWithPattern);
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
		
		niCon.sendTextMessage(nicknameList, message, ipAddressesList);
		
	}
	
	public void performSendFile(){
		
	}
	
	public String getNicknameOfId(int id){
		
		String nickname = null;
		User user = null;
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
	
	public void addIDListModel(int id){
		
		NI.getArrayPositionsListModel().add(id);
		
	}
	
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
