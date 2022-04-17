package simulation.entity.provider;

import simulation.Location;
import simulation.entity.hindrance.Product;

public class DeliSectionProvider extends Provider {

    private static final String _ICON_PATH_ = "./../../Imagens/DeliSectionProvider.jpeg";

    private static final int _WEIGHT_OF_THE_SUPPLIED_PRODUCT_ = 1;

    public DeliSectionProvider(Location location) {

        super(
            new Product(DeliSectionProvider._WEIGHT_OF_THE_SUPPLIED_PRODUCT_), 
            location, 
            DeliSectionProvider._ICON_PATH_
        );

    }

    @Override
    public String toString() {

        return "Deli Section Provider. Product: " + super.toString();

    }

}