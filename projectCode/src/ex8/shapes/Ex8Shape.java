package ex8.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import ex8.Ex8Direction;
import ex8.Ex8GameBoard;

/**
 * Abstract class to represent a 'shape' made up of blocks.
 * 
 * @author Mattie432
 * 
 */
public abstract class Ex8Shape {

	private int shapeWidth;
	private int shapeheight;
	private int posX;
	private int posY;
	private int currentRotation;
	private Color color;

	public ArrayList<Ex8Block> blocks = new ArrayList<Ex8Block>();
	public Ex8GameBoard gameBoard;

	/**
	 * Constructor for the class, allows the shape to connect with the GameBoard
	 * in use through a local variable. Also sets the shapes rotation randomly,
	 * calls the create shape method and checks its starting position is valid.
	 * 
	 * @param gameBoard
	 *            GameBoard - the GameBoard in use.
	 */
	public Ex8Shape(Ex8GameBoard gameBoard) {
		this.gameBoard = gameBoard;

		setCurrentRotation(randomNum(1, 4));

		createShape();
		moveToValidPosition(1);
	}

	/**
	 * Moves the shape left by calling the move position of all of the blocks in
	 * the 'Blocks' array.
	 */
	public void moveLeft() {
		if (checkValidMove(Ex8Direction.LEFT)) {
			for (Ex8Block block : blocks) {
				block.moveLeft(true, true);
			}
			posX = posX - 1;
		}
		gameBoard.repaint();
	}

	/**
	 * Moves the shape right by calling the move position of all of the blocks
	 * in the 'Blocks' array.
	 */
	public void moveRight() {
		if (checkValidMove(Ex8Direction.RIGHT)) {
			for (Ex8Block block : blocks) {
				block.moveRight(true, true);
			}
			posX = posX + 1;
		}
		gameBoard.repaint();
	}

	/**
	 * Moves the shape down by calling the move position of all of the blocks in
	 * the 'Blocks' array.
	 */
	public void moveDown() {
		if (checkValidMove(Ex8Direction.DOWN)) {
			for (Ex8Block block : blocks) {
				block.moveDown(true, true);
			}
			posY = posY + 1;
		}
		checkCanMove();
		gameBoard.repaint();
	}

	/**
	 * <b>! Unused !</b> Moves the shape up by calling the move position of all
	 * of the blocks in the 'Blocks' array.
	 */
	public void moveUp() {
		if (checkValidMove(Ex8Direction.UP)) {
			for (Ex8Block block : blocks) {
				block.moveDown(true, true);
			}
			posY = posY - 1;
		}
		checkCanMove();
	}

