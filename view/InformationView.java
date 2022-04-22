package simulation.view;

import java.awt.*;
import javax.swing.*;

/**
 * Classe para criar uma GUI para mostrar informações em uma tela a parte da simulação
 */

public class InformationView extends JFrame {

    private JLabel iterationLabel; 

    private JLabel iteration02Label; 

    private JLabel carProductAmountLabel01; 

    private JLabel carProductAmountLabel02;

    private JLabel carWeightLabel01;

    private JLabel carWeightLabel02;

    private JLabel visitedProviderAmountLabel01;

    private JLabel visitedProviderAmountLabel02;

    private int visitedProviderAmount;

    /**
     * Construtor da classe que inicializa as propriedades e builda os componentes
    */
    public InformationView() {

        initializeProperties();
        buildComponents();
        buildLayout();

    }

    /**
     * Método para inicializar as propriedades da simulação 
    */
    private void initializeProperties() {

        visitedProviderAmount = 0;
        setTitle("Informations of Simulation");
        setSize(500,600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    /**
     * Método para construir os componentes
     */
    private void buildComponents() {

        buildLabelComponents();

    }

    /**
     * Método para construir as labels dos componentes
     */
    private void buildLabelComponents() {

        this.iterationLabel = new JLabel("Iteração: ");
        this.iteration02Label = new JLabel("");
        this.carProductAmountLabel01 = new JLabel("Car collected product amount: ");
        this.carProductAmountLabel02 = new JLabel("");
        this.carWeightLabel01 = new JLabel("Car weight: ");
        this.carWeightLabel02 = new JLabel("");
        this.visitedProviderAmountLabel01 = new JLabel("Visited provider amount: ");
        this.visitedProviderAmountLabel02 = new JLabel("");

    }

    /**
     * Método para construir o layout
     */
    private void buildLayout() {

        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        getContentPane().add(this.iterationLabel);
        getContentPane().add(this.iteration02Label);

    }

    /**
     * Método para atualizar a quantidade de iterações
     * @param stepNumber - recebe a quantidade de passos referente ao número de iterações
     */
    public void updateIterationAmount(int stepNumber) {

        this.iteration02Label.setText(String.valueOf(stepNumber));

    }

    /**
     * Método para atualizar a quantidade de peso carregado pelo carro
     * @param weight - recebe o peso que está sendo carregado pelo carro
     */
    public void updateCarWeightInformation(int weight) {

        this.carWeightLabel02.setText(String.valueOf(weight));

    }
    
    /**
     * Método que conta a quantidade de fornecedores visitados
     */
    public void increaseVisitedProviderAmount() {

        this.visitedProviderAmount++;
        this.visitedProviderAmountLabel02.setText(String.valueOf(this.visitedProviderAmount));

    }

    /**
     * Método para atualizar a quantidade de produtos coletados pelo carro
     * @param amount - recebe a quantidade de produtos coletados pelo carro
     */
    public void updateCollectedProductAmount(int amount) {

        this.carProductAmountLabel02.setText(String.valueOf(amount));

    }



}