package simulacao.entity.actor;

import simulacao.Location;
import simulacao.SimulationMap;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public abstract class VehicleActor extends SimulationActor {

    public VehicleActor(Location location, String iconPath) {
        
        super(location,iconPath);
        
    }

    protected void updateLocation() {
        Location nextLocation = getCurrentLocation().calculateNextLocation(getNextLocation());
        setCurrentLocation(nextLocation);
    }
    
    @Override
    public abstract void executeStep(SimulationMap map);

}
