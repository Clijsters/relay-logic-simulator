package resi.library;

import resi.common.*;

/**
 * This is a decoder that converts a 4-bit BCD value into decimal outputs. This is basically equivalent with the BCD
 * decoder with hexadecimal outputs, but requires fewer switches.
 *
 * @author Peter H&auml;nsgen
 */
public class BCDDecoder10 extends Component
{
    private final Input powerIn;

    private final Input in0;

    private final Input in1;

    private final Input in2;

    private final Input in3;

    private final Output out0;

    private final Output out1;

    private final Output out2;

    private final Output out3;

    private final Output out4;

    private final Output out5;

    private final Output out6;

    private final Output out7;

    private final Output out8;

    private final Output out9;

    private final Output outA;

    /**
     * The constructor.
     */
    public BCDDecoder10(Circuit parent, String name)
    {
        super(parent, name);

        powerIn = new Input();

        in0 = new Input();
        in1 = new Input();
        in2 = new Input();
        in3 = new Input();

        out0 = new Output();
        out1 = new Output();
        out2 = new Output();
        out3 = new Output();
        out4 = new Output();
        out5 = new Output();
        out6 = new Output();
        out7 = new Output();
        out8 = new Output();
        out9 = new Output();
        outA = new Output();

        Circuit local = getLocalCircuit();

        // create top first to reduce simulation dependencies
        Relay r3 = new Relay(local, name + "_R3");
        Relay r2 = new Relay(local, name + "_R2");
        Relay r1 = new Relay(local, name + "_R1");
        Relay r0 = new Relay(local, name + "_R0");

        // internal wiring
        // connect input signals
        new Signal(local).from(in0).to(r0.getCoilIn());
        new Signal(local).from(in1).to(r1.getCoilIn());
        new Signal(local).from(in2).to(r2.getCoilIn());
        new Signal(local).from(in3).to(r3.getCoilIn());

        // each relay has 8 switches (0..F)
        // connect the middle of r3 with +
        // the others are chained
        new Signal(local).from(powerIn).to(r3.getMiddleIn(0));

        new Signal(local).from(r3.get_Out(0)).to(r2.getMiddleIn(0));
        new Signal(local).from(r3.getOut(0)).to(r2.getMiddleIn(1));

        new Signal(local).from(r2.get_Out(0)).to(r1.getMiddleIn(0));
        new Signal(local).from(r2.getOut(0)).to(r1.getMiddleIn(1));
        new Signal(local).from(r2.get_Out(1)).to(r1.getMiddleIn(2));
        new Signal(local).from(r2.getOut(1)).to(r1.getMiddleIn(3));

        new Signal(local).from(r1.get_Out(0)).to(r0.getMiddleIn(0));
        new Signal(local).from(r1.getOut(0)).to(r0.getMiddleIn(1));
        new Signal(local).from(r1.get_Out(1)).to(r0.getMiddleIn(2));
        new Signal(local).from(r1.getOut(1)).to(r0.getMiddleIn(3));
        new Signal(local).from(r1.get_Out(2)).to(r0.getMiddleIn(4));
        new Signal(local).from(r1.getOut(2)).to(r0.getMiddleIn(5));

        // connect output signals
        new Signal(local).from(r0.get_Out(0)).to(out0);
        new Signal(local).from(r0.getOut(0)).to(out1);
        new Signal(local).from(r0.get_Out(1)).to(out2);
        new Signal(local).from(r0.getOut(1)).to(out3);
        new Signal(local).from(r0.get_Out(2)).to(out4);
        new Signal(local).from(r0.getOut(2)).to(out5);
        new Signal(local).from(r0.get_Out(3)).to(out6);
        new Signal(local).from(r0.getOut(3)).to(out7);
        new Signal(local).from(r0.get_Out(4)).to(out8);
        new Signal(local).from(r0.getOut(4)).to(out9);
        new Signal(local).from(r0.get_Out(5)).to(outA);
    }

    public Input getPowerIn()
    {
        return powerIn;
    }

    public Input getIn0()
    {
        return in0;
    }

    public Input getIn1()
    {
        return in1;
    }

    public Input getIn2()
    {
        return in2;
    }

    public Input getIn3()
    {
        return in3;
    }

    public Output getOut0()
    {
        return out0;
    }

    public Output getOut1()
    {
        return out1;
    }

    public Output getOut2()
    {
        return out2;
    }

    public Output getOut3()
    {
        return out3;
    }

    public Output getOut4()
    {
        return out4;
    }

    public Output getOut5()
    {
        return out5;
    }

    public Output getOut6()
    {
        return out6;
    }

    public Output getOut7()
    {
        return out7;
    }

    public Output getOut8()
    {
        return out8;
    }

    public Output getOut9()
    {
        return out9;
    }

    public Output getOutA()
    {
        return outA;
    }
}
