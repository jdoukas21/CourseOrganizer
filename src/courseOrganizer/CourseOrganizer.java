package courseOrganizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import courseOrganizer.models.Course;
import courseOrganizer.models.CourseList;

public class CourseOrganizer
{
	private CourseList			courseList = new CourseList();
	private Course				currentCourse;
	private Scanner				in				= new Scanner(System.in);
	private File				lastSavedName	= new File(
														(System.getProperty("user.dir"))
																+ "\\lastSaved.txt");

	public CourseList getCourseList()
	{
		return courseList;
	}

	public void setCourseList(CourseList courseList)
	{
		this.courseList = courseList;
	}

	public Course getCurrentCourse()
	{
		return currentCourse;
	}

	public void setCurrentCourse(Course currentCourse)
	{
		this.currentCourse = currentCourse;
	}
	
	public void createNewCourseList()
	{
		courseList = new CourseList();
	}

	public void showHelpDialog()
	{
		System.out
				.println("The available commands are: "
						+ "\nadd <Course Title> ---------- Adds the specified course to the course list."
						+ "\nedit <Course Title> ---------- Edit the details (Title, Instructor, Date/Time, Field of Study) of specified course."
						+ "\nsort ---------- Sorts the current CourseList alphabetically by course title."
						+ "\nedit assign <Course Title> ---------- Edit an assignment attached to the specified course."
						+ "\nview all ---------- View all courses. Shows details."
						+ "\nview all assigns ---------- View all assignments, completed or not, from all courses."
						+ "\nview all fin assigns ---------- View all completed assignments from all courses."
						+ "\nview all unfin assigns ---------- View all uncompleted assignments from all courses."
						+ "\nview <Course Title> ---------- View details about specified course. Shows uncompleted assignments."
						+ "\nview assign details <Course Title> ---------- View everything about an assignment from the specified course."
						+ "\nview assigns from <Course Title> ---------- View all assignments from specified course."
						+ "\nview unfin assigns <Course Title> ---------- View all uncompleted assignments from specified course."
						+ "\nview fin assigns <Course Title> ---------- View all completed assignments from specified course."
						+ "\ndelete <Course Title> ---------- Remove a course permanently from the course list."
						+ "\nassignment to <Course Title> ---------- Create a new assignment and add it to specified course."
						+ "\nassignment fin <Course Title> ---------- Indicate that you have finished an assignment from the specified course."
						+ "\nassignment del <Course Title> ---------- Delete an assignment from specified course."
						+ "\nsave ---------- Saves the current CourseList to a file titled \"CourseList.txt\" in the same directory as the program."
						+ "\nsave to <File Name> ---------- Saves the current CourseList to a rich text file with the specified name. Do not include file extension."
						+ "\nload last ---------- Reads from the last default CourseList (\"CourseList.txt\")."
						+ "\nload from ---------- Opens the file explorer for selection of a CourseList file from which to read."
						+ "\nexit ---------- Terminate the program.");
	}

	public void add(String courseName)
	{

		courseList.addCourse(new Course(courseName));
		currentCourse = courseList.get(courseList.getIndexOf(courseName));

		System.out.println("Would you like to add details to the course \""
				+ courseName + "\"? Y/N");
		String choice = in.nextLine();

		if (choice.equalsIgnoreCase("Y"))
		{
			System.out.println("Course number: ");
			currentCourse.setNumber(in.nextLine());
			System.out.println("Instructor: ");
			currentCourse.setInstructor(in.nextLine());
			System.out.println("Classroom: ");
			currentCourse.setClassroom(in.nextLine());
			System.out.println("Time (e.g., 10:30 - 11:45 AM): ");
			currentCourse.setTime(in.nextLine());
			System.out.println("Field of study: ");
			currentCourse.setField(in.nextLine());

			System.out.println(courseName + " added with details.");
		}
		else if (choice.equalsIgnoreCase("N"))
		{
			System.out.println(courseName + " added with no details.");
		}
	}
	
	public void delete(String courseName)
	{
		currentCourse = courseList.get(courseName);
		
		if (!courseList.checkCourseExists(courseName))
		{
			System.out.println("Specified course not found.");
			return;
		}
		
		System.out.println("Are you sure you wouldl ike to delete \n" + currentCourse.getName() + "? Y/N");
		String next = in.nextLine();
		if (next.equalsIgnoreCase("Y"))
		{
			courseList.remove(courseList.getIndexOf(courseName));
			System.out.println("Deleted.");
		}
		else if (next.equalsIgnoreCase("N"))
		{
			System.out.println("Operation cancelled.");
		}
		else
		{
			System.out.println("Crap.");
			return;
		}
		
	}

