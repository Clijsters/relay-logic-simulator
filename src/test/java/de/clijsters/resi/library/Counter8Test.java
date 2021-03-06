package de.clijsters.resi.library;

import de.clijsters.resi.common.*;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests the 3-bit counter.
 *
 * @author Peter H&auml;nsgen
 */
public class Counter8Test {
	@Test
	public void testCounter() {
		Circuit circuit = new Circuit();
		Power power = new Power(circuit, "VCC");
		Counter8 counter = new Counter8(circuit, "Counter");

		new Signal(circuit).from(power.getOut()).to(counter.getPowerIn());

		Output _clock = new Output();
		Output clock = new Output();
		Input out0 = new Input();
		Input out1 = new Input();
		Input out2 = new Input();

		// test wirings
		new Signal(circuit).from(_clock).to(counter.get_clock());
		new Signal(circuit).from(clock).to(counter.getClock());
		new Signal(circuit).from(counter.getOut0()).to(out0);
		new Signal(circuit).from(counter.getOut1()).to(out1);
		new Signal(circuit).from(counter.getOut2()).to(out2);

		// test counter
		// initially, output is 0
		_clock.setValue(true);
		clock.setValue(null);
		circuit.simulate();
		assertNull(out0.getValue());
		assertNull(out1.getValue());
		assertNull(out2.getValue());

		_clock.setValue(null);
		clock.setValue(true);
		circuit.simulate();
		assertNull(out0.getValue());
		assertNull(out1.getValue());
		assertNull(out2.getValue());

		// 1
		_clock.setValue(null);
		clock.setValue(true);
		circuit.simulate();
		assertNull(out0.getValue());
		assertNull(out1.getValue());
		assertNull(out2.getValue());

		_clock.setValue(true);
		clock.setValue(null);
		circuit.simulate();
		assertTrue(out0.getValue());
		assertNull(out1.getValue());
		assertNull(out2.getValue());

		// 2
		_clock.setValue(null);
		clock.setValue(true);
		circuit.simulate();
		assertTrue(out0.getValue());
		assertNull(out1.getValue());
		assertNull(out2.getValue());

		_clock.setValue(true);
		clock.setValue(null);
		circuit.simulate();
		assertNull(out0.getValue());
		assertTrue(out1.getValue());
		assertNull(out2.getValue());

		// 3
		_clock.setValue(null);
		clock.setValue(true);
		circuit.simulate();
		assertNull(out0.getValue());
		assertTrue(out1.getValue());
		assertNull(out2.getValue());

		_clock.setValue(true);
		clock.setValue(null);
		circuit.simulate();
		assertTrue(out0.getValue());
		assertTrue(out1.getValue());
		assertNull(out2.getValue());

		// 4
		_clock.setValue(null);
		clock.setValue(true);
		circuit.simulate();
		assertTrue(out0.getValue());
		assertTrue(out1.getValue());
		assertNull(out2.getValue());

		_clock.setValue(true);
		clock.setValue(null);
		circuit.simulate();
		assertNull(out0.getValue());
		assertNull(out1.getValue());
		assertTrue(out2.getValue());

		// 5
		_clock.setValue(null);
		clock.setValue(true);
		circuit.simulate();
		assertNull(out0.getValue());
		assertNull(out1.getValue());
		assertTrue(out2.getValue());

		_clock.setValue(true);
		clock.setValue(null);
		circuit.simulate();
		assertTrue(out0.getValue());
		assertNull(out1.getValue());
		assertTrue(out2.getValue());

		// 6
		_clock.setValue(null);
		clock.setValue(true);
		circuit.simulate();
		assertTrue(out0.getValue());
		assertNull(out1.getValue());
		assertTrue(out2.getValue());

		_clock.setValue(true);
		clock.setValue(null);
		circuit.simulate();
		assertNull(out0.getValue());
		assertTrue(out1.getValue());
		assertTrue(out2.getValue());

		// 7
		_clock.setValue(null);
		clock.setValue(true);
		circuit.simulate();
		assertNull(out0.getValue());
		assertTrue(out1.getValue());
		assertTrue(out2.getValue());

		_clock.setValue(true);
		clock.setValue(null);
		circuit.simulate();
		assertTrue(out0.getValue());
		assertTrue(out1.getValue());
		assertTrue(out2.getValue());

		// 0
		_clock.setValue(null);
		clock.setValue(true);
		circuit.simulate();
		assertTrue(out0.getValue());
		assertTrue(out1.getValue());
		assertTrue(out2.getValue());

		_clock.setValue(true);
		clock.setValue(null);
		circuit.simulate();
		assertNull(out0.getValue());
		assertNull(out1.getValue());
		assertNull(out2.getValue());
	}
}
