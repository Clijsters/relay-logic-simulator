package resi.example.dice;

import resi.common.Circuit;
import resi.common.Component;
import resi.common.Input;
import resi.common.Signal;
import resi.library.*;
import resi.util.PartConsoleMonitor;
import resi.util.console.SystemConsole;

import java.util.concurrent.TimeUnit;

/**
 * This is a dice which prints out random values.
 *
 * @author Peter H&auml;nsgen
 */
public class Dice extends Component
{
    private Input powerIn;

    public Dice(Circuit circuit, String name)
    {
        super(circuit, name);

        powerIn = new Input();

        Clock clock = new Clock(circuit, name + "_Clock", 10, TimeUnit.MILLISECONDS);

        Counter8 counter = new Counter8(circuit, name + "_Counter");

        BCDDecoder10 bcd = new BCDDecoder10(circuit, name + "_BCD");

        SevenSegmentDecoder10 decoder = new SevenSegmentDecoder10(circuit, name + "_Decoder");

        SevenSegmentDisplay display = new SevenSegmentDisplay(circuit, name + "_Display");
        circuit.addMonitor(new PartConsoleMonitor(display, new SystemConsole()));

        Relay reset = new Relay(circuit, name + "_Reset");

        // internal wiring
        new Signal(circuit).from(powerIn).to(decoder.getPowerIn(), bcd.getPowerIn(), reset.getMiddleIn(0));

        new Signal(circuit).from(clock.get_Out()).to(counter.get_Clock());
        new Signal(circuit).from(clock.getOut()).to(counter.getClock());

        new Signal(circuit).from(counter.getOut0()).to(bcd.getIn0());
        new Signal(circuit).from(counter.getOut1()).to(bcd.getIn1());
        new Signal(circuit).from(counter.getOut2()).to(bcd.getIn2());

        new Signal(circuit).from(bcd.getOut0()).to(decoder.getIn1());
        new Signal(circuit).from(bcd.getOut1()).to(decoder.getIn2());
        new Signal(circuit).from(bcd.getOut2()).to(decoder.getIn3());
        new Signal(circuit).from(bcd.getOut3()).to(decoder.getIn4());
        new Signal(circuit).from(bcd.getOut4()).to(decoder.getIn5());
        new Signal(circuit).from(bcd.getOut5()).to(decoder.getIn6());
        new Signal(circuit).from(bcd.getOut6()).to(reset.getCoilIn());

        new Signal(circuit).from(reset.get_Out(0)).to(counter.getPowerIn());

        new Signal(circuit).from(decoder.getOutA()).to(display.getInA());
        new Signal(circuit).from(decoder.getOutB()).to(display.getInB());
        new Signal(circuit).from(decoder.getOutC()).to(display.getInC());
        new Signal(circuit).from(decoder.getOutD()).to(display.getInD());
        new Signal(circuit).from(decoder.getOutE()).to(display.getInE());
        new Signal(circuit).from(decoder.getOutF()).to(display.getInF());
        new Signal(circuit).from(decoder.getOutG()).to(display.getInG());
    }

    public Input getPowerIn()
    {
        return powerIn;
    }
}
