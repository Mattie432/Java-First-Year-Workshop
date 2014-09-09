package ex8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This is the score for the current game.
 * 
 * @author Mattie432
 * 
 */
@SuppressWarnings("serial")
public class Ex8ScoreBoard extends JPanel {

	private int score = 0;
	private int lines = 0;
	private int level = 1;

	private JTextField scoreTxt;
	private JTextField linesTxt;
	private JTextField levelTxt;

	/**
	 * Constructor for class, this sets the panels properties accordingly. It
	 * adds the textBoxes to the panel and sets ther default text.
	 * 
	 * @param width
	 *            Int - Width of the panel.
	 */
	public Ex8ScoreBoard(int width) {
		this.setOpaque(false);

		this.setLayout(new BorderLayout());

		scoreTxt = new JTextField();
		scoreTxt.setPreferredSize(new Dimension(width, 25));
		scoreTxt.setEditable(false);
		scoreTxt.setText("Score : " + score);
		this.add(scoreTxt, BorderLayout.NORTH);

		linesTxt = new JTextField();
		linesTxt.setEditable(false);
		linesTxt.setPreferredSize(new Dimension(width, 25));
		linesTxt.setText("Lines : " + getLines());
		this.add(linesTxt, BorderLayout.CENTER);

		levelTxt = new JTextField();
		levelTxt.setEditable(false);
		levelTxt.setPreferredSize(new Dimension(width, 25));
		levelTxt.setText("Level : " + getLevel());
		this.add(levelTxt, BorderLayout.SOUTH);
	}

	/**
	 * Called when a block is successfully placed, adds points accordingly and
	 * updates score shown on panel.
	 */
	public void placeBlock() {
		score += (10 * getLevel());
		setLines(getLines() + 1);
		scoreTxt.setText("Score : " + score);
	}

	/**
	 * Called when a row is successfully cleared, adds points accordingly and
	 * updates score shown on panel.
	 */
	public void clearRow() {
		score += (1000 * getLevel());
		linesTxt.setText("Lines : " + getLines());
	}

	/**
	 * Called when the level difficulty is changed, adds points accordingly and
	 * updates score shown on panel.
	 */
	public void nextLevel() {
		setLevel(getLevel() + 1);
		levelTxt.setText("Level : " + getLevel());
	}

	/**
	 * Resets all scores and the panels display.
	 */
	public void reset() {
		score = 0;
		setLines(0);
		setLevel(1);
		scoreTxt.setText("Score : " + score);
		linesTxt.setText("Lines : " + getLines());
		levelTxt.setText("Level : " + getLevel());
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLines() {
		return lines;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
