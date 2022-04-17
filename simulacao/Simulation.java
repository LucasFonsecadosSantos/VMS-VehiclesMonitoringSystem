package simulacao;

import simulacao.entity.actor.VehicleActor;
import simulacao.entity.actor.CarActor;
import simulacao.entity.actor.TrafficLightActor;
import simulacao.entity.actor.BikeActor;
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
    
    private VehicleActor vehicle;
    
    private JanelaSimulacao janelaSimulacao;

    private List<VehicleActor> bikeList;

    private static final int _MAX_TRAFFIC_LIGHT_ = 5;
    
    private SimulationMap map;
    
    public Simulation() {

        initSimultion();

    }
    
    private void initSimultion() {

        map = new SimulationMap();
        this.bikeList = initVehicles();
        List<Provider> providerList = initProviders();
        List<TrafficLightActor> trafficLightList = initTrafficLightHindrance();
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

    private List<TrafficLightActor> initTrafficLightHindrance() {

        List<TrafficLightActor> trafficLightList = new ArrayList<>();

        int trafficLightAmount = Randomizer.getRandomInteger(Simulation._MAX_TRAFFIC_LIGHT_);
        int minTrafficLightAmount = (int) Math.round(this.map.getColumnAmount() * this.map.getRowAmount() *.05);
        System.out.println(minTrafficLightAmount);
        for (int i=minTrafficLightAmount; i < minTrafficLightAmount + 10; i++) {
            
            trafficLightList.add(new TrafficLightActor(initNewLocation()));
        }

        return trafficLightList;

    }

    private void initSimulationMap(List<VehicleActor> bikeList, List<Provider> providerList, List<TrafficLightActor> trafficLightList) {
        
        addVehiclesToMap(bikeList);
        addProvidersToMap(providerList);
        addTrafficLightsToMap(trafficLightList);

    }

    private void addVehiclesToMap(List<VehicleActor> bikeList) {

        for (VehicleActor bike : bikeList) {
            this.map.addVehicle(bike);
        }

    }

    private void addProvidersToMap(List<Provider> providerList) {

        for (Provider provider : providerList) {
            this.map.addProvider(provider);
        }

    }

    private void addTrafficLightsToMap(List<TrafficLightActor> trafficLightList) {

        for (TrafficLightActor trafficLight : trafficLightList) {
            this.map.addTrafficLight(trafficLight);
        }

    }

    private List<VehicleActor> initVehicles() {

        List<VehicleActor> bikeList = new ArrayList<>();
        initMainVehicle();
        
        int bikeAmount = Randomizer.getRandomInteger(0,10);

        for (int i=0; i < bikeAmount; i++) {
            bikeList.add(initBike());
        }

        return bikeList;

    }

    private VehicleActor initMainVehicle() {

        this.vehicle = new CarActor(initNewLocation());
        this.vehicle.setNextLocation(initNewLocation());

        return this.vehicle;
    }

    private VehicleActor initBike() {

        VehicleActor bike = new BikeActor(initNewLocation());
        bike.setNextLocation(initNewLocation());

        return bike;

    }

    private Location initNewLocation() {
        return new Location(Randomizer.getRandomInteger(this.map.getColumnAmount()),Randomizer.getRandomInteger(this.map.getRowAmount()));
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
