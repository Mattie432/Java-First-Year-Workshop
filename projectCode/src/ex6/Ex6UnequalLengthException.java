package ex6;

public class Ex6UnequalLengthException extends Exception{

	private static final long serialVersionUID = 1L;

	/**
	 * Strings have different number of elements. 
	 * @param str1 - String
	 * @param str2 - String
	 */
	public Ex6UnequalLengthException(String str1, String str2){
		super("Strings have unequal number of elements. " + "String 1 = '" + str1 + "', String 2 = '" + str2 + "'");
	}
	
}
