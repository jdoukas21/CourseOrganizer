package courseOrganizer.views.deleteViews;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import courseOrganizer.listeners.ButtonMouseListener;
import courseOrganizer.listeners.DisposeButtonListener;
import courseOrganizer.lookAndFeel.Fonts;
import courseOrganizer.models.CourseList;
import courseOrganizer.views.MainWindow;

public class DeleteCourseWindow extends JFrame
{
	private CourseList cl;
	private JComboBox coursesBox;
	private JFrame thisFrame = this;
	private MainWindow parent;
	private JButton defaultButton;
	
	public DeleteCourseWindow(MainWindow parent, CourseList cl)
	{
		super("Delete Course");
		
		this.requestFocus();
		this.getContentPane().setLayout(new BorderLayout());
		this.cl = cl;
		this.parent = parent;
		
		add(buildMainPanel(), BorderLayout.CENTER);
		add(buildBottomPanel(), BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
		UIManager.put("Button.defaultButtonFollowsFocus", true); //makes enter activate the default button
		defaultButton.requestFocusInWindow(); //IMPORTANT! NOTE THE POSITION OF THIS METHOD CALL!
		this.setLocationRelativeTo(parent);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public JPanel buildMainPanel()
	{
		JPanel panel = new JPanel(new BorderLayout());
		
		JLabel instructions = new JLabel("<html><div style =\"text-align: center;\">Select which course you'd like"
				  + "<BR>to delete and press OK.</div style></html>"); 
		instructions.setFont(Fonts.DEFAULT_FONT);
	
		String[] courseNames = new String[cl.size()];

		for (int n = 0; n < courseNames.length; n++)
		{
			courseNames[n] = cl.get(n).getName();
		}
		
		coursesBox = new JComboBox(courseNames);
		coursesBox.setSelectedIndex(0);
		coursesBox.setFont(Fonts.DEFAULT_FONT);
		
		panel.setBorder(BorderFactory.createEtchedBorder());
		
		panel.add(instructions, BorderLayout.NORTH);
		panel.add(coursesBox, BorderLayout.CENTER);
		
		return panel;
	}
	
	public JPanel buildBottomPanel()
	{
		JPanel panel = new JPanel(new GridLayout(1, 2));
		
		JButton okayButton = new JButton("OK");
		okayButton.setFont(Fonts.DEFAULT_FONT);
		okayButton.addMouseListener(new ButtonMouseListener(okayButton));
		okayButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				cl.remove((String) coursesBox.getSelectedItem());
				parent.draw(false);
				thisFrame.dispose();
			}
		});
		defaultButton = okayButton;
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(Fonts.DEFAULT_FONT);
		cancelButton.addMouseListener(new ButtonMouseListener(cancelButton));
		cancelButton.addActionListener(new DisposeButtonListener(this));
		
		panel.add(okayButton);
		panel.add(cancelButton);
		
		panel.setBorder(BorderFactory.createEtchedBorder());
		
		return panel;
		
	}
	
	
}
