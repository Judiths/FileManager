package back_runner;

import java.util.List;

public interface CommInterface {

	boolean createFile(String fileName);

	boolean deleteFile(String fileName);

	boolean renameFile(String fileName, String newName);

	boolean copyFile(String srcName, String desName);

	boolean moveFile(String srcName, String desName);

	List<String> listFiles(String dirName);

	boolean createDir(String dirName);

	boolean deleteDir(String dirName);

	boolean renameDir(String dirName, String newPathName);

	boolean copyDir(String srcPathName, String desPathName);

	List<String> listDirs(String dirName);

	boolean moveDir(String srcPathName, String desPathName);

}
