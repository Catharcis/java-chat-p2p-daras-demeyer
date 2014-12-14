package GUI;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

/**
 * @author Valérie Daras et Alexandre Demeyer
 */

@SuppressWarnings("serial")
public abstract class AbstractFenetre extends JFrame implements ActionListener, MouseListener, WindowListener, KeyListener {

	/************************************************* 
	 * 				ATTRIBUTS & FIELDS 
	 ************************************************/
	

	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	
	/************************************************* 
	 * 				GETTERS & SETTERS
	 ************************************************/
	
	
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/

	/**
	 * Permet d'initialiser les composants d'une classe
	 * @param visible : composants visible si vrai sinon faux
	 */
	public abstract void initializeComponents(boolean visible);
	
}
