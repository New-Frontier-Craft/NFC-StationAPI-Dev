package net.newfrontiercraft.nfc.utils;

import java.util.Random;

public class RandomUtil {

    private final Random random;

    public RandomUtil(Random random) {
        this.random = random;
    }

    public int bidirectionalRandom(int range) {
        int result = random.nextInt(range);
        if (random.nextBoolean()) {
            result *= -1;
        }
        return result;
    }

}
