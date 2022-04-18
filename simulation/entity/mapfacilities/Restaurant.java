package simulation.entity.mapfacilities;

import simulation.Location;

public class Restaurant extends MapFacility {

    private static final String _ICON_PATH_ = "./../../Imagens/Pizza.jpeg";

    public Restaurant(Location location) {

        super(location, Restaurant._ICON_PATH_);

    }

    @Override
    public String toString() {
        return super.toString() + " -> Restaurant";
    }
    
}
