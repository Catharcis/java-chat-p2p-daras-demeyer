package GUI;

import java.util.Observable;
import java.util.Observer;

import Controler.NetworkInformation;
import Controler.typeOfChange;
import GUI.GUIControler.Etats;

public class GUIView implements Observer{

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private static GUIView singleton;
	
	private ChatFenetre chatFenetre;
	
	private GUIControler guiControler ;

	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private GUIView() {
		chatFenetre = chatFenetre.getInstance();
		chatFenetre.setVisible(true);
		guiControler = guiControler.getInstance() ;
	}
	
	public static GUIView getInstance(){
		if (singleton == null){
			singleton = new GUIView();
		}
		return singleton;
	}
	
	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/
	
	public ChatFenetre getChatFenetre () {
		return this.chatFenetre ;
	}
	
	public GUIControler getGUIControler(){
		return this.guiControler;
	}
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/

		
	protected void Connection (String name) {
		guiControler.Connection(name);
	}

	protected void Disconnection () {
		guiControler.Disconnection();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		if (arg0 instanceof NetworkInformation){
			
			NetworkInformation NI = NetworkInformation.getInstance();
			
			if (NI.getLastChange().equals(typeOfChange.CONNECTION)) {
				
				/** le bouton connect change d'aspect **/
				this.chatFenetre.getConnectDisconnectPanel().getButtonConnectOnOff().setText("Deconnexion");
				guiControler.setEtatConnect();
			}
			
			else if (NI.getLastChange().equals(typeOfChange.DISCONNECTION)) {
				/** le bouton connect change d'aspect **/
				this.chatFenetre.getConnectDisconnectPanel().getButtonConnectOnOff().setText("Connexion");
				guiControler.setEtatDisconnect();
			}
			
			else if (NI.getLastChange().equals(typeOfChange.ADDUSER)){
				
			}
			
			else if (NI.getLastChange().equals(typeOfChange.REMOVEUSER)) {
				
			}
			
		}

		// Permet de placer correctement l'ensemble des composants
		this.getChatFenetre().pack();
			
	}

	
}
