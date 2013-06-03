package courseOrganizer.models.notepad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JEditorPane;

import courseOrganizer.listeners.NotepaperListener;
import courseOrganizer.models.ScrollPane;
import courseOrganizer.views.MainWindow;

// Η κλάση NotePaper υλοποιεί την κλάση JEditorPane οπότε όλες οι μέθοδοι προέρχονται από την JEditorPane.
public class NotePaper extends JEditorPane
{
        private ScrollPane scrollPane;
        private EditorPane editorpane;
        private static NotePaper notepaper;
        private MainWindow mainWindow;
    
	private int width;
	private final int height; //η μεταβλητή είναι σταθερή (final) και εφόσον αρχικοποηθεί δε μπορεί να αλλάξει.
	private Font NOTE_FONT;			
	private int LINE_SPACE = 23; // Ορίζει το μέγεθος μεταξύ των εικονικών γραμμών. Το 23 είανι το default και αντιστοιχεί στη γραμματοσειρά 20.
	private String text; // Font.BOLD, 14)
        
        
	public NotePaper(ScrollPane scrollPane)
	{
		super();
		
		width = scrollPane.getMaximumSize().width;
		height = scrollPane.getMaximumSize().height;
		
		this.setOpaque(false);
		
		NOTE_FONT = new Font("Helvetica", Font.BOLD, 20);// Το default font
		this.setFont(NOTE_FONT);
                		
	}
	
        /* Αυτός ο constructor καλείται όταν το αντικείμενο που δημιουργείται έχει ως όρισμα και String. 
        Αυτός o constructor καλείται από τη μέθοδο displayNotes() της ScrollPane. */
	public NotePaper(final ScrollPane sp, String str)
	{
                super();
                                             
                scrollPane = sp;
		this.text = str;
                
                mainWindow = scrollPane.getParentWindow();
                mainWindow.setNotePaper(this);
                
                editorpane = new EditorPane(mainWindow, this);
                mainWindow.setEditorPane(editorpane);
                                
		width = scrollPane.getMaximumSize().width;
		height = scrollPane.getMaximumSize().height;
                
                // Ορίζει το Font και το χρώμα του κειμένου
                this.setFont(mainWindow.getNotepaperFont());
                this.setForeground(mainWindow.getNotepaperColor());
                		
                // Μέθοδος που σχετίζεται με την εμφάνιση.
		this.setOpaque(false);
		                                
		this.setText(text); // Μέθοδος της JEditorPane
		this.setMaximumSize(new Dimension(width, height));
                		
                // Προσθέτει στο JEditor ακροατή συμβάντων για όταν πατιέται ένα κουμπί (keyListener)
                // και για όταν γίνεται focus το παράθυρο (focusListener)
		this.addKeyListener(new NotepaperListener(scrollPane.getParentWindow(), this));                
	}
              
        // Ορίζει την εμφάνιση του JEditorPane.
	@Override 
	public void paintComponent(Graphics g)
	{
		BufferedImage notepaper = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = notepaper.createGraphics();
		
		drawBackground((Graphics2D) graphics); // Καλεί τη drawBackground() για να δημιουργήσει το χρώμα φόντο.
		drawLines((Graphics2D) graphics); // Καλεί τη drawLines() για να δημιουργήσει τις γραμμές.

		g.drawImage(notepaper, 0, 0, null);
		
		super.paintComponent(g);
	}
	
        
        // Μέθοδος η οποία ορίζει το χρώμα φόντο για το notepaper.
	public void drawBackground(Graphics2D g)
	{
		g.setColor(Color.WHITE);

		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
	}

        // Μέθοδος η οποία δημιουργεί τις εικονικές γραμμές
	public void drawLines(Graphics2D graphics)
	{
		graphics.setColor(Color.BLUE);

		//future versions should start n at 80
		for (int n = 0; n <= (this.getSize().height); n += LINE_SPACE)
		{
			graphics.drawLine(0, n, this.getSize().width, n);
                }

		graphics.setColor(Color.RED);

		// double redLinePos = this.getWidth() * .1125;
		//future versions will have redline and advanced caret position, just like regular notepaper
		//int redLineX = 80;

		//graphics.drawLine(redLineX, 0, redLineX, this.getHeight());
	}

        // Δε νομίζω να χρησιμοποιείται κάπου.
	public void drawCursor(Graphics2D g)
	{
		g.setColor(Color.BLACK);
	}

	/*
	 * public Font createFont() {
	 * 
	 * try { Font f = java.awt.Font.createFont(Font.TRUETYPE_FONT, new
	 * File("Note this.ttf")); return f.deriveFont(15f); } catch
	 * (FontFormatException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } return null;
	 * 
	 * }
	 */
        
}


