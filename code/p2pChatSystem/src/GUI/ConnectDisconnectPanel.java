package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

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
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private ConnectDisconnectPanel(){
		// On initialise les param�tres
		labelNickname = new JLabel("Nom :");
		nameOfLocalUser = new JTextField("Entrez votre nom ici");
		buttonConnectOnOff = new JButton("Connexion");
		// On les ajoute � notre panel
		this.add(labelNickname);
		this.add(nameOfLocalUser);
		this.add(buttonConnectOnOff);
		this.setVisible(true);
		}
	
	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/
	
	public JLabel getLabelNickname() {
		return labelNickname;
	}

	public void setLabelNickname(JLabel labelNickname) {
		this.labelNickname = labelNickname;
	}

	public JTextField getNameOfLocalUser() {
		return nameOfLocalUser;
	}

	public void setNameOfLocalUser(JTextField nameOfLocalUser) {
		this.nameOfLocalUser = nameOfLocalUser;
	}

	public JButton getButtonConnectOnOff() {
		return buttonConnectOnOff;
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
