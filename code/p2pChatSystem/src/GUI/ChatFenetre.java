package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		// On récupère les deux panels
		connectDisconnectPanel = connectDisconnectPanel.getInstance();
		contactsListPanel = contactsListPanel.getInstance();
		
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
		this.guiView = view.getInstance() ;
	}
	
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/

	
	@Override
	public void initializeComponents() {
		// On les réunit au sein d'un seule et même panel
		GridLayout grid = new GridLayout(2,1);
		this.setLayout(grid);

		this.getContentPane().add(connectDisconnectPanel);
		this.getContentPane().add(contactsListPanel);
		
		// On ajoute les informations utiles à la fenêtre
		this.setTitle("Chat System");
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.pack();
		connectDisconnectPanel.getButtonConnectOnOff().addActionListener(this);
	}

	@Override
	public void miseAJour(Observable arg0) {

		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == connectDisconnectPanel.getButtonConnectOnOff()){
			System.out.println("Bouton Connexion appuyé !");
			System.out.println(connectDisconnectPanel.getNameOfLocalUser()) ;
			guiView.newConnection(connectDisconnectPanel.getNameOfLocalUser());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		
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

	
}
