package day0918.bobing;

import java.security.SecureRandom;

public class Util {
    public static final SecureRandom RANDOM = new SecureRandom();
    public static int num(int num) {
        return RANDOM.nextInt(num);
    }
}
