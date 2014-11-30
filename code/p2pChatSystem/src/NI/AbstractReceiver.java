package NI;

public abstract class AbstractReceiver extends AbstractNetwork{

	// Boolean indiquant si les sockets doivent écouter
	private boolean stateListen = true;
	
	/*
	 * Permet de récupérer l'état d'écoute
	 */
	public boolean isListening(){
		return stateListen;
	}
	
	/*
	 * Permet de modifier l'état d'écoute
	 */
	public void setStateListen(boolean bool){
		this.stateListen = bool;
	}
	
	/*
	 * Permet de définir l'écoute de la classe sur le réseau
	 */
	public abstract void listen();
	
}
