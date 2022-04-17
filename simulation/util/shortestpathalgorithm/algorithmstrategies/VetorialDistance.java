package simulation.util.shortestpathalgorithm.algorithmstrategies;

import java.util.List;

import simulation.Location;
import simulation.util.shortestpathalgorithm.ShortestPathAlgorithmStrategy;

public class VetorialDistance implements ShortestPathAlgorithmStrategy {
    
    public VetorialDistance() {}

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
            System.out.println("" + xAxisDistance + ", " + yAxisDistance + " -> Candidate: " + candidateWeight + " -> Lowest: " + lowestWeight);
            if (candidateWeight <= lowestWeight) {
                lowestWeight = candidateWeight;
                nextXCoordinate = candidateLocation.getX();
                nextYCoordinate = candidateLocation.getY();
            }
            System.out.println("------------------------------");
        }
        System.out.println("" + nextXCoordinate + ", " + nextYCoordinate);
        return new Location(nextXCoordinate, nextYCoordinate);

    }

}
