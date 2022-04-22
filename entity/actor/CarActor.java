package simulation.entity.actor;

import java.util.List;

import simulation.Location;
import simulation.application.SimulationMap;
import simulation.entity.hindrance.Product;
import simulation.entity.provider.Provider;
import simulation.util.shortestpathalgorithm.ShortestPathAlgorithm;
import simulation.util.shortestpathalgorithm.algorithmstrategies.VetorialDistance;

import java.util.ArrayList;

/**
 * Subclasse de veículo para definir o elemento do tipo CarActor, que andam pelo mapa buscando os ingredientes da pizzaria
*/
public class CarActor extends VehicleActor {

    private List<Product> productList; // declaração da lista com os produtos carregados pelo veículo

    private List<Location> destinationLocationList; // lista com os destinos na qual o carro deve passar

    private ShortestPathAlgorithm shortestPathAlgorithm; // variável para encontrar o menor caminho entre as localizações

    private static final String _ICON_PATH_ = "./../../Imagens/Car.jpg"; // caminho para atribuição da imagem do carro

    /**
     * Construtor da classe CarActor, onde o método para inicialização dos elementos é chamada e os atributos pertinentes enviados à superclasse
     * @param location recebe por parâmetro a localização onde o veículo está
     */
    public CarActor(Location location) {

        super(location, CarActor._ICON_PATH_);
        initAttributeInstances();

    }

    /**
     * Método para inicializar os atributos de menor caminho, lista de destinos e lista de produtos
     */
    private void initAttributeInstances() {
        this.shortestPathAlgorithm = new ShortestPathAlgorithm(new VetorialDistance());
        this.destinationLocationList = new ArrayList<>();
        this.productList = new ArrayList<>();
    }

    /**
     * Método para alterar a lista de destinos na qual o veículo deve passar 
     * @param locations - recebe por parâmetro uma lista de locais onde o veículo deve passar
     */
    public void setDestinationLocationList(List<Location> locations) {
        this.destinationLocationList = locations;
    }
    
    /**
     * Método para verificar se um produto já está na lista de produtos carregados pelo veículo, caso contrário o produto é adicionado à lista
     * @param product recebe por parâmetro um produto para verificar se este já encontra-se na lista de produtos que estão no veículo 
     */
    public void retriveProduct(Product product) {

        if (!this.productList.contains(product)) {
            this.productList.add(product);
        }

    }

    /**
     * Método para identificar a próxima localização para a qual o veículo deverá se deslocar
     * @param map recebe o mapa como parâmetro para identificar qual será a próxima localização. Caso a localização esteja vazia, o sistema retorna para a pizzaria
     * @return o retorno deste método é a próxima localização para a qual o veículo deverá ir 
     */
    public Location getNextLocation(SimulationMap map) {
        
        if (this.destinationLocationList.isEmpty()) {
            return map.getRestaurantLocation();
        }

        Location nextLocation = this.shortestPathAlgorithm.calculateShortestPath(getCurrentLocation(), this.destinationLocationList);
        
        for (Location location : this.destinationLocationList) {
            if (location.equals(nextLocation)) {
                if (nextLocation.equals(getCurrentLocation())) {
                    this.destinationLocationList.remove(nextLocation);
                }
                return nextLocation;
            }
        }
        return nextLocation;
    }

    /**
     * Método para executar os passos a serem realizados pelo carro durante o percorrimento do mapa
     * @param map recebe como parâmetro o mapa da simulação
     * @param step recebe como parâmetro a quantidade de passos a serem executados pelo carro ao longo do mapa
     */
    @Override
    public void executeStep(SimulationMap map, int step){

        Location currentLocation = getCurrentLocation();
        Location nextLocation = this.getNextLocation(map);
        Provider provider = null;
        
        if(isValidStep(nextLocation, step)) {

            if (map.isAllowToContinue(nextLocation)) {
                
                if (map.isNotNextLocationOccupied(nextLocation)) {

                    provider = map.getProviderAtCoordinates(currentLocation.getX(), currentLocation.getY());
                    
                    if (provider != null) {
                        this.retriveProduct(provider.getProduct());
                    }
                    
                    super.updateLocation(this.getNextLocation(map));

                }
            }

        }

    }

    /**
     * Método para verificar se o próximo passo a ser realizado pelo carro é um passo válido
     * @param nextLocation recebe como parâmetro o mapa da simulação
     * @param step recebe como parâmetro a quantidade de passos a serem executados pelo carro ao longo do mapa
     * @return retorna "true" para casos onde a próxima localização for diferente de null e o peso da carga for igual a 0 e "false" para casos onde a próxima localização for diferente de null e o resultado da divisão da quantidade de passos pelo peso for igual a zero.
     */
    private boolean isValidStep(Location nextLocation, int step) {
        return ((nextLocation != null && getWeight() == 0) ||
        (nextLocation != null && step % getWeight() == 0));
    }

    /**
     * Método para obter o peso que está sendo carregado pelo veículo
     * @return retorna um inteiro com o peso dos produtos presentes na lista de produtos
     */
    public int getWeight() {
        
        int weight = 0;

        for (Product product : this.productList) {
            weight += product.getWeight();
        }

        return weight;

    }
    
    /**
     * Método para pegar a quantidade de produtos coletados pelo veículo
     * @return retorna a quantidade de produtos coletados pelo veículo
     */
    public int getCollectedProductAmount() {
        return this.productList.size();
    }

}