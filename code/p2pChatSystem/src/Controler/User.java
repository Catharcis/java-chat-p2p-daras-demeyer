package Controler;

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
	
	/** Constructeur du User **/
	public User (String nickname) {
		this.nickname = nickname ;
		this.idUser = lastID++ ;
	}
	
	
	/************************************************* 
	 * 				GETTERS & SETTERS 
	 ************************************************/
	
	/** Getter du idUser **/
	public int getIdUser () {
		return idUser ;
	}
	
	/** Getter du Nickname **/
	public String getNickname () {
		return nickname ;
	}
	
	/** Redefinition de la methode toString() **/
	public String toString(){
		return "[id = "+this.idUser+", name = "+this.nickname+"]";
	}
	
}
