package de.clijsters.resi.util;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Part;
import de.clijsters.resi.library.Relay;
import de.clijsters.resi.util.console.Console;

import java.util.Map;
import java.util.TreeMap;

/**
 * A helper class that prints the count of parts of a circuit.
 *
 * @author Peter H&auml;nsgen
 */
public class PartCountPrinter {
	private Console console;
	private int maxSwitchCount;

	/**
	 * The constructor.
	 *
	 * @param maxSwitchCount the maximum number of switches that a relay can have
	 */
	public PartCountPrinter(Console console, int maxSwitchCount) {
		this.console = console;
		this.maxSwitchCount = maxSwitchCount;
	}

	/**
	 * Prints the number of required relays for the circuit.
	 */
	public void printParts(Circuit circuit) {
		Map<String, Integer> partCounts = new TreeMap<>();

		for (Part p : circuit.getAllParts()) {
			String type = p.getClass().getSimpleName();
			Integer count = partCounts.get(type);
			if (count == null) {
				count = 0;
			}

			if (p instanceof Relay) {
				Relay r = (Relay) p;

				// we assume that every relay has up to maxSwitchCount switches
				// if there are more switches we need multiple relays
				count += (r.getSwitchCount() + 1) / maxSwitchCount;
			} else {
				count++;
			}
			partCounts.put(type, count);
		}

		console.println("*** Part Counts ***");
		for (Map.Entry<String, Integer> entry : partCounts.entrySet()) {
			console.println(entry.getKey() + ": " + entry.getValue());
		}
	}
}
