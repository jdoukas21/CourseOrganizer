package courseOrganizer.models;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class TextPane extends JTextPane
{
	private Font DEFAULT_FONT = new Font("Times New Roman", Font.PLAIN, 14);
	private CourseList cl;
	
	public TextPane()
	{
		this.setSize(new Dimension(550, 800));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		this.setEditable(false);
	}
	
	public TextPane(String displayType, CourseList courseList)
	{
		cl = courseList;
		
		StyledDocument sdoc = this.getStyledDocument();
		
		final Style BOLD = this.addStyle("Bold", null);
		StyleConstants.setBold(BOLD, true);
		
		this.setFont(DEFAULT_FONT);
		
		if (displayType.equals("Courses"))
		{
			this.setEditable(false);
			
			this.setCaretPosition(sdoc.getLength());
			
			try
			{
				for (int n = 0; n < cl.size(); n ++)
				{				
					sdoc.insertString(sdoc.getLength(), ((n+1) + ". " + cl.get(n).getName() + ":"), BOLD);
					sdoc.insertString(sdoc.getLength(), "\n\tNumber: ", BOLD);
					sdoc.insertString(sdoc.getLength(), cl.get(n).getNumber(), null);
					sdoc.insertString(sdoc.getLength(), "\n\tInstructor: ", BOLD);
					sdoc.insertString(sdoc.getLength(), cl.get(n).getInstructor(), null);
					sdoc.insertString(sdoc.getLength(), "\n\tClassroom: ", BOLD);
					sdoc.insertString(sdoc.getLength(), cl.get(n).getClassroom(), null);
					sdoc.insertString(sdoc.getLength(), "\n\tTime: ", BOLD);
					sdoc.insertString(sdoc.getLength(), cl.get(n).getTime(), null);
					sdoc.insertString(sdoc.getLength(), "\n\tField of Study: ", BOLD);
					sdoc.insertString(sdoc.getLength(), cl.get(n).getField() + "\n\n", null);
				}
				
			}
			catch (BadLocationException e)
			{
				System.err.print("Ah shit.");
				e.printStackTrace();
			}
			//stringArray = courseList.toString(true).split(" ");
			///this.setText(courseList.toString(true));
		}
		
		/*else if (displayType.equals("assignments"))
		{
			
			/*this.setEditable(false);
			String str = "";
			
			ArrayList<String> stringArray = new ArrayList<String>();
			stringArray = this.splitByWords(cal.get(2).getAssignment(0).getDescription(), 4);
			System.out.println(stringArray.size() + " " + stringArray.get(0));
			
			
			for (int n = 0; n < stringArray.size(); n++)
			{
				try
				{
					sdoc.insertString(sdoc.getLength(), stringArray.get(n) + "\n", null) ;
				}
				catch (BadLocationException e)
				{
					// TODO Auto-generated catch block
					System.err.print("Oh fuck.");
					e.printStackTrace();
				}
			}
			
			//this.setText(str);
			//sdoc.setCharacterAttributes(0, 12, BOLD, true);
		}*/
		
		else if (displayType.equals("Notes"))
		{
			
		}

		this.setSize(new Dimension(550, 800));
		//this.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
	}
	
	public ArrayList<String> splitByWords(String toSplit, int numberOfWords)
	{
		int wordCount = 0;
		int letterCount = 0;
		int beginIndex = 0;
		int endIndex = 0;
		int lastWordIndex = 0;
		int MAX_WORDCOUNT = numberOfWords;
		ArrayList<String> arrayList = new ArrayList<String>();
		
		/*System.out.println("Original string: " + toSplit);
		System.out.println(toSplit.length());*/
		
		for (int n = 0; n + 1< toSplit.length(); n ++)
		{
			if (Character.isWhitespace(toSplit.charAt(n)) && !Character.isWhitespace(toSplit.charAt(n+1)))
			{
				if (wordCount == MAX_WORDCOUNT)
				{
					beginIndex = n + 1;
					wordCount = 0;
				}
				wordCount++;
			}
			else if (!Character.isWhitespace(toSplit.charAt(n)) && !Character.isWhitespace(toSplit.charAt(n+1)))
			{
				letterCount++;
			}
			else if (!Character.isWhitespace(toSplit.charAt(n)) && Character.isWhitespace(toSplit.charAt(n+1)))
			{
				letterCount++; 
				lastWordIndex++;
				//wordCount++;
				
				if (letterCount >= 9 && wordCount == 2)
				{
					arrayList.add(toSplit.substring(endIndex, beginIndex));
				}
				
				endIndex = n + 1;
				if (wordCount >= MAX_WORDCOUNT)
				{
					arrayList.add(toSplit.substring(beginIndex, endIndex));
				}
				if (n + 1 == toSplit.length() - 1)
				{
					arrayList.add(toSplit.substring(beginIndex, endIndex));
				}
				letterCount = 0;
			}
			
			
		}
		return arrayList;
	}
	 
}
