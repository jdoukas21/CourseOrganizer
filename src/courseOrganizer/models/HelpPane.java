package courseOrganizer.models;

import courseOrganizer.views.MainWindow;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class HelpPane extends JDialog {

    private MainWindow mw;

    //constructor
    public HelpPane() {
    }

    public void openBasicHelpDialog() {

        //Κείμενο βασικής βοήθειας
        String basicHelpTitle = "\n The available commands are: ";
        String basicHelpContent = ("\n* Add <Course Title> ---------- Adds the specified course to the course list."
                + "\n* Edit <Course Title> ---------- Edit the details (Title, Instructor, Date/Time, Field of Study) of specified course."
                + "\n* Sort ---------- Sorts the current CourseList alphabetically by course title."
                + "\n* Edit assign <Course Title> ---------- Edit an assignment attached to the specified course."
                + "\n* View all ---------- View all courses. Shows details."
                + "\n* View all assigns ---------- View all assignments, completed or not, from all courses."
                + "\n* View all fin assigns ---------- View all completed assignments from all courses."
                + "\n* View all unfin assigns ---------- View all uncompleted assignments from all courses."
                + "\n* View <Course Title> ---------- View details about specified course. Shows uncompleted assignments."
                + "\n* View assign details <Course Title> ---------- View everything about an assignment from the specified course."
                + "\n* View assigns from <Course Title> ---------- View all assignments from specified course."
                + "\n* View unfin assigns <Course Title> ---------- View all uncompleted assignments from specified course."
                + "\n* View fin assigns <Course Title> ---------- View all completed assignments from specified course."
                + "\n* Delete <Course Title> ---------- Remove a course permanently from the course list."
                + "\n* Assignment to <Course Title> ---------- Create a new assignment and add it to specified course."
                + "\n* Assignment fin <Course Title> ---------- Indicate that you have finished an assignment from the specified course."
                + "\n* Assignment del <Course Title> ---------- Delete an assignment from specified course."
                + "\n* Save ---------- Saves the current CourseList to a file titled \"<file_name>.ser\" in the same directory as the program."
                + "\n* Save as <File Name> ---------- Saves the current CourseList to a ser file with the specified name."
                + "\n* Load last ---------- Reads from the last default CourseList (\"<file_name>.ser\")."
                + "\n* Load from ---------- Opens the file explorer for selection of a CourseList file from which to read."
                + "\n* Exit ---------- Terminate the program.");

        //Πλαίσιο που εμφανίζει τον τίτλο του κειμένου της βασικής βοήθειας
        JPanel titlePanel = new JPanel(new FlowLayout());
        JLabel titleLabel = new JLabel(basicHelpTitle);
        titlePanel.add(titleLabel);

        //Πλαίσιο που περιλαμβάνει σε text area το βασικό κείμενο της βασικής βοήθειας
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(1, 0));
        JTextArea basicHelp = new JTextArea(basicHelpContent);
        basicHelp.setEditable(false);//Απενεργοποίηση της δυνατότητας επεξεργασίας του κειμένου
        basicHelp.setBackground(Color.getColor(basicHelpTitle)); //Εδώ ορίζουμε το χρώμα για το background της text area
        contentPanel.add(basicHelp);

        //Σε ένα νέο πλαίσιο προσθέτουμε τα δυο παραπάνω πλαίσια ορίζοντας τη διάταξή τους
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        ImageIcon CLOSE_ICON = new ImageIcon("button_x.png");
        JButton closeButton = new JButton("Close", CLOSE_ICON);
        buttonsPanel.add(closeButton);

        this.add(mainPanel);
        this.add(buttonsPanel, BorderLayout.SOUTH);
        this.setTitle("Basic Help..");
        this.setSize(640, 480);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(mw);

        closeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    public void openHelpTopicsDialog() {
    }

    public void openAboutDialog() {


        JPanel mainDeveloperPanel = new JPanel(new FlowLayout());
        JLabel mainDeveloperLabel = new JLabel("Written by: Jamie Wohletz");
        mainDeveloperLabel.setFont(mainDeveloperLabel.getFont().deriveFont(Font.PLAIN));
        mainDeveloperPanel.add(mainDeveloperLabel);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2, 0));
        northPanel.add(new JLabel("Course Organizer, version 0.5", JLabel.CENTER));
        northPanel.add(mainDeveloperPanel);

        //Δημιουργία πλαισίου που περιέχει το λινκ προς τη σελιδα της εφαρμογής στο github
        JPanel middlePanel = new JPanel(new FlowLayout());
        JLabel link = new JLabel("Find more by clicking here.. ", JLabel.CENTER);
        link.setFont(link.getFont().deriveFont(Font.PLAIN));
        link.setForeground(Color.BLUE);
        middlePanel.add(link);
        link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        link.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                Desktop desktop = Desktop.getDesktop();
                try {
                    URI url = new URI("https://github.com/JamieWohletz/CourseOrganizer");
                    desktop.browse(url);

                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "There was an exception during the process of your request.");

                } catch (URISyntaxException e1) {
                    JOptionPane.showMessageDialog(null, "There was an exception during the process of your request.");

                } catch (Throwable t) {
                    JOptionPane.showMessageDialog(null, "There was an exception during the process of your request.");

                }
            }
        });

        JPanel codePlayPanel = new JPanel();
        codePlayPanel.setLayout(new GridLayout(2, 0));
        codePlayPanel.add(new JLabel("Contribution: _CodePlay", JLabel.CENTER));
        codePlayPanel.add(middlePanel);
        
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1, 0));
        southPanel.add(new JLabel("We hope you find useful this opensource!", JLabel.CENTER));
        
        
        //Σε ένα νέο πλαίσιο προσθέτουμε τα παραπάνω στοιχεία ορίζοντας τη διάταξή τους
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(codePlayPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);


        JPanel buttonsPanel = new JPanel(new FlowLayout());
        ImageIcon CLOSE_ICON = new ImageIcon("button_x.png");
        JButton closeButton = new JButton("Close", CLOSE_ICON);
        buttonsPanel.add(closeButton);

        this.add(mainPanel);
        this.add(buttonsPanel, BorderLayout.SOUTH);
        this.setTitle("About..");
        this.setSize(400, 200);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(mw);

        closeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });


    }
}
