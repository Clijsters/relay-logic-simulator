package de.clijsters.resi.library;

import de.clijsters.resi.common.*;
import lombok.Getter;

/**
 * This is a 2-bit counter based on flip flops.
 *
 * @author Peter H&auml;nsgen
 */
@Getter
public class Counter4 extends Component {
	private final Input powerIn;
	private final Input _clock;
	private final Input clock;
	private final Output _out0;
	private final Output out0;
	private final Output _out1;
	private final Output out1;

	/**
	 * The constructor.
	 */
	public Counter4(Circuit parent, String name) {
		super(parent, name);
		powerIn = new Input();
		_clock = new Input();
		clock = new Input();

		_out0 = new Output();
		out0 = new Output();
		_out1 = new Output();
		out1 = new Output();

		Circuit local = getLocalCircuit();

		FlipFlop f0 = new FlipFlop(local, name + "_FF0");
		FlipFlop f1 = new FlipFlop(local, name + "_FF1");

		// internal wiring
		new Signal(local).from(powerIn).to(f0.getPowerIn(), f1.getPowerIn());

		// connect the flip flops with each other for form a 4-bit counter
		new Signal(local).from(_clock).to(f0.get_Clock());
		new Signal(local).from(clock).to(f0.getClock());

		// inverse external outputs, otherwise it would count backwards
		new Signal(local).from(f0.getOut()).to(out0).to(f1.getClock());
		new Signal(local).from(f1.getOut()).to(out1);

		new Signal(local).from(f0.get_Out()).to(_out0).to(f1.get_Clock());
		new Signal(local).from(f1.get_Out()).to(_out1);
	}

	public Input get_Clock() {
		return _clock;
	}

	public Output get_Out0() {
		return _out0;
	}

	public Output get_Out1() {
		return _out1;
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
				"]";
		return sb;
	}
}
