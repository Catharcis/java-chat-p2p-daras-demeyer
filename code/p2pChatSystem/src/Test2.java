import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.FileChooserUI;

import GUI.ChatFenetre;
import GUI.ConnectDisconnectPanel;
import GUI.ConversationFenetre;
import GUI.GUIView;
import NI.NIControler;
import Signals.Hello;


public class Test2 {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame() ;
		frame.setTitle("Chat System") ; 
		frame.setSize(500, 400);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);

	    //On crée nos différents conteneurs de couleur différente
	    JPanel cell1 = new JPanel();
	    cell1.setBackground(Color.white);
	    cell1.setPreferredSize(new Dimension(160, 75));	
	    
	    JPanel cell2 = new JPanel();
	    cell2.setBackground(Color.white);
	    cell2.setPreferredSize(new Dimension(180, 75));
	    JTextField nameOfLocalUser = new JTextField(15) ;
	    JLabel labelNickname = new JLabel("Nickname :");
	    cell2.add(labelNickname, BorderLayout.NORTH) ;
	    cell2.add(nameOfLocalUser, BorderLayout.CENTER) ;
	    
	    JPanel cell3 = new JPanel();
	    cell3.setBackground(Color.white);
	    cell3.setPreferredSize(new Dimension(160, 75));
	    JLabel imageStatus = new JLabel(new ImageIcon("offline.png")) ;
	    cell3.setLayout(new BorderLayout());
	    cell3.add(imageStatus, BorderLayout.CENTER) ;
	    
	    JPanel cell4 = new JPanel();
	    cell4.setBackground(Color.white);
	    cell4.setPreferredSize(new Dimension(500, 75));
	    JButton connect = new JButton("Connexion") ;
	    connect.setMargin(new Insets(10,10,10,10)) ;
	    cell4.add(connect) ;
	    
	    JPanel cell5 = new JPanel();
	    cell5.setBackground(Color.white);
	    cell5.setPreferredSize(new Dimension(300, 250));
	    JLabel imageChat = new JLabel(new ImageIcon("imagechat.png")) ;
	    cell5.setLayout(new BorderLayout());
	    cell5.add(imageChat, JLabel.CENTER) ;
	    
	    JPanel cell6 = new JPanel();
	    cell6.setBackground(Color.BLUE);
	    cell6.setPreferredSize(new Dimension(200, 250));

		
	    //Le conteneur principal
	    JPanel content = new JPanel();
	    content.setSize(new Dimension(500, 400));
	    content.setBackground(Color.WHITE);
	    //On définit le layout manager
	    content.setLayout(new GridBagLayout());
			
	    //L'objet servant à positionner les composants
	    GridBagConstraints gbc = new GridBagConstraints();
			
	    //On positionne la case de départ du composant
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    //La taille en hauteur et en largeur
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    content.add(cell1, gbc);
	    //---------------------------------------------
	    gbc.gridx = 1;
	    content.add(cell2, gbc);
	    //---------------------------------------------
	    gbc.gridx = 2;		
	    content.add(cell3, gbc);		
	    //---------------------------------------------
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 2;
	    //Celle-ci indique que la cellule se réplique de façon verticale
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    content.add(cell4, gbc);
	    //---------------------------------------------
	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    gbc.gridwidth = 1;
	    gbc.anchor = GridBagConstraints.LAST_LINE_START ;
	    //Celle-ci indique que la cellule se réplique de façon horizontale
	    gbc.fill = GridBagConstraints.VERTICAL;
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    content.add(cell5, gbc);
	    //---------------------------------------------
	    //---------------------------------------------
	    gbc.gridx = 1;
	    gbc.gridy = 3;
	    //Celle-ci indique que la cellule se réplique de façon horizontale
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.fill = GridBagConstraints.VERTICAL;
	    gbc.anchor = GridBagConstraints.LAST_LINE_END ;
	    content.add(cell6, gbc);
	    //---------------------------------------------

	    //On ajoute le conteneur
	    frame.setContentPane(content);
		frame.setVisible(true) ;
	}

}
