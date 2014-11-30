import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Controler.GUIToControler;
import Controler.NetworkInformation;
import Controler.NetworkToControler;
import Controler.User;
import GUI.GUIControler;
import GUI.GUIView;
import NI.NIControler;
import Signals.*;




public class Test {


public static void initChatSystem () {
	/** Initialisation du Controler **/
	NetworkToControler netToCon = null ;
	netToCon.getInstance() ;
	
	GUIToControler guiToCon = null ;
	guiToCon.getInstance() ;
	
	/** Initialisation du NI **/
	NIControler niCon = null ;
	niCon = niCon.getInstance() ;
	
	/** Initialisation de la GUI **/
	GUIView guiView = null ;
	guiView = guiView.getInstance() ;
	
	/** Creation des liens **/
	guiView.getChatFenetre().setGuiView(guiView);
	if (netToCon == null) {
		System.out.println("netToCon null") ;
	}
	else 
		netToCon.getNetInfo().setGuiView(guiView);

}
	
	public static void main(String[] args) throws UnknownHostException {
		initChatSystem() ;
	}

}
