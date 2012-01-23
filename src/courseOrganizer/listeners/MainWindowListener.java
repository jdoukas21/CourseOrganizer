package courseOrganizer.listeners;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import courseOrganizer.views.MainWindow;

public class MainWindowListener implements ComponentListener, WindowListener
{
	private MainWindow	window;
	private boolean		firstOpened	= false;
	private String		noteString = "";

	public MainWindowListener(MainWindow window)
	{
		this.window = window;
		window.setVisible(true);
	}

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

	@Override
	public void componentResized(ComponentEvent arg0)
	{
		if (firstOpened)
		{
			window.draw(false);
		}
	}

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
		// TODO Auto-generated method stub
		//prompt if user would like to save current note
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
