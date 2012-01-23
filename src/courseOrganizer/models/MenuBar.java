package courseOrganizer.models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import courseOrganizer.lookAndFeel.Fonts;
import courseOrganizer.views.MainWindow;

//PALE YELLOW: 255, 240, 98

public class MenuBar extends JMenuBar
{
	private MainWindow mainWindow;
	
	public MenuBar(MainWindow mw)
	{
		mainWindow = mw;
		/*UIManager.put("Menu.selectionBackground", Colors.PALE_YELLOW);
		UIManager.put("MenuItem.selectionBackground", Colors.PALE_YELLOW);
		UIManager.put("CheckBoxMenuItem.selectionBackground", Colors.PALE_YELLOW);*/
		this.setBorder(BorderFactory.createEtchedBorder());
		
		// FILE
		// MENU******************************************************************************
		JMenu file = new JMenu("File");
		file.setFont(Fonts.DEFAULT_FONT);

		JMenuItem[] fileActions = new JMenuItem[6];

		fileActions = initializeMenuItems(fileActions);
		file = addItemsToMenu(fileActions, file);

		fileActions[0].setText("New");
		fileActions[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				KeyEvent.CTRL_DOWN_MASK));

		fileActions[1].setText("Open Last");
		fileActions[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				KeyEvent.CTRL_DOWN_MASK));

		fileActions[2].setText("Open File...");
		fileActions[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				KeyEvent.CTRL_DOWN_MASK));

		fileActions[3].setText("Save");
		fileActions[3].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				KeyEvent.CTRL_DOWN_MASK));

		fileActions[4].setText("Save as...");

		fileActions[5].setText("Exit");
		fileActions[5].addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);				
			}
		});

		// EDIT
		// MENU***************************************************************************
		JMenu edit = new JMenu("Edit");
		edit.setFont(Fonts.DEFAULT_FONT);

		JMenuItem[] editActions = new JMenuItem[2];
		
		editActions = initializeMenuItems(editActions);
		edit = addItemsToMenu(editActions, edit);
		
		editActions[0].setText("Undo");
		editActions[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				KeyEvent.CTRL_DOWN_MASK));

		editActions[1].setText("Redo");
		editActions[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
				KeyEvent.CTRL_DOWN_MASK));

		// VIEW
		// MENU*****************************************************************************
		JMenu view = new JMenu("View");
		view.setFont(Fonts.DEFAULT_FONT);

		JMenuItem[] viewActions = new JMenuItem[6];

		viewActions = initializeMenuItems(viewActions);
		view = addItemsToMenu(viewActions, view);
		
		viewActions[0].setText("Courses");
		viewActions[0].addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainWindow.changeView("Courses");				
			}
		});
		
		viewActions[1].setText("Assignments");
		viewActions[2].setText("Notepaper");
		viewActions[3].setText("Notebook");
		viewActions[4].setText("Schedule");
		viewActions[5].setText("Advanced...");
		
		//TOOLS  MENU************************************************************************
		JMenu tools = new JMenu("Tools");
		tools.setFont(Fonts.DEFAULT_FONT);
		
		JMenuItem[] toolsActions = new JMenuItem[1];
		
		toolsActions = initializeMenuItems(toolsActions);
		tools = addItemsToMenu(toolsActions, tools);
		
		toolsActions[0].setText("Options");

		//HELP MENU**************************************************************************
		JMenu help = new JMenu("Help");
		help.setFont(Fonts.DEFAULT_FONT);
		
		JMenuItem[] helpActions = new JMenuItem[3];
		
		helpActions = initializeMenuItems(helpActions);
		help = addItemsToMenu(helpActions, help);
		
		helpActions[0].setText("Basic Help");
		helpActions[1].setText("Help Topics");
		helpActions[2].setText("About");

		this.add(file);
		this.add(edit);
		this.add(view);
		this.add(tools);
		this.add(help);
		
		//this.setBackground(new Color(152, 251, 152));
	}

	public JMenuItem[] initializeMenuItems(JMenuItem[] array)
	{
		int length = array.length;

		for (int n = 0; n < length; n++)
		{
			array[n] = new JMenuItem();
			array[n].setFont(Fonts.DEFAULT_FONT);
		}
		return array;

	}
	
	public JMenu addItemsToMenu(JMenuItem[] array, JMenu menu)
	{
		for (int n = 0; n < array.length; n ++)
		{
			menu.add(array[n]);
		}
		return menu;
	}
}
