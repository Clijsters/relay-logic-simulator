package de.clijsters.resi.library;

import de.clijsters.resi.common.*;

/**
 * This is a 3-bit counter based on flip flops.
 *
 * @author Peter H&auml;nsgen
 */
public class Counter8 extends Component {
	private final Input powerIn;
	private final Input _clock;
	private final Input clock;
	private final Output _out0;
	private final Output out0;
	private final Output _out1;
	private final Output out1;
	private final Output _out2;
	private final Output out2;

	/**
	 * The constructor.
	 */
	public Counter8(Circuit parent, String name) {
		super(parent, name);
		powerIn = new Input();
		_clock = new Input();
		clock = new Input();

		_out0 = new Output();
		out0 = new Output();
		_out1 = new Output();
		out1 = new Output();
		_out2 = new Output();
		out2 = new Output();

		Circuit local = getLocalCircuit();

		FlipFlop f0 = new FlipFlop(local, name + "_FF0");
		FlipFlop f1 = new FlipFlop(local, name + "_FF1");
		FlipFlop f2 = new FlipFlop(local, name + "_FF2");

		// internal wiring
		new Signal(local).from(powerIn).to(f0.getPowerIn(), f1.getPowerIn(), f2.getPowerIn());

		// connect the flip flops with each other for form a 4-bit counter
		new Signal(local).from(_clock).to(f0.get_clock());
		new Signal(local).from(clock).to(f0.getClock());

		// inverse external outputs, otherwise it would count backwards
		new Signal(local).from(f0.getOut()).to(out0).to(f1.getClock());
		new Signal(local).from(f1.getOut()).to(out1).to(f2.getClock());
		new Signal(local).from(f2.getOut()).to(out2);

		new Signal(local).from(f0.get_Out()).to(_out0).to(f1.get_clock());
		new Signal(local).from(f1.get_Out()).to(_out1).to(f2.get_clock());
		new Signal(local).from(f2.get_Out()).to(_out2);
	}

	public Input getPowerIn() {
		return powerIn;
	}

	public Input get_clock() {
		return _clock;
	}

	public Input getClock() {
		return clock;
	}

	public Output get_Out0() {
		return _out0;
	}

	public Output getOut0() {
		return out0;
	}

	public Output get_Out1() {
		return _out1;
	}

	public Output getOut1() {
		return out1;
	}

	public Output get_Out2() {
		return _out2;
	}

	public Output getOut2() {
		return out2;
	}

	@Override
	public String toString() {

		String sb = "[Counter " +
				getName() +
				": _clock=" +
				_clock +
				", clock=" +
				clock +
				", _out0=" +
				_out0 +
				", out0=" +
				out0 +
				", _out1=" +
				_out1 +
				", out1=" +
				out1 +
				", _out2=" +
				_out2 +
				", out2=" +
				out2 +
				"]";
		return sb;
	}
}
