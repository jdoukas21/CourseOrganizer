package courseOrganizer.listeners;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import courseOrganizer.lookAndFeel.Fonts;

//NEEDS WORK
public class TextFieldFocusListener implements FocusListener
{
	private JTextField field;
	private String fieldText;
	
	public TextFieldFocusListener(JTextField field, String fieldText)
	{
		this.field = field;
		this.fieldText = fieldText;
	}
	
	@Override
	public void focusGained(FocusEvent arg0)
	{
		if (field.getFont() == Fonts.ITALIC_FONT)
		{
			field.selectAll();
		}
	}

	@Override
	public void focusLost(FocusEvent arg0)
	{
		// TODO Auto-generated method stub
		/*if (field.getText().isEmpty())
		{
			field.setText(fieldText);
			field.setFont(Fonts.ITALIC_FONT);
		}*/
	}

}
