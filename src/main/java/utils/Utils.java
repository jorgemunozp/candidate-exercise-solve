package utils;

import java.util.Random;

public class Utils {

    public static int validRandom(){
        Random random = new Random();
        return random.nextInt(5) + 1;
    }

    public static int invalidRandom(){
        Random random = new Random();
        return random.nextInt(100) + 10;
    }

}
