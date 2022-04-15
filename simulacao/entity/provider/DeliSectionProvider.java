package simulacao.entity.provider;

public class DeliSectionProvider extends Provider {

    public DeliSectionProvider() {

        super(new Product(1));

    }

    @Override
    public String toString() {

        return "Deli Section Provider. Product: " + super.toString();

    }

}