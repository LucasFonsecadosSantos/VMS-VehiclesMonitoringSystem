package simulacao.entity.provider;

import simulacao.entity.hindrance.Product;
import simulacao.Location;

public class DeliSectionProvider extends Provider {

    private static final String _ICON_PATH_ = "./../../Imagens/DeliSectionProvider.jpeg";

    public DeliSectionProvider(Location location) {

        super(new Product(1), location, DeliSectionProvider._ICON_PATH_);

    }

    @Override
    public String toString() {

        return "Deli Section Provider. Product: " + super.toString();

    }

}