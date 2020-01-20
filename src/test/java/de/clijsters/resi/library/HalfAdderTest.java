package de.clijsters.resi.library;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Input;
import de.clijsters.resi.common.Output;
import de.clijsters.resi.common.Power;
import de.clijsters.resi.common.Signal;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class HalfAdderTest {
    private static final Circuit circuit = new Circuit();
    private static final HalfAdder underTest = new HalfAdder(circuit, "TestHalfAdder");

    private static final Output a = new Output();
    private static final Output b = new Output();
    private static final Input sum = new Input();
    private static final Input carry = new Input();



    /*
     |0  1  |
    -+------|
    0|00 01 |
    1|01 10 |

     */
    @BeforeClass
    public static void prepare() {
        Power power = new Power(circuit, "testPower");
        new Signal(circuit).from(power.getOut()).to(underTest.getPowerIn());
        new Signal(circuit).from(a).to(underTest.getInA());
        new Signal(circuit).from(b).to(underTest.getInB());
        new Signal(circuit).from(underTest.getOutCarry()).to(carry);
        new Signal(circuit).from(underTest.getOutSum()).to(sum);
    }

    @Test
    public void zeroPlusZero() {
        a.setValue(false);
        b.setValue(false);
        circuit.simulate();
        assertNull(sum.getValue());
        assertNull(carry.getValue());
    }

    @Test
    public void zeroPlusOne() {
        a.setValue(false);
        b.setValue(true);
        circuit.simulate();
        circuit.simulate();
        assertTrue(sum.getValue());
        assertNull(carry.getValue());
    }

    @Test
    public void onePlusZero() {
        a.setValue(true);
        b.setValue(false);
        circuit.simulate();
        circuit.simulate();
        assertTrue(sum.getValue());
        assertNull(carry.getValue());
    }

    @Test
    public void onePlusOne() {
        a.setValue(true);
        b.setValue(true);
        circuit.simulate();
        circuit.simulate();
        assertNull(sum.getValue());
        assertTrue(carry.getValue());
    }
}
