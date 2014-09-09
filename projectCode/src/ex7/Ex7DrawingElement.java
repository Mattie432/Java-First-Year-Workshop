package ex7;
import java.awt.Color;
import java.awt.Graphics;
public abstract class Ex7DrawingElement {

	private int pt1X;
	private int pt1Y;
	private int pt2X;
	private int pt2Y;
	private Color color = Color.BLACK;

	/**
	 * Constructor for the Ex7DrawingElement, sets the private x & y coordinates
	 * for the first and second points.
	 * 
	 * @param pt1X
	 *            int - X Coordinate for the first point
	 * @param pt1Y
	 *            int - Y Coordinate for the first point
	 * @param pt2X
	 *            int - X Coordinate for the second point
	 * @param pt2Y
	 *            int - Y Coordinate for the second point
	 */
	protected Ex7DrawingElement(int pt1X, int pt1Y, int pt2X, int pt2Y) {
		this.setPt1X(pt1X);
		this.setPt1Y(pt1Y);
		this.setPt2Y(pt2Y);
		this.setPt2X(pt2X);
	}

	/**
	 * Abstract draw method
	 * @param graphics	Graphics - Graphics object to draw to
	 */
	public abstract void draw(Graphics graphics);

	/**
	 * Getter method for the X Coordinate for the first point
	 * @return	int - X Coordinate
	 */
	public int getPt1X() {
		return pt1X;
	}

	/**
	 * Setter method for the X Coordinate for the first point.
	 * @param pt1X	int - X Coordinate
	 */
	public void setPt1X(int pt1X) {
		this.pt1X = pt1X;
	}

	/**
	 * Getter method for the Y Coordinate for the first point
	 * @return	int - Y Coordinate
	 */
	public int getPt1Y() {
		return pt1Y;
	}

	/**
	 * Setter method for the Y Coordinate for the first point.
	 * @param pt1Y	int - Y Coordinate
	 */
	public void setPt1Y(int pt1Y) {
		this.pt1Y = pt1Y;
	}

	/**
	 * Getter method for the X Coordinate for the second point.
	 * @return	int - X Coordinate
	 */
	public int getPt2X() {
		return pt2X;
	}

	/**
	 * Setter method for the X Coordinate for the second point.
	 * @param pt2X	int - X Coordinate
	 */
	public void setPt2X(int pt2X) {
		this.pt2X = pt2X;
	}

	/**
	 * Getter method for the Y Coordinate for the second point.
	 * @return	int - Y Coordinate
	 */
	public int getPt2Y() {
		return pt2Y;
	}

	/**
	 * Setter method for the Y Coordinate for the second point.
	 * @param pt2Y	int - Y Coordinate
	 */
	public void setPt2Y(int pt2Y) {
		this.pt2Y = pt2Y;
	}

	/**
	 * Abstract method to override toString
	 */
	@Override
	public abstract String toString();

	/**
	 * Getter method for the colour of the current Ex7DrawingElement.
	 * @return	Color - Color for the element.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Setter method for the colour of the current Ex7DrawingElement
	 * @param color	Colour - Colour of the current element.
	 */
	public void setColor(Color color) {
		this.color = color;
	}
}
