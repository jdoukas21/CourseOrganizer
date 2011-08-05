package courseOrganizer;

import java.util.ArrayList;

public class CourseList
//this class holds all the information about the courses
{
	private ArrayList<Course> courses = new ArrayList<Course>();
	
	public CourseList()
	{
		
	}
	
	protected void addCourse(Course course)
	{
		courses.add(course);
	}
	
	protected ArrayList<Course> getArrayList()
	{
		return courses;
	}
	
	@Override
	public String toString()
	{
		String str = "";
		for (int n = 0; n < courses.size(); n ++)
		{
			str += (n+1) + ". " + courses.get(n).getName();
			if (n < courses.size() -1)
			{
				str += "\n";
			}
		}
		return str;
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
		for (int n = 0; n < courses.size(); n ++)
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
