package simulacao.entity.provider;

import simulacao.entity.hindrance.Product;
import simulacao.Localizacao;

import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class Provider {

    private Product product;
    
    private Localizacao location;

    private Image image;

    public Provider(Product product, Localizacao location, String iconPath) {

        setImage(iconPath);
        setLocation(location);
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

    public Localizacao getLocation() {
        return this.location;
    }

    @Override
    public String toString() {

        return this.product.toString();

    }

    private void setLocation(Localizacao location) {
        this.location = location;
    }

    private void setImage(String iconPath) {

        this.image = new ImageIcon(getClass().getResource(iconPath)).getImage();

    }
    
}