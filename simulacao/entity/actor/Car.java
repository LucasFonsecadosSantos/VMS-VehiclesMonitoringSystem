package simulacao.entity.actor;

import simulacao.Localizacao;
import simulacao.entity.hindrance.Product;
import simulacao.Mapa;

import java.util.List;
import java.util.ArrayList;

public class Car extends Vehicle {

    private List<Product> productList;

    private int gasLevel;

    private static final String _ICON_PATH_ = "./../../Imagens/Car.jpg";

    private static final int _MAX_GAS_LEVEL_ = 30;

    public Car(Localizacao location) {

        super(location, Car._ICON_PATH_);
        setGasLevel(Car._MAX_GAS_LEVEL_);

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
    public void executeStep(Mapa map){

        Localizacao currentLocation = getCurrentLocation();
        Localizacao nextLocation = getNextLocation();
        System.out.println("CAIU");
        if(nextLocation != null) {
            
            if (map.isAllowToContinue(currentLocation)) {

                if (map.isNotNextLocationOccupied(nextLocation)) {
                    
                    updateLocation();
                    //updateGasLevel();

                }
            }

        }

    }

    private void setGasLevel(int level) {
        this.gasLevel = level;
    }

    private void updateLocation() {
        Localizacao nextLocation = getCurrentLocation().calculateNextLocation(getNextLocation());
        setLocalizacaoAtual(nextLocation);
        System.out.println("CARRO: " + this.getCurrentLocation().toString());
    }

    // private void updateGasLevel() {
    //     if (!map.hasGasStationAtCurrentLocation(getCurrentLocation())) {
    //         decreaseGasLevel();
    //     } else {
    //         increaseGasLevel();
    //     }
    // }

}