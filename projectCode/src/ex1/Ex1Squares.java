package ex1;

import fyw.turtles.*;

public class Ex1Squares {

	public static void main(String[] args) {
		Turtle theTurtle = new Turtle();
		new TurtleGUI(theTurtle);
		 
		/* -------------------------------------------------------- */
		/* Uncomment lines to perform the functions. */
		
		/* DrawSquare(300,theTurtle); */
		/* DrawGridOfSquares(5,5,theTurtle,100,100); */
		DrawGridInsideGrid(theTurtle, 500);
		
		/* --------------------------------------------------------- */
	}
	

	public static void DrawSquare(double lengthOfSquare, Turtle theTurtle) {
		/* prepare to draw */
		theTurtle.penDown();
		
		/* repeat 4 times */
		for(int i=0;i<4;i++){
			/* Move forwards the specified length */
			theTurtle.move(lengthOfSquare);
			/* Turn right 90deg */
			theTurtle.turn(90);
		}

		/* Stop Drawing */
		theTurtle.penUp();
	}

	public static void DrawGridOfSquares(int Collumns, int Rows,
			Turtle theTurtle, double SpaceBetweenSquares, double lengthOfSquare) {

		for (int c = 0; c < Collumns; c++) {
			/* Repeat for each Collumn */
			for (int r = 0; r < Rows; r++) {
				/* Repeat for each row */
				
				
				/* move turtle across to collumn */
				theTurtle.turn(90);
				theTurtle.move((lengthOfSquare + SpaceBetweenSquares) * c);
				theTurtle.turn(-90);

				/* move turtle up to row */
				theTurtle.move((lengthOfSquare + SpaceBetweenSquares) * r);

				/* draw square */
				DrawSquare(lengthOfSquare, theTurtle);

				/* return to start position */
				theTurtle.turn(-90);
				theTurtle.move((lengthOfSquare + SpaceBetweenSquares) * c);
				theTurtle.turn(90);
				theTurtle.move(-(lengthOfSquare + SpaceBetweenSquares) * r);
			}

		}

	}

	public static void DrawGridInsideGrid(Turtle theTurtle,
			double size) {
		
		/* This value is equal to half of the total picture size */
		double lengthOfPicture = size/2;
		
		/* This code will create the pattern for a quarter of the whole picture.
		 * Therefore its repeated 4 times to create the entire pattern */
		for (int i = 0; i < 4; i++) {

			/* Indent the turtle a small ammount so the lines dont cross */
			theTurtle.penUp();
			theTurtle.move(0.05 * lengthOfPicture);
			theTurtle.turn(90);
			theTurtle.move(0.05 * lengthOfPicture);
			theTurtle.turn(-90);
			theTurtle.penDown();

			/* Draw a square  */
			DrawSquare(0.95 * lengthOfPicture, theTurtle);

			/* Stop drawing while repositioning the turtle */
			theTurtle.penUp();
			
			/* Move the turtle inside the square we just drew */
			theTurtle.move(0.05 * (0.95 * lengthOfPicture));
			theTurtle.turn(90);
			theTurtle.move(0.05 * (0.95 * lengthOfPicture));
			theTurtle.turn(-90);
			theTurtle.penDown();

			/* Draw 4 squares inside the larger square */
			DrawGridOfSquares(2, 2, theTurtle, (0.1 * (0.95 * lengthOfPicture)),(0.4*(0.95 * lengthOfPicture)));
			
			/* Move inside the square closest to the centre */
			theTurtle.penUp();
			theTurtle.move(0.05 * (0.4 * (0.95 * lengthOfPicture)));
			theTurtle.turn(90);
			theTurtle.move(0.05 * (0.4 * (0.95 * lengthOfPicture)));
			theTurtle.turn(-90);
			theTurtle.penDown();

			/* Repeat for two rows inside each smaller square */
			for (int up = 0; up < 2; up++) {
				/* Repeat for two collumns inside each smaller square */
				for (int accross = 0; accross < 2; accross++) {

					/* Move the turtle to the correct square of the 4 inside the quarter */
					theTurtle.move((0.5 * (0.95 * lengthOfPicture)) * up);
					theTurtle.turn(90);
					theTurtle.move((0.5 * (0.95 * lengthOfPicture)) * accross);
					theTurtle.turn(-90);

					/* Draw the grid of squares inside the quarter */
					DrawGridOfSquares(2, 2, theTurtle,(0.1*(0.4*(0.95*lengthOfPicture))),(0.4*(0.4*(0.95*lengthOfPicture))));

					/* Return the turtle to the start position */
					theTurtle.move(-(0.5 * (0.95 * lengthOfPicture)) * up);
					theTurtle.turn(90);
					theTurtle.move(-(0.5 * (0.95 * lengthOfPicture)) * accross);
					theTurtle.turn(-90);
				}
			}

			/* Move the turtle back to the initial position and
			 * rotate the turtle 90deg ready to repeat the pattern. */
			double distance = -1*((0.05*lengthOfPicture)+(0.05*(0.95*lengthOfPicture))+(0.05*(0.4*(0.95*lengthOfPicture))));
			
			theTurtle.move(distance);
			theTurtle.turn(90);
			theTurtle.move(distance);
			
		}

	}

}
