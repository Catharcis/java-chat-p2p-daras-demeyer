import java.net.UnknownHostException;
import GUI.GUIView;




public class Test {

public static void initGUI () {
	/** Initialisation de la GUI **/
	GUIView guiView = null ;
	guiView = GUIView.getInstance() ;
	guiView.getChatFenetre().setGuiView(guiView);
}
	
	public static void main(String[] args) throws UnknownHostException {
		initGUI() ;
	}

}
