package NI;

public abstract class AbstractNetwork {

	// Port d'écoute
	private int port;
	
	/*
	 * Permet d'obtenir le port d'écoute
	 */
	public int getPort(){
		return this.port;
	}
	
	/*
	 * Permet de modifier le port d'écoute
	 */
	public void setPort(int newPort){
	    this.port = newPort;
	}
	
	
	
}
