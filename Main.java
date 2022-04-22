package simulation;

import simulation.application.Simulation;

/**
 * Classe Principal (main) utilizada para execução do programa. 
 * O método main cria um novo objeto do tipo "Simulation" e o executa passando por parâmetro a quantidade de passos que o veículo pode andar
 * @author Bruna Capeleti, Joicy Reis e Lucas Fonseca
*/
public class Main {

    public static void main(String[] args) {

        Simulation sim = new Simulation();
        sim.executeSimulation(10000);

    }
}
