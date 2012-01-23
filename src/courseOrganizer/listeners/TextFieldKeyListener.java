package courseOrganizer.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import courseOrganizer.lookAndFeel.Fonts;

public class TextFieldKeyListener implements KeyListener
{
	JTextField field;
	String fieldText;
	
	public TextFieldKeyListener(JTextField field, String fieldText)
	{
		this.field = field;
		this.fieldText = fieldText;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0)
	{
		
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		if (field.getText().equals(fieldText))
		{
			field.setText("");
		}
		field.setFont(Fonts.DEFAULT_FONT);
	}

}
