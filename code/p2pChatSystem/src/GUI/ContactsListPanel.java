package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

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
		DefaultListModel dlm = new DefaultListModel();
		this.list = new JList(dlm);
		this.add(list);
		this.arrayIdListToIdUser = new ArrayList<String> () ;
	}

	public static ContactsListPanel getInstance(){
		if (singleton == null){
			singleton = new ContactsListPanel();
		}
		return singleton;
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
	
	public void initComponents () {
		list.setLayoutOrientation(JList.VERTICAL);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setFixedCellWidth(100);
		list.setFixedCellHeight(10);
		list.setVisibleRowCount(5);
		this.setVisible(true);
		String[] data = new String [10] ;
		data[0] = "Michel" ;
		
	}
	
	
	public void miseAJour(Observable arg0) {

	}
	
}