	public void edit(String courseName)
	{
		boolean goodInput = false;

		if (!courseList.checkCourseExists(courseName))
		{
			System.out.println("Specified course not found.");
			return;
		}

		currentCourse = courseList.get(courseName);

		do
		{
			System.out.println("What would you like to modify about "
					+ currentCourse.getName() + "?");
			System.out
					.println("Enter \"name\", \"number\", \"instructor\", \"field\", \"classroom\", or \"time\". "
							+ "\nType /abort to cancel edit operation.");
			String changeType = in.nextLine();

			if (changeType.equalsIgnoreCase("name"))
			{
				System.out.print("New name: ");
				currentCourse.setName(in.nextLine());
				goodInput = true;
			}
			else if (changeType.equalsIgnoreCase("number"))
			{
				System.out.print("New number: ");
				currentCourse.setNumber(in.nextLine());
				goodInput = true;
			}
			else if (changeType.equalsIgnoreCase("instructor"))
			{
				System.out.print("New instructor: ");
				currentCourse.setInstructor(in.nextLine());
				goodInput = true;
			}
			else if (changeType.equalsIgnoreCase("field"))
			{
				System.out.print("New field of study: ");
				currentCourse.setField(in.nextLine());
				goodInput = true;
			}
			else if (changeType.equalsIgnoreCase("classroom"))
			{
				System.out.print("New classroom: ");
				currentCourse.setClassroom(in.nextLine());
				goodInput = true;
			}
			else if (changeType.equalsIgnoreCase("time"))
			{
				System.out.print("New time: ");
				currentCourse.setTime(in.nextLine());
				goodInput = true;
			}
			else if (changeType.equalsIgnoreCase("/abort"))
			{
				System.out.println("Operation cancelled.");
				return;
			}
			else
			{
				System.out.println("Invalid input.");
			}
		} while (!goodInput);

		System.out.println("Value set.");
	}

	public void save(String directory) throws FileNotFoundException
	{
		//COURSE LIST SAVE
		File outputFile = new File(directory + "\\CourseList."
				+ getCurrentDate('-') + ".rtf");
		File lastSaved = new File(directory + "\\lastSaved.txt");
		File assignmentList = new File(directory + "\\assignments"
				+ getCurrentDate('-') + ".rtf");
		PrintWriter writer = new PrintWriter(outputFile);

		writer.println("CourseList Auto-Save");
		writer.println(getCurrentDate('-'));
		writer.println();
		writer.print(courseList.getSaveString());

		writer.println();

		writer.print("See the following file:\n"
				+ assignmentList
				+ "\nfor details on all assignments associated with the above courses.");

		writer.close();
		System.out
				.println("CourseList successfully saved to \n\t" + outputFile);

		//ASSIGNMENT LIST SAVE
		writer = new PrintWriter(lastSaved);
		writer.print(outputFile);
		writer.close();
		lastSavedName = lastSaved;

		writer = new PrintWriter(assignmentList);
		writer.println("Assignment List Auto-Save");
		writer.println(getCurrentDate('-'));
		writer.println("-PLEASE DO NOT EDIT THIS FILE MANUALLY-");
		writer.println();
		writer.print(courseList.getAssignmentSaveString());
		writer.close();

		System.out.println("Assignment list successfully saved to \n\t"
				+ assignmentList);

	}

	public void loadFrom()
	{
		JFileChooser fileChooser = new JFileChooser(
				System.getProperty("user.dir"));
		fileChooser.showDialog(null, "Open");

		Scanner fileRead;
		try
		{
			fileRead = new Scanner(fileChooser.getSelectedFile());
		}
		catch (Exception e)
		{
			System.out.println("File-read error. Operation cancelled.");
			return;
		}

		readFile(fileRead);
	}

	public void loadLast()
	{
		File nameOfLastSaved = lastSavedName;
		Scanner fileRead;
		try
		{
			fileRead = new Scanner(nameOfLastSaved);
		}
		catch (FileNotFoundException e)
		{
			System.out
					.println("No last save present. If you believe this is an error,\nuse the load from function to find the file.");
			return;
		}

		try
		{
			File file = new File(fileRead.nextLine());
			fileRead = new Scanner(file);
		}
		catch (FileNotFoundException e)
		{
			System.out
					.println("Last saved file not found! \nPlease use the load from function.");
			return;
		}

		readFile(fileRead);
	}

