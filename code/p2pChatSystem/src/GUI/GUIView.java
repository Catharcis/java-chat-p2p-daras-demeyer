package GUI;

import java.util.Observable;
import java.util.Observer;

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
	
	
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/

	
	
	
	public void initChatSystem(){
		
	}

	
	protected void newConnection (String name) {
		guiControler.newConnection(name);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {

		
	}

	
}
