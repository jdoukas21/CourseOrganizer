package courseOrganizer.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import courseOrganizer.CourseOrganizer;
import courseOrganizer.listeners.MainWindowListener;
import courseOrganizer.lookAndFeel.Fonts;
import courseOrganizer.models.CourseList;
import courseOrganizer.models.LeftToolBar;
import courseOrganizer.models.MenuBar;
import courseOrganizer.models.ScrollPane;
import courseOrganizer.views.assignmentWindows.AddAssignmentWindow;
import courseOrganizer.views.assignmentWindows.DescriptionWindow;

public class MainWindow extends JFrame
{
	//DEFAULT COLOR IS PALE GREEN, WHICH IS RGB 152, 251, 152
	private CourseList courseList;
	private JPanel bigPane;
	//private ScrollPane scrollPanel;
	private CourseOrganizer co = new CourseOrganizer();
	private String view; //what the current view or mode is -- can be "Courses", "Assignments", "Notes", or "Notepad"
	
	private String saveString = "";
	
	public MainWindow(CourseList cl)
	{
		super("Course Organizer");
		//COMPONENTS (in order of addition): topPane (MenuBar, view ComboBox), bigPane (left toolbar, main view)
		
		this.getContentPane().setLayout(new BorderLayout());
		
		courseList = cl;
		view = "Courses";
		
		this.setSize(new Dimension(600, 800));
		
		draw(true);
		
		MainWindowListener mwl = new MainWindowListener(this);
		this.addComponentListener(mwl);
		this.addWindowListener(mwl);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public JPanel topPanel(String selectedItem)
	{
		JPanel topPane = new JPanel();
		topPane.setLayout(new BoxLayout(topPane, BoxLayout.Y_AXIS));
		
		MenuBar mb = new MenuBar(this);
		mb.setMaximumSize(new Dimension(this.getMaximumSize().width, 50));
		
		
		final JPanel panel = new JPanel();
		
		JLabel views = new JLabel("<html><B>View: </B></html>");
		views.setFont(Fonts.DEFAULT_FONT);
		//view.setMaximumSize(new Dimension(50, 20));
		
		String[] viewTypes = {"Courses", "Assignments", "Notepaper", "Notebook"};
		
		final JComboBox<String> viewComboBox = new JComboBox<String>(viewTypes);
		viewComboBox.setFont(Fonts.DEFAULT_FONT);
		viewComboBox.setSelectedItem(selectedItem);
		viewComboBox.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				switch (viewComboBox.getSelectedIndex())
				{
					case 0:
						view = "Courses";
						break;
					case 1:
						view = "Assignments";
						break;
					case 2:
						view = "Notepaper";
						break;
					case 3: 
						view = "Notebook";
						break;
					default:
						view = "Courses";
				}
				draw(false);
			}
		});
		//viewComboBox.setMaximumSize(new Dimension(50,20));
		
		panel.add(views);
		panel.add(viewComboBox);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
		
		panel.setMaximumSize(new Dimension(this.getMaximumSize().width, 50));
		topPane.add(mb);
		topPane.add(panel);
		
		return topPane;
	}
	
	public void draw(boolean beginning)
	{	
		this.getContentPane().removeAll();
		
		bigPane = new JPanel();
		bigPane.setLayout(new BoxLayout(bigPane, BoxLayout.X_AXIS));
		
		LeftToolBar leftToolBar = new LeftToolBar(this, courseList);
		leftToolBar.setViewMode(view);
		leftToolBar.setMaximumSize(new Dimension((this.getSize().width * 9) / 100, this.getSize().height));
		leftToolBar.resizeComponents();
		
		bigPane.add(leftToolBar);
		
		ScrollPane scrollPane = new ScrollPane(this, courseList, "courses"); //default state = "courses"
	
		
		if (view.equals("Courses"))
		{
			scrollPane = new ScrollPane(this, courseList, "Courses");
		}
		else if (view.equals("Assignments"))
		{
			scrollPane = new ScrollPane(this, courseList, "Assignments");
		}
		else if (view.equals("Notebook"))
		{
			//*notebook view will use a JList to display a list of notes (if existent)
			scrollPane = new ScrollPane(this, courseList, "Notebook");
		}
		
		if (view.equals("Notepaper")) //this case has to be here in order for the notepaper sizing to work properly
		{	
			scrollPane.setMaximumSize(new Dimension(this.getSize().width - leftToolBar.getMaximumSize().width, this.getSize().height));
			scrollPane.displayNotes(saveString);
		}
		else
		{
			scrollPane.setMaximumSize(new Dimension(this.getSize().width - leftToolBar.getMaximumSize().width, this.getSize().height));
		}
		
		bigPane.add(scrollPane);
		//scrollPanel = scrollPane;
		
		
		this.getContentPane().add(topPanel(view), BorderLayout.NORTH);
		this.getContentPane().add(bigPane, BorderLayout.CENTER);
		
		
		
		if (!beginning)
		{
			this.validate();
		}
	}
	
	public void setSaveString(String string)
	{
		saveString = string;
	}

	public void changeView(String view)
	{
		this.view = view;
		draw(false);
	}
}
