package ex8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 * This class sets up the 'game over' message when the user fails the game.
 * 
 * @author Mattie432
 * 
 */
@SuppressWarnings("serial")
public class Ex8GameOver extends JFrame {

	int height = 150;
	int width = 200;
	private Ex8ScoreBoard scoreBoard;

	/**
	 * Constructor for class, allows the use of a local variable to retrieve the
	 * score from the scoreboard. This also sets the windows size and layout
	 * properties.
	 * 
	 * @param scoreBoard
	 *            ScoreBoard - the scoreboard of the current game.
	 */
	public Ex8GameOver(Ex8ScoreBoard scoreBoard) {
		this.scoreBoard = scoreBoard;
		this.setResizable(false);
		this.setPreferredSize(new Dimension(width, height));
		this.setSize(new Dimension(width, height));
		this.setLayout(new BorderLayout());

		repaint();
		this.setVisible(true);
	}

	/**
	 * Paints the JFrame with the message 'game over' and the scores of the last
	 * game.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawString("Game Over", width / 2 - 50, 50);
		g.drawString("Score : " + scoreBoard.getScore(), width / 2 - 50, 70);
		g.drawString("Level : " + scoreBoard.getLevel(), width / 2 - 50, 85);
		g.drawString("Lines broken : " + scoreBoard.getLevel(), width / 2 - 50,
				100);
	}
}
