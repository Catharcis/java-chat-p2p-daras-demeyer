package NI;

/**
 * @author Valérie Daras et Alexandre Demeyer
 */

public abstract class AbstractNetwork {

	/************************************************* 
	 * 				ATTRIBUTS and FIELDS 
	 ************************************************/
	
	/**  Port d'ecoute **/
	private int portEcoute;
	
	
	/************************************************* 
	 * 				GETTERS and SETTERS 
	 ************************************************/
	
	/** 
	 * Permet d'obtenir le port d'ecoute
	 * @return le numero de port
	 */
	public int getPortEcoute(){
		return this.portEcoute;
	}

	
	/**
	 *  Permet de modifier le port d'ecoute
	 * @param newPort : nouveau port d'ecoute
	 */
	public void setPortEcoute(int newPort){
	    this.portEcoute = newPort;
	}
	
	
	
}
