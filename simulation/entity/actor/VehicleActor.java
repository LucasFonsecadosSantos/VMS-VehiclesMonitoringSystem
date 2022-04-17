package simulation.entity.actor;

import java.awt.Image;
import javax.swing.ImageIcon;

import simulation.Location;
import simulation.application.SimulationMap;

/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public abstract class VehicleActor extends SimulationActor {

    public VehicleActor(Location location, String iconPath) {
        
        super(location,iconPath);
        
    }

    protected void updateLocation(Location nextLocationFromEntity) {
        Location nextLocation = getCurrentLocation().calculateNextLocation(nextLocationFromEntity);
        setCurrentLocation(nextLocation);
    }
    
    @Override
    public abstract void executeStep(SimulationMap map, int step);

}
