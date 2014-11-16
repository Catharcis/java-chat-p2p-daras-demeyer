package Signals;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class TextMessage extends AbstractMessage{

	private ArrayList<String> listNicknamesDest;
	private String message;
	
	
	/* Constructeur principal
	 * La valeur test est mise par d�faut mais ce sera le nom de l'utilisateur local.
	 * @param message : le message � envoyer
	 * Remarque : Une nouvelle liste de nickname (vide) est cr��e, il faut ajouter un par un les destinataires via une m�thode
	 */
	public TextMessage(String message){
		this.nickname = "TEST";
		this.type = typeContenu.TEXTMESSAGE;
		this.message = message;
		this.listNicknamesDest = new ArrayList<String>();
	}
	
	/*
	 * Constructeur secondaire au cas o� l'on poss�de d�j� une liste d'utilisateurs
	 */
	public TextMessage(String message, ArrayList<String> list){
		this.nickname = "TEST";
		this.type = typeContenu.TEXTMESSAGE;
		this.message = message;
		this.listNicknamesDest = list;
	}
	
	// Getters et Setters
	
	public ArrayList<String> getListNicknamesDest(){
		return this.listNicknamesDest;
	}
	
	public String getMessage(){
		return this.message;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	
	// M�thodes
	
	public void addNickame(String nicknameDest){
		this.listNicknamesDest.add(nicknameDest);
	}
	
	public void removeNickname(String nicknameDest){
		this.listNicknamesDest.remove(nicknameDest);
	}
	
	
	
}
