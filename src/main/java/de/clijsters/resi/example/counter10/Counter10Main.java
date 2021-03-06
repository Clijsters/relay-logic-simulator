package de.clijsters.resi.example.counter10;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Power;
import de.clijsters.resi.common.Signal;
import de.clijsters.resi.common.Simulator;
import de.clijsters.resi.library.*;
import de.clijsters.resi.util.PartConsoleMonitor;
import de.clijsters.resi.util.console.SystemConsole;

import java.util.concurrent.TimeUnit;

/**
 * Simulates a decimal counter and shows the result on a 7-segment display.
 *
 * @author Peter H&auml;nsgen
 */
public class Counter10Main {
	public static void main(String[] args) {
		Circuit circuit = new Circuit();
		Power power = new Power(circuit, "VCC");
		Clock clock = new Clock(circuit, "Clock", 500, TimeUnit.MILLISECONDS);

		Counter16 counter = new Counter16(circuit, "Counter");

		BCDDecoder10 bcd = new BCDDecoder10(circuit, "BCD");

		Relay reset = new Relay(circuit, "Reset");

		SevenSegmentDecoder16 decoder = new SevenSegmentDecoder16(circuit, "Decoder");

		SevenSegmentDisplay display = new SevenSegmentDisplay(circuit, "Display");
		circuit.addMonitor(new PartConsoleMonitor(display, new SystemConsole()));

		// internal wiring
		new Signal(circuit).from(power.getOut()).to(decoder.getPowerIn(), bcd.getPowerIn(), reset.getMiddleIn(0));

		new Signal(circuit).from(clock.get_Out()).to(counter.get_clock());
		new Signal(circuit).from(clock.getOut()).to(counter.getClock());

		new Signal(circuit).from(reset.get_Out(0)).to(counter.getPowerIn());

		new Signal(circuit).from(counter.getOut0()).to(bcd.getIn0());
		new Signal(circuit).from(counter.getOut1()).to(bcd.getIn1());
		new Signal(circuit).from(counter.getOut2()).to(bcd.getIn2());
		new Signal(circuit).from(counter.getOut3()).to(bcd.getIn3());

		new Signal(circuit).from(bcd.getOut0()).to(decoder.getIn0());
		new Signal(circuit).from(bcd.getOut1()).to(decoder.getIn1());
		new Signal(circuit).from(bcd.getOut2()).to(decoder.getIn2());
		new Signal(circuit).from(bcd.getOut3()).to(decoder.getIn3());
		new Signal(circuit).from(bcd.getOut4()).to(decoder.getIn4());
		new Signal(circuit).from(bcd.getOut5()).to(decoder.getIn5());
		new Signal(circuit).from(bcd.getOut6()).to(decoder.getIn6());
		new Signal(circuit).from(bcd.getOut7()).to(decoder.getIn7());
		new Signal(circuit).from(bcd.getOut8()).to(decoder.getIn8());
		new Signal(circuit).from(bcd.getOut9()).to(decoder.getIn9());
		new Signal(circuit).from(bcd.getOutA()).to(reset.getCoilIn());

		new Signal(circuit).from(decoder.getOutA()).to(display.getInA());
		new Signal(circuit).from(decoder.getOutB()).to(display.getInB());
		new Signal(circuit).from(decoder.getOutC()).to(display.getInC());
		new Signal(circuit).from(decoder.getOutD()).to(display.getInD());
		new Signal(circuit).from(decoder.getOutE()).to(display.getInE());
		new Signal(circuit).from(decoder.getOutF()).to(display.getInF());
		new Signal(circuit).from(decoder.getOutG()).to(display.getInG());

		Simulator sim = new Simulator(circuit, 10);
		sim.start();
	}
}
