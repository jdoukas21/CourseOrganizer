package courseOrganizer.listeners;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import courseOrganizer.lookAndFeel.Fonts;

public class ButtonMouseListener implements MouseListener
{
	private JButton button;
	private Color backgroundColor;
	private Color fontColor;
	private Color highlightBackground = Color.BLACK;
	private Color highlightForeground = Color.WHITE;
	
	private String type;
	
	public ButtonMouseListener(JButton button)
	{
		this.button = button;
		backgroundColor = button.getBackground();
		fontColor = button.getForeground();
	}
	
	public ButtonMouseListener(JButton button, Color background, Color foreground)
	{
		this.button = button;
		backgroundColor = button.getBackground();
		fontColor = button.getForeground();
		highlightBackground = background;
		highlightForeground = foreground;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		button.setBackground(highlightBackground);
		button.setForeground(highlightForeground);
	}
	
	@Override
	public void mouseExited(MouseEvent e)
	{
		button.setBackground(backgroundColor);
		button.setForeground(fontColor);
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		
	}

}