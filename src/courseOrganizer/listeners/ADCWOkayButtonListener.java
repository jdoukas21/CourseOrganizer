package courseOrganizer.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Calendar;

import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import courseOrganizer.models.Course;
import courseOrganizer.models.CourseList;
import courseOrganizer.models.Days;
import courseOrganizer.views.addCourseViews.AddDetailedCourseWindow;

// Σχετίζεται με τις ενέργειες όταν πατηθεί το "ΟΚ" κατά την προσθήκη νέου μαθήματος.
public class ADCWOkayButtonListener implements ActionListener
{
	private Object[] inputObjects;
	/*
	 * This is what the inputObjects array holds:
	 * 0. JTextField titleField
	 * 1. JSpinner beginTimeSpinner
	 * 2. JTextField numberField
	 * 3. JSpinner endTimeSpinner;
	 * 4. JTextField instructorField;
	 * 5. JTextField FOSField;
	 * 6. JTextField classroomField;
	 * 7. JTextField semesterField;
	 * 8. checkBoxArray -- JCheckBoxes:
	 * --> monday
	 * --> tuesday
	 * --> wednesday
	 * --> thursday
	 * --> friday
	 * --> saturday
	 * --> sunday
	 * --> all 
	*/
	private CourseList courseList;
	
	private AddDetailedCourseWindow adcw; // Μεταβλητή JFrame.
	
        //constructor
	public ADCWOkayButtonListener(AddDetailedCourseWindow adcw, Object[] inputObjects, CourseList courseList)
	{
                this.inputObjects = inputObjects;
		this.adcw = adcw;
		this.courseList = courseList;
        }

        // Αυτή η μέθοδος καθορίζει τις ενέργειες όταν πατηθεί το "ΟΚ"
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JTextField titleField = (JTextField) inputObjects[0];   //Το στοιχείο inputObjects[0] είναι τύπου JTextField.
		JSpinner beginTimeSpinner = (JSpinner) inputObjects[1]; //Το στοιχείο inputObjects[1] είναι τύπου JSpinner.
		JTextField numberField = (JTextField) inputObjects[2];
		JSpinner endTimeSpinner = (JSpinner) inputObjects[3];
		JTextField instructorField = (JTextField) inputObjects[4];
		JTextField FOSField = (JTextField) inputObjects[5];
		JTextField classroomField = (JTextField) inputObjects[6];
		JTextField semesterField = (JTextField) inputObjects[7];
		JCheckBox[] checkBoxArray = (JCheckBox[]) inputObjects[8]; //Το στοιχείο inputObjects[8] είναι τύπου JCheckBox.
		
		// Δημιουργεί ένα νέο μάθημα
                Course course =  new Course(titleField.getText());
		
		String time = getTime(beginTimeSpinner, endTimeSpinner);
		course.setTime(time);
		
		course.setNumber(numberField.getText());
		course.setInstructor(instructorField.getText());
		course.setField(FOSField.getText());
		course.setClassroom(classroomField.getText());
		course.setSemester(semesterField.getText());
		
		course.setDays(determineSelectedDays(checkBoxArray, course));
                
                // Αποθηκεύει τα χαρακτηριστικά του μαθήματος στην επόμενη θέση της λίστας.
                courseList.addCourse(course);
		adcw.dispose(); // Αφού προστεθούν τα πεδία στη λίστα το παράθυρο κλείνει.
                
	}
	
	private String getTime(JSpinner beginTimeSpinner, JSpinner endTimeSpinner)
	{
		String time = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime((Date)beginTimeSpinner.getValue());
		System.out.println(beginTimeSpinner.getValue().toString());
		
		time += calendar.get(Calendar.HOUR) + ":";
		time += calendar.get(Calendar.MINUTE) + " ";
		if (calendar.get(Calendar.AM_PM)== 1)
		{
			time += "PM";
		}
		else
			time += "AM";
		
		time += " - ";
		calendar.setTime((Date)endTimeSpinner.getValue());
		
		time += calendar.get(Calendar.HOUR) + ":";
		time += calendar.get(Calendar.MINUTE) + " ";
		if (calendar.get(Calendar.AM_PM)== 1)
		{
			time += "PM";
		}
		else
			time += "AM";
		
		return time;
	}

	private Days determineSelectedDays(JCheckBox[] checkBoxArray, Course course)
	{
		Days days = new Days(false);
		
		JCheckBox allCheckBox = checkBoxArray[checkBoxArray.length - 1];
		
		if (allCheckBox.getText().equals("All") && allCheckBox.isSelected()) //check to make sure all aren't already selected
		{
			days = new Days(true);
			return days;
		}
		
		for (int n = 0; n < checkBoxArray.length; n++)
		{
			JCheckBox cBox = checkBoxArray[n];	
			
			if (cBox.getText().equals(Days.MONDAY) && cBox.isSelected())
			{
				System.out.println("ADCWListener: Monday added to days.");
				days.addDay(Days.MONDAY);
			}
			else if (cBox.getText().equals(Days.TUESDAY) && cBox.isSelected())
			{
				days.addDay(Days.TUESDAY);
			}
			else if (cBox.getText().equals(Days.WEDNESDAY) && cBox.isSelected())
			{
				System.out.println("ADCWListener: Wednesday added to days.");
				days.addDay(Days.WEDNESDAY);
			}
			else if (cBox.getText().equals(Days.THURSDAY) && cBox.isSelected())
			{
				days.addDay(Days.THURSDAY);
			}
			else if (cBox.getText().equals(Days.FRIDAY) && cBox.isSelected())
			{
				days.addDay(Days.FRIDAY);
			}
			else if (cBox.getText().equals(Days.SATURDAY) && cBox.isSelected())
			{
				days.addDay(Days.SATURDAY);
			}
			else if (cBox.getText().equals(Days.SUNDAY) && cBox.isSelected())
			{
				days.addDay(Days.SUNDAY);
			}
			
		}
		
		return days;
	}

}
