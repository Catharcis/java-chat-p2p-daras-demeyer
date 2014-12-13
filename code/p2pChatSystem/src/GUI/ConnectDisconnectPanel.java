package GUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Valérie Daras et Alexandre Demeyer
 */

@SuppressWarnings("serial")
public class ConnectDisconnectPanel extends JPanel {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	/**
	 * Singleton
	 */
	private static ConnectDisconnectPanel singleton;
	
	/**
	 * Pointeur d'objet (composant) JLabel
	 */
	private JLabel labelNickname;
	
	/**
	 * Pointeur d'objet (composant) JTextField
	 */
	private JTextField nameOfLocalUser;
	
	/**
	 * Pointeur d'objet (composant) JButton
	 */
	private JButton buttonConnectOnOff;
	
	/**
	 * Pointeur d'objet (composant) JLabel
	 */
	private JLabel imageStatus;
	
	/**
	 * Pointeur d'objet (composant) JLabel
	 */
	private JLabel imageChat;
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	/**
	 * Constructeur par defaut qui initialise les champs, objets et images
	 */
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
	
	/**
	 * Setter de labelNickname
	 * @param labelNickname : l'objet JLabel
	 */
	public void setLabelNickname(JLabel labelNickname) {
		this.labelNickname = labelNickname;
	}
	
	/**
	 * Getter de labelNickname
	 * @return : l'objet JLabel
	 */
	public JLabel getLabelNickname(){
		return labelNickname;
	}
	
	/**
	 * Getter recuperant la valeur de la chaine de caractere de nameOfLocalUser
	 * @return : la chaine de caractere de l'objet JTextField
	 */
	public String getNameOfLocalUser() {
		return nameOfLocalUser.getText();
	}
	
	/**
	 * Getter de nameOfLocalUser
	 * @return l'objet JTextField
	 */
	public JTextField getNicknameField () {
		return nameOfLocalUser ;
	}

	/**
	 * Setter de nameOfLocalUser
	 * @param nameOfLocalUser : l'objet JTextField
	 */
	public void setNameOfLocalUser(JTextField nameOfLocalUser) {
		this.nameOfLocalUser = nameOfLocalUser;
	}

	/**
	 * Getter de buttonConnectOnOff
	 * @return l'objet JButton
	 */
	public JButton getButtonConnectOnOff() {
		return buttonConnectOnOff;
	}
	
	/**
	 * Getter de imageStatus
	 * @return l'objet JLabel
	 */
	public JLabel getImageStatus(){
		return imageStatus;
	}
	
	/**
	 * Setter de imageStatus
	 * @param newIcon : l'objet ImageIcon representant la nouvelle image
	 */
	public void setImageStatus(ImageIcon newIcon) {
		this.imageStatus.setIcon(newIcon);;
		this.validate();
	}
	
	/**
	 * Getter de imageChat
	 * @return l'objet JLabel
	 */
	public JLabel getImageChat() {
		return imageChat ;
	}
	
	/**
	 * Setter de imageChat
	 * @param newIcon : l'objet ImageIcon representant la nouvelle image
	 */
	public void setImageChat(ImageIcon newIcon) {
		this.imageChat = new JLabel(newIcon) ;
	}
	
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/

	/**
	 * Creer l'instance si elle n'est pas cree ou la recupere
	 * @return l'instance ConnectDisconnectPanel
	 */
	public static ConnectDisconnectPanel getInstance(){
		if (singleton == null){
			singleton = new ConnectDisconnectPanel();
		}
		return singleton;
	}
	
}
