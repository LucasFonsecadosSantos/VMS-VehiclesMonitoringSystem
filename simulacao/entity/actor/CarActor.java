package simulacao.entity.actor;

import simulacao.Location;
import simulacao.entity.hindrance.Product;
import simulacao.SimulationMap;

import java.util.List;
import java.util.ArrayList;

public class CarActor extends VehicleActor {

    private List<Product> productList;

    private int gasLevel;

    private static final String _ICON_PATH_ = "./../../Imagens/Car.jpg";

    private static final int _MAX_GAS_LEVEL_ = 30;

    public CarActor(Location location) {

        super(location, CarActor._ICON_PATH_);
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

        this.productList.add(product);

    }

    @Override
    public void executeStep(SimulationMap map){

        Location currentLocation = getCurrentLocation();
        Location nextLocation = getNextLocation();
        
        if(nextLocation != null) {
            
            if (map.isAllowToContinue(currentLocation)) {
                
                if (map.isNotNextLocationOccupied(nextLocation)) {
                        
                    super.updateLocation();
                    //updateGasLevel();

                }
            }

        }

    }

    private void setGasLevel(int level) {
        this.gasLevel = level;
    }

    // private void updateGasLevel() {
    //     if (!map.hasGasStationAtCurrentLocation(getCurrentLocation())) {
    //         decreaseGasLevel();
    //     } else {
    //         increaseGasLevel();
    //     }
    // }

}