package NI;

public class NIControler {

	private static NIControler NISingleton ;
	
	private NIControler () { }
	
	public static NIControler getInstance () {
		if (NISingleton == null) {
			NISingleton = new NIControler () ;
		}
		return NISingleton ;
	}
	
	
}
