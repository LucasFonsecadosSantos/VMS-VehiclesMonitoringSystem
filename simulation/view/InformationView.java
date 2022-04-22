package simulation.view;

import java.awt.*;
import javax.swing.*;

public class InformationView extends JFrame {

    private JPanel panel;
    
    private JLabel iterationLabel;

    private JLabel iteration02Label;

    private JLabel carProductAmountLabel01;

    private JLabel carProductAmountLabel02;

    private JLabel carWeightLabel01;

    private JLabel carWeightLabel02;

    private JLabel visitedProviderAmountLabel01;

    private JLabel visitedProviderAmountLabel02;

    private int visitedProviderAmount;

    public InformationView() {

        initializeProperties();
        buildComponents();
        buildLayout();

    }

    private void initializeProperties() {

        visitedProviderAmount = 0;
        setTitle("Informations of Simulation");
        setSize(500,600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void buildComponents() {

        buildLabelComponents();

    }

    private void buildLabelComponents() {

        this.iterationLabel = new JLabel("Iteração: ");
        this.iteration02Label = new JLabel("");
        this.carProductAmountLabel01 = new JLabel("Car collected product amount: ");
        this.carProductAmountLabel02 = new JLabel("");
        this.carWeightLabel01 = new JLabel("Car weight: ");
        this.carWeightLabel02 = new JLabel("");
        this.visitedProviderAmountLabel01 = new JLabel("Visited provider amount: ");
        this.visitedProviderAmountLabel02 = new JLabel("");
        this.panel = new JPanel();

    }

    private void buildLayout() {
        
        panel.setLayout(new GridLayout(4, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(this.iterationLabel);
        panel.add(this.iteration02Label);
        panel.add(this.carProductAmountLabel01);
        panel.add(this.carProductAmountLabel02);
        panel.add(this.carWeightLabel01);
        panel.add(this.carWeightLabel02);
        panel.add(this.visitedProviderAmountLabel01);
        panel.add(this.visitedProviderAmountLabel02);

        getContentPane().add(panel);
    }

    public void updateIterationAmount(int stepNumber) {

        this.iteration02Label.setText(String.valueOf(stepNumber));

    }

    public void updateCarWeightInformation(int weight) {

        this.carWeightLabel02.setText(String.valueOf(weight));

    }

    public void increaseVisitedProviderAmount() {

        this.visitedProviderAmount++;
        this.visitedProviderAmountLabel02.setText(String.valueOf(this.visitedProviderAmount));

    }

    public void updateCollectedProductAmount(int amount) {

        this.carProductAmountLabel02.setText(String.valueOf(amount));

    }



}