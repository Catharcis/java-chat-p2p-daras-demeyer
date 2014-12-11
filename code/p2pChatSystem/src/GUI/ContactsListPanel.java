package GUI; 
 
import java.util.ArrayList; 
import java.util.Observable; 
import javax.swing.DefaultListModel; 
import javax.swing.JList;
import javax.swing.JPanel; 
import javax.swing.ListSelectionModel; 

 
@SuppressWarnings("serial")
public class ContactsListPanel extends JPanel{ 
 
    /*************************************************  
     *                 ATTRIBUTS & FIELDS  
     ************************************************/ 
     
    private static ContactsListPanel singleton; 
    
    private JList<String> list; 
     
    // Modele de la JList pour pouvoir la modifier facilement 
    private DefaultListModel<String> dlm; 
     
    // Permet d'associer le numero de la liste "list" a l'id utilisateur 
    private ArrayList<String> arrayIdListToIdUser; 
     
    /*************************************************  
     *                 CONSTRUCTOR  
     ************************************************/ 
     
    private ContactsListPanel(){ 
        this.dlm = new DefaultListModel<String>(); 
        this.list = new JList<String>(dlm); 
        this.add(list); 
        this.arrayIdListToIdUser = new ArrayList<String> () ;
        this.setVisible(true); 
        this.initComponents() ;
    } 
 
    public static ContactsListPanel getInstance(){ 
        if (singleton == null){ 
            singleton = new ContactsListPanel(); 
        } 
        return singleton; 
    } 
     
     
    /*************************************************  
     *                 GETTERS & SETTERS 
     ************************************************/ 
     
    public JList<String> getList(){ 
        return list; 
    } 
     
    public DefaultListModel<String> getDefaultListModel(){ 
        return dlm; 
    } 
     
    public ArrayList<String> getArrayIdListToIdUser(){ 
        return arrayIdListToIdUser; 
    } 
     
    /*************************************************  
     *                     METHODS  
     ************************************************/ 
     
    public void initComponents () { 
        list.setLayoutOrientation(JList.VERTICAL); 
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION) ; 
        list.setFixedCellWidth(100); 
        list.setFixedCellHeight(10);
        list.setVisibleRowCount(5);
        this.setVisible(true);        
    } 
     
    public void miseAJour(Observable arg0) { 
 
    } 
     
} 
