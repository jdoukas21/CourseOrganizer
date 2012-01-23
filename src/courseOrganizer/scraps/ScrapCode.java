package courseOrganizer.scraps;

import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

//this class is purely for garbage code that may be used later
public class ScrapCode
{
/*
 * 	
	protected JPanel buildMainPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,4));
		
		panel.add(buildLeftInnerPanel());
		panel.add(buildLeftMiddleInnerPanel());
		panel.add(buildRightMiddleInnerPanel());
		panel.add(buildRightInnerPanel());
		
		return panel;
	}
	
	protected JPanel buildLeftMiddleInnerPanel()
	{
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		JTextField titleField = new JTextField("Course Title");
		JTextField numberField = new JTextField("Course Number");
		JTextField instructorField = new JTextField("Instructor");
		JTextField classroomField = new JTextField("Classroom");
		
		pane.add(titleField);
		pane.add(numberField);
		pane.add(instructorField);
		pane.add(classroomField);
		
		return pane;
	}
	
	private JPanel buildLeftInnerPanel()
	{
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Title:"); 
		title.setFont(DEFAULT_FONT);
		JLabel number = new JLabel("Number");
		number.setFont(DEFAULT_FONT);
		JLabel instructor = new JLabel("Instructor:");
		instructor.setFont(DEFAULT_FONT);
		JLabel classroom = new JLabel("Classroom:");
		classroom.setFont(DEFAULT_FONT);
		
		pane.add(title);
		pane.add(number);
		pane.add(instructor);
		pane.add(classroom);
		
		pane.setBorder(BorderFactory.createLoweredBevelBorder());
		
		return pane;
	}
	
	private JPanel buildRightMiddleInnerPanel()
	{
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		JLabel startTime = new JLabel("Starts at:");
		startTime.setFont(DEFAULT_FONT);
		JLabel endTime = new JLabel("Ends at:");
		endTime.setFont(DEFAULT_FONT);
		JLabel fieldOfStudy = new JLabel("Field of Study:");
		fieldOfStudy.setFont(DEFAULT_FONT);
		
		pane.add(startTime);
		pane.add(endTime);
		pane.add(fieldOfStudy);
		
		pane.setBorder(BorderFactory.createLoweredBevelBorder());
		return pane;
	}
	
	protected JPanel buildRightInnerPanel()
	{
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		Date date = new Date();
		
		SpinnerDateModel sdm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		
		JSpinner beginTimeSpinner = new JSpinner(sdm);
		JSpinner.DateEditor de = new JSpinner.DateEditor(beginTimeSpinner, "hh:mm a");
		
		beginTimeSpinner.setEditor(de);
		
		pane.add(beginTimeSpinner);
		
		sdm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		
		JSpinner endTimeSpinner = new JSpinner(sdm);
		de = new JSpinner.DateEditor(endTimeSpinner, "hh:mm a");
		endTimeSpinner.setEditor(de);
		
		pane.add(endTimeSpinner);
		
		/*String[] dayArray = { "Monday", "Tuesday", "Wednesday",
				"Thursday", "Friday", "Saturday", "Sunday" };
		
		JComboBox days = new JComboBox(dayArray);
		days.setSelectedIndex(3);
		
		pane.add(days);
		
		JTextField fieldOfStudy = new JTextField("Field of Study");
		pane.add(fieldOfStudy);
		
		JTextField semesterField = new JTextField("Fall 2014");
		pane.add(semesterField);
		
		return pane;
	}
	
	
	private JPanel topPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		
		JLabel title = new JLabel("<html><pre style =\"font-family: Times New Roman;\">Title:	</pre></html>");
		JTextField titleField = new JTextField("Calculus 1");
		
		JLabel startTime = new JLabel("Starts at:");
		
		Date date = new Date();
		
		SpinnerDateModel spinnerModel = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		
		JSpinner beginTimeSpinner = new JSpinner(spinnerModel);
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(beginTimeSpinner, "hh:mm a");
		
		beginTimeSpinner.setEditor(dateEditor);
		
		panel.add(title);
		panel.add(titleField);
		panel.add(startTime);
		panel.add(beginTimeSpinner);
		
		return panel;
	}
	
	private JPanel upperMiddlePanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		
		JLabel number = new JLabel("Number:");
		
		JTextField numberField = new JTextField("1410");
		
		JLabel endTime = new JLabel("Ends at:");
		
		Date date = new Date();
		SpinnerDateModel sdm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		
		JSpinner endTimeSpinner = new JSpinner(sdm);
		JSpinner.DateEditor de = new JSpinner.DateEditor(endTimeSpinner, "hh:mm a");
		endTimeSpinner.setEditor(de);
		
		panel.add(number);
		panel.add(numberField);
		panel.add(endTime);
		panel.add(endTimeSpinner);
		
		return panel;
	} 
 */
}
