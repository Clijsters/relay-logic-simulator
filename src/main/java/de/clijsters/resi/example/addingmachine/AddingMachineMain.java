package de.clijsters.resi.example.addingmachine;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Input;

public class AddingMachineMain {

    public static void main(String[] args) {
        Circuit circuit = new Circuit();
        TenBitAdder tenBitAdder = new TenBitAdder(circuit,"tba");

        boolean[] input = {
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
            tenBitAdder.getInputsA().get(i).getSignal().setValue(input[i]);
        }
        
    }
}
