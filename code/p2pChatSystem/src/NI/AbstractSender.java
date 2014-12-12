package NI;

/**
 * @author Valérie Daras et Alexandre Demeyer
 */

public abstract class AbstractSender extends AbstractNetwork {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	/** Port d'envoi **/
	private int portEnvoi ;

	
	
	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/
	
	/** 
	 * Permet d'obtenir le port d'envoi
	 * @return le numero de port d'envoi
	 */
	public int getPortEnvoi(){
		return this.portEnvoi ;
	}
	
	/**
	 * Permet de modifier le port d'envoi
	 * @param newPort : le port a ecouter
	 */
	public void setPortEnvoi(int newPort){
	    this.portEnvoi = newPort;
	}
	

	
}
