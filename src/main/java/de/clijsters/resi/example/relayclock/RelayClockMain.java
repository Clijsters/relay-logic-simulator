package de.clijsters.resi.example.relayclock;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Power;
import de.clijsters.resi.common.Signal;
import de.clijsters.resi.common.Simulator;
import de.clijsters.resi.library.Relay;

/**
 * Simulates the relay clock.
 *
 * @author Peter H&auml;nsgen
 */
public class RelayClockMain {
	/**
	 * The constructor.
	 */
	public static void main(String[] args) {
		Circuit circuit = new Circuit();
		Power power = new Power(circuit, "VCC");
		RelayClock clock = new RelayClock(circuit, "Digital Clock");

		new Signal(circuit).from(power.getOut()).to(clock.getPowerIn());

		Simulator sim = new Simulator(circuit, 10);

		RelayClockFrame frame = new RelayClockFrame(clock, sim);
		circuit.addMonitor(frame);
		printRelayCount(circuit, 2);

		sim.start();
	}

	/**
	 * Prints the number of required relays for the circuit.
	 */
	static void printRelayCount(Circuit circuit, int maxSwitchCount) {
		// we assume that every relay has up to maxSwitchCount switches
		int count = 0;
		for (Relay r : circuit.getAllParts(Relay.class)) {
			count += (r.getSwitchCount() + 1) / maxSwitchCount;
		}

		System.out.println(count + " relays with " + maxSwitchCount + " switches required.");
	}
}
