import NI.NIControler;
import Signals.Hello;


public class Test2 {

	public static void main(String[] args) {
		NIControler NI = null ;
		NI = NI.getInstance() ;
		NI.getUDPSender().sendBroadcast(new Hello("Valerie"));
	}

}
