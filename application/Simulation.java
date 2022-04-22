package simulation.application;

import java.util.List;
import java.util.Map;

import simulation.Location;
import simulation.entity.actor.BikeActor;
import simulation.entity.actor.CarActor;
import simulation.entity.actor.TrafficLightActor;
import simulation.entity.actor.VehicleActor;
import simulation.entity.mapfacilities.MapFacility;
import simulation.entity.mapfacilities.Restaurant;
import simulation.entity.provider.DeliSectionProvider;
import simulation.entity.provider.DoughProvider;
import simulation.entity.provider.DrinkProvider;
import simulation.entity.provider.MeatProvider;
import simulation.entity.provider.Provider;
import simulation.util.Randomizer;
import simulation.view.JanelaSimulacao;
import simulation.view.InformationView;

import java.util.ArrayList;

/**
 * Classe responsável pela realização da simulação
 */
public class Simulation {
    
    private CarActor vehicle; // declaração do ator "veiculo" que passará por cada fornecedor
    
    private JanelaSimulacao janelaSimulacao; //cria uma variável do tipo JanelaSimulacao

    private List<VehicleActor> bikeList; // declaração de uma lista de atores "bicicleta" que andarao pelo mapa e serao obstaculos para o veiculo

    private List<TrafficLightActor> trafficLightList; // declaracao de uma lista de semaforos que servirao de obstaculo para o veiculo sempre que vermelhos 

    private List<Provider> providerList; // declaracao de uma lista com os fornecedores

    private List<MapFacility> mapFacilityList; // declaracao de uma lista contendo os restaurantes

    private static final double _MAX_PERCENT_PROVIDER_AMOUNT_ = .02; // porcentagem máxima de espaços que um fornecedor pode ocupar

    private static final double _MIN_PERCENT_PROVIDER_AMOUNT_ = .01; // porcentagem mínima de espaços que um fornecedor pode ocupar

    private static final double _MAX_PERCENT_TRAFFIC_LIGHT_AMOUNT_ = .15; // porcentagem máxima de espaços que um semáforo pode ocupar

    private static final double _MIN_PERCENT_TRAFFIC_LIGHT_AMOUNT_ = .05; // porcentagem mínima de espaços que um semáforo pode ocupar
    
    private SimulationMap map;
    
    /**
     * Construtor da simulação, chamando o método responsável por iniciar a simulação
     */
    public Simulation() {

        initSimultion();

    }
    
    /**
     * Método para iniciar a simulação, pegando uma instância do simulationMap e, em seguida, iniciando todos os elementos por meio da chamada de seus métodos init.
     * Em seguida, o init do SimulationMap é chamado passando por parâmetro os elementos instanciados.
     * Por fim, um novo objeto do tipo JanelaSimulacao é criado.
     */
    private void initSimultion() {

        map = SimulationMap.getInstance();

        this.providerList = initProviders();
        this.vehicle = initMainVehicle();
        this.bikeList = initVehicles();
        this.trafficLightList = initTrafficLight();
        this.mapFacilityList = initMapFacilities();

        initSimulationMap(bikeList, providerList, trafficLightList, mapFacilityList);
        janelaSimulacao = new JanelaSimulacao(map);

    }
    
    /**
     * Método responsável por inicializar o ArrayList de restaurantes
     * @return retorna uma lista contendo os restaurantes inicializados e presentes na lista
     */
    private List<MapFacility> initMapFacilities() {

        List<MapFacility> facilityList = new ArrayList<>();

        facilityList = initRestaurant(facilityList);

        return facilityList;

    }

    /**
     * Método para pegar a porcentagem que está sendo utilizada no mapa para garantir as interações
     * @param percentage - fornece a porcentagem que está sendo utilizada na simulação
     * @return retorna um inteiro contendo a porcentagem que está sendo utilizada
     */
    private int getPercentageFromMapSize(double percentage) {
        return (int) Math.round(this.map.getColumnAmount() * this.map.getRowAmount() * percentage);
    }

