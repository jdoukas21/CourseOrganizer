package courseOrganizer.models.notepad;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class NotePaperKeyListener implements KeyListener
{
	private String words = "";
	private ArrayList<Character> currentWord = new ArrayList<Character>();
	private ArrayList<Character> lastWord = new ArrayList<Character>();
	private NoteVariables variables = new NoteVariables();
	private boolean controlDown = false;
	
	public String getWords()
	{
		return words;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0)
	{
		//words += KeyEvent.getKeyText(arg0.getKeyCode());
		//System.out.println("Words: " + words);
		int code = arg0.getKeyCode();
		char c = arg0.getKeyChar();
		
		if (code == KeyEvent.VK_CONTROL)
		{
			controlDown = true;
		}
		else if (Character.isDefined(c) && code != KeyEvent.VK_BACK_SPACE && code != KeyEvent.VK_DELETE && code != KeyEvent.VK_ESCAPE)
		{
			words += arg0.getKeyChar();
			System.out.println("\tAdding char " + c + " to linkedList.");
			variables.addCharacter(c);
			//System.out.println("Character is whitespace: " + Character.isWhitespace(c));
			
			if (!Character.isWhitespace(c))
			{
				System.out.println("\tAdding char " + c + " to currentWord ArrayList");
				currentWord.add(c);
				lastWord.add(c);
				System.out.println("CurrentWord: " + currentWord);
				System.out.println("LastWord: " + lastWord);
			}
			else
			{
				if (!currentWord.isEmpty())
				{
					System.out.println("\tADDED WORD >" + characterArrayToString(currentWord) + "< to linkedList");
					variables.addWord(characterArrayToString(currentWord));
					currentWord.clear();
				}
			}
			
		}
		else if (code == KeyEvent.VK_BACK_SPACE && words.length() != 0)
		{
			System.out.println("Backspace pressed. Currentword = " + currentWord);
			if (controlDown == true)
			{
				words = "";
			}
			else if (Character.isWhitespace(words.charAt(words.length() - 1)))
			{
				System.out.println("Character is whitespace... supposedly.");
				words = words.substring(0, (words.length() - 1));
				System.out.println("Words is now " + words);
				variables.removeWhiteSpaceCharacter();
			}
			else if (!lastWord.isEmpty())
			{
				words = words.substring(0, (words.length() - 1));
				variables.addWord(characterArrayToString(lastWord));
				variables.removeLastCharacter();
				lastWord.remove(lastWord.size() - 1);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		int code = arg0.getKeyCode();
		
		if (code == KeyEvent.VK_CONTROL)
		{
			controlDown = false;
			System.out.println("Control released.");
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		
	}
	
	public String characterArrayToString(ArrayList<Character> ac)
	{
		String w = "";
		for (int n = 0; n < ac.size(); n++)
		{
			w += ac.get(n);
		}
		return w;
	}
}
