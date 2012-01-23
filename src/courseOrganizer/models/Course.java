package courseOrganizer.models;

import java.util.ArrayList;

public class Course
{
	private String					name		= "Unknown";
	private String					number		= "Unknown";
	private String					instructor	= "Unknown";
	private String					classroom	= "Unknown";
	private String					fullTime 		= "Unknown";
	private String					beginTime	= "Unknown";
	private String					endTime		= "Unknown";
	private String					field		= "Unknown";
	private Days					days;
	private Course[]				preReqs;
	private String[]				books;
	private ArrayList<Assignment>	assignments	= new ArrayList<Assignment>();
	private String					semester	= "Unknown";

	public Course()
	{
		// no-arg constructor
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

	public Days getDays()
	{
		return days;
	}

	public void setDays(Days days)
	{
		this.days = days;
	}

	public String getSemester()
	{
		return semester;
	}

	public void setSemester(String semester)
	{
		this.semester = semester;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getSection()
	{
		return number;
	}

	public void setInstructor(String instructor)
	{
		this.instructor = instructor;
	}

	public String getInstructor()
	{
		return instructor;
	}

	public void setField(String field)
	{
		this.field = field;
	}

	public String getField()
	{
		return field;
	}

	public void setClassroom(String classroom)
	{
		this.classroom = classroom;
	}

	public String getClassroom()
	{
		return classroom;
	}

	public void setTime(String time)
	{
		this.fullTime = time;
	}

	public String getTime()
	{
		return fullTime;
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

	public boolean isSimple()
	{
		if (number.equals("Unknown") && instructor.equals("Unknown")
				&& classroom.equals("Unknown") && fullTime.equals("Unknown")
				&& field.equals("Unknown"))
		{
			return true;
		}
		else
			return false;
	}

	public void addAssignment(String title, String description,
			String dateAssigned, String dateDue, String dateFinished,
			boolean finished)
	{
		if (!finished)
		{
			assignments.add(new Assignment(title, description, dateAssigned,
					dateDue));
		}
		else
		{
			assignments.add(new Assignment(title, description, dateAssigned,
					dateDue, dateFinished, finished));
		}
	}

	public ArrayList<Assignment> getAllAssignments()
	{
		return assignments;
	}

	public Assignment getAssignment(int index)
	{
		return assignments.get(index);
	}

	@Override
	public String toString()
	{
		String fullString = "Course title: " + name + "\nCourse number: "
				+ number + "\nInstructor: " + instructor + "\nClassroom: "
				+ classroom + "\nTime: " + fullTime + "\nField: " + field;

		return fullString;
	}

	public String assignmentsToString(boolean showCourseName, String type)
	{
		// three different types: all info, console, and save
		String str = "";
		if (showCourseName)
		{
			if (type.equals("all info") || type.equals("auto save"))
			{
				str = name + "\n";
			}
			else if (type.equals("console") || type.equals("window"))
			{
				str = name + ": \n";
			}
			else
			{
				System.out
						.println("<INTERNAL ERROR> Invalid assignment string type.");
				return str;
			}
		}
		else
		{
			str = "";
		}

		if (type.equals("all info"))
		{
			for (int n = 0; n < assignments.size(); n++)
			{
				if (n == assignments.size() - 1)
				{
					str += "\t" + (n + 1) + ". Title: "
							+ assignments.get(n).getTitle()
							+ "\n\t\tDescription: \n\t"
							+ assignments.get(n).getDescription()
							+ "\n\t\tDate assigned: "
							+ assignments.get(n).getDateAssigned()
							+ "\n\t\tDate due: "
							+ assignments.get(n).getDateDue()
							+ "\n\t\tFinished: "
							+ assignments.get(n).isFinished()
							+ "\n\t\tDate finished: "
							+ assignments.get(n).getDateFinished();
				}
				else
				{
					str += "\t" + (n + 1) + ". Title: "
							+ assignments.get(n).getTitle()
							+ "\n\t\tDescription: "
							+ assignments.get(n).getDescription()
							+ "\n\t\tDate assigned: "
							+ assignments.get(n).getDateAssigned()
							+ "\n\t\tDate due: "
							+ assignments.get(n).getDateDue()
							+ "\n\t\tFinished: "
							+ assignments.get(n).isFinished()
							+ "\n\t\tDate finished: "
							+ assignments.get(n).getDateFinished() + "\n";
				}
			}
		}
		else if (type.equals("console"))
		{
			for (int n = 0; n < assignments.size(); n++)
			{
				if (n == assignments.size() - 1)
				{
					str += "\t" + (n + 1) + ". \""
							+ assignments.get(n).getTitle() + "\"";
				}
				else
				{
					str += "\t" + (n + 1) + ". \""
							+ assignments.get(n).getTitle() + "\"\n";
				}
			}
		}
		else if (type.equals("window"))
		{
			for (int n = 0; n < assignments.size(); n++)
			{
				if (n == assignments.size() - 1)
				{
					str += "\t" + (n + 1) + ". Title: "
							+ assignments.get(n).getTitle()
							+ "\n\t\tDate assigned: "
							+ assignments.get(n).getDateAssigned()
							+ "\n\t\tDate due: "
							+ assignments.get(n).getDateDue()
							+ "\n\t\tFinished: "
							+ assignments.get(n).isFinished()
							+ "\n\t\tDate finished: "
							+ assignments.get(n).getDateFinished();
				}
				else
				{
					str += "\t" + (n + 1) + ". Title: "
							+ assignments.get(n).getTitle()
							+ "\n\t\tDate assigned: "
							+ assignments.get(n).getDateAssigned()
							+ "\n\t\tDate due: "
							+ assignments.get(n).getDateDue()
							+ "\n\t\tFinished: "
							+ assignments.get(n).isFinished()
							+ "\n\t\tDate finished: "
							+ assignments.get(n).getDateFinished() + "\n";
				}
			}
		}
		else if (type.equals("auto save"))
		{
			for (int n = 0; n < assignments.size(); n++)
			{
				str += assignments.get(n).getTitle() + "\n\n"
						+ assignments.get(n).getDescription() + "\n\n"
						+ assignments.get(n).getDateAssigned() + "\n"
						+ assignments.get(n).getDateDue() + "\n"
						+ assignments.get(n).isFinished() + "\n"
						+ assignments.get(n).getDateFinished() + "\n\n";

			}
		}

		return str;
	}

	public String getNumber()
	{
		return number;
	}

	public String unfinishedAssignmentsToString(boolean showName)
	{
		String str;
		if (showName)
		{
			str = name + ". \n\t";
		}
		else
		{
			str = "\n\t";
		}

		for (int n = 0; n < assignments.size(); n++)
		{
			if (assignments.get(n).isFinished() == false)
			{
				if (n == assignments.size() - 1)
				{
					str += (n + 1) + ". \"" + assignments.get(n).getTitle()
							+ "\"";
				}
				else
				{
					str += (n + 1) + ". \"" + assignments.get(n).getTitle()
							+ "\"" + "\n\t";
				}
			}
		}

		return str;
	}

	public String finishedAssignmentsToString(boolean showName)
	{
		String str;
		if (showName)
		{
			str = name + ". \n\t";
		}
		else
		{
			str = "\n\t";
		}

		for (int n = 0; n < assignments.size(); n++)
		{
			if (assignments.get(n).isFinished())
			{
				if (n == assignments.size() - 1)
				{
					str += (n + 1) + ". \"" + assignments.get(n).getTitle()
							+ "\"";
				}
				else
				{
					str += (n + 1) + ". \"" + assignments.get(n).getTitle()
							+ "\"" + "\n\t";
				}
			}
		}
		return str;
	}

	public String detailsToString()
	{
		return "\n\tNumber: " + number + "\n\tInstructor: " + instructor
				+ "\n\tClassroom: " + classroom + "\n\tTime: " + fullTime
				+ "\n\tField of Study: " + field;

	}

	public int getNumberOfAssignments(String type)
	{
		int number = 0;
		if (type.equals("all"))
		{
			return assignments.size();
		}
		else if (type.equals("finished"))
		{
			for (int n = 0; n < assignments.size(); n++)
			{
				if (assignments.get(n).isFinished())
				{
					number++;
				}
			}
			return number;
		}
		else if (type.equals("unfinished"))
		{
			for (int n = 0; n < assignments.size(); n++)
			{
				if (!assignments.get(n).isFinished())
				{
					number++;
				}
			}
			return number;
		}
		else
			return -1;

	}
}
