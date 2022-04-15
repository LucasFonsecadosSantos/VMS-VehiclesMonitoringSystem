package simulacao.entity.provider;

import simulacao.entity.hindrance.Product;
import simulacao.Localizacao;

public class MeatProvider extends Provider {

    private static final String _ICON_PATH_ = "./../../Imagens/MeatProvider.jpeg";

    public MeatProvider(Localizacao location) {

        super(new Product(5), location, MeatProvider._ICON_PATH_);

    }

    @Override
    public String toString() {

        return "Meat Provider. Product: " + super.toString();

    }

}