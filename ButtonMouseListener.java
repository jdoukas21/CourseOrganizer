import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonMouseListener implements MouseListener
{
	private JButton button;
	private Color backgroundColor;
	private Color fontColor;
	
	public ButtonMouseListener(JButton button)
	{
		this.button = button;
		backgroundColor = button.getBackground();
		fontColor = button.getForeground();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
	
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		button.setBackground(Color.BLACK);
		button.setForeground(Color.WHITE);
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