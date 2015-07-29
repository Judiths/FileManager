package file_system;

import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

@SuppressWarnings("serial")
public class FileTree extends JTree {  

	public FileTree() {
		super();
	}
	private FileList theList;

	public FileTree(FileList theList) {
		super();
		this.theList = theList;
		setModel(new FileSystemModel(new FolderNode()));
		this.setCellRenderer(new FolderRenderer());
		addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		this.setSelectionRow(0);
	}
	
	public void fireValueChanged(TreeSelectionEvent tse) {
		TreePath tmp = tse.getNewLeadSelectionPath();
		Object o = tmp.getLastPathComponent();
		theList.fireTreeSelectionChanged((FolderNode) o);
	}
	
	public void fireTreeCollapsed(TreePath path) {
		
		super.fireTreeCollapsed(path);
		TreePath currentpath = getSelectionPath();
		if (path.isDescendant(currentpath)) {
			setSelectionPath(path);
		}
	}
	public void fireTreeWillExpand(TreePath path) {
       
    }

    public void fireTreeWillCollapse(TreePath path) {
        
    }
	
	class ExpansionListener implements TreeExpansionListener {

		FileTree tree;
		
		public ExpansionListener(FileTree tree) {
			super();
			this.tree = tree;
		}

		@Override
		public void treeExpanded(TreeExpansionEvent event) {
			// TODO Auto-generated method stub
		}

		@Override
		public void treeCollapsed(TreeExpansionEvent event) {
			// TODO Auto-generated method stub
		}
	}
} 
