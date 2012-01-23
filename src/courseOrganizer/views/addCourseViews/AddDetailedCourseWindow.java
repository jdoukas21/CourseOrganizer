package courseOrganizer.views.addCourseViews;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import courseOrganizer.listeners.ADCWOkayButtonListener;
import courseOrganizer.listeners.ButtonMouseListener;
import courseOrganizer.lookAndFeel.Fonts;
import courseOrganizer.models.Course;
import courseOrganizer.models.CourseList;
import courseOrganizer.utilities.Utilities;
import courseOrganizer.views.MainWindow;

public class AddDetailedCourseWindow extends JFrame
{
	//uses two panels with different layouts: one mainPanel has GridLayout with 4 rows, 4 columns. BottomPanel has GridLayout with 2 rows, 1 column. 
	
	private CourseList courseList;
	private Font BOLD_FONT = new Font("Times New Roman", Font.BOLD, 14);
	private boolean editMode = false;
	private JPanel grandPanel;
	
	private String courseName = "Calculus 1";
	private String number = "1410";
	private String instructor = "Dr. Morrison";
	private String fieldOfStudy = "Mathematics";
	private String classroom = "Central 157";
	private String semester = "Fall 2014";
	
	private Object[] inputObjects = new Object[9]; 
	
	
	public AddDetailedCourseWindow(MainWindow container, CourseList cl, boolean editMode)
	{
		super("Add Course");
		courseList = cl;
		
		this.editMode = editMode;
		if (editMode)
			super.setTitle("Edit Course");
		
		if (!editMode)
			addComponents();
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		pack();
		this.setResizable(false);
		this.setLocationRelativeTo(container);
		if (!editMode)
		{
			this.setVisible(true);
		}
	}
	
	public void setDefaultCourseAndShow(String courseName) //this method will set the fields to default values if a user is using this window
 	{													  //to edit a course. 
		Course course = courseList.get(courseName);
		this.courseName = courseName;
		number = course.getNumber();
		instructor = course.getInstructor();
		fieldOfStudy = course.getField();
		classroom = course.getClassroom();
		semester = course.getSemester();
		
		this.addComponents();
		pack();
		this.setVisible(true);
	}
	
	public void addComponents()
	{
		System.out.println("AddDetailedCourseWindow: Adding components");

		grandPanel = new JPanel(new BorderLayout());
		
		
		JLabel instructions = new JLabel("<HTML>You may leave any of the fields blank except for the title field.</HTML>");
		instructions.setFont(BOLD_FONT);
		instructions.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		grandPanel.add(instructions, BorderLayout.NORTH);
		
		grandPanel.add(mainPanel(), BorderLayout.CENTER);
		grandPanel.add(bottomPanel(), BorderLayout.SOUTH);
		
		this.add(grandPanel);
		this.validate();
	}
	
	private JPanel mainPanel()
	{
		
		JPanel pane = new JPanel(new GridLayout(4, 4));
		
		//ROW 1
		JLabel title = new JLabel("Title:");
		title.setFont(Fonts.DEFAULT_FONT);
		
		JTextField titleField = new JTextField(courseName);
		titleField.setFont(Fonts.ITALIC_FONT);
		Utilities.prepareTextField(titleField, courseName);
		inputObjects[0] = titleField;
		
		JLabel startsAt = new JLabel("Starts at:");
		startsAt.setFont(Fonts.DEFAULT_FONT);
		
		Date date = new Date();
		
		SpinnerDateModel sdm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		
		JSpinner beginTimeSpinner = new JSpinner(sdm);
		JSpinner.DateEditor de = new JSpinner.DateEditor(beginTimeSpinner, "hh:mm a");
		beginTimeSpinner.setEditor(de);
		beginTimeSpinner.setFont(Fonts.DEFAULT_FONT);
		inputObjects[1] = beginTimeSpinner;
		
		//ROW 2
		pane.add(title); pane.add(titleField); pane.add(startsAt); pane.add(beginTimeSpinner);
		
		JLabel numberLabel = new JLabel("Number:");
		numberLabel.setFont(Fonts.DEFAULT_FONT);
		
		JTextField numberField = new JTextField(number);
		numberField.setFont(Fonts.ITALIC_FONT);
		Utilities.prepareTextField(numberField, number);
		inputObjects[2] = numberField;
		
		JLabel endsAt = new JLabel("Ends at:");
		endsAt.setFont(Fonts.DEFAULT_FONT);
		
		sdm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		
		JSpinner endTimeSpinner = new JSpinner(sdm);
		de = new JSpinner.DateEditor(endTimeSpinner, "hh:mm a");
		
		endTimeSpinner.setEditor(de);
		endTimeSpinner.setFont(Fonts.DEFAULT_FONT);
		inputObjects[3] = endTimeSpinner;
		
		//ROW 3
		pane.add(numberLabel); pane.add(numberField); pane.add(endsAt); pane.add(endTimeSpinner);
		
		JLabel instructorLabel = new JLabel("Instructor:");
		instructorLabel.setFont(Fonts.DEFAULT_FONT);
		
		JTextField instructorField = new JTextField(instructor);
		instructorField.setFont(Fonts.ITALIC_FONT);
		Utilities.prepareTextField(instructorField, instructor);
		inputObjects[4] = instructorField;
		
		JLabel fieldOfStudyLabel = new JLabel("Field of Study:");
		fieldOfStudyLabel.setFont(Fonts.DEFAULT_FONT);
		
		JTextField FOSField = new JTextField(fieldOfStudy);
		FOSField.setFont(Fonts.ITALIC_FONT);
		Utilities.prepareTextField(FOSField, fieldOfStudy);
		inputObjects[5] = FOSField;
		
		//ROW 4
		pane.add(instructorLabel); pane.add(instructorField); pane.add(fieldOfStudyLabel); pane.add(FOSField);
		
		JLabel classroomLabel = new JLabel("Classroom:");
		classroomLabel.setFont(Fonts.DEFAULT_FONT);
		
		JTextField classroomField = new JTextField(classroom);
		classroomField.setFont(Fonts.ITALIC_FONT);
		Utilities.prepareTextField(classroomField, classroom);
		inputObjects[6] = classroomField; 
		
		JLabel semesterLabel = new JLabel("Semester:");
		semesterLabel.setFont(Fonts.DEFAULT_FONT);
		
		JTextField semesterField = new JTextField(semester);
		semesterField.setFont(Fonts.ITALIC_FONT);
		Utilities.prepareTextField(semesterField, semester);
		inputObjects[7] = semesterField;
		
		
		pane.add(classroomLabel); pane.add(classroomField); pane.add(semesterLabel); pane.add(semesterField);
		
		pane.setBorder(BorderFactory.createEtchedBorder());
		
		return pane;
		
	}
	
