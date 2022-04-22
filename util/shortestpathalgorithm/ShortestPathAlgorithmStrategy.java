package simulation.util.shortestpathalgorithm;

import java.util.List;
import simulation.Location;

/**
 * Interface contendo o método para cálculo do menor caminho com base na estratégia recebida
 */
public interface ShortestPathAlgorithmStrategy {
    
    public abstract Location calculateShortestPath(Location currentLocation, List<Location> locationList);

}