	public void readFile(Scanner fileScanner)
	{
		Scanner read = fileScanner;
		String line = "";
		Course course;
		File assignmentList = new File("");

		// System.out.println("In readFile");

		// set the courseList title
		line = read.nextLine();
		if (!line.isEmpty())
		{
			courseList.setTitle(line);
		}
		// set the courseList date
		line = read.nextLine();
		if (!line.isEmpty())
		{
			courseList.setDate(line);
		}

		// read the rest of the info from the courseList file
		while (read.hasNext())
		{
			line = read.nextLine();
			/*
			 * System.out.println("ReadFile, 1st half, outer loop.\n\tLine = " +
			 * line);
			 */

			if (line.contains("See the following file:"))
			{
				line = read.nextLine();
				assignmentList = new File(line);
				break;
			}

			if (!line.isEmpty() && read.hasNext())
			{
				course = new Course();
				while (!line.isEmpty() && read.hasNext())
				{
					/*
					 * System.out
					 * .println("ReadFile, 1st half, inner loop.\n\tLine = " +
					 * line);
					 */
					if (Character.isDigit(line.charAt(0)))
					{
						course.setName(getImportantValue(line));
					}
					else if (!Character.isDigit(line.charAt(0)))
					{
						if (line.contains("Number"))
						{
							course.setNumber(getImportantValue(line));
						}
						else if (line.contains("Instructor"))
						{
							course.setInstructor(getImportantValue(line));
						}
						else if (line.contains("Classroom"))
						{
							course.setClassroom(getImportantValue(line));
						}
						else if (line.contains("Time"))
						{
							course.setTime(getImportantValue(line));
						}
						else if (line.contains("Field of Study"))
						{
							course.setField(getImportantValue(line));
						}
						else
						{

						}
						/*
						 * else if (line.contains("Assignments")) {
						 * 
						 * } else if (line.contains("Finished")) {
						 * 
						 * } else if (line.contains("Unfinished")) {
						 * 
						 * }
						 */
					}
					line = read.nextLine();
				}
				// add the course to the course list
				courseList.addCourse(course);
			}
		}// end courseList read outer while loop

		System.out.println();

		// PART 2: ASSIGNMENT LIST
		// READ*************************************************
		try
		{
			read = new Scanner(assignmentList);
			//System.out.println("AssignmentList file: " + assignmentList);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Oh noes! AssignmentList file not found!");
			e.printStackTrace();
			return;
		}

		// advance the scanner past the first four lines of the file
		// (Assignment List Auto-Save, <date>, warning message, and whitespace)
		read.nextLine();
		read.nextLine();
		read.nextLine();
		read.nextLine();

		String cn = ""; // course name

		// assignment fields
		String title = "Unknown";
		String description = "Unknown";
		String dateAssigned = "Unknown";
		String dateDue = "Unknown";
		boolean finished = false;
		String dateFinished = "(not finished)";

		while (read.hasNext())
		{
			line = read.nextLine();
			//System.out.println("Line at beginning of while loop: " + line);

			if (line.contains(".* "))
			{
				cn = getImportantValue(line); // read the course title
				line = read.nextLine();
				//System.out.println("Line at end of course title if statement: "
						//+ line);
			}
			// System.out.println("ReadFile, 2nd part, OUTER loop.\nLine = "
			// + line);

			if (!line.isEmpty())
			{

				title = line;
				//System.out.println("Assignment title: " + title);

				read.nextLine(); // advance past whitespace between assignment
									// title and description
				description = ""; // clear description
				line = read.nextLine();
				while (!line.isEmpty())
				{
					description += line;
					//System.out.println("Description: " + description);
					line = read.nextLine();
				}
				//System.out.println("Line after description if statement: "
						//+ line);

				dateAssigned = read.nextLine();
				//System.out.println("Date Assigned: " + dateAssigned);
				dateDue = read.nextLine();
				//System.out.println("Date due: " + dateDue);
				line = read.nextLine();
				//System.out.println("Line before the boolean if block: " + line);
				if (line.equalsIgnoreCase("false"))
				{
					finished = false;
				}
				else if (line.equalsIgnoreCase("true"))
				{
					finished = true;
				}
				else
				{
					System.out
							.println("<FORMATTING ERROR>finished boolean not formatted correctly");
				}
				dateFinished = read.nextLine();
				courseList.get(cn).addAssignment(title, description,
						dateAssigned, dateDue, dateFinished, finished);

				if (read.hasNext())
				{
					read.nextLine(); // advance past whitespace
				}
			}
			/*
			 * System.out.println("Assignment to be added to " + cn);
			 * System.out.println("Title: " + title + "\nDescription: " +
			 * description + "\nDate Assigned: " + dateAssigned);
			 */
		}

		read.close();

		System.out.println("File load successful.");

	}

