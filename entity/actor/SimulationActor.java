package simulation.entity.actor;

import javax.swing.ImageIcon;

import simulation.Location;
import simulation.application.SimulationMap;

import java.awt.Image;

/**
 * Superclasse de todos os atores do jogo, utilizada para setar localizações e a imagem do elemento.
 */

public abstract class SimulationActor {

    private Location currentLocation; //variável para armazenar a localização atual

    private Location nextLocation; //variável para armazenar a próxima localização

    protected Image image; // variável para armazenar a imagem que representará o ator

    /**
     * Construtor para instanciar a localização atual, a próxima localização e a imagem a ser adicionada
     * @param location - recebe a localização que será dada como localização atual
     * @param iconPath - recebe uma String com o caminho da imagem a ser utilizada
     */
    public SimulationActor(Location location, String iconPath) {

        setCurrentLocation(location);
        setNextLocation(null);
        this.image = new ImageIcon(getClass().getResource(iconPath)).getImage();
        
    }
    
    /**
     * Método para retornar a localização atual
     * @return retorna um valor do tipo "Location" contendo a localização atual
     */

    public Location getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Método para retornar a próxima localização
     * @return retorna um valor do tipo "Location" contendo a próxima localização
     */
    
    public Location getNextLocation() {
        return nextLocation;
    }
    
    /**
     * Método para retornar a imagem a ser utilizada
     * @return retorna um valor do tipo "Image" contendo a imagem a ser utilizada
     */
    public Image getImage(){
        return image;
    }

    /**
     * Método para alterar a localização atual
     * @param currentLocation - recebe um valor do tipo "Location" para realizar a atualização do valor da localização atual
     */
    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * Método para alterar a próxima localização
     * @param nextLocation - recebe um valor do tipo "Location" para realizar a atualização do valor da próxima localização
     */
    public void setNextLocation(Location nextLocation) {
        this.nextLocation = nextLocation;
    }

    /**
     * Método abstrato para execução dos passos a serem realizados pelo ator
     * @param map - recebe o mapa do jogo
     * @param step - recebe o número de passos a serem realizados pelo ator
     */
    public abstract void executeStep(SimulationMap map, int step);

}