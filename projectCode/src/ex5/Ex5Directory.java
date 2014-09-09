package ex5;

import java.io.File;
import java.util.ArrayList;

/**
 * Represents a directory on the system.
 * 
 * @author Matt
 */
public class Ex5Directory extends Ex5DirectoryEntry {

	private boolean found = false;
	private File dir;
	private ArrayList<String> errorList = new ArrayList<String>();

	/**
	 * Constructor for the class.
	 * 
	 * @param path
	 *            - File (the directory to search)
	 */
	public Ex5Directory(File path) {
		dir = path;
	}

	public void searchForString(String searchStr) throws Exception {

		for (File _file : dir.listFiles()) {
			if (_file.isDirectory()) {
				Ex5Directory subDir = new Ex5Directory(_file);
				subDir.searchForString(searchStr);
				errorList.addAll(subDir.getErrors());
				// Set found to true if search term was found in subDir
				found = subDir.getFound();
			} else {

				try {
					if (super.checkFileFormat(_file)) {

						Ex5File file = new Ex5File(_file);
						file.searchForString(searchStr);
						found = file.getFound();
					}
				} catch (Exception e) {
					errorList.add(e.getLocalizedMessage());
				}
			}
		}

	}

	/**
	 * Getter method to retrieve the error list of the directory
	 * 
	 * @return errorList - ArrayList<String> (list of errors)
	 */
	public ArrayList<String> getErrors() {
		return errorList;

	}

	public boolean getFound() {
		return found;
	}

	public void printErrors() {
		if (!errorList.isEmpty()) {
			System.out.println();
			System.out.println("ERRORS");
			for (String str : errorList) {
				System.out.println(str);
			}
		}
	}
}
