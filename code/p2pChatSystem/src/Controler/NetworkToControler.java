package Controler;

import java.util.ArrayList;

import userModel.*;

public class NetworkToControler {

	private static NetworkToControler netToContSingleton;
	
	
	private NetworkToControler(){
		
	}
	
	/** Méthode qui permet d'obtenir l'instance de la classe **/
	public static NetworkToControler getInstance(){
			if (netToContSingleton == null) {
				netToContSingleton = new NetworkToControler() ;
			}
			return netToContSingleton ;
	}
	
	
	/** Méthodes de type process() indiquant la réception d'un message sur le réseau au GUI **/
	
	public void processHello(User user){
		
	}
	
	public void processHelloAck(User user){
		
	}
	
	public void processGoodbye(User user){
		
	}
	
	public void processTextMessage(String message, ArrayList<User> listOfUsers){
		
	}
	
	public void processFileMessage(){
		
	}
}
