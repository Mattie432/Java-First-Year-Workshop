package ex4;

public class Ex4NotFoundException extends Exception{

	private static final long serialVersionUID = -4846172201211444344L;

	/**
	 * Exception for when an element is not found.
	 * @param elem - search element
	 */
	public Ex4NotFoundException(Object elem){
			super("Element " + elem.toString() + " was not found in the array");
	}
	

}
