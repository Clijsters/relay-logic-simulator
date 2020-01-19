package de.clijsters.resi.library;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Part;

/**
 * This is the common superclass for switching elements. There are different kinds of switches with different inputs and
 * outputs and different behavior.
 *
 * @author Peter H&auml;nsgen
 */
public abstract class AbstractSwitch extends Part
{
    private boolean pushed;

    /**
     * The constructor.
     */
    public AbstractSwitch(Circuit circuit, String name)
    {
        super(circuit, name);
    }

    /**
     * Puts the switch into active state.
     */
    public void push()
    {
        pushed = true;
    }

    /**
     * Puts the switch back into default passive state.
     */
    public void release()
    {
        pushed = false;
    }

    public boolean isPushed()
    {
        return pushed;
    }
}
