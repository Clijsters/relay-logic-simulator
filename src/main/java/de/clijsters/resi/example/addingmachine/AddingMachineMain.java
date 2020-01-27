package de.clijsters.resi.example.addingmachine;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Input;
import de.clijsters.resi.common.Signal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class AddingMachineMain {

	public static void main(String[] args) throws IOException {
		Circuit circuit = new Circuit();
		TenBitAdder tenBitAdder = new TenBitAdder(circuit, "tba");
		List<Input> sum = new ArrayList<>();
		Input carry = new Input();

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


		IntStream.range(0, 10).forEach(value -> {
			Signal aSignal = new Signal(circuit, "ain_" + value);
			aSignal.setValue(false);
			tenBitAdder.getInputsA().get(value).setSignal(aSignal);
			Signal bSignal = new Signal(circuit, "bin_" + value);
			bSignal.setValue(false);
			tenBitAdder.getInputsB().get(value).setSignal(bSignal);
			Input sumIn = new Input();
			new Signal(circuit).from(tenBitAdder.getOutputsSum().get(value)).to(sumIn);
			sum.add(sumIn);
		});
		new Signal(circuit).from(tenBitAdder.getOutputCarry()).to(carry);

		circuit.simulate();

		while (true) {
			System.out.print("A: ");
			Integer a = Integer.valueOf(reader.readLine());
			String aString = intToBinString(a);

			System.out.print("B: ");
			Integer b = Integer.valueOf(reader.readLine());
			String bString = intToBinString(b);


			System.out.println("   A = " + aString + " = " + a.toString());
			System.out.println("   B = " + bString + " = " + b.toString());

			List<Boolean> aIn = binStringToBoolList(aString);
			List<Boolean> bIn = binStringToBoolList(bString);
			IntStream.range(0, 10).forEach(value -> {
				tenBitAdder.getInputsA().get(value).getSignal().setValue(aIn.get(value));
				tenBitAdder.getInputsB().get(value).getSignal().setValue(bIn.get(value));
				circuit.simulate();
			});
			circuit.simulate();
			System.out.println(String.format("Sum = %s = %s", tenBitAdder.getSumBin(), tenBitAdder.getSumDec()));
			System.out.println("      CSSSSSSSSSS");
		}
	}

	private static List<Boolean> binStringToBoolList(String binaryNumber) {
		List<Boolean> result = new ArrayList<>();
		for (char c : binaryNumber.toCharArray()) {
			result.add('1' == c);
		}
		return result;
	}

	private static String intToBinString(int decimalNumber) {
		String binaryString = Integer.toBinaryString(decimalNumber);
		if (binaryString.length() > 10) {
			throw new IllegalArgumentException("A ten bit adder can't add numbers larger than 10 bit.");
		}
		StringBuilder result = new StringBuilder();
		//Leading zeros
		for (int i = 0; i < 10 - binaryString.length(); i++) {
			result.append("0");
		}
		result.append(binaryString);
		return result.toString();
	}
}
