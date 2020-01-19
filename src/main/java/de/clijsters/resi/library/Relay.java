package de.clijsters.resi.library;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Input;
import de.clijsters.resi.common.Output;
import de.clijsters.resi.common.Part;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents a relay with an arbitrary number of switching elements. A relay has a single coil input which determines
 * the state of all switches. There are two types of switching elements:
 * <ul>
 * <li>A switch has a single middle input whose signal is forwarded to an active and an inactive output.</li>
 * <li>A swotch has two inputs whose signal is forwarded to the output</li>
 * </ul>
 * The switches and swotches are created dynamically when a switching element for an index is accessed for the first
 * time. The kind of access determines whether a switch or a swatch is created. For example, if "getMiddleIn(0)" is
 * called, a switch which has a single middle input connector is instantiated and the input is returned.
 *
 * @author Peter H&auml;nsgen
 */
public class Relay extends Part {
	private final Input coilIn;
	private final Map<Integer, AbstractSwitch> switches;

	/**
	 * The constructor.
	 */
	public Relay(Circuit circuit, String name) {
		super(circuit, name);

		coilIn = new Input();
		switches = new LinkedHashMap<>();
	}

	public Input getCoilIn() {
		return coilIn;
	}

	/**
	 * Returns a switch for the given index. If no switch exists yet, a new instance is created.
	 */
	public Switch getSwitch(int index) {
		// will lead to a ClassCastException if there was already a swotch created for this index
		Switch sw = (Switch) switches.get(index);
		if (sw == null) {
			sw = new Switch(getCircuit(), getName() + "_SWI" + index);
			switches.put(index, sw);
		}
		return sw;
	}

	/**
	 * Convenience method for <code>getSwitch(index).getMiddleIn()</code>.
	 */
	public Input getMiddleIn(int index) {
		Switch sw = getSwitch(index);
		return sw.getMiddleIn();
	}

	/**
	 * Convenience method for <code>getSwitch(index).get_Out()</code>.
	 */
	public Output get_Out(int index) {
		Switch sw = getSwitch(index);
		return sw.get_Out();
	}

	/**
	 * Convenience method for <code>getSwitch(index).getOut()</code>.
	 */
	public Output getOut(int index) {
		Switch sw = getSwitch(index);
		return sw.getOut();
	}

	/**
	 * Returns a swotch for the given index. If no swotch exists yet, a new instance is created.
	 */
	public Swotch getSwotch(int index) {
		// will lead to a ClassCastException if there was already a switch created for this index
		Swotch sw = (Swotch) switches.get(index);
		if (sw == null) {
			sw = new Swotch(getCircuit(), getName() + "_SWO" + index);
			switches.put(index, sw);
		}
		return sw;
	}

	/**
	 * Convenience method for <code>getSwotch(index).getMiddleOut()</code>.
	 */
	public Output getMiddleOut(int index) {
		Swotch sw = getSwotch(index);
		return sw.getMiddleOut();
	}

	/**
	 * Convenience method for <code>getSwotch(index).get_In()</code>.
	 */
	public Input get_In(int index) {
		Swotch sw = getSwotch(index);
		return sw.get_In();
	}

	/**
	 * Convenience method for <code>getSwotch(index).getIn()</code>.
	 */
	public Input getIn(int index) {
		Swotch sw = getSwotch(index);
		return sw.getIn();
	}

	/**
	 * Returns the number of used switches of the relay. Can be used to estimate the required number of real relays.
	 */
	public int getSwitchCount() {
		return switches.size();
	}

	@Override
	public void simulate() {
		Boolean state = coilIn.getValue();

		if (Boolean.TRUE.equals(state)) {
			for (AbstractSwitch sw : switches.values()) {
				sw.push();
			}
		} else if ((state == null) || Boolean.FALSE.equals(state)) {
			for (AbstractSwitch sw : switches.values()) {
				sw.release();
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Relay ");
		sb.append(getName());
		sb.append(": ");
		sb.append("coilIn=");
		sb.append(coilIn);

		for (int i = 0; i < switches.size(); i++) {
			sb.append(", ");
			AbstractSwitch sw = switches.get(i);
			sb.append(sw);
		}
		sb.append("]");
		return sb.toString();
	}
}
