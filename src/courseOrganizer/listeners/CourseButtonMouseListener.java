package courseOrganizer.listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import courseOrganizer.lookAndFeel.Fonts;
import courseOrganizer.models.CourseList;
import courseOrganizer.views.MainWindow;
import courseOrganizer.views.addCourseViews.AddCourseOptionWindow;
import courseOrganizer.views.addCourseViews.AddDetailedCourseWindow;
import courseOrganizer.views.deleteViews.DeleteCourseWindow;

public class CourseButtonMouseListener implements MouseListener
{
	private JButton button;
	private Color backgroundColor;
	private Color fontColor;
	private Color highlightBackground = Color.BLACK;
	private Color highlightForeground = Color.WHITE;
	private String courseName;
	
	private MainWindow parent;
	private CourseList cl;
	
	private String type;
	
	public CourseButtonMouseListener(JButton button)
	{
		this.button = button;
		backgroundColor = button.getBackground();
		fontColor = button.getForeground();
	}
	
	public CourseButtonMouseListener(JButton button, String courseName, Color background, Color foreground, MainWindow parent, CourseList cl)
	{
		this.button = button;
		this.parent = parent;
		this.cl = cl;
		this.courseName = courseName;
		backgroundColor = button.getBackground();
		fontColor = button.getForeground();
		highlightBackground = background;
		highlightForeground = foreground;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (e.isPopupTrigger())
		{
			showPopUpMenu(e);
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		button.setBackground(highlightBackground);
		button.setForeground(highlightForeground);
	}
	
	@Override
	public void mouseExited(MouseEvent e)
	{
		button.setBackground(backgroundColor);
		button.setForeground(fontColor);
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		if (e.isPopupTrigger())
		{
			showPopUpMenu(e);
		}
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		if (e.isPopupTrigger())
		{
			showPopUpMenu(e);
		}
		
	}
	
	private void showPopUpMenu(MouseEvent me)
	{
		JPopupMenu popMenu = new JPopupMenu("Actions");
		popMenu.setFont(Fonts.DEFAULT_FONT);
		JMenuItem add = new JMenuItem("Add New Course");
		add.setFont(Fonts.DEFAULT_FONT);
		add.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				AddCourseOptionWindow acow = new AddCourseOptionWindow(parent, cl);
			}
		});
		
		JMenuItem delete = new JMenuItem("Delete");
		delete.setFont(Fonts.DEFAULT_FONT);
		delete.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int choice = JOptionPane.showConfirmDialog(null, "Permanently delete " + courseName + "?", "Confirm Deletion", JOptionPane.OK_CANCEL_OPTION);
				if (choice == JOptionPane.OK_OPTION)
				{
					cl.remove(courseName);
					parent.draw(false);
				}
			}
		});
		
		JMenuItem edit = new JMenuItem("Edit");
		edit.setFont(Fonts.DEFAULT_FONT);
		edit.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				AddDetailedCourseWindow adcw = new AddDetailedCourseWindow(parent, cl, true);
				adcw.setDefaultCourseAndShow(courseName);
				
			}
		});
		
		JMenu search = new JMenu("Search");
		search.setFont(Fonts.DEFAULT_FONT);
		
		JTextField courseSearch = new JTextField("Search Course...");
		courseSearch.setFont(Fonts.ITALIC_FONT);
		courseSearch.addMouseListener(new TextFieldMouseListener(courseSearch, "Search Course..."));
		
		JMenuItem openSearchDialog = new JMenuItem("Open Search Dialog...");
		openSearchDialog.setFont(Fonts.DEFAULT_FONT);
		
		search.add(courseSearch);
		search.add(openSearchDialog);
		
		popMenu.add(add);
		popMenu.add(delete);
		popMenu.add(edit);
		popMenu.add(search);
		
		popMenu.show(me.getComponent(), me.getX(), me.getY());
	}

}
