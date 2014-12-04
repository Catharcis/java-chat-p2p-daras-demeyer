package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controler.NetworkInformation;

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
	
	private JLabel image;
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private ConnectDisconnectPanel(){
		// On initialise les param�tres
		labelNickname = new JLabel("Please choose your nickname : ");
		status = new JLabel("Status : ");
		nameOfLocalUser = new JTextField(10) ;
		buttonConnectOnOff = new JButton("Connexion");
		image = new JLabel(new ImageIcon("offline.png"));
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
	
	public JLabel getImage(){
		return image;
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
