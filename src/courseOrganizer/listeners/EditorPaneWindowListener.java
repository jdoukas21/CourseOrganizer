package courseOrganizer.listeners;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Font;
import java.awt.Color;

import courseOrganizer.models.notepad.NotePaper;
import courseOrganizer.views.MainWindow;

public class EditorPaneWindowListener implements WindowListener{

        private Font lastNotepaperFont;
        private Color lastNotepaperColor;
    
        private NotePaper notepaper;
        private MainWindow mainWindow;
    
        public EditorPaneWindowListener(MainWindow mw, NotePaper np)
        {
                this.mainWindow = mw;
                this.notepaper = np;
        }
            
        @Override
        public void windowOpened(WindowEvent e) 
        {
                // TODO Auto-generated method stub    
        }

        // Εκετελείται όταν κλείνει το παράθυρο.
        @Override
        public void windowClosing(WindowEvent e) 
        {
                // Παίρνει την τελευταία αποθηκευμένη τιμή από την MainWindow
                lastNotepaperFont = mainWindow.getNotepaperFont();
                lastNotepaperColor = mainWindow.getNotepaperColor();
                    
                // Ορίζει το font στην τελευταία αποθηκευμένη τιμή.
                notepaper.setFont(lastNotepaperFont);
                notepaper.setForeground(lastNotepaperColor);
                
        }

        @Override
        public void windowClosed(WindowEvent e) 
        {
                // TODO Auto-generated method stub
        }

        @Override
        public void windowIconified(WindowEvent e) 
        {
                // TODO Auto-generated method stub    
        }

        @Override
        public void windowDeiconified(WindowEvent e) 
        {
                // TODO Auto-generated method stub    
        }

        @Override
        public void windowActivated(WindowEvent e) 
        {
                // TODO Auto-generated method stub    
        }

        @Override
        public void windowDeactivated(WindowEvent e) 
        {
                // TODO Auto-generated method stub    
        }
    
}
