package ex7;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Ex7MyLineListener extends MouseAdapter {
	Ex7Drawing d;
	Ex7Line l;
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
	public Ex7MyLineListener(Ex7Drawing d, Ex7UserInterface userInterface) {
		this.d = d;
		this.ui = userInterface;
	}

	/**
	 * The event where the mouse is pressed. Creates a new Ex7Line element
	 * and adds it to the Ex7Line.
	 */
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		l = new Ex7Line(x, y, x, y);
		l.setColor(ui.getCurrentColor());
		d.addNewDrawingElement(l);
	}

	/**
	 * Updates the position of the Ex7Line element when the mouse is moved
	 * and redraws it on the Ex7Line.
	 */
	public void mouseDragged(MouseEvent e) {
		updateSize(e);
		d.removeLastElem();
		d.addNewDrawingElement(l);
		d.repaint();
	}

	/**
	 * Sets the final end position for the Ex7Line element.
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