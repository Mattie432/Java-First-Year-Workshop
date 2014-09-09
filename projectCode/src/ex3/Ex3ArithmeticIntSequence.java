package ex3;


public class Ex3ArithmeticIntSequence extends Ex3AbstractIntSequence{

	/**
	 * This is the constructor for the class, it initializes the firstIndex to 1.
	 */
	public Ex3ArithmeticIntSequence() {
		super(1);
	}

	/**
	 * This returns the index supplied to it.
	 * @return int
	 */
	protected int getIntByIndex(int index) {
		return index;
	}
}
