package NI;

public abstract class AbstractNetwork {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	/**  Port d'ecoute **/
	private int portEcoute;
	
	
	/************************************************* 
	 * 				GETTERS & SETTERS 
	 ************************************************/
	
	/** Permet d'obtenir le port d'ecoute **/
	public int getPortEcoute(){
		return this.portEcoute;
	}

	
	/** Permet de modifier le port d'ecoute **/
	public void setPortEcoute(int newPort){
	    this.portEcoute = newPort;
	}
	
	
	
}
