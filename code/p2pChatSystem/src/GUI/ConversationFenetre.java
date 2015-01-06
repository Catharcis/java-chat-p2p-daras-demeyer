package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import GUI.GUIControler.Etats;

/**
 * @author Val�rie Daras et Alexandre Demeyer
 */

@SuppressWarnings("serial")
public class ConversationFenetre extends AbstractFenetre{

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	/**
	 * Field vers la classe pere
	 */
	private static GUIView guiView ;
	
	/**
	 * JTextArea correspondant a l'historique d'une conversation
	 */
	private JTextArea historic;
	
	/**
	 * JTextArea correspondant a l'espace d'ecriture pour une conversation
	 */
	private JTextArea writerArea;
	
	/**
	 * Button d'envoi de texte
	 */
	private JButton sendButton;
	
	/**
	 * Liste des nicknames des utilisateurs concernes par la conversation
	 */
	private ArrayList<String> listOfNicknames ;
	
	/**
	 * Liste des ID des utilisateurs concernes par la conversation
	 */
	private TreeSet <Integer> listOfId ;
	
	/**
	 * Button d'envoi de fichier
	 */
	private JButton fileButton;
	
	/**
	 * Object Permettant de choisir un fichier 
	 */
	private JFileChooser fileChooser;
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	/**
	 * Constructeur d'une fenetre de conversation
	 * @param listNicknames : Liste des nicknames des utilisateurs concernes par la conversation
	 * @param listUserId : Liste des ID des utilisateurs concernes par la conversation
	 * @param visible : boolean indiquant si la fenetre doit etre visible ou non
	 */
    public ConversationFenetre(ArrayList<String> listNicknames, TreeSet<Integer> listUserId, boolean visible){
        historic = new JTextArea(10,30);
        historic.setLineWrap(true) ;
        writerArea = new JTextArea(10,30);
        writerArea.setLineWrap(true) ;
        sendButton = new JButton("Send");
        fileButton = new JButton("File");
        fileChooser = new JFileChooser();
        this.listOfNicknames = new ArrayList<String>(listNicknames) ;
        this.listOfId = new TreeSet<Integer>(listUserId) ;
        String title = "" ;
        for (int i = 0; i<this.listOfNicknames.size() ; i++) {
        	if (i < this.listOfNicknames.size()-1) {
        		title += this.listOfNicknames.get(i)+", ";
        	}
        	else 
        		title += this.listOfNicknames.get(i);
        }
        this.setTitle(title);
        initializeComponents(visible);
}

	
	
	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/

    /**
     * Permet d'etablir le lien vers GUIView
     * @param objet GUIView
     */
	public void setGuiView (GUIView view) {
		guiView = GUIView.getInstance() ;
	}

	/**
	 * Getter de la liste des ID des users
	 * @return la liste des ID de la conversation
	 */
	public TreeSet<Integer> getListOfIds () {
		return this.listOfId ;
	}
	
	/**
	 * Getter de la liste des nicknames
	 * @return la liste des nicknames de la conversation
	 */
	public ArrayList<String> getListOfNicknames() {
		return this.listOfNicknames ;
	}
	
	/**
	 * Getter de l'espace historique
	 * @return l'objet contenant l'historique
	 */
	public JTextArea getHistoricArea () {
		return this.historic ;
	}
	
	/**
	 * Getter de l'espace d'ecriture
	 * @return l'objet contenant l'espace d'ecriture
	 */
	public JTextArea getWriteArea () {
		return this.writerArea ;
	}
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/


	/**
	 * Methode d'initialisation des composants de la fenetre de conversation
	 * @param boolean indiquant si la fenetre doit etre visible ou non
	 */
	public void initializeComponents(boolean visible){
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		historic.setEditable(false);
        writerArea.setSize(400, 100);
        sendButton.addActionListener(this);
        fileButton.addActionListener(this);
        historic.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        writerArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JPanel generalPanel = new JPanel(new BorderLayout());
        
        JPanel writePartPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(historic);
        writePartPanel.add(scrollPane) ;
        JPanel writeAreaPanel = new JPanel();
        JScrollPane scrollPane2 = new JScrollPane(writerArea);
        writeAreaPanel.add(scrollPane2);
        
        JPanel buttonsPanel = new JPanel(new BorderLayout());
        JPanel invitePanel = new JPanel();
        JPanel historicAndInvitePanel = new JPanel(new BorderLayout());
        JPanel historicPanel = new JPanel();
        historicPanel.add(scrollPane,BorderLayout.EAST);
        historicAndInvitePanel.add(invitePanel,BorderLayout.NORTH);
        historicAndInvitePanel.add(historicPanel,BorderLayout.SOUTH);
        writePartPanel.add(scrollPane2);
        buttonsPanel.add(sendButton,BorderLayout.NORTH);
        buttonsPanel.add(fileButton,BorderLayout.SOUTH);
        writePartPanel.add(buttonsPanel);
        generalPanel.add(historicAndInvitePanel,BorderLayout.NORTH);
        generalPanel.add(writePartPanel,BorderLayout.SOUTH);
        

        this.getContentPane().add(generalPanel);
        this.pack();
        this.setVisible(visible);
	}

	/**
	 * Methode permettant la mise a jour de la fenetre
	 * Par exemple : lorsque un user quitte la conversation
	 */
	protected void miseAJourFenetre () {	
		if (this.listOfNicknames.isEmpty()) {
			// conversation terminee
			this.writerArea.setForeground(Color.red) ;
			this.writerArea.setText("Your partner is disconnected.\nYou can close this window.") ;
			this.writerArea.setEditable(false) ;
			this.sendButton.setEnabled(false) ;
			this.fileButton.setEnabled(false) ;
		}
		else {
			String title = null ;
			for (int i = 0; i<this.listOfNicknames.size();i++) {
				// cas du dernier element
				if (i == this.listOfNicknames.size()-1) {
					title = title + this.listOfNicknames.get(i) ;
				}
				else 
					title = title + "this.listOfNicknames.get(i), ";
			}
			this.setTitle(title) ;
		}
	}
	
	/**
	 * Methode appelee lorsque l'utilisateur agit sur un des boutons de la fenetre
	 */
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == sendButton){
			if (guiView.getGUIControler().getEtat() == Etats.connected) {
				if (!this.writerArea.getText().isEmpty()) {
					ConversationFenetre.guiView.TextMessage(this.writerArea.getText(), listOfId);
					this.writerArea.setText("") ;
				}
			}
		}
		else if (arg0.getSource() == fileButton){
			int returnVal = fileChooser.showOpenDialog(this);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fileChooser.getSelectedFile();
	            ConversationFenetre.guiView.FileMessage(file, listOfId);
			}
			
			
			
		}	
	}

	@Override
	public void mouseClicked(MouseEvent e) { }

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
	public void windowClosing(WindowEvent e) { }

	@Override
	public void windowDeactivated(WindowEvent e) { }

	@Override
	public void windowDeiconified(WindowEvent e) { }

	@Override
	public void windowIconified(WindowEvent e) { }

	@Override
	public void windowOpened(WindowEvent e) { }

	@Override
	public void keyPressed(KeyEvent e) { }

	@Override
	public void keyReleased(KeyEvent e) { }

	@Override
	public void keyTyped(KeyEvent e) { }


	
}
