package courseOrganizer;

import java.util.ArrayList;

public class Course
{
	private String name; 
	private String number; 
	private String instructor;
	private String classroom;
	private String time;
	private String field; 
	private Course[] preReqs;
	private String[] books;
	private ArrayList<Assignment> assignments = new ArrayList<Assignment>();
	
	public Course()
	{
		//no-arg constructor
	}
	
	public Course(String courseName)
	{
		setName(courseName); 
	}
	
	public Course(String courseName, String instructor)
	{
		setName(courseName);
		setInstructor(instructor);
	}
	
	public Course(String courseName, String instructor, String classroom)
	{
		setName(courseName);
		setInstructor(instructor);
		setClassroom(classroom);
	}
	
	protected void setName(String name)
	{
		this.name = name;
	}
	
	protected String getName()
	{
		return name;
	}
	
	protected void setNumber(String number)
	{
		this.number = number;
	}
	
	protected String getSection()
	{
		return number;
	}
	
	protected void setInstructor(String instructor)
	{
		this.instructor = instructor;
	}
	
	protected String getInstructor()
	{
		return instructor;
	}
	
	protected void setField(String field)
	{
		this.field = field;
	}
	
	protected String getField()
	{
		return field;
	}
	
	protected void setClassroom(String classroom)
	{
		this.classroom = classroom;
	}
	
	protected String getClassroom()
	{
		return classroom;
	}
	
	protected void setTime(String time)
	{
		this.time = time;
	}
	
	protected String getTime()
	{
		return time;
	}
	
	protected void setPreReq(Course[] preReqs)
	{
		this.preReqs = preReqs;
	}
	
	protected Course[] getPreReqs()
	{
		return preReqs;
	}
	
	protected void setBooks(String[] books)
	{
		this.books = books;
	}

	protected String[] getBooks()
	{
		return books;
	}
	
	protected void addAssignment(String title, String description, int dateAssigned, int dateFinished, boolean finished)
	{
		if (!finished)
		{
			assignments.add(new Assignment(title, description, dateAssigned));
		}
		else
		{
			assignments.add(new Assignment(title, description, dateAssigned, dateFinished, finished));
		}
	}
	
	protected ArrayList<Assignment> getAllAssignments()
	{
		return assignments;
	}
	
	protected Assignment getAssignment(int index)
	{
		return assignments.get(index);
	}
	
	@Override
	public String toString()
	{
		String fullString =
				"Course title: " + name
				+"\nCourse number: " + number
				+ "\nInstructor: " + instructor
				+ "\nClassroom: " + classroom
				+ "\nTime: " + time
				+ "\nField: " + field; 
		
		return fullString;
	}
	
	public String assignmentsToString()
	{
		String str = "";
		
		for (int n = 0; n < assignments.size(); n++)
		{
			if (n == assignments.size() - 1)
			{
				str += (n+1) + ". " + assignments.get(n).getTitle();
			}
			else
			{
				str += (n+1) + ". " + assignments.get(n).getTitle() + "\n";
			}
		}
		
		return str; 
	}
	
	public String unfinishedAssignmentsToString()
	{
		String str = "";
		
		for (int n = 0; n < assignments.size(); n ++)
		{
			if (assignments.get(n).isFinished() ==  false)
			{
				if (n == assignments.size() - 1)
				{
					str += (n+1) + ". " + assignments.get(n).getTitle();
				}
				else 
				{
					str += (n+1) + ". " + assignments.get(n).getTitle() + "\n";
				}
			}
		}
		
		return str; 
	}
}
