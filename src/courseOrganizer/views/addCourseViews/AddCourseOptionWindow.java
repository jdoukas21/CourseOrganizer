package courseOrganizer.views.addCourseViews;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import courseOrganizer.listeners.ButtonMouseListener;
import courseOrganizer.models.CourseList;
import courseOrganizer.views.MainWindow;

// Κλάση που εμφανίζει το μενού για δημιουργία νέου μαθήματος.
public class AddCourseOptionWindow extends JFrame
{
	/**
	 * 
	 */
	private Font DEFAULT_FONT = new Font("Times New Roman", Font.PLAIN, 14);
	private CourseList courseList;
	private MainWindow container;
	
	private JFrame thisFrame = this;
	
	public AddCourseOptionWindow(MainWindow c, CourseList cl)
	{
		super("Add Course");
                
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Dispose on close: κλείνει το παράθυρο αφού εκτελέσει τις κατάλληλες ενέργειες.		courseList = cl;
		courseList = cl;
                this.container = c;
		
                // Το mainPane περιέχει όλα τα κουμπιά.
		JPanel mainPane = new JPanel();
		mainPane.setLayout(new BorderLayout()); // Το BorderLayout τοποθετεί τα components στις θέσεις NORTH, EAST, WEST, SOUTH κ.ο.κ.
		
                //Το centerPane περιέχει τα 3 κουμπιά.
		JPanel centerPane = new JPanel(new GridLayout(3, 1)); // Η διάταξη των κουμπιών θα είναι 3 γραμμές και 1 στήλη.
		
                // Η ετικέτα με τις οδηγίες
		JLabel instructions = new JLabel("<html><div style =\"text-align: center;\">Please choose the type of course"
													+ "<BR>you would like to add.</div style></html>");
		//instructions.setBorder(BorderFactory.createEtchedBorder()); // Ορίζει ένα πλαίσιο γύρω από το κείμενο με τις οδηγίες. Το αφαιρούμε γιατί φαίνεται σαν editable.
		instructions.setFont(DEFAULT_FONT);
		
                //Toποθετεί το κείμενο με τις οδηγίες στο mainPane στη θέση NORTH.
		mainPane.add(instructions, BorderLayout.NORTH);
		
                //Δημιουργεί το κουμπί Detailed Course.
		JButton detailedCourse = new JButton("Detailed Course");
		detailedCourse.setFont(DEFAULT_FONT);
                // Ορίζει σαν ακροατή συμβάντων τη ButtonMouseListener η οποία είναι γενική κλάση που
                // καθορίζει την εμφάνιση όλων των κουμπιών του προγράμματος.
		detailedCourse.addMouseListener(new ButtonMouseListener(detailedCourse));
                // Ορίζει και δεύτερο ακροατή συμβάντων που καθορίζει τις λεπτομέρειες
                // που αφορούν το συγκεκριμένο κουμπί detailed Course.
		detailedCourse.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
                                // Όταν πατηθεί το detailed Course εμφανίζεται το αντίστοιχο παράθυρο.
				new AddDetailedCourseWindow(container, courseList, false);
			}
		});
		
                // Δημιουργεί το κουμπί Simple Course.
		JButton simpleCourse = new JButton("Simple Course");
		simpleCourse.setFont(DEFAULT_FONT);
		simpleCourse.addMouseListener(new ButtonMouseListener(simpleCourse));
		simpleCourse.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new AddSimpleCourse((JFrame)container, courseList);
			}
		});
		
		JButton freeForm = new JButton("Free-form Course");
		freeForm.addMouseListener(new ButtonMouseListener(freeForm));
		freeForm.setFont(DEFAULT_FONT);
		
		centerPane.add(detailedCourse);
		centerPane.add(simpleCourse);
		centerPane.add(freeForm);
		
		centerPane.setBorder(BorderFactory.createLineBorder(mainPane.getBackground(), 5));
		mainPane.add(centerPane, BorderLayout.CENTER);
		
		//BUTTONPANEL -- HOLDS CANCEL BUTTON
		JPanel buttonPanel = new JPanel();
		
		JButton done = new JButton("Done");
		done.setFont(DEFAULT_FONT);
		done.addMouseListener(new ButtonMouseListener(done));
		done.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				container.draw(false);
				thisFrame.dispose();
			}
		});
		
		buttonPanel.add(done);
		
		buttonPanel.setBorder(BorderFactory.createEtchedBorder());
		mainPane.add(buttonPanel, BorderLayout.SOUTH);
		
		mainPane.setBorder(BorderFactory.createLineBorder(mainPane.getBackground(), 5));
		
		this.add(mainPane);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(container);
		setVisible(true);
	}
}