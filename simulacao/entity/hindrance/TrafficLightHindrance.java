package simulacao.entity.hindrance;

import simulacao.Localizacao;
import simulacao.util.Randomizer;

public class TrafficLightHindrance extends Hindrance {

    private String state;

    public static final String _RED_ = "red";

    public static final String _GREEN_ = "green";

    public TrafficLightHindrance(Localizacao location) {

        super(location);
        System.out.println("SEMAFORO: " + location.toString());
        setState();

    }

    public void setState() {

        this.state = Randomizer.getRandomInteger(1,2) == 1 ? TrafficLightHindrance._RED_ : TrafficLightHindrance._GREEN_;

    }

    public void changeState() {

        this.state = getState() == TrafficLightHindrance._RED_ ? TrafficLightHindrance._GREEN_ : TrafficLightHindrance._RED_;

    }

    public void setState(String state) {

        this.state = state;

    }

    public String getState() {

        return this.state;

    }

    @Override
    public int getWeight() {
        return 1;
    }

}