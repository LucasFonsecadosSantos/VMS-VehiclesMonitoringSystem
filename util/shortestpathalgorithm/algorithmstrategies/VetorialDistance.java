package simulation.util.shortestpathalgorithm.algorithmstrategies;

import java.util.List;

import simulation.Location;
import simulation.util.shortestpathalgorithm.ShortestPathAlgorithmStrategy;

/**
 * Classe contendo o cálculo da distância vetorial para definição do menor caminho
 */
public class VetorialDistance implements ShortestPathAlgorithmStrategy {
    
    public VetorialDistance() {}
    
    /**
     * Método para cálculo da distância vetorial para encontrar o menor caminho a ser realizado entre os pontos do mapa
     * @param currentLocation - localização atual
     * @param locationList - lista contendo as localizações na qual um veículo deve passar
     * @return retorna uma localização que consiste no local de menor caminho a partir do ponto atual
     */
    @Override
    public Location calculateShortestPath(Location currentLocation, List<Location> locationList) {

        double lowestWeight = Integer.MAX_VALUE;
        double candidateWeight = 0;
        int nextXCoordinate = currentLocation.getX();
        int nextYCoordinate = currentLocation.getY();
        double xAxisDistance;
        double yAxisDistance;

        for (Location candidateLocation : locationList) {

            xAxisDistance = Math.pow((currentLocation.getX() - candidateLocation.getX()), 2);
            yAxisDistance = Math.pow((currentLocation.getY() - candidateLocation.getY()), 2);

            candidateWeight = Math.sqrt(xAxisDistance + yAxisDistance);
            if (candidateWeight <= lowestWeight) {
                lowestWeight = candidateWeight;
                nextXCoordinate = candidateLocation.getX();
                nextYCoordinate = candidateLocation.getY();
            }
        }
        return new Location(nextXCoordinate, nextYCoordinate);

    }

}
