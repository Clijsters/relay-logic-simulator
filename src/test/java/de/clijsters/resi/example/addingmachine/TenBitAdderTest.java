package de.clijsters.resi.example.addingmachine;

import de.clijsters.resi.common.Circuit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TenBitAdderTest {

    TenBitAdder underTest;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getSum() {
		Circuit circuit = new Circuit();
		TenBitAdder tenBitAdder = new TenBitAdder(circuit,"tba");

		boolean[] inputA = {
				true,
				false,
				false,
				false,
				false,
				false,
				true,
				true,
				true,
				true
		};
		for (int i = 0; i < 10; i++) {
			tenBitAdder.getInputsA().get(i).getSignal().setValue(inputA[i]);
		}
    }
}
