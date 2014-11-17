package NI;

import java.util.ArrayList;

import userModel.*;
import Signals.*;

public abstract class AbstractSender extends AbstractNetwork {

	// Pas d'attributs ni de champs
	
	/*
	 * Permet d'envoyer un message en UDP en broadcast sur le réseau 
	 */
	public abstract void sendBroadcast(AbstractMessage message);
	
	/*
	 * Permet d'envoyer un TextMessage en UDP à la liste d'utilisateurs en paramètres
	 */
	public abstract void send(AbstractMessage message, ArrayList<User> listDest);
	

}
