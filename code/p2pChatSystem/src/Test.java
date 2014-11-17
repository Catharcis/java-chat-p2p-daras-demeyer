import NI.NIControler;
import Signals.*;



public class Test {

	public static void main(String[] args) {
		NIControler NI = null ;
		NI = NI.getInstance() ;
		NI.getUDPReceiver().listen() ;
	}

}
