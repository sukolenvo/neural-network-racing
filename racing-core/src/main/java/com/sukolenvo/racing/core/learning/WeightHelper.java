package com.sukolenvo.racing.core.learning;

import java.util.Random;

public class WeightHelper {

    private static final Random random = new Random();

    public static double getRandowmWeight() {
        return random.nextDouble() * 2 - 1;
    }

    public static boolean rollSwap() {
        return random.nextInt(100) < 60;
    }
}
