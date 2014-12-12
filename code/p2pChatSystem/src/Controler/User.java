package Controler;

/**
 * @author Valérie Daras et Alexandre Demeyer
 */

public class User {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	
	/** Identificateur unique d'un User **/
	private int idUser ;
	
	/** Nom d'utilisateur du User **/
	private String nickname ; 
	
	/** Identificateur statique **/
	private static int lastID = 0 ;
	
	
	
	/************************************************* 
	 * 					CONSTRUCTOR
	 ************************************************/
	
	/** 
	 * Constructeur du User
	 * @param nickname : nom de l'utilisateur
	 */
	public User (String nickname) {
		this.nickname = nickname ;
		this.idUser = lastID++ ;
	}
	
	
	/************************************************* 
	 * 				GETTERS & SETTERS 
	 ************************************************/
	
	/** 
	 * Getter du idUser 
	 * @return l'id de l'utilisateur
	 */
	public int getIdUser () {
		return idUser ;
	}
	
	/** 
	 * Getter du Nickname 
	 * @return le nom de l'utilisateur
	 */
	public String getNickname () {
		return nickname ;
	}
	
	/** Redefinition de la methode toString() **/
	public String toString(){
		return "[id = "+this.idUser+", name = "+this.nickname+"]";
	}
	
	/************************************************* 
	 * 					METHODS
	 ************************************************/
	
	/**
	 * Permet de réinitialiser les id utilisateur
	 */
	public static void reinitializeUsers () {
		lastID = 0 ;
	}
	
}
