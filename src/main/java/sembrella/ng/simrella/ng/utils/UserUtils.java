package sembrella.ng.simrella.ng.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserUtils {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String hashPassword (String plainPassword){
        return passwordEncoder.encode(plainPassword);
    }
    public static boolean verifyPassword(String plainPassword, String hashedPassword){
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }
}
