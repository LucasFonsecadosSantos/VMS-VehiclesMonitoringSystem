package simulation;

import simulation.application.Simulation;

/**
 *
 * @author Luiz Merschmann
 */
public class Main {

    public static void main(String[] args) {

        Simulation sim = new Simulation();
        sim.executeSimulation(10000);

    }
}