	private JPanel bottomPanel()
	{
		JPanel labelPanel = new JPanel();
		
		JLabel dayInstructions = new JLabel("Select the day(s) during which this class takes place:");
		dayInstructions.setFont(BOLD_FONT);
		dayInstructions.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		labelPanel.add(dayInstructions);
		
		JPanel checkBoxPanel = new JPanel(new GridLayout(2, 1));
		
		final JCheckBox[] checkBoxArray = new JCheckBox[8];
		
		JCheckBox monday = new JCheckBox("Monday");
		monday.setFont(Fonts.DEFAULT_FONT);
		checkBoxArray[0] = monday;
		JCheckBox tuesday = new JCheckBox("Tuesday");
		tuesday.setFont(Fonts.DEFAULT_FONT);
		checkBoxArray[1] = tuesday;
		JCheckBox wednesday = new JCheckBox("Wednesday");
		wednesday.setFont(Fonts.DEFAULT_FONT);
		checkBoxArray[2] = wednesday;
		JCheckBox thursday = new JCheckBox("Thursday");
		thursday.setFont(Fonts.DEFAULT_FONT);
		checkBoxArray[3] = thursday;
		JCheckBox friday = new JCheckBox("Friday");
		friday.setFont(Fonts.DEFAULT_FONT);
		checkBoxArray[4] = friday;
		JCheckBox saturday = new JCheckBox("Saturday");
		saturday.setFont(Fonts.DEFAULT_FONT);
		checkBoxArray[5] = saturday;
		JCheckBox sunday = new JCheckBox("Sunday");
		sunday.setFont(Fonts.DEFAULT_FONT);
		checkBoxArray[6] = sunday;
		JCheckBox all = new JCheckBox("All");
		all.setFont(Fonts.DEFAULT_FONT);
		checkBoxArray[7] = all;
		all.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					for (int n = 0; n < checkBoxArray.length; n++)
					{
						checkBoxArray[n].setSelected(true);
					}
				}
				else
				{
					for (int n = 0; n < checkBoxArray.length; n++)
					{
						checkBoxArray[n].setSelected(false);
					}
				}
			}
		});
		
		inputObjects[8] = checkBoxArray;
		
		checkBoxPanel.add(monday);
		checkBoxPanel.add(tuesday);
		checkBoxPanel.add(wednesday);
		checkBoxPanel.add(thursday);
		checkBoxPanel.add(friday);
		checkBoxPanel.add(saturday);
		checkBoxPanel.add(sunday);
		checkBoxPanel.add(all);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,2));
		
		JButton okayButton = new JButton("OK");
		okayButton.setFont(Fonts.DEFAULT_FONT);
		okayButton.addMouseListener(new ButtonMouseListener(okayButton));
		okayButton.addActionListener(new ADCWOkayButtonListener(this, inputObjects, courseList));
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(Fonts.DEFAULT_FONT);
		Utilities.prepareDisposeButton(cancelButton, this);
		
		buttonPanel.add(okayButton);
		buttonPanel.add(cancelButton);
		
		JPanel container = new JPanel(new BorderLayout());
		container.add(labelPanel, BorderLayout.NORTH);
		container.add(checkBoxPanel, BorderLayout.CENTER);
		container.add(buttonPanel, BorderLayout.SOUTH);
		
		container.setBorder(BorderFactory.createEtchedBorder());
		
		return container;
	}
	
}
