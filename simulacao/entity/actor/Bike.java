package simulacao.entity.actor;

import simulacao.Mapa;
import simulacao.Localizacao;

public class Bike extends Vehicle {

    public Bike(Localizacao location) {
        super(location);
    }

    @Override
    public void executeStep(Mapa map){

        Localizacao destino = getLocalizacaoDestino();

        if(destino != null) {

            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(getLocalizacaoDestino());
            setLocalizacaoAtual(proximaLocalizacao);

        }

    }

}