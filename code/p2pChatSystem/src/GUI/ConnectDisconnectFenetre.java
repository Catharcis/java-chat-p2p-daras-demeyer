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
public class ConnectDisconnectFenetre extends AbstractFenetre implements MouseListener{

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private static ConnectDisconnectFenetre conDisFen;
	
	private JLabel labelNickname;
	
	private JTextField nameOfLocalUser;
	
	private JButton buttonConnectOnOff;
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private ConnectDisconnectFenetre(){
		labelNickname = new JLabel("Nom :");
		nameOfLocalUser = new JTextField("Entrez votre nom ici");
		buttonConnectOnOff = new JButton("Connexion");
		initializeComponents();
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

	public static ConnectDisconnectFenetre getInstance(){
		if (conDisFen == null){
			conDisFen = new ConnectDisconnectFenetre();
		}
		return conDisFen;
	}
	
	/*
	 * Attention, les m�thodes suivantes :
	 * public void actionPerformed(ActionEvent arg0)
	 * public void update(Observable arg0, Object arg1)
	 * doivent �tre impl�menter dans les classes filles
	 */

	public void initializeComponents(){
		this.setTitle("Connexion/D�connexion"); //On donne un titre � l'application
		this.setSize(320,100); //On donne une taille � notre fen�tre
		this.setLocationRelativeTo(null); //On centre la fen�tre sur l'�cran
		this.setResizable(true); //On permet le redimensionnement
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //On dit � l'application de se fermer lors du clic sur la croix
		this.nameOfLocalUser.addMouseListener(this);
		this.buttonConnectOnOff.addActionListener(this);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(labelNickname);
		panel.add(nameOfLocalUser);
		panel.add(buttonConnectOnOff);
		this.setContentPane(panel);
	
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		if (arg0 instanceof NetworkInformation){
			System.out.println("Oh ! Les informations du reseau ont changes !");
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == this.buttonConnectOnOff){
			if (this.buttonConnectOnOff.getText() == "Connexion"){
				this.buttonConnectOnOff.setText("Deconnexion");
				this.nameOfLocalUser.setEnabled(false);;
				NetworkInformation NI = null;
				NI = NI.getInstance();
			}
			else
			{
				this.nameOfLocalUser.setEnabled(true);
				this.buttonConnectOnOff.setText("Connexion");
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		if (this.nameOfLocalUser.isEnabled()){
			this.nameOfLocalUser.setText("");
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
