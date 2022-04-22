package simulation.entity.mapfacilities;

import java.awt.Image;
import javax.swing.ImageIcon;
import simulation.Location;

/**
 * Classe para adicionar o(s) restaurante(s) em uma dada posição no mapa
 */
public abstract class MapFacility {
    
    private Location location; // variável para armazenar a localização

    private Image image; // variável para armazenar o caminho da imagem a ser adicionada

    /**
     * Construtor para setar a imagem e a localização do objeto
     * @param location - localização na qual o restaurante será adicionado
     * @param iconPath - caminho da imagem que será adicionada no local onde o restaurante estiver
     */
    public MapFacility(Location location, String iconPath) {

        setImage(iconPath);
        setLocation(location);

    }

    /**
     * Método para pegar o valor armazenado na variável location
     * @return retorna a localização armazenada na variável location
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Método para alterar a localização armazenada na variável location
     * @param location - recebe a nova localização do restaurante a ser atribuída
     */
    private void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Método para alterar o caminho da imagem utilizada para representação do restaurante
     * @param iconPath - recebe o caminho da nova imagem a ser utilizada
     */
    private void setImage(String iconPath) {

        this.image = new ImageIcon(getClass().getResource(iconPath)).getImage();

    }

    /** 
     * Método para pegar o caminho da imagem armazenada na variável image
     * @return retorna o caminho da imagem que está sendo utilizada
     */
    public Image getImage(){
        return image;
    }

    /**
     * Método toString que sobrescreve a localização atual do restaurante
     * @return retorna uma String contendo a localização atual do restaurante
     */
    @Override
    public String toString() {

        return "MapFacility Location: " + this.location.toString();

    }

}
