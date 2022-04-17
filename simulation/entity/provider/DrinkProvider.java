package simulation.entity.provider;

import simulation.Location;
import simulation.entity.hindrance.Product;

public class DrinkProvider extends Provider {

    private static final String _ICON_PATH_ = "./../../Imagens/DrinkProvider.jpeg";

    private static final int _WEIGHT_OF_THE_SUPPLIED_PRODUCT_ = 2;

    public DrinkProvider(Location location) {

        super(
            new Product(DrinkProvider._WEIGHT_OF_THE_SUPPLIED_PRODUCT_), 
            location, 
            DrinkProvider._ICON_PATH_
        );

    }

    @Override
    public String toString() {

        return "Drink Provider. Product: " + super.toString();

    }

}