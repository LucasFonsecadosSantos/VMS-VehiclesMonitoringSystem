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

    private List<Vehicle> bikeList;

    private static final int _MAX_TRAFFIC_LIGHT_ = 5;
    
    private Mapa map;
    
    public Simulation() {

        initSimultion();

    }
    
    private void initSimultion() {

        map = new Mapa();
        this.bikeList = initVehicles();
        List<Provider> providerList = initProviders();
        List<TrafficLightHindrance> trafficLightList = initTrafficLightHindrance();
        initSimulationMap(bikeList, providerList, trafficLightList);        
        janelaSimulacao = new JanelaSimulacao(map);
    }

    private List<Provider> initProviders() {

        List<Provider> providerList = new ArrayList<>();
        
        providerList.add(new DeliSectionProvider(initNewLocation()));
        providerList.add(new DoughProvider(initNewLocation()));
        providerList.add(new MeatProvider(initNewLocation()));
        providerList.add(new DrinkProvider(initNewLocation()));

        return providerList;

    }

    private List<TrafficLightHindrance> initTrafficLightHindrance() {

        List<TrafficLightHindrance> trafficLightList = new ArrayList<>();

        int trafficLightAmount = Randomizer.getRandomInteger(Simulation._MAX_TRAFFIC_LIGHT_);

        for (int i=0; i < trafficLightAmount; i++) {
            
            trafficLightList.add(new TrafficLightHindrance(initNewLocation()));
        }

        return trafficLightList;

    }

    private void initSimulationMap(List<Vehicle> bikeList, List<Provider> providerList, List<TrafficLightHindrance> trafficLightList) {
        
        addVehiclesToMap(bikeList);
        addProvidersToMap(providerList);
        addTrafficLightsToMap(trafficLightList);

    }

    private void addVehiclesToMap(List<Vehicle> bikeList) {

        for (Vehicle bike : bikeList) {
            this.map.addVehicle(bike);
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

        List<Vehicle> bikeList = new ArrayList<>();
        initMainVehicle();
        
        //int bikeAmount = Randomizer.getRandomInteger(5);

        for (int i=0; i < 5; i++) {
            bikeList.add(initBike());
        }

        return bikeList;

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
        return new Localizacao(Randomizer.getRandomInteger(this.map.getColumnAmount()),Randomizer.getRandomInteger(this.map.getRowAmount()));
    }

    public void executeSimulation(int stepAmount){

        janelaSimulacao.executarAcao();
        for (int i = 0; i < stepAmount; i++) {
            System.out.println("-------------------------------");
            executeIteration();
            esperar(100);
        }

    }

    private void executeIteration() {
        executeBikeIteration();
        executeVehicleIteration();
        
        janelaSimulacao.executarAcao();
    }

    private void executeBikeIteration() {
        bikeList.forEach(vehicle -> {
            map.removeVehicle(vehicle);
            vehicle.executeStep(map);
            map.addVehicle(vehicle);
        });
    }

    private void executeVehicleIteration() {
        map.removeVehicle(vehicle);
        vehicle.executeStep(map);
        map.addVehicle(vehicle);
    }
    
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    
}
