package simulation.entity.mapfacilities;

import java.awt.Image;
import javax.swing.ImageIcon;


import simulation.Location;

public abstract class MapFacility {
    
    private Location location;

    private Image image;

    public MapFacility(Location location, String iconPath) {

        setImage(iconPath);
        setLocation(location);

    }

    public Location getLocation() {
        return this.location;
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

    @Override
    public String toString() {

        return "MapFacility Location: " + this.location.toString();

    }

}
