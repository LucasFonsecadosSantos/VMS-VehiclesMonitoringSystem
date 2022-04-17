package simulacao.entity.actor;

import simulacao.SimulationMap;
import simulacao.Location;

public class BikeActor extends VehicleActor {

    private static final String _ICON_PATH_ = "./../../Imagens/Bike.jpeg";

    public BikeActor(Location location) {
        super(location, BikeActor._ICON_PATH_);
    }

    @Override
    public void executeStep(SimulationMap map){

        Location destino = getNextLocation();

        if(destino != null) {

            super.updateLocation();

        }

    }

}