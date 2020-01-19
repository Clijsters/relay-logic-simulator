package de.clijsters.resi.library;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Input;
import de.clijsters.resi.common.Output;

/**
 * This is a switch where the middle contact represents an output which selects its value from one of the two inputs.
 *
 * @author Peter H&auml;nsgen
 */
public class Swotch extends AbstractSwitch {
	private final Input _in;
	private final Input in;
	private final Output middleOut;

	/**
	 * The constructor.
	 */
	public Swotch(Circuit circuit, String name) {
		super(circuit, name);

		_in = new Input();
		in = new Input();
		middleOut = new Output();
	}

	public Input get_In() {
		return _in;
	}

	public Input getIn() {
		return in;
	}

	public Output getMiddleOut() {
		return middleOut;
	}

	@Override
	public void simulate() {
		if (isPushed()) {
			middleOut.setValue(in.getValue());
		} else {
			middleOut.setValue(_in.getValue());
		}
	}

	@Override
	public String toString() {
		String sb = "[Swotch " +
				getName() +
				": " +
				"pushed=" +
				isPushed() +
				", _in=" +
				_in +
				", in=" +
				in +
				", middleOut=" +
				middleOut +
				"]";
		return sb;
	}
}
