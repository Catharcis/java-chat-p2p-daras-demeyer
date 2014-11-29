package GUI;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ContactsListPanel extends JPanel{

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	private static ContactsListPanel singleton;
	
	// Permet d'avoir une liste de noms (pour le visuel)
	private JList<String> list;
	
	// Permet d'associer le numéro de la liste "list" à l'id utilisateur
	private ArrayList<String> arrayIdListToIdUser;
	
	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	private ContactsListPanel(){
		// TEST AVEC UN SIMPLE NOM
		String[] data = new String[10];
		data[0] = "Michel";
		list = new JList<String>(data);
		this.add(list);
		this.setVisible(true);
	}
	
	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/
	
	public JList<String> getList(){
		return list;
	}
	
	public ArrayList<String> getArrayIdListToIdUser(){
		return arrayIdListToIdUser;
	}
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/
	
	public static ContactsListPanel getInstance(){
		if (singleton == null){
			singleton = new ContactsListPanel();
		}
		return singleton;
	}
	
	
	public void miseAJour(Observable arg0) {

	}
	
}
