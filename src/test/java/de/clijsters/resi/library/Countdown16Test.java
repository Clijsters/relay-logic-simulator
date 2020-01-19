package de.clijsters.resi.library;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import de.clijsters.resi.library.Countdown16;
import org.junit.Test;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Input;
import de.clijsters.resi.common.Output;
import de.clijsters.resi.common.Power;
import de.clijsters.resi.common.Signal;

/**
 * Tests the 4-bit countdown.
 *
 * @author Peter H&auml;nsgen
 */
public class Countdown16Test
{
    @Test
    public void testCounter()
    {
        Circuit circuit = new Circuit();

        Power power = new Power(circuit, "VCC");
        Countdown16 countdown = new Countdown16(circuit, "Countdown");

        new Signal(circuit).from(power.getOut()).to(countdown.getPowerIn());

        Output _clock = new Output();
        Output clock = new Output();
        Input out0 = new Input();
        Input out1 = new Input();
        Input out2 = new Input();
        Input out3 = new Input();

        // test wirings
        new Signal(circuit).from(_clock).to(countdown.get_Clock());
        new Signal(circuit).from(clock).to(countdown.getClock());
        new Signal(circuit).from(countdown.getOut0()).to(out0);
        new Signal(circuit).from(countdown.getOut1()).to(out1);
        new Signal(circuit).from(countdown.getOut2()).to(out2);
        new Signal(circuit).from(countdown.getOut3()).to(out3);

        // test countdown
        // initially, output is 0
        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());

        // 15
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertTrue(out0.getValue());
        assertTrue(out1.getValue());
        assertTrue(out2.getValue());
        assertTrue(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertTrue(out0.getValue());
        assertTrue(out1.getValue());
        assertTrue(out2.getValue());
        assertTrue(out3.getValue());

        // 14
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertNull(out0.getValue());
        assertTrue(out1.getValue());
        assertTrue(out2.getValue());
        assertTrue(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertNull(out0.getValue());
        assertTrue(out1.getValue());
        assertTrue(out2.getValue());
        assertTrue(out3.getValue());

        // 13
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertTrue(out0.getValue());
        assertNull(out1.getValue());
        assertTrue(out2.getValue());
        assertTrue(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertTrue(out0.getValue());
        assertNull(out1.getValue());
        assertTrue(out2.getValue());
        assertTrue(out3.getValue());

        // 12
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertTrue(out2.getValue());
        assertTrue(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertTrue(out2.getValue());
        assertTrue(out3.getValue());

        // 11
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertTrue(out0.getValue());
        assertTrue(out1.getValue());
        assertNull(out2.getValue());
        assertTrue(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertTrue(out0.getValue());
        assertTrue(out1.getValue());
        assertNull(out2.getValue());
        assertTrue(out3.getValue());

        // 10
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertNull(out0.getValue());
        assertTrue(out1.getValue());
        assertNull(out2.getValue());
        assertTrue(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertNull(out0.getValue());
        assertTrue(out1.getValue());
        assertNull(out2.getValue());
        assertTrue(out3.getValue());

        // 9
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertTrue(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertTrue(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertTrue(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertTrue(out3.getValue());

        // 8
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertTrue(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertTrue(out3.getValue());

        // 7
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertTrue(out0.getValue());
        assertTrue(out1.getValue());
        assertTrue(out2.getValue());
        assertNull(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertTrue(out0.getValue());
        assertTrue(out1.getValue());
        assertTrue(out2.getValue());
        assertNull(out3.getValue());

        // 6
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertNull(out0.getValue());
        assertTrue(out1.getValue());
        assertTrue(out2.getValue());
        assertNull(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertNull(out0.getValue());
        assertTrue(out1.getValue());
        assertTrue(out2.getValue());
        assertNull(out3.getValue());

        // 5
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertTrue(out0.getValue());
        assertNull(out1.getValue());
        assertTrue(out2.getValue());
        assertNull(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertTrue(out0.getValue());
        assertNull(out1.getValue());
        assertTrue(out2.getValue());
        assertNull(out3.getValue());

        // 4
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertTrue(out2.getValue());
        assertNull(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertTrue(out2.getValue());
        assertNull(out3.getValue());

        // 3
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertTrue(out0.getValue());
        assertTrue(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertTrue(out0.getValue());
        assertTrue(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());

        // 2
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertNull(out0.getValue());
        assertTrue(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertNull(out0.getValue());
        assertTrue(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());

        // 1
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertTrue(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertTrue(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());

        // 0
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());
    }
}
