package NI;

public class UDPReceiver extends AbstractReceiver {

	// On a comme champ le singleton de la classe
	private static UDPReceiver singleton = null;
	
	
	/*
	 * On définit les constructeurs
	 */
	private UDPReceiver(){
		this.setPort(9876);
	}
	
	/*
	 * Méthode pour récupérer l'instance 
	 */
	public static UDPReceiver getInstanceUDPReceiver(){
		if (singleton == null){
			singleton = new UDPReceiver();
		}
		return singleton;
	}
	
	/*
	 * Redéfinition de la méthode d'écoute des paquets UDP sur le réseau
	 */
	public void listen(){
		
	}
	
}
