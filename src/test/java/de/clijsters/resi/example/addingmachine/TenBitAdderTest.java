package de.clijsters.resi.example.addingmachine;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Output;
import de.clijsters.resi.common.Signal;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

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




		tenBitAdder.getOutputsSum().stream()
				.map(Output::getSignal)
				.map(Signal::getValue)//Duplicated
				.map(aBoolean -> Optional.ofNullable(aBoolean).orElse(false))
				.map(aBoolean -> aBoolean ? "1" : "0")
				.forEach(System.out::print);
    }
}
