package simulation.entity.provider;

import java.awt.Image;
import javax.swing.ImageIcon;

import simulation.Location;
import simulation.entity.hindrance.Product;

public abstract class Provider {

    private Product product;
    
    private Location location;

    private Image image;

    public Provider(Product product, Location location, String iconPath) {

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

    public Location getLocation() {
        return this.location;
    }

    @Override
    public String toString() {

        return this.product.toString();

    }

    private void setLocation(Location location) {
        this.location = location;
    }

    private void setImage(String iconPath) {

        this.image = new ImageIcon(getClass().getResource(iconPath)).getImage();

    }

    public Image getImage(){
        return image;
    }
    
}