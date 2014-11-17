package NI;

public class NIControler {

	private static NIControler NISingleton ;
	
	private NIControler () { }
	
	public static NIControler getInstance () {
		return NISingleton ;
	}
	
	
}
