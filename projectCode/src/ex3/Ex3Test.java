package ex3;


public class Ex3Test {
	
	/**
	 * Main method, creates an array and prints the results from
	 * "printFirstFiveElements" of each sequence.
	 */
	public static void main(String[] args){
		
		int[] intArr = {9,8,7,6,5,4,3,2,1};
		
		Ex3GeometricIntSequence geoSeq = new Ex3GeometricIntSequence();
		Ex3ArithmeticIntSequence arithSeq = new Ex3ArithmeticIntSequence();
		Ex3ArrayIntSequence arraySeq = new Ex3ArrayIntSequence(intArr);

		System.out.print("Geometric Seq: ");
		printFirstFiveElements(geoSeq);
		System.out.print("Array Seq: ");
		printFirstFiveElements(arraySeq);
		System.out.print("Arithmetic Seq: ");
		printFirstFiveElements(arithSeq);
		
	}
	
	/**
	 * This prints the first element in the sequence followed by the next 4 from the sequence.
	 * @param intSeq - Ex3IntSequence1
	 */
	private static void printFirstFiveElements(Ex3IntSequence intSeq){
		System.out.print(intSeq.firstInt());
		for(int i = 0; i<4; i++){
			System.out.print(", " + intSeq.nextInt());
		}
		
		System.out.println();
	}
}
