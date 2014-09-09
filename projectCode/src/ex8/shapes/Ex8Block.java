package ex8.shapes;

import java.awt.Color;
import java.awt.Graphics;

import ex8.Ex8GameBoard;

/**
 * Represents a single 'block' square which will be a part of a shape for the
 * tetris game.
 * 
 * @author Mattie432
 */
public class Ex8Block {

	Ex8GameBoard gameBoard;
	private int y;
	private int x;
	private Color color;

	/**
	 * Constructor for class, allows the block to communicate with the gameboard
	 * through a local variable.
	 * 
	 * @param gameBoard
	 *            GameBoard - The gameboard in use.
	 */
	public Ex8Block(Ex8GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}

	/**
	 * Constructor for class, allows the block to communicate with the gameboard
	 * through a local variable. Also allows the X & Y coordinates to be set
	 * (relative to the gameBoard grid).
	 * 
	 * @param gameBoard
	 *            GameBoard - the gameboard in use
	 * @param x
	 *            Int - X coordinate
	 * @param y
	 *            Int - Y coordinate
	 */
	public Ex8Block(Ex8GameBoard gameBoard, int x, int y) {
		this.gameBoard = gameBoard;
		this.x = x;
		this.y = y;
	}

	/**
	 * Draw method to draw the block, this draws a square (of the color of the
	 * block) and outlines it in black.
	 * 
	 * @param g
	 *            Graphics - the graphics object to draw with.
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		int cellWidthHeight = gameBoard.getCellWidthHeight();
		g.fillRect(getX() * cellWidthHeight, getY() * cellWidthHeight,
				cellWidthHeight, cellWidthHeight);
		g.setColor(Color.BLACK);
		g.drawRect(getX() * cellWidthHeight, getY() * cellWidthHeight,
				cellWidthHeight, cellWidthHeight);
	}

	/**
	 * Moves the block one grid square down. If Check collisions is true then it
	 * will check there isn't a block below it. It check valid point is true it
	 * will check the point its moving to is on the grid.
	 * 
	 * @param checkColisions
	 *            Boolean - check for collisions with other blocks.
	 * @param checkValidPoint
	 *            Boolean - check point is on the grid.
	 */
	public void moveDown(boolean checkColisions, boolean checkValidPoint) {
		if (checkValidPoint && gameBoard.checkValidPoint(x, y + 1)) {
			if (!gameBoard.checkCollisions(x, y + 1) && checkColisions) {
				y = y + 1;
			} else {
				y = y + 1;
			}
		} else {
			y = y + 1;
		}
	}

	/**
	 * ! Unused in final program ! Moves the block one grid square up. If Check
	 * collisions is true then it will check there isn't a block below it. It
	 * check valid point is true it will check the point its moving to is on the
	 * grid.
	 * 
	 * @param checkColisions
	 *            Boolean - check for collisions with other blocks.
	 * @param checkValidPoint
	 *            Boolean - check point is on the grid.
	 */
	public void moveUp(boolean checkColisions, boolean checkValidPoint) {
		if (checkValidPoint && gameBoard.checkValidPoint(x, y - 1)) {
			if (!gameBoard.checkCollisions(x, y - 1) && checkColisions) {
				y = y - 1;
			} else {
				y = y - 1;
			}
		} else {
			y = y - 1;
		}
	}

	/**
	 * Moves the block one grid square left. If Check collisions is true then it
	 * will check there isn't a block below it. It check valid point is true it
	 * will check the point its moving to is on the grid.
	 * 
	 * @param checkColisions
	 *            Boolean - check for collisions with other blocks.
	 * @param checkValidPoint
	 *            Boolean - check point is on the grid.
	 */
	public void moveLeft(boolean checkColisions, boolean checkValidPoint) {
		if (checkValidPoint && gameBoard.checkValidPoint(x - 1, y)) {
			if (!gameBoard.checkCollisions(x - 1, y) && checkColisions) {
				x = x - 1;
			} else {
				x = x - 1;
			}
		} else {
			x = x - 1;
		}
	}

	/**
	 * Moves the block one grid square right. If Check collisions is true then
	 * it will check there isn't a block below it. It check valid point is true
	 * it will check the point its moving to is on the grid.
	 * 
	 * @param checkColisions
	 *            Boolean - check for collisions with other blocks.
	 * @param checkValidPoint
	 *            Boolean - check point is on the grid.
	 */
	public void moveRight(boolean checkColisions, boolean checkValidPoint) {
		if (checkValidPoint && gameBoard.checkValidPoint(x + 1, y)) {
			if (!gameBoard.checkCollisions(x + 1, y) && checkColisions) {
				x = x + 1;
			} else {

				x = x + 1;
			}
		} else {
			x = x + 1;
		}
	}

	/**
	 * This moves the block downwards until its blocked by either another block
	 * or the end of the playable area.
	 * 
	 * This is intended to be used to allow the blocks to 'fall' when a line is
	 * broken.
	 */
	public void moveDownUntilBlocked() {
		boolean temp = true;
		while (temp) {
			if (gameBoard.checkValidPoint(x, y + 1)) {
				if (!gameBoard.checkCollisions(x, y + 1)) {
					y = y + 1;
				} else {
					temp = false;
				}
			} else {
				temp = false;
			}
		}
	}

	/**
	 * Getter method for the color of the block
	 * 
	 * @return Color - color of the blocks
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Setter method for the color of the block.
	 * 
	 * @param color
	 *            Color - color to set to.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Getter method for the Y coordinate.
	 * 
	 * @return Int - Y Coordinate.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setter method for the Y Coordinate.
	 * 
	 * @param y
	 *            Int - Y Coordinate.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Getter method for the X Coordinate.
	 * 
	 * @return Int - X Coordinate.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Setter method for the X Coordinate.
	 * 
	 * @param x
	 *            Int - X Coordinate.
	 */
	public void setX(int x) {
		this.x = x;
	}

}
