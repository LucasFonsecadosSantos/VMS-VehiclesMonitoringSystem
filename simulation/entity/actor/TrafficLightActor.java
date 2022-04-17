package simulation.entity.actor;

import javax.swing.ImageIcon;

import simulation.Location;
import simulation.application.SimulationMap;
import simulation.util.Randomizer;

import java.awt.Image;

public class TrafficLightActor extends SimulationActor {

    private String state;

    public static final String _RED_ = "red";

    public static final String _GREEN_ = "green";

    private static final String _ICON_PATH_RED_STATE_ = "./../../Imagens/redSign.jpeg";

    private static final String _ICON_PATH_GREEN_STATE_ = "./../../Imagens/greenSign.jpeg";

    private static final int _CHANGE_STATE_FACTOR_ = 4;

    public TrafficLightActor(Location location) {

        super(location, TrafficLightActor._ICON_PATH_GREEN_STATE_);
        setState();
        
    }

    @Override
    public void executeStep(SimulationMap map, int step) {

        if (step % TrafficLightActor._CHANGE_STATE_FACTOR_ == 0) {
            changeState();
        }

    }

    public void changeState() {

        setState(getState().equals(TrafficLightActor._RED_) ? TrafficLightActor._GREEN_ : TrafficLightActor._RED_);
        this.image = new ImageIcon(getClass().getResource(getState().equals(TrafficLightActor._RED_) ? TrafficLightActor._ICON_PATH_GREEN_STATE_ : TrafficLightActor._ICON_PATH_RED_STATE_)).getImage();

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