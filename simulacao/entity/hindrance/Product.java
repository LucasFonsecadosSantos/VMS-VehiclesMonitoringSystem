package simulacao.entity.hindrance;

public class Product extends Hindrance {

    private int weight;

    public Product(int weight) {
        super();
        setWeight(weight);
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    private void setWeight(int weight) {
        this.weight = weight;
    }

}