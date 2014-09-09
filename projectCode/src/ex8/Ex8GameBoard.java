package ex8;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import ex8.shapes.Ex8Block;
import ex8.shapes.Ex8L_Shape;
import ex8.shapes.Ex8J_Shape;
import ex8.shapes.Ex8Line;
import ex8.shapes.Ex8S_Shape;
import ex8.shapes.Ex8Shape;
import ex8.shapes.Ex8Square;
import ex8.shapes.Ex8T_Shape;
import ex8.shapes.Ex8Z_Shape;

@SuppressWarnings("serial")
public class Ex8GameBoard extends JPanel implements KeyListener, MouseListener {

	/**
	 * This is the timer which makes the blocks fall (initially each second).
	 * The speed gradually increases over time.
	 */
	private final javax.swing.Timer timer = new javax.swing.Timer(1000,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					long elapsedTime = System.nanoTime() - sysTimeLast;
					double seconds = (double) elapsedTime / 1000000000.0;
					if (seconds >= 45) {
						// 45sec passed
						// increase difficulty
						timer.setDelay((int) (timer.getDelay() * 0.9f));
						userInterface.getScoreBoard().nextLevel();
						sysTimeLast = System.nanoTime();
					}
					if (movingShape != null && getPlay() == true) {
						movingShape.moveDown();
					}
				}
			});

	private boolean play = false;
	private boolean gameOver = true;
	private Ex8UserInterface userInterface;
	private ArrayList<Ex8Block> stationaryBlocks = new ArrayList<Ex8Block>();
	private Ex8Shape movingShape;
	private Ex8Shape nextShape;
	private int cellWidthHeight = 25;
	private int numColumns;
	private int numRows;
	private long sysTimeLast;

	/**
	 * Constructor for the class. This creates the gameboard to the correct size
	 * and allows the gameboard to interact with the user interface through a
	 * local variable.
	 * 
	 * @param width
	 *            Int - width of the board
	 * @param height
	 *            Int - height of the board
	 * @param ui
	 *            UserInterface - the user interface.
	 */
	public Ex8GameBoard(int width, int height, Ex8UserInterface ui) {
		userInterface = ui;
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addMouseListener(this);

		int fixedWidth = (((int) (width * 0.8) / cellWidthHeight) * cellWidthHeight);
		int fixedHeight = (((int) (height) / cellWidthHeight) * cellWidthHeight);
		setNumColumns(fixedWidth / cellWidthHeight);
		setNumRows(fixedHeight / cellWidthHeight);

		this.setSize(new Dimension(fixedWidth, height));
		this.setPreferredSize(new Dimension(fixedWidth, height));

	}

	/**
	 * This method is called to start the game. It sorts out the timer, clears
	 * any previous game and creates a new movingShape and nextShape.
	 */
	public void startNewGame() {

		sysTimeLast = System.nanoTime();
		timer.setDelay(1000);
		stationaryBlocks.clear();
		movingShape = null;
		nextShape = null;
		userInterface.getScoreBoard().reset();
		movingShape = newShape();
		nextShape = newShape();
		// userInterface.repaint();
		repaint();
	}

	/**
	 * Calls the drawAll method to draw all shapes active in the game.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// drawGridLines(g);
		drawAll(g);
	}

	/**
	 * Creates a new shape, this is randomly picked from the 7 available shapes
	 * and returns it.
	 * 
	 * @return Shape - the crated shape.
	 */
	public Ex8Shape newShape() {
		Ex8Shape newShape = null;

		int Max = 7;
		int rand = 1 + (int) (Math.random() * ((Max - 1) + 1));

		if (rand == 1) {
			newShape = new Ex8S_Shape(this);
		} else if (rand == 2) {
			newShape = new Ex8L_Shape(this);
		} else if (rand == 3) {
			newShape = new Ex8J_Shape(this);
		} else if (rand == 4) {
			newShape = new Ex8Line(this);
		} else if (rand == 5) {
			newShape = new Ex8T_Shape(this);
		} else if (rand == 6) {
			newShape = new Ex8Square(this);
		} else if (rand == 7) {
			newShape = new Ex8Z_Shape(this);
		}
		return newShape;
	}

	/**
	 * This swaps the moving shape for the next shape and then creates a new
	 * nextShape. It also calls the method to check if there is a full line.The
	 * moving shape is painted to the gameBoard and the next shape painted to
	 * the preview panel.
	 */
	public void changeMovingShape() {
		if (movingShape != null && movingShape.blocks != null) {
			stationaryBlocks.addAll(movingShape.getBlocks());
		}
		checkForFullLine();
		userInterface.getScoreBoard().placeBlock();
		if (!checkIfAtTop()) {
			movingShape = nextShape;
			nextShape = newShape();
			userInterface.getPreviewPanel().repaint();
		}
	}

	/**
	 * <b> ! Unused ! <b> This draws gridlines to the provided graphics object.
	 * 
	 * @param g
	 *            Graphics - graphics to draw with.
	 */
	@SuppressWarnings("unused")
	private void drawGridLines(Graphics g) {
		for (int rows = 0; rows <= numRows; rows = rows + 1) {
			g.drawLine(0, rows * cellWidthHeight, numColumns * cellWidthHeight,
					rows * cellWidthHeight);
		}
		for (int columns = 0; columns <= numColumns; columns = columns + 1) {
			g.drawLine(columns * cellWidthHeight, 0, columns * cellWidthHeight,
					numRows * cellWidthHeight);
		}
	}

	/**
	 * This calls the draw method for all of the stationary and moving blocks,
	 * effectively drawing all blocks on the gameBoard.
	 * 
	 * @param g
	 *            Graphics - graphics object to draw with
	 */
	public void drawAll(Graphics g) {
		if (stationaryBlocks != null) {
			for (Ex8Block e : stationaryBlocks) {
				e.draw(g);
			}
		}

		if (movingShape != null) {
			movingShape.draw(g);
		}
	}

	/**
	 * Checks if there are any collisions with stationarry blocks at the given
	 * coordinate.
	 * 
	 * @param x
	 *            Int - X coordinate
	 * @param y
	 *            Int - Y coordinate
	 * @return Boolean - Returns true if there are any collisions.
	 */
	public boolean checkCollisions(int x, int y) {
		boolean state = false;

		for (Ex8Block stationaryBlock : stationaryBlocks) {
			if (stationaryBlock.getX() == x && stationaryBlock.getY() == y) {
				state = true;
			}
		}
		return state;
	}

	/**
	 * Checks that the given coordinate is a valid point on the GameBoard.
	 * 
	 * @param x
	 *            Int - X coordinate
	 * @param y
	 *            Int - Y coordinate
	 * @return Boolean - Returns true if it is a vaild point.
	 */
	public boolean checkValidPoint(int x, int y) {
		if (x < (numColumns) && y < (numRows) && x >= 0 && y >= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Getter method for the number of rows in the GridBoard.
	 * 
	 * @return Int - number of rows
	 */
	public int getNumRows() {
		return numRows;
	}

	/**
	 * Setter method for the number of rows in the gridboard.
	 * 
	 * @param numRows
	 *            Int - number of rows
	 */
	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	/**
	 * Getter method for the number of columns.
	 * 
	 * @return Int - number of columns
	 */
	public int getNumColumns() {
		return numColumns;
	}

	/**
	 * Setter method for the number of columns.
	 * 
	 * @param numColumns
	 *            Int - number of columns
	 */
	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
	}

	/**
	 * Checks if there is a full line in the stationary blocks. If there is then
	 * it removes it and adds points accordingly and calls the
	 * moveDownUntilBlocked() method of each block before recalling its self to
	 * check for more lines.
	 */
	public void checkForFullLine() {

		for (int i = numRows; i >= 0; i--) {
			ArrayList<Ex8Block> line = new ArrayList<Ex8Block>();
			for (Ex8Block block : stationaryBlocks) {
				if (block.getY() == i) {
					line.add(block);
				}
			}
			if (line.size() == numColumns) {
				// Remove line at onece
				stationaryBlocks.removeAll(line);
				this.repaint();

				// for (Block b : line) {
				// stationaryBlocks.remove(b);
				// this.repaint();
				// try {
				// Thread.sleep(100);
				// } catch (InterruptedException e) {
				// continue;
				// }
				// }

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
				for (int y = numRows - 1; y >= 0; y--) {
					for (Ex8Block stationaryBlock : stationaryBlocks) {
						if (stationaryBlock.getY() == y) {
							stationaryBlock.moveDownUntilBlocked();
						}
					}
				}
				this.repaint();
				userInterface.getScoreBoard().clearRow();
				checkForFullLine();
				return;
			}
		}
	}

	/**
	 * Checks if the blocks are piled up the the top of the gameboard.
	 * 
	 * @return Boolean - is at top.
	 */
	public boolean checkIfAtTop() {
		for (Ex8Block block : stationaryBlocks) {
			if (block.getY() <= 0) {
				movingShape = null;
				gameOver = true;
				timer.stop();
				userInterface.resetAll();

				// inform end of game
				new Ex8GameOver(userInterface.getScoreBoard());
				return true;
			}
		}
		return false;
	}

	/**
	 * Getter method for the stationary blocks
	 * 
	 * @return Block[] - Stationary blocks
	 */
	public ArrayList<Ex8Block> getStationaryBlocks() {
		return stationaryBlocks;
	}

	/**
	 * Getter method for the cellWidthheight.
	 * 
	 * @return Int - cellWidthHeight
	 */
	public int getCellWidthHeight() {
		return cellWidthHeight;
	}

	/**
	 * Getter method for the moving shape.
	 * 
	 * @return Shape - moving shape
	 */
	public Ex8Shape getMovingShape() {
		return movingShape;
	}

	/**
	 * Setter method for the moving shape.
	 * 
	 * @param movingShape
	 *            Shape - moving shape.
	 */
	public void setMovingShape(Ex8Shape movingShape) {
		this.movingShape = movingShape;
	}

	/**
	 * Getter method for the play state
	 * 
	 * @return Boolean - play
	 */
	public boolean getPlay() {
		return play;
	}

	/**
	 * Setter method for the play state
	 * 
	 * @param play
	 *            Boolean - play state
	 */
	public void setPlay(boolean play) {
		this.play = play;
	}

	/**
	 * Getter method for the timer.
	 * 
	 * @return Timer - the game timer
	 */
	public javax.swing.Timer getTimer() {
		return timer;
	}

	/**
	 * Getter method for the gameover state
	 * 
	 * @return Boolean - Game over
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * Setter method for the gameover state
	 * 
	 * @param gameOver
	 *            Boolean - game over
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * Getter method for the next shape.
	 * 
	 * @return Shape - next shape
	 */
	public Ex8Shape getNextShape() {
		return nextShape;
	}

	/**
	 * KeyPresed event, this is triggered when the user presses a key and
	 * performs the move in-game depending upon which key was pressed.
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		if (movingShape != null && play == true) {
			if (arg0.getKeyCode() == 39) {
				// right arrow
				movingShape.moveRight();
				this.repaint();
			} else if (arg0.getKeyCode() == 37) {
				// left arrow
				movingShape.moveLeft();
				this.repaint();
			} else if (arg0.getKeyCode() == 40) {
				// down arrow
				movingShape.moveDown();
				this.repaint();
			} else if (arg0.getKeyCode() == 38) {
				// rotate
				movingShape.rotate();
				this.repaint();
			}
		}

		// System.out.println(arg0.getKeyCode());
	}

	/**
	 * Mouse clicked event for the gameboard, shifts focus to the gameboard when
	 * clicked.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		this.requestFocusInWindow();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