	public String getImportantValue(String original)
	{
		String desiredValue = "";

		for (int n = 0; n + 1 < original.length(); n++)
		{
			if (original.charAt(n) == ':' && original.charAt(n + 1) == ' ')
			{
				desiredValue = original.substring(n + 2);
				return desiredValue;
			}
			else if (Character.isDigit(original.charAt(n))
					&& original.charAt(n + 1) == '.'
					&& original.charAt(n + 2) == ' ')
			{
				desiredValue = original.substring(n + 3);
				return desiredValue;
			}
			else if (Character.isDigit(original.charAt(n))
					&& original.charAt(n + 1) == '.'
					&& original.charAt(n + 2) == '*'
					&& original.charAt(n + 3) == ' ')
			{
				desiredValue = original.substring(n + 4);
				return desiredValue;
			}
		}
		return desiredValue;
	}

	public void view(String courseName)
	{
		if (!courseList.checkCourseExists(courseName))
		{
			System.out.println("Specified course not found.");
			return;
		}
		System.out.println(courseList.get(courseName).getName() + "\nDetails:"
				+ courseList.get(courseName).detailsToString());
		System.out.println("Unfinished assignments: "
				+ courseList.get(courseName).unfinishedAssignmentsToString(
						false));

		System.out.println("View operation complete.");
	}

	public void viewAll()
	{
		if (courseList.isEmpty())
		{
			System.out.println("The CourseList is empty!");
		}
		else
			System.out.println(courseList.toString());
	}

	public void viewAssignsFrom(String courseName)
	{
		if (!courseList.checkCourseExists(courseName))
		{
			System.out.println("Specified course not found.");
			return;
		}
		currentCourse = courseList.get(courseName);

		if (currentCourse.getAllAssignments().isEmpty())
		{
			System.out
					.println(currentCourse.getName()
							+ " currently has no assignments attached to it. Operation cancelled.");
			return;
		}

		courseList.viewAssignmentsFrom(currentCourse, "all");
		System.out.println("Operation complete.");

	}

	public void viewUnfinAssigns(String courseName)
	{
		if (!courseList.checkCourseExists(courseName))
		{
			System.out.println("Specified course not found.");
			return;
		}
		currentCourse = courseList.get(courseName);

		if (currentCourse.getAllAssignments().isEmpty())
		{
			System.out
					.println(currentCourse.getName()
							+ " currently has no assignments attached to it. Operation cancelled.");
			return;
		}

		courseList.viewAssignmentsFrom(currentCourse, "unfinished");
		System.out.println("Operation complete.");

	}

	public void viewAssignDetails(String courseName)
	{
		if (!courseList.checkCourseExists(courseName))
		{
			System.out.println("Specified course not found.");
			return;
		}
		currentCourse = courseList.get(courseName);

		if (currentCourse.getAllAssignments().isEmpty())
		{
			System.out
					.println(currentCourse.getName()
							+ " currently has no assignments attached to it. Operation cancelled.");
			return;
		}

		System.out
				.println("The following is a list of all assignments attached to the selected course: ");
		System.out.println(currentCourse.assignmentsToString(false, "console"));
		System.out
				.println("Please enter the number corresponding to the assignment you'd like to view."
						+ "\nType a letter or non-numeric character to cancel operation.");
		try
		{
			int index = in.nextInt();
			in.nextLine();
			System.out.println(currentCourse.getAssignment(index - 1));
		}
		catch (InputMismatchException ime)
		{
			System.out.println("Operation cancelled.");
			in.nextLine();
			return;
		}

	}

	public void viewAllUnfinishedAssignments()
	{
		for (int n = 0; n < courseList.size(); n++)
		{
			System.out.println(courseList.get(n)
					.unfinishedAssignmentsToString(true));
		}
	}

	public void viewAllFinishedAssignments()
	{
		for (int n = 0; n < courseList.size(); n++)
		{
			System.out.println(courseList.get(n)
					.finishedAssignmentsToString(true));
		}
	}

	public void viewAllAssignments()
	{
		for (int n = 0; n < courseList.size(); n++)
		{
			System.out.println(courseList.get(n).assignmentsToString(true,
					"all info"));
		}
	}

