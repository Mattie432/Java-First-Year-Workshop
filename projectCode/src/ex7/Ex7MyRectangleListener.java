package ex7;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ex7MyRectangleListener extends MouseAdapter {

	Ex7Drawing d;
	Ex7Rectangle l;
	private Ex7UserInterface ui;

	/**
	 * Class constructor, sets the local variables of the Ex7Drawing and
	 * Ex7UserInterface.
	 * 
	 * @param drawing
	 *            Ex7Drawing - the drawing panel in use.
	 * @param userInterface
	 *            Ex7UserInterface - The user interface component.
	 */
	public Ex7MyRectangleListener(Ex7Drawing drawing,
			Ex7UserInterface userInterface) {
		d = drawing;
		ui = userInterface;
	}
	
	/**
	 * The event where the mouse is pressed. Creates a new Ex7Rectangle element
	 * and adds it to the Ex7Drawing.
	 */
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		l = new Ex7Rectangle(x, y, x, y);
		l.setColor(ui.getCurrentColor());
		d.addNewDrawingElement(l);
	}

	/**
	 * Updates the position of the Ex7Rectangle element when the mouse is moved
	 * and redraws it on the Ex7Drawing.
	 */
	public void mouseDragged(MouseEvent e) {
		updateSize(e);
		d.removeLastElem();
		d.addNewDrawingElement(l);
		d.repaint();
	}

	/**
	 * Sets the final end position for the Ex7Rectangle element.
	 */
	public void mouseReleased(MouseEvent e) {

		updateSize(e);
		d.removeLastElem();
		d.addNewDrawingElement(l);
		d.repaint();
		d.paintComponents(d.getGraphics());

		d.clearUndoList();
	}

	/**
	 * Update the secondary points to the current mouse position.
	 * 
	 * @param e
	 *            MouseEvent - mouse listener
	 */
	void updateSize(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		l.setPt2X(x);
		l.setPt2Y(y);
	}

}
