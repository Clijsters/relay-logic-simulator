package de.clijsters.resi.util;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Component;
import de.clijsters.resi.common.Part;
import de.clijsters.resi.util.console.Console;

/**
 * A helper class that prints the tree of parts of a circuit.
 *
 * @author Peter H&auml;nsgen
 */
public class PartTreePrinter {
	private Console console;

	/**
	 * The constructor.
	 */
	public PartTreePrinter(Console console) {
		this.console = console;

	}

	public void printParts(Circuit circuit) {
		console.println("*** Part Tree ***");
		printParts(circuit, "");
	}

	private void printParts(Circuit circuit, String intend) {
		for (Part part : circuit.getParts()) {
			console.println(intend + part.getName());

			if (part instanceof Component) {
				Component comp = (Component) part;
				printParts(comp.getLocalCircuit(), intend + "  ");
			}
		}
	}
}
