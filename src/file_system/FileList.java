package file_system;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class FileList extends JList<Object> {
	
	public String path00 = "";		//current folder
	public String file00 = "";		//current file
	DIY_FileSystem nodeTemp;		// self_definite yourself fileSystem
	DIY_ListFileModel dataModel;	// set the format of filelist with self_definition
	@SuppressWarnings("unchecked")
	public FileList() {
		super();
		dataModel = new DIY_ListFileModel();
		setModel(dataModel);	 // list the current Node's files
		this.setCellRenderer(new DIYRenderer());
		this.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(FileList.this.getSelectedValue());
				file00 = FileList.this.getSelectedValue().toString();
			}
			
		});
	}
	
	public void fireTreeSelectionChanged(DIY_FileSystem node) {
		nodeTemp = node;
		path00 = node.getPath();
		dataModel.setNode(node);
		System.out.println("Done.list");
		System.out.println(node.getPath());
		node.freshDir();
		updateUI();
	}
	
	public void updateData() {
		nodeTemp.freshDir();
		fireTreeSelectionChanged(nodeTemp);
	}
}
