package Controler;

import java.util.ArrayList;

import userModel.*;

public class NetworkToControler {

	private static NetworkToControler netToContSingleton;
	
	
	private NetworkToControler(){
		
	}
	
	/** M�thode qui permet d'obtenir l'instance de la classe **/
	public static NetworkToControler getInstance(){
			if (netToContSingleton == null) {
				netToContSingleton = new NetworkToControler() ;
			}
			return netToContSingleton ;
	}
	
	
	/** M�thodes de type process() indiquant la r�ception d'un message sur le r�seau au GUI **/
	
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
