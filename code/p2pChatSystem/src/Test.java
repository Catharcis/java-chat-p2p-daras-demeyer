import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Controler.GUIToControler;
import Controler.NetworkInformation;
import Controler.User;
import NI.NIControler;
import Signals.*;



public class Test {

	public static void main(String[] args) throws UnknownHostException {
		NetworkInformation NI = null;
		NI = NI.getInstance() ;
		GUIToControler guiToCont = null;
		guiToCont = guiToCont.getInstance();
		//guiToCont.performConnect();
		//guiToCont.performDisconnect();
		//guiToCont.performSendHelloAck(NI.getLocalUser());
		
		ArrayList<User> liste = new ArrayList<User>() ;
		liste.add(NI.getLocalUser());
		guiToCont.performSendTextMessage("Hello binome, ça marche !!!!!!!!!!!!!!!!!!",liste);
	}

}
