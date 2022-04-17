package simulacao.entity.actor;

import simulacao.Location;
import simulacao.entity.hindrance.Product;
import simulacao.entity.provider.Provider;
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

        this.productList.add(product);
        System.out.println("SIZE " + this.productList.size());

    }

    @Override
    public void executeStep(SimulationMap map){

        Location currentLocation = getCurrentLocation();
        Location nextLocation = getNextLocation();
        Provider provider = null;

        if(nextLocation != null) {
            
            if (map.isAllowToContinue(currentLocation)) {
                
                if (map.isNotNextLocationOccupied(nextLocation)) {

                    provider = map.getProviderAtCoordinates(nextLocation.getX(), nextLocation.getY());
                    System.out.print("ZE ");
                    if (provider != null) {
                        System.out.print(" 0000 ");
                        this.retriveProduct(provider.getProduct());
                    }
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