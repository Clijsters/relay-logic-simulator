package de.clijsters.resi.example.relayblinker;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Power;
import de.clijsters.resi.common.Signal;
import de.clijsters.resi.common.Simulator;
import de.clijsters.resi.util.PartConsoleMonitor;
import de.clijsters.resi.util.console.SystemConsole;
import de.clijsters.resi.util.logicanalyzer.LogicAnalyzer;
import de.clijsters.resi.util.logicanalyzer.LogicAnalyzerFrame;

/**
 * Simulates the relay blinker.
 *
 * @author Peter H&auml;nsgen
 */
public class RelayBlinkerMain {
	public static void main(String[] args) {
		Circuit circuit = new Circuit();
		Power vcc = new Power(circuit, "VCC");
		RelayBlinker blinker = new RelayBlinker(circuit, "RelayBlinker");

		new Signal(circuit).from(vcc.getOut()).to(blinker.getPowerIn());

		// debug output to system out
		circuit.addMonitor(new PartConsoleMonitor(blinker, new SystemConsole()));

		// add logic analyzer
		LogicAnalyzer logicAnalyzer = new LogicAnalyzer(500);
		logicAnalyzer.addTrack(blinker.getLamp1().getIn().getSignal()).setLabel("Lamp1");
		logicAnalyzer.addTrack(blinker.getLamp2().getIn().getSignal()).setLabel("Lamp2");
		circuit.addMonitor(logicAnalyzer);

		Simulator sim = new Simulator(circuit, 10);

		// visualize behavior in window
		LogicAnalyzerFrame frame = new LogicAnalyzerFrame(sim, logicAnalyzer);
		circuit.addMonitor(frame);

		sim.start();
	}
}
