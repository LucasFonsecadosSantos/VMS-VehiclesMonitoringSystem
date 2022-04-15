package simulacao.entity.provider;

public class DoughProvider extends Provider {

    public DoughProvider() {

        super(new Product(2));

    }

    @Override
    public String toString() {

        return "Dough Provider. Product: " + super.toString();

    }

}