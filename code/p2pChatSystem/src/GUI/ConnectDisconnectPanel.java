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
	
	private JLabel imageStatus;
	
	private JLabel imageChat;
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private ConnectDisconnectPanel(){
		labelNickname = new JLabel("Nickname : ");
		nameOfLocalUser = new JTextField(15) ;
		buttonConnectOnOff = new JButton("Connexion");
		imageStatus = new JLabel(new ImageIcon("offline.png"));
		imageChat = new JLabel(new ImageIcon("imagechat.png")) ;
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
	
	public void miseAJour(Observable arg0) {

		
	}
	
}
