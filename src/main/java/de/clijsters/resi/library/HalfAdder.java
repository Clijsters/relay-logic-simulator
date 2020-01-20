package de.clijsters.resi.library;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Component;
import de.clijsters.resi.common.Input;
import de.clijsters.resi.common.Output;
import de.clijsters.resi.common.Signal;

import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class HalfAdder extends Component {
    @Getter(AccessLevel.NONE)
    private Xor xor;
    @Getter(AccessLevel.NONE)
    private And and;

    private Input inA = new Input();
    private Input inB = new Input();
    private Output outSum = new Output();
    private Output outCarry = new Output();

    private Input powerIn = new Input();

    public HalfAdder(Circuit parent, String name) {
        super(parent, name);
        Circuit local = getLocalCircuit();
        this.xor = new Xor(local, name + "_XOR");
        this.and = new And(local, name + "_AND", 2);

        new Signal(local).from(powerIn).to(xor.getPowerIn(), and.getPowerIn());
        new Signal(local).from(inA).to(xor.getIn0(), and.getIn(0));
        new Signal(local).from(inB).to(xor.getIn1(), and.getIn(1));
        new Signal(local).from(xor.getOut()).to(outSum);
        new Signal(local).from(and.getOut()).to(outCarry);
    }
}
