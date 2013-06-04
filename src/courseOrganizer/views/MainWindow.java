package courseOrganizer.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import courseOrganizer.listeners.MainWindowListener;
import courseOrganizer.lookAndFeel.Fonts;
import courseOrganizer.models.CourseList;
import courseOrganizer.models.LeftToolBar;
import courseOrganizer.models.MenuBar;
import courseOrganizer.models.ScrollPane;
import courseOrganizer.models.notepad.NotePaper;
import courseOrganizer.models.notepad.EditorPane;
import java.awt.Font;

public class MainWindow extends JFrame
{
    
	//DEFAULT COLOR IS PALE GREEN, WHICH IS RGB 152, 251, 152
	private CourseList courseList;
        private NotePaper notepaper;
        private EditorPane editorpane;
        
	private JPanel bigPane;
	private String view; //what the current view or mode is -- can be "Courses", "Assignments", "Notes", or "Notepad"
	
        // Default values για το notepaper
	private String saveString = "Notes";
        private Font noteFont = new Font("Arial", Font.PLAIN, 20);
        private Color noteColor = Color.BLACK;
        
        //Αυτή η μέθοδος είναι ο constructor της κλάσης
	public MainWindow(CourseList cl)
	{
		super("Course Organizer"); //Εμφανίζει το κείμενο στην πάνω αριστερή πλευρά του παραθύρου.
		//COMPONENTS (in order of addition): topPane (MenuBar, view ComboBox), bigPane (left toolbar, main view)
		
                this.getContentPane().setLayout(new BorderLayout());
		
		courseList = cl;
		view = "Courses"; // Default mode = Courses
                		
		this.setSize(new Dimension(800, 700)); //Ορίζει τις διαστάσεις όλου του εξωτερικού παραθύρου.
		
		draw(true); //καλεί τη μέθοδο draw
		
                // Προσθέτει ως ακροατή συμβάντων για το frame τη MainWindowListener
		MainWindowListener mwl = new MainWindowListener(this);
		this.addComponentListener(mwl);
		this.addWindowListener(mwl);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //Λέει στην εφαρμογή να τερματίσει όταν κλείσει το παράθυρο (JFrame)
		
		setLocationRelativeTo(null); // Ορίζει το παράθυρο (JFrame) στο κέντρο της οθόνης
		setVisible(true); // Κάνει το παράθυρο ορατό
	}
	
