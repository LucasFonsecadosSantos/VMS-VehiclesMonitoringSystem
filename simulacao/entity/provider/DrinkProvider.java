package simulacao.entity.provider;

public class DrinkProvider extends Provider {

    public DrinkProvider() {

        super(new Product(3));

    }

    @Override
    public String toString() {

        return "Drink Provider. Product: " + super.toString();

    }

}