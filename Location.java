package simulation;

import java.util.Random;

import simulation.application.SimulationMap;
import simulation.util.Randomizer;

/**
 * Classe utilizada para determinar uma localização no mapa por meio da utilização de coordenadas.

 * Exemplo: x = 10, y = 20, logo o elemento estará localizado na posição (10,20)
 * 
 */
public class Location {

    private int x; // declarando o elemento que irá armazenar a posicao do eixo x do mapa

    private int y; // declarando o elemento que irá armazenar a posicao do eixo y do mapa

    private static Random rand = new Random(); // Criando elemento do tipo random para sortear a localização dos elementos
    
    /**
     * Representa uma localização na cidade
     * @param x representa a coordenada x, devendo esta ser maior ou igual a 0.
     * @param y representa a coordenada y, devendo esta ser maior ou igual a 0.
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retorna o valor atribuído a variável x, representando a posição do elemento na cidade
     * @return posicao do elemento no eixo x, sendo este um valor inteiro
     */
    public int getX() {
        return x;
    }

    /**
     * Retorna o valor atribuído a variável y, representando a posição do elemento na cidade
     * @return posição do elemento no eixo y, sendo este um valor inteiro
     */
    public int getY() {
        return y;
    }
    
    /**
     * Gera a localizacao para que o elemento possa se mover e alcançar o destino
     * @param nextLocation: próximo ponto de localizacao a ser alcançado, definido de forma aleatória
     * @return Retorna um objeto do tipo "Location" contendo a proxima localização para a qual o elemento deve ir
     */
    public Location calculateNextLocation(Location nextLocation){
        
        if(nextLocation.equals(this)){//Verifica se já alcancou o destino
            
            return nextLocation;

        }else{

            int destX = nextLocation.getX();
            int destY = nextLocation.getY();
            int deslocX = x < destX ? 1 : x > destX ? -1 : 0;//Deslocamento de 1 ou 0 ou -1 posição em x
            int deslocY = y < destY ? 1 : y > destY ? -1 : 0;//Deslocamento de 1 ou 0 ou -1 posição em y
            
            Location newLocation;
            
            if(deslocX != 0 && deslocY != 0) { //Verifica se nenhuma coordenada coincide com a localização destino
                
                if(rand.nextInt(2) == 0) {//Atualiza o valor de x
                    newLocation = new Location(x + deslocX, y);
                } else {//Atualiza o valor de y
                    newLocation = new Location(x, y + deslocY);
                }
            } else {
                
                newLocation = (deslocX != 0) ? 
                                new Location(x + deslocX, y) :
                                new Location(x, y + deslocY);
                
            }
            return newLocation;
        }
    }
    
    /**
     * Verificacao de igualdade de conteudo de objetos do tipo Localizacao.
     * @return true: se a localizacao é igual.
     *         false: caso a localizacao não seja igual.
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }else if(!(obj instanceof Location)){
            return false;
        }else{
            Location outro = (Location) obj;
            return x == outro.x && y == outro.y;
        }
    }
    
    
    /**
     * Método utilizado para pegar aleatoriamente uma nova localizacao
     * @param map - recebe como parâmetro um mapa da simulacao para definicao de um elemento
     * @return um objeto do tipo "Location" contendo a nova localização gerada aleatoriamente
     */
    public static Location getNewRandomLocation(SimulationMap map) {
        return new Location(Randomizer.getRandomInteger(map.getColumnAmount()),Randomizer.getRandomInteger(map.getRowAmount()));
    }

    /**
     * @return Retorna a representacao da localizacao por meio da utilização do modelo de coordenadas cartesianas(x,y)
     */
    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
    
    
}
