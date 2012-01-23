package courseOrganizer.views;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ScheduleView extends JFrame
{
	private JTable table; 
	
	public ScheduleView()
	{
		createTable();
		this.add(createScrollPane());
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void createTable()
	{
		String[] columnNames = {"Class", //declare columnNames
                        "Day(s)",
                        "Time",
                        "Classroom"};
								
		Object[][] data = //declare column data.. in your program, you will want this to work automatically
		{
	    {"Computer Organization 1", "Mon/Wed",
	     "12:00 - 1:50", "SC 1011"},
	    {"Calculus 2", "Tue/Thu",
	     "12:00 - 1:50", "NC 3112"},
	    {"Physics Lab", "Thu",
	     "4:00 - 5:50", "NC 3606"},
	    {"General Physics 1", "Tue/Thu",
	     "6:00 - 7:50", "NC 1608"}
		};
		
		table = new JTable(data, columnNames);
	}
	
	public JScrollPane createScrollPane() //for the JScrollPane that holds the JTable
	{
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true); //self-explanatory...
		return scrollPane;
	}
}
