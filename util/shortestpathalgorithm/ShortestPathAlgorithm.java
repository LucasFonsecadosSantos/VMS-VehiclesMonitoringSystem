package simulation.util.shortestpathalgorithm;

import java.util.List;
import simulation.Location;

/**
 * Classe para calcular a menor distância entre pontos da lista por onde o veículo deve passar
 */
public class ShortestPathAlgorithm {
    
    private ShortestPathAlgorithmStrategy strategy; // declarando a variável que armazena a estratégia de menor caminho

    /**
     * Construtor contendo a estratégia recebida
     * @param strategy - estratégia recebida por parâmetro para utilização no cálculo de menor caminho
     */
    public ShortestPathAlgorithm(ShortestPathAlgorithmStrategy strategy) {
        setStrategy(strategy);
    }

    /**
     * Método para definir a estratégia de menor caminho a partir do parâmetro recebido
     * @param strategy - parâmetro recebido contendo a estratégia a ser utilizada
     */
    private void setStrategy(ShortestPathAlgorithmStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Método para calcular o menor caminho entre os pontos da lista pela qual o veículo deverá realizar seu caminho
     * @param currentLocation - localização atual do veículo
     * @param locationList - lista com as localizações pela qual o veículo deverá passar
     * @return retorna lista contendo o menor caminho a ser seguido
     */
    public Location calculateShortestPath(Location currentLocation, List<Location> locationList) {
        return this.strategy.calculateShortestPath(currentLocation, locationList);
    }

}
