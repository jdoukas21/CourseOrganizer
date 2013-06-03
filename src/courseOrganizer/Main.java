package courseOrganizer;

import java.awt.FontFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import courseOrganizer.views.MainWindow;

/* H main ουσιαστικά καλεί τη μέθοδο getCourseList() που βρίσκεται στην κλάση
 * courseOrganizer και το αποτέλεσμα θα εμφανιστεί στο mainWindow.
 */
public class Main
{
	public static void main(String[] args) throws FileNotFoundException, IOException, FontFormatException
	{
		Scanner in = new Scanner(System.in);
		
		//NotePaper np = new NotePaper();
		CourseOrganizer co = new CourseOrganizer();
		
		MainWindow mainWindow = new MainWindow(co.getCourseList());
                /* Με τη δημιουργία αντικειμένου mainWindow καλείται ο constructor
                 * της κλάσης MainWindow στον οποίο δίνουμε ως όρισμα τη λίστα
                 * των μαθημάτων, την οποίο έχουμε πάρει από τη μέθοδο getCourseList 
                 * της κλάσης CourseOrganizer*/
                
		
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
