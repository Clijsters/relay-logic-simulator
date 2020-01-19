package de.clijsters.resi.library;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Input;
import de.clijsters.resi.common.Output;

/**
 * This is a switch where the middle contact represents an input whose value is forwarded to one of the two outputs.
 *
 * @author Peter H&auml;nsgen
 */
public class Switch extends AbstractSwitch {
	private final Input middleIn;
	private final Output _out;
	private final Output out;

	/**
	 * The constructor.
	 */
	public Switch(Circuit circuit, String name) {
		super(circuit, name);

		middleIn = new Input();
		_out = new Output();
		out = new Output();
	}

	public Input getMiddleIn() {
		return middleIn;
	}

	public Output get_Out() {
		return _out;
	}

	public Output getOut() {
		return out;
	}

	@Override
	public void simulate() {
		if (isPushed()) {
			_out.setValue(null);
			out.setValue(middleIn.getValue());
		} else {
			_out.setValue(middleIn.getValue());
			out.setValue(null);
		}
	}

	@Override
	public String toString() {
		String sb = "[Switch " +
				getName() +
				": " +
				"pushed=" +
				isPushed() +
				", middleIn=" +
				middleIn +
				", _out=" +
				_out +
				", out=" +
				out +
				"]";
		return sb;
	}
}
