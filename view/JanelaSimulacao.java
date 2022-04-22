package simulation.view;

import java.awt.*;
import javax.swing.*;

import simulation.Location;
import simulation.application.SimulationMap;
import simulation.entity.actor.BikeActor;
import simulation.entity.actor.CarActor;
import simulation.entity.actor.SimulationActor;
import simulation.entity.actor.TrafficLightActor;
import simulation.entity.actor.VehicleActor;
import simulation.entity.mapfacilities.MapFacility;
import simulation.entity.provider.Provider;

/**
 * Classe que permite a visualização da simulação
 */
public class JanelaSimulacao extends JFrame{
    private SimulationMap map; // variavel que armazena o mapa
    private VisaoMapa visaoMapa; // variavel que armazena uma visão do mapa
    private InformationView infoView; // variavel que armazena uma informacao da view
    /**
     * Construtor da classe JanelaSimulacao, instanciando os elementos necessários
     * @param map - recebe um mapa por parâmetro para localização e montagem da janela de simulação
     */
    public JanelaSimulacao(SimulationMap map){
        this.map = map;
        visaoMapa = new VisaoMapa(map.getColumnAmount(),map.getRowAmount());
        getContentPane().add(visaoMapa);
        setTitle("Simulator");
        setSize(1000,1000);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        infoView = new InformationView();
    }
    
    /**
     * Método responsável por executar a ação e apresentar o estado atual do mapa
    */
    public void executarAcao(){
        visaoMapa.preparePaint();
        int rowAmount = map.getRowAmount();
        for(int i = 0; i < rowAmount; i++){
            int columnAmount = map.getColumnAmount();
            for(int j = 0; j < columnAmount; j++){
                
                TrafficLightActor trafficLight = map.getTrafficLightAtCoordinates(i, j);
                Provider provider = map.getProviderAtCoordinates(i, j);
                MapFacility facility = map.getFacilityAtCoordinates(i, j);

                if (provider != null) {
                    visaoMapa.desenharImagem(i, j, provider.getImage());
                }
                if (trafficLight != null) {
                    visaoMapa.desenharImagem(i, j, trafficLight.getImage());
                }
                if (facility != null) {
                    visaoMapa.desenharImagem(i, j, facility.getImage());
                }
                if(map.getActor(i, j) != null){//Se existir algum objeto na posicao (i,j)
                    SimulationActor veiculo = map.getActor(i, j);          
                    
                    Location location = veiculo.getCurrentLocation();
                    if (veiculo != null) {
                        visaoMapa.desenharImagem(location.getX(), location.getY(), veiculo.getImage());
                    }
                    
                    
                }
            }
        }
        visaoMapa.repaint();
    }
    
    /**
     * Método para atualizar o peso carregado pelo carro
     * @param weight - recebe o novo peso a ser carregado pelo carro
     */
    public void updateCarWeightInformation(int weight) {
        this.infoView.updateCarWeightInformation(weight);
    }
    
    /**
     * Método para atualizar a quantidade de produtos coletados
     * @param amount - recebe a quantidade de produtos coletados pelo carro
     */
    public void updateCollectedProductAmountInformation(int amount) {
        this.infoView.updateCollectedProductAmount(amount);;
    }
    
    /**
     * Classe que fornece uma visualizacao grafica do mapa
     */    
    private class VisaoMapa extends JPanel{

        private final int VIEW_SCALING_FACTOR = 6;

        private int larguraMapa, alturaMapa;

        private int xScale, yScale;

        private Dimension tamanho;

        private Graphics g;

        private Image imagemMapa;

        /**
         * Método para criar um novo componente do tipo VisaoMapa.
         */
        public VisaoMapa(int largura, int altura)
        {
            larguraMapa = largura;
            alturaMapa = altura;
            setBackground(Color.white);
            tamanho = new Dimension(0, 0);
        }

        /**
         * Método para informar para o gerenciador da interface o tamanho do mapa.
         */
        public Dimension getPreferredSize()
        {
            return new Dimension(larguraMapa * VIEW_SCALING_FACTOR,
                                 alturaMapa * VIEW_SCALING_FACTOR);
        }
        
        /**
         * Método para preparar para um novo ciclo de exibição. Uma vez que o componente
         * pode ser redimensionado, calcula o "fator de escala" novamente.
         */
        public void preparePaint()
        {
            if(!tamanho.equals(getSize())) {  // se o tamanho mudou...
                tamanho = getSize();
                imagemMapa = visaoMapa.createImage(tamanho.width, tamanho.height);
                g = imagemMapa.getGraphics();

                xScale = tamanho.width / larguraMapa;
                if(xScale < 1) {
                    xScale = VIEW_SCALING_FACTOR;
                }
                yScale = tamanho.height / alturaMapa;
                if(yScale < 1) {
                    yScale = VIEW_SCALING_FACTOR;
                }
            }
            g.setColor(Color.white);
            g.fillRect(0, 0, tamanho.width, tamanho.height);
            g.setColor(Color.gray);
            for(int i = 0, x = 0; x < tamanho.width; i++, x = i * xScale) {
                g.drawLine(x, 0, x, tamanho.height - 1);
            }
            for(int i = 0, y = 0; y < tamanho.height; i++, y = i * yScale) {
                g.drawLine(0, y, tamanho.width - 1, y);
            }
        }
        
        /**
         * Método que desenha a imagem para um determinado item
         */
        public void desenharImagem(int x, int y, Image image)
        {
            g.drawImage(image, x * xScale + 1, y * yScale + 1,
                        xScale - 1, yScale - 1, this);
        }

        /**
         * Método que permite que o componente VisaoMapa seja reexibido. Copia a
         * imagem interna para a tela.
         */
        public void paintComponent(Graphics g)
        {
            if(imagemMapa != null) {
                g.drawImage(imagemMapa, 0, 0, null);
            }
        }
    }
    
}
