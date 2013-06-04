package courseOrganizer.models.notepad;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import courseOrganizer.listeners.EditorPaneWindowListener;
import courseOrganizer.views.MainWindow;
import javax.swing.ImageIcon;


public class EditorPane extends JFrame{
    
        // Η παρακάτω μεταβλητή θα χρησιμοποιηθεί για την προσωρινή αποθήκευση των αλλαγών στο Font
        private Font applyNotepaperFont;

        private String size[] = {"10", "12", "15", "18", "20", "25", "30", "35", "40", "45", "50", "60"};
        private int sizeToInteger; 
        
        private String typeface[] = {"Arial", "Helvetica", "Serif", "Calibri", "Times New Roman", "Bradley Hand ITC", "Forte", "Tahoma", "Verdana"};
        private String typefont;
        
        private Color colorface[] = {Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW, Color.WHITE};
        private String colorfaceString[] = {"Black", "Blue", "Cyan", "Gray", "Green", "Magenta", "Orange", "Pink", "Red", "Yellow", "White"};
        private int applyColorPosition; 
        
        private JCheckBox bold  = new JCheckBox("Bold");;
        private JCheckBox italic = new JCheckBox("Italic");;
        private JComboBox sizeDropDown = new JComboBox(size);
        private JComboBox fontDropDown = new JComboBox(typeface);
        private JComboBox colorDropDown = new JComboBox(colorfaceString);
        
        private ImageIcon OK_ICON = new ImageIcon("button_ok.png");
        private ImageIcon CANCEL_ICON = new ImageIcon("button_x.png");
        private JButton OKButton = new JButton("OK", OK_ICON);
        private JButton CancelButton = new JButton("Cancel", CANCEL_ICON);
        
        private JPanel stylePanel = new JPanel(); 
        private JPanel fontPanel = new JPanel();
        private JPanel sizeColorPanel = new JPanel();
        private JPanel OkCancelPanel = new JPanel(new GridLayout(1,2));
                
        private NotePaper notepaper; 
        private MainWindow mainWindow;
                
        // constructor
        public EditorPane(MainWindow mw, NotePaper np)
        {
                super("Notepaper Editor");
                
                mainWindow = mw;
                notepaper = np;
                
                // Στην "προσωρινή" μεταβλητή εκχωρούμε τη "μονιμη" που βρίσκεται στην κλάση MainWindow
                applyNotepaperFont = mainWindow.getNotepaperFont();
            
                // Προσθέτει ακροατή συμβάντων για το παράθυρο.
                this.addWindowListener(new EditorPaneWindowListener(mainWindow, notepaper));
                 
        }
        
        // Καθορίζει το πως θα φαίνονται τα στοιχεία του Editor
        public void displayEditor()
        {
                // Οριζουμε την διάταξη των components.
                this.setLayout(new GridLayout(4,1));
                
        // Ανάλογα την τελευταία αποθηκευμένη τιμή, προεπιλέγουμε τ ααντίστοιχα πεδία-----------------------------
                applyNotepaperFont = mainWindow.getNotepaperFont();
                
                // Αν το τελευταία αποθηκευμένο στυλ είναι bold τότε όταν ανοίξει ο Editor η επιλογή να είναι τικαρισμένη.
                if (applyNotepaperFont.getStyle() == Font.BOLD)
                    bold.setSelected(true);
                else if (applyNotepaperFont.getStyle() == Font.ITALIC)
                    italic.setSelected(true);
                else if (applyNotepaperFont.getStyle() == Font.ITALIC + Font.BOLD){
                    bold.setSelected(true);
                    italic.setSelected(true);
                }
                else{
                    bold.setSelected(false);
                    italic.setSelected(false);
                }
                
                // Στη μεταβλητή typefont εκχωρείται η τελευταία αποθηκευμένη τιμή για το είδος της γραμματοσειράς.
                typefont = applyNotepaperFont.getFamily();
                for (int i=0; i<typeface.length; i++)
                {
                        if(typefont.equals(typeface[i]))
                        {
                                fontDropDown.setSelectedIndex(i);
                        }
                }
                
                // Στη μεταβλητή sizeToInteger εκχωρείται η τελευταία αποθηκευμένη τιμή για το μέγεθος του κειμένου.
                sizeToInteger = applyNotepaperFont.getSize();
                for (int i=0; i<size.length; i++)
                {
                        if(sizeToInteger == Integer.parseInt(size[i]))
                        {
                                sizeDropDown.setSelectedIndex(i);
                        }
                }
                
                // Παίρνει το χρώμα από τη MainWindow και το αντιστοιχίζει στη θέση του πίνακα με τα χρώματα
                for (int i=0; i<colorface.length; i++)
                {
                        if (mainWindow.getNotepaperColor().equals(colorface[i]))
                        {
                                applyColorPosition = i;
                        }
                }
                
                // Ορίζει σε επιλεγμένη την τιμή την οποία έχει τώρα το χρώμα.
                colorDropDown.setSelectedIndex(applyColorPosition);
                
        //----------------------------------------------------------------------------------------------------------     
        //Style-------------------------------------------------------------------
                stylePanel.setBorder(BorderFactory.createTitledBorder(null, "Style"));
                
                // Bold checkbox
                stylePanel.add(bold);
                
                // Italic checkbox
                stylePanel.add(italic);
                
                this.add(stylePanel);
        //---------------------------------------------------------------------------
        //font-----------------------------------------------------------------------
                fontPanel.setBorder(BorderFactory.createTitledBorder(null, "Font"));
                                
                // DropDown list για τη γραμματοσειρά του κειμένου 
                fontDropDown.setMaximumRowCount(5);
                fontPanel.add(fontDropDown);
                
                this.add(fontPanel);
        //---------------------------------------------------------------------------
        //size-----------------------------------------------------------------------              
                sizeColorPanel.setBorder(BorderFactory.createTitledBorder(null, "Size & Color"));
                
                // DropDown list για το μέγεθος του κειμένου
                sizeDropDown.setMaximumRowCount(7);
                sizeDropDown.setMaximumSize(new Dimension(80, 80));
                sizeColorPanel.add(sizeDropDown);
                
        //---------------------------------------------------------------------------
        //Color----------------------------------------------------------------------        
                                
                // DropDown list για το χρώμα της γραμματοσειράς
                colorDropDown.setMaximumRowCount(5);
                sizeColorPanel.add(colorDropDown);
                
                this.add(sizeColorPanel);
        //---------------------------------------------------------------------------
        //OK & Cancel----------------------------------------------------------------
                                
                // ΟΚ button
                OKButton.setMaximumSize(new Dimension(25, 25));
                OkCancelPanel.add(OKButton);
                
                //Cancel button
                CancelButton.setMaximumSize(new Dimension(25, 25));
                OkCancelPanel.add(CancelButton);
                
                this.add(OkCancelPanel);
        //-----------------------------------------------------------------------------
                
                // Προσθέτουμε τους ακροατές συμβάντων στα components. 
                OKButtonHandler OKhandler = new OKButtonHandler();
                OKButton.addActionListener(OKhandler);
                
                CancelButtonHandler Cancelhandler = new CancelButtonHandler();
                CancelButton.addActionListener(Cancelhandler);
                
                CheckBoxListener listener = new CheckBoxListener();
                bold.addItemListener(listener);
                italic.addItemListener(listener);
                
                SizeComboBoxListener SCBListener = new SizeComboBoxListener();
                sizeDropDown.addItemListener(SCBListener);
                
                FontComboBoxListener FCBListener = new FontComboBoxListener();
                fontDropDown.addItemListener(FCBListener);
                
                ColorComboBoxListener CCBListener = new ColorComboBoxListener();
                colorDropDown.addItemListener(CCBListener);
        }
        
