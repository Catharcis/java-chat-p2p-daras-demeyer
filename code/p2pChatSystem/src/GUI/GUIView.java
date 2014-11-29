package GUI;

import java.util.Observable;
import java.util.Observer;

public class GUIView implements Observer{

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private static GUIView singleton;
	
	private ChatFenetre chatFenetre;

	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private GUIView(){
		chatFenetre = chatFenetre.getInstance();
		chatFenetre.setVisible(true);
	}
	
	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/
	
	
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/

	
	public static GUIView getInstance(){
		if (singleton == null){
			singleton = new GUIView();
		}
		return singleton;
	}
	
	public void initChatSystem(){
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		
	}

	
}
