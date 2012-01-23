package courseOrganizer;

import java.awt.FontFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import courseOrganizer.views.MainWindow;

public class Main
{
	public static void main(String[] args) throws FileNotFoundException, IOException, FontFormatException
	{
		Scanner in = new Scanner(System.in);
		
		//NotePaper np = new NotePaper();
		CourseOrganizer co = new CourseOrganizer();
		
		MainWindow mainWindow = new MainWindow(co.getCourseList());
		
		/*System.out.println("Would you like to use the console version or window version?");
		if (in.nextLine().contains("console"))
		{
			ConsoleVersion cv = new ConsoleVersion();
		}
		else
		{
			Window mainWindow = new Window();
		}*/
	}
}
