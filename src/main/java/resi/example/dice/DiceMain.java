package resi.example.dice;

import resi.common.Circuit;
import resi.common.Power;
import resi.common.Signal;
import resi.common.Simulator;

/**
 * Simulates a dice built from relays.
 * 
 * @author Peter H&auml;nsgen
 */
public class DiceMain
{
    public static void main(String[] args)
    {
        Circuit circuit = new Circuit();

        Power vcc = new Power(circuit, "VCC");
        Dice dice = new Dice(circuit, "Dice");

        new Signal(circuit).from(vcc.getOut()).to(dice.getPowerIn());

        Simulator sim = new Simulator(circuit, 1);
        sim.start();

        try
        {
            Thread.sleep((long) (Math.random() * 500) + 500L);
        }
        catch (InterruptedException e)
        {
        }

        sim.stop();
    }
}