    /**
     * Método para adicionar um restaurante à lista de inicialização de restaurantes
     * @param facilityList - lista contendo os restaurantes a serem adicionados
     * @return retorna uma lista contendo os restaurantes adicionados
     */
    private List<MapFacility> initRestaurant(List<MapFacility> facilityList) {

        facilityList.add(new Restaurant(Location.getNewRandomLocation(this.map)));
        return facilityList;

    }

    /**
     * Método para inicialização dos fornecededores
     * @return retorna uma lista contendo os fornecedores adicionados 
     */
    private List<Provider> initProviders() {

        List<Provider> providerList = new ArrayList<>();
        
        int providerAmount = Randomizer.getRandomInteger(
                                    getPercentageFromMapSize(Simulation._MIN_PERCENT_PROVIDER_AMOUNT_), 
                                    getPercentageFromMapSize(Simulation._MAX_PERCENT_PROVIDER_AMOUNT_)
                                );

        for (int i=0; i < 1; i++) {
            providerList.add(new DeliSectionProvider(Location.getNewRandomLocation(this.map)));
            providerList.add(new DoughProvider(Location.getNewRandomLocation(this.map)));
            providerList.add(new MeatProvider(Location.getNewRandomLocation(this.map)));
            providerList.add(new DrinkProvider(Location.getNewRandomLocation(this.map)));
        }

        return providerList;

    }
    
    /**
     * Método para inicialização dos semáforos
     * @return retorna uma lista contendo os semáforos adicionados 
     */
    private List<TrafficLightActor> initTrafficLight() {

        List<TrafficLightActor> trafficLightList = new ArrayList<>();

        int trafficLightAmount = Randomizer.getRandomInteger(
                                    getPercentageFromMapSize(Simulation._MIN_PERCENT_TRAFFIC_LIGHT_AMOUNT_), 
                                    getPercentageFromMapSize(Simulation._MAX_PERCENT_TRAFFIC_LIGHT_AMOUNT_)
                                );

        for (int i=0; i < trafficLightAmount; i++) {
            
            trafficLightList.add(new TrafficLightActor(Location.getNewRandomLocation(this.map)));

        }

        return trafficLightList;

    }

    /**
     * Método para iniciar os elementos utilizados na simulação do mapa
     * @param bikeList - parâmetro contendo a lista com todas as bicicletas cadastradas
     * @param providerList - parâmetro contendo a lista com todos os fornecedores cadastrados
     * @param trafficLightList - parâmetro contendo a lista com todos os semáforos cadastrados
     * @param facilityList - parâmetro contendo a lista com todos os restaurantes cadastrados
     */
    private void initSimulationMap(List<VehicleActor> bikeList, List<Provider> providerList, List<TrafficLightActor> trafficLightList, List<MapFacility> facilityList) {
        
        addFacilitiesToMap(facilityList);
        addVehiclesToMap(bikeList);
        addProvidersToMap(providerList);
        addTrafficLightsToMap(trafficLightList);

    }

    /**
     * Método para adicionar restaurantes ao mapa
     * @param facilityList - parâmetro contendo a lista de restaurantes que deverão ser adicionados ao mapa
     */
    private void addFacilitiesToMap(List<MapFacility> facilityList) {

        for (MapFacility facility : facilityList) {
            this.map.addFacility(facility);
        }

    }

    /**
     * Método para adicionar os veículos ao mapa
     * @param bikeList - parâmetro contendo a lista de veículos a serem adicionados no mapa
     */
    private void addVehiclesToMap(List<VehicleActor> bikeList) {

        for (VehicleActor bike : bikeList) {
            this.map.addVehicle(bike);
        }

    }
    
    /**
     * Método para adicionar os fornecedores ao mapa
     * @param providerList - parâmetro contendo a lista de fornecedores a serem adicionados no mapa
     */  
    private void addProvidersToMap(List<Provider> providerList) {

        for (Provider provider : providerList) {
            this.map.addProvider(provider);
        }

    }

    /**
     * Método para adicionar os semáforos ao mapa
     * @param trafficLightList - parâmetro contendo a lista de semáforos a serem adicionados no mapa
     */
    private void addTrafficLightsToMap(List<TrafficLightActor> trafficLightList) {

        for (TrafficLightActor trafficLight : trafficLightList) {
            this.map.addTrafficLight(trafficLight);
        }

    }

