package GUI;

import java.util.Observable;
import java.util.Observer;

public class GUIView implements Observer{

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private static GUIView singleton;
	
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private GUIView(){
		
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

	@Override
	public void update(Observable arg0, Object arg1) {

		
	}

	
}
