package ex2;

import fyw.turtles.*;
public class Ex2RectangleMethods {
	
	/**
	 * This runs when the program is first launched. It creates the turtle object and GUI
	 * and then calls the randomArray method to create an array of the number of rectangles specified.
	 * Each of these rectangles is then drawn and the program then calls the boundingBox method and
	 * draws the surrounding box it creates. 
	 * @param args
	 */
	public static void main(String args[]){
		int numRectangles = 5;
		Turtle theTurtle = new Turtle();
		new TurtleGUI(theTurtle);
		
		Ex2Rectangle[] arrayRect = RandomArray(numRectangles);
		
		for(Ex2Rectangle elem : arrayRect){
			elem.draw(theTurtle);
		}
		
		Ex2Rectangle boundingBox = BoundingBox(arrayRect);
		if(boundingBox != null){
			boundingBox.draw(theTurtle);
		}
	}

/**
 * This method creates a random array of rectangles of a size specified. Each new rectangle is
 * placed into the array and the entire array is returned once filled.
 * <p>This is static because it applies only to this class and not any object, it does not rely
 * on an object to perform this method.
 * @param length - int
 * @return - Ex2Rectangle[]
 */
	private static Ex2Rectangle[] RandomArray(int length){
		Ex2Rectangle[] arrayRect = new Ex2Rectangle[length];
		
		for(int i=0; i<length;i=i+1){
			Ex2Rectangle rect = new Ex2Rectangle();
			arrayRect[i] = rect;
		}
		
		return arrayRect;
	}	
	
	/**
	 * This takes an array of rectangles as a parameter and cycles through each of them checking
	 * if each rectangle is outside of the boundingBox rectangle. If it is then the bounding box is
	 * extended to incorporate that rectangle.
	 * @param arrayRect - Ex2Rectangle[]
	 * @return Ex2Rectangle
	 */
	public static Ex2Rectangle BoundingBox(Ex2Rectangle[] arrayRect){
		
		if(arrayRect.length >0){

			Ex2Rectangle Box = new Ex2Rectangle(arrayRect[0].getHeight(),arrayRect[0].getWidth(),arrayRect[0].getTop(),arrayRect[0].getLeft());
			for(Ex2Rectangle rect : arrayRect){
				 
				if(rect.getLeft() <= Box.getLeft()){
					Box.setLeft(rect.getLeft());
				}
				
				if(rect.getTop() >= Box.getTop()){
					Box.setTop(rect.getTop());
				}
			}
			
			for(Ex2Rectangle rect : arrayRect){
				if(rect.getBottom() <= Box.getBottom()){
					Box.setBottom(rect.getBottom());
				}
			}
			
			for(Ex2Rectangle rect : arrayRect){
				if(rect.getRight() >= Box.getRight()){
					Box.setRight(rect.getRight());
				}
			}
			
			
			return Box;
		}else{
			return null;
		}
		
		
	}
}
