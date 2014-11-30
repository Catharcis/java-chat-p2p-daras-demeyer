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

		
	protected void newConnection (String name) {
		guiControler.Connection(name);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		/** connexion d'un local user **/
		if (guiControler.getEtat() == Etats.disconnected) {
			/** on passe a l'etat connected **/
			guiControler.setEtatConnect();
			
			/** le bouton connect change d'aspect **/
			
		}
	}

	
}
