package simulacao.entity.actor;

import simulacao.Localizacao;

public class Bike extends Vehicle {

    public Bike(Localizacao location) {
        super(location);
    }

    @Override
    public void executeStep(){

        Localizacao destino = getLocalizacaoDestino();

        if(destino != null) {

            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(getLocalizacaoDestino());
            setLocalizacaoAtual(proximaLocalizacao);

        }

    }

}