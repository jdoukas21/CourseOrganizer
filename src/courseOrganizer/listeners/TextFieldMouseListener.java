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
	
        // Εκτελείται όταν το ποντίκι πατιέται πάνω από ένα TextField.
	@Override
	public void mouseClicked(MouseEvent arg0)
	{
                field.select(0, 1000); // Επιλέγει το κείμενο του TextField από την αρχή (0) εως το τέλος (1000 - μεγάλος αριθμός).
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
                /* To font της default τιμής είναι italic. Μόλις το ποντίκι μπει πάνω από το textfield
                 * τότε η default τιμή σβήνει και ορίζει το font σε default (από την κλάση Fonts το default
                 * είναι font.plain). Έτσι όταν ο χρήστης συμπληρώσει μία τιμή στο πεδίο και ξαναβάλει το
                 * ποντίκι πάνω από το textfield, η νέα τιμή δε θα σβηστεί.*/
		if (field.getFont() == Fonts.ITALIC_FONT)
		{
			field.setText(null);
			field.setFont(Fonts.DEFAULT_FONT);
		}
	}

        // Όταν το ποντίκι βγεί από το textfield η μέθοδος αυτή ξαναβάζει την default τιμή.
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
