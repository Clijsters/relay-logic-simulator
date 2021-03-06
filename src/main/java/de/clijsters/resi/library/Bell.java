package de.clijsters.resi.library;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Input;
import de.clijsters.resi.common.Part;

import java.awt.*;

/**
 * Simulates a sound signal, like a bell or a buzzer.
 *
 * @author Peter H&auml;nsgen
 */
public class Bell extends Part {
	private final Input in;
	private long lastTime;

	/**
	 * The constructor.
	 */
	public Bell(Circuit circuit, String name) {
		super(circuit, name);

		in = new Input();
	}

	public Input getIn() {
		return in;
	}

	@Override
	public void simulate() {
		if (Boolean.TRUE.equals(in.getValue())) {
			long time = System.currentTimeMillis();
			long diff = time - lastTime;
			if (diff > 120) {
				Toolkit.getDefaultToolkit().beep();
				lastTime = time;
			}
		}
	}

	@Override
	public String toString() {

		String sb = "[Bell " +
				getName() +
				": in=" +
				in +
				"]";
		return sb;
	}
}
