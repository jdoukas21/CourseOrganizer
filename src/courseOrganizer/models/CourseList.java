package courseOrganizer.models;

import java.util.ArrayList;
import java.util.Collections;


public class CourseList
// this class holds all the information about the courses
{
	private ArrayList<Course>	courses		= new ArrayList<Course>();
	private String				semester	= "Unknown";
	private String				title		= "";
	private String				date		= "";

	public CourseList()
	{

	}
	
	public CourseList(ArrayList<Course> cl)
	{
		this.courses = cl;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getSemester()
	{
		return semester;
	}

	public void setSemester(String semester)
	{
		this.semester = semester;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void addCourse(Course course)
	{
		courses.add(course);
	}
	
	public int size()
	{
		return courses.size();
	}

	public Course get(int n)
	{
		return courses.get(n);
	}
	
	public void remove(int n)
	{
		courses.remove(n);
	}
	
	public void remove(String courseName)
	{
		courses.remove(getIndexOf(courseName));
	}
	
	public boolean isEmpty()
	{
		return courses.size() == 0;
	}
	
	public CourseList sort()
	{
		ArrayList<String> sortedTitles = new ArrayList<String>();
		for (int n = 0; n < courses.size(); n++)
		{
			sortedTitles.add(courses.get(n).getName());
		}

		Collections.sort(sortedTitles);

		for (int n = 0; n < courses.size(); n++)
		{
			for (int j = 0; j < courses.size(); j++)
			{
				if (courses.get(j).getName().equals(sortedTitles.get(n)))
				{
					Collections.swap(courses, n, j);
				}
			}
		}

		return new CourseList(courses);

	}

	public void viewAssignmentsFrom(Course course, String type)
	{
		ArrayList<Assignment> assignments = course.getAllAssignments();

		if (type.equals("all"))
		{
			for (int n = 0; n < assignments.size(); n++)
			{
				System.out.println((n + 1) + ". " + assignments.get(n) + "\n");
			}
		}
		else if (type.equals("finished"))
		{

		}
		else if (type.equals("unfinished"))
		{
			for (int n = 0; n < assignments.size(); n++)
			{
				if (!assignments.get(n).isFinished())
				{
					System.out.println((n + 1) + ". " + assignments.get(n)
							+ "\n");
				}
			}
		}
	}

	@Override
	public String toString()
	{
		String str = "";
		for (int n = 0; n < courses.size(); n++)
		{
			str += (n + 1) + ". " + courses.get(n).getName() + ". "
					+ "\n\tNumber: " + courses.get(n).getNumber()
					+ "\n\tInstructor: " + courses.get(n).getInstructor()
					+ "\n\tClassroom: " + courses.get(n).getClassroom()
					+ "\n\tTime: " + courses.get(n).getTime()
					+ "\n\tField of Study: " + courses.get(n).getField();
			if (n < courses.size() - 1)
			{
				str += "\n";
			}
		}
		return str;
	}

	public String toString(boolean windowVersion)
	{
		String str = "";

		if (windowVersion)
		{
			for (int n = 0; n < courses.size(); n++)
			{
				str += (n + 1) + ". " + courses.get(n).getName() + ". "
						+ "\n\tNumber: " + courses.get(n).getNumber()
						+ "\n\tInstructor: " + courses.get(n).getInstructor()
						+ "\n\tClassroom: " + courses.get(n).getClassroom()
						+ "\n\tTime: " + courses.get(n).getTime()
						+ "\n\tField of Study: " + courses.get(n).getField()
						+ "\n";
				if (n < courses.size() - 1)
				{
					str += "\n";
				}
			}
			return str;
		}
		else
		{
			return toString();
		}
	}

	public String getSaveString()
	{
		String str = "";
		for (int n = 0; n < courses.size(); n++)
		{
			str += (n + 1) + ". " + courses.get(n).getName() + "\n\tNumber: "
					+ courses.get(n).getNumber() + "\n\tInstructor: "
					+ courses.get(n).getInstructor() + "\n\tClassroom: "
					+ courses.get(n).getClassroom() + "\n\tTime: "
					+ courses.get(n).getTime() + "\n\tField of Study: "
					+ courses.get(n).getField() + "\n\tAssignments: "
					+ courses.get(n).getNumberOfAssignments("all")
					+ "\n\t\tFinished: "
					+ courses.get(n).getNumberOfAssignments("finished")
					+ "\n\t\tUnfinished: "
					+ courses.get(n).getNumberOfAssignments("unfinished")
					+ "\n\n";
			/*
			 * if (n < courses.size() -1) { str += "\n"; }
			 */
		}

		return str;
	}

	public String getAssignmentSaveString()
	{
		String str = "";
		for (int n = 0; n < courses.size(); n++)
		{
			str += (n + 1) + ".* "
					+ courses.get(n).assignmentsToString(true, "auto save")
					+ "\n";
		}

		return str;
	}

	public int getNumberOfAssignments(String type)
	{
		int number = 0;
		if (type.equals("all"))
		{
			for (int n = 0; n < courses.size(); n++)
			{
				number += courses.get(n).getNumberOfAssignments("all");
			}
			return number;
		}
		else if (type.equals("finished"))
		{
			for (int n = 0; n < courses.size(); n++)
			{
				number += courses.get(n).getNumberOfAssignments("finished");
			}
			return number;
		}
		else if (type.equals("unfinished"))
		{
			for (int n = 0; n < courses.size(); n++)
			{
				number += courses.get(n).getNumberOfAssignments("unfinished");
			}
			return number;
		}
		else
			return -1;
	}

	public boolean checkCourseExists(String courseName)
	{
		for (int n = 0; n < courses.size(); n++)
		{
			if (courses.get(n).getName().equalsIgnoreCase(courseName))
			{
				return true;
			}
		}
		return false;
	}

	public int getIndexOf(String course)
	{
		for (int n = 0; n < courses.size(); n++)
		{
			if (courses.get(n).getName().equalsIgnoreCase(course))
			{
				return n;
			}
		}

		return -1;
	}

	public Course get(String courseName)
	{
		return courses.get(getIndexOf(courseName));
	}

}
