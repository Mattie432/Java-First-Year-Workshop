package ex4;

import java.lang.Comparable;
import java.util.Arrays;

public class Ex4BinarySearch{
	/**
	 * Constructor for the class. Has no code as there are no internal fields to be set.
	 */
	private Ex4BinarySearch(){
		
	}
	
	
	/**
	 * Main method to test the binary search and array contains.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		String[ ] aryStrings = {"Autumn","Green", "Spring", "Summer", "Winter" };
		System.out.println("element is at index: " + binarySearch(aryStrings,"Winter"));
		System.out.println("element is in array: " + arrayContains(aryStrings,"Summer"));
	}

	
	/**
	 * Search an array for an element.
	 * @param array - Array of comparable elements 
	 * @param searchItem - Item to search for
	 * @return Index of item in array (int)
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static int binarySearch(Comparable[] array, Comparable searchItem) throws Exception{
		return binarySearchHelper(array,0,array.length,searchItem);
	}
	
	/**
	 * Method to check if an element is contained in the array.
	 * @param array - Array of comparable elements.
	 * @param searchItem - Item to search for.
	 * @return Boolean - is element in array.
	 */
	@SuppressWarnings("rawtypes")
	public static boolean arrayContains(Comparable[] array,Comparable searchItem){
		Arrays.sort(array);
		
		try {
		 binarySearch(array,searchItem);
		} catch (Exception e) {
			return false;
		}
		return true;
		
	}
	
	/**
	 * Check the range of the array for any errors.
	 * Ensures its a valid range.
	 * @param arrLength - Length of array
	 * @param startIndex - Start index
	 * @param endIndexplus1 - End index (+1)
	 */
	public static void rangeCheck(int arrLength, int startIndex, int endIndexplus1){
		if(endIndexplus1<startIndex){
			throw new IllegalArgumentException();
		}else if(endIndexplus1>arrLength | startIndex<0){
			throw new ArrayIndexOutOfBoundsException();
		}else{
			return;
		}
	}
	
	/**
	 * Recursive search of the array for the search item. 
	 * @param arr - Array of comparable elements
	 * @param startIndex - Start index of subarray
	 * @param endIndexplus1 - End index of subarray
	 * @param searchItem - Item to search for
	 * @return - Index of item in array as a whole (int)
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static int binarySearchHelper(Comparable[] arr, int startIndex, int endIndexplus1, Comparable searchItem) throws Exception{
		int range = endIndexplus1 - startIndex;
		rangeCheck(arr.length,startIndex,endIndexplus1);

		if(range>1){
			int index = startIndex + (range/2);
			int result = searchItem.compareTo(arr[index]);
			
			if(result == 0){
				return index;
			}else if(result < 0){
				return binarySearchHelper(arr,startIndex,index,searchItem);
			}else if(result > 0){
				return binarySearchHelper(arr,index,endIndexplus1,searchItem);
			}
		}

		throw new Ex4NotFoundException(searchItem);
	}
}