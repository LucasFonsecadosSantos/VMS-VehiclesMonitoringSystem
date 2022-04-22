package simulation.util;

import java.util.Random;

/**
 * Classe responsável por gerar valores aleatoriamente para permitir o sorteio de inicializações do sistema
 */
public class Randomizer {

    /**
     * Método para obter um valor inteiro aleatório entre um intervalo de valores 
     * @param lowerLimit - menor valor a ser utilizado no sorteio
     * @param upperLimit - valor limite para realização do sorteio
     * @return retorna um inteiro com o valor sorteado
     */
    public static int getRandomInteger(int lowerLimit, int upperLimit) {

        Random rand = new Random();
        return rand.nextInt((upperLimit - lowerLimit) + 1);

    }

    /**
     * Método para obter um valor inteiro tendo apenas um limite máximo
     * @param upperLimit - valor limite para realização do sorteio
     * @return retorna um inteiro com o valor sorteado
     */
    public static int getRandomInteger(int upperLimit) {

        Random rand = new Random();
        return rand.nextInt(upperLimit);

    }

}