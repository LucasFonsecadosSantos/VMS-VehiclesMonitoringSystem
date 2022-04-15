package simulacao.entity.actor;

import simulacao.Mapa;

public interface SimulatorActor {

    public abstract void executeStep(Mapa map);

}