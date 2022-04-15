package simulacao;

import simulacao.entity.actor.Vehicle;
import simulacao.entity.actor.Car;
import simulacao.entity.actor.Bike;
import simulacao.entity.hindrance.TrafficLightHindrance;
import simulacao.entity.provider.Provider;

import java.util.Map;
import java.util.HashMap;

/**
 * Representa um mapa com todos os itens que participam da simulacao
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Mapa {

    private Vehicle[][] itens;

    private Map<Provider, Localizacao> providers;

    private Map<TrafficLightHindrance, Localizacao> TrafficLights;
    
    private int largura;
    
    private int altura;
    
    private static final int LARGURA_PADRAO = 35;
    
    private static final int ALTURA_PADRAO = 35;
    
    /**
     * Cria mapa para alocar itens da simulacao.
     * @param largura: largura da área de simulacao.
     * @param altura: altura da área de simulação.
     */
    public Mapa(int largura, int altura) {

        this.largura = largura;
        this.altura = altura;
        itens = new Vehicle[altura][largura];

    }
    /**
     * Cria mapa com tamanho padrao.
     */
    public Mapa(){
        this(LARGURA_PADRAO,ALTURA_PADRAO);
    }
    
    public void adicionarItem(Vehicle v){
        itens[v.getLocalizacaoAtual().getX()][v.getLocalizacaoAtual().getY()] = v;
    }
    
    public void removerItem(Vehicle v){
        itens[v.getLocalizacaoAtual().getX()][v.getLocalizacaoAtual().getY()] = null;
    }
    
    public void atualizarMapa(Vehicle v){
        removerItem(v);
        adicionarItem(v);
    }
    
    public Vehicle getItem(int x, int y){
        return itens[x][y];
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }
    
}
