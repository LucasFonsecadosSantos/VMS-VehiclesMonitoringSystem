package simulation.entity.provider;

import simulation.Location;
import simulation.entity.hindrance.Product;

/**
 * Classe para definição do fornecedor de frios utilizados na produção das pizzas
 */
public class DeliSectionProvider extends Provider {

    private static final String _ICON_PATH_ = "./../../Imagens/DeliSectionProvider.jpeg"; // definindo o caminho da imagem a ser utilizada

    private static final int _WEIGHT_OF_THE_SUPPLIED_PRODUCT_ = 1; // definindo o peso do produto

    /**
     * Construtor da classe, instanciando um novo produto e atribuindo a ele seu peso, localização e imagem
     * @param location - recebe a localização onde o fornecedor estará localizado
     */
    public DeliSectionProvider(Location location) {

        super(
            new Product(DeliSectionProvider._WEIGHT_OF_THE_SUPPLIED_PRODUCT_), 
            location, 
            DeliSectionProvider._ICON_PATH_
        );

    }

    /**
     * Método toString contendo o fornecedor em questão e qual o peso do produto (advindo da superclasse)
     * @return retorna uma String contendo o fornecedor e o peso do produto
     */
    @Override
    public String toString() {

        return "Deli Section Provider. Product: " + super.toString();

    }

}