package courseOrganizer.utilities;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import courseOrganizer.listeners.ButtonMouseListener;
import courseOrganizer.listeners.DisposeButtonListener;
import courseOrganizer.listeners.TextFieldFocusListener;
import courseOrganizer.listeners.TextFieldKeyListener;
import courseOrganizer.listeners.TextFieldMouseListener;
import courseOrganizer.lookAndFeel.Fonts;

public class Utilities
{
	public static void prepareTextField(JTextField field, String fieldText) 
	//adds the three necessary listeners to the textfield and sets the font 
	//to the text field default, which is Fonts.ITALIC_FONT
	{
		field.setFont(Fonts.ITALIC_FONT);
		field.addMouseListener(new TextFieldMouseListener(field, fieldText));
		field.addFocusListener(new TextFieldFocusListener(field, fieldText));
		field.addKeyListener(new TextFieldKeyListener(field, fieldText));
	}
	
	public static void prepareDisposeButton(JButton button, JFrame frame)
	{
		button.setFont(Fonts.DEFAULT_FONT);
		button.addMouseListener(new ButtonMouseListener(button));
		button.addActionListener(new DisposeButtonListener(frame));
	}
	
	public static void prepareRegularButton(JButton button)
	{
		button.setFont(Fonts.DEFAULT_FONT);
		button.addMouseListener(new ButtonMouseListener(button));
	}
}
