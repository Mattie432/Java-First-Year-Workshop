package ex6;

public class Ex6NoUniqueHeadingException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4969558115590154341L;

	/**
	 * No common headings were found. 
	 */
	public Ex6NoUniqueHeadingException(){
		super("No common heading found");
	}
	
	/**
	 * More than one common heading was found.
	 * @param i - Int (number of common headings)
	 */
	public Ex6NoUniqueHeadingException(int i){
		super("There were " + i + " common headings fouund.");
	}
}
