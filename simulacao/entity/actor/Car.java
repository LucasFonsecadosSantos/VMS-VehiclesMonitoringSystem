package simulacao.entity.actor;

import simulacao.Localizacao;
import simulacao.entity.hindrance.Product;

import java.util.List;
import java.util.ArrayList;

public class Car extends Vehicle {

    private List<Product> productList;

    private int gasLevel;

    public Car(Localizacao location) {

        super(location);

    }

    public void increaseGasLevel() {
        this.gasLevel++;
    }

    public void decreaseGasLevel() {
        this.gasLevel--;
    }

    public int getGasLevel() {
        return this.gasLevel;
    }

    public void retriveProduct(Product product) {

        this.productList.add(product);

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