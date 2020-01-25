package de.clijsters.resi.example.addingmachine;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Output;
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

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("A:");
		Integer a = 123;//Integer.valueOf(reader.readLine());
		String aString = intToBinString(a);

		System.out.println("B:");
		Integer b = 321;//Integer.valueOf(reader.readLine());
		String bString = intToBinString(b);

		System.out.println("Using " + aString + " as A-Input.");
		System.out.println("Using " + bString + " as B-Input.");

		List<Boolean> aIn = binStringToBoolList(aString);
		List<Boolean> bIn = binStringToBoolList(bString);
		IntStream.range(0,9).forEach(value -> {
			Signal aSignal = new Signal(circuit, "ain_" + value);
			aSignal.setValue(aIn.get(value));
			tenBitAdder.getInputsA().get(value).setSignal(aSignal);
			Signal bSignal = new Signal(circuit, "bin_" + value);
			bSignal.setValue(bIn.get(value));
			tenBitAdder.getInputsB().get(value).setSignal(bSignal);
		});
		circuit.simulate();
		circuit.simulate();
		circuit.simulate();



		tenBitAdder.getOutputsSum().stream().map(Output::getSignal).map(Signal::getValue).forEach(output -> {
			System.out.print(output ? "1" : "0");
		});
	}

	private static List<Boolean> binStringToBoolList(String binaryNumber) {
		List<Boolean> result = new ArrayList<>();
		for (char c : binaryNumber.toCharArray()) {
			result.add('1'== c);
		}
		return result;
	}

	private static String intToBinString(int decimalNumber) {
		String binaryString = Integer.toBinaryString(decimalNumber);
		if (binaryString.length() > 10) {
			throw new IllegalArgumentException("A ten bit adder can't add numbers larger than 10 bit.");
		}
		String result = "";
		for (int i = 0; i < 10 - binaryString.length(); i++) {
			result += "0";
		}
		result += binaryString;
		return result;
	}

	private static int binStringToDecNumber(String binaryNumber) {
		return Integer.parseInt(binaryNumber, 2);
	}
}
