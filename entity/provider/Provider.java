package simulation.entity.provider;

import java.awt.Image;
import javax.swing.ImageIcon;

import simulation.Location;
import simulation.entity.hindrance.Product;

/**
 * Superclasse contendo as definições dos fornecedores dos ingredientes para produção das pizzas
 */
public abstract class Provider {

    private Product product; // variável para armazenar o produto que será transportado
    
    private Location location; // variável para armazenar a localização de onde o fornecedor estará no mapa

    private Image image; // variável para armazenar o caminho da imagem a ser utilizada

    /**
     * Construtor da classe de fornecedores, atribuindo os valores de produto, localização e imagem para as variáveis pertinentes
     * @param product - passa o produto a ser carregado do fornecedor
     * @param location - passa a localização onde o fornecedor estará
     * @param iconPath - passa o caminho da imagem que será utilizada
     */
    public Provider(Product product, Location location, String iconPath) {

        setImage(iconPath);
        setLocation(location);
        setProduct(product);

    }

    /**
     * Método para retornar o produto que está armazendo na variável product
     * @return retorna o produto armazenado na variável product
     */
    public Product getProduct() {
        return this.product;
    }

    /**
     * Método para alterar o produto com base em um produto recebido por parâmetro
     * @param product - novo produto a ser atualizado na variável product
     */
    public void setProduct(Product product) {

        try {

            if (product == null) {
                throw new Exception("Product doesn't exist.");
            } else {
                this.product = product;
            }

        } catch(Exception exception) {
            System.out.println(exception.getMessage());
        }

    }

    /**
     * Método que retorna a localização do fornecedor
     * @return fornece um objeto do tipo Location contendo a localização do fornecedor
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Método toString para retornar o peso do produto que está sendo carregado
     * @return retorna uma String contendo o peso do produto
     */
    @Override
    public String toString() {

        return this.product.toString();

    }

    /**
     * Método para alterar a localização do fornecedor
     * @param location - recebe a nova localização na qual o fornecedor deverá ser atualizado para
     */
    private void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Método para atualizar a imagem que está sendo utilizada para representar o fornecedor
     * @param iconPath - caminho contendo a imagem que será utilizada na atualização
     */
    private void setImage(String iconPath) {

        this.image = new ImageIcon(getClass().getResource(iconPath)).getImage();

    }

    /**
     * Método que retorna a imagem que está sendo utilizada
     * @return retorna um objeto do tipo Image contendo a imagem que está sendo retornada
     */
    public Image getImage(){
        return image;
    }
    
}