package NI;

public abstract class AbstractSender extends AbstractNetwork {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	/** Port d'envoi **/
	private int portEnvoi ;

	
	
	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/
	
	/** Permet d'obtenir le port d'envoi **/
	public int getPortEnvoi(){
		return this.portEnvoi ;
	}
	
	/** Permet de modifier le port d'envoi **/
	public void setPortEnvoi(int newPort){
	    this.portEnvoi = newPort;
	}
	

	
}
