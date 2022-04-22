package simulation.entity.provider;

import simulation.Location;
import simulation.entity.hindrance.Product;

/**
 * Classe para definição do fornecedor de massas utilizadas na produção de pizzas
 */
public class DoughProvider extends Provider {

    private static final String _ICON_PATH_ = "./../../Imagens/DoughProvider.jpeg"; // definindo o caminho da imagem a ser utilizada

    private static final int _WEIGHT_OF_THE_SUPPLIED_PRODUCT_ = 2; // definindo o peso do produto
    
    /**
     * Construtor da classe, instanciando um novo produto e atribuindo a ele seu peso, localização e imagem
     * @param location - recebe a localização onde o fornecedor estará localizado
     */
    public DoughProvider(Location location) {

        super(
            new Product(DoughProvider._WEIGHT_OF_THE_SUPPLIED_PRODUCT_), 
            location, 
            DoughProvider._ICON_PATH_
        );

    }

    /**
     * Método toString contendo o fornecedor em questão e qual o peso do produto (advindo da superclasse)
     * @return retorna uma String contendo o fornecedor e o peso do produto
     */
    @Override
    public String toString() {

        return "Dough Provider. Product: " + super.toString();

    }

}