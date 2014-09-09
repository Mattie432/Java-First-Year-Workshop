package ex7;

import java.awt.Graphics;

public class Ex7Ellipse extends Ex7DrawingElement {

	/**
	 * Constructor for the Ex7Ellipse element. Sets the constructor of the
	 * Ex7Drawing element.
	 * 
	 * @param pt1X	int - X Coordinate of first point.
	 * @param pt1Y	int - Y Coordinate of first point.
	 * @param pt2X	int - X Coordinate of second point.
	 * @param pt2Y	int - Y Coordinate of second point.
	 */
	protected Ex7Ellipse(int pt1X, int pt1Y, int pt2X, int pt2Y){
		super(pt1X, pt1Y, pt2X, pt2Y);
	}
	
	/**
	 * Draw method for the Ex7Ellipse object. 
	 */
	@Override
	public void draw(Graphics graphics) {
		int width =  Math.abs(getPt1X() - getPt2X());
		int height = Math.abs(getPt1Y() - getPt2Y());
		graphics.drawOval(getPt1X()-width, getPt1Y()-height,width*2, height*2);
	    
	}
	
	/**
	 * toString method for the Ex7Ellipse object.
	 * @return	string - "line"
	 */
	@Override
	public String toString() {
		return "Ellipse";
	}

}
