package simulacao;

import java.util.Random;

/**
 * Representa uma localização no mapa
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Location {

    private int x;

    private int y;

    private static Random rand = new Random();
    
    /**
     * Representa uma localização na cidade
     * @param x Coordenada x: deve ser maior ou igual a 0.
     * @param y Coordenada y: deve ser maior ou igual a 0.
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    /**
     * Gera a localizacao para se mover visando alcançar o destino
     * @param nextLocation: localizacao que se deseja alcancar.
     * @return Localizacao para onde se deve ir
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
            
            if(deslocX != 0 && deslocY != 0) {//Se nenhuma coordenada coincide com a localizacao destino
                
                if(rand.nextInt(2) == 0) {//Atualizar x
                    newLocation = new Location(x + deslocX, y);
                } else {//Atualizar y
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
     *         false: caso contrario.
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
     * @return A representacao da localizacao.
     */
    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
    
    
}