        private class OKButtonHandler implements ActionListener
        {
                // Εκτελείται όταν πατιέται το ΟΚ.
                @Override
                public void actionPerformed(ActionEvent e)
                {
                        mainWindow.setNotepaperFont(applyNotepaperFont);
                        mainWindow.setNotepaperColor(colorface[applyColorPosition]);
                                                         
                        EditorPane.super.dispose(); // Κλείνει το παράθυρο.
                }
        }
        
        private class CancelButtonHandler implements ActionListener
        {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                        // Επαναφέρει τις τελευταίες αλλαγές
                        notepaper.setFont(mainWindow.getNotepaperFont());
                        notepaper.setForeground(mainWindow.getNotepaperColor());
                        
                        //Κλείνει το παράθυρο
                        EditorPane.super.dispose();
                }
        }
        
        private class CheckBoxListener implements ItemListener
        {
                //Εκτελείται όταν ο χρήστης επιλέγει μία από τις επιλογές.
                @Override
                public void itemStateChanged(ItemEvent event)
                {
                        if (bold.isSelected() && italic.isSelected())
                        {
                                applyNotepaperFont = new Font(typefont, Font.BOLD + Font.ITALIC, sizeToInteger);
                                displayFont();
                        }    
                        else if (bold.isSelected())
                        {
                                applyNotepaperFont = new Font(typefont, Font.BOLD, sizeToInteger);
                                displayFont();
                        }
                        else if (italic.isSelected())
                        {
                                applyNotepaperFont = new Font(typefont, Font.ITALIC, sizeToInteger);
                                displayFont();
                        }
                        else
                        {
                                applyNotepaperFont = new Font(typefont, Font.PLAIN, sizeToInteger);
                                displayFont();
                        }
                                
                }
        }
        
        private class SizeComboBoxListener implements ItemListener
        {
                @Override
                public void itemStateChanged(ItemEvent event)
                {
                        if (event.getStateChange() == ItemEvent.SELECTED)
                        {
                                // Μετατρέπει το String του ComboBox σε int
                                sizeToInteger = Integer.parseInt(size[sizeDropDown.getSelectedIndex()]);
                                applyNotepaperFont = new Font(typefont, applyNotepaperFont.getStyle(), sizeToInteger);
                                displayFont();
                        }
                }
        }
        
        private class FontComboBoxListener implements ItemListener
        {
                @Override
                public void itemStateChanged(ItemEvent event)
                {
                        if (event.getStateChange() == ItemEvent.SELECTED)
                        {
                                typefont = typeface[fontDropDown.getSelectedIndex()];
                                applyNotepaperFont = new Font(typefont, applyNotepaperFont.getStyle(), sizeToInteger);
                                displayFont();
                        }
                }
        }
        
        private class ColorComboBoxListener implements ItemListener
        {
                @Override
                public void itemStateChanged(ItemEvent event)
                {
                        // Παίρνουμε τη θέση του στοιχείου για να το αντιστοιχίσουμε στον πίνακα με τα χρώματα
                        if (event.getStateChange() == ItemEvent.SELECTED)
                        {
                                applyColorPosition = colorDropDown.getSelectedIndex();
                                displayFont();
                        }
                }
        }
        
        // Χρησιμοποιείται για την προσωρινή επίδειξη των αλλαγών στον editor.
        public void displayFont()
        {
                try{
                    notepaper.setFont(applyNotepaperFont);
                    notepaper.setForeground(colorface[applyColorPosition]);
                }
                catch(NullPointerException exc){
                    JOptionPane.showMessageDialog(null, "You have to set the mode\n to Notepaper in order to\n use the Editor");
                }
        }








}
