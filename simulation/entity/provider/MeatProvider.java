package simulation.entity.provider;

import simulation.Location;
import simulation.entity.hindrance.Product;

public class MeatProvider extends Provider {

    private static final String _ICON_PATH_ = "./../../Imagens/MeatProvider.jpeg";

    private static final int _WEIGHT_OF_THE_SUPPLIED_PRODUCT_ = 4;

    public MeatProvider(Location location) {

        super(
            new Product(MeatProvider._WEIGHT_OF_THE_SUPPLIED_PRODUCT_), 
            location, 
            MeatProvider._ICON_PATH_
        );

    }

    @Override
    public String toString() {

        return "Meat Provider. Product: " + super.toString();

    }

}