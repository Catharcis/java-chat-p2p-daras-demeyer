package NI;

/**
 * @author Valérie Daras et Alexandre Demeyer
 */

public abstract class AbstractReceiver extends AbstractNetwork{

	/**
	 * Boolean indiquant si les sockets doivent ecouter
	 */
	private boolean stateListen = true;
	
	/**
	 * Permet de recuperer l'état d'ecoute
	 * @return vrai si on écoute le réseau, faux sinon
	 */
	public boolean isListening(){
		return stateListen;
	}
	
	/**
	 * Permet de modifier l'etat d'ecoute
	 * @param bool : vrai si on souhaite se mettre en ecoute et faux sinon
	 */
	public void setStateListen(boolean bool){
		this.stateListen = bool;
	}
	
	/**
	 * Permet de définir comment une classe va ecouter le reseau
	 */
	protected abstract void listen();
	
}
