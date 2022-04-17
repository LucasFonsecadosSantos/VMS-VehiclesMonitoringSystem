package simulation.util.shortestpathalgorithm;

import java.util.List;

import simulation.Location;

public interface ShortestPathAlgorithmStrategy {
    
    public abstract Location calculateShortestPath(Location currentLocation, List<Location> locationList);

}
