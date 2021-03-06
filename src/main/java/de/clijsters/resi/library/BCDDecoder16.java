package de.clijsters.resi.library;

import de.clijsters.resi.common.*;
import lombok.Getter;

/**
 * This is a decoder that converts a 4-bit BCD value into hexadecimal outputs.
 *
 * @author Peter H&auml;nsgen
 */
@Getter
public class BCDDecoder16 extends Component {
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
	private final Output outB;
	private final Output outC;
	private final Output outD;
	private final Output outE;
	private final Output outF;

	/**
	 * The constructor.
	 */
	public BCDDecoder16(Circuit parent, String name) {
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
		outB = new Output();
		outC = new Output();
		outD = new Output();
		outE = new Output();
		outF = new Output();

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
		new Signal(local).from(r1.get_Out(3)).to(r0.getMiddleIn(6));
		new Signal(local).from(r1.getOut(3)).to(r0.getMiddleIn(7));

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
		new Signal(local).from(r0.getOut(5)).to(outB);
		new Signal(local).from(r0.get_Out(6)).to(outC);
		new Signal(local).from(r0.getOut(6)).to(outD);
		new Signal(local).from(r0.get_Out(7)).to(outE);
		new Signal(local).from(r0.getOut(7)).to(outF);
	}

}
