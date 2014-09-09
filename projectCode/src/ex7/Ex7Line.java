package ex7;

import java.awt.Graphics;

public class Ex7Line extends Ex7DrawingElement {

	/**
	 * Constructor for the Ex7line element. Sets the constructor of the
	 * Ex7Drawing element.
	 * 
	 * @param pt1X	int - X Coordinate of first point.
	 * @param pt1Y	int - Y Coordinate of first point.
	 * @param pt2X	int - X Coordinate of second point.
	 * @param pt2Y	int - Y Coordinate of second point.
	 */
	protected Ex7Line(int pt1X, int pt1Y, int pt2X, int pt2Y) {
		super(pt1X, pt1Y, pt2X, pt2Y);
	}

	/**
	 * Draw method for the Ex7Line object. 
	 */
	@Override
	public void draw(Graphics graphics) {
		graphics.drawLine(getPt1X(), getPt1Y(), getPt2X(), getPt2Y());
	}

	/**
	 * toString method for the Ex7Line object.
	 * @return	string - "line"
	 */
	@Override
	public String toString() {
		return "Line";
	}

}
