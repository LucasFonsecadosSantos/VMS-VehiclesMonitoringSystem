package simulation.util.shortestpathalgorithm;

import java.util.List;

import simulation.Location;

public class ShortestPathAlgorithm {
    
    private ShortestPathAlgorithmStrategy strategy;

    public ShortestPathAlgorithm(ShortestPathAlgorithmStrategy strategy) {
        setStrategy(strategy);
    }

    private void setStrategy(ShortestPathAlgorithmStrategy strategy) {
        this.strategy = strategy;
    }

    public Location calculateShortestPath(Location currentLocation, List<Location> locationList) {
        return this.strategy.calculateShortestPath(currentLocation, locationList);
    }

}
