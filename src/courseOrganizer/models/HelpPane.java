package courseOrganizer.models;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class HelpPane extends JFrame
{
        //constructor
        public HelpPane(int pointer)
        {
            super("Course Organizer - Help");
            
            JLabel label = new JLabel();
            
            //Το mode είναι Basic Help
            if (pointer == 0){                
                label.setText("<html> <p> Basic Help text is displayed here! </p></html>");
            
            //To mode είναι Help Topics    
            }else if (pointer == 1){  
                label.setText("<html> <p> Help Topics text is displayed here! </p></html>"); 
                
            // To mode είναι About    
            }else{
                label.setText("<html> <p> About text is displayed here! </p></html>"); 
                
            }  
            
            this.add(label);
        }
    
}
