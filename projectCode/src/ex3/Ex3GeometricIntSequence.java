package ex3;


public class Ex3GeometricIntSequence implements Ex3IntSequence{

	private int mostRecent;
	
	/**
	 * This returns the first int in the sequence (which is always 1) so we set the most recent to 1
	 * and then return the most recent.
	 */
	public int firstInt() {
		mostRecent = 1;
		return mostRecent;
	}

	/**
	 * This returns the next int in the sequence, this is equal to the previous int * 2.
	 */
	public int nextInt() {
		mostRecent = (mostRecent*2);
		return mostRecent;
	}
	
}
