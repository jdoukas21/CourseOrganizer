package courseOrganizer.views.addCourseViews;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import courseOrganizer.listeners.ButtonMouseListener;
import courseOrganizer.models.CourseList;
import courseOrganizer.views.MainWindow;

public class AddCourseOptionWindow extends JFrame
{
	/**
	 * 
	 */
	private Font DEFAULT_FONT = new Font("Times New Roman", Font.PLAIN, 14);
	private CourseList courseList;
	private MainWindow container;
	
	private JFrame thisFrame = this;
	
	public AddCourseOptionWindow(MainWindow c, CourseList cl)
	{
		super("Add Course");
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		courseList = cl;
		this.container = c;
		
		JPanel mainPane = new JPanel();
		mainPane.setLayout(new BorderLayout());
		
		JPanel centerPane = new JPanel(new GridLayout(3, 1));
		
		JLabel instructions = new JLabel("<html><div style =\"text-align: center;\">Please choose the type of course"
													+ "<BR>you would like to add.</div style></html>");
		instructions.setBorder(BorderFactory.createEtchedBorder());
		instructions.setFont(DEFAULT_FONT);
		
		mainPane.add(instructions, BorderLayout.NORTH);
		
		JButton detailedCourse = new JButton("Detailed Course");
		detailedCourse.setFont(DEFAULT_FONT);
		detailedCourse.addMouseListener(new ButtonMouseListener(detailedCourse));
		detailedCourse.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new AddDetailedCourseWindow(container, courseList, false);
			}
		});
		
		JButton simpleCourse = new JButton("Simple Course");
		simpleCourse.setFont(DEFAULT_FONT);
		simpleCourse.addMouseListener(new ButtonMouseListener(simpleCourse));
		simpleCourse.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new AddSimpleCourse((JFrame)container, courseList);
			}
		});
		
		JButton freeForm = new JButton("Free-form Course");
		freeForm.addMouseListener(new ButtonMouseListener(freeForm));
		freeForm.setFont(DEFAULT_FONT);
		
		centerPane.add(detailedCourse);
		centerPane.add(simpleCourse);
		centerPane.add(freeForm);
		
		centerPane.setBorder(BorderFactory.createLineBorder(mainPane.getBackground(), 5));
		mainPane.add(centerPane, BorderLayout.CENTER);
		
		//BUTTONPANEL -- HOLDS CANCEL BUTTON
		JPanel buttonPanel = new JPanel();
		
		JButton done = new JButton("Done");
		done.setFont(DEFAULT_FONT);
		done.addMouseListener(new ButtonMouseListener(done));
		done.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				container.draw(false);
				thisFrame.dispose();
			}
		});
		
		buttonPanel.add(done);
		
		buttonPanel.setBorder(BorderFactory.createEtchedBorder());
		mainPane.add(buttonPanel, BorderLayout.SOUTH);
		
		mainPane.setBorder(BorderFactory.createLineBorder(mainPane.getBackground(), 5));
		
		this.add(mainPane);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(container);
		setVisible(true);
	}
}