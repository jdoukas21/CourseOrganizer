package courseOrganizer.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import courseOrganizer.lookAndFeel.Fonts;

public class TextFieldMouseListener implements MouseListener
{
	private JTextField field;
	private String fieldText;
	
	public TextFieldMouseListener(JTextField field, String fieldText)
	{
		this.field = field;
		this.fieldText = fieldText;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		if (field.getFont() == Fonts.ITALIC_FONT)
		{
			field.setText(null);
			field.setFont(Fonts.DEFAULT_FONT);
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		if (field.getText().isEmpty())
		{
			field.setText(fieldText);
			field.setFont(Fonts.ITALIC_FONT);
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	

}
