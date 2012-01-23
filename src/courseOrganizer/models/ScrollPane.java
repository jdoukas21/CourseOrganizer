package courseOrganizer.models;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import courseOrganizer.listeners.ButtonMouseListener;
import courseOrganizer.listeners.CourseButtonMouseListener;
import courseOrganizer.lookAndFeel.Colors;
import courseOrganizer.lookAndFeel.Fonts;
import courseOrganizer.models.notebook.Notebook;
import courseOrganizer.models.notepad.NotePaper;
import courseOrganizer.views.MainWindow;

public class ScrollPane extends JScrollPane
{
	private CourseList courseList;
	private MainWindow parent;
	private String displayType = "courses";
	private String textPaneText = "";
	private NotePaper notePaper;
	
	public ScrollPane(CourseList cl)
	{
		courseList = cl;
	}
	
	public ScrollPane(MainWindow parent, CourseList cl, String type)
	{
		courseList = cl;
		this.parent = parent;
		this.displayType = type;
		
		if (displayType.equals("Courses"))
		{
			displayCourses();
		}
		else if (displayType.equals("Assignments"))
		{
			displayAssignments();
		}
		else if (displayType.equals("Notebook"))
		{
			displayNotebook();
		}
		else if (displayType.equals("Notepaper"))
		{
			//JLabel courseName = new JLabel(cl.get());
			/*TextPane tp = new TextPane("Notes", courseList);
			tp.setText(textPaneText);
			textPaneText = tp.getText();
			this.setViewportView(tp);*/
			//displayNotes();
		}

		this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		this.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
		
	}
	
	private void displayNotebook()
	{
		// TODO Auto-generated method stub
		this.setViewportView(new Notebook());
	}

	private void displayCourses()
	{
		JPanel coursePane = new JPanel(new GridLayout(courseList.size(), 1));
		
		if (courseList.isEmpty())
		{
			coursePane.setLayout(new BorderLayout());
			JLabel instructions = new JLabel("<html><div style =\"text-align: center;\">No courses found." +
											"<BR>To add a course, press the green plus button to the left.</div style></html>");
			instructions.setFont(Fonts.ITALIC_FONT);
			coursePane.add(instructions, BorderLayout.CENTER);
			instructions.setHorizontalAlignment(JLabel.CENTER);
		}
		else
		{
			for (int n = 0; n < courseList.size(); n ++)
			{
				JButton button = new JButton();
				button.setFont(Fonts.DEFAULT_FONT);
				if (courseList.get(n).isSimple())
				{
					button.setText("<html><font size=\"6\"<B>" + courseList.get(n).getName() + "</B></font>");
				}
				else
				{
					button.setText("<html><font size=\"6\"><B>" + courseList.get(n).getName() + ":</B></font><BR>" +
							"<B><I>Number</I>: </B>" + courseList.get(n).getNumber() + "<BR>" +
							"<B><I>Instructor</I>: </B>" + courseList.get(n).getInstructor() + "<BR>" +
							"<B><I>Classroom</I>: </B>" + courseList.get(n).getClassroom() + "<BR>" + 
							"<B><I>Time</I>: </B>" + courseList.get(n).getTime() + "<BR>" +
							"<B><I>Field of Study</I>: </B>" + courseList.get(n).getField() + "<BR>" +
							"<B><I>Days:</I> </B>" + courseList.get(n).getDays() + "<BR>" + "</html>"
							);
				}
				button.setBackground(Colors.LIGHT_GRAY);
				button.setForeground(Color.BLACK);
				button.setBorder(BorderFactory.createEtchedBorder());
				button.addMouseListener(new CourseButtonMouseListener(button, courseList.get(n).getName(), Color.WHITE, Color.BLACK, parent, courseList));
				coursePane.add(button);
			}
		}
		
		this.setViewportView(coursePane);
	}
	
	private void displayAssignments()
	{
		JPanel assignmentPane = new JPanel(new GridLayout(courseList.size(), 1));
		
		if (courseList.getNumberOfAssignments("all") == 0)
		{
			assignmentPane.setLayout(new BorderLayout());
			JLabel instructions = new JLabel("<html><div style =\"text-align: center;\">No assignments found." +
											"<BR>To add an assignment, press the green plus button to the left.</div style></html>");
			instructions.setFont(Fonts.ITALIC_FONT);
			assignmentPane.add(instructions, BorderLayout.CENTER);
			instructions.setHorizontalAlignment(JLabel.CENTER);
		}
		else
		{
			for (int n = 0; n < courseList.size(); n ++)
			{
				JLabel label = new JLabel(courseList.get(n).getName());
				label.setFont(Fonts.DEFAULT_FONT);
				JButton button = new JButton();
				button.setFont(Fonts.DEFAULT_FONT);
				button.setText("<html><font size=\"6\"><B>" + courseList.get(n).getAssignment(n).getTitle() + ":</B></font><BR>" +
						"<B><I>Number</I>: </B>" + courseList.get(n).getNumber() + "<BR>" +
						"<B><I>Instructor</I>: </B>" + courseList.get(n).getInstructor() + "<BR>" +
						"<B><I>Classroom</I>: </B>" + courseList.get(n).getClassroom() + "<BR>" + 
						"<B><I>Time</I>: </B>" + courseList.get(n).getTime() + "<BR>" +
						"<B><I>Field of Study</I>: </B>" + courseList.get(n).getField() + "<BR></html>"
						);
				button.setBackground(Colors.LIGHT_GRAY);
				button.setForeground(Color.BLACK);
				button.setBorder(BorderFactory.createEtchedBorder());
				button.addMouseListener(new ButtonMouseListener(button, Color.WHITE, Color.BLACK));
				assignmentPane.add(button);
			}
		}
		
		this.setViewportView(assignmentPane);
	}
	
	public void displayNotes(String saveString)
	{
		notePaper = new NotePaper(this, saveString);
		//this.setViewportView(new NotePaper(this));
		this.setViewportView(notePaper);
	}
	
	public void setDisplayType(String displayType)
	{
		this.displayType = displayType;
	}
	
	public MainWindow getParentWindow()
	{
		return parent;
	}
	
	
}
