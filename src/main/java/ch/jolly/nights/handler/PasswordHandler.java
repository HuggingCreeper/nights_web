package ch.jolly.nights.handler;

import java.security.MessageDigest;
import java.util.ArrayList;

public class PasswordHandler {
    private static final String INSTANCE = "MD5";
    private static MessageDigest md;


    public static String hashIt(String password) {
        ArrayList<String> bytes = new ArrayList<>();
        String salt = "!?/kijXXcndQ";
        String pepper = "poiDDkauE,.-";

        try {
            md = MessageDigest.getInstance(INSTANCE);
        } catch (Exception e) {
            System.out.println("MessageDigest.getInstance failed");
        }

        String newPassword = salt + password + pepper;

        StringBuilder hash = new StringBuilder();
        md.update(newPassword.getBytes());
        byte[] digest = md.digest();
        for (byte d : digest) {
            String nextbyte = Integer.toHexString(d & 0xFF).toUpperCase();
            if (nextbyte.length() < 2) {
                nextbyte = "0" + nextbyte;
            }
            bytes.add(nextbyte);
            hash.append(nextbyte);
        }
        newPassword = hash.toString();

        return newPassword;
    }
}
