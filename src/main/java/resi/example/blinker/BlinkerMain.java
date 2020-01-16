package resi.example.blinker;

import resi.common.Circuit;
import resi.common.Signal;
import resi.common.Simulator;
import resi.util.PartConsoleMonitor;
import resi.util.console.SystemConsole;
import resi.util.logicanalyzer.LogicAnalyzer;
import resi.util.logicanalyzer.LogicAnalyzerFrame;

/**
 * Simulates the blinker.
 * 
 * @author Peter H&auml;nsgen
 */
public class BlinkerMain
{
    public static void main(String[] args)
    {
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
