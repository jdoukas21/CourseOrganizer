package courseOrganizer.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class DisposeButtonListener implements ActionListener
{
	private JFrame container; 
	
	public DisposeButtonListener(JFrame c)
	{
		container = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		container.dispose();
	}

}
