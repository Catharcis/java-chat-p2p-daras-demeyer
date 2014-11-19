import java.net.InetAddress;
import java.net.UnknownHostException;

import NI.NIControler;
import Signals.*;



public class Test {

	public static void main(String[] args) throws UnknownHostException {
		NIControler NI = null ;
		NI = NI.getInstance() ;
		NI.sendHello(NI.getNetInfo().getLocalUser()) ;
	}

}
