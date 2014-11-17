package NI;

public abstract class AbstractNetwork {

	/**  Port d'ecoute **/
	private int portEcoute;
	
	/** Permet d'obtenir le port d'écoute **/
	public int getPortEcoute(){
		return this.portEcoute;
	}

	
	/** Permet de modifier le port d'ecoute **/
	public void setPortEcoute(int newPort){
	    this.portEcoute = newPort;
	}
	
	
	
}
