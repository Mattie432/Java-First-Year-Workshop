package ex6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Ex6DataFile {

	private ArrayList<Ex6DataLine> file = new ArrayList<Ex6DataLine>();

	/**
	 * Returns if the file contains the specified heading.
	 * @param heading - String
	 * @return boolean
	 */
	public boolean checkIfHeadingExists(String heading) {
		return file.get(0).checkIfHeadingExists(heading);
	}

/**
 * Constructor for file. Creates a file representation from the input stream given to it consisting of Ex6DataLine's.
 * @param in - InputStream
 * @throws IOException
 * @throws Ex6UnequalLengthException
 * @throws Ex6NullStringException
 * @throws Ex6NoElementException
 */
	public Ex6DataFile(InputStream in) throws IOException, Ex6UnequalLengthException, Ex6NullStringException, Ex6NoElementException {
		
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String headings = "";
			boolean firstLine = true;
			String line = "";
			while ((line = br.readLine()) != null) {

				if (firstLine == true) {
					headings = line;
					firstLine = false;
				} else {
					file.add(new Ex6DataLine(headings, line));
				}
			}
			
	}

	/**
	 * Getter method for the file headings.
	 * @return - ArrayList of type 'String'
	 */
	public ArrayList<String> getFileHeadings() {

		return this.file.get(0).getColumnHeadings();
	}

	/**
	 * Outputs the file to System.out in the format originally given to it.
	 */
	public void writeToStream() {

		Ex6DataLine temp = file.get(0);
		System.out.println(temp.outHeadingsLine());
		for (Ex6DataLine line : file) {
			System.out.println(line.outDataLine());

		}
	}

	/**
	 * Returns the line containing the given value for the given heading.
	 * @param heading - String
	 * @param val - String
	 * @return - Ex6DataLine
	 * @throws Ex6UnequalLengthException
	 * @throws Ex6NullStringException
	 * @throws Ex6NoElementException
	 */
	public Ex6DataLine getLineFromHeadingValue(String heading, String val) throws Ex6UnequalLengthException, Ex6NullStringException, Ex6NoElementException {
		for (Ex6DataLine line : file) {
			if (line.checkIfHeadingExists(heading)) {
				String lineData = line.getData(heading);
				if (lineData.equals(val)) {
					return line;
				}
			}
		}
		return new Ex6DataLine("", "");
	}

	/**
	 * Merges the current file with the given file. This is then output to System.out
	 * @param mergeFile - Ex6DataFile
	 * @throws Ex6UnequalLengthException
	 * @throws Exception
	 */
	public void merge(Ex6DataFile mergeFile) throws Ex6UnequalLengthException,Ex6NoUniqueHeadingException, Exception {
		int count = 0;
		String commonHeading = "";

		ArrayList<String> thisHeadings = this.getFileHeadings();
		for (String str : thisHeadings) {

			ArrayList<String> headings = mergeFile.getFileHeadings();
			for (String head : headings) {
				if (head.contains(str) == true) {
					count += 1;
					commonHeading = str;
				}
			}
		}

		if (count > 1) {
			throw new Ex6NoUniqueHeadingException(count);
		} else if (count == 0) {
			throw new Ex6NoUniqueHeadingException();
		} else {
			ArrayList<Ex6DataLine> newFile = new ArrayList<Ex6DataLine>();

			// Create new headings
			Set<String> test = new TreeSet<String>();
			test.addAll(getFileHeadings());
			test.addAll(mergeFile.getFileHeadings());
			
			// cast to arraylist
			ArrayList<String> headings = new ArrayList<String>(test);
			//sorts alphabetically.
			Collections.sort(headings);
			
			for (Ex6DataLine line : file) {
				ArrayList<String> values = new ArrayList<String>();
				String commonHeadVal = line.getData(commonHeading);
				
				
				Ex6DataLine lineFromExternalFile = mergeFile
						.getLineFromHeadingValue(commonHeading, commonHeadVal);
				
				
				for (String head : headings) {
					if (line.checkIfHeadingExists(head)) {
						// in this file
						values.add(line.getData(head));
					} else if (lineFromExternalFile.checkIfHeadingExists(head)) {
						// its in the 2nd file
						values.add(lineFromExternalFile.getData(head));
					} else {
						values.add(" ");
					}
				}

				newFile.add(new Ex6DataLine(headings, values));
				file = newFile;
			}
		}
		writeToStream();
	}




	/**
	 *  * Merges the current file with the given file and removes all columns specified in deleteColumns. This is then output to System.out.
	 * @param mergeFile - Ex6DataFile
	 * @param deleteColumns - ArrayList of type 'String'
	 * @throws Ex6UnequalLengthException
	 * @throws Ex6NoUniqueHeadingException
	 * @throws Exception
	 */
	public void merge(Ex6DataFile mergeFile, ArrayList<String> deleteColumns) throws Ex6UnequalLengthException,Ex6NoUniqueHeadingException, Exception {
		int count = 0;
		String commonHeading = "";

		ArrayList<String> thisHeadings = this.getFileHeadings();
		for (String str : thisHeadings) {

			ArrayList<String> headings = mergeFile.getFileHeadings();
			for (String head : headings) {
				if (head.contains(str) == true) {
					count += 1;
					commonHeading = str;
				}
			}
		}

		if (count > 1) {
			throw new Ex6NoUniqueHeadingException(count);
		} else if (count == 0) {
			throw new Ex6NoUniqueHeadingException();
		} else {
			ArrayList<Ex6DataLine> newFile = new ArrayList<Ex6DataLine>();

			// Create new headings
			Set<String> test = new TreeSet<String>();
			test.addAll(getFileHeadings());
			test.addAll(mergeFile.getFileHeadings());
			
			// cast to arraylist
			ArrayList<String> headings = new ArrayList<String>(test);
			//remove deleted columns
			headings.removeAll(deleteColumns);
			//sorts alphabetically.
			Collections.sort(headings);
			
			for (Ex6DataLine line : file) {
				ArrayList<String> values = new ArrayList<String>();
				String commonHeadVal = line.getData(commonHeading);
				
				
				Ex6DataLine lineFromExternalFile = mergeFile
						.getLineFromHeadingValue(commonHeading, commonHeadVal);
				
				
				for (String head : headings) {
					if (line.checkIfHeadingExists(head)) {
						// in this file
						values.add(line.getData(head));
					} else if (lineFromExternalFile.checkIfHeadingExists(head)) {
						// its in the 2nd file
						values.add(lineFromExternalFile.getData(head));
					} else {
						values.add(" ");
					}
				}

				newFile.add(new Ex6DataLine(headings, values));
				file = newFile;
			}
		}
		writeToStream();
	}
}
