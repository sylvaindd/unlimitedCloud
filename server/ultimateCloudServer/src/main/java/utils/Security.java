package utils;

import java.security.SecureRandom;

/**
 * Created by sylva on 19/05/2016.
 */
public class Security {
    private static SecureRandom random = new SecureRandom();

    public static String generateToken() {
        byte bytes[] = new byte[30];
        random.nextBytes(bytes);
        return bytes.toString();
    }
}
