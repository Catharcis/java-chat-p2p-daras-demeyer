package GUI;

import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

import Controler.GUIToControler;
import Controler.NetworkInformation;
import Controler.NetworkToControler;
import Controler.typeOfChange;
import GUI.GUIControler.Etats;
import NI.NIControler;

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

	public static void initChatSystem (GUIView guiView) {
		/** Initialisation du Controler **/
		NetworkToControler netToCon = null ;
		netToCon = netToCon.getInstance() ;
		
		GUIToControler guiToCon = null ;
		guiToCon = guiToCon.getInstance() ;
		
		/** Initialisation du NI **/
		NIControler niCon = null ;
		niCon = niCon.getInstance() ;
		
		/** Creation des liens **/
		netToCon.getNetInfo().setGuiView(guiView);
		guiToCon.setGuiCon(niCon);
		niCon.setNetToCon(netToCon);
		niCon.getUDPReceiver().setNiCon(niCon);
	}	
	
	protected void Connection (String name) {
		if (name.length() == 0)
			JOptionPane.showMessageDialog(null, "Please choose a nickname.", "Error", JOptionPane.ERROR_MESSAGE);
		else {
			initChatSystem(this) ;
			this.chatFenetre.getContactsListPanel().initComponents();
			guiControler.Connection(name);
		}
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
				this.chatFenetre.getConnectDisconnectPanel().getNicknameFiled().setEditable(false);
				guiControler.setEtatConnect();
			}
			
			else if (NI.getLastChange().equals(typeOfChange.DISCONNECTION)) {
				
				/** le bouton connect change d'aspect **/
				this.chatFenetre.getConnectDisconnectPanel().getButtonConnectOnOff().setText("Connexion");
				this.chatFenetre.getConnectDisconnectPanel().getNicknameFiled().setEditable(true);
				guiControler.setEtatDisconnect();
			}
			
			else if (NI.getLastChange().equals(typeOfChange.ADDUSER)){
				
				int idUser = (Integer)arg1;
				String nickname = guiControler.getGUIToControler().getNicknameOfId(idUser);
				ListModel<String> list = this.chatFenetre.getContactsListPanel().getList().getModel();
				DefaultListModel<String> newList = new DefaultListModel<String>();
				for (int i = 0; i < list.getSize(); i++){
					newList.addElement(list.getElementAt(i));
				}
				newList.addElement(nickname);
				this.chatFenetre.getContactsListPanel().getList().setModel(newList);;
				this.chatFenetre.pack();
				this.guiControler.getGUIToControler().addIDListModel(idUser);
				
			}
			
			else if (NI.getLastChange().equals(typeOfChange.REMOVEUSER)) {
				
				int idUser = (Integer)arg1;
				String nickname = guiControler.getGUIToControler().getNicknameOfId(idUser);
				ListModel<String> list = this.chatFenetre.getContactsListPanel().getList().getModel();
				DefaultListModel<String> newList = new DefaultListModel<String>();
				for (int i = 0; i < list.getSize(); i++){
					if (list.getElementAt(i) != nickname){
						newList.addElement(list.getElementAt(i));
					}
				}
				this.chatFenetre.getContactsListPanel().getList().setModel(newList);;
				this.chatFenetre.pack();
				this.guiControler.getGUIToControler().removeIDListModel(idUser);
				
			}
			
			else if (NI.getLastChange().equals(typeOfChange.NEWINCOMINGTEXTMESSAGE)){
				
			}
			
		}

		// Permet de placer correctement l'ensemble des composants
		this.getChatFenetre().pack();
			
	}

	
}
