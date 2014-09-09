package ex8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 * This is the user inteface that the user interacts with.
 * 
 * @author Mattie432
 * 
 */
@SuppressWarnings("serial")
public class Ex8UserInterface extends JFrame {

	JFrame frame;
	private Ex8GameBoard gameBoard;
	private JToggleButton startBtn;
	private Ex8PreviewPanel previewPanel;
	private Ex8ScoreBoard scoreBoard;

	/**
	 * Constructor for the class, this creates the frame and calls the methods
	 * to create the GameBoard and sidePannel.
	 */
	public Ex8UserInterface() {
		frame = this;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(600, 600));
		this.setSize(new Dimension(600, 600));
		this.setLayout(new BorderLayout());
		createGameBoard();
		createSideBar();
		this.pack();
		this.setResizable(false);

	}

	/**
	 * This creates a new panel for the sidebar, this calls the methods to add
	 * buttons + panels to it and then adds it to the JFrame.
	 */
	private void createSideBar() {
		JPanel sideBar = new JPanel();
		sideBar.setLayout(new FlowLayout());
		sideBar.setBackground(Color.BLUE);
		// addDownButton(sideBar);
		// addLeftButton(sideBar);
		// addRightButton(sideBar);
		// addRotateBtn(sideBar);
		// addDrawBtn(sideBar);

		startBtn = addStartBtn(sideBar);
		previewPanel = createPreviewPanel(sideBar);
		scoreBoard = createScoreBoard(sideBar);

		Dimension d = new Dimension(
				((int) (frame.getWidth() * 0.2f / gameBoard.getCellWidthHeight()) * gameBoard
						.getCellWidthHeight()), frame.getHeight());
		// Dimension d = new Dimension(frame.getWidth() - gameBoard.getWidth(),
		// gameBoard.getHeight());
		sideBar.setSize(d);
		sideBar.setPreferredSize(d);

		this.add(sideBar, BorderLayout.EAST);
	}

	/**
	 * Creates the scoreboard with the width of the sidePannel and adds it to
	 * the sidepannel.
	 * 
	 * @param sideBar
	 *            JPanel - side bar panel
	 * @return ScoreBoard - the scoreBoard Pannel.
	 */
	private Ex8ScoreBoard createScoreBoard(JPanel sideBar) {
		Ex8ScoreBoard sc = new Ex8ScoreBoard(
				(int) (frame.getWidth() * 0.2f / gameBoard.getCellWidthHeight())
						* gameBoard.getCellWidthHeight());
		sideBar.add(sc);
		return sc;
	}

	/**
	 * Creates a start button and adds it to the JPanel sidebar.
	 * 
	 * @param sideBar
	 *            JPanel - sideBar to add to
	 * @return JToggleButton - start button.
	 */
	private JToggleButton addStartBtn(JPanel sideBar) {
		JToggleButton button = new JToggleButton();
		button.setText("Start");
		button.setSelected(false);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JToggleButton button = (JToggleButton) arg0.getSource();
				if (button.isSelected()) {
					// start

					if (gameBoard.isGameOver() == true) {
						gameBoard.startNewGame();
						gameBoard.setGameOver(false);
					}

					button.setText("pause");
					gameBoard.setPlay(true);
					gameBoard.getTimer().start();
					gameBoard.requestFocusInWindow();

				} else {
					// pause
					button.setText("start");
					gameBoard.setPlay(false);
					gameBoard.getTimer().stop();
				}
			}
		});

		sideBar.add(button, BorderLayout.CENTER);
		return button;
	}

	/**
	 * Creates the preview panel and adds it to the sidebar.
	 * 
	 * @param sideBar
	 *            JPanel - sidebar to add to
	 * @return PreviewPanel - the preview panel created.
	 */
	private Ex8PreviewPanel createPreviewPanel(JPanel sideBar) {
		Ex8PreviewPanel p = new Ex8PreviewPanel(gameBoard);
		p.setSize(new Dimension(100, 125));
		p.setPreferredSize(new Dimension(100, 200));
		sideBar.add(p);
		return p;
	}

	/**
	 * Creates the gameboard with the correct size for the JFrame and adds it to
	 * the JFrame.
	 */
	private void createGameBoard() {
		gameBoard = new Ex8GameBoard(this.getWidth(), this.getHeight() - 50, this);
		this.add(gameBoard, BorderLayout.CENTER);
		// gameBoard.setBackground(Color.RED);
	}

	/**
	 * Resets all buttons on the sideBar to their origional state.
	 */
	public void resetAll() {
		startBtn.setText("Start");
		startBtn.setSelected(false);

	}

	/**
	 * Getter method for the preview panel.
	 * 
	 * @return PreviewPanel - the preview panel
	 */
	public Ex8PreviewPanel getPreviewPanel() {
		return previewPanel;
	}

	/**
	 * Getter method for the scoreboard
	 * 
	 * @return ScoreBoard - the Scoreboard.
	 */
	public Ex8ScoreBoard getScoreBoard() {
		return scoreBoard;
	}

	/**
	 * <b> ! Unused ! <b> adds a move down button to the sidebar.
	 * 
	 * @param p
	 *            JPanel - sidebar
	 */
	@SuppressWarnings("unused")
	private void addDownButton(JPanel p) {
		JButton button = new JButton();
		button.setText("Down");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameBoard.getMovingShape().moveDown();
				gameBoard.repaint();
			}
		});

		p.add(button, BorderLayout.CENTER);
	}

	/**
	 * <b> ! Unused ! <b> adds a move left button to the sidebar.
	 * 
	 * @param p
	 *            JPanel - sidebar
	 */
	@SuppressWarnings("unused")
	private void addLeftButton(JPanel p) {
		JButton button = new JButton();
		button.setText("Left");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameBoard.getMovingShape().moveLeft();
				gameBoard.repaint();
			}
		});

		p.add(button, BorderLayout.CENTER);
	}

	/**
	 * <b> ! Unused ! <b> adds a move right button to the sidebar.
	 * 
	 * @param p
	 *            JPanel - sidebar
	 */
	@SuppressWarnings("unused")
	private void addRightButton(JPanel p) {
		JButton button = new JButton();
		button.setText("Right");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameBoard.getMovingShape().moveRight();
				gameBoard.repaint();
			}
		});

		p.add(button, BorderLayout.CENTER);
	}

	/**
	 * <b> ! Unused ! <b> adds a rotate button to the sidebar.
	 * 
	 * @param p
	 *            JPanel - sidebar
	 */
	@SuppressWarnings("unused")
	private void addRotateBtn(JPanel p) {
		JButton button = new JButton();
		button.setText("Rotate");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameBoard.getMovingShape().rotate();
				gameBoard.repaint();
			}
		});

		p.add(button);
	}

}
