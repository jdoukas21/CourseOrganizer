package courseOrganizer.utilities;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JOptionPane;

import courseOrganizer.models.CourseList;
import courseOrganizer.models.MenuBar;

public class CreateSerializableFile {
    
        private File filepath; // Περιέχει το path
        private MenuBar menubar;
        private ObjectOutputStream output1; // Μεταβλητή που χρησιμοποιείται για εγγραφή στο αρχείο
        private ObjectOutputStream output2; // Χρησιμοποιείται για την εγγραφή του path σε κάποιο αρχείο ώστε να μπορέσουμε να το πάρουμε όταν ο χρήστης πατήσει open last.
    
        private CourseList courseList;
        private String notePaperString;
        private Font notePaperFont;
        private Color notePaperColor;
        
        private Object[] components = new Object[4];
    
        public void openFile(File fl)
        {
                this.filepath = fl;
                try{
                        output1 = new ObjectOutputStream(new FileOutputStream(filepath));
                        output2 = new ObjectOutputStream(new FileOutputStream("LastSavedPath.ser"));
                }
                catch(IOException exc){
                        JOptionPane.showMessageDialog(null, "File does not exist");
                }
        }
        
        public void WriteToFile(CourseList cl, String str, Font ft, Color col, MenuBar mb)
        {
                menubar = mb;
            
                courseList = cl;
                notePaperString = str;
                notePaperFont= ft;
                notePaperColor = col;
                
                components[0] = courseList;
                components[1] = notePaperString;
                components[2] = notePaperFont;
                components[3] = notePaperColor;
                
                try{
                       output1.writeObject(components);
                       
                       // Στο αρχείο LastSavedPath αποθηκεύει το path
                       output2.writeObject(filepath);
                       
                       menubar.setFilePath(filepath);
                       
                }
                catch(IOException exc){
                        JOptionPane.showMessageDialog(null, "Error writting to file");
                }
        }
        
        public void closeFile()
        {
                try{
                    if (output1 != null)
                        output1.close();
                    if (output2 != null)
                        output2.close();
                }
                catch(IOException exc){
                        JOptionPane.showMessageDialog(null, "Error closing file");
                }
        }

}
