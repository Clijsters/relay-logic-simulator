package de.clijsters.resi.example.addingmachine;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Component;
import de.clijsters.resi.common.Input;
import de.clijsters.resi.common.Output;
import de.clijsters.resi.common.Signal;
import de.clijsters.resi.library.FullAdder;

import java.util.List;
import java.util.Optional;

import lombok.Getter;

@Getter
public class TenBitAdder extends Component {
    private List<FullAdder> fullAdders;
    private List<Input> inputsA;
    private List<Input> inputsB;
    private List<Output> outputsSum;

    private Output outputCarry = new Output();
    private Input inputCarry = new Input();

    public TenBitAdder(Circuit parent, String name) {
        super(parent, name);
        Circuit local = getLocalCircuit();
        for (int i = 0; i < 10; i++) {
            final FullAdder fullAdder = new FullAdder(local, "TenBitAdder_FA" + i);
            fullAdders.add(fullAdder);
            inputsA.add(fullAdder.getInA());
            inputsB.add(fullAdder.getInB());
            outputsSum.add(fullAdder.getOutSum());

            if (i == 0) {
                new Signal(local).from(inputCarry).to(fullAdder.getInCarry());
            } else  {
                new Signal(local).from(fullAdders.get(i - 1).getOutCarry()).to(fullAdder.getInCarry());
                if (i == 9) {
                    new Signal(local).from(fullAdder.getOutCarry()).to(outputCarry);
                }
            }
        }
    }

    public int getSum() {
        StringBuilder stringBuilder = new StringBuilder(10);
        outputsSum.forEach(output -> {
            stringBuilder.append(Optional.ofNullable(output.getSignal().getValue()).orElse(false) ? "1" : "0");
        });

        return Integer.parseInt(stringBuilder.toString(), 2);
    }
}
