package de.clijsters.resi.example.relayblinker;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Component;
import de.clijsters.resi.common.Input;
import de.clijsters.resi.common.Signal;
import de.clijsters.resi.library.Clock;
import de.clijsters.resi.library.Lamp;
import de.clijsters.resi.library.Relay;

import java.util.concurrent.TimeUnit;

/**
 * This is a blinker with a relay which alternates between two lamps.
 *
 * @author Peter H&auml;nsgen
 */
public class RelayBlinker extends Component {
	private Input powerIn;
	private Lamp lamp1;
	private Lamp lamp2;
	private Relay relay;

	/**
	 * The constructor.
	 */
	public RelayBlinker(Circuit circuit, String name) {
		super(circuit, name);
		powerIn = new Input();

		Clock c1 = new Clock(circuit, name + "_Clock", 1, TimeUnit.SECONDS);
		relay = new Relay(circuit, name + "_Relay");
		lamp1 = new Lamp(circuit, name + "_Lamp1");
		lamp2 = new Lamp(circuit, name + "_Lamp2");

		// connect signals
		new Signal(circuit).from(powerIn).to(relay.getMiddleIn(0));
		new Signal(circuit).from(c1.getOut()).to(relay.getCoilIn());
		new Signal(circuit).from(relay.getOut(0)).to(lamp1.getIn());
		new Signal(circuit).from(relay.get_Out(0)).to(lamp2.getIn());
	}

	public Input getPowerIn() {
		return powerIn;
	}

	public Lamp getLamp1() {
		return lamp1;
	}

	public Lamp getLamp2() {
		return lamp2;
	}

	@Override
	public String toString() {
		return lamp1.getOn() + " " + lamp2.getOn();
	}
}
