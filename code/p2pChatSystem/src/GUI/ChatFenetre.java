package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.TreeSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListModel;
import Controler.NetworkInformation;
import Controler.typeOfChange;
import GUI.GUIControler.Etats;

@SuppressWarnings("serial")
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
	
	private ChatFenetre() {
		this.initializeWindow() ;
		connectDisconnectPanel = ConnectDisconnectPanel.getInstance();
		contactsListPanel = ContactsListPanel.getInstance();
		this.addWindowListener(this);
	    this.initializeComponents(true);
	}

	public static ChatFenetre getInstance() {
		if (singleton == null){
			singleton = new ChatFenetre();
		}
		return singleton;
	}


	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/
	
	public void setGuiView (GUIView view) {
		guiView = GUIView.getInstance() ;
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

	public void initializeWindow() {
		this.setTitle("Chat System") ; 
		this.setResizable(false) ;
		this.setSize(500, 400);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	}
	
	public void initializeComponents(boolean visible) {	
		// On crée nos différents conteneurs
	    JPanel cell1 = new JPanel();
	    cell1.setBackground(Color.white);
	    cell1.setPreferredSize(new Dimension(160, 75));	
	    
	    JPanel cell2 = new JPanel();
	    cell2.setBackground(Color.white);
	    cell2.setPreferredSize(new Dimension(180, 75));
	    cell2.add(this.connectDisconnectPanel.getLabelNickname(), BorderLayout.NORTH) ;
	    cell2.add(this.connectDisconnectPanel.getNicknameField(), BorderLayout.CENTER) ;
	    
	    JPanel cell3 = new JPanel();
	    cell3.setBackground(Color.white);
	    cell3.setPreferredSize(new Dimension(160, 75));
	    cell3.setLayout(new BorderLayout());
	    cell3.add(this.connectDisconnectPanel.getImageStatus(), BorderLayout.CENTER) ;
	    
	    JPanel cell4 = new JPanel();
	    cell4.setBackground(Color.white);
	    cell4.setPreferredSize(new Dimension(500, 75));
	    this.connectDisconnectPanel.getButtonConnectOnOff().setMargin(new Insets(10,10,10,10)) ;
	    cell4.add(this.connectDisconnectPanel.getButtonConnectOnOff()) ;
	    
	    JPanel cell5 = new JPanel();
	    cell5.setBackground(Color.white);
	    cell5.setPreferredSize(new Dimension(300, 250));
	    cell5.setLayout(new BorderLayout());
	    cell5.add(this.connectDisconnectPanel.getImageChat(), BorderLayout.CENTER) ;
	    
	    JPanel cell6 = new JPanel();
	    cell6.setBackground(Color.white);
	    cell6.setPreferredSize(new Dimension(200, 250));
	    this.contactsListPanel.initComponents() ;
	    cell6.add(this.contactsListPanel.getList()) ;
	    
	    //Le conteneur principal
	    connectDisconnectPanel.setSize(new Dimension(500, 400));
	    connectDisconnectPanel.setBackground(Color.white);
	    connectDisconnectPanel.setLayout(new GridBagLayout());
	    
	    //L'objet servant à positionner les composants
	    GridBagConstraints gbc = new GridBagConstraints();
		
	    //On positionne les composants
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    connectDisconnectPanel.add(cell1, gbc);
	    //---------------------------------------------
	    gbc.gridx = 1;
	    connectDisconnectPanel.add(cell2, gbc);
	    //---------------------------------------------
	    gbc.gridx = 2;		
	    gbc.anchor = GridBagConstraints.FIRST_LINE_END ;
	    connectDisconnectPanel.add(cell3, gbc);		
	    //---------------------------------------------
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    connectDisconnectPanel.add(cell4, gbc);
	    //---------------------------------------------
	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    gbc.anchor = GridBagConstraints.LAST_LINE_START ;
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.gridheight = GridBagConstraints.REMAINDER;
	    connectDisconnectPanel.add(cell5, gbc);
	    //---------------------------------------------
	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    gbc.anchor = GridBagConstraints.LAST_LINE_END ;
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.gridheight = GridBagConstraints.REMAINDER;
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    //contactsListPanel.add(cell6, gbc);
	    this.connectDisconnectPanel.add(cell6,gbc);
	    //---------------------------------------------

	    //On ajoute le conteneur
	    this.setContentPane(connectDisconnectPanel);
	    this.setVisible(visible) ;
	    
	    // On ajoute les Listener
		this.connectDisconnectPanel.getButtonConnectOnOff().addActionListener(this);
		this.connectDisconnectPanel.getNicknameField().addKeyListener(this);
		this.contactsListPanel.getList().addMouseListener(this) ; 
		
		this.pack();
	}
		
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == connectDisconnectPanel.getButtonConnectOnOff()){
			if (guiView.getGUIControler().getEtat() == Etats.disconnected) {
				getGUIView().Connection(connectDisconnectPanel.getNameOfLocalUser());
				this.pack();
			}
			else
			{
				getGUIView().Disconnection() ;
				this.pack();
			}
		}
	}

	
	public void mouseClicked(MouseEvent evt) {
		if (ChatFenetre.guiView.getGUIControler().getEtat() != Etats.disconnected) {
	        if (evt.getClickCount() == 2) {
	        	
	            int index = this.contactsListPanel.getList().locationToIndex(evt.getPoint());
	            @SuppressWarnings("rawtypes")
				ListModel dlm = this.contactsListPanel.getList().getModel();
	            Object item = dlm.getElementAt(index);;
	            this.contactsListPanel.getList().ensureIndexIsVisible(index);
	            
	            // creation de la fenetre
	            int idUser = ChatFenetre.guiView.getGUIControler().getGUIToControler().getNetInfo().getArrayPositionsListModel().get(index);
	            TreeSet<Integer> idList = new TreeSet<Integer> () ;
	            idList.add(idUser) ;
	            
	            int i = 0;
	            boolean found = false ;
	            
	            // on cherche si une fenetre correspondant a cette conversation existe deja 
	            while (i < ChatFenetre.guiView.getConversationFenetre().size() && !found) {
	            	if (ChatFenetre.guiView.getConversationFenetre().get(i).getListOfIds().equals(idList)) {
            			ChatFenetre.guiView.getConversationFenetre().get(i).setVisible(true) ;
            			String historic = ChatFenetre.guiView.getGUIControler().getGUIToControler().getNetInfo().getHistoricConversations().get(idList);
            			ChatFenetre.guiView.getConversationFenetre().get(i).getHistoricArea().setText(historic) ;
            			found = true ;
            		}
	            	i++ ;
	            }
	            // si on n'a pas trouve de conversation existante, on en cree une
	            if (!found) {
	            	TreeSet <Integer> listId = new TreeSet<Integer> () ;
	            	listId.add(idUser) ;
	            	ArrayList<String> listNickname = new ArrayList<String>() ;
	            	listNickname.add(item.toString()) ;
	            	ConversationFenetre newConversation = new ConversationFenetre(listNickname, listId, true) ;
		            newConversation.setGuiView(guiView) ;
		            ChatFenetre.guiView.getConversationFenetre().add(newConversation);
            	}
	        }
        }
	}

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void windowActivated(WindowEvent arg0) { }


	@Override
	public void windowClosed(WindowEvent e) { }

	@Override
	public void windowClosing(WindowEvent e) {	
		System.out.println("Arret du Chat System...");
		if (ChatFenetre.guiView.getGUIControler().getEtat() != Etats.disconnected) {
			if (NetworkInformation.getInstance().getLastChange() != typeOfChange.DISCONNECTION){
				ChatFenetre.getGUIView().getGUIControler().getGUIToControler().performDisconnect();
			}
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) { }

	@Override
	public void windowDeiconified(WindowEvent e) { }

	@Override
	public void windowIconified(WindowEvent e) { }

	@Override
	public void windowOpened(WindowEvent e) { }

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getSource() == connectDisconnectPanel.getNicknameField()) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER ) {
				if (guiView.getGUIControler().getEtat() == Etats.disconnected) {
					getGUIView().Connection(connectDisconnectPanel.getNameOfLocalUser());
					this.pack();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) { }

	@Override
	public void keyTyped(KeyEvent arg0) { }

}
