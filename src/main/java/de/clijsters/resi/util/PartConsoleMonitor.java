package de.clijsters.resi.util;

import de.clijsters.resi.common.Monitor;
import de.clijsters.resi.common.Part;
import de.clijsters.resi.util.console.Console;

/**
 * This is a monitor for a part which logs changes to the parts state to the console.
 *
 * @author Peter H&auml;nsgen
 */
public class PartConsoleMonitor implements Monitor {
	private Part part;
	private Console console;
	private String lastDebugMessage;

	/**
	 * The constructor.
	 */
	public PartConsoleMonitor(Part part, Console console) {
		this.part = part;
		this.console = console;
	}

	@Override
	public void monitor() {
		String debugMessage = part.toString();
		if (!debugMessage.equals(lastDebugMessage)) {
			console.println(debugMessage);
			lastDebugMessage = debugMessage;
		}
	}
}
