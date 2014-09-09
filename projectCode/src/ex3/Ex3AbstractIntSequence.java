package ex3;


public abstract class Ex3AbstractIntSequence implements Ex3IntSequence{

	private int prevIndex;
	private int firstIndex;
	
	
	/**
	 * This is the constructor which takes the firstIndex as a parameter and
	 * sets firstIndex and prevIndex equal to it.
	 * @param int - firstIndex
	 */
	public Ex3AbstractIntSequence(int firstIndex)
	   {
		this.firstIndex = firstIndex;
		this.prevIndex = firstIndex;
	   }

	/**
	 * This is an abstract method so it is expecting to be defined in the class which extends it.
	 * Its is public so that it is accessible within the subclass.
	 * @param
	 */
	protected abstract int getIntByIndex(int index);
	
	/**
	 * This returns the firstInt and resets the prevIndex = firstIndex.
	 * @return int - firstIndex 
	 */
	public int firstInt() {
		this.prevIndex = firstIndex;
		return getIntByIndex(firstIndex);
	}

	/**
	 * This returns the nextInt by incrementing the previous int by 1, then
	 * returning the previous int.
	 */
	public int nextInt() {
		this.prevIndex = prevIndex+1;
		return getIntByIndex(prevIndex);
		
	}

}
