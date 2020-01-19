package de.clijsters.resi.library;

import de.clijsters.resi.common.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests the 7-segment decoder with input range 0..2.
 *
 * @author Peter H&auml;nsgen
 */
public class SevenSegmentDecoder3Test {
	private SevenSegmentDecoder3 decoder;
	private Output in0;
	private Output in1;
	private Output in2;
	private Input outA;
	private Input outB;
	private Input outC;
	private Input outD;
	private Input outE;
	private Input outF;
	private Input outG;

	@Before
	public void before() {
		Circuit circuit = new Circuit();
		Power p = new Power(circuit, "VCC");

		decoder = new SevenSegmentDecoder3(circuit, "Decoder");

		// test signals
		in0 = new Output();
		in1 = new Output();
		in2 = new Output();
		outA = new Input();
		outB = new Input();
		outC = new Input();
		outD = new Input();
		outE = new Input();
		outF = new Input();
		outG = new Input();

		// connect test signals
		new Signal(circuit).from(p.getOut()).to(decoder.getPowerIn());

		new Signal(circuit).from(in0).to(decoder.getIn0());
		new Signal(circuit).from(in1).to(decoder.getIn1());
		new Signal(circuit).from(in2).to(decoder.getIn2());
		new Signal(circuit).from(decoder.getOutA()).to(outA);
		new Signal(circuit).from(decoder.getOutB()).to(outB);
		new Signal(circuit).from(decoder.getOutC()).to(outC);
		new Signal(circuit).from(decoder.getOutD()).to(outD);
		new Signal(circuit).from(decoder.getOutE()).to(outE);
		new Signal(circuit).from(decoder.getOutF()).to(outF);
		new Signal(circuit).from(decoder.getOutG()).to(outG);
	}

	@Test
	public void testOff() {
		decoder.getCircuit().simulate();
		assertNotEquals(Boolean.TRUE, outA.getValue());
		assertNotEquals(Boolean.TRUE, outB.getValue());
		assertNotEquals(Boolean.TRUE, outC.getValue());
		assertNotEquals(Boolean.TRUE, outD.getValue());
		assertNotEquals(Boolean.TRUE, outE.getValue());
		assertNotEquals(Boolean.TRUE, outF.getValue());
		assertNotEquals(Boolean.TRUE, outG.getValue());
	}

	@Test
	public void test0() {
		in0.setValue(true);
		decoder.getCircuit().simulate();
		assertEquals(Boolean.TRUE, outA.getValue());
		assertEquals(Boolean.TRUE, outB.getValue());
		assertEquals(Boolean.TRUE, outC.getValue());
		assertEquals(Boolean.TRUE, outD.getValue());
		assertEquals(Boolean.TRUE, outE.getValue());
		assertEquals(Boolean.TRUE, outF.getValue());
		assertNotEquals(Boolean.TRUE, outG.getValue());
	}

	@Test
	public void test1() {
		in1.setValue(true);
		decoder.getCircuit().simulate();
		assertNotEquals(Boolean.TRUE, outA.getValue());
		assertEquals(Boolean.TRUE, outB.getValue());
		assertEquals(Boolean.TRUE, outC.getValue());
		assertNotEquals(Boolean.TRUE, outD.getValue());
		assertNotEquals(Boolean.TRUE, outE.getValue());
		assertNotEquals(Boolean.TRUE, outF.getValue());
		assertNotEquals(Boolean.TRUE, outG.getValue());
	}

	@Test
	public void test2() {
		in2.setValue(true);
		decoder.getCircuit().simulate();
		assertEquals(Boolean.TRUE, outA.getValue());
		assertEquals(Boolean.TRUE, outB.getValue());
		assertNotEquals(Boolean.TRUE, outC.getValue());
		assertEquals(Boolean.TRUE, outD.getValue());
		assertEquals(Boolean.TRUE, outE.getValue());
		assertNotEquals(Boolean.TRUE, outF.getValue());
		assertEquals(Boolean.TRUE, outG.getValue());
	}
}
