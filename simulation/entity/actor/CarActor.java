package simulation.entity.actor;

import java.util.List;

import simulation.Location;
import simulation.application.SimulationMap;
import simulation.entity.hindrance.Product;
import simulation.entity.provider.Provider;
import simulation.util.shortestpathalgorithm.ShortestPathAlgorithm;
import simulation.util.shortestpathalgorithm.algorithmstrategies.VetorialDistance;

import java.util.ArrayList;

public class CarActor extends VehicleActor {

    private List<Product> productList;

    private List<Location> destinationLocationList;

    private int gasLevel;

    private ShortestPathAlgorithm shortestPathAlgorithm;

    private static final String _ICON_PATH_ = "./../../Imagens/Car.jpg";

    private static final int _MAX_GAS_LEVEL_ = 30;

    public CarActor(Location location) {

        super(location, CarActor._ICON_PATH_);
        initAttributeInstances();
        setGasLevel(CarActor._MAX_GAS_LEVEL_);

    }

    private void initAttributeInstances() {
        this.shortestPathAlgorithm = new ShortestPathAlgorithm(new VetorialDistance());
        this.destinationLocationList = new ArrayList<>();
        this.productList = new ArrayList<>();
    }

    public void setDestinationLocationList(List<Location> locations) {
        this.destinationLocationList = locations;
    }

    public void increaseGasLevel() {
        this.gasLevel++;
    }

    public void decreaseGasLevel() {
        this.gasLevel--;
    }

    public int getGasLevel() {
        return this.gasLevel;
    }

    public void retriveProduct(Product product) {

        if (!this.productList.contains(product)) {
            this.productList.add(product);
            System.out.println("SIZE " + this.productList.size());
        }

    }

    public Location getNextLocation() {
        
        Location nextLocation = this.shortestPathAlgorithm.calculateShortestPath(getCurrentLocation(), this.destinationLocationList);

        for (Location location : this.destinationLocationList) {
            if (location.equals(nextLocation)) {
                if (nextLocation.equals(getCurrentLocation())) {
                    this.destinationLocationList.remove(nextLocation);
                }
                return nextLocation;
            }
        }
        return nextLocation;
    }

    @Override
    public void executeStep(SimulationMap map, int step){

        Location currentLocation = getCurrentLocation();
        Location nextLocation = getNextLocation();
        Provider provider = null;

        if(isValidStep(nextLocation, step)) {
            
            if (map.isAllowToContinue(currentLocation)) {
                
                if (map.isNotNextLocationOccupied(nextLocation)) {

                    provider = map.getProviderAtCoordinates(currentLocation.getX(), currentLocation.getY());
                    
                    if (provider != null) {
                        System.out.println("Pegou " + provider.getProduct());
                        this.retriveProduct(provider.getProduct());
                    }
                    super.updateLocation(this.getNextLocation());
                    //updateGasLevel();

                }
            }

        }

    }

    private boolean isValidStep(Location nextLocation, int step) {
        return ((nextLocation != null && getWeight() == 0) ||
        (nextLocation != null && step % getWeight() == 0));
    }

    private void setGasLevel(int level) {
        this.gasLevel = level;
    }

    private int getWeight() {
        
        int weight = 0;

        for (Product product : this.productList) {
            weight += product.getWeight();
        }

        return weight;

    }

    // private void updateGasLevel() {
    //     if (!map.hasGasStationAtCurrentLocation(getCurrentLocation())) {
    //         decreaseGasLevel();
    //     } else {
    //         increaseGasLevel();
    //     }
    // }

}