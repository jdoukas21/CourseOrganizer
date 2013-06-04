package courseOrganizer.utilities;

import courseOrganizer.models.MenuBar;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.File;
import javax.swing.JOptionPane;

public class OpenLastSavedPath {

    private File filepath;
    private MenuBar menubar;
    private ObjectInputStream input;

    public void openFile() {
        try {
            input = new ObjectInputStream(new FileInputStream("LastSavedPath.ser"));
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(null, "File does not exist");
        }
    }

    public File ReadRecordsFromFile() {
        try {
            while (true) {
                filepath = (File) input.readObject();
            }

        } // Αυτό το catch εκετελείται όταν τελειώσει η ανάγνωση του αρχείου. EOF=End-of-file
        catch (EOFException exc) {
            System.out.println(filepath.toString());
            menubar.setFilePath(filepath);
            return filepath;
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(null, "Error during reading from file");
        } catch (ClassNotFoundException exc) {
            JOptionPane.showMessageDialog(null, "Unable to create object");
        }

        return filepath;
    }

    public void closeFile() {
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(null, "Error closing file");
        }
    }
}
