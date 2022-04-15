package simulacao;

import simulacao.entity.actor.Vehicle;
import simulacao.entity.actor.Car;
import simulacao.entity.actor.Bike;

import java.util.Random;
/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulation {
    
    private Vehicle veiculo;
    
    private JanelaSimulacao janelaSimulacao;
    
    private Mapa mapa;
    
    public Simulation() {

        Random rand = new Random(12345);
        mapa = new Mapa();
        int largura = mapa.getLargura();
        int altura = mapa.getAltura();
        veiculo = new Car(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));//Cria um veiculo em uma posicao aleatoria
        veiculo.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));//Define a posicao destino aleatoriamente
        mapa.adicionarItem(veiculo);//Inicializando o mapa com o veículo
        janelaSimulacao = new JanelaSimulacao(mapa);

    }
    
    public void executeSimulation(int numPassos){

        janelaSimulacao.executarAcao();
        for (int i = 0; i < numPassos; i++) {
            executarUmPasso();
            esperar(100);
        }

    }

    private void executarUmPasso() {
        mapa.removerItem(veiculo);
        veiculo.executeStep();
        mapa.adicionarItem(veiculo);
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
