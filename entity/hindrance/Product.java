package simulation.entity.hindrance;

/**
 * Classe para definição dos pesos de cada um dos produtos
 */
public class Product {

    private int weight; // variável para armazenar o peso do produto

    /**
     * Construtor de produto, que recebe um peso a ser atribuido ao produto por parâmetro
     * @param weight - parâmetro com o peso do produto, a ser atribuído ao mesmo no método setWeight(weight)
     */
    public Product(int weight) {
        setWeight(weight);
    }

    /**
     * Método que retorna o peso de um determinado produto
     * @return retorna um inteiro contendo o peso de um produto
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     * Método para atribuir um novo peso a um produto
     * @param weight - novo peso a ser atribuído, recebido por parâmetro
     */
    private void setWeight(int weight) {
        this.weight = weight;
    }

}