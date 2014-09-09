package ex6;

public class Ex6NoElementException extends Exception {

	private static final long serialVersionUID = 7516740739330812592L;
/**
 * No element in the string when there was on expected.
 * @param str - String with missing element.
 */
	public Ex6NoElementException(String str){
		super("Element missing from string : " + str);
	}

}
