package simulacao.entity.provider;

import simulacao.entity.hindrance.Product;
import simulacao.Localizacao;

public class DoughProvider extends Provider {

    private static final String _ICON_PATH_ = "./../../Imagens/DoughProvider.jpeg";

    public DoughProvider(Localizacao location) {

        super(new Product(2), location, DoughProvider._ICON_PATH_);

    }

    @Override
    public String toString() {

        return "Dough Provider. Product: " + super.toString();

    }

}