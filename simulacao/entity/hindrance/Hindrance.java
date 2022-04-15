package simulacao.entity.hindrance;

import simulacao.Localizacao;

public abstract class Hindrance {

    private Localizacao location;

    public Hindrance(Localizacao location) {
        setLocation(location);
    }

    public Hindrance() {
        setLocation(null);
    }

    public abstract int getWeight();

    private void setLocation(Localizacao location) {
        this.location = location;
    }

    public Localizacao getLocation() {
        return location;
    }

}