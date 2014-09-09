package ex3;


public class Ex3ArrayIntSequence extends Ex3AbstractIntSequence{
	
	int[] arr;
	
	/**
	 * This is the constructor for the class, it sets the firstIndex to the first item
	 * in the array.
	 * @param arr - int array
	 */
	public Ex3ArrayIntSequence(int[] arr){
		super(0);
		this.arr = arr;
	}

	/**
	 * This gets the integer in the sequence at position index.
	 * @param index
	 * @return int
	 */
	protected int getIntByIndex(int index) {
		return arr[index];
		
	}
}
