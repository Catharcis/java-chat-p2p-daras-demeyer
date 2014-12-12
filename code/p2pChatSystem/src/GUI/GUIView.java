package GUI;

import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controler.GUIToControler;
import Controler.NetworkInformation;
import Controler.NetworkToControler;
import Controler.typeOfChange;
import NI.NIControler;

public class GUIView implements Observer{

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private static GUIView singleton;
	
	private ChatFenetre chatFenetre;
	
	private GUIControler guiControler ;
	
	private ArrayList<ConversationFenetre> listOfConversationFenetre ;
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private GUIView() {
		chatFenetre = ChatFenetre.getInstance();
		chatFenetre.setVisible(true);
		guiControler = GUIControler.getInstance() ;
		listOfConversationFenetre = new ArrayList<ConversationFenetre>() ;
	}
	
	public static GUIView getInstance() {
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
	
	public GUIControler getGUIControler() {
		return this.guiControler;
	}
	
	public ArrayList<ConversationFenetre> getConversationFenetre () {
		return this.listOfConversationFenetre ;
	}
	
	/************************************************* 
	 * 					METHODS 
	 * @throws UnknownHostException 
	 ************************************************/

	public static void initChatSystem (GUIView guiView) {
		/** Initialisation du Controler **/
		NetworkToControler netToCon = null ;
		netToCon = NetworkToControler.getInstance() ;
		
		GUIToControler guiToCon = null ;
		guiToCon = GUIToControler.getInstance() ;
		
		/** Initialisation du Network Interface **/
		NIControler niCon = null ;
		niCon = NIControler.getInstance() ;
		
		/** Creation des liens **/
		netToCon.getNetInfo().setGuiView(guiView);
		guiToCon.setNiCon(niCon);
		niCon.setNetToCon(netToCon);
		niCon.getUDPReceiver().setNiCon(niCon);
		niCon.getTCPServer().setNiCon(niCon) ;
	}	
	
	protected void Connection (String name) {
		if (name.length() == 0)
			JOptionPane.showMessageDialog(null, "Please choose a nickname.", "Error", JOptionPane.ERROR_MESSAGE);
		else {
			initChatSystem(this) ;
			guiControler.Connection(name);
		}
	}

	protected void Disconnection () {
		guiControler.Disconnection();
	}
	
	protected void TextMessage (String message, TreeSet <Integer> listOfId) throws UnknownHostException {
		guiControler.TextMessage(message, listOfId) ;
	}
	
	protected void FileMessage (File file, TreeSet <Integer> listOfId) throws UnknownHostException {
		guiControler.FileMessage(file, listOfId) ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof NetworkInformation){
			NetworkInformation NI = NetworkInformation.getInstance();
			
			if (NI.getLastChange().equals(typeOfChange.CONNECTION)) {
				/** le bouton connect change d'aspect **/
				this.chatFenetre.getConnectDisconnectPanel().getButtonConnectOnOff().setText("Deconnexion");
				this.chatFenetre.getConnectDisconnectPanel().getNicknameField().setEditable(false);
				this.chatFenetre.getConnectDisconnectPanel().setImageStatus(new ImageIcon("online.png"));
				guiControler.setEtatConnect();
			}
			
			else if (NI.getLastChange().equals(typeOfChange.DISCONNECTION)) {
				/** le bouton connect change d'aspect **/
				this.chatFenetre.getConnectDisconnectPanel().getButtonConnectOnOff().setText("Connexion");
				this.chatFenetre.getConnectDisconnectPanel().getNicknameField().setEditable(true);
				this.chatFenetre.getConnectDisconnectPanel().setImageStatus(new ImageIcon("offline.png"));
				this.chatFenetre.getContactsListPanel().getDefaultListModel().clear() ;
				for (int i = 0; i<this.listOfConversationFenetre.size(); i++) {
					this.listOfConversationFenetre.get(i).setVisible(false) ;
				}
				this.listOfConversationFenetre.clear() ;
				guiControler.setEtatDisconnect();
			}
			else if (NI.getLastChange().equals(typeOfChange.ADDUSER_HELLO)){	
				int idUser = (Integer)arg1;
				String nickname = guiControler.getGUIToControler().getNetInfo().getUserWithId(idUser).getNickname() ;
				this.chatFenetre.getContactsListPanel().getDefaultListModel().addElement(nickname);
				this.chatFenetre.pack();
				this.guiControler.getGUIToControler().addIDListModel(idUser);

				// on envoit un HelloAck au nouvel user
				this.guiControler.getGUIToControler().performSendHelloAck(idUser);
			}
			
			else if (NI.getLastChange().equals(typeOfChange.ADDUSER_HELLO_ACK)){	
				int idUser = (Integer)arg1;
				String nickname = guiControler.getGUIToControler().getNetInfo().getUserWithId(idUser).getNickname() ;
				this.chatFenetre.getContactsListPanel().getDefaultListModel().addElement(nickname);
				this.chatFenetre.pack();
				this.guiControler.getGUIToControler().addIDListModel(idUser);
			}
			
			else if (NI.getLastChange().equals(typeOfChange.REMOVEUSER)) {
				
				int idUser = (Integer)arg1;
				String nickname = guiControler.getGUIToControler().getNetInfo().getUserWithId(idUser).getNickname() ;
				// suppression du user de la liste de contact
				if (this.chatFenetre.getContactsListPanel().getDefaultListModel().removeElement(nickname)){
					System.out.println("JList : remove "+nickname+" success");
				}
				else{
					System.out.println("JList - ERROR : remove "+nickname+" failed !");
				}
				// remise en forme de la liste visuelle
				this.chatFenetre.pack();
				this.guiControler.getGUIToControler().removeIDListModel(idUser);
				System.out.println("JList pack()");
				
				//suppression du user des conversations
				for (int i = 0; i<this.listOfConversationFenetre.size(); i++) {
					// on supprime l'ID du user de toutes les conversations
					if (this.listOfConversationFenetre.get(i).getListOfIds().contains(idUser))
						this.listOfConversationFenetre.get(i).getListOfIds().remove(idUser) ;
					// on supprime son nickname de toutes les conversations
					if (this.listOfConversationFenetre.get(i).getListOfNicknames().contains(this.guiControler.getGUIToControler().getNetInfo().getUserWithId(idUser).getNickname()))
						this.listOfConversationFenetre.get(i).getListOfNicknames().remove(this.guiControler.getGUIToControler().getNetInfo().getUserWithId(idUser).getNickname()) ;
					// mise a jour de la fenetre
					this.listOfConversationFenetre.get(i).miseAJourFenetre() ;
					// si la conversation est alors vide, on la supprime de la liste
					if (this.listOfConversationFenetre.get(i).getListOfIds().isEmpty()) {
						this.listOfConversationFenetre.remove(i) ;
					}
				}
			}

			else if (NI.getLastChange().equals(typeOfChange.NEWINCOMINGTEXTMESSAGE)){
				// On recupere la conversation
				TreeSet<Integer> listOfIds = (TreeSet<Integer>)arg1 ;
				String conversation = NI.getHistoricConversations().get(listOfIds);
				
				ArrayList<String> nicknames = new ArrayList<String>() ;
				for (Integer aux : listOfIds) {
					nicknames.add(this.getGUIControler().getGUIToControler().getNetInfo().getUserWithId(aux).getNickname()) ;
				}
				boolean found = false ;
				int i = 0 ;
				while (!found && i < this.listOfConversationFenetre.size()) {
					if (this.listOfConversationFenetre.get(i).getListOfIds().equals(listOfIds)) {
						found = true ;
						this.listOfConversationFenetre.get(i).getHistoricArea().setText(conversation) ;
						if (!this.listOfConversationFenetre.get(i).isVisible()) {
							JFrame frame  = new JFrame() ;
							JOptionPane optionPane = new JOptionPane("There is a new message in your conversation with "+nicknames);
							JDialog myDialog = optionPane.createDialog(frame, "New Message Notification");
							myDialog.setModal(false);
							myDialog.setVisible(true);
						}
					}
					i++ ;
				}
				if (!found) {
					JFrame frame  = new JFrame() ;
					JOptionPane optionPane = new JOptionPane("There is a new message in your conversation with "+nicknames);
					JDialog myDialog = optionPane.createDialog(frame, "New Message Notification");
					myDialog.setModal(false);
					myDialog.setVisible(true);
					ConversationFenetre newConversation = new ConversationFenetre(nicknames, listOfIds, false) ;
		            newConversation.setGuiView(this) ;
		            this.getConversationFenetre().add(newConversation);
				}
				
			}
			
			else if (NI.getLastChange().equals(typeOfChange.NEWINCOMINGFILEMESSAGE)){
				// On recupere la conversation
				TreeSet<Integer> listOfIds = (TreeSet<Integer>)arg1 ;
				String conversation = NI.getHistoricConversations().get(listOfIds);
				
				ArrayList<String> nicknames = new ArrayList<String>() ;
				for (Integer aux : listOfIds) {
					nicknames.add(this.getGUIControler().getGUIToControler().getNetInfo().getUserWithId(aux).getNickname()) ;
				}
				boolean found = false ;
				int i = 0 ;
				while (!found && i < this.listOfConversationFenetre.size()) {
					if (this.listOfConversationFenetre.get(i).getListOfIds().equals(listOfIds)) {
						found = true ;
						this.listOfConversationFenetre.get(i).getHistoricArea().setText(conversation) ;
						if (!this.listOfConversationFenetre.get(i).isVisible()) {
							JFrame frame  = new JFrame() ;
							JOptionPane optionPane = new JOptionPane("There is a new incoming file in your conversation with "+nicknames);
							JDialog myDialog = optionPane.createDialog(frame, "New File Notification");
							myDialog.setModal(false);
							myDialog.setVisible(true);
						}
					}
					i++ ;
				}	
				if (!found) {
					JFrame frame  = new JFrame() ;
					JOptionPane optionPane = new JOptionPane("There is a new incoming file in your conversation with "+nicknames);
					JDialog myDialog = optionPane.createDialog(frame, "New File Notification");
					myDialog.setModal(false);
					myDialog.setVisible(true);
					ConversationFenetre newConversation = new ConversationFenetre(nicknames, listOfIds, false) ;
		            newConversation.setGuiView(this) ;
		            this.getConversationFenetre().add(newConversation);
				}
			}
		}
		// Permet de placer correctement l'ensemble des composants
		this.getChatFenetre().pack();
	}

	
}
