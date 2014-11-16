package Signals;

@SuppressWarnings("serial")
public class Goodbye extends AbstractMessage{

	
	// Constructeur principal
	// La valeur test est mise par défaut mais ce sera le nom de l'utilisateur local.
	public Goodbye(){
		this.nickname = "TEST";
		this.type = typeContenu.GOODBYE;
	}

}
