package simulacao;

import simulacao.entity.actor.SimulationActor;
import simulacao.entity.actor.TrafficLightActor;
import simulacao.entity.actor.VehicleActor;
import simulacao.entity.actor.CarActor;
import simulacao.entity.actor.BikeActor;
import simulacao.entity.provider.Provider;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.List;

/**
 * Representa um mapa com todos os actors que participam da simulacao
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class SimulationMap {

    private SimulationActor[][] actors;

    private Map<Provider, Location> providers;

    private Map<TrafficLightActor, Location> trafficLights;
    
    private int columnAmount;
    
    private int rowAmount;
    
    private static final int _DEFAULT_COLUMN_AMOUNT_ = 35;
    
    private static final int _DEFAULT_ROW_AMOUNT_ = 35;
    
    /**
     * Cria mapa para alocar actors da simulacao.
     * @param largura: largura da área de simulacao.
     * @param altura: altura da área de simulação.
     */
    public SimulationMap(int columnAmount, int rowAmount) {

        this.columnAmount = columnAmount;
        this.rowAmount = rowAmount;
        initLists();
        actors = new VehicleActor[columnAmount][rowAmount];

    }
    /**
     * Cria mapa com tamanho padrao.
     */
    public SimulationMap(){
        this(_DEFAULT_COLUMN_AMOUNT_, _DEFAULT_ROW_AMOUNT_);
    }

    private void initLists() {

        this.providers = new HashMap<>();
        this.trafficLights = new HashMap<>();

    }
    
    public void addVehicle(VehicleActor v){
        actors[v.getCurrentLocation().getX()][v.getCurrentLocation().getY()] = v;
    }

    public void addProvider(Provider provider){
        providers.put(provider, provider.getLocation());
    }

    public void addTrafficLight(TrafficLightActor trafficLight){
        trafficLights.put(trafficLight, trafficLight.getCurrentLocation());
    }

    public void removeVehicle(VehicleActor v){
        actors[v.getCurrentLocation().getX()][v.getCurrentLocation().getY()] = null;
    }
    
    public void updateMap(VehicleActor vehicle) {

        removeVehicle(vehicle);
        addVehicle(vehicle);        
        
    }

    public boolean isAllowToContinue(Location currentLocation) {

        return !hasRedTrafficLightAtPosition(currentLocation);

    }

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

    public boolean isNotNextLocationOccupied(Location nextLocation) {
        
        return getActor(nextLocation.getX(), nextLocation.getY()) == null;

    }

    // public boolean hasGasStationAtCurrentLocation(Localizacao location) {

    //     return 

    // }

    public SimulationActor getActor(int x, int y) {
        return actors[x][y];
    }

    public TrafficLightActor getTrafficLightAtCoordinates(int x, int y) {
        
        for(Map.Entry<TrafficLightActor, Location> entry : trafficLights.entrySet()) {

            if (entry.getValue().getX() == x && entry.getValue().getY() == y) {
                return entry.getKey();
            }
        }
        return null;

    }

    public Provider getProviderAtCoordinates(int x, int y) {

        for(Map.Entry<Provider, Location> entry : providers.entrySet()) {
            
            if (entry.getValue().getX() == x && entry.getValue().getY() == y) {
                return entry.getKey();
            }
        }
        return null;

    }

    public int getColumnAmount() {
        return columnAmount;
    }

    public int getRowAmount() {
        return rowAmount;
    }
    
}
