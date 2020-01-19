package de.clijsters.resi.example.blinker;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Component;
import de.clijsters.resi.common.Signal;
import de.clijsters.resi.library.Clock;
import de.clijsters.resi.library.Lamp;

import java.util.concurrent.TimeUnit;

/**
 * This is a simple blinker which periodically switches a light on and off.
 *
 * @author Peter H&auml;nsgen
 */
public class Blinker extends Component {
	private Lamp lamp;

	/**
	 * The constructor.
	 */
	public Blinker(Circuit circuit, String name) {
		super(circuit, name);

		Circuit local = getLocalCircuit();

		Clock clock = new Clock(local, name + "_Clock", 1, TimeUnit.SECONDS);
		lamp = new Lamp(local, name + "_Lamp");

		new Signal(local).from(clock.getOut()).to(lamp.getIn());
	}

	public Lamp getLamp() {
		return lamp;
	}
}
