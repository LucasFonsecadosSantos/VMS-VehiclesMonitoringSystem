package simulation.application;

import java.util.Map;

import simulation.Location;
import simulation.entity.actor.SimulationActor;
import simulation.entity.actor.TrafficLightActor;
import simulation.entity.actor.VehicleActor;
import simulation.entity.mapfacilities.MapFacility;
import simulation.entity.mapfacilities.Restaurant;
import simulation.entity.provider.Provider;

import java.util.HashMap;

/**
 * Representação de um mapa contendo todo o cenário na qual a simulação ocorrerá
 */
public class SimulationMap {

    private SimulationActor[][] actors; // consiste em um vetor para armazenar os atores do jogo

    private Map<Provider, Location> providers; // hashmap para permitir a localização dos fornecedores

    private Map<TrafficLightActor, Location> trafficLights; // hashmap para permitir a localização dos semáforos
    
    private Map<MapFacility, Location> facilities; // hashmap para permitir a localização do restaurante

    private int columnAmount; // variável para armazenar a quantidade de colunas
    
    private int rowAmount; // variável para armazenar a quantidade de linhas
    
    private static final int _DEFAULT_COLUMN_AMOUNT_ = 35; // definindo a quantidade de colunas
    
    private static final int _DEFAULT_ROW_AMOUNT_ = 35; // definindo a quantidade de linhas

    private static SimulationMap singletonObject = null; // Objeto para permitir apenas uma única instanciação do mapa
    
    /**
     * Método para garantir que apenas uma única instância do mapa da simulação seja criado
     * @return retorna um objeto do tipo SimulationMap caso já exista alguma instância criada
     */
    public static SimulationMap getInstance() {
        if (singletonObject == null) {
            singletonObject = new SimulationMap();
        }
        return singletonObject;
    }

    /**
     * Método para permitir que uma única instância do mapa de simulação seja criado e que sejam criadas as linhas e colunas do mapa
     * @param columnAmount - variável inteira contendo a quantidade de colunas
     * @param rowAmount - variável inteira contendo a quantidade de linhas
     * @return retorna um objeto do tipo SimulationMap caso alguma instância do objeto já tenha sido criada
     */
    public SimulationMap getInstance(int columnAmount, int rowAmount) {
        
        if (singletonObject == null) {
            singletonObject = new SimulationMap(columnAmount, rowAmount);
        }
        return singletonObject;
    }

    /**
     * Método para criar mapa para alocar os atores da simulacao.
     * @param columnAmount: variável inteira contendo a quantidade de colunas
     * @param rowAmount: variável inteira contendo a quantidade de linhas
     */
    private SimulationMap(int columnAmount, int rowAmount) {

        this.columnAmount = columnAmount;
        this.rowAmount = rowAmount;
        initLists();
        actors = new VehicleActor[columnAmount][rowAmount];

    }
    /**
     * Método para criar mapa com tamanho padrão definido.
     */
    private SimulationMap(){
        this(_DEFAULT_COLUMN_AMOUNT_, _DEFAULT_ROW_AMOUNT_);
    }   

    /**
     * Método para inicializar os hashmaps utilizados
     */
    private void initLists() {

        this.providers = new HashMap<>();
        this.trafficLights = new HashMap<>();
        this.facilities = new HashMap<>();

    }
    
    /**
     * Método para adicionar um novo veículo a lista de veículos a serem utilizados na simulação
     * @param v - fornece o objeto do tipo VehicleActor para sua adição na simulação
     */
    public void addVehicle(VehicleActor v){
        actors[v.getCurrentLocation().getX()][v.getCurrentLocation().getY()] = v;
    }

    /**
     * Método para adicionar um novo fornecedor a lista de fornecedores a serem utilizados na simulação
     * @param provider - fornece o objeto do tipo Provider para sua adição na simulação
     */
    public void addProvider(Provider provider){
        providers.put(provider, provider.getLocation());
    }

    /**
     * Método para adicionar um novo restaurante a lista de restaurantes a serem utilizados na simulação
     * @param facility - fornece o objeto do tipo MapFacility para sua adição na simulação
     */
    public void addFacility(MapFacility facility){
        facilities.put(facility, facility.getLocation());
    }

    /**
     * Método para pegar a localização do restaurante
     * @return a localização do restaurante (objeto do tipo "Location") caso encontre uma localização. Caso a nova localização seja igual a null a aplicação retorna null
     */
    public Location getRestaurantLocation() {

        for (Map.Entry<MapFacility, Location> entry : this.facilities.entrySet()) {

            if (entry.getKey() instanceof Restaurant) {
                return entry.getValue();
            }

        }

        return null;
    } 

    /**
     * Método para adicionar um semáforo na simulação
     * @param trafficLight - fornece o objeto do tipo TrafficLightActor para sua adição na simulação
     */
    public void addTrafficLight(TrafficLightActor trafficLight){
        trafficLights.put(trafficLight, trafficLight.getCurrentLocation());
    }

