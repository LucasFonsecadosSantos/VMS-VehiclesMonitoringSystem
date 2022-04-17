package simulation.entity.actor;

import java.util.List;

import simulation.Location;
import simulation.application.SimulationMap;
import simulation.entity.hindrance.Product;
import simulation.entity.provider.Provider;

import java.util.ArrayList;

public class CarActor extends VehicleActor {

    private List<Product> productList;

    private int gasLevel;

    private static final String _ICON_PATH_ = "./../../Imagens/Car.jpg";

    private static final int _MAX_GAS_LEVEL_ = 30;

    public CarActor(Location location) {

        super(location, CarActor._ICON_PATH_);
        this.productList = new ArrayList<>();
        setGasLevel(CarActor._MAX_GAS_LEVEL_);

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

    @Override
    public void executeStep(SimulationMap map, int step){

        Location currentLocation = getCurrentLocation();
        Location nextLocation = getNextLocation();
        Provider provider = null;

        if(isValidStep(nextLocation, step)) {
            
            if (map.isAllowToContinue(currentLocation)) {
                
                if (map.isNotNextLocationOccupied(nextLocation)) {

                    provider = map.getProviderAtCoordinates(nextLocation.getX(), nextLocation.getY());
                    
                    if (provider != null) {
                        System.out.println("Pegou " + provider.getProduct());
                        this.retriveProduct(provider.getProduct());
                    }
                    super.updateLocation();
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