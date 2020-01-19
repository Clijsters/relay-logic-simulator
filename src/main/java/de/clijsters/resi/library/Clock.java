package de.clijsters.resi.library;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Output;
import de.clijsters.resi.common.Part;

import java.util.concurrent.TimeUnit;

/**
 * This is a clock generator that generates a clock with a configurable frequency.
 *
 * @author Peter H&auml;nsgen
 */
public class Clock extends Part
{
    public static final String CLOCK = "clock";

    private final long interval;

    private long lastTime;

    private boolean state;

    private final Output _out;

    private final Output out;

    /**
     * The constructor.
     */
    public Clock(Circuit circuit, String name, int time, TimeUnit unit)
    {
        super(circuit, name);

        interval = unit.toMillis(time);

        _out = new Output();
        out = new Output();
    }

    public Output get_Out()
    {
        return _out;
    }

    public Output getOut()
    {
        return out;
    }

    @Override
    public void simulate()
    {
        long time = System.currentTimeMillis();
        long diff = time - lastTime;
        if (diff > interval)
        {
            state = !state;

            if (out != null)
            {
                out.setValue(state ? true : null);
            }

            if (_out != null)
            {
                _out.setValue(state ? null : true);
            }

            lastTime = time;
        }
    }

    @Override
    public String toString()
    {
        return getName() + ": " + String.valueOf(state);
    }
}
