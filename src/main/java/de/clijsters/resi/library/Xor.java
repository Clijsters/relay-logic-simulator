package de.clijsters.resi.library;

import de.clijsters.resi.common.*;

/**
 * A logical XOR gate built with relays.
 *
 * @author Peter H&auml;nsgen
 */
public class Xor extends Component {
	private final Input powerIn;
	private final Input in0;
	private final Input in1;
	private final Output out;

	/**
	 * The constructor.
	 */
	public Xor(Circuit parent, String name) {
		super(parent, name);
		powerIn = new Input();
		in0 = new Input();
		in1 = new Input();
		out = new Output();

		Circuit local = getLocalCircuit();

		Relay r0 = new Relay(local, name + "_R0");
		Switch s0 = r0.getSwitch(0);

		Relay r1 = new Relay(local, name + "_R1");
		Swotch s1 = r1.getSwotch(0);

		// internal wiring
		new Signal(local).from(powerIn).to(s0.getMiddleIn());

		new Signal(local).from(in0).to(r0.getCoilIn());
		new Signal(local).from(in1).to(r1.getCoilIn());

		new Signal(local).from(s0.get_Out()).to(s1.getIn());
		new Signal(local).from(s0.getOut()).to(s1.get_In());

		new Signal(local).from(s1.getMiddleOut()).to(out);
	}

	public Input getPowerIn() {
		return powerIn;
	}

	public Input getIn0() {
		return in0;
	}

	public Input getIn1() {
		return in1;
	}

	public Output getOut() {
		return out;
	}

	@Override
	public String toString() {

		String sb = "[XOR " +
				getName() +
				": " +
				"in0=" +
				in0 +
				", " +
				"in1=" +
				in1 +
				", " +
				"out=" +
				out +
				"]";
		return sb;
	}
}
