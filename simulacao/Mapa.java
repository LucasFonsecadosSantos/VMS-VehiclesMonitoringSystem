package simulacao;

import simulacao.entity.actor.Vehicle;
import simulacao.entity.actor.Car;
import simulacao.entity.actor.Bike;
import simulacao.entity.hindrance.TrafficLightHindrance;
import simulacao.entity.provider.Provider;

import java.util.Map;
import java.util.HashMap;

/**
 * Representa um mapa com todos os items que participam da simulacao
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Mapa {

    private Vehicle[][] items;

    private Map<Provider, Localizacao> providers;

    private Map<TrafficLightHindrance, Localizacao> trafficLights;
    
    private int columnAmount;
    
    private int rowAmount;
    
    private static final int _DEFAULT_COLUMN_AMOUNT_ = 35;
    
    private static final int _DEFAULT_ROW_AMOUNT_ = 35;
    
    /**
     * Cria mapa para alocar items da simulacao.
     * @param largura: largura da área de simulacao.
     * @param altura: altura da área de simulação.
     */
    public Mapa(int columnAmount, int rowAmount) {

        this.columnAmount = columnAmount;
        this.rowAmount = rowAmount;
        initLists();
        items = new Vehicle[columnAmount][rowAmount];

    }
    /**
     * Cria mapa com tamanho padrao.
     */
    public Mapa(){
        this(_DEFAULT_COLUMN_AMOUNT_, _DEFAULT_ROW_AMOUNT_);
    }

    private void initLists() {

        this.providers = new HashMap<>();
        this.trafficLights = new HashMap<>();

    }
    
    public void addVehicle(Vehicle v){
        items[v.getCurrentLocation().getX()][v.getCurrentLocation().getY()] = v;
    }

    public void addProvider(Provider provider){
        providers.put(provider, provider.getLocation());
    }
    
    public void addTrafficLight(TrafficLightHindrance trafficLight){
        trafficLights.put(trafficLight, trafficLight.getLocation());
    }

    public void removeVehicle(Vehicle v){
        items[v.getCurrentLocation().getX()][v.getCurrentLocation().getY()] = null;
    }
    
    public void updateMap(Vehicle vehicle) {

        removeVehicle(vehicle);
        addVehicle(vehicle);        
        
    }

    public boolean isAllowToContinue(Localizacao currentLocation) {

        return !hasRedTrafficLightAtPosition(currentLocation);

    }

    private boolean hasRedTrafficLightAtPosition(Localizacao currentLocation) {

        TrafficLightHindrance trafficLight;
        Localizacao trafficLightLocation;

        for (Map.Entry<TrafficLightHindrance, Localizacao> entry : trafficLights.entrySet()) {

            trafficLight = entry.getKey();
            trafficLightLocation = entry.getValue();

            if (currentLocation.getX() == trafficLightLocation.getX() && currentLocation.getY() == trafficLightLocation.getY()) {
                return trafficLight.getState().equals(TrafficLightHindrance._RED_);
            }
        }

        return false;

    }

    public boolean isNotNextLocationOccupied(Localizacao nextLocation) {
        
        return getItem(nextLocation.getX(), nextLocation.getY()) == null;

    }

    // public boolean hasGasStationAtCurrentLocation(Localizacao location) {

    //     return 

    // }

    public Vehicle getItem(int x, int y) {
        return items[x][y];
    }

    public int getColumnAmount() {
        return columnAmount;
    }

    public int getRowAmount() {
        return rowAmount;
    }
    
}
