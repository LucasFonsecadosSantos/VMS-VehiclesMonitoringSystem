package simulation.entity.actor;

import simulation.Location;
import simulation.application.SimulationMap;

public class BikeActor extends VehicleActor {

    private static final String _ICON_PATH_ = "./../../Imagens/Bike.jpeg";

    public BikeActor(Location location) {
        super(location, BikeActor._ICON_PATH_);
    }

    @Override
    public void executeStep(SimulationMap map, int step){

        Location destino = getNextLocation();

        if(destino != null) {

            super.updateLocation(getNextLocation());

        }

    }

}