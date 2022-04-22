package simulation.entity.actor;

import simulation.Location;
import simulation.application.SimulationMap;

/**
 * Subclasse de veículo para definir os elementos do tipo BikeActor, que andam pelo mapa e servem como "obstáculos" para o veículo principal
*/

public class BikeActor extends VehicleActor {

    private static final String _ICON_PATH_ = "./../../Imagens/Bike.jpeg"; // caminho para obtenção da imagem a ser utilizada pela simulação
    
    /**
     * Construtor da classe BikeActor para instanciar objetos.
     * @param location - Recebe como parâmetro uma localização na qual a bicicleta deverá estar.
     * Envia para a superclasse a localização e a imagem a ser utilizada.
     */
    public BikeActor(Location location) {
        super(location, BikeActor._ICON_PATH_);
    }

    /**
     * Método que sobrescreve a localização atual e a próxima localização na qual um veículo do tipo BikeActor deve ir 
     * @param map recebe como parâmetro o mapa da simulação
     * @param step recebe a quantidade de passos a ser dada
     */
    @Override
    public void executeStep(SimulationMap map, int step){

        Location destino = getNextLocation();

        if(destino != null) {

            super.updateLocation(getNextLocation());

        }

    }

}