package courseOrganizer.utilities;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.File;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;

import courseOrganizer.models.CourseList;
import courseOrganizer.CourseOrganizer;
import courseOrganizer.views.MainWindow;
import courseOrganizer.models.MenuBar;

public class OpenSerializableFile {
        
        private File filepath; // Περιέχει το path
        private MenuBar menubar;
        private ObjectInputStream input;
        
        private CourseList courseList;
        private String notePaperString;
        private Font notePaperFont;
        private Color notePaperColor;
        
        private Object components[] = new Object[4];
        
        public void openFile(File fp)
        {
                filepath = fp;
                try{
                        input = new ObjectInputStream(new FileInputStream(filepath));
                }
                catch(IOException exc)
                {
                        JOptionPane.showMessageDialog(null, "File does not exist");
                }
        }
        
        public void ReadRecordsFromFile()
        {
                try{
                    while(true)
                    {
                           components = (Object[]) input.readObject();
                           
                           courseList = (CourseList) components[0];
                           notePaperString = (String) components[1];
                           notePaperFont = (Font) components[2];
                           notePaperColor = (Color) components[3];

                    }
                
                }
                // Αυτό το catch εκετελείται όταν τελειώσει η ανάγνωση του αρχείου. EOF=End-of-file
                catch(EOFException exc){
                         //Δημιουργεί νέο courseOrganizer με την αποθηκευμένη courseList
                        CourseOrganizer co = new CourseOrganizer();
                        // Ορίζει την τιμή του αποθηκευμένου courseList
                        co.setCourseList(courseList);
                        // To εμφανίζει σε νέο παράθυρο
                        MainWindow mw = new MainWindow(co.getCourseList());
                        // Βάζει τις αποθηκευμένες τιμές του αχείου για το NotePaper 
                        mw.setSaveString(notePaperString);
                        mw.setNotepaperFont(notePaperFont);
                        mw.setNotepaperColor(notePaperColor);
                        
                        menubar.setFilePath(filepath);
                        
                        return; // Το αρχείο έφτασε στο τέλος του
                }
                catch(IOException exc){
                        JOptionPane.showMessageDialog(null, "Error during reading from file");
                }
                catch(ClassNotFoundException exc){
                        JOptionPane.showMessageDialog(null, "Unable to create object");
                }               
        }
        
        public void closeFile()
        {
                try{
                    if (input != null)
                        input.close();
                }
                catch(IOException exc){
                        JOptionPane.showMessageDialog(null, "Error closing file");
                }
        }

}
