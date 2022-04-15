package simulacao.entity.actor;

import simulacao.Localizacao;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public abstract class Vehicle implements SimulatorActor {

    private Localizacao localizacaoAtual;

    private Localizacao localizacaoDestino;

    private Image imagem;

    public Vehicle(Localizacao localizacao) {
        
        this.localizacaoAtual = localizacao;
        localizacaoDestino = null;
        imagem = new ImageIcon(getClass().getResource("Imagens/veiculo.jpg")).getImage();

    }

    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }
    
    public Image getImagem(){
        return imagem;
    }

    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }

    @Override
    public void executeStep(){

        Localizacao destino = getLocalizacaoDestino();

        if(destino != null) {

            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(getLocalizacaoDestino());
            setLocalizacaoAtual(proximaLocalizacao);

        }

    }
     
}
