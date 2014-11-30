package GUI;

import Controler.GUIToControler;
import NI.NIControler;
import NI.TCPSender;
import NI.TCPServer;
import NI.UDPReceiver;
import NI.UDPSender;

public class GUIControler {
	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private static GUIControler GUISingleton ;
	
	private GUIToControler guiToCon ;
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private GUIControler () {
		guiToCon = guiToCon.getInstance() ;
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
	
	public GUIToControler getGUIToControler(){
		return guiToCon;
	}

	
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/
	
	protected void newConnection (String name) {
		if (guiToCon == null) {
			System.out.println("Erreur in NewConnection - GUIControler : guiToCon null") ;
		}
		else 
			guiToCon.performConnect(name) ;
	}
}
