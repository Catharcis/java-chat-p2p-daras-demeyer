package Signals;

@SuppressWarnings("serial")
public class Hello extends AbstractMessage {

	
	// Constructeur principal
	// La valeur test est mise par défaut mais ce sera le nom de l'utilisateur local.
	public Hello(){
		this.nickname = "TEST";
		this.type = typeContenu.HELLO;
	}
	
}