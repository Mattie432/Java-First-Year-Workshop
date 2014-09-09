package ex6;

import java.util.ArrayList;

public class Ex6DataLine {

	private ArrayList<String> columnHeadings;
	private ArrayList<String> data;

	/**
	 * Creates a new DataLine from the headings and values given. This is stored
	 * in ArrayList format.
	 * 
	 * @param columnHeadingsStr
	 *            - String
	 * @param dataStr
	 *            - String
	 * @throws Ex6UnequalLengthException
	 * @throws Ex6NullStringException
	 * @throws Ex6NoElementException
	 */
	public Ex6DataLine(String columnHeadingsStr, String dataStr)
			throws Ex6UnequalLengthException, Ex6NullStringException,
			Ex6NoElementException {

		if (countOccurrences(columnHeadingsStr, ',') == countOccurrences(
				dataStr, ',')) {
			columnHeadings = retrieveEntrys(columnHeadingsStr);
			data = retrieveEntrys(dataStr);

		} else {
			throw new Ex6UnequalLengthException(columnHeadingsStr, dataStr);
		}

	}

	/**
	 * Creates a new DataLine from the headings and values given. This is stored
	 * in ArrayList format.
	 * 
	 * @param columnHeadingsLst - ArrayList of type 'String'
	 * @param dataLst - ArrayList of type 'String'
	 * @throws Ex6UnequalLengthException
	 * @throws Exception
	 */
	public Ex6DataLine(ArrayList<String> columnHeadingsLst,
			ArrayList<String> dataLst) throws Ex6UnequalLengthException,
			Exception {
		String columnHeadingsStr = "";
		for (String str : columnHeadingsLst) {
			columnHeadingsStr += str + ",";
		}

		if (columnHeadingsStr.lastIndexOf(',') == columnHeadingsStr.length() - 1) {
			columnHeadingsStr = columnHeadingsStr.substring(0,
					columnHeadingsStr.length() - 1);
		}

		String dataStr = "";
		for (String str : dataLst) {
			dataStr += str + ",";
		}

		if (dataStr.lastIndexOf(',') == dataStr.length() - 1) {
			dataStr = dataStr.substring(0, dataStr.length() - 1);
		}
		if (countOccurrences(columnHeadingsStr, ',') == countOccurrences(
				dataStr, ',')) {
			columnHeadings = retrieveEntrys(columnHeadingsStr);
			data = retrieveEntrys(dataStr);
		} else {
			throw new Ex6UnequalLengthException(columnHeadingsStr, dataStr);
		}
	}

	/**
	 * Boolean return if the given heading is present in the dataline.
	 * @param heading - String
	 * @return - Boolean
	 */
	public boolean checkIfHeadingExists(String heading) {
		if (columnHeadings.contains(heading)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the data as a single string line, separate elements separated by commas.
	 * @return - String
	 */
	public String outDataLine() {
		String output = "";
		for (String str : data) {
			output += str + ",";
		}

		if (output.lastIndexOf(',') == output.length() - 1) {
			output = output.substring(0, output.length() - 1);
		}
		return output;
	}

	/**
	 * Returns the headings as a single string line, separate elements separated by commas.
	 * @return - String
	 */
	public String outHeadingsLine() {
		String output = "";
		for (String str : columnHeadings) {
			output += str + ",";
		}

		if (output.lastIndexOf(',') == output.length() - 1) {
			output = output.substring(0, output.length() - 1);
		}
		return output;
	}

	/**
	 * Sets the data value for the given column to the parameter.
	 * @param columnHeading - String
	 * @param dataStr - String
	 */
	public void setData(String columnHeading, String dataStr) {
		data.set(columnHeadings.indexOf(columnHeading), dataStr);
	}

	/**
	 * Gets the data value for the given column.
	 * @param columnHeading - String
	 * @return - String
	 */
	public String getData(String columnHeading) {
		return data.get(columnHeadings.indexOf(columnHeading));
	}

	/**
	 * Takes a string of data/headings (separated by commas) and returns the same data as an ArrayList of type 'String'
	 * @param str - String (data to turn to array list)
	 * @return - ArrayList of type 'String'
	 */
	public static ArrayList<String> retrieveEntrys(String str) {

		ArrayList<String> returnArr = new ArrayList<String>();
		int prevIndex = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ',') {
				returnArr.add(str.substring(prevIndex, i));
				prevIndex = i + 1;
			}
		}

		// gets last element
		returnArr.add(str.substring(prevIndex));
		return returnArr;
	}

	/**
	 * Getter method for the column headings.
	 * @return - ArrayList of type 'String'
	 */
	public ArrayList<String> getColumnHeadings() {
		return this.columnHeadings;
	}

	/**
	 * Counts the number of elements in a string of headings/data.
	 * @param str - String (of headings/data)
	 * @param character - Char (element separator)
	 * @return - int
	 * @throws Ex6NullStringException
	 * @throws Ex6NoElementException
	 */
	private static int countOccurrences(String str, char character)
			throws Ex6NullStringException, Ex6NoElementException {
		if (str != null) {
			int numStrings = 0;
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == character) {
					if (i > 0 && i < str.length() - 1) {
						// String occurrence before ','
						numStrings += 1;
					} else {
						throw new Ex6NoElementException(str);
					}
				}
			}

			// Adds count for final string after last ','
			if (str.lastIndexOf(character) < str.length() - 1) {
				numStrings += 1;
			}

			return numStrings;
		} else {
			// create new null string exception
			throw new Ex6NullStringException();
		}
	}

}
