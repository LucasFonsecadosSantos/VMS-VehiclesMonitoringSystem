package simulation.entity.mapfacilities;

import simulation.Location;
import simulation.util.Randomizer;

public class GasStation extends MapFacility {

    private static final String _ICON_PATH_ = "./../../Imagens/gasStation.jpeg";

    private static final int _LOWER_LIMIT_GAS_LEVEL_ = 0;

    private static final int _UPPER_LIMIT_GAS_LEVEL_ = 0;

    private int gasLevel;

    public GasStation(Location location) {

        super(location, GasStation._ICON_PATH_);
        initGasLevel();

    }

    private void initGasLevel() {

        this.gasLevel = Randomizer.getRandomInteger(GasStation._LOWER_LIMIT_GAS_LEVEL_, GasStation._UPPER_LIMIT_GAS_LEVEL_);

    }

    public int refuelTheVehicle() {

        int gasAmount = this.gasLevel;
        this.gasLevel = 0;

        return gasAmount;

    }

    @Override
    public String toString() {

        return super.toString() + "\nGas Level: " + this.gasLevel + " -> Gas Station";

    }

}
