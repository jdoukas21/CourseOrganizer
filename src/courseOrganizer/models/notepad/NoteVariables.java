package courseOrganizer.models.notepad;

import java.util.LinkedList;

public class NoteVariables
{
	private LinkedList<String> characters;
	private LinkedList<String> words;
	
	public NoteVariables()
	{
		characters = new LinkedList<String>();
		words = new LinkedList<String>();
	}
	
	public void addCharacter(char c)
	{
		characters.add(Character.toString(c));
		//System.out.println("NoteVariables characters = " + characters);
	}
	
	public void removeLastCharacter() //for this to work, outside app must call addWord first
	{
		//BROKEN
		System.out.println("Characters.last is >" + characters.peekLast() + "<");
		if (!words.isEmpty() && !Character.isWhitespace(characters.removeLast().charAt(0)))
		{
			String temp = words.removeLast();
			System.out.println("Temp word variable is >" + temp + "<");
			temp = temp.substring(0, temp.length()-1);
			System.out.println("Now temp is " + temp + " and it is going to be added to words.");
			words.add(temp);
			System.out.println("NoteVariables words = " + words);
		}
	}
	
	public void removeCharacterAt(int pos)
	{
		characters.remove(pos);
	}
	
	public void addWord(String w)
	{
		words.add(w);
		System.out.println("NoteVariables WORDS = " + words);
	}
	
	public void removeWord(String w)
	{
		
	}

	public void removeWhiteSpaceCharacter()
	{
		if (Character.isWhitespace(characters.peekLast().charAt(0)))
		{
			characters.removeLast();
		}	
	}
	
}
