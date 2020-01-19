package de.clijsters.resi.example.relaytimer;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Power;
import de.clijsters.resi.common.Signal;
import de.clijsters.resi.common.Simulator;
import de.clijsters.resi.util.PartCountPrinter;
import de.clijsters.resi.util.PartTreePrinter;
import de.clijsters.resi.util.PeakPowerMonitor;

/**
 * Simulates the relay timer.
 *
 * @author Peter H&auml;nsgen
 */
public class RelayTimerMain
{
    /**
     * The constructor.
     */
    public static void main(String[] args)
    {
        Circuit circuit = new Circuit();

        Power power = new Power(circuit, "VCC");
        RelayTimer timer = new RelayTimer(circuit, "RelayTimer");

        new Signal(circuit).from(power.getOut()).to(timer.getPowerIn());

        Simulator sim = new Simulator(circuit, 10);

        RelayTimerFrame frame = new RelayTimerFrame(sim, timer);
        circuit.addMonitor(frame);
        circuit.addMonitor(new PeakPowerMonitor(circuit, 2, frame.getPeakPowerConsole()));

        new PartCountPrinter(frame.getConsole(), 2).printParts(circuit);
        new PartTreePrinter(frame.getConsole()).printParts(circuit);

        sim.start();
    }
}
