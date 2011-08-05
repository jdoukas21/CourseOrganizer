package courseOrganizer;

import java.awt.GridLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame
{
	public Window()
	{
		add(buttonPanel());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,400);
		setResizable(false);
		setVisible(true);
	}
	
	public JPanel buttonPanel()
	{
		JPanel panel = new JPanel(new GridLayout(6, 1));
		
		JLabel name = new JLabel("Course Organizer");
		JButton addCourse = new JButton("Add Course");
		JButton removeCourse = new JButton("Remove Course...");
		JButton viewAll = new JButton("View All");
		JLabel currentCourses = new JLabel("Current Courses:");
		
		panel.add(name);
		panel.add(addCourse);
		panel.add(removeCourse);
		panel.add(viewAll);
		panel.add(currentCourses);
		
		return panel;
	}
}
