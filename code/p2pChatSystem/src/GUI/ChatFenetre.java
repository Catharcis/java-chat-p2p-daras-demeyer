package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.net.UnknownHostException;
import java.util.Observable;

import javax.swing.JPanel;
import javax.swing.ListModel;

import Controler.NetworkInformation;
import Controler.typeOfChange;
import GUI.GUIControler.Etats;

public class ChatFenetre extends AbstractFenetre{


	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private static ChatFenetre singleton;
	
	private ConnectDisconnectPanel connectDisconnectPanel;
	
	private ContactsListPanel contactsListPanel;
	
	private static GUIView guiView ;
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private ChatFenetre(){
		// On recupere les deux panels
		connectDisconnectPanel = connectDisconnectPanel.getInstance();
		contactsListPanel = contactsListPanel.getInstance();
		this.addWindowListener(this);
	    this.initializeComponents();
	}

	public static ChatFenetre getInstance(){
		if (singleton == null){
			singleton = new ChatFenetre();
		}
		return singleton;
	}


	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/
	
	public void setGuiView (GUIView view) {
		guiView = view.getInstance() ;
	}
	
	public static GUIView getGUIView() {
		return guiView;
	}
	
	public ConnectDisconnectPanel getConnectDisconnectPanel () {
		return this.connectDisconnectPanel ;
	}
	
	public ContactsListPanel getContactsListPanel () {
		return this.contactsListPanel ;
	}
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/

	
	@Override
	public void initializeComponents() {
		
		// On les reunit au sein d'un seule et meme panel
		JPanel generalPanel = new JPanel(new BorderLayout());
		JPanel textFieldAndButton = new JPanel(new BorderLayout());
		JPanel statusAndImagePanel = new JPanel(new GridLayout(1,2));
		JPanel nicknameAndStatusAndImagePanel = new JPanel(new BorderLayout());
		JPanel generalConnectPanel = new JPanel(new BorderLayout());
		
		textFieldAndButton.add(connectDisconnectPanel.getTextFieldNameOfLocalUser(),BorderLayout.WEST);
		textFieldAndButton.add(connectDisconnectPanel.getButtonConnectOnOff(),BorderLayout.EAST);
			
		
		// On les ajoute a notre panel
		statusAndImagePanel.add(connectDisconnectPanel.getStatus());
		statusAndImagePanel.add(connectDisconnectPanel.getImage());
		
		nicknameAndStatusAndImagePanel.add(statusAndImagePanel,BorderLayout.NORTH);
		nicknameAndStatusAndImagePanel.add(connectDisconnectPanel.getLabelNickname(),BorderLayout.SOUTH);
		
		generalConnectPanel.add(nicknameAndStatusAndImagePanel,BorderLayout.NORTH);
		generalConnectPanel.add(textFieldAndButton,BorderLayout.SOUTH);
		
		generalPanel.add(generalConnectPanel,BorderLayout.NORTH);
		generalPanel.add(contactsListPanel,BorderLayout.SOUTH);
		
		this.getContentPane().add(generalPanel);
		
		// On ajoute les informations utiles a la fenetre
		this.setTitle("Chat System");
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.pack();
		connectDisconnectPanel.getButtonConnectOnOff().addActionListener(this);
		connectDisconnectPanel.getNicknameField().addKeyListener(this);
		contactsListPanel.getList().addMouseListener(this) ;
	}


	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == connectDisconnectPanel.getButtonConnectOnOff()){
			if (guiView.getGUIControler().getEtat() == Etats.disconnected) {
				try {
					getGUIView().Connection(connectDisconnectPanel.getNameOfLocalUser());
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.pack();
			}
			else
			{
				getGUIView().Disconnection() ;
				this.pack();
			}
		}
	}

	@Override
	public void miseAJour(Observable arg0) {
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            int index = this.contactsListPanel.getList().locationToIndex(evt.getPoint());
            ListModel dlm = this.contactsListPanel.getList().getModel();
            Object item = dlm.getElementAt(index);;
            this.contactsListPanel.getList().ensureIndexIsVisible(index);
            // creation de la fenetre
            int idUser = this.guiView.getGUIControler().getGUIToControler().getNetInfo().getArrayPositionsListModel().get(index);
            ConversationFenetre newConversation = new ConversationFenetre(item.toString(), idUser) ;
            newConversation.setGuiView(guiView) ;
            this.guiView.getConversationFenetre().add(newConversation);
            
        }
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		
	}

	@Override
	public void mouseExited(MouseEvent e) {

		
	}

	@Override
	public void mousePressed(MouseEvent e) {

		
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		
	}


	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		
		System.out.println("Arret du Chat System...");
		if (this.guiView.getGUIControler().getEtat() != Etats.disconnected) {
			if (NetworkInformation.getInstance().getLastChange() != typeOfChange.DISCONNECTION){
				this.getGUIView().getGUIControler().getGUIToControler().performDisconnect();
			}
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getSource() == connectDisconnectPanel.getNicknameField()) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER ) {
				if (guiView.getGUIControler().getEtat() == Etats.disconnected) {
					try {
						getGUIView().Connection(connectDisconnectPanel.getNameOfLocalUser());
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.pack();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	
}
