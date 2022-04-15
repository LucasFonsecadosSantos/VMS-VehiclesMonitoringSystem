package simulacao.entity.provider;

import simulacao.entity.hindrance.Product;
import simulacao.Localizacao;

public class DrinkProvider extends Provider {

    private static final String _ICON_PATH_ = "./../../Imagens/DrinkProvider.jpeg";

    public DrinkProvider(Localizacao location) {

        super(new Product(3), location, DrinkProvider._ICON_PATH_);

    }

    @Override
    public String toString() {

        return "Drink Provider. Product: " + super.toString();

    }

}