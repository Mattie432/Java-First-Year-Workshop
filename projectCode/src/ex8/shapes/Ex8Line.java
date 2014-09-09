package ex8.shapes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import ex8.Ex8GameBoard;

/**
 * This is a shape for the tetris game. It represents a 'line' piece.
 * 
 * @author Mattie432
 * 
 */
public class Ex8Line extends Ex8Shape {
	/**
	 * Constructor for the object, allows the object to connect with the
	 * gameboard through a local variable. Also sets the color of the super
	 * class to that of this shape.
	 * 
	 * @param gameBoard
	 *            GameBoard - The GameBoard in use.
	 */
	public Ex8Line(Ex8GameBoard gameBoard) {
		super(gameBoard);
		super.setColor(new Color(46, 208, 217));
	}

	/**
	 * Sets the shape according to this specific type of shape.
	 */
	@Override
	void createShape() {
		super.setShapeWidth(5);
		super.setShapeheight(1);
		super.setPosX(startPositionX());
		super.setPosY(0);

		shapeType(super.getCurrentRotation());
		super.moveToValidPosition(1);
	}

	/**
	 * Creates the shape in one of four rotations specific to this shape.
	 */
	@Override
	int startPositionX() {
		int Min = 3;
		int Max = gameBoard.getNumColumns() - super.getShapeWidth();
		return Min + (int) (Math.random() * ((Max - Min) + 1));
	}

	/**
	 * Creates the shape in one of four rotations specific to this shape.
	 */
	@Override
	void shapeType(int x) {
		if (x == 1) {
			super.blocks.add(new Ex8Block(gameBoard, super.getPosX(), super
					.getPosY()));
			super.blocks.add(new Ex8Block(gameBoard, super.getPosX() - 1, super
					.getPosY()));
			super.blocks.add(new Ex8Block(gameBoard, super.getPosX() - 2, super
					.getPosY()));
			super.blocks.add(new Ex8Block(gameBoard, super.getPosX() + 1, super
					.getPosY()));
			super.blocks.add(new Ex8Block(gameBoard, super.getPosX() + 2, super
					.getPosY()));

		} else if (x == 2) {
			super.blocks.add(new Ex8Block(gameBoard, super.getPosX(), super
					.getPosY() + 2));
			super.blocks.add(new Ex8Block(gameBoard, super.getPosX(), super
					.getPosY() + 1));
			super.blocks.add(new Ex8Block(gameBoard, super.getPosX(), super
					.getPosY()));
			super.blocks.add(new Ex8Block(gameBoard, super.getPosX(), super
					.getPosY() - 1));
			super.blocks.add(new Ex8Block(gameBoard, super.getPosX(), super
					.getPosY() - 2));

		} else if (x == 3) {
			super.blocks.add(new Ex8Block(gameBoard, super.getPosX(), super
					.getPosY()));
			super.blocks.add(new Ex8Block(gameBoard, super.getPosX() - 1, super
					.getPosY()));
			super.blocks.add(new Ex8Block(gameBoard, super.getPosX() - 2, super
					.getPosY()));
			super.blocks.add(new Ex8Block(gameBoard, super.getPosX() + 1, super
					.getPosY()));
			super.blocks.add(new Ex8Block(gameBoard, super.getPosX() + 2, super
					.getPosY()));

		} else if (x == 4) {
			super.blocks.add(new Ex8Block(gameBoard, super.getPosX(), super
					.getPosY() + 2));
			super.blocks.add(new Ex8Block(gameBoard, super.getPosX(), super
					.getPosY() + 1));
			super.blocks.add(new Ex8Block(gameBoard, super.getPosX(), super
					.getPosY()));
			super.blocks.add(new Ex8Block(gameBoard, super.getPosX(), super
					.getPosY() - 1));
			super.blocks.add(new Ex8Block(gameBoard, super.getPosX(), super
					.getPosY() - 2));

		}
	}

	/**
	 * Draws the preview specific to this shape.
	 */
	@Override
	public void drawPreview(JPanel p, Graphics g) {
		g.setColor(getColor());
		int cellWidthHeight = 25;
		int centerX = p.getWidth() / 2;
		int centerY = p.getHeight() / 2;

		// Centre block
		g.fillRect(centerX - (cellWidthHeight / 2), centerY - cellWidthHeight
				/ 2, cellWidthHeight, cellWidthHeight);

		// one up
		g.fillRect(centerX - (cellWidthHeight / 2), centerY - cellWidthHeight
				/ 2 - cellWidthHeight, cellWidthHeight, cellWidthHeight);

		// two up
		g.fillRect(centerX - (cellWidthHeight / 2), centerY - cellWidthHeight
				/ 2 - cellWidthHeight - cellWidthHeight, cellWidthHeight,
				cellWidthHeight);

		// one down
		g.fillRect(centerX - (cellWidthHeight / 2), centerY - cellWidthHeight
				/ 2 + cellWidthHeight, cellWidthHeight, cellWidthHeight);

		// two down
		g.fillRect(centerX - (cellWidthHeight / 2), centerY - cellWidthHeight
				/ 2 + cellWidthHeight + cellWidthHeight, cellWidthHeight,
				cellWidthHeight);

		g.setColor(Color.BLACK);
		g.drawRect(centerX - (cellWidthHeight / 2), centerY - cellWidthHeight
				/ 2, cellWidthHeight, cellWidthHeight);
		g.drawRect(centerX - (cellWidthHeight / 2), centerY - cellWidthHeight
				/ 2 - cellWidthHeight, cellWidthHeight, cellWidthHeight);
		g.drawRect(centerX - (cellWidthHeight / 2), centerY - cellWidthHeight
				/ 2 - cellWidthHeight - cellWidthHeight, cellWidthHeight,
				cellWidthHeight);
		g.drawRect(centerX - (cellWidthHeight / 2), centerY - cellWidthHeight
				/ 2 + cellWidthHeight, cellWidthHeight, cellWidthHeight);
		g.drawRect(centerX - (cellWidthHeight / 2), centerY - cellWidthHeight
				/ 2 + cellWidthHeight + cellWidthHeight, cellWidthHeight,
				cellWidthHeight);

	}

}
