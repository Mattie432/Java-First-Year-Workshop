package ex7;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This is the panel that all drawings are displayed on.
 * 
 * @author Mattie432
 */
@SuppressWarnings("serial")
public class Ex7Drawing extends JPanel {

	private JButton undoBtn;
	private JButton redoBtn;

	ArrayList<Ex7DrawingElement> listElems = new ArrayList<Ex7DrawingElement>();
	Stack<Ex7DrawingElement> undoElems = new Stack<Ex7DrawingElement>();

	/**
	 * Constructor for the Ex7Drawing class. Sets the local button variables to
	 * the undo and redo buttons.
	 * 
	 * @param undoBtn
	 *            JButton - the undo button from the user interface.
	 * @param redoBtn
	 *            JButton - the redo button from the user interface.
	 */
	public Ex7Drawing(JButton undoBtn, JButton redoBtn) {
		this.undoBtn = undoBtn;
		this.redoBtn = redoBtn;
	}

	/**
	 * Adds a drawing element to the current drawing.
	 * 
	 * @param elem
	 *            Ex7DrawingElement - a drawing element to be added.
	 */
	public void addNewDrawingElement(Ex7DrawingElement elem) {
		listElems.add(elem);
	}

	/**
	 * Returns the last element from the current drawing.
	 * 
	 * @return Ex7DrawingElement - last element added.
	 */
	public Ex7DrawingElement getLastElem() {
		return listElems.get(listElems.size() - 1);
	}

	/**
	 * Removes the last element added from the current drawing.
	 */
	public void removeLastElem() {
		listElems.remove(listElems.size() - 1);
	}

	/**
	 * Paints all the elements added to the drawing to the Ex7DrawingPanel.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (listElems != null) {

			for (int i = 0; i < listElems.size(); i++) {
				Ex7DrawingElement e = listElems.get(i);
				g.setColor(e.getColor());
				e.draw(g);
			}
		}
	}

	/**
	 * Checks if the undo button should be enabled and toggles the enabled state
	 * accordingly.
	 */
	public void checkUndoEnabled() {
		if (listElems.isEmpty()) {
			undoBtn.setEnabled(false);
		} else {
			undoBtn.setEnabled(true);
		}
	}

	/**
	 * Checks if the redo button should be enabled and toggles the enabled state
	 * accordingly.
	 */
	public void checkRedoEnabled() {
		if (undoElems.isEmpty()) {
			redoBtn.setEnabled(false);
		} else {
			redoBtn.setEnabled(true);
		}
	}

	/**
	 * Undo method, removes the last element added from the Ex7DrawingElement
	 * list and adds it to the undo stack.
	 */
	public void undo() {
		undoElems.push(listElems.get(listElems.size() - 1));
		removeLastElem();
	}

	/**
	 * Redo method, takes the last element from the undo stack and adds it back
	 * to the drawings element list.
	 */
	public void redo() {
		listElems.add(undoElems.pop());
	}

	/**
	 * Clear the undo list (should be called when the user draws a different
	 * element after clicking undo). Also calls the check undo/redo buttons
	 * methods.
	 */
	public void clearUndoList() {
		undoElems.clear();
		checkRedoEnabled();
		checkUndoEnabled();
	}
}
