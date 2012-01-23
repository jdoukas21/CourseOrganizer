package courseOrganizer.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import courseOrganizer.models.notepad.NotePaper;
import courseOrganizer.views.MainWindow;

public class NotepaperListener implements KeyListener
{
	private MainWindow mainWindow;
	private NotePaper paper;
	
	public NotepaperListener(MainWindow mw, NotePaper paper)
	{
		this.paper = paper;
		mainWindow = mw;
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
		String text = paper.getText();
		mainWindow.setSaveString(text);
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
		String text = paper.getText();
		mainWindow.setSaveString(text);
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub
		String text = paper.getText();
		mainWindow.setSaveString(text);
	}

}
