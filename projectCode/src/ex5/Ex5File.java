package ex5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Represents a file on the system.
 * 
 * @author Matt
 */
public class Ex5File extends Ex5DirectoryEntry {

	private boolean found = false;
	private String fileAddress;
	private String fileName;

	public Ex5File(File _file) {
		fileAddress = _file.getPath();
		fileName = _file.getName();
	}

	public void searchForString(String searchStr) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(
				fileAddress));

		String line;
		int lineCount = 0;

		while ((line = bufferedReader.readLine()) != null) {
			// Increment the count and find the index of the word
			lineCount++;
			int indexfound = line.indexOf(searchStr);

			// If greater than -1, means we found the word
			if (indexfound > -1) {
				if (found == false) {
					System.out.println("File : '" + fileName + "'");
					found = true;
				}
				System.out.println("Match on line " + lineCount + "  :  "
						+ line);
			}
		}

		if (found == true) {
			System.out.println();
		}
		bufferedReader.close();
	}

	public boolean getFound() {
		return found;
	}

	public void printErrors() {

	}

}
