package simulacao.entity.actor;

import simulacao.Location;
import simulacao.SimulationMap;
import simulacao.util.Randomizer;

public class TrafficLightActor extends SimulationActor {

    private String state;

    public static final String _RED_ = "red";

    public static final String _GREEN_ = "green";

    private static final String _ICON_PATH_RED_STATE_ = "./../../Imagens/redSign.jpeg";

    private static final String _ICON_PATH_GREEN_STATE_ = "./../../Imagens/greenSign.jpeg";

    public TrafficLightActor(Location location) {

        super(location, TrafficLightActor._ICON_PATH_GREEN_STATE_);
        setState();
        System.out.println("SEMAFORO: " + location.toString());
        
    }

    @Override
    public void executeStep(SimulationMap map) {

        changeState();

    }

    public void changeState() {

        this.state = getState() == TrafficLightActor._RED_ ? TrafficLightActor._GREEN_ : TrafficLightActor._RED_;

    }

    public void setState() {
        this.state = Randomizer.getRandomInteger(1,2) == 1 ? TrafficLightActor._RED_ : TrafficLightActor._GREEN_;
    }

    public void setState(String state) {

        this.state = state;

    }

    public String getState() {

        return this.state;

    }

}