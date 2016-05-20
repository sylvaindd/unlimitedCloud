package utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by sylva on 19/05/2016.
 */
public class Security {
	private static SecureRandom random = new SecureRandom();

	public static String generateToken() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz-_+=()$*€";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 25) {
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		return salt.toString();

	}
}
