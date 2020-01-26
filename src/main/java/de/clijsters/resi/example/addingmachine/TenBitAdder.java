package de.clijsters.resi.example.addingmachine;

import de.clijsters.resi.common.*;
import de.clijsters.resi.library.FullAdder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class TenBitAdder extends Component {
	private List<FullAdder> fullAdders = new ArrayList<>();
	private List<Input> inputsA = new ArrayList<>();
	private List<Input> inputsB = new ArrayList<>();
	private List<Output> outputsSum = new ArrayList<>();

	private Output outputCarry = new Output();
	private Input inputCarry = new Input();

	public TenBitAdder(Circuit parent, String name) {
		super(parent, name);
		Circuit local = getLocalCircuit();
		Power vcc = new Power(local, "VCC");
		for (int i = 0; i < 10; i++) {
			final FullAdder fullAdder = new FullAdder(local, "TenBitAdder_FA" + i);
			fullAdders.add(fullAdder);


			Input aInput = new Input();
			new Signal(local).from(aInput).to(fullAdder.getInA());
			inputsA.add(aInput);

			Input bInput = new Input();
			new Signal(local).from(bInput).to(fullAdder.getInB());
			inputsB.add(bInput);

			Output sumOut = new Output();
			new Signal(local).from(sumOut).to(fullAdder.getOutSum());
			outputsSum.add(sumOut);

			if (i == 0) {
				new Signal(local).from(inputCarry).to(fullAdder.getInCarry());
			} else {
				new Signal(local).from(fullAdders.get(i - 1).getOutCarry()).to(fullAdder.getInCarry());
				if (i == 9) {
					new Signal(local).from(fullAdder.getOutCarry()).to(outputCarry);
				}
			}
		}
		List<Input> inputs = fullAdders.stream().map(FullAdder::getPowerIn).collect(Collectors.toList());
		Input[] t = {};

		new Signal(local).from(vcc.getOut()).to(inputs.toArray(t));
	}

	public int getSum() {
		StringBuilder stringBuilder = new StringBuilder(10);
		outputsSum.forEach(output -> {
			stringBuilder.append(Optional.ofNullable(output.getSignal().getValue()).orElse(false) ? "1" : "0");
		});

		return Integer.parseInt(stringBuilder.toString(), 2);
	}
}
