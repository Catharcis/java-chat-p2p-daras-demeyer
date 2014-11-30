package GUI;

import java.util.Observable;
import java.util.Observer;

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
		/** connexion d'un local user **/
		System.out.println("Update GUIVIEW");
		if (guiControler.getEtat() == Etats.disconnected) {
			/** on passe a l'etat connected **/
			guiControler.setEtatConnect();
			System.out.println("Etat : "+guiControler.getEtat()) ;
			
			/** le bouton connect change d'aspect **/
			this.chatFenetre.getConnectDisconnectPanel().getButtonConnectOnOff().setText("Deconnexion");
		}
		
		else {
			/** le bouton connect change d'aspect **/
			this.chatFenetre.getConnectDisconnectPanel().getButtonConnectOnOff().setText("Connexion");
			
			System.out.println("Etat : "+guiControler.getEtat()) ;
			
		} 
			
	}

	
}
