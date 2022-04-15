package simulacao.entity.provider;

import simulacao.entity.hindrance.Product;

public abstract class Provider {

    private Product product;

    public Provider(Product product) {

        setProduct(product);

    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {

        try {

            if (product == null) {
                throw new Exception("Product doesn't exist.");
            } else {
                this.product = product;
            }

        } catch(Exception exception) {
            System.out.println(exception.getMessage());
        }

    }

    @Override
    public String toString() {

        return this.product.toString();

    }
    
}