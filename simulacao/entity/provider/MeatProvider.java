package simulacao.entity.provider;

public class MeatProvider extends Provider {

    public MeatProvider() {

        super(new Product(5));

    }

    @Override
    public String toString() {

        return "Meat Provider. Product: " + super.toString();

    }

}