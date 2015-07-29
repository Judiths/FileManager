package helper;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.BevelBorder;

import back_runner.BackgroundRunner;
import back_runner.CommInterface;
import file_system.FileList;
import file_system.FileTree;

@SuppressWarnings("serial")
public class UIHelper extends JPanel {

	static int LEFT_WIDTH = 300;
	static int RIGHT_WIDTH = 400;
	static int WINDOW_HEIGHT = 400;
	String oldPath = "";
	
	boolean isMovedFile = false;
	boolean isMovedFolder = false;
	
	JFrame frame = null;
	protected Component right_click_menu;
	@SuppressWarnings("deprecation")
	public UIHelper(JFrame frame) {
		super();
		this.frame = frame;
		
		setPreferredSize(new Dimension(600,500));
		setBorder(new BevelBorder(BevelBorder.RAISED));
		setLayout(new BorderLayout());
		
		FileList list = new FileList();
		FileTree tree = new FileTree(list);
		tree.setDoubleBuffered(true);
		list.setDoubleBuffered(true);
		
		JScrollPane treeView = new JScrollPane(tree);
		treeView.setPreferredSize(new Dimension(LEFT_WIDTH,WINDOW_HEIGHT));
		
		JScrollPane listView = new JScrollPane(list);
		listView.setPreferredSize(new Dimension(RIGHT_WIDTH,WINDOW_HEIGHT));
		
		JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeView, listView);
		pane.setDividerLocation(180);
		pane.setDividerSize(10);
		pane.setDoubleBuffered(true);
		add(pane);
		
		/*Now, set the popup menu*/
		JPopupMenu popup = new JPopupMenu();
		Component rightClick = new RightClickMenu(popup, frame.getContentPane());
		frame.setGlassPane(rightClick);
		rightClick.setVisible(true);
		/*add EventListener for the menu*/
		JMenuItem createF = new JMenuItem("create File");
		createF.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String path = list.path00;
				CommInterface test = new BackgroundRunner();
				test.createFile(path + "/New File");
				System.out.println(path + "/New File");
				System.out.println("Done.cresteF");
				list.updateData();
				tree.updateUI();
			}
			
		});
		popup.add(createF);
		
		JMenuItem deleteF = new JMenuItem("delete File");
		deleteF.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String path = list.path00;
				String file = list.file00;
				CommInterface test = new BackgroundRunner();
				test.deleteFile(path + "/" + file);
				System.out.println("Done.deleteF");
				list.updateData();
				tree.updateUI();			
			}
			
		});
		popup.add(deleteF);
		
		JMenuItem cutF = new JMenuItem("cut File");
		cutF.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String path = list.path00;
				isMovedFolder = false;			//move the file
				isMovedFile = true;
				oldPath = path;
				System.out.println("Done.cutF");
				list.updateData();
				tree.updateUI();
			}
			
		});
		popup.add(cutF);
		
		JMenuItem paste = new JMenuItem("paste");
		paste.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String path = list.path00;
				String file = list.file00;
				CommInterface test = new BackgroundRunner();
				if(!isMovedFolder) {
					if(isMovedFile) {
						// isMovedFolder = false; isMovedFile = true
						test.moveFile(oldPath + "/" + file, path + "/" + file);
					} else {
						// isMovedFolder = false; isMovedFile = false
						test.copyFile(oldPath + "/" + file, path + "/" + file);
					}
				} else {
					if(isMovedFile) {
						// isMovedFolder = true; isMovedFile = true
						test.moveDir(oldPath, path);
					} else {
						// isMovedFolder = true; isMovedFile = false
						test.copyDir(oldPath, path);
					}
				}
				list.updateData();
				tree.updateUI();
				System.out.println("Done.paste");
			}
			
		});
		popup.add(paste);
		
		JMenuItem copyF = new JMenuItem("copy File");
		copyF.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String path = list.path00;
				isMovedFolder = false;
				isMovedFile = false;
				oldPath = path;
				System.out.println("Done.copyF");
			}
			
		});
		popup.add(copyF);
		
		JMenuItem createDir = new JMenuItem("create Folder");
		createDir.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String path = list.path00;
				CommInterface test = new BackgroundRunner();
				test.createDir(path + "/New Folder");
				System.out.println("Done.createDir");
				list.updateData();
				tree.updateUI();
			}
			
		});
		popup.add(createDir);
		
		JMenuItem deleteDir = new JMenuItem("delete Folder");
		deleteDir.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String path = list.path00;
				CommInterface test = new BackgroundRunner();
				test.deleteDir(path);
				System.out.println("Done.deleteDir");
				list.updateData();
				tree.updateUI();
			}
			
		});
		popup.add(deleteDir);
		
		JMenuItem copyDir = new JMenuItem("copy Folder");
		copyDir.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String path = list.path00;
				isMovedFolder = true;
				isMovedFile = false;
				oldPath = path;
				System.out.println("Done.copyDir");
				list.updateData();
				tree.updateUI();
			}
			
		});
		popup.add(copyDir);
		
		JMenuItem cutDir = new JMenuItem("cut Folder");
		cutDir.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String path = list.path00;
				isMovedFolder = true;			//move the file
				isMovedFile = true;
				oldPath = path;
				System.out.println("Done.cutDir");
				list.updateData();
				tree.updateUI();
			}
			
		});
		popup.add(cutDir);
		
		frame.setSize(600, 800);
		frame.pack();
		frame.show();
	}
}