    /**
     * Método para remover um veículo da lista de veículos
     * @param v - fornece o veículo a ser retirado da lista de veículos
     */
    public void removeVehicle(VehicleActor v){
        actors[v.getCurrentLocation().getX()][v.getCurrentLocation().getY()] = null;
    }
    
    /**
     * Método para atualizar o mapa, retirando um veículo de uma posição e o alocando em uma nova posição
     * @param vehicle - fornece o veículo a ser utilizado na atualização do mapa
     */
    public void updateMap(VehicleActor vehicle) {

        removeVehicle(vehicle);
        addVehicle(vehicle);        
        
    }

    /**
     * Método para verificar se um veículo pode continuar os passos no jogo ou se precisa parar devido aos semáforos
     * @param currentLocation - fornece a localização atual do veículo, a fim de verificar se este pode avançar para a próxima localização
     * @return retorna true se o semáforo estiver verde e false se o semáforo estiver vermelho
     */
    public boolean isAllowToContinue(Location currentLocation) {

        return !hasRedTrafficLightAtPosition(currentLocation);

    }

    /**
     * Método para verificar se existe um semáforo vermelho na posição
     * @param currentLocation - fornece a posição a ser verificada se possui um semáforo que está com a luz vemelha acesa
     * @return retorna true se encontrar a luz vermelha no semáforo e false se encontrar a luz verde
     */
    private boolean hasRedTrafficLightAtPosition(Location currentLocation) {

        TrafficLightActor trafficLight;
        Location trafficLightLocation;

        for (Map.Entry<TrafficLightActor, Location> entry : trafficLights.entrySet()) {

            trafficLight = entry.getKey();
            trafficLightLocation = entry.getValue();

            if (currentLocation.getX() == trafficLightLocation.getX() && currentLocation.getY() == trafficLightLocation.getY()) {
                return trafficLight.getState().equals(TrafficLightActor._RED_);
            }
        }

        return false;

    }

    /**
     * Método para verificar se a próxima localização do veículo em questão não está ocupada por bicicletas
     * @param nextLocation - recebe a próxima localização para a qual o veículo irá
     * @return retorna true se a próxima localização tiver valor igual a null
     */
    public boolean isNotNextLocationOccupied(Location nextLocation) {
        
        return getActor(nextLocation.getX(), nextLocation.getY()) == null;

    }

    /**
     * Método para obter o ator que está em uma das coordenadas da simulação
     * @param x - representa a posição no eixo x do mapa da simulação
     * @param y - representa a posição no eixo y do mapa da simulação
     * @return retorna o ator que está em uma determinada posição fornecida por parâmetro
     */
    public SimulationActor getActor(int x, int y) {
        return actors[x][y];
    }

    /**
     * Método para obter o restaurante que está em uma das coordenadas da simulação
     * @param x - representa a posição no eixo x do mapa da simulação
     * @param y - representa a posição no eixo y do mapa da simulação
     * @return retorna o restaurante que está em uma determinada posição fornecida por parâmetro
     */
    public MapFacility getFacilityAtCoordinates(int x, int y) {

        for(Map.Entry<MapFacility, Location> entry : facilities.entrySet()) {

            if (entry.getValue().getX() == x && entry.getValue().getY() == y) {
                return entry.getKey();
            }

        }
        return null;

    }

    /**
     * Método para obter um semáforo que está em uma das coordenadas da simulação
     * @param x - representa a posição no eixo x do mapa da simulação
     * @param y - representa a posição no eixo y do mapa da simulação
     * @return - retorna o semáforo que está em uma determinada posição fornecida por parâmetro
     */
    public TrafficLightActor getTrafficLightAtCoordinates(int x, int y) {
        
        for(Map.Entry<TrafficLightActor, Location> entry : trafficLights.entrySet()) {

            if (entry.getValue().getX() == x && entry.getValue().getY() == y) {
                return entry.getKey();
            }

        }
        return null;

    }

    /**
     * Método para pegar o fornecedor que está em uma das coordenadas da simulação
     * @param x - representa a posição no eixo x do mapa da simulação
     * @param y - representa a posição no eixo y do mapa da simulação
     * @return - retorna o fornecedor que está em uma determinada posição fornecida por parâmetro
     */
    public Provider getProviderAtCoordinates(int x, int y) {

        for(Map.Entry<Provider, Location> entry : providers.entrySet()) {
            
            if (entry.getValue().getX() == x && entry.getValue().getY() == y) {
                return entry.getKey();
            }
            
        }
        return null;

    }

    /**
     * Método que retorna a quantidade de colunas
     * @return retorna um inteiro contendo a quantidade de colunas
     */
    public int getColumnAmount() {
        return columnAmount;
    }

    /**
     * Método que retorna a quantidade de linhas
     * @return retorna um inteiro contendo a quantidade de linhas
     */
    public int getRowAmount() {
        return rowAmount;
    }
    
}
