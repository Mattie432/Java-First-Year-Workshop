package ex5;

import java.io.*;

public abstract class Ex5DirectoryEntry {

	private static String filePath;

	/**
	 * Main method asks the user for two arguments needed to search. Calls the
	 * methods to search the files/directories and outputs the results to the
	 * console.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		System.out.println("Please enter a directory or file to search...");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Ex5DirectoryEntry a = createFromFilename(br.readLine());
		System.out.println("Please enter a string to search for...");
		a.searchForString(br.readLine());

		if (!a.getFound()) {
			System.out.println("Search term not found...");
		}
		a.printErrors();
	}

	/**
	 * Creates either an Ex5Directory or Ex5File depending upon the input from
	 * the user. This determines if the file exists and deals with it
	 * accordingly.
	 * 
	 * @param _FilePath
	 *            - String (full path to the file/directory)
	 * @return Ex5DirectoryEnquiry
	 * @throws Exception
	 */
	public static Ex5DirectoryEntry createFromFilename(String _filePath)
			throws Exception {
		filePath = _filePath;
		File file = new File(_filePath);

		if (file.exists()) {
			// directory or file exists
			if (file.isDirectory() == true) {
				// Is Directory
				filePath = _filePath;
				return new Ex5Directory(file);

			} else if (file.isFile() == true) {
				// Is File
				filePath = _filePath;
				if (checkFileFormat(file)) {
					return new Ex5File(file);
				} else {
					throw new Exception();
				}
			} else {
				throw new FileNotFoundException();
			}
		} else {
			throw new FileNotFoundException();
		}
	}

	/**
	 * Determines if the format of the specified file is a text file. This can
	 * be operating system dependent so there is a check for which OS is in use.
	 * 
	 * @param file
	 *            - String (file to check)
	 * @return
	 */
	public static boolean checkFileFormat(File file) {
		String system = System.getProperty("os.name");
		if (system != "Mac OS X") {
			String name = file.getPath();
			if (name.contains(".txt")) {
				return true;
			} else if (name.contains(".rtf")) {
				return true;
			} else if (name.contains(".text")) {
				return true;
			} else {
				return false;
			}
		} else {
			// OS X does not append file extensions to file's
			return true;
		}
	}

	/**
	 * Getter method.
	 * 
	 * @return - String (path of file)
	 */
	public static String getFilePath() {
		return filePath;
	}

	/**
	 * Searches for the string in the specified file. If a directory is
	 * specified it will search all files of that directory and any sub
	 * directories.
	 * 
	 * @param searchStr
	 *            - String (search term)
	 * @throws Exception
	 */
	public abstract void searchForString(String searchStr) throws Exception;

	/**
	 * Prints to the console and errors which were collected.
	 */
	public abstract void printErrors();

	/**
	 * Getter method for if the search term has been found in any files
	 * searched.
	 * 
	 * @return - Boolean (search term found)
	 */
	public abstract boolean getFound();
}
