package courseOrganizer.views.assignmentWindows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import courseOrganizer.lookAndFeel.Fonts;
import courseOrganizer.models.CourseList;

public class DescriptionWindow extends JFrame
{
	
	public DescriptionWindow(JFrame parent, CourseList cl)
	{
		super("Assignment Description");
		
		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		JLabel instructions = new JLabel("<html><div style =\"text-align: center;\"><B>Please describe your assignment. When you are finished, click OK.</B></div style></html>");
		
		instructions.setFont(Fonts.DEFAULT_FONT);
		instructions.setAlignmentX(RIGHT_ALIGNMENT);
		instructions.setBorder(BorderFactory.createEtchedBorder());
		
		JTextArea descriptionArea = new JTextArea();
		descriptionArea.setFont(Fonts.DEFAULT_FONT);
		
		descriptionArea.setPreferredSize(new Dimension(300, 400));
		descriptionArea.setLineWrap(true);
		descriptionArea.setWrapStyleWord(true);
		descriptionArea.setBorder(BorderFactory.createEtchedBorder());
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,2));
		
		JButton OK = new JButton("OK");
		courseOrganizer.utilities.Utilities.prepareRegularButton(OK);
		JButton cancelButton = new JButton("Cancel");
		courseOrganizer.utilities.Utilities.prepareDisposeButton(cancelButton, this);
		
		buttonPanel.add(OK);
		buttonPanel.add(cancelButton);
		buttonPanel.setBorder(BorderFactory.createEtchedBorder());
		
		this.add(instructions, BorderLayout.NORTH);
		this.add(descriptionArea, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(parent);
		setVisible(true);
	}
}
