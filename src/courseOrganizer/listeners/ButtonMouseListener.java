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

// Γενική κλάση που καθορίζει τη συμπεριφορά για όλα τα κουμπιά της εφαρμογής.
// Ρυθμίζει κυρίως την εμφάνισή τους, όπως το χρώμα όταν το ποντίκι είναι από πάνω.
public class ButtonMouseListener implements MouseListener
{
	private JButton button;
	private Color backgroundColor;
	private Color fontColor;
	private Color highlightBackground = Color.lightGray;
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
	
        // Ορίζει τις ενέργειες όταν το ποντίκι είναι πάνω από κάθε κουμπί.
	@Override
	public void mouseEntered(MouseEvent e)
	{
		button.setBackground(highlightBackground); // Αλλάζει το χρώμα του κουμπιού.
		button.setForeground(highlightForeground); // Αλλάζει το χρώμα του κειμένου του κουμπιού.
	}
	
        // Ορίζει τις ενέργειες όταν το ποντίκι δεν είναι πάνω από το κουμπί.
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