package Signals;

@SuppressWarnings("serial")
public class HelloAck extends AbstractMessage{

	
	// Constructeur principal
	// La valeur test est mise par défaut mais ce sera le nom de l'utilisateur local.
	public HelloAck(){
		this.nickname = "TEST";
		this.type = typeContenu.HELLOACK;
	}
	
}