    /**
     * Método para inicializar os veículos
     * @return retorna a lista de bicicletas inicializadas
     */
    private List<VehicleActor> initVehicles() {

        List<VehicleActor> bikeList = new ArrayList<>();
        
        int bikeAmount = Randomizer.getRandomInteger(0,10);

        for (int i=0; i < bikeAmount; i++) {
            bikeList.add(initBike());
        }

        return bikeList;

    }

    /**
     * Método para inicializar o carro que passará pelos fornecedores buscando os produtos
     * @return retorna o carro inicializado
     */
    private CarActor initMainVehicle() {

        this.vehicle = new CarActor(Location.getNewRandomLocation(this.map));
        this.vehicle.setDestinationLocationList(initNewDestinationLocationBasedInProviders());

        return this.vehicle;
    }


    /**
     * Método para inicializar as bicicletas que estarão presentes no mapa e atuarão como obstáculo
     * @return retorna as bicicletas inicializadas
     */    
    private VehicleActor initBike() {

        VehicleActor bike = new BikeActor(Location.getNewRandomLocation(this.map));
        bike.setNextLocation(Location.getNewRandomLocation(this.map));

        return bike;

    }

    /**
     * Método para definir os locais de destino com base na localização dos fornecedores
     * @return retorna uma lista contendo a localização dos fornecedores na qual o carro deverá passar
     */
    private List<Location> initNewDestinationLocationBasedInProviders() {
        
        List<Location> providerLocationList = new ArrayList<>();

        for (Provider provider : this.providerList) {

            providerLocationList.add(provider.getLocation());

        }

        return providerLocationList;

    }

    /**
     * Método para executar a simulação com base em uma quantidade de passos
     * @param stepAmount - parâmetro fornecendo a quantidade de passos a serem executados
     */
    public void executeSimulation(int stepAmount){

        janelaSimulacao.executarAcao();
        for (int i = 0; i < stepAmount; i++) {
            executeIteration(i);
            simulationDelay(200);
        }

    }

    /**
     * Método para realizar a chamada para as iterações de cada ator da simulação
     * @param stepNumber - parâmetro fornecendo a quantidade de passos a ser realizada
     */
    private void executeIteration(int stepNumber) {

        executeTrafficLightIteration(stepNumber);
        executeBikeIteration(stepNumber);
        executeVehicleIteration(stepNumber);
        
        updateInformationsView();
        
        janelaSimulacao.executarAcao();
    }
    
    
    /**
     * Método para atualizar as informações sobre o veículo que serão apresentadas na interface InformationView
     */
    private void updateInformationsView() {

        this.janelaSimulacao.updateCarWeightInformation(this.vehicle.getWeight());
        this.janelaSimulacao.updateCollectedProductAmountInformation(this.vehicle.getCollectedProductAmount());

    }
    
    /**
     * Método utilizado para realizar as iterações dos semáforos
     * @param step - parâmetro fornecendo a quantidade de passos a ser realizada
     */
    private void executeTrafficLightIteration(int step) {

        trafficLightList.forEach(traffictLight -> {
            traffictLight.executeStep(map, step);
        });

    }

    /**
     * Método utilizado para realizar as iterações das bicicletas
     * @param step - parâmetro fornecendo a quantidade de passos a ser realizada
     */
    private void executeBikeIteration(int step) {

        bikeList.forEach(vehicle -> {
            map.removeVehicle(vehicle);
            vehicle.executeStep(map, step);
            map.addVehicle(vehicle);
        });

    }

    /**
     * Método utilizado para realizar as iterações dos veículos
     * @param step - parâmetro fornecendo a quantidade de passos a ser realizada
     */
    private void executeVehicleIteration(int step) {

        map.removeVehicle(vehicle);
        this.vehicle.executeStep(map, step);
        map.addVehicle(vehicle);

    }
    
    /**
     * Método contendo o "delay" utilizado, para que as interações não ocorram rápido demais
     * @param milisegundos - quantidade de milisegundos na qual o delay deverá ser aplicado
     */
    private void simulationDelay(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    
}
