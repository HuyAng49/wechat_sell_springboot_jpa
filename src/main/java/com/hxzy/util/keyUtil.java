package com.hxzy.util;

import java.util.Random;

public class keyUtil {

    public static synchronized String generateKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
