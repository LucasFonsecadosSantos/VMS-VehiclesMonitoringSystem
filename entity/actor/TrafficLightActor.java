package simulation.entity.actor;

import javax.swing.ImageIcon;

import simulation.Location;
import simulation.application.SimulationMap;
import simulation.util.Randomizer;

import java.awt.Image;

/**
 * Classe para controlar os semáforos utilizados como obstáculo no jogo
 */

public class TrafficLightActor extends SimulationActor {

    private String state; // variável indicar o status do semáforo

    public static final String _RED_ = "red"; // variável para armazenar o comportamento "vermelho" do semáforo

    public static final String _GREEN_ = "green"; // variável para armazenar o comportamento "verde" do semáforo

    private static final String _ICON_PATH_RED_STATE_ = "./../../Imagens/redSign.jpeg"; // caminho da imagem "vermelha" do semáforo

    private static final String _ICON_PATH_GREEN_STATE_ = "./../../Imagens/greenSign.jpeg"; // caminho da imagem "verde" do semáforo

    private static final int _CHANGE_STATE_FACTOR_ = 4; // Fator de modificação do estado do semáforo, mudando o status do semáfoto a cada 4 iterações

    /**
     * Construtor da classe para inicializar os semáforos aleatoriamente e informar a superclasse a localização de cada um
     * @param location - recebe uma localização por parâmetro, referente à localização do semáforo
     */
    public TrafficLightActor(Location location) {

        super(location, TrafficLightActor._ICON_PATH_GREEN_STATE_);
        setState();
        
    }

    /**
     * Método para realizar a troca do status do semáforo
     * @param map - recebe o mapa por parâmetro
     * @param step - recebe a quantidade de passos a serem executados durante a interação
     */
    @Override
    public void executeStep(SimulationMap map, int step) {

        if (step % TrafficLightActor._CHANGE_STATE_FACTOR_ == 0) {
            changeState();
        }

    }

    /**
     * Método para trocar o status do semáforo - Exemplo: se estiver verde passa a ser vermelho e vice-versa, atualizando também a foto
     */
    public void changeState() {

        setState(getState().equals(TrafficLightActor._RED_) ? TrafficLightActor._GREEN_ : TrafficLightActor._RED_);
        this.image = new ImageIcon(getClass().getResource(getState().equals(TrafficLightActor._RED_) ? TrafficLightActor._ICON_PATH_GREEN_STATE_ : TrafficLightActor._ICON_PATH_RED_STATE_)).getImage();

    }

    /**
     * Método para alterar o status de um semáforo de forma aleatória, permitindo que semáforos inicializem com cores de forma aleatória
     */
    public void setState() {
        this.state = Randomizer.getRandomInteger(1,2) == 1 ? TrafficLightActor._RED_ : TrafficLightActor._GREEN_;
    }

    /**
     * Método para alterar o status de um semáforo a partir de um parâmetro recebido
     * @param state - status a ser atualizado no semáforo
     */
    public void setState(String state) {

        this.state = state;

    }

    /**
     * Método para pegar o status atual de um semáforo
     * @return retorna uma String contendo o status do semáforo (se a luz é igual a verde ou a vermelha)
     */
    public String getState() {

        return this.state;

    }

}