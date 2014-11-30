package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MessageErreur extends AbstractFenetre {

	
	public MessageErreur(String Tmessage) {
		 JOptionPane.showMessageDialog(this, Tmessage,"Nouveau message", JOptionPane.WARNING_MESSAGE);
		this.addWindowListener(this);
	    this.initializeComponents();
	}
	
	
	@Override
	public void initializeComponents() {
		// TODO Auto-generated method stub
		GridLayout grid = new GridLayout(2,1);
		this.setLayout(grid);
		
		this.setTitle("Error");
		this.setLocationRelativeTo(null);
	    //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.pack();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}
	

	@Override
	public void miseAJour(Observable arg0) {
		// TODO Auto-generated method stub

	}

}
