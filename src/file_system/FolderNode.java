package file_system;

import java.io.File;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;

class FolderNode implements DIY_FileSystem {

	private static FileSystemView fsView;
	private static boolean showHiden = true;
	private File theFile;
	private ArrayList<File> both = new ArrayList<File>();
	private ArrayList<File> folder = new ArrayList<File>();
	
	public void setShowHiden(boolean ifshow) {
		showHiden = ifshow;
	}
	public String toString() {
		return fsView.getSystemDisplayName(theFile);
	}
	public FolderNode() {
		super();
		fsView = FileSystemView.getFileSystemView();
		theFile = fsView.getHomeDirectory();
		prepareChildren();
	}

	private void prepareChildren() {
		// TODO Auto-generated method stub
		File[] files = fsView.getFiles(theFile, showHiden);
		for (int i = 0; i < files.length; i++) {
			both.add(files[i]);
			if (files[i].isDirectory() && !files[i].toString().toLowerCase().endsWith(".link")) {
				folder.add(files[i]);
			}
		}
	}
	
	public FolderNode(File theFile) {
		super();
		this.theFile = theFile;
		prepareChildren();
	}

	@Override
	public Icon getIcon() {
		// TODO Auto-generated method stub
		return fsView.getSystemIcon(theFile);
	}

	@Override
	public DIY_FileSystem getChild(char fileType, int index) {
		// TODO Auto-generated method stub
		if (DIY_FileSystem.DIRECTORY == fileType) {
			return new FolderNode(folder.get(index));
		} else if (DIY_FileSystem.FILE == fileType) {
			return null;
		} else if (DIY_FileSystem.BOTH == fileType) {
			return new FolderNode(both.get(index));
		} else {
			return null;
		} 

	}

	@Override
	public int getChildCount(char fileType) {
		// TODO Auto-generated method stub
		if (DIY_FileSystem.DIRECTORY == fileType) {
			return folder.size();
		} else if (DIY_FileSystem.FILE == fileType) {
			return -1;
		} else if (DIY_FileSystem.BOTH == fileType) {
			return both.size();
		} else {
			return -1;
		}

	}

	@Override
	public boolean isLeaf(char fileType) {
		// TODO Auto-generated method stub
		if (DIY_FileSystem.DIRECTORY == fileType) {
			return folder.size() == 0;
		} else if (DIY_FileSystem.FILE == fileType) {
			return true;
		} else if (DIY_FileSystem.BOTH == fileType) {
			return both.size() == 0;
		} else {
			return true;
		}

	}

	@Override
	public int getIndexOfChild(char fileType, Object child) {
		// TODO Auto-generated method stub
		if (child instanceof FolderNode) {
			if (DIY_FileSystem.DIRECTORY == fileType) {
				return folder.indexOf(((FolderNode) child).theFile);
			} else if (DIY_FileSystem.FILE == fileType) {
				return -1;
			} else if (DIY_FileSystem.BOTH == fileType) {
				return both.indexOf(((FolderNode) child).theFile);
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return theFile.getAbsolutePath();
	}

	@Override
	public void freshDir() {
		// TODO Auto-generated method stub
		both.clear();
		folder.clear();
		prepareChildren();
	}
}