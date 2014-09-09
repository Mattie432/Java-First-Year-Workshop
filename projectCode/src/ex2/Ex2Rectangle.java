package ex2;

import fyw.turtles.*;
/**
 * Rectangle with configurable variables.
 * @author Matt
 */
public class Ex2Rectangle {
	
	private float left;
	private float top;
	private float width;
	private float height;
	
	/**
	 * Returns the value of the left side of the rectangle
	 * @return float
	 */
	public float getLeft(){ return left; }
	
	/**
	 * Returns the value of the top side of the rectangle
	 * @return float
	 */
	public float getTop(){ return top; }
	
	/**
	 * Returns the value of the height of the rectangle
	 * @return float
	 */
	public float getHeight(){ return height; }
	
	/**
	 * Returns the value of the width of the rectangle
	 * @return float
	 */
	public float getWidth(){ return width; }
	
	/**
	 * Calculates and returns the value of the right side of the rectangle.
	 * This is calculated by the value of the left side + the width.
	 * @return float
	 */
	public float getRight(){return (left + width);}
	
	/**
	 * Returns the value of the bottom of the rectangle. This is calculated by the top - height.
	 * @return float
	 */
	public float getBottom(){return (top - height);}
	
	/**
	 * Sets the value of left to the parameter specified.
	 * @param value - float
	 */
	public void setLeft(float value){ left = value; }
	
	/**
	 * Sets the value of the top to the parameter specified
	 * @param value - float
	 */
	public void setTop (float value){ top = value; }
	
	/**
	 * Sets the value of height to the parameter specified.
	 * Rectangle cannot have a negative height.
	 * @param value - float
	 */
	public void setHeight(float value){
		if(value<0){
			/*Error, cant be negative*/ 
		}else{
			height = value;
		}
	}
	
	/**
	 * Sets the value of width to the parameter specified.
	 * Width cannot be negative.
	 * @param value - float
	 */
	public void setWidth(float value){
		if(value<0){
			/*Error, cant be negative*/ 
		}else{
			width = value;
		}
	}
	
	/**
	 * Calculates the value that width needs to be in order for the
	 * right to be equal to the value. The equation is (width = value - left).
	 * The value of the right cannot be less than the value of the left. 
	 * @param value - float
	 */
	public void setRight(float value){
		if(value<left){
			/* Error, width cant be negative */
		}else{
			/*right - left because,
			 * right = left + width
			 * therefore rearrange to get
			 * width = right - left*/
			setWidth(value - left);
		}	
	}
	
	/**
	 * Calculates the value that height needs to be in order for the
	 * bottom to be equal to the value. The equation is (height = top - value).
	 * The value of the bottom cannot be more/higher than the value of the left. 
	 * @param value - float
	 */
	public void setBottom(float value){
		if(value>top){
			/*Error, height cant be negative*/
		}else{
			/*top - bottom because,
			 * bottom = top - width
			 * therefore rearrange to get width
			 * width = top - bottom*/
			setHeight(top - value);
		}
		
	}

	/**
	 * The random() method naturally a random number between 0.0 and 1.0.
	 * That value is then multiplied by (max - min), the difference between the lower and
	 * upper limit.<p> (e.g max = 90 min = 40) now ranges from 0.0. to 50.0. When 40 is 
	 * added to that value, the range becomes 40.0 to 90.0.
	 * @param min - Minimum range of random number
	 * @param max - Maximum range of random number
	 * @return float
	 */
	private static float randomNum(float min, float max){
		return (float) (Math.random() * (max - min) + min);
	}
	
	/**
	 * Constructor, used when the object is created and no variables are specified. This creates a rectangle
	 * with random size and position. The range for the size is between 1 & 100, and the range of
	 * the position is between -200 & 200.
	 */
	public Ex2Rectangle(){
		/*width & height must be in the range 1 <= x <= 100 */
		setWidth( randomNum(5,100) );
		setHeight( randomNum(5,100) );
		
		/*left & top can be in the range "-200 <= x <= 200"*/
		setTop(randomNum(-200,200));
		setLeft(randomNum(-200,200));
	}
	
	/**
	 * Constructor, used when the object is created and variables are specified. This sets
	 * the variables of the rectangle to the parameters specified.
	 * @param height_ - float
	 * @param width_ - float
	 * @param top_ - float
	 * @param left_ - float
	 */
	public Ex2Rectangle(float height_, float width_,float top_, float left_){
		setHeight(height_);
		setWidth(width_);
		setTop(top_);
		setLeft(left_);
		
	}

/**
 * This draws the rectangle with the turtle object parameter. It first moves to the
 * correct position on the axis using the values of top and left. Then it draws the
 * rectangle using the values of width and height, after this is returns to its point
 * of origin.
 * @param theTurtle - Turtle
 */
	public void draw(Turtle theTurtle){
		//Move to position
		theTurtle.penUp();
		theTurtle.turn(90);
		theTurtle.move(getLeft());
		theTurtle.turn(-90);
		theTurtle.move(getTop());
		
		//draw rectangle
		theTurtle.penDown();
		theTurtle.turn(90);
		theTurtle.move(getWidth());
		theTurtle.turn(90);
		theTurtle.move(getHeight());
		theTurtle.turn(90);
		theTurtle.move(getWidth());
		theTurtle.turn(90);
		theTurtle.move(getHeight());
		theTurtle.turn(-360);
		
		//return to start pos
		theTurtle.penUp();
		theTurtle.move(-1*getTop());
		theTurtle.turn(90);
		theTurtle.move(-1*getLeft());
		theTurtle.turn(-90);
		
		
		
	}
}
