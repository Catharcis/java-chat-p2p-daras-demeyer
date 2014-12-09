package GUI;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.util.Observable;

import javax.swing.JFrame;


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

	/*
	 * Attention, les méthodes suivantes :
	 * public void actionPerformed(ActionEvent arg0)
	 * public void update(Observable arg0, Object arg1)
	 * doivent être implémenter dans les classes filles
	 */
	
	public abstract void initializeComponents();
	
	public abstract void miseAJour(Observable arg0);
}
