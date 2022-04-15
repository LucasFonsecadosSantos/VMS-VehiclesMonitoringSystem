package simulacao.util;

import java.util.Random;

public class Randomizer {

    public static int getRandomInteger(int lowerLimit, int upperLimit) {

        Random rand = new Random();
        return rand.nextInt((upperLimit - lowerLimit) + 1);

    }

    public static int getRandomInteger(int upperLimit) {

        Random rand = new Random();
        return rand.nextInt(upperLimit);

    }

}