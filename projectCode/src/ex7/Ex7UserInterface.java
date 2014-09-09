package ex7;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ex7UserInterface {

	JFrame frame;
	JComponent contentPane;
	Ex7Drawing drawingPanel;
	
	private JButton undoBtn;
	private JButton redoBtn;
	private Color currentColor = Color.BLACK;

	/**
	 * Constructor for the class, calls the methods to create the UI.
	 */
	public Ex7UserInterface() {
		  try {
		        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		    } catch (Exception e2) {
		        e2.printStackTrace();
		    }
		setUpWindow();
		setUpToolBar();
		setUpDrawPanel();

	}

	/**
	 * Creates a new JFrame window for the program to run from.
	 */
	private void setUpWindow() {
		frame = new JFrame();
		// setup
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Drawing Pane");

		// set size
		frame.setSize(new Dimension(800, 800));
		// frame.pack();

		contentPane = (JComponent) frame.getContentPane();
		frame.setVisible(true);

	}

	/**
	 * Creates the toolbar aligned to the top of the JFrame containing all the menu buttons.
	 */
	private void setUpToolBar() {
		JPanel toolbarPanel = new JPanel();
		contentPane.add(toolbarPanel, BorderLayout.NORTH);
		toolbarPanel.setBackground(Color.blue);

		addLineBtn(toolbarPanel);
		addEllipseBtn(toolbarPanel);
		addRectangleBtn(toolbarPanel);
		addCloseBtn(toolbarPanel);
		addColorBtn(toolbarPanel);
		addUndoButtons(toolbarPanel);
		addSaveBtn(toolbarPanel);
	}

	/**
	 * Creates the Ex7Drawing panel that the drawing will be displayed on.
	 */
	private void setUpDrawPanel() {

		drawingPanel = new Ex7Drawing(undoBtn, redoBtn);
		contentPane.add(drawingPanel, BorderLayout.CENTER);
		drawingPanel.checkRedoEnabled();
		drawingPanel.checkUndoEnabled();
	}

	/**
	 * Changes the cursor from 'default' to 'crosshair' for the Ex7Drawing panel.
	 */
	private void toggleCursor() {
		if (drawingPanel.getCursor().getType() == Cursor.CROSSHAIR_CURSOR) {
			// reset to default
			drawingPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else {
			drawingPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
	}

	/**
	 * Adds the toggle button for drawing lines to the toolbar
	 * @param panel	JPanel - toolbar panel to add button to.
	 */
	private void addLineBtn(JPanel panel) {

		JToggleButton btn = new JToggleButton("Line");
		final Ex7UserInterface ui = this;
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JToggleButton button = (JToggleButton) e.getSource();

				if (button.isSelected()) {

					// is selected
					toggleCursor();
					Ex7MyLineListener l = new Ex7MyLineListener(drawingPanel, ui);

					drawingPanel.addMouseListener(l);
					drawingPanel.addMouseMotionListener(l);

				} else {
					// not selected
					drawingPanel.removeMouseListener(drawingPanel
							.getMouseListeners()[0]);
					drawingPanel.removeMouseMotionListener(drawingPanel
							.getMouseMotionListeners()[0]);
					toggleCursor();
				}
			}
		});

		panel.add(btn, BorderLayout.WEST);
	}

	/**
	 * Adds the toggle button for drawing ellipse to the toolbar
	 * @param panel	JPanel - toolbar panel to add button to.
	 */
	private void addEllipseBtn(JPanel panel) {

		JToggleButton btn = new JToggleButton("Ellipse");
		final Ex7UserInterface ui = this;
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JToggleButton button = (JToggleButton) e.getSource();

				if (button.isSelected()) {

					// is selected
					toggleCursor();
					Ex7MyEllipseListener l = new Ex7MyEllipseListener(drawingPanel,
							ui);

					drawingPanel.addMouseListener(l);
					drawingPanel.addMouseMotionListener(l);

				} else {
					// not selected
					drawingPanel.removeMouseListener(drawingPanel
							.getMouseListeners()[0]);
					drawingPanel.removeMouseMotionListener(drawingPanel
							.getMouseMotionListeners()[0]);
					toggleCursor();
				}
			}
		});

		panel.add(btn, BorderLayout.WEST);
	}

	/**
	 * Adds the toggle button for drawing rectangles to the toolbar
	 * @param panel	JPanel - toolbar panel to add button to.
	 */
	private void addRectangleBtn(JPanel panel) {

		JToggleButton btn = new JToggleButton("Rectangle");
		final Ex7UserInterface ui = this;
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JToggleButton button = (JToggleButton) e.getSource();

				if (button.isSelected()) {

					// is selected
					toggleCursor();
					Ex7MyRectangleListener l = new Ex7MyRectangleListener(
							drawingPanel, ui);

					drawingPanel.addMouseListener(l);
					drawingPanel.addMouseMotionListener(l);

				} else {
					// not selected
					drawingPanel.removeMouseListener(drawingPanel
							.getMouseListeners()[0]);
					drawingPanel.removeMouseMotionListener(drawingPanel
							.getMouseMotionListeners()[0]);
					toggleCursor();
				}
			}
		});

		panel.add(btn, BorderLayout.WEST);
	}

	/**
	 * Adds the button for changing the drawing colour to the toolbar.
	 * @param panel	JPanel - toolbar panel to add button to.
	 */
	private void addColorBtn(final JPanel panel) {
		JButton btn = new JButton("Select Color");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currentColor = (JColorChooser.showDialog(panel, "Pick a color",
						Color.BLACK));
			}
		});

		panel.add(btn, BorderLayout.EAST);
	}

	/**
	 * Adds the close button to the toolbar.
	 * @param panel	JPanel - toolbar panel to add button to.
	 */
	private void addCloseBtn(JPanel panel) {

		JButton btn = new JButton("Exit");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}

		});

		panel.add(btn, BorderLayout.EAST);
	}

	/**
	 * Adds the undo and redo buttons to the toolbar.
	 * @param panel	JPanel - toolbar panel to add button to.
	 */
	private void addUndoButtons(JPanel panel) {

		undoBtn = new JButton("Undo");
		undoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				drawingPanel.undo();
				drawingPanel.paintComponent(drawingPanel.getGraphics());
				drawingPanel.checkRedoEnabled();
				drawingPanel.checkUndoEnabled();
			}

		});

		redoBtn = new JButton("Redo");
		redoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				drawingPanel.redo();
				drawingPanel.paintComponent(drawingPanel.getGraphics());
				drawingPanel.checkRedoEnabled();
				drawingPanel.checkUndoEnabled();
			}

		});

		panel.add(undoBtn, BorderLayout.EAST);
		panel.add(redoBtn, BorderLayout.EAST);
	}

	/**
	 * Adds the save button to the toolbar, this saves the drawing to a jpg file.
	 * @param panel	JPanel - toolbar panel to add button to.
	 */
	private void addSaveBtn(final JPanel panel) {
		JButton btn = new JButton("Save");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					BufferedImage bi = new Robot()
							.createScreenCapture(new Rectangle(drawingPanel
									.getLocationOnScreen().x, drawingPanel
									.getLocationOnScreen().y, drawingPanel
									.getWidth(), drawingPanel.getHeight()));

					FileNameExtensionFilter ft = new FileNameExtensionFilter( "Jpeg", "jpg", "jpeg");

					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
					fileChooser.setDialogTitle("Specify a loacation to save");
					fileChooser.setFileFilter(ft);
					


					int userSelection = fileChooser.showSaveDialog(frame);

					if (userSelection == JFileChooser.APPROVE_OPTION) {
						File fileToSave = fileChooser.getSelectedFile();

						ImageIO.write(bi, "jpg", fileToSave);
					}
				} catch (IOException ioe) {
					System.out.println("Clip write help: " + ioe.getMessage());
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		panel.add(btn, BorderLayout.EAST);
	}

	
	
	/**
	 * Gets the current colour chosen to draw in.
	 * @return	Color - Current drawing colour
	 */
	public Color getCurrentColor() {
		return currentColor;
	}
}
