import java.net.InetAddress;
import java.net.UnknownHostException;

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
		guiToCont.performConnect();
		System.out.println(NI.getNicknameWithoutIP("nickname2Test@192.168.1.16"));
	}

}
