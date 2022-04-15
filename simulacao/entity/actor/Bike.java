package simulacao.entity.actor;

import simulacao.Mapa;
import simulacao.Localizacao;

public class Bike extends Vehicle {

    private static final String _ICON_PATH_ = "./../../Imagens/Bike.jpeg";

    public Bike(Localizacao location) {
        super(location, Bike._ICON_PATH_);
    }

    @Override
    public void executeStep(Mapa map){

        Localizacao destino = getNextLocation();

        if(destino != null) {

            updateLocation();

        }

    }

    private void updateLocation() {
        Localizacao nextLocation = getCurrentLocation().calculateNextLocation(getNextLocation());
        setLocalizacaoAtual(nextLocation);
        System.out.println("BIKE: " + getCurrentLocation().toString());
    }

}