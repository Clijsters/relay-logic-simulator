package resi.library;

import resi.common.*;

/**
 * A logical OR gate built with relays.
 *
 * @author Peter H&auml;nsgen
 */
public class Or extends Component
{
    private final Input powerIn;

    private final Input[] ins;

    private final Output out;

    /**
     * The constructor.
     *
     * @param n number of inputs
     */
    public Or(Circuit parent, String name, int n)
    {
        super(parent, name);

        powerIn = new Input();

        ins = new Input[n];

        Circuit local = getLocalCircuit();

        // first create relays, then the joint to keep them in the right processing order
        Relay[] relays = new Relay[n];
        for (int i = 0; i < n; i++)
        {
            Input in = ins[i] = new Input();
            Relay r = relays[i] = new Relay(local, name + "_R" + String.valueOf(i));

            new Signal(local).from(in).to(r.getCoilIn());
            new Signal(local).from(powerIn).to(r.getMiddleIn(0));
        }

        Joint joint = new Joint(local, name + "_J");

        out = new Output();

        // internal wiring
        for (int i = 0; i < n; i++)
        {
            Relay r = relays[i];

            new Signal(local).from(r.getOut(0)).to(joint.getIn(i));
        }

        new Signal(local).from(joint.getOut()).to(out);
    }

    public Input getPowerIn()
    {
        return powerIn;
    }

    public Input getIn(int index)
    {
        return ins[index];
    }

    public Output getOut()
    {
        return out;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[OR ");
        sb.append(getName());
        sb.append(": ");

        for (int i = 0; i < ins.length; i++)
        {
            Input in = ins[i];

            sb.append("in");
            sb.append(String.valueOf(i));
            sb.append("=");
            sb.append(String.valueOf(in));
            sb.append(", ");
        }

        sb.append("out=");
        sb.append(String.valueOf(out));
        sb.append("]");

        return sb.toString();
    }
}
