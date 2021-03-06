package de.clijsters.resi.example.relaytimer;

import de.clijsters.resi.common.Monitor;
import de.clijsters.resi.common.Simulator;
import de.clijsters.resi.library.Switch;
import de.clijsters.resi.util.SimulatorFrame;
import de.clijsters.resi.util.console.Console;
import de.clijsters.resi.util.console.TextAreaConsole;

import java.awt.*;

/**
 * A window for a graphical visualization of the relay timer.
 *
 * @author Peter H&auml;nsgen
 */
public class RelayTimerFrame extends SimulatorFrame implements Monitor {
	private static final long serialVersionUID = 1L;
	private RelayTimerCanvas canvas;
	private TextAreaConsole console;
	private TextAreaConsole peakPowerConsole;

	/**
	 * The constructor.
	 */
	public RelayTimerFrame(Simulator simulator, RelayTimer timer) {
		super(simulator);

		setTitle("Relay Timer");
		setSize(600, 400);

		// position the frame in the center of the screen
		Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((ss.width - getWidth()) / 2, (ss.height - getHeight()) / 2);

		BorderLayout layout = new BorderLayout();
		setLayout(layout);

		// timer display
		canvas = new RelayTimerCanvas(timer);
		add(canvas, BorderLayout.CENTER);

		// buttons to set the timer
		Panel buttonPanel = new Panel();
		buttonPanel.setBackground(Color.BLACK);
		FlowLayout buttonPanelLayout = new FlowLayout(FlowLayout.CENTER);
		buttonPanel.setLayout(buttonPanelLayout);
		add(buttonPanel, BorderLayout.NORTH);

		// button for setting the 10 minutes
		Button setM1Button = new Button("Set Minutes 10");
		setM1Button.setEnabled(true);
		setM1Button.addActionListener(e -> {
			Switch setM1Switch = timer.getM1Switch();
			setM1Switch.push();
			try {
				// we have to wait for a number of simulation cycles so that the change becomes effective
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			setM1Switch.release();
		});
		buttonPanel.add(setM1Button);

		// button for setting the minutes
		Button setM0Button = new Button("Set Minutes");
		setM0Button.setEnabled(true);
		setM0Button.addActionListener(e -> {
			Switch setM0Switch = timer.getM0Switch();
			setM0Switch.push();
			try {
				// we have to wait for a number of simulation cycles so that the change becomes effective
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			setM0Switch.release();
		});
		buttonPanel.add(setM0Button);

		// button for starting the countdown
		Button startButton = new Button("Start / Stop");
		startButton.setEnabled(true);
		startButton.addActionListener(e -> {
			Switch startSwitch = timer.getStartSwitch();
			startSwitch.push();
			try {
				// we have to wait for a number of simulation cycles so that the change becomes effective
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			startSwitch.release();
		});
		buttonPanel.add(startButton);

		// button for resetting the countdown
		Button resetButton = new Button("Reset");
		resetButton.setEnabled(true);
		resetButton.addActionListener(e -> {
			Switch resetSwitch = timer.getResetSwitch();
			resetSwitch.push();
			try {
				// we have to wait for a number of simulation cycles so that the change becomes effective
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			resetSwitch.release();
		});
		buttonPanel.add(resetButton);
		Panel south = new Panel();
		south.setLayout(new BorderLayout());
		add(south, BorderLayout.SOUTH);

		console = new TextAreaConsole();
		console.setPreferredSize(new Dimension(300, 150));
		south.add(console, BorderLayout.WEST);

		// add extra text area for showing the peak power consumption
		peakPowerConsole = new TextAreaConsole();
		peakPowerConsole.setPreferredSize(new Dimension(300, 150));
		south.add(peakPowerConsole, BorderLayout.EAST);

		setVisible(true);
	}

	@Override
	public void monitor() {
		canvas.repaint();
	}

	public Console getConsole() {
		return console;
	}

	public Console getPeakPowerConsole() {
		return peakPowerConsole;
	}
}
