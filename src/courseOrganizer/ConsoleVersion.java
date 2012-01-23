package courseOrganizer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import courseOrganizer.models.Course;
import courseOrganizer.models.CourseList;

public class ConsoleVersion
{
	private static CourseList			courseList		= new CourseList();
	
	public ConsoleVersion() throws FileNotFoundException
	{

		Scanner in = new Scanner(System.in);
		CourseOrganizer co = new CourseOrganizer();
		String command;
		String courseName;

		// FUNCTIONS
		String add = "add", edit = "edit", sort = "sort", editAssign = "edit assign", viewAssignDetails = "view assign details", viewAllUnfinAssigns = "view all unfin assigns", viewAllFinAssigns = "view all fin assigns", viewAllAssigns = "view all assigns", viewAll = "view all", view = "view", viewAssignsFrom = "view assigns from", viewUnfinAssigns = "view unfin assigns", viewFinAssigns = "view fin assigns", delete = "delete", saveTo = "save to", save = "save", loadLast = "load last", loadFrom = "load from", assignmentTo = "assignment to", assignmentFin = "assignment fin", assignmentDel = "assignment del", exit = "exit";

		System.out
				.println("What would you like to do? Type /help for a list of commands.");

		while (true)
		{
			command = in.nextLine();

			if (command.equalsIgnoreCase("/help"))
			{
				co.showHelpDialog();
			}

			// ADD****************************************
			else if (command.contains(add))
			{
				courseName = co.checkFunctionValidityAndGetCourseTitle(add,
						command);
				if (courseName.isEmpty())
				{
					continue;
				}
				co.add(courseName);
			}

			// EDIT COMMANDS***************************************
			else if (command.contains(editAssign))
			{
				
			}
			else if (command.contains(edit))
			{
				courseName = co.checkFunctionValidityAndGetCourseTitle(edit,
						command);
				if (courseName.isEmpty())
				{
					continue;
				}
				co.edit(courseName);
			}
			
			// SORT***********************************************
			else if (command.contains(sort))
			{
				courseList = courseList.sort();
				System.out.println("Sort successful.");
			}
			
			// VIEW COMMANDS**************************************
			else if (command.contains(viewAssignDetails))
			{
				courseName = co.checkFunctionValidityAndGetCourseTitle(
						viewAssignDetails, command);
				if (courseName.isEmpty())
				{
					continue;
				}
				co.viewAssignDetails(courseName);
			}
			else if (command.contains(viewAllUnfinAssigns))
			{
				co.viewAllUnfinishedAssignments();
			}
			else if (command.contains(viewAllFinAssigns))
			{
				co.viewAllFinishedAssignments();
			}
			else if (command.contains(viewAllAssigns))
			{
				co.viewAllAssignments();
			}
			else if (command.contains(viewAll))
			{
				co.viewAll();
			}
			else if (command.contains(viewAssignsFrom))
			{
				courseName = co.checkFunctionValidityAndGetCourseTitle(viewAssignsFrom, command);
				if (courseName.isEmpty())
				{
					continue;
				}
				co.viewAssignsFrom(courseName);
			}
			else if (command.contains(viewUnfinAssigns))
			{
				courseName = co.checkFunctionValidityAndGetCourseTitle(viewUnfinAssigns, command);
				if (courseName.isEmpty())
				{
					continue;
				}
				co.viewUnfinAssigns(courseName);
			}
			else if (command.contains(viewFinAssigns))
			{

			}
			// DELETE****************************************************
			else if (command.contains(delete))
			{
				courseName = co.checkFunctionValidityAndGetCourseTitle(delete, command);
				co.delete(courseName);
			}
			// SAVE COMMANDS*********************************************
			else if (command.contains(saveTo))
			{

			}
			else if (command.contains(save))
			{
				String curDir = System.getProperty("user.dir");
				co.save(curDir);
			}
			// LOAD COMMANDS*********************************************
			else if (command.equalsIgnoreCase(loadLast))
			{
				co.loadLast();
			}
			else if (command.equalsIgnoreCase(loadFrom))
			{
				co.loadFrom();
			}
			// ASSIGNMENT COMMANDS***************************************
			else if (command.contains(assignmentTo))
			{
				courseName = co.checkFunctionValidityAndGetCourseTitle(
						assignmentTo, command);
				if (courseName.isEmpty())
				{
					continue;
				}

				co.assignmentTo(courseName);
			}
			else if (command.contains(assignmentFin))
			{
				courseName = co.checkFunctionValidityAndGetCourseTitle(
						assignmentFin, command);
				if (courseName.isEmpty())
				{
					continue;
				}

				co.assignmentFin(courseName);
			}

			else if (command.contains(assignmentDel))
			{
				courseName = co.checkFunctionValidityAndGetCourseTitle(
						assignmentDel, command);
				if (courseName.isEmpty())
				{
					continue;
				}

				co.assignmentDel(courseName);
			}
			else if (command.contains(view))
			{
				courseName = co.checkFunctionValidityAndGetCourseTitle(view,
						command);
				if (courseName.isEmpty())
				{
					continue;
				}
				co.view(courseName);
			}
			// EXIT
			// COMMAND*******************************************************************
			else if (command.equalsIgnoreCase(exit))
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
	
	
}
