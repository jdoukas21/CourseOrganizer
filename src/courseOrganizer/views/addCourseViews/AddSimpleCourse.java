package courseOrganizer.views.addCourseViews;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import courseOrganizer.listeners.ButtonMouseListener;
import courseOrganizer.listeners.DisposeButtonListener;
import courseOrganizer.listeners.TextFieldFocusListener;
import courseOrganizer.listeners.TextFieldKeyListener;
import courseOrganizer.listeners.TextFieldMouseListener;
import courseOrganizer.lookAndFeel.Fonts;
import courseOrganizer.models.Course;
import courseOrganizer.models.CourseList;
import courseOrganizer.utilities.Utilities;

public class AddSimpleCourse extends JFrame
{
	private CourseList cl;
	private JTextField courseTitleField;
	private JFrame thisFrame = this;
	
	public AddSimpleCourse(JFrame container, CourseList cl)
	{
		super("Add Course");
		this.cl = cl;
	
		this.getContentPane().setLayout(new GridLayout(2, 1));
		
		add(buildTextFieldPanel());
		add(buildButtonPanel());
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(container);
		this.setResizable(false);
		this.setVisible(true);
	}

	public JPanel buildTextFieldPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel innerPanel = new JPanel();
		
		JLabel instructions = new JLabel("Course Title: ");
		instructions.setFont(Fonts.DEFAULT_FONT);
		
		courseTitleField = new JTextField("General Biology");
		Utilities.prepareTextField(courseTitleField, "General Biology");
		
		innerPanel.add(instructions);
		innerPanel.add(courseTitleField);
		
		panel.add(innerPanel, BorderLayout.CENTER);
		
		panel.setBorder(BorderFactory.createEtchedBorder());
		
		return panel;
	}
	
	public JPanel buildButtonPanel()
	{
		JPanel panel = new JPanel();
		
		JButton okayButton = new JButton("OK");
		okayButton.setFont(Fonts.DEFAULT_FONT);
		okayButton.addMouseListener(new ButtonMouseListener(okayButton));
		okayButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (courseTitleField.getFont() != Fonts.ITALIC_FONT && !courseTitleField.getText().isEmpty())
				{
					cl.addCourse(new Course(courseTitleField.getText()));
					thisFrame.dispose();
				}
			}
		});
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(Fonts.DEFAULT_FONT);
		Utilities.prepareDisposeButton(cancelButton, this);
		
		panel.add(okayButton);
		panel.add(cancelButton);
		
		panel.setBorder(BorderFactory.createEtchedBorder());
		
		return panel;
		
	}
	
}
