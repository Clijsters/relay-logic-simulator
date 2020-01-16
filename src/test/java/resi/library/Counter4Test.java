package resi.library;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import resi.library.Counter4;
import org.junit.Test;

import resi.common.Circuit;
import resi.common.Input;
import resi.common.Output;
import resi.common.Power;
import resi.common.Signal;

/**
 * Tests the 2-bit counter.
 * 
 * @author Peter H&auml;nsgen
 */
public class Counter4Test
{
    @Test
    public void testCounter()
    {
        Circuit circuit = new Circuit();

        Power power = new Power(circuit, "VCC");
        Counter4 counter = new Counter4(circuit, "Counter");

        new Signal(circuit).from(power.getOut()).to(counter.getPowerIn());

        Output _clock = new Output();
        Output clock = new Output();
        Input out0 = new Input();
        Input out1 = new Input();

        // test wirings
        new Signal(circuit).from(_clock).to(counter.get_Clock());
        new Signal(circuit).from(clock).to(counter.getClock());
        new Signal(circuit).from(counter.getOut0()).to(out0);
        new Signal(circuit).from(counter.getOut1()).to(out1);

        // test counter
        // initially, output is 0
        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());

        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());

        // 1
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertTrue(out0.getValue());
        assertNull(out1.getValue());

        // 2
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertTrue(out0.getValue());
        assertNull(out1.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertNull(out0.getValue());
        assertTrue(out1.getValue());

        // 3
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertNull(out0.getValue());
        assertTrue(out1.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertTrue(out0.getValue());
        assertTrue(out1.getValue());

        // 0
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertTrue(out0.getValue());
        assertTrue(out1.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());
    }
}
