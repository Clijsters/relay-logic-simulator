package de.clijsters.resi.example.blinker;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Signal;
import de.clijsters.resi.common.Simulator;
import de.clijsters.resi.util.PartConsoleMonitor;
import de.clijsters.resi.util.console.SystemConsole;
import de.clijsters.resi.util.logicanalyzer.LogicAnalyzer;
import de.clijsters.resi.util.logicanalyzer.LogicAnalyzerFrame;

/**
 * Simulates the blinker.
 *
 * @author Peter H&auml;nsgen
 */
public class BlinkerMain {
	public static void main(String[] args) {
		Circuit circuit = new Circuit();

		Blinker blinker = new Blinker(circuit, "Blinker");

		circuit.addMonitor(new PartConsoleMonitor(blinker.getLamp(), new SystemConsole()));

		Simulator sim = new Simulator(circuit, 10);

		LogicAnalyzer logicAnalyzer = new LogicAnalyzer(500);
		circuit.addMonitor(logicAnalyzer);

		Signal out = blinker.getLamp().getIn().getSignal();
		LogicAnalyzer.Track t = logicAnalyzer.addTrack(out);
		t.setLabel("Lamp");

		LogicAnalyzerFrame logicAnalyzerFrame = new LogicAnalyzerFrame(sim, logicAnalyzer);
		circuit.addMonitor(logicAnalyzerFrame);

		sim.start();
	}
}
