package courseOrganizer.views.assignmentWindows;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import courseOrganizer.listeners.ButtonMouseListener;
import courseOrganizer.lookAndFeel.Fonts;
import courseOrganizer.models.CourseList;
import courseOrganizer.utilities.Utilities;
import courseOrganizer.views.MainWindow;

public class AddAssignmentWindow extends JFrame
{
	private Font DEFAULT_FONT = Fonts.DEFAULT_FONT;
	private Font BOLD_FONT = Fonts.BOLD_FONT;
	private Font ITALIC_FONT = Fonts.ITALIC_FONT;
	
	private MainWindow mWindow;
	private CourseList cl;
	
	public AddAssignmentWindow(MainWindow mWindow, CourseList cl)
	{
		super("Add Assignment");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.mWindow = mWindow;
		this.cl = cl;
		
		this.getContentPane().setLayout(new BorderLayout());
		add(mainPanel(), BorderLayout.CENTER);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public JPanel mainPanel()
	{	
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		container.add(makeTitlePanel());
		container.add(makeDescriptionPanel());
		container.add(makeDatesPanel());
		container.setBorder(BorderFactory.createEtchedBorder());
		
		JPanel mainContainer = new JPanel();
		mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
		mainContainer.add(container);
		mainContainer.add(makeButtonPanel());
		
		return mainContainer;
	}
	
	private JPanel makeTitlePanel()
	{
		JPanel titlePanel = new JPanel(new GridLayout(1, 2));
		
		JLabel title = new JLabel("Title");
		title.setFont(DEFAULT_FONT);
		
		JTextField titleField = new JTextField("Research Paper");
		Utilities.prepareTextField(titleField, "Research Paper");
		
		titlePanel.add(title);
		titlePanel.add(titleField);
		
		return titlePanel;
	}
	
	private JPanel makeDescriptionPanel()
	{
		JPanel descriptionPanel = new JPanel();
		
		descriptionPanel.setLayout(new GridLayout(1,2));
		JLabel description = new JLabel("Description:");
		description.setFont(DEFAULT_FONT);
		
		JButton largeDescription = new JButton("Add Description...");
		largeDescription.setFont(DEFAULT_FONT);
		largeDescription.addMouseListener(new ButtonMouseListener(largeDescription));
		largeDescription.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new DescriptionWindow(mWindow, cl);
			}
		});
		
		descriptionPanel.add(description);
		descriptionPanel.add(largeDescription);
		
		return descriptionPanel;
	}
	
	private JPanel makeDatesPanel()
	{
		JPanel datesPanel = new JPanel(new GridLayout(4, 2));
		
		JLabel dateAssigned = new JLabel("Date Assigned:");
		dateAssigned.setFont(DEFAULT_FONT);
		
		JFormattedTextField dateAssignedField = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));
		dateAssignedField.setValue(new Date());
		dateAssignedField.setFont(DEFAULT_FONT);
		
		JLabel dateDue = new JLabel("Date Due:");
		dateDue.setFont(DEFAULT_FONT);
		
		JFormattedTextField dateDueField = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));
		dateDueField.setValue(new Date());
		dateDueField.setFont(DEFAULT_FONT);
		
		final JLabel dateFinished = new JLabel("Date Finished:");
		dateFinished.setEnabled(false);
		dateFinished.setFont(DEFAULT_FONT);
		
		final JFormattedTextField dateFinishedField = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));
		dateFinishedField.setValue(new Date());
		dateFinishedField.setFont(DEFAULT_FONT);
		dateFinishedField.setEnabled(false);
		
		JLabel finished = new JLabel("Is Finished:");
		finished.setFont(DEFAULT_FONT);
		
		JCheckBox isFinished = new JCheckBox();
		isFinished.addItemListener(new ItemListener()
		{
			
			@Override
			public void itemStateChanged(ItemEvent arg0)
			{
				if (arg0.getStateChange() == ItemEvent.SELECTED)
				{
					dateFinished.setEnabled(true);
					dateFinishedField.setEnabled(true);
				}
				else
				{
					dateFinished.setEnabled(false);
					dateFinishedField.setEnabled(false);
				}
			}
		});
		
		datesPanel.add(dateAssigned); datesPanel.add(dateAssignedField);
		datesPanel.add(dateDue); datesPanel.add(dateDueField);
		datesPanel.add(finished); datesPanel.add(isFinished);
		datesPanel.add(dateFinished); datesPanel.add(dateFinishedField);
		
		return datesPanel;
	}
	
	private JPanel  makeButtonPanel()
	{
		JPanel buttonPanel = new JPanel(new GridLayout(1,2));
		
		JButton okayButton = new JButton("OK");
		okayButton.setFont(DEFAULT_FONT);
		okayButton.addMouseListener(new ButtonMouseListener(okayButton));
		
		JButton cancelButton = new JButton("Cancel");
		Utilities.prepareDisposeButton(cancelButton, this);
		
		buttonPanel.add(okayButton);
		buttonPanel.add(cancelButton);
		buttonPanel.setBorder(BorderFactory.createEtchedBorder());
		
		return buttonPanel;
	}
	
	
}
