package ex6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex6Main {

	/**
	 * Main method. Runs when program is started.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			String file1str;
			String file2str;

			System.out.println("Please enter the path of the first file.");
			file1str = br.readLine();
			System.out.println("Please enter the path of the second file.");
			file2str = br.readLine();
			System.out.println();

			mergeTwoFiles(file1str, file2str);

			br.close();

		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}

	}

	/**
	 * Creates two files and calls the merge method between them.
	 * 
	 * @param file1str
	 *            - String (File1's path)
	 * @param file2str
	 *            - String (File2's path)
	 */
	private static void mergeTwoFiles(String file1str, String file2str) {

		try {
			
			Ex6DataFile file1 = new Ex6DataFile(new FileInputStream(file1str));
			Ex6DataFile file2 = new Ex6DataFile(new FileInputStream(file2str));
			file1.merge(file2);
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File not found.");
			System.out.println(e.getMessage());

		} catch (Ex6UnequalLengthException e) {
			System.out.println("ERROR: File's are of unequal length.");
			System.out.println(e.getMessage());

		} catch (IOException e) {
			System.out.println("ERROR: Problem with reading input for file.");
			System.out.println(e.getMessage());

		} catch (Ex6NullStringException e) {
			System.out.println("ERROR: String is null.");
			System.out.println(e.getMessage());
		} catch (Ex6NoElementException e) {
			System.out.println("ERROR: No element found");
			System.out.println(e.getMessage());
		} catch (Ex6NoUniqueHeadingException e) {
			System.out.println("Error:" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error: File empty.");
		}
	}

}
