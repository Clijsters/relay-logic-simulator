package resi.example.countdown10;

import resi.common.Circuit;
import resi.common.Power;
import resi.common.Signal;
import resi.common.Simulator;
import resi.library.*;
import resi.util.PartConsoleMonitor;
import resi.util.console.SystemConsole;

import java.util.concurrent.TimeUnit;

/**
 * Simulates a decimal countdown and shows the result on a 7-segment display.
 *
 * @author Peter H&auml;nsgen
 */
public class Countdown10Main
{
    public static void main(String[] args)
    {
        Circuit circuit = new Circuit();

        Power power = new Power(circuit, "VCC");
        Clock clock = new Clock(circuit, "Clock", 500, TimeUnit.MILLISECONDS);

        Countdown16 countdown = new Countdown16(circuit, "Countdown");

        BCDDecoder16 bcd = new BCDDecoder16(circuit, "BCD");

        Relay reset = new Relay(circuit, "Reset");

        SevenSegmentDecoder16 decoder = new SevenSegmentDecoder16(circuit, "Decoder");

        SevenSegmentDisplay display = new SevenSegmentDisplay(circuit, "Display");
        circuit.addMonitor(new PartConsoleMonitor(display, new SystemConsole()));

        // internal wiring
        new Signal(circuit).from(power.getOut()).to(decoder.getPowerIn(), bcd.getPowerIn(), reset.getMiddleIn(0));

        new Signal(circuit).from(clock.get_Out()).to(countdown.get_Clock());
        new Signal(circuit).from(clock.getOut()).to(countdown.getClock());

        new Signal(circuit).from(reset.get_Out(0)).to(countdown.getPowerIn());

        new Signal(circuit).from(countdown.getOut0()).to(bcd.getIn0());
        new Signal(circuit).from(countdown.getOut1()).to(bcd.getIn1());
        new Signal(circuit).from(countdown.getOut2()).to(bcd.getIn2());
        new Signal(circuit).from(countdown.getOut3()).to(bcd.getIn3());

        // connect counter and decoder
        new Signal(circuit).from(bcd.getOut5()).to(reset.getCoilIn());
        new Signal(circuit).from(bcd.getOut6()).to(decoder.getIn0());
        new Signal(circuit).from(bcd.getOut7()).to(decoder.getIn1());
        new Signal(circuit).from(bcd.getOut8()).to(decoder.getIn2());
        new Signal(circuit).from(bcd.getOut9()).to(decoder.getIn3());
        new Signal(circuit).from(bcd.getOutA()).to(decoder.getIn4());
        new Signal(circuit).from(bcd.getOutB()).to(decoder.getIn5());
        new Signal(circuit).from(bcd.getOutC()).to(decoder.getIn6());
        new Signal(circuit).from(bcd.getOutD()).to(decoder.getIn7());
        new Signal(circuit).from(bcd.getOutE()).to(decoder.getIn8());
        new Signal(circuit).from(bcd.getOutF()).to(decoder.getIn9());

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
