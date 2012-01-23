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

public class NotePaper extends JEditorPane
{
	private int						width;
	private final int				height;
	private Font					NOTE_FONT;			// new Font("Helvetica",
	private final int               LINE_SPACE = 26;
	private String text; // Font.BOLD, 14)

	public NotePaper(ScrollPane scrollPane)
	{
		super();
		
		width = scrollPane.getMaximumSize().width;
		height = scrollPane.getMaximumSize().height;
		
		this.setOpaque(false);
		
		NOTE_FONT = new Font("Helvetica", Font.BOLD, 20);// createFont();
		this.setFont(NOTE_FONT);
		
	}
	
	public NotePaper(final ScrollPane scrollPane, String str)
	{
		super();
		
		this.text = str;
		width = scrollPane.getMaximumSize().width;
		height = scrollPane.getMaximumSize().height;
		
		this.setOpaque(false);
		
		NOTE_FONT = new Font("Helvetica", Font.PLAIN, 20);// createFont();
		this.setFont(NOTE_FONT);
		
		this.setText(text);
		this.setMaximumSize(new Dimension(width, height));
		
		this.addKeyListener(new NotepaperListener(scrollPane.getParentWindow(), this));
	}

	@Override
	public void paintComponent(Graphics g)
	{
		BufferedImage notepaper = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = notepaper.createGraphics();
		
		drawBackground((Graphics2D) graphics);
		drawLines((Graphics2D) graphics);

		g.drawImage(notepaper, 0, 0, null);
		
		super.paintComponent(g);
	}
	
	public void drawBackground(Graphics2D g)
	{
		g.setColor(Color.WHITE);

		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
	}

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
