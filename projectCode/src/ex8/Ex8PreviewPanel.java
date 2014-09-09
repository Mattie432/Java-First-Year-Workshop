package ex8;

import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Preview panel for viewing the nextShape from the gameboard.
 * 
 * @author Mattie432
 * 
 */
@SuppressWarnings("serial")
public class Ex8PreviewPanel extends JPanel {

	private Ex8GameBoard gameBoard;

	/**
	 * Constructor for the class, allows the previewPanel to interact with the
	 * gameBoard through a local variable.
	 * 
	 * @param gb
	 *            gameBoard - the gameboard in use
	 */
	public Ex8PreviewPanel(Ex8GameBoard gb) {
		gameBoard = gb;
	}

	/**
	 * Paints the panel with the nextShape being painted in the center.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (gameBoard != null && gameBoard.getNextShape() != null) {
			gameBoard.getNextShape().drawPreview(this, g);
		}
	}

}