	/**
	 * Checks if the point at the parameters specified is inside the shape.
	 * 
	 * @param x
	 *            Int - X coordinate
	 * @param y
	 *            Int - Y coordinate
	 * @return Boolean - is the point inside the shape.
	 */
	public boolean checkIfPartOfShape(int x, int y) {
		for (Ex8Block block : blocks) {
			if (x == block.getX() && y == block.getY()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This checks that a move one block in the specified direction is valid and
	 * that it will not collide with any stationary blocks.
	 * 
	 * @param direction
	 *            Direction - the direction to move
	 * @return Boolean - is valid.
	 */
	public boolean checkValidMove(Ex8Direction direction) {
		for (Ex8Block block : blocks) {
			if (direction == Ex8Direction.LEFT) {
				if (gameBoard.checkCollisions(block.getX() - 1, block.getY())
						|| !gameBoard.checkValidPoint(block.getX() - 1,
								block.getY())) {
					return false;
				}
			} else if (direction == Ex8Direction.RIGHT) {
				if (gameBoard.checkCollisions(block.getX() + 1, block.getY())
						|| !gameBoard.checkValidPoint(block.getX() + 1,
								block.getY())) {
					return false;
				}
			} else if (direction == Ex8Direction.DOWN) {
				if (gameBoard.checkCollisions(block.getX(), block.getY() + 1)
						|| !gameBoard.checkValidPoint(block.getX(),
								block.getY() + 1)) {
					return false;
				}
			} else if (direction == Ex8Direction.UP) {
				if (gameBoard.checkCollisions(block.getX(), block.getY() - 1)
						|| !gameBoard.checkValidPoint(block.getX(),
								block.getY() - 1)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Called when creating the shape. This checks if any of the blocks are in
	 * an invalid position and moves the shape accordingly to fix this.
	 * 
	 * @param attempt
	 *            Int - start at 1
	 * @return Int - attempt number.
	 */
	public int moveToValidPosition(int attempt) {
		if (attempt < 4) {
			for (Ex8Block b : blocks) {
				if (!gameBoard.checkValidPoint(b.getX(), b.getY())) {
					// (x < (numColumns) && y < (numRows) && x >= 0 && y >= 0)

					if (b.getX() < 0) {
						for (Ex8Block block : blocks) {
							block.moveRight(true, false);
						}
						posX = posX + 1;

						checkCanMove();
					} else if (b.getX() >= gameBoard.getNumColumns()) {
						for (Ex8Block block : blocks) {
							block.moveLeft(true, false);
						}
						posX = posX - 1;

						checkCanMove();
					} else if (b.getY() < 0) {
						for (Ex8Block block : blocks) {
							block.moveDown(true, false);
						}
						posY = posY + 1;
						checkCanMove();
					} else if (b.getY() > gameBoard.getNumRows()) {
						for (Ex8Block block : blocks) {
							block.moveDown(true, false);
						}
						posY = posY - 1;
						checkCanMove();
					}

					return moveToValidPosition(attempt + 1);
				}
			}
		}
		return attempt;
	}

	/**
	 * Creates a random number between the values specified.
	 * 
	 * @param min
	 *            Int - min value
	 * @param max
	 *            Int - max value
	 * @return Int - Random number
	 */
	public int randomNum(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}

	/**
	 * Method to create the shape initially, this sets the size in the super
	 * class and calls the 'shapeType' method to create the shape in a random
	 * rotation.
	 */
	abstract void createShape();

	public void rotate() {
		if (blocks.size() > 0) {
			ArrayList<Ex8Block> temp = blocks;

			if (getCurrentRotation() < 4) {
				setCurrentRotation(getCurrentRotation() + 1);
			} else {
				setCurrentRotation(1);
			}
			blocks.clear();
			shapeType(getCurrentRotation());
			if (moveToValidPosition(1) >= 4) {
				blocks = temp;
			}

			for (Ex8Block b : blocks) {
				if (gameBoard.checkCollisions(b.getX(), b.getY())) {
					rotate();
					break;
				}
			}
			setColor(color);
		}
	}

	/**
	 * Creates the shape in the rotation specified by the int parameter. This
	 * creates the blocks in the required positions and adds them to the
	 * 'blocks' array.
	 * 
	 * @param x
	 *            Int - between 1 & 4 (inclusive)
	 */
	abstract void shapeType(int x);

	/**
	 * Draws the shape to the preview panel (used to show is user the next
	 * shape).
	 * 
	 * @param p
	 *            JPanel - the panel to draw on.
	 * @param g
	 *            Graphics - the graphics object to use.
	 */
	public abstract void drawPreview(JPanel p, Graphics g);

	/**
	 * Picks a random start position along the X axis with the possible positons
	 * being relative to the shape. (so that it will not be created outside the
	 * available area).
	 * 
	 * @return Int - start position X coordinate.
	 */
	abstract int startPositionX();

	/**
	 * Getter method for the shapes width.
	 * 
	 * @return Int - shape width
	 */
	public int getShapeWidth() {
		return shapeWidth;
	}

	/**
	 * Setter method for the shapes width
	 * 
	 * @param shapeWidth
	 *            Int - Shape width
	 */
	public void setShapeWidth(int shapeWidth) {
		this.shapeWidth = shapeWidth;
	}

	/**
	 * Getter method for the shapes height.
	 * 
	 * @return Int - shape height
	 */
	public int getShapeheight() {
		return shapeheight;
	}

	/**
	 * Setter method for the shapes height
	 * 
	 * @param shapeheight
	 *            Int - shape height
	 */
	public void setShapeheight(int shapeheight) {
		this.shapeheight = shapeheight;
	}

	/**
	 * Draw method for the shape. Calls the draw method of each of the blocks.
	 * 
	 * @param g
	 *            Graphics - the graphics object to draw with
	 */
	public void draw(Graphics g) {
		for (Ex8Block b : blocks) {
			b.draw(g);
		}
	}

	/**
	 * Getter method for the array of blocks for the shape.
	 * 
	 * @return Blocks[] - the blocks of the shape.
	 */
	public ArrayList<Ex8Block> getBlocks() {
		return blocks;
	}

	/**
	 * Checks if the shape can move next turn. It checks that the block isnt
	 * resting on top of any other blocks and that is not on the bottom row.
	 */
	public void checkCanMove() {
		for (Ex8Block block : blocks) {
			if (block.getY() >= gameBoard.getNumRows() - 1) {
				if (!gameBoard.checkIfAtTop()) {
					gameBoard.changeMovingShape();
				}
				return;
			} else {
				for (Ex8Block stationaryBlock : gameBoard.getStationaryBlocks()) {
					if (stationaryBlock.getX() == block.getX()
							&& stationaryBlock.getY() == block.getY() + 1) {
						if (!gameBoard.checkIfAtTop()) {
							gameBoard.changeMovingShape();
						}
						return;
					}
				}
			}
		}
	}

	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @param posX
	 *            the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getCurrentRotation() {
		return currentRotation;
	}

	public void setCurrentRotation(int currentRotation) {
		this.currentRotation = currentRotation;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		for (Ex8Block b : blocks) {
			b.setColor(color);
		}
	}
}