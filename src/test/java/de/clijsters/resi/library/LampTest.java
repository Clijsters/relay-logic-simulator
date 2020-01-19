package de.clijsters.resi.library;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Output;
import de.clijsters.resi.common.Signal;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the lamp.
 *
 * @author Peter H&auml;nsgen
 */
public class LampTest {
	@Test
	public void testLamp() {
		Circuit circuit = new Circuit();
		Lamp lamp = new Lamp(circuit, "L1");

		Output o = new Output();

		new Signal(circuit).from(o).to(lamp.getIn());

		o.setValue(null);
		circuit.simulate();
		assertFalse(lamp.isOn());

		o.setValue(true);
		circuit.simulate();
		assertTrue(lamp.isOn());

		o.setValue(false);
		circuit.simulate();
		assertFalse(lamp.isOn());
	}
}
