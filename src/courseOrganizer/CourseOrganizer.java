package courseOrganizer;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CourseOrganizer
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Window window = new Window();
		Scanner in = new Scanner(System.in);

		CourseList cl = new CourseList();
		CourseOrganizer co = new CourseOrganizer();
		ArrayList<Course> cal = cl.getArrayList();
		Course currentCourse;
		int index;
		String command;
		String courseName;

		System.out
				.println("What would you like to do? Type /help for a list of commands.");

		while (true)
		{
			command = in.nextLine();

			if (command.equalsIgnoreCase("/help"))
			{
				System.out
						.println("The available commands are: "
								+ "\nadd <Course Title> ---------- Adds the specified course to the course list."
								+ "\nedit <Course Title> ---------- Edit the details (Title, Instructor, Date/Time, etc.) of specified course."
								+ "\nview all ---------- View all courses. Does not show details."
								+ "\nview all assign ---------- View all assignments, completed or not, from all courses."
								+ "\nview all fin assign ---------- View all completed assignments from all courses."
								+ "\nview all unfin assign ---------- View all uncompleted assignments from all courses."
								+ "\nview <Course Title> ---------- View details about specified course. Shows uncompleted assignments."
								+ "\nview assign <Course Title> ---------- View all assignments from specified course."
								+ "\nview unfin assign <Course Title> ---------- View all uncompleted assignments from specified course."
								+ "\ndelete <Course Title> ---------- Remove a course permanently from the course list."
								+ "\nassignment to <Course Title> ---------- Create a new assignment and add it to specified course."
								+ "\nassignment fin <Course Title> ---------- Indicate that you have finished an assignment from the specified course."
								+ "\nassignment del <Course Title> ---------- Delete an assignment from specified course."
								+ "\nexit ---------- Terminate the program.");
			}

			// ADD****************************************
			else if (command.contains("add"))
			{

				courseName = co.checkFunctionValidityAndGetCourseTitle("add", command);
				if (courseName.isEmpty())
				{
					continue;
				}

				cl.addCourse(new Course(courseName));
				Course cCourse = cal.get(cl.getIndexOf(courseName));

				System.out
						.println("Would you like to add details to the course \""
								+ courseName + "\"? Y/N");
				String choice = in.nextLine();

				if (choice.equalsIgnoreCase("Y"))
				{
					System.out.println("Course number: ");
					cCourse.setNumber(in.nextLine());
					System.out.println("Instructor: ");
					cCourse.setInstructor(in.nextLine());
					System.out.println("Field of study: ");
					cCourse.setField(in.nextLine());
					System.out.println("Classroom: ");
					cCourse.setClassroom(in.nextLine());
					System.out.println("Time (e.g., 10:30 - 11:45 AM): ");
					cCourse.setTime(in.nextLine());

					System.out.println(courseName + " added with details.");
				}
				else if (choice.equalsIgnoreCase("N"))
				{
					System.out.println(courseName + " added with no details.");
				}

			}

			// EDIT***************************************
			else if (command.contains("edit"))
			{
				courseName = co.checkFunctionValidityAndGetCourseTitle("edit", command);
				if (courseName.equals(""))
				{
					continue;
				}

				if (!cl.checkCourseExists(courseName))
				{
					System.out.println("Specified course not found.");
					continue;
				}

				currentCourse = cl.get(courseName);

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
				}
				else if (changeType.equalsIgnoreCase("number"))
				{
					System.out.print("New number: ");
					currentCourse.setNumber(in.nextLine());
				}
				else if (changeType.equalsIgnoreCase("instructor"))
				{
					System.out.print("New instructor: ");
					currentCourse.setInstructor(in.nextLine());
				}
				else if (changeType.equalsIgnoreCase("field"))
				{
					System.out.print("New field of study: ");
					currentCourse.setField(in.nextLine());
				}
				else if (changeType.equalsIgnoreCase("classroom"))
				{
					System.out.print("New classroom: ");
					currentCourse.setClassroom(in.nextLine());
				}
				else if (changeType.equalsIgnoreCase("time"))
				{
					System.out.print("New time: ");
					currentCourse.setTime(in.nextLine());
				}
				else if (changeType.equalsIgnoreCase("/abort"))
				{
					continue;
				}
				System.out.println("Value set.");

			}

			// VIEW ALL**************************************
			else if (command.contains("view all"))
			{
				System.out.println(cl.toString());
			}
			else if (command.contains("view all assign"))
			{

			}
			else if (command.contains("view all fin assign"))
			{

			}
			else if (command.contains("view all unfin assign"))
			{
				
			}
			else if (command.contains("view"))
			{
				

			}
			else if (command.contains("view assign"))
			{

			}
			else if (command.contains("view unfin assign"))
			{
				
			}
			else if (command.contains("delete"))
			{

			}
			else if (command.contains("assignment to"))
			{
				String assignTitle;
				String description = "";
				int dateAssigned = 0;
				int dateFinished = 0;
				boolean finished = false;

				courseName = co.checkFunctionValidityAndGetCourseTitle("assignment to", command);
				if (!cl.checkCourseExists(courseName))
				{
					System.out.println("Specified course not found.");
					continue;
				}

				currentCourse = cl.get(courseName);

				System.out
						.println("Enter the title of the assignment (if you cannot think of a title, press enter"
								+ "\nand a default value will be assigned.");
				assignTitle = in.nextLine();

				System.out
						.println("Please describe the assignment to be added.");
				description = in.nextLine();
				if (assignTitle.isEmpty())
				{
					assignTitle = co.getFirstWords(description) + "...";
				}

				do
				{
					System.out.println("Please enter the date the assignment was given in the following format: " +
							"\nMMDDYYYY (e.g., 09152011).");
					try
					{
						dateAssigned = in.nextInt();
					}
					catch (InputMismatchException ime)
					{
						System.out
								.println("Error. Date not formatted correctly or erroneous input entered. Please try again.");
					}
				} while (dateAssigned == 0);
				
				System.out.println("Have you already finished this assignment? Y/N");
				if (in.nextLine().equalsIgnoreCase("Y"))
				{
					finished = true;
					do
					{
						System.out.println("What was the date of completion? Format in this manner: MMDDYYYY. If unknown, leave blank.");
						try
						{
							dateFinished = in.nextInt();
						}
						catch (InputMismatchException ime)
						{
							System.out
									.println("Error. Date not formatted correctly or erroneous input entered. Please try again.");
						}
					} while (dateFinished == 0);
				}
				else if (in.nextLine().equalsIgnoreCase("N"))
				{
					finished = false;
					System.out.println("Noted. Assignment operation complete.");
				}
				else 
				{
					System.out.println("Error. Invalid input. Default value assigned.");
					finished = false;
				}
				
				currentCourse.addAssignment(assignTitle, description, dateAssigned, dateFinished, finished);
				
			}
			
			else if (command.contains("assignment fin"))
			{
				courseName = co.checkFunctionValidityAndGetCourseTitle("assignment fin", command);
				if (!cl.checkCourseExists(courseName))
				{
					System.out.println("Specified course not found.");
					continue;
				}
				currentCourse = cl.get(courseName);
				
				System.out.println("The following is a list of uncompleted assignments attached to the selected course: ");
				System.out.println(currentCourse.unfinishedAssignmentsToString());
				System.out.println("Please type in the number corresponding to the assignment you'd like to mark as finished.");
				int courseIndex = in.nextInt();
				in.nextLine();
				
				System.out.println("Assignment marked as complete.");
				currentCourse.getAllAssignments().get(courseIndex - 1).setFinished(true);
			}
			
			else if (command.contains("assignment del"))
			{
				courseName = co.checkFunctionValidityAndGetCourseTitle("assignment del", command);
				if (!cl.checkCourseExists(courseName))
				{
					System.out.println("Specified course not found.");
					continue;
				}
				currentCourse = cl.get(courseName);
				
				System.out.println("The following is a list of all assignments attached to the selected course: ");
				System.out.println(currentCourse.assignmentsToString());
				System.out.println("Please enter the number corresponding to the assignment you'd like to delete." +
						"\nType a letter or non-numeric character to cancel operation.");
				try 
				{
					int courseIndex = in.nextInt();
					in.nextLine();
					currentCourse.getAllAssignments().remove(courseIndex-1);
					System.out.println("Assignment deleted.");
				}
				catch (InputMismatchException ime)
				{
					System.out.println("Operation cancelled.");
				}
				
			}
			else if (command.equalsIgnoreCase("exit"))
			{
				System.out.println("Goodbye!");
				System.exit(0);
			}
			else
			{
				System.out.println("\"" + command
						+ "\" is not recognized as a command in this program. "
						+ "\nType /help for a list of available commands.");
			}
		}

	}

	public String checkFunctionValidityAndGetCourseTitle(String function, String input)
	{
		String courseName = "";
		try
		{
			courseName = input.substring(function.length() + 1);
			if (courseName.isEmpty())
			{
				System.out.println("Invalid use of the " + function
						+ " function. Type /help for details.");
			}
		}
		catch (StringIndexOutOfBoundsException siobe)
		{
			System.out.println("Invalid use of the " + function
					+ " function. Type /help for details.");
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

}
