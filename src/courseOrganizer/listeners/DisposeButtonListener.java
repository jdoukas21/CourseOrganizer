package courseOrganizer.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

// Αφορά τη λειτουργικότητα του cancel κουμπιού
public class DisposeButtonListener implements ActionListener
{
	private JFrame container; 
	
	public DisposeButtonListener(JFrame c)
	{
		container = c;
	}
	
        // Εκτελείται μόλις πατηηθεί το cancel κουμπί.
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		container.dispose(); // κλείνει το frame - παράθυρο.
	}

}
