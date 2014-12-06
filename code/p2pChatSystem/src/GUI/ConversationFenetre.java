package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import org.omg.CORBA.portable.UnknownException;

import GUI.GUIControler.Etats;

@SuppressWarnings("serial")
public class ConversationFenetre extends AbstractFenetre{

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	private static GUIView guiView ;
	
	private JTextArea historic;
	
	private JTextArea writerArea;
	
	private JButton sendButton;
	
	private JButton invite;
	
	private TreeSet <Integer> listOfId ;
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	public ConversationFenetre(String nickname, int UserId) {
		this.setTitle(nickname);
		historic = new JTextArea(20,30);
		JScrollPane scrollPane = new JScrollPane(historic);
		writerArea = new JTextArea(10,30);
		sendButton = new JButton("Send");
		invite = new JButton("Invite");
		initializeComponents();
		this.listOfId = new TreeSet<Integer>() ;
		this.listOfId.add(UserId) ;
	}
	
	
	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/
	
	public static GUIView getGUIView() {
		return guiView;
	}

	
	public void setGuiView (GUIView view) {
		guiView = view.getInstance() ;
	}
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/

	/*
	 * Attention, les methodes suivantes :
	 * public void actionPerformed(ActionEvent arg0)
	 * public void update(Observable arg0, Object arg1)
	 * doivent etre implementer dans les classes filles
	 */
	
	public void initializeComponents(){
		historic.setEditable(false);
		historic.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		writerArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		sendButton.addActionListener(this);
		JPanel generalPanel = new JPanel(new BorderLayout());
		JPanel writePartPanel = new JPanel();
		JPanel invitePanel = new JPanel();
		JPanel historicAndInvitePanel = new JPanel(new BorderLayout());
		JPanel historicPanel = new JPanel();
		invitePanel.add(invite);
		historicPanel.add(historic,BorderLayout.EAST);
		historicAndInvitePanel.add(invitePanel,BorderLayout.NORTH);
		historicAndInvitePanel.add(historicPanel,BorderLayout.SOUTH);
		writePartPanel.add(writerArea);
		writePartPanel.add(sendButton);
		generalPanel.add(historicAndInvitePanel,BorderLayout.NORTH);
		generalPanel.add(writePartPanel,BorderLayout.SOUTH);
		this.getContentPane().add(generalPanel);
		this.pack();
		this.setVisible(true);
		
	}

	public void miseAJour(Observable arg0) {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == sendButton){
			if (guiView.getGUIControler().getEtat() == Etats.connected) {
				if (this.writerArea.getText() != null) {
					try {
						getGUIView().TextMessage(this.writerArea.getText(), listOfId);
					} catch (UnknownHostException e) {
						System.out.println("ERREUR Conversation : UnknownHostException") ;
					}
				}
			}
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
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
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
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	
}
