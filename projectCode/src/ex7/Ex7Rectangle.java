package ex7;

import java.awt.Graphics;

public class Ex7Rectangle extends Ex7DrawingElement {

	/**
	 * Constructor for the Ex7line element. Sets the constructor of the
	 * Ex7Drawing element.
	 * 
	 * @param pt1X	int - X Coordinate of first point.
	 * @param pt1Y	int - Y Coordinate of first point.
	 * @param pt2X	int - X Coordinate of second point.
	 * @param pt2Y	int - Y Coordinate of second point.
	 */
	protected Ex7Rectangle(int pt1X, int pt1Y, int pt2X, int pt2Y){
		super(pt1X, pt1Y, pt2X, pt2Y);
	}
	
	/**
	 * Draw method for the Ex7Line object. 
	 */
	@Override
	public void draw(Graphics graphics) {
		int startX = 0 ;
		int startY =0;
		int width =0;
		int height=0;

		if (getPt1X() > getPt2X() && getPt1Y() < getPt2Y()) {
			// bottom left
			startX = getPt2X();
			width = getPt1X() - getPt2X();
			
			startY = getPt1Y();
			height = getPt2Y() - getPt1Y();
			
		} else if(getPt1X() > getPt2X() && getPt1Y() > getPt2Y()) {
			// top left
			startX = getPt2X();
			width = getPt1X() - getPt2X();
			
			startY = getPt2Y();
			height = getPt1Y() - getPt2Y();
		} else if(getPt1X() < getPt2X() && getPt1Y() < getPt2Y()){
			//bottom right
			startX = getPt1X();
			width = getPt2X() - getPt1X();
			
			startY = getPt1Y();
			height = getPt2Y() - getPt1Y();
		}else if(getPt1X() < getPt2X() && getPt1Y() > getPt2Y()){
			//top right
			startX = getPt1X();
			width = getPt2X() - getPt1X();
			
			startY = getPt2Y();
			height = getPt1Y() - getPt2Y();
		}
			graphics.drawRect(startX, startY, width, height);
	}

	/**
	 * toString method for the Ex7Line object.
	 * @return	string - "line"
	 */
	@Override
	public String toString() {
		return "Rectangle";
	}

}
