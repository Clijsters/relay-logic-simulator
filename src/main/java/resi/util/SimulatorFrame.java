package resi.util;

import resi.common.Simulator;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * A common super class for windows that show simulation results.
 *
 * @author Peter H&auml;nsgen
 */
public class SimulatorFrame extends Frame
{
    private static final long serialVersionUID = 1L;

    /**
     * The constructor.
     */
    public SimulatorFrame(Simulator simulator)
    {
        // handle window events
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                simulator.stop();
                System.exit(0);
            }

            @Override
            public void windowIconified(WindowEvent e)
            {
                simulator.stop();
            }

            @Override
            public void windowDeiconified(WindowEvent e)
            {
                simulator.start();
            }
        });
    }
}
