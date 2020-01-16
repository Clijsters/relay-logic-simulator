package resi.example.blinker;

import java.util.concurrent.TimeUnit;

import resi.common.Circuit;
import resi.common.Component;
import resi.common.Signal;
import resi.library.Clock;
import resi.library.Lamp;

/**
 * This is a simple blinker which periodically switches a light on and off.
 * 
 * @author Peter H&auml;nsgen
 */
public class Blinker extends Component
{
    private Lamp lamp;

    /**
     * The constructor.
     */
    public Blinker(Circuit circuit, String name)
    {
        super(circuit, name);

        Circuit local = getLocalCircuit();

        Clock clock = new Clock(local, name + "_Clock", 1, TimeUnit.SECONDS);
        lamp = new Lamp(local, name + "_Lamp");

        new Signal(local).from(clock.getOut()).to(lamp.getIn());
    }

    public Lamp getLamp()
    {
        return lamp;
    }
}
