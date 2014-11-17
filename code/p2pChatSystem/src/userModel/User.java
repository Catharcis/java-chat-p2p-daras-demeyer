package userModel;

public class User {

	/** Identificateur unique d'un User **/
	private int idUser ;
	
	/** Nom d'utilisateur du User **/
	private String nickname ; 
	
	/** Identificateur statique **/
	private static int lastID = 0 ;
	
	/** Constructeur du User **/
	public User (String nickname) {
		this.nickname = nickname ;
		this.idUser = lastID++ ;
	}
	
	/** Getter du idUser **/
	public int getIdUser () {
		return idUser ;
	}
	
	/** Getter du Nickname **/
	public String getNickname () {
		return nickname ;
	}
	
}
