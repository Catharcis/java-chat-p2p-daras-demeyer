import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import GUI.GUIView;




public class Test {

public static void initGUI () {
	/** Initialisation de la GUI **/
	GUIView guiView = null ;
	guiView = guiView.getInstance() ;
	guiView.getChatFenetre().setGuiView(guiView);
}
	
	
	public static void main(String[] args) throws UnknownHostException {
		initGUI() ;
	}

}
