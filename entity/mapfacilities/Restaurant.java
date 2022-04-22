package simulation.entity.mapfacilities;

import simulation.Location;

/** 
 * Classe para definir as informações do restaurante que será atribuído ao mapa
 * Essa classe herda de MapFacility a fim de seguir o Princípio de Projeto Open-Closed Principle
 */
public class Restaurant extends MapFacility {

    private static final String _ICON_PATH_ = "./../../Imagens/Pizza.jpeg"; // definindo o caminho da imagem a ser utilizada

    /**
     * Construtor da classe, que repassa à superclasse a localização e o caminho da imagem que deve ser exibida
     * @param location - recebe a localização na qual o restaurante deve estar localizado no mapa
     */
    public Restaurant(Location location) {

        super(location, Restaurant._ICON_PATH_);

    }

    /**
     * Método toString para complementar o escrito pela superclasse, indicando que é um restaurante
     * @return concatena o toString da superclasse com o desta classe, a fim de fornecer as informações do restaurante
     */
    @Override
    public String toString() {
        return super.toString() + " -> Restaurant";
    }
    
}
