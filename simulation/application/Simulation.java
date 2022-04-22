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
import simulation.view.InformationView;
import simulation.view.JanelaSimulacao;

import java.util.ArrayList;

/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulation {
    
    private CarActor vehicle;
    
    private JanelaSimulacao janelaSimulacao;

    private List<VehicleActor> bikeList;

    private List<TrafficLightActor> trafficLightList;

    private List<Provider> providerList;

    private List<MapFacility> mapFacilityList;

    private static final double _MAX_PERCENT_PROVIDER_AMOUNT_ = .02;

    private static final double _MIN_PERCENT_PROVIDER_AMOUNT_ = .01;

    private static final double _MAX_PERCENT_TRAFFIC_LIGHT_AMOUNT_ = .15;

    private static final double _MIN_PERCENT_TRAFFIC_LIGHT_AMOUNT_ = .05;
    
    private SimulationMap map;
    
    public Simulation() {

        initSimultion();

    }
    
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

    private List<MapFacility> initMapFacilities() {

        List<MapFacility> facilityList = new ArrayList<>();

        facilityList = initRestaurant(facilityList);

        return facilityList;

    }

    private int getPercentageFromMapSize(double percentage) {
        return (int) Math.round(this.map.getColumnAmount() * this.map.getRowAmount() * percentage);
    }

    private List<MapFacility> initRestaurant(List<MapFacility> facilityList) {

        facilityList.add(new Restaurant(Location.getNewRandomLocation(this.map)));
        return facilityList;

    }

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

    private void initSimulationMap(List<VehicleActor> bikeList, List<Provider> providerList, List<TrafficLightActor> trafficLightList, List<MapFacility> facilityList) {
        
        addFacilitiesToMap(facilityList);
        addVehiclesToMap(bikeList);
        addProvidersToMap(providerList);
        addTrafficLightsToMap(trafficLightList);

    }

    private void addFacilitiesToMap(List<MapFacility> facilityList) {

        for (MapFacility facility : facilityList) {
            this.map.addFacility(facility);
        }

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
        
        int bikeAmount = Randomizer.getRandomInteger(0,10);

        for (int i=0; i < bikeAmount; i++) {
            bikeList.add(initBike());
        }

        return bikeList;

    }

    private CarActor initMainVehicle() {

        this.vehicle = new CarActor(Location.getNewRandomLocation(this.map));
        this.vehicle.setDestinationLocationList(initNewDestinationLocationBasedInProviders());

        return this.vehicle;
    }

    private VehicleActor initBike() {

        VehicleActor bike = new BikeActor(Location.getNewRandomLocation(this.map));
        bike.setNextLocation(Location.getNewRandomLocation(this.map));

        return bike;

    }

    private List<Location> initNewDestinationLocationBasedInProviders() {
        
        List<Location> providerLocationList = new ArrayList<>();

        for (Provider provider : this.providerList) {

            providerLocationList.add(provider.getLocation());

        }

        return providerLocationList;

    }

    public void executeSimulation(int stepAmount){

        janelaSimulacao.executarAcao();
        for (int i = 0; i < stepAmount; i++) {
            executeIteration(i);
            simulationDelay(200);
        }

    }

    private void executeIteration(int stepNumber) {

        executeTrafficLightIteration(stepNumber);
        executeBikeIteration(stepNumber);
        executeVehicleIteration(stepNumber);
        
        updateInformationsView(stepNumber);

        janelaSimulacao.executarAcao();
        
    }

    private void updateInformationsView(int stepNumber) {

        this.janelaSimulacao.updateCarWeightInformation(this.vehicle.getWeight());
        this.janelaSimulacao.updateCollectedProductAmountInformation(this.vehicle.getCollectedProductAmount());
        this.janelaSimulacao.updateIterationAmount(stepNumber);
        this.janelaSimulacao.increaseVisitedProviderAmount(stepNumber);
    }

    private void executeTrafficLightIteration(int step) {

        trafficLightList.forEach(traffictLight -> {
            traffictLight.executeStep(map, step);
        });

    }

    private void executeBikeIteration(int step) {

        bikeList.forEach(vehicle -> {
            map.removeVehicle(vehicle);
            vehicle.executeStep(map, step);
            map.addVehicle(vehicle);
        });

    }

    private void executeVehicleIteration(int step) {

        map.removeVehicle(vehicle);
        this.vehicle.executeStep(map, step);
        map.addVehicle(vehicle);

    }
    
    private void simulationDelay(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    
}
