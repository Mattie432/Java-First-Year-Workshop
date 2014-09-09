package ex6;

public class Ex6NullStringException extends Exception{

	private static final long serialVersionUID = 7768553367722536601L;

	/**
	 * String contains no data.
	 */
	public Ex6NullStringException(){
		super("Empty String");
	}

}
