package simulacao.entity.hindrance;

import java.util.Random;

public class TrafficLightHindrance implements Hindrance {

    private String state;

    private static final String _RED_ = "red";

    private static final String _GREEN_ = "green";

    public TrafficLightHindrance() {

        setState();

    }

    public void setState() {

        Random rand = new Random();
        this.state = rand.nextInt((2 - 1) + 1) == 1 ? TrafficLightHindrance._RED_ : TrafficLightHindrance._GREEN_;

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