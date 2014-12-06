package GUI;

import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ConnectDisconnectPanel extends JPanel {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private static ConnectDisconnectPanel singleton;
	
	private JLabel labelNickname;
	
	private JTextField nameOfLocalUser;
	
	private JButton buttonConnectOnOff;
	
	private JLabel status;
	
	private JLabel imageStatus;
	
	private JLabel imageChat;
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private ConnectDisconnectPanel(){
		// On initialise les parametres
		labelNickname = new JLabel("Please choose your nickname : ");
		status = new JLabel("Status : ");
		nameOfLocalUser = new JTextField(10) ;
		buttonConnectOnOff = new JButton("Connexion");
		imageStatus = new JLabel(new ImageIcon("offline.png"));
		imageChat = new JLabel(new ImageIcon("chat.png")) ;
		this.setVisible(true);
		}
	
	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/
	
	public void setLabelNickname(JLabel labelNickname) {
		this.labelNickname = labelNickname;
	}
	
	public JLabel getLabelNickname(){
		return labelNickname;
	}

	public JTextField getTextFieldNameOfLocalUser(){
		return nameOfLocalUser;
	}
	
	public String getNameOfLocalUser() {
		return nameOfLocalUser.getText();
	}
	
	public JTextField getNicknameField () {
		return nameOfLocalUser ;
	}

	public void setNameOfLocalUser(JTextField nameOfLocalUser) {
		this.nameOfLocalUser = nameOfLocalUser;
	}

	public JButton getButtonConnectOnOff() {
		return buttonConnectOnOff;
	}
	
	public JLabel getStatus(){
		return status;
	}
	
	public JLabel getImageStatus(){
		return imageStatus;
	}
	
	public void setImageStatus(ImageIcon newIcon) {
		this.imageStatus.setIcon(newIcon);;
		this.validate();
	}
	
	public JLabel getImageChat() {
		return imageChat ;
	}
	
	public void setImageChat(ImageIcon newIcon) {
		this.imageChat = new JLabel(newIcon) ;
	}
	
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/

	public static ConnectDisconnectPanel getInstance(){
		if (singleton == null){
			singleton = new ConnectDisconnectPanel();
		}
		return singleton;
	}
	
	/*
	 * Attention, les m�thodes suivantes :
	 * public void actionPerformed(ActionEvent arg0)
	 * public void update(Observable arg0, Object arg1)
	 * doivent �tre impl�menter dans les classes filles
	 */

	/*
	 * Permet de mettre � jour la fen�tre dans le cas o� un objet observ� a �t� modifi�
	 */
	public void miseAJour(Observable arg0) {

		
	}
	
}
