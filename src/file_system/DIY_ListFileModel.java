package file_system;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

@SuppressWarnings("rawtypes")
class DIY_ListFileModel implements ListModel {

	FileList theList;		// the current list of nodeFolder
	DIY_FileSystem node;	// the current folder which is a node
	char fileType = DIY_FileSystem.BOTH;
	public void setNode(DIY_FileSystem node) {
		this.node = node;
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		if (node != null) {
			return node.getChildCount(fileType);
		} else {
			return 0;
		}
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		if (node != null) {
			return node.getChild(fileType, index);
		} else {
			return null;
		}
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

}

/* Inorder to get the Selected event ,we defined ... */
@SuppressWarnings("rawtypes")
class DIYRenderer extends JLabel implements ListCellRenderer {

	
	/**
	 * @desc set the format for the selected node
	 */
	private static final long serialVersionUID = 1L;

	public DIYRenderer() {
		setOpaque(true);
	}
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		FolderNode node = (FolderNode) value;
		setIcon(node.getIcon());
		setText(value.toString());
		setBackground(isSelected ? Color.green.darker() : Color.white);
		setForeground(isSelected ? Color.white.brighter() : Color.black);
		return this;
	}
}
