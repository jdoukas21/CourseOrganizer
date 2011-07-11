package courseOrganizer;

import java.util.ArrayList;

public class Course
{
	private String name; 
	private String section; 
	private String instructor;
	private String field; 
	private String classroom;
	private String time;
	private Course[] preReqs;
	private String[] books;
	private ArrayList<String> assignments;
	
	protected void setName(String name)
	{
		this.name = name;
	}
	
	protected String getName()
	{
		return name;
	}
	
	protected void setSection(String section)
	{
		this.section = section;
	}
	
	protected String getSection()
	{
		return section;
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
	
	protected void addAssignment(String assignment)
	{
		assignments.add(assignment);
	}
	
	protected ArrayList<String> getAllAssignments()
	{
		return assignments;
	}
	
	protected String getAssignment(int index)
	{
		return assignments.get(index);
	}
	
	@Override
	public String toString()
	{
		String fullString = "";
		
		
		return fullString;
	}
}
