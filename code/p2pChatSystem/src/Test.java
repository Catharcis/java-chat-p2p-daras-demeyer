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
	netToCon = netToCon.getInstance() ;
	
	GUIToControler guiToCon = null ;
	guiToCon = guiToCon.getInstance() ;
	
	/** Initialisation du NI **/
	NIControler niCon = null ;
	niCon = niCon.getInstance() ;
	
	/** Initialisation de la GUI **/
	GUIView guiView = null ;
	guiView = guiView.getInstance() ;
	
	/** Creation des liens **/
	netToCon.getNetInfo().setGuiView(guiView);
	guiToCon.setGuiCon(niCon);
	guiView.getChatFenetre().setGuiView(guiView);
	
}
	
	public static void main(String[] args) throws UnknownHostException {
		initChatSystem() ;
	}

}
