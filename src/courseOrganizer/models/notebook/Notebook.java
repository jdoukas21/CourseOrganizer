package courseOrganizer.models.notebook;

import javax.swing.JList;

import courseOrganizer.lookAndFeel.Fonts;

public class Notebook extends JList<String>
{
	public Notebook() //will be Notebook(String[] data, and if data is empty, then display message telling to add note, press green button... 
					 //that should be done in the scrollPane class, probably. 
	{
		String[] testData = {"Jamie", "Is", "Awesome"};
		this.setListData(testData);
		this.setFont(Fonts.DEFAULT_FONT);
	}
	
	
}