	public void assignmentTo(String courseName)
	{
		if (!courseList.checkCourseExists(courseName))
		{
			System.out.println("Specified course not found.");
			return;
		}

		String assignTitle;
		String description = "";
		String dateAssigned = "";
		String dateDue = "";
		String dateFinished = "";
		String choice = "";
		boolean finished = false;

		currentCourse = courseList.get(courseName);

		System.out
				.println("Enter the title of the assignment (if you cannot think of a title, press enter"
						+ "\nand a default value will be assigned.");
		assignTitle = in.nextLine();

		System.out.println("Please describe the assignment to be added.");
		description = in.nextLine();
		if (assignTitle.isEmpty())
		{
			assignTitle = getFirstWords(description) + "...";
		}

		System.out
				.println("Please enter the date the assignment was given in the following format: "
						+ "\nMM/DD/YYYY (e.g., 09/15/2011).");
		dateAssigned = in.nextLine();

		System.out
				.println("What is the due date of this assignment? Format in this manner: MM/DD/YYYY");
		dateDue = in.nextLine();

		System.out.println("Have you already finished this assignment? Y/N");
		choice = in.nextLine();

		if (choice.equalsIgnoreCase("Y"))
		{
			finished = true;
			System.out
					.println("What was the date of completion? Format in this manner: MM/DD/YYYY. If unknown, leave blank.");
			dateFinished = in.nextLine();

		}
		else if (choice.equalsIgnoreCase("N"))
		{
			finished = false;
			System.out.println("Noted.");
		}
		else
		{
			System.out.println("Error. Invalid input. Default value assigned.");
			finished = false;
		}

		currentCourse.addAssignment(assignTitle, description, dateAssigned,
				dateDue, dateFinished, finished);
		System.out.println("Assignment added.");
	}

	public void assignmentFin(String courseName)
	{
		if (!courseList.checkCourseExists(courseName))
		{
			System.out.println("Specified course not found.");
			return;
		}

		currentCourse = courseList.get(courseName);
		System.out
				.println("The following is a list of uncompleted assignments attached to the selected course: ");
		System.out.println(currentCourse.unfinishedAssignmentsToString(false));
		System.out
				.println("Please type in the number corresponding to the assignment you'd like to mark as finished.");
		int courseIndex = in.nextInt();
		in.nextLine();

		System.out.println("Assignment marked as complete.");
		currentCourse.getAllAssignments().get(courseIndex - 1)
				.setFinished(true);

	}

	public void assignmentDel(String courseName)
	{
		if (!courseList.checkCourseExists(courseName))
		{
			System.out.println("Specified course not found.");
			return;
		}
		currentCourse = courseList.get(courseName);

		System.out
				.println("The following is a list of all assignments attached to the selected course: ");
		System.out.println(currentCourse.assignmentsToString(false, "console"));
		System.out
				.println("Please enter the number corresponding to the assignment you'd like to delete."
						+ "\nType a letter or non-numeric character to cancel operation.");
		try
		{
			int courseIndex = in.nextInt();
			in.nextLine();
			currentCourse.getAllAssignments().remove(courseIndex - 1);
			System.out.println("Assignment deleted.");
		}
		catch (InputMismatchException ime)
		{
			System.out.println("Operation cancelled.");
			return;
		}
	}

	public String checkFunctionValidityAndGetCourseTitle(String function,
			String input)
	{
		String courseName = "";
		try
		{
			courseName = input.substring(function.length() + 1);
			if (courseName.isEmpty())
			{
				displayErrorMessage(function);
			}
		}
		catch (StringIndexOutOfBoundsException siobe)
		{
			displayErrorMessage(function);
		}
		return courseName;
	}

	public String getFirstWords(String str)
	{
		char ch;
		String firstWords = "";

		for (int n = 0; n < str.length(); n++)
		{
			ch = str.charAt(n);
			if (n > str.length() / 3)
			{
				break;
			}
			if (Character.isLetter(ch))
			{
				firstWords += ch;
			}
			else
			{
				firstWords += " ";
			}
		}

		return firstWords;
	}

	public void displayErrorMessage(String function)
	{
		System.out.println("Invalid use of the " + function
				+ " function. Type /help for details.");
	}

	public void setLastSavedNameFile(File last)
	{
		lastSavedName = last;
	}

	public File getLastSavedNameFile()
	{
		return lastSavedName;
	}

	public String getCurrentDate(char separator)
	{
		DateFormat dateFormat;
		if (separator == '-')
		{
			dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		}
		else if (separator == '/')
		{
			dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		}
		else
		{
			return "error";
		}

		Date date = new Date();
		return dateFormat.format(date);
	}

}
