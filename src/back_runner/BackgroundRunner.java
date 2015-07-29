package back_runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class BackgroundRunner implements CommInterface {

	@Override
	public boolean createFile(String fileName) {
		File file = new File(fileName);
		try {
			return file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteFile(String fileName) {
		File file = new File(fileName);
		return file.delete();
	}

	@Override
	public boolean renameFile(String fileName, String newName) {
		File file = new File(fileName);
		return file.renameTo(new File(newName));
	}

	@Override
	public boolean copyFile(String srcName, String desName) {
		InputStream in;
		OutputStream out;

		// echo("copy file: "+src+" -> "+des+" .");

		try {
			in = new FileInputStream(srcName);
			out = new FileOutputStream(desName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return false;
		}

		byte b[] = new byte[1024 * 1024 * 5];
		int len;
		try {
			len = in.read(b);
			while (len != -1) {
				out.write(b, 0, len);
				len = in.read(b);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		try {
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean moveFile(String srcName, String desName) {
		File file = new File(srcName);
		return file.renameTo(new File(desName));
	}

	@Override
	public List<String> listFiles(String dirName) {
		File dir = new File(dirName);
		String files[] = dir.list();
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < files.length; i++) {
			File file = new File(dir, files[i]);
			if (file.isFile())
				result.add(files[i]);
		}
		return result;
	}

	@Override
	public boolean createDir(String dirName) {
		File dir = new File(dirName);
		return dir.mkdir();
	}

	private boolean _deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();

			for (int i = 0; i < children.length; i++) {
				boolean success = _deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}

	@Override
	public boolean deleteDir(String dirName) {
		return _deleteDir(new File(dirName));
	}

	@Override
	public boolean renameDir(String dirName, String newPathName) {
		File dir = new File(dirName);
		return dir.renameTo(new File(newPathName));
	}

	private boolean _copyDir(File srcDir, String desDir) {

		if (srcDir.isDirectory()) {
			String newDir = desDir;
			File f = new File(newDir);

			if (!f.exists() || f.exists() && !f.isDirectory())
				if (!f.mkdirs())
					return false;

			String[] children = srcDir.list();

			for (int i = 0; i < children.length; i++) {
				boolean success = _copyDir(new File(srcDir, children[i]),
						newDir + "/" + children[i]);
				if (!success) {
					return false;
				}

			}

			return true;
		} else if (srcDir.isFile()) {
			try {
				return copyFile(srcDir.getCanonicalPath(), desDir);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} else
			return false;
	}

	@Override
	public boolean copyDir(String srcPathName, String desPathName) {
		return _copyDir(new File(srcPathName), desPathName);
	}

	@Override
	public boolean moveDir(String srcPathName, String desPathName) {
		File dir = new File(srcPathName);
		return dir.renameTo(new File(desPathName));
	}

	@Override
	public List<String> listDirs(String dirName) {
		File dir = new File(dirName);
		String files[] = dir.list();
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < files.length; i++) {
			File file = new File(dir, files[i]);
			if (file.isDirectory())
				result.add(files[i]);
		}
		return result;
	}

}
