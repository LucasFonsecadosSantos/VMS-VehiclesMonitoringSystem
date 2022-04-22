package simulation.entity.provider;

import simulation.Location;
import simulation.entity.hindrance.Product;

/**
 * Classe para definição do fornecedor de bebidas
 */
public class DrinkProvider extends Provider {

    private static final String _ICON_PATH_ = "./../../Imagens/DrinkProvider.jpeg"; // definindo o caminho da imagem a ser utilizada
 
    private static final int _WEIGHT_OF_THE_SUPPLIED_PRODUCT_ = 2; // definindo o peso do produto

    /**
     * Construtor da classe, instanciando um novo produto e atribuindo a ele seu peso, localização e imagem
     * @param location - recebe a localização onde o fornecedor estará localizado
     */
    public DrinkProvider(Location location) {

        super(
            new Product(DrinkProvider._WEIGHT_OF_THE_SUPPLIED_PRODUCT_), 
            location, 
            DrinkProvider._ICON_PATH_
        );

    }

    /**
     * Método toString contendo o fornecedor em questão e qual o peso do produto (advindo da superclasse)
     * @return retorna uma String contendo o fornecedor e o peso do produto
     */
    @Override
    public String toString() {

        return "Drink Provider. Product: " + super.toString();

    }

}