package simulacao.entity.actor;

import simulacao.Localizacao;
import simulacao.Mapa;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public abstract class Vehicle implements SimulatorActor {

    private Localizacao currentLocation;

    private Localizacao nextLocation;

    private Image imagem;

    public Vehicle(Localizacao localizacao, String iconPath) {
        
        this.currentLocation = localizacao;
        nextLocation = null;
        imagem = new ImageIcon(getClass().getResource(iconPath)).getImage();

    }

    public Localizacao getCurrentLocation() {
        return currentLocation;
    }

    public Localizacao getNextLocation() {
        return nextLocation;
    }
    
    public Image getImagem(){
        return imagem;
    }

    public void setLocalizacaoAtual(Localizacao currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setLocalizacaoDestino(Localizacao nextLocation) {
        this.nextLocation = nextLocation;
    }
     
}