	public JPanel topPanel(String selectedItem)
	{
                // Το topPane είναι αυτό το component που περιέχει τη dropdown list με τις επιλογές "courses", "assignment", "notebook", "notepaper"
		JPanel topPane = new JPanel();
		topPane.setLayout(new BoxLayout(topPane, BoxLayout.Y_AXIS));
		
                //Δημιουργία του component "μενού"
		MenuBar mb = new MenuBar(this);
		mb.setMaximumSize(new Dimension(this.getMaximumSize().width, 50));
		

		final JPanel panel = new JPanel();
		
                //Δημιουργία του component "view:"
		JLabel views = new JLabel("<html><B>View: </B></html>");
		views.setFont(Fonts.DEFAULT_FONT);
		
		              
                //Δημιουργία της dropdown λίστας. Αυτά είναι απλά η εικόνα. Δηλαδή τι θα εμφανίζεται σαν κείμενο.
		String[] viewTypes = {"Courses", "Assignments", "Notepaper", "Notebook"};
		
		final JComboBox viewComboBox = new JComboBox (viewTypes);
		viewComboBox.setFont(Fonts.DEFAULT_FONT);
		viewComboBox.setSelectedItem(selectedItem);
                viewComboBox.setFont(new Font("Arial", Font.ITALIC, 15)); //Ορίζει πως θα φαίνονται τα στοιχεία της dropdown λίστας, γραμματοσειρά, μέγεθος, στυλ
		
                // Ανώνυμη εσωτερική κλάση για το χειρισμό συμβάντων
                viewComboBox.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				switch (viewComboBox.getSelectedIndex())
				{
					case 0: // Αν το selectedIndex είναι 0 (το πρώτο στοιχείο του πίνακα viewComboBox είναι το Courses), τότε θέτει το mode σε Courses (view = courses)
						view = "Courses"; // Το Courses εδώ δηλώνει το mode. Δεν είναι απλά κείμενο. 
						break;
					case 1:
						view = "Assignments";
						break;
					case 2:
						view = "Notepaper";
						break;
					case 3: 
						view = "Notebook";
						break;
					default:
						view = "Courses";
				}
				draw(false);
			}
		});
		//viewComboBox.setMaximumSize(new Dimension(50,20));
		
                //To JPanel "panel" περιέχει τα components "view:" και "dropdown λίστας"
		panel.add(views); 
		panel.add(viewComboBox);
		
		panel.setMaximumSize(new Dimension(this.getMaximumSize().width, 50));
		topPane.add(mb); //Προσθέτει το "μενού" στο "topPane"
		topPane.add(panel); //Προσθέτει το "panel" στο "topPane". Το ότι το "μενού" είναι γραμμένο πρώτο σημαίνει ότι θα εμφανιστεί πάνω από το "panel"
		
		return topPane; 
	}
	
        /* Αυτή η μέθοδος εμφανίζει το περιεχόμενο (αριστερό toolbar και πεδίο περιεχομένων)
         * δυναμικά, ανάλογα την επιλογή από τη dropdown λίστας. Αυτό φαίνεται από το όρισμα 
         * που παίρνει, που είναι τύπου boolean. Στο τέλος της μεθόδου καλείται η μέθοδος this.validate()
         * η οποία ανανεώνει το περιεχόμενο του JFrame. Για να ανανεωθεί θα πρέπει να καλέσουμε αρχικά
         * την this.invalidate() και μετά τη validate(). Γι αυτό και ο constructor καλεί τη draw() με τιμή 
         * true. Εναλλακτικά υπάρχουν η repaint() ή frame.repaint() ή this.repaint()*/
	public void draw(boolean beginning)
	{	
                /* Παίρνει το περιεχόμενο και το αφαιρεί. Για παράδειγμα αν η επιλογή ήταν courses 
                 * και τώρα είναι assignments, θα πάρει το περιεχόμενο του courses και θα το "πετάξει" */
		this.getContentPane().removeAll();
                //Δημιουργία του "μεγάλου παραθύρου". Το μεγάλο παράθυρο, περιέχει το toolbar στα αριστερά και το παράθυρο που θα εμφανίζεται το περιεχόμενο
		bigPane = new JPanel(); //δημιουργία αντικειμένου JPanel
		bigPane.setLayout(new BoxLayout(bigPane, BoxLayout.X_AXIS)); //Οριζει ότι τα components θα είναι διατεταγμένα από τα αριστερά προς τα δεξιά (X_AXIS)
		
                //Δημιουργία του component "αριστερό toolbar"
		LeftToolBar leftToolBar = new LeftToolBar(this, courseList);
		leftToolBar.setViewMode(view); // Ανάλογα το mode θα αλλάξει και η λειτουργικότητα του αριστερού toolbar
		leftToolBar.setMaximumSize(new Dimension((this.getSize().width * 9) / 100, this.getSize().height)); //Ορίζει το μέγεθος του αριστερού toolbar
		leftToolBar.resizeComponents();
		leftToolBar.setPreferredSize(new Dimension (110, 130));// Έτσι ορίζουμε τις διαστάσεις ενός component
		bigPane.add(leftToolBar); //Προσθήκη του "αριστερού toolbar" στο "μεγάλο παράθυρο"
		
                //Δημιουργία αντικειμένου "scrollPane" από την αντίστοιχη κλάση. Αυτό το component θα χρησιμοποιούν για να εμαφνίζεται το περιεχόμενο από όλα τα modes.
		ScrollPane scrollPane = new ScrollPane(this, courseList, "Courses"); //default mode = "courses"
	
		/* Ανάλογα την επιλογή mode από τη dropdown λίστα, ανανεώνεται και το περιεχόμενο του scrollPane*/
		if (view.equals("Courses"))
		{
			scrollPane = new ScrollPane(this, courseList, "Courses");
		}
		else if (view.equals("Assignments"))
		{
			scrollPane = new ScrollPane(this, courseList, "Assignments");
		}
		else if (view.equals("Notebook"))
		{
			//*notebook view will use a JList to display a list of notes (if existent)
			scrollPane = new ScrollPane(this, courseList, "Notebook");
		}
		
		if (view.equals("Notepaper")) //this case has to be here in order for the notepaper sizing to work properly
		{	
			scrollPane.setMaximumSize(new Dimension(this.getSize().width - leftToolBar.getMaximumSize().width, this.getSize().height));
			scrollPane.displayNotes(saveString); // Καλείται η μέθοδος displayNotes της κλάσης ScrollPane και περνάει ως όρισμα το saveString για να εμφανίσει τις σημειώσεις.
		}
		else
		{
			scrollPane.setMaximumSize(new Dimension(this.getSize().width - leftToolBar.getMaximumSize().width, this.getSize().height));
		}		
		bigPane.add(scrollPane); //Προσθήκη του "ScrollPane" στο "μεγάλο παράθυρο"           
		//scrollPanel = scrollPane;
		
		
		this.getContentPane().add(topPanel(view), BorderLayout.NORTH); //Ορίζει την τοποθεσία του "μενού επιλογών" (topPane)
		this.getContentPane().add(bigPane, BorderLayout.CENTER); //Ορίζει την τοποθεσία του "παραθύρου περιεχομένων" (BigPane)
		// Την παραπάνω ιδιότητα την έχουν τα topPane και BigPane διότι είναι αντικείμενα JPanel
		
		
		if (!beginning)
		{
			this.validate();  //Ανανεώνει το περιεχόμενο της σελίδας
		}
	}
	
        /* Καλείται από τη NotepaperListener. Κάθε φορά που ο χρήστης πληκτρολογεί κάτι
         * στο σημειωματάριο (Notepaper), αυτό αποθηκεύεται σε μια string μεταβλητή*/
	public void setSaveString(String string)
	{
		saveString = string;
	}
        
        public String getSaveString()
        {
                return saveString;
        }
        
        public CourseList getCourseList()
        {
                return courseList;
        }
        
        public void setNotePaper(NotePaper np)
        {
                notepaper = np;
        }
        
        public NotePaper getNotePaper()
        {
                return notepaper;
        }
        
        public void setEditorPane(EditorPane ep)
        {
                editorpane = ep;
        }
        
        public EditorPane getEditorPane()
        {
                return editorpane;
        }
        
        public void setNotepaperFont(Font ft)
        {
                noteFont = ft;
        }
        
        public Font getNotepaperFont()
        {
                return noteFont;
        }
        
        public void setNotepaperColor(Color col)
        {
                noteColor = col;
        }
        
        public Color getNotepaperColor()
        {
                return noteColor;
        }

	public void changeView(String view)
	{
		this.view = view;
		draw(false);
	}
}
