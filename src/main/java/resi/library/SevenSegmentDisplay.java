package resi.library;

import resi.common.Circuit;
import resi.common.Component;
import resi.common.Input;
import resi.common.Signal;

/**
 * This is a 7-segment-display made of lamps.
 *
 * @author Peter H&auml;nsgen
 */
public class SevenSegmentDisplay extends Component {

    private final Lamp a;
    private final Lamp b;
    private final Lamp c;
    private final Lamp d;
    private final Lamp e;
    private final Lamp f;
    private final Lamp g;

    private final Input inA;
    private final Input inB;
    private final Input inC;
    private final Input inD;
    private final Input inE;
    private final Input inF;
    private final Input inG;

    /**
     * The constructor.
     */
    public SevenSegmentDisplay(Circuit parent, String name)
    {
        super(parent, name);

        inA = new Input();
        inB = new Input();
        inC = new Input();
        inD = new Input();
        inE = new Input();
        inF = new Input();
        inG = new Input();

        Circuit local = getLocalCircuit();

        a = new Lamp(local, name + "_A");
        b = new Lamp(local, name + "_B");
        c = new Lamp(local, name + "_C");
        d = new Lamp(local, name + "_D");
        e = new Lamp(local, name + "_E");
        f = new Lamp(local, name + "_F");
        g = new Lamp(local, name + "_G");

        // internal wirings
        new Signal(local).from(inA).to(a.getIn());
        new Signal(local).from(inB).to(b.getIn());
        new Signal(local).from(inC).to(c.getIn());
        new Signal(local).from(inD).to(d.getIn());
        new Signal(local).from(inE).to(e.getIn());
        new Signal(local).from(inF).to(f.getIn());
        new Signal(local).from(inG).to(g.getIn());
    }

	@Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(" ");
        sb.append(a.getOn());
        sb.append(a.getOn());
        sb.append(" \n");

        sb.append(f.getOn());
        sb.append("  ");
        sb.append(b.getOn());
        sb.append(" \n");
        sb.append(f.getOn());
        sb.append("  ");
        sb.append(b.getOn());
        sb.append(" \n");

        sb.append(" ");
        sb.append(g.getOn());
        sb.append(g.getOn());
        sb.append(" \n");

        sb.append(e.getOn());
        sb.append("  ");
        sb.append(c.getOn());
        sb.append(" \n");
        sb.append(e.getOn());
        sb.append("  ");
        sb.append(c.getOn());
        sb.append(" \n");

        sb.append(" ");
        sb.append(d.getOn());
        sb.append(d.getOn());
        sb.append(" \n");

        return sb.toString();
    }

	public Lamp getA() {
		return this.a;
	}

	public Lamp getB() {
		return this.b;
	}

	public Lamp getC() {
		return this.c;
	}

	public Lamp getD() {
		return this.d;
	}

	public Lamp getE() {
		return this.e;
	}

	public Lamp getF() {
		return this.f;
	}

	public Lamp getG() {
		return this.g;
	}

	public Input getInA() {
		return this.inA;
	}

	public Input getInB() {
		return this.inB;
	}

	public Input getInC() {
		return this.inC;
	}

	public Input getInD() {
		return this.inD;
	}

	public Input getInE() {
		return this.inE;
	}

	public Input getInF() {
		return this.inF;
	}

	public Input getInG() {
		return this.inG;
	}
}
