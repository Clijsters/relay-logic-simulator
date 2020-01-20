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

public class FullAdderTest {

    private static final Circuit circuit = new Circuit();
    private static final Power power = new Power(circuit, "testPower");
    private static FullAdder underTest = new FullAdder(circuit, "TestFullAdder");

    private static final Output a = new Output();
    private static final Output b = new Output();
    private static final Output carryIn = new Output();
    private static final Input sum = new Input();
    private static final Input carryOut = new Input();

    @BeforeClass
    public static void prepare() {
        new Signal(circuit).from(power.getOut()).to(underTest.getPowerIn());
        new Signal(circuit).from(a).to(underTest.getInA());
        new Signal(circuit).from(b).to(underTest.getInB());
        new Signal(circuit).from(carryIn).to(underTest.getInCarry());
        new Signal(circuit).from(underTest.getOutCarry()).to(carryOut);
        new Signal(circuit).from(underTest.getOutSum()).to(sum);
    }


    @Test
    public void test000() {
        a.setValue(null);
        b.setValue(null);
        carryIn.setValue(null);
        circuit.simulate();
        assertNull(sum.getValue());
        assertNull(carryOut.getValue());
    }

    @Test
    public void test001() {
        a.setValue(null);
        b.setValue(null);
        carryIn.setValue(true);
        circuit.simulate();
        assertTrue(sum.getValue());
        assertNull(carryOut.getValue());
    }


    @Test
    public void test010() {
        a.setValue(null);
        b.setValue(true);
        carryIn.setValue(null);
        circuit.simulate();
        assertTrue(sum.getValue());
        assertNull(carryOut.getValue());
    }

    @Test
    public void test011() {
        a.setValue(null);
        b.setValue(true);
        carryIn.setValue(true);
        circuit.simulate();
        circuit.simulate();
        assertNull(sum.getValue());
        assertTrue(carryOut.getValue());
    }

    @Test
    public void test100() {
        a.setValue(true);
        b.setValue(null);
        carryIn.setValue(null);
        circuit.simulate();
        assertTrue(sum.getValue());
        assertNull(carryOut.getValue());
    }

    @Test
    public void test110() {
        a.setValue(true);
        b.setValue(true);
        carryIn.setValue(null);
        circuit.simulate();
        assertNull(sum.getValue());
        assertTrue(carryOut.getValue());
    }

    @Test
    public void test111() {
        a.setValue(true);
        b.setValue(true);
        carryIn.setValue(true);
        circuit.simulate();
        assertTrue(sum.getValue());
        assertTrue(carryOut.getValue());
    }


}
