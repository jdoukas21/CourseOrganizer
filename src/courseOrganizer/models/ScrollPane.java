package courseOrganizer.models;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

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
        private NotePaper notepaper;
        
	private String displayType = "courses";
	private String textPaneText = "";
	
	
        public ScrollPane(){}
        
	public ScrollPane(CourseList cl)
	{
		courseList = cl;
	}
	
	public ScrollPane(MainWindow parent, CourseList cl, String type)
	{
		courseList = cl;
		this.parent = parent;
		this.displayType = type;  //Σε αυτή τη μεταβήτή αποθηκεύεται το mode
		
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
                        //H displayNotes() καλείται από τη MainWindow
		}

		this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		this.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
		
	}
        
	private void displayNotebook()
	{
		// TODO Auto-generated method stub
		this.setViewportView(new Notebook());
	}

        // Μέθοδος που εμφανίζει το mode "Courses" έπειτα από επιλογή του χρήστη από τη dropdown λίστα.
	private void displayCourses()
	{
		JPanel coursePane = new JPanel();
		
                //Αν δεν υπάρχουν μαθήματα προς εμφάνιση. H Μέθοδος isEmpty() βρίσκεται στην κλάση CourseList.
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
                                if (n == 0 || n == 1){
                                    coursePane.setLayout(new GridLayout());
                                }
                                else{
                                    coursePane.setLayout(new GridLayout(0,3,2,2)); // Ορίζει τη διάταξη σε 0 γραμμές, 3 στήλες και με 2 pixel κενό ανάμεσα.
                                }
				JButton button = new JButton();
				button.setFont(Fonts.DEFAULT_FONT);
                                /* Καλείται η get(n) της κλάσης CourseList η οποία επιστρέφει το αντικείμενο τύπου
                                 * Course της ν-οστής θέσης. Έπειτά για το συγκεκριμένο αντικείμενο Course καλείται
                                 * η isSimple() της κλάσης Course η οποία επιστρέφει αν συγκεκριμένο αντικείμενο είναι
                                 * Simple ή Detailed. */
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
                                button.setPreferredSize(new Dimension(250,250)); //ρυθμίζει το μέγεθος του κουμπιού
				button.setBorder(BorderFactory.createEtchedBorder());
				button.addMouseListener(new CourseButtonMouseListener(button, courseList.get(n).getName(), Color.WHITE, Color.BLACK, parent, courseList));
				coursePane.add(button);
			}
		}
		
		this.setViewportView(coursePane);
	}
	
        // Μέθοδος που εμφανίζει το mode "Assignments" έπειτα από επιλογή του χρήστη από τη dropdown λίστα.
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
	
        // Μέθοδος που σχετίζεται με το mode "Notepaper".
	public void displayNotes(String saveString/*, Font noteFont, Color noteColor*/)
	{
		notepaper = new NotePaper(this, saveString);
		
                // Το component notepaper που είναι JEditorPane θα εμφανιστεί με scroll. Πρέπει να μπει 
                // εφόσον δε δηλώθηκε στον constructor.
                this.setViewportView(notepaper); 
	}
	
	public void setDisplayType(String displayType)
	{
		this.displayType = displayType;
	}
	
	public MainWindow getParentWindow()
	{
		return parent;
	}
        
        public ScrollPane getScrollPane()
        {
                return this;
        }
		
}
