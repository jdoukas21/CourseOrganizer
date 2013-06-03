package courseOrganizer.listeners;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;

import courseOrganizer.views.MainWindow;

/* Αυτή η κλάση "ακούει" τα συμβάντα του παραθύρου της εφαρμογής. Δηλαδή 
 * ελέγχει την αλληλεπίδραση του χρήστη με το βασικό παράθυρο.*/
public class MainWindowListener implements ComponentListener, WindowListener
{
	private MainWindow      window;
	private boolean         firstOpened     = false;
	private String          noteString = "";

	public MainWindowListener(MainWindow window)
	{
		this.window = window;
		window.setVisible(true);
	}

        /* Οι μέθοδοι που ακολουθούν επικαλύπτουν τις default των κλάσεων 
         * ComponentListener και WindowListener επειδή θέλουμε να προσθέσουμε
         * άλλη λειτουργικότητα.*/
        
	@Override
	public void componentHidden(ComponentEvent arg0)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void componentMoved(ComponentEvent arg0)
	{
		// TODO Auto-generated method stub

	}

        // Δολεύει όταν αλλάζει η διάσταση του παραθύρου
	@Override
	public void componentResized(ComponentEvent arg0)
	{                     
		if (firstOpened)
		{
			window.draw(false);
		}
	}

        // Δουλεύει όταν πρωτο-ανοίγει το πρόγραμμα
	@Override
	public void componentShown(ComponentEvent arg0)
	{
		// TODO Auto-generated method stub
		firstOpened = true;
		//System.out.println("(componentShown) FirstOpened = " + firstOpened);

	}

	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		// TODO Auto-generated method stub
		//System.out.println("That shit is closing ;_;");
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
                // Εκτελείται οταν κλείνει το παράθυο mainWindow
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

}
