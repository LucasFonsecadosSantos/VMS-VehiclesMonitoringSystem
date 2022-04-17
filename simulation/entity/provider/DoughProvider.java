package simulation.entity.provider;

import simulation.Location;
import simulation.entity.hindrance.Product;

public class DoughProvider extends Provider {

    private static final String _ICON_PATH_ = "./../../Imagens/DoughProvider.jpeg";

    private static final int _WEIGHT_OF_THE_SUPPLIED_PRODUCT_ = 2;

    public DoughProvider(Location location) {

        super(
            new Product(DoughProvider._WEIGHT_OF_THE_SUPPLIED_PRODUCT_), 
            location, 
            DoughProvider._ICON_PATH_
        );

    }

    @Override
    public String toString() {

        return "Dough Provider. Product: " + super.toString();

    }

}