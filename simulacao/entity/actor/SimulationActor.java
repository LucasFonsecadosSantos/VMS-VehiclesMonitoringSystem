package simulacao.entity.actor;

import javax.swing.ImageIcon;
import java.awt.Image;

import simulacao.Location;
import simulacao.SimulationMap;

public abstract class SimulationActor {

    private Location currentLocation;

    private Location nextLocation;

    protected Image image;

    public SimulationActor(Location location, String iconPath) {

        setCurrentLocation(location);
        setNextLocation(null);
        this.image = new ImageIcon(getClass().getResource(iconPath)).getImage();
        
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public Location getNextLocation() {
        return nextLocation;
    }
    
    public Image getImage(){
        return image;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setNextLocation(Location nextLocation) {
        this.nextLocation = nextLocation;
    }

    public abstract void executeStep(SimulationMap map);

}