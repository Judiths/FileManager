package file_system;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

class FileSystemModel implements TreeModel {

	DIY_FileSystem theRoot;
	char fileType = DIY_FileSystem.DIRECTORY;
	public FileSystemModel(DIY_FileSystem theRoot) {
		super();
		this.theRoot = theRoot;
	}

	@Override
	public Object getRoot() {
		// TODO Auto-generated method stub
		return theRoot;
	}

	@Override
	public Object getChild(Object parent, int index) {
		// TODO Auto-generated method stub
		return ((DIY_FileSystem) parent).getChild(fileType,index);
	}

	@Override
	public int getChildCount(Object parent) {
		// TODO Auto-generated method stub
		return ((DIY_FileSystem) parent).getChildCount(fileType);
	}

	@Override
	public boolean isLeaf(Object node) {
		// TODO Auto-generated method stub
		return ((DIY_FileSystem) node).isLeaf(fileType);
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		// TODO Auto-generated method stub
		return ((DIY_FileSystem)parent).getIndexOfChild(fileType, child);
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
	}
}
