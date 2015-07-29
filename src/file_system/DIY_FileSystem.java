package file_system;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

interface DIY_FileSystem {
	final public static char DIRECTORY = 'D';	// the type of _Dir
	final public static char FILE = 'F';		// the type of _File
	final public static char BOTH = 'B';		// the type of List<String> operation
	
	public Icon getIcon();		//
	public DIY_FileSystem getChild(char fileType, int index);	// 
	public int getChildCount(char fileType);	
	public boolean isLeaf(char fileType);
	public int getIndexOfChild(char fileType, Object child);
	public String getPath();
	public void freshDir();	
	
}

@SuppressWarnings("serial")
class FolderRenderer extends DefaultTreeCellRenderer {

	public Component getTreeCellRendererComponent(JTree tree, Object value, 
			boolean selected, boolean expanded,	boolean leaf, int row, boolean hasFocus) {
		DIY_FileSystem node = (DIY_FileSystem) value;
		Icon icon = node.getIcon();
		setLeafIcon(icon);
		setOpenIcon(icon);
		setClosedIcon(icon);
		return super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
	}
		
}
