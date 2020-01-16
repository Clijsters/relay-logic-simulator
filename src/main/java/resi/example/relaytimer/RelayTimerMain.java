package resi.example.relaytimer;

import resi.common.Circuit;
import resi.common.Power;
import resi.common.Signal;
import resi.common.Simulator;
import resi.util.PartCountPrinter;
import resi.util.PartTreePrinter;
import resi.util.PeakPowerMonitor;

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
