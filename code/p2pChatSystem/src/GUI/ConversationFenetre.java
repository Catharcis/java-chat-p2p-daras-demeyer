package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ConversationFenetre extends AbstractFenetre{

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private JTextArea historic;
	
	private JTextArea writerArea;
	
	private JButton sendButton;
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	public ConversationFenetre(){
		historic = new JTextArea(20,30);
		JScrollPane scrollPane = new JScrollPane( historic );
		writerArea = new JTextArea(10,30);
		sendButton = new JButton("Send");
		initializeComponents();
	}
	
	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/
	
	
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/

	/*
	 * Attention, les m�thodes suivantes :
	 * public void actionPerformed(ActionEvent arg0)
	 * public void update(Observable arg0, Object arg1)
	 * doivent �tre impl�menter dans les classes filles
	 */
	
	public void initializeComponents(){
		
		historic.setEditable(false);
		writerArea.setSize(400, 100);
		sendButton.addActionListener(this);
		JPanel generalPanel = new JPanel(new BorderLayout());
		JPanel writePartPanel = new JPanel();
		JPanel historicPanel = new JPanel();
		historicPanel.add(historic);
		writePartPanel.add(writerArea);
		writePartPanel.add(sendButton);
		generalPanel.add(historicPanel,BorderLayout.NORTH);
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
	
}
