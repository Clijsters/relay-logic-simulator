package de.clijsters.resi.library;

import de.clijsters.resi.common.Circuit;
import de.clijsters.resi.common.Component;
import de.clijsters.resi.common.Input;
import de.clijsters.resi.common.Output;
import de.clijsters.resi.common.Signal;

import lombok.Getter;

@Getter
public class FullAdder extends Component {

    private HalfAdder halfAdderA;
    private HalfAdder halfAdderB;
    private Or or;
    private Input powerIn = new Input();
    private Input inA = new Input();
    private Input inB = new Input();
    private Input inCarry = new Input();
    private Output outSum = new Output();
    private Output outCarry = new Output();

    /**
     * The constructor.
     *
     * @param parent The circuit where this component takes place
     * @param name A unique name for this component
     */
    public FullAdder(Circuit parent, String name) {
        super(parent, name);
        Circuit local = getLocalCircuit();
        halfAdderA = new HalfAdder(local, name + "_HAA");
        halfAdderB = new HalfAdder(local, name + "_HAB");
        or = new Or(local, name + "_OR", 2);

        new Signal(local).from(powerIn).to(halfAdderA.getPowerIn(), halfAdderB.getPowerIn(), or.getPowerIn());

        new Signal(local).from(inCarry).to(halfAdderB.getInA());
        new Signal(local).from(halfAdderA.getOutSum()).to(halfAdderB.getInB());

        new Signal(local).from(inA).to(halfAdderA.getInA());
        new Signal(local).from(inB).to(halfAdderA.getInB());
        new Signal(local).from(halfAdderB.getOutSum()).to(outSum);
        new Signal(local).from(halfAdderB.getOutCarry()).to(or.getIn(0));
        new Signal(local).from(halfAdderA.getOutCarry()).to(or.getIn(1));
        new Signal(local).from(or.getOut()).to(outCarry);
    }
}

/*
                                          +-------------+
inCa+------------------------------------>+ A          S+------------------------> outSum
                                          | Half Adder  |
                                          |      B      |
           +-------------+        +------>+ B         CO+------+      _______
inA +----> | A          S| +------+       +-------------+      +----->\      \
           | Half Adder  |                                             | OR   >--> outCarry
           |      A      |                             +------------->/______/
inB +----> | B         CO| +---------------------------+
           +-------------+

 */
