package courseOrganizer.models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.Color;
import java.awt.Font;

import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import courseOrganizer.lookAndFeel.Fonts;
import courseOrganizer.views.MainWindow;
import courseOrganizer.models.notepad.EditorPane;
import courseOrganizer.utilities.CreateSerializableFile;
import courseOrganizer.utilities.OpenSerializableFile;
import courseOrganizer.utilities.OpenLastSavedPath;
import courseOrganizer.CourseOrganizer;

//PALE YELLOW: 255, 240, 98

public class MenuBar extends JMenuBar
{
	private MainWindow mainWindow;
        private CourseList courseList;
        private EditorPane editorpane;
        
        private JFileChooser fileChooser;
        private static File filepath;
        
        private int pointer;
        private String notePaperString;
        private Font notePaperFont;
        private Color notePaperColor;
	
	public MenuBar(MainWindow mw)
	{
		mainWindow = mw;
		/*UIManager.put("Menu.selectionBackground", Colors.PALE_YELLOW);
		UIManager.put("MenuItem.selectionBackground", Colors.PALE_YELLOW);
		UIManager.put("CheckBoxMenuItem.selectionBackground", Colors.PALE_YELLOW);*/
		this.setBorder(BorderFactory.createEtchedBorder());
		
		// FILE
		// MENU******************************************************************************
		JMenu file = new JMenu("File");
		file.setFont(Fonts.DEFAULT_FONT);

		JMenuItem[] fileActions = new JMenuItem[6];

		fileActions = initializeMenuItems(fileActions);
		file = addItemsToMenu(fileActions, file);

		fileActions[0].setText("New");
		fileActions[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
                fileActions[0].addActionListener(new ActionListener()
                {
                        @Override
                        public void actionPerformed(ActionEvent arg0)
                        {
                                // Δημιουργεί νέο αντικέιμενο CourseOrganizer
                                CourseOrganizer co = new CourseOrganizer();
                                // Ανοίγει νέο παράθυρο
                                MainWindow mw = new MainWindow(co.getCourseList());
                        }
                });

		fileActions[1].setText("Open Last");
		fileActions[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
                fileActions[1].addActionListener(new ActionListener()
                {
                        @Override
                        public void actionPerformed(ActionEvent arg0)
                        {
                                // Παίρνει το filepath από το αρχείο LastSavedPath
                                OpenLastSavedPath olsp = new OpenLastSavedPath();
                                olsp.openFile();
                                filepath = olsp.ReadRecordsFromFile();
                                olsp.closeFile();
                                
                                // Ανοίγει το αρχείο
                                OpenSerializableFile osf = new OpenSerializableFile();
                                osf.openFile(filepath);
                                osf.ReadRecordsFromFile();
                                osf.closeFile();
                                
                                // Κλείνει το παλιό παράθυρο
                                mainWindow.dispose();
                        }
                });

		fileActions[2].setText("Open File...");
		fileActions[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
                fileActions[2].addActionListener(new ActionListener()
                {
                        @Override
                        public void actionPerformed(ActionEvent arg0)
                        {
                                fileChooser = new JFileChooser(System.getProperty("user.dir"));
                                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                                fileChooser.showOpenDialog(null);
                                
                                filepath = fileChooser.getSelectedFile();
                                
                                OpenSerializableFile osf = new OpenSerializableFile();
                                osf.openFile(filepath);
                                osf.ReadRecordsFromFile();
                                osf.closeFile();
                                
                                // Όταν ο χρήστης κάνει load ένα αποθηκευμένο αρχείο, αυτό ανοίγει σε νέο παράθυρο.
                                // Έτσι με την παρακάτω εντολή κλείνει το παλιό παράθυρο.
                                mainWindow.dispose(); 
                        }
                });

                // Αν το filepath είναι null σημαίνει ότι δεν έχει αποθηκευτεί ή ανοιχτεί κάποιο αρχείο και η επιλογή Save δεν έχει νόημα να υπάρχει.
                fileActions[3].setText("Save");
                fileActions[3].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
                if (filepath != null){
                        fileActions[3].addActionListener(new ActionListener()
                        {
                                @Override
                                public void actionPerformed(ActionEvent arg0)
                                {
                                        courseList = mainWindow.getCourseList();
                                        notePaperString = mainWindow.getSaveString();
                                        notePaperFont = mainWindow.getNotepaperFont();
                                        notePaperColor = mainWindow.getNotepaperColor();
                                
                                        if (filepath == null)
                                        {
                                        }
                                        else
                                        {
                                                CreateSerializableFile csf = new CreateSerializableFile();
                                                csf.openFile(filepath);
                                                csf.WriteToFile(courseList, notePaperString, notePaperFont, notePaperColor, MenuBar.this);
                                                csf.closeFile();

                                                JOptionPane.showMessageDialog(null, "File Saved Successfully");
                                        }
                                }
                        });
                }
                else
                {
                        // Αδρανοποιεί την επιλογή Save.
                        fileActions[3].setEnabled(false);
                }

		fileActions[4].setText("Save as...");
                fileActions[4].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK));
                fileActions[4].addActionListener(new ActionListener()
                {
                        @Override
                        public void actionPerformed(ActionEvent arg0)
                        {
                                // Δημιουργία JFileChooser με default directory
                                fileChooser = new JFileChooser(System.getProperty("user.dir"));
                                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                                // Εμφανίζει το παράθυρο
                                int result = fileChooser.showSaveDialog(null);
                                
                                // Ο παρακάτω κώδικας εκτελείται μόνο αν πατηθεί το Save στον JFileChooser
                                if (result != JFileChooser.CANCEL_OPTION)
                                {
                                        // Παίρνουμε το path
                                        filepath = fileChooser.getSelectedFile();
                                        // Παίρνουμε την courselist, το String, το Font και το Color του NotePaper
                                        courseList = mainWindow.getCourseList();
                                        notePaperString = mainWindow.getSaveString();
                                        notePaperFont = mainWindow.getNotepaperFont();
                                        notePaperColor = mainWindow.getNotepaperColor();
                                        // Δημιουργεί ένα νέο αρχείο
                                        CreateSerializableFile csf = new CreateSerializableFile();
                                        csf.openFile(filepath);
                                        csf.WriteToFile(courseList, notePaperString, notePaperFont, notePaperColor, MenuBar.this);
                                        csf.closeFile();
                                        
                                        JOptionPane.showMessageDialog(null, "File Saved Successfully");
                                }
                        }
                });

		fileActions[5].setText("Exit");
		fileActions[5].addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);				
			}
		});

		// EDIT
		// MENU***************************************************************************
		JMenu edit = new JMenu("Edit");
		edit.setFont(Fonts.DEFAULT_FONT);

		JMenuItem[] editActions = new JMenuItem[2];
		
		editActions = initializeMenuItems(editActions);
		edit = addItemsToMenu(editActions, edit);
		
		editActions[0].setText("Undo");
		editActions[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				KeyEvent.CTRL_DOWN_MASK));

		editActions[1].setText("Redo");
		editActions[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
				KeyEvent.CTRL_DOWN_MASK));

		// VIEW
		// MENU*****************************************************************************
		JMenu view = new JMenu("View");
		view.setFont(Fonts.DEFAULT_FONT);

		JMenuItem[] viewActions = new JMenuItem[6];

		viewActions = initializeMenuItems(viewActions);
		view = addItemsToMenu(viewActions, view);
		
		viewActions[0].setText("Courses");
		viewActions[0].addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainWindow.changeView("Courses");				
			}
		});
		
		viewActions[1].setText("Assignments");
		viewActions[2].setText("Notepaper");
		viewActions[3].setText("Notebook");
		viewActions[4].setText("Schedule");
		viewActions[5].setText("Advanced...");
		
		//TOOLS  
                //MENU************************************************************************
		JMenu tools = new JMenu("Tools");
		tools.setFont(Fonts.DEFAULT_FONT);
		
		JMenuItem[] toolsActions = new JMenuItem[2];
		
		toolsActions = initializeMenuItems(toolsActions);
		tools = addItemsToMenu(toolsActions, tools);
                
                //H επιλογή tools-->Editor, θα περιέχει τα fonts για το notepaper.
                toolsActions[0].setText("Editor");
                // Αν ο editor έχει αρχικοποιηθεί πρόσθεσε τον ακροατή συμβάντων
                editorpane = mainWindow.getEditorPane(); // Παίρνει την τιμή του editorpane από τη MainWindow
                if (editorpane != null)
                {
                    toolsActions[0].addActionListener(new ActionListener()
                    {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                    editorpane.displayEditor();
                                    editorpane.setSize(250,250);
                                    editorpane.setVisible(true);
                                    editorpane.setLocationRelativeTo(null);
                                    editorpane.setResizable(false);
                            }
                    });
                }
                else{
                    toolsActions[0].setEnabled(false);
                }
           
		
		toolsActions[1].setText("Options");

		//HELP 
                //MENU**************************************************************************
		JMenu help = new JMenu("Help");
		help.setFont(Fonts.DEFAULT_FONT);
		
		JMenuItem[] helpActions = new JMenuItem[3];
		
		helpActions = initializeMenuItems(helpActions);
		help = addItemsToMenu(helpActions, help);
		
		helpActions[0].setText("Basic Help");
                helpActions[0].addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed (ActionEvent e)
                    {
                        pointer = 0; //Αποθηκεύει τη θέση του πίνακα. Δηλαδή το mode ώστε ο constructor της HelpPane να μπορέσει να κάνει το διαχωρισμό.
                        HelpPane helppane = new HelpPane(pointer);
                        helppane.setSize(275, 100);
                        helppane.setResizable(false); // Το παράθυρο δεν αλλάζει διαστάσεις
                        helppane.setLocationRelativeTo(null);
                        helppane.setVisible(true);
                    }
                });
                
		helpActions[1].setText("Help Topics");
                helpActions[1].addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed (ActionEvent e)
                    {
                        pointer = 1;
                        HelpPane helppane = new HelpPane(pointer);
                        helppane.setSize(275, 100);
                        helppane.setResizable(false); // Το παράθυρο δεν αλλάζει διαστάσεις
                        helppane.setLocationRelativeTo(null);
                        helppane.setVisible(true);
                    }
                });
                
		helpActions[2].setText("About");
                helpActions[2].addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed (ActionEvent e)
                    {
                        pointer = 2;
                        HelpPane helppane = new HelpPane(pointer);
                        helppane.setSize(275, 100);
                        helppane.setResizable(false); // Το παράθυρο δεν αλλάζει διαστάσεις
                        helppane.setLocationRelativeTo(null);
                        helppane.setVisible(true);
                    }
                });

		this.add(file);
		this.add(edit);
		this.add(view);
		this.add(tools);
		this.add(help);
		
		//this.setBackground(new Color(152, 251, 152));
	}

        /* Η μέθοδος αυτή εκχωρεί τις επιλογές σε έναν πίνακα. */
	public JMenuItem[] initializeMenuItems(JMenuItem[] array)
	{
		int length = array.length;

		for (int n = 0; n < length; n++)
		{
			array[n] = new JMenuItem();
			array[n].setFont(Fonts.DEFAULT_FONT);
		}
		return array;

	}
	
        /* Η μέθοδος αυτή συνδέει τα κουμπιά του μενού με τις αντίστοιχες επιλογές.*/
	public JMenu addItemsToMenu(JMenuItem[] array, JMenu menu)
	{
		for (int n = 0; n < array.length; n ++)
		{
			menu.add(array[n]);
		}
		return menu;
	}
        
        public static void setFilePath(File fp)
        {   
                filepath = fp;
        }
        
}
