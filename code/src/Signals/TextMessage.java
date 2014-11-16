package Signals;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class TextMessage extends AbstractMessage{

	private ArrayList<String> listNicknamesDest;
	private String message;
	
	
	/* Constructeur principal
	 * La valeur test est mise par défaut mais ce sera le nom de l'utilisateur local.
	 * @param message : le message à envoyer
	 * Remarque : Une nouvelle liste de nickname (vide) est créée, il faut ajouter un par un les destinataires via une méthode
	 */
	public TextMessage(String message){
		this.nickname = "TEST";
		this.type = typeContenu.TEXTMESSAGE;
		this.message = message;
		this.listNicknamesDest = new ArrayList<String>();
	}
	
	/*
	 * Constructeur secondaire au cas où l'on possède déjà une liste d'utilisateurs
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
	
	
	// Méthodes
	
	public void addNickame(String nicknameDest){
		this.listNicknamesDest.add(nicknameDest);
	}
	
	public void removeNickname(String nicknameDest){
		this.listNicknamesDest.remove(nicknameDest);
	}
	
	
	
}
