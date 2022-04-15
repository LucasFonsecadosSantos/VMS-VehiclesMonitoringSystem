package simulacao;

import simulacao.entity.actor.Vehicle;
import simulacao.entity.actor.Car;
import simulacao.entity.actor.Bike;
import simulacao.entity.hindrance.TrafficLightHindrance;
import simulacao.entity.provider.Provider;
import simulacao.entity.provider.MeatProvider;
import simulacao.entity.provider.DrinkProvider;
import simulacao.entity.provider.DoughProvider;
import simulacao.entity.provider.DeliSectionProvider;
import simulacao.util.Randomizer;

import java.util.List;
import java.util.ArrayList;

/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulation {
    
    private Vehicle vehicle;
    
    private JanelaSimulacao janelaSimulacao;

    private static final int _MAX_TRAFFIC_LIGHT_ = 5;
    
    private Mapa map;
    
    public Simulation() {

        initSimultion();

    }
    
    private void initSimultion() {

        map = new Mapa();
        List<Vehicle> vehicleList = initVehicles();
        List<Provider> providerList = initProviders();
        List<TrafficLightHindrance> trafficLightList = initTrafficLightHindrance();
        initSimulationMap(vehicleList, providerList, trafficLightList);        
        janelaSimulacao = new JanelaSimulacao(map);
    }

    private List<Provider> initProviders() {

        List<Provider> providerList = new ArrayList<>();
        
        providerList.add(new DeliSectionProvider());
        providerList.add(new DoughProvider());
        providerList.add(new MeatProvider());
        providerList.add(new DrinkProvider());

        return providerList;

    }

    private List<TrafficLightHindrance> initTrafficLightHindrance() {

        List<TrafficLightHindrance> trafficLightList = new ArrayList<>();

        int trafficLightAmount = Randomizer.getRandomInteger(Simulation._MAX_TRAFFIC_LIGHT_);

        for (int i=0; i < trafficLightAmount; i++) {
            trafficLightList.add(new TrafficLightHindrance());
        }

        return trafficLightList;

    }

    private void initSimulationMap(List<Vehicle> vehicleList, List<Provider> providerList, List<TrafficLightHindrance> trafficLightList) {
        
        addVehiclesToMap(vehicleList);
        addProvidersToMap(providerList);
        addTrafficLightsToMap(trafficLightList);

    }

    private void addVehiclesToMap(List<Vehicle> vehicleList) {

        for (Vehicle vehicle : vehicleList) {
            this.map.addVehicle(vehicle);
        }

    }

    private void addProvidersToMap(List<Provider> providerList) {

        for (Provider provider : providerList) {
            this.map.addProvider(provider);
        }

    }

    private void addTrafficLightsToMap(List<TrafficLightHindrance> trafficLightList) {

        for (TrafficLightHindrance trafficLight : trafficLightList) {
            this.map.addTrafficLight(trafficLight);
        }

    }

    private List<Vehicle> initVehicles() {

        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(initMainVehicle());
        
        int bikeAmount = Randomizer.getRandomInteger(5);

        for (int i=0; i < bikeAmount; i++) {
            vehicleList.add(initBike());
        }

        return vehicleList;

    }

    private Vehicle initMainVehicle() {

        this.vehicle = new Car(initNewLocation());
        this.vehicle.setLocalizacaoDestino(initNewLocation());

        return this.vehicle;
    }

    private Vehicle initBike() {

        Vehicle bike = new Bike(initNewLocation());
        bike.setLocalizacaoDestino(initNewLocation());

        return bike;

    }

    private Localizacao initNewLocation() {
        return new Localizacao(Randomizer.getRandomInteger(this.map.getLargura()),Randomizer.getRandomInteger(this.map.getAltura()));
    }

    public void executeSimulation(int numPassos){

        janelaSimulacao.executarAcao();
        for (int i = 0; i < numPassos; i++) {
            executarUmPasso();
            esperar(100);
        }

    }

    private void executarUmPasso() {
        map.removerItem(vehicle);
        vehicle.executeStep(map);
        map.adicionarItem(vehicle);
        janelaSimulacao.executarAcao();
    }
    
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    
}
