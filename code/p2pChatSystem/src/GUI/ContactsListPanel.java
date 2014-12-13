package GUI; 
 
import java.util.ArrayList; 
import javax.swing.DefaultListModel; 
import javax.swing.JList;
import javax.swing.JPanel; 
import javax.swing.ListSelectionModel; 

/**
 * @author Valérie Daras et Alexandre Demeyer
 */ 

@SuppressWarnings("serial")
public class ContactsListPanel extends JPanel{ 
 
    /*************************************************  
     *                 ATTRIBUTS & FIELDS  
     ************************************************/ 
    
	/**
	 * Singleton
	 */
    private static ContactsListPanel singleton; 
    
    /**
     * Pointeur d'objet (composant) JList(String)
     */
    private JList<String> list; 
     
    /**
     * Modele de la JList pour pouvoir la modifier facilement 
     */
    private DefaultListModel<String> dlm; 
     
    /**
     * Permet d'associer le numero de la liste "list" a l'id utilisateur 
     */
    private ArrayList<String> arrayIdListToIdUser; 
     
    /*************************************************  
     *                 CONSTRUCTOR  
     ************************************************/ 
    
    /**
     * Constructeur par defaut qui initialise les parametres
     */
    private ContactsListPanel(){ 
        this.dlm = new DefaultListModel<String>(); 
        this.list = new JList<String>(dlm); 
        this.add(list); 
        this.arrayIdListToIdUser = new ArrayList<String> () ;
        this.setVisible(true); 
        this.initComponents() ;
    } 
 
	/**
	 * Creer l'instance si elle n'est pas cree ou la recupere
	 * @return l'instance ContactListPanel
	 */
    public static ContactsListPanel getInstance(){ 
        if (singleton == null){ 
            singleton = new ContactsListPanel(); 
        } 
        return singleton; 
    } 
     
     
    /*************************************************  
     *                 GETTERS & SETTERS 
     ************************************************/ 
     
    /**
     * Getter de list
     * @return l'objet JList(String)
     */
    public JList<String> getList(){ 
        return list; 
    } 
     
    /**
     * Getter de dlm
     * @return l'objet DefaultListModel(String)
     */
    public DefaultListModel<String> getDefaultListModel(){ 
        return dlm; 
    } 
    
    /**
     * Getter de arrayIdListToIdUser
     * @return l'objet ArrayList<String>
     */
    public ArrayList<String> getArrayIdListToIdUser(){ 
        return arrayIdListToIdUser; 
    } 
     
    /*************************************************  
     *                     METHODS  
     ************************************************/ 
    
    /**
     * Permet d'initialiser les composants du panel et de les rendre visible
     */
    public void initComponents () { 
        list.setLayoutOrientation(JList.VERTICAL); 
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION) ; 
        list.setFixedCellWidth(100); 
        list.setFixedCellHeight(10);
        list.setVisibleRowCount(5);
        this.setVisible(true);        
    } 
     
} 
