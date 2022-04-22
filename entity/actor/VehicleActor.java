package simulation.entity.actor;

import java.awt.Image;
import javax.swing.ImageIcon;

import simulation.Location;
import simulation.application.SimulationMap;

/**
 * Classe para representar os veículos utilizados na simulação
 */
public abstract class VehicleActor extends SimulationActor {

    /**
     * Construtor da classe VehicleActor para instanciar a localização e o caminho da imagem
     * @param location - parâmetro responsável por trazer a localização do veículo em questão
     * @param iconPath - parâmetro responsável por trazer o caminho da imagem que será utilizada
     */
    public VehicleActor(Location location, String iconPath) {
        
        super(location,iconPath);
        
    }

    /**
     * Método para atualizar a próxima localização a ser visitada pelo veículo
     * @param nextLocationFromEntity - recebe a próxima localização a ser visitada pelo veículo, atualizando a variável nextLocation
     */
    protected void updateLocation(Location nextLocationFromEntity) {
        Location nextLocation = getCurrentLocation().calculateNextLocation(nextLocationFromEntity);
        setCurrentLocation(nextLocation);
    }
    
    /**
     * Método abstrato para executar os passos a serem realizados pelo veículo durante a simulação
     * @param map - parâmetro responsável por trazer o mapa do jogo
     * @param step - parâmetro responsável por trazer a quantidade de passos a serem realizados
     */
    @Override
    public abstract void executeStep(SimulationMap map, int step);

}